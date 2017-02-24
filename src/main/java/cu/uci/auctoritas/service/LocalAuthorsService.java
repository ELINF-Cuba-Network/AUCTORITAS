package cu.uci.auctoritas.service;

import cu.uci.auctoritas.domain.CorporateAuthor;
import cu.uci.auctoritas.domain.Event;
import cu.uci.auctoritas.domain.PersonalAuthor;
import cu.uci.auctoritas.repository.AuthorRepository;
import cu.uci.auctoritas.source.Datasource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class LocalAuthorsService {
    @Autowired
    private DatasourceService datasourceService;
    @Autowired
    private AuthorRepository authorRepository;

    public <T> Event post(String uri, String name, String lastname, String authority, Class<T> clazz) throws Exception {
        Event event = new Event();
        try{
            Datasource ds = datasourceService.getLocalDatasource(clazz);
            String table = extractTable(ds.getMapped());
            authorRepository.insertAuthor(uri, name, lastname, authority, ds, clazz, table);
            event.setEvent("Authority created successfully");
            return event;
        }catch (Exception e){
            String s=e.getCause().getMessage().toString();
            event.setEvent(s);
            return event;
        }
        }

    public <T> List<T> getByID(String uri, String name, String lastname, String authority, Class clazz) {

        try {
            Datasource ds = datasourceService.getLocalDatasource(clazz);
            String table = extractTable(ds.getMapped());
            return authorRepository.getBy(uri, name, lastname, authority, ds, clazz, table);
        }catch (Exception e){
            if(clazz== PersonalAuthor.class){
                PersonalAuthor pa= new PersonalAuthor();
                List<PersonalAuthor> lpa= new ArrayList<>();
                pa.setName(e.getCause().getMessage().toString());
                lpa.add(pa);
                return (List<T>) lpa;
            }else{
                CorporateAuthor ca= new CorporateAuthor();
                List<CorporateAuthor> lca= new ArrayList<>();
                ca.setName(e.getCause().getMessage().toString());
                lca.add(ca);
                return (List<T>) lca;
            }
        }
        }

    public Event update(String uri, String name, String lastname, String authority, Class clazz, String newUri) {
        Event event = new Event();
        try {
            Datasource ds = datasourceService.getLocalDatasource(clazz);
            String table = extractTable(ds.getMapped());
            authorRepository.updateAuthor(uri, name, lastname, authority, newUri, table, ds, clazz);
            event.setEvent("Author updated");
            return event;
        }catch (Exception e){
            event.setEvent(e.getCause().getMessage().toString());
            return event;
        }
        }

    public Event delete(String uri, Class clazz) {
        Event event = new Event();
        try {
            Datasource ds = datasourceService.getLocalDatasource(clazz);
            String table = extractTable(ds.getMapped());
            authorRepository.delete(uri, ds, clazz, table);
            event.setEvent("Author deleted");
            return event;
        }catch (Exception e){
            event.setEvent(e.getCause().getMessage().toString());
            return event;
        }
    }

    private String extractTable(String query) {
        int pos1 = query.indexOf("om ");
        int pos2 = query.indexOf(" wh");
        if ((-1 != pos1) && (-1 != pos2)) {
            String tablename = query.substring(pos1 + 3, pos2);
            return tablename;
        }
        return null;
    }
}

