package cu.uci.auctoritas.service;

import cu.uci.auctoritas.domain.AuthorizedTerm;
import cu.uci.auctoritas.domain.CorporateAuthor;
import cu.uci.auctoritas.domain.PersonalAuthor;
import cu.uci.auctoritas.domain.Vocabulary;
import cu.uci.auctoritas.source.Datasource;
import cu.uci.auctoritas.source.DatasourceJDBCResolver;
import cu.uci.auctoritas.source.DatasourceRESTResolver;
import cu.uci.auctoritas.source.DatasourceResolver;
import cu.uci.auctoritas.util.OntologyUtil;
import org.apache.http.util.TextUtils;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bichos on 7/05/16.
 */
@Service
public class DatasourceService {

    final String FIELD_MAPPED = "mapped";
    final String FIELD_ENDPOINT = "endpoint";
    final String FIELD_DS = "ds";
    final String FIELD_USER = "user";
    final String FIELD_PASSWORD = "password";


    @Value("${virtuoso.virtuosoEndPoint}")
    private String endPoint;

    @Value("${virtuoso.query.personalAuthor}")
    private String personalAuthorQuery;

    @Value("${virtuoso.query.corporateAuthor}")
    private String corporateAuthorQuery;

    @Value("${virtuoso.query.authorizedTerm}")
    private String authorizedTermQuery;


    public <T> List<T> getEntities(String name, String lastname, Class<T> clazz) {
        name = TextUtils.isEmpty(name) ? "" : name;
        lastname = TextUtils.isEmpty(lastname) ? "" : lastname;
        if ((name.isEmpty()) && (lastname.isEmpty()))
            return null;
        List<Datasource> datasources = getDatasources(clazz);
        List<T> entities = new ArrayList<>();
        for (Datasource ds : datasources) {
            String query = ds.getMapped();
            query = query.replace("param1", name);
            query = query.replace("param2", lastname);

            DatasourceResolver<T> resolver = getDatasourceResolver(ds);
            if (resolver == null) continue;

            try {
                entities.addAll(resolver
                        .getElementByDynamicQuery(query, ds.getEndpoint(), ds.getUsername(), ds.getPassword(), clazz));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return entities;
    }

    public <T> DatasourceResolver<T> getDatasourceResolver(Datasource ds) {
        DatasourceResolver<T> resolver = null;
        switch (ds.getEndpoint().substring(0, 4)) {
            case "jdbc":
                resolver = new DatasourceJDBCResolver<T>();
                break;
            case "http":
                resolver = new DatasourceRESTResolver<T>();
                break;
        }
        return resolver;
    }


    public <T> List<Datasource> getDatasources(Class<T> clazz) {
        String query = "";
        if (clazz == PersonalAuthor.class)
            query = personalAuthorQuery;
        if (clazz == CorporateAuthor.class)
            query = corporateAuthorQuery;
        if (clazz == AuthorizedTerm.class)
            query = authorizedTermQuery;
        return getDatasources(query);
    }

    private <T> List<Datasource> getDatasources(String query) {
        List<Datasource> result = new ArrayList<>();

        QueryExecution execution = new QueryEngineHTTP(endPoint, query);
        ResultSet qr = execution.execSelect();

        while (qr.hasNext())
            result.add(getDatasource(qr.next()));

        return result;
    }

    private <T> Datasource getDatasource(QuerySolution data) {
        Datasource ds = new Datasource();
        ds.setMapped(OntologyUtil.clean(data.get(FIELD_MAPPED).asNode().toString()));
        ds.setEndpoint(OntologyUtil.clean(data.get(FIELD_ENDPOINT).asNode().toString()));
        ds.setDatasource(OntologyUtil.clean(data.get(FIELD_DS).asNode().toString()));
        ds.setUsername(OntologyUtil.clean(data.get(FIELD_USER).asNode().toString()));
        ds.setPassword(OntologyUtil.clean(data.get(FIELD_PASSWORD).asNode().toString()));
        return ds;
    }
}
