package cu.uci.auctoritas.repository;

import cu.uci.auctoritas.domain.CorporateAuthor;
import cu.uci.auctoritas.domain.PersonalAuthor;
import cu.uci.auctoritas.service.DatasourceService;
import cu.uci.auctoritas.source.Datasource;
import cu.uci.auctoritas.source.DatasourceResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bichos on 19/05/16.
 */
@Repository
public class AuthorRepository {
    @Autowired
    private DatasourceService datasourceService;

    public <T> void insertAuthor(String uri, String name, String lastname, String authority, Datasource ds, Class<T> clazz, String table) {
        DatasourceResolver<T> resolver = datasourceService.getDatasourceResolver(ds);
        String query=new String();
        if (clazz==PersonalAuthor.class) {
           query = "INSERT INTO " + table + " (uri, nombre, apellidos, autoridad) VALUES ( '" + uri + "','" + name + "','" + lastname + "','" + authority + "')";
      }else if (clazz== CorporateAuthor.class){
           query = "INSERT INTO " + table + " (uri, nombre, autoridad) VALUES ( '" + uri + "', '" + name + "', '" + authority + "')";
      }
        resolver.postElementByDynamicQuery(query, ds.getEndpoint(), ds.getUsername(), ds.getPassword(), clazz);
     }

    public <T> List<T> getBy(String uri, String name, String lastname, String authority, Datasource ds, Class clazz, String table) {
        List<T> entities = new ArrayList<>();
        DatasourceResolver<T> resolver = datasourceService.getDatasourceResolver(ds);
        String query = "select * from " + table + " where ";
        int i = 0;
        if ((!uri.isEmpty()) && (i == 0)) {
            query =  query.concat(" uri ilike '%" + uri + "%'");
            i++;
        }
        if ((!name.isEmpty()) && (i == 0)) {
            query = query.concat(" nombre ilike '%" + name + "%'");
            i++;
        } else if ((!name.isEmpty()) && (i > 0)) {
            query = query.concat(" and nombre ilike '%" + name + "%'");
        }
        if ((!lastname.isEmpty()) && (i == 0)) {
            query = query.concat(" apellidos ilike '%" + lastname + "%'");
            i++;
        } else if ((!lastname.isEmpty()) && (i > 0)) {
            query = query.concat(" and apellidos ilike '%" + lastname + "%'");
        }
        if ((!authority.isEmpty()) && (i == 0)) {
            query = query.concat(" autoridad ilike '%" + authority + "%'");
            i++;
        } else if ((!authority.isEmpty()) && (i > 0)) {
            query = query.concat(" and autoridad ilike '%" + authority + "%'");
        }

            entities.addAll(resolver.getElementByDynamicQuery(query, ds.getEndpoint(), ds.getUsername(), ds.getPassword(), clazz));
        return entities;
    }

    public <T> void updateAuthor(String uri, String name, String lastname, String authority, String newUri, String table, Datasource ds, Class clazz) {
        if ((newUri == "") || (newUri == null))
            newUri = uri;
        DatasourceResolver<T> resolver = datasourceService.getDatasourceResolver(ds);

        String query = new String();
        if (clazz==PersonalAuthor.class) {
            query="UPDATE " + table + " SET ( uri, nombre, apellidos, autoridad) = ('" + newUri + "', '" + name + "', '" + lastname + "', '" + authority + "') where uri= '" + uri+"'";
        }else if (clazz== CorporateAuthor.class){
            query="UPDATE " + table + " SET ( uri, nombre, autoridad) = ('" + newUri + "', '" + name + "', '" + authority + "') where uri= '" + uri+"'";
        }

      resolver.postElementByDynamicQuery(query, ds.getEndpoint(), ds.getUsername(), ds.getPassword(), clazz);
    }

    public <T> void delete(String uri, Datasource ds, Class clazz, String table) {

        String query = "DELETE FROM " + table + " WHERE uri =  '" + uri+"'";
        DatasourceResolver<T> resolver = datasourceService.getDatasourceResolver(ds);

            resolver.postElementByDynamicQuery(query, ds.getEndpoint(), ds.getUsername(), ds.getPassword(), clazz);
    }
}