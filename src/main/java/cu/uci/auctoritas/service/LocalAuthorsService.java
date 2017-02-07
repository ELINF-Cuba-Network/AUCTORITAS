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

    public <T> Event post(String uri, String name, String lastname, String authority, int datasourceindex, Class<T> clazz) throws Exception {
        Event event = new Event();
        try{
            List<Datasource> ds = datasourceService.getDatasources(clazz);
            String table = extractTable(ds.get(datasourceindex).getMapped());
            authorRepository.insertAuthor(uri, name, lastname, authority, ds.get(datasourceindex), clazz, table);
            event.setEvent("Authority created successfully");
            return event;
        }catch (Exception e){
            String s=e.getCause().getMessage().toString();
            event.setEvent(s);
            return event;
        }
        }

    public <T> List<T> getByID(String uri, String name, String lastname, String authority, int datasourceindex, Class clazz) {

        try {
            List<Datasource> ds = datasourceService.getDatasources(clazz);
            String table = extractTable(ds.get(datasourceindex).getMapped());
            return authorRepository.getBy(uri, name, lastname, authority, ds.get(datasourceindex), clazz, table);
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

    public Event update(String uri, String name, String lastname, String authority, int datasourceindex, Class clazz, String newUri) {
        Event event = new Event();
        try {
            List<Datasource> ds = datasourceService.getDatasources(clazz);
            String table = extractTable(ds.get(datasourceindex).getMapped());
            authorRepository.updateAuthor(uri, name, lastname, authority, newUri, table, ds.get(datasourceindex), clazz);
            event.setEvent("Author updated");
            return event;
        }catch (Exception e){
            event.setEvent(e.getCause().getMessage().toString());
            return event;
        }
        }

    public Event delete(String uri, int datasourceindex, Class clazz) {
        Event event = new Event();
        try {
        List<Datasource> ds = datasourceService.getDatasources(clazz);
        String table = extractTable(ds.get(datasourceindex).getMapped());
        authorRepository.delete(uri, ds.get(datasourceindex), clazz, table);
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
        String tablename = query.substring(pos1 + 3, pos2);
        return tablename;
    }
}

