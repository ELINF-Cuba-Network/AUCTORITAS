package cu.uci.auctoritas.service;

import cu.uci.auctoritas.domain.AuthorizedTerm;
import cu.uci.auctoritas.domain.Vocabulary;
import cu.uci.auctoritas.source.Datasource;
import cu.uci.auctoritas.source.DatasourceRESTResolver;
import cu.uci.auctoritas.source.DatasourceResolver;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ControledTermsService {
    @Autowired
    DatasourceService datasourceService;


    public <T> List<AuthorizedTerm> getAuthorizedTerms(String term, String lang, String vocabulary) {
        Class clazz = AuthorizedTerm.class;
        term = TextUtils.isEmpty(term) ? "" : term;
        lang = TextUtils.isEmpty(lang) ? "" : lang;

        List<Datasource> datasources = datasourceService.getDatasources(clazz);
        List<AuthorizedTerm> entities = new ArrayList<>();

        modifyQuery(term, lang, datasources);

        for (int i = 0; i < datasources.size(); i++) {
            String query2 = datasources.get(i).getMapped().replace("altLabel", "prefLabel");
            entities.addAll(getAuthorizedTerms(vocabulary, clazz, datasources, i, query2));
            entities.addAll(getAuthorizedTerms(vocabulary, clazz, datasources, i, datasources.get(i).getMapped()));
        }
        clearBylanguage(lang, entities);
        return entities;
    }

    private void clearBylanguage(String lang, List<AuthorizedTerm> entities) {
        int j = entities.size();
        int i = 0;
        while (j > 0) {

            int pos1 = entities.get(i).getTerm().indexOf("@");
            //  int pos2=entities.get(j).getTerm().indexOf(entities.get(j).getTerm().);
            String text = entities.get(i).getTerm().substring(pos1, pos1 + 3);
            if (!text.contains(lang)) {
                entities.remove(i);
            } else {
                i++;
            }
            j--;


        }
    }

    private void modifyQuery(String term, String lang, List<Datasource> datasources) {
        for (int j = 0; j < datasources.size(); j++) {
            Datasource ds = new Datasource();
            String query = datasources.get(j).getMapped();
            query = query.replace("param1", term);
            query = query.replace("param2", lang);
            ds.setMapped(query);
            ds.setDatasource(datasources.get(j).getDatasource());
            ds.setEndpoint(datasources.get(j).getEndpoint());
            ds.setPassword(datasources.get(j).getPassword());
            ds.setUsername(datasources.get(j).getUsername());
            datasources.set(j, ds);
        }
    }

    private <T> List<AuthorizedTerm> getAuthorizedTerms(String vocabulary, Class clazz, List<Datasource> datasources, int i, String query) {
        DatasourceResolver<T> resolver = new DatasourceRESTResolver<T>();
        List<T> entities = new ArrayList<>();


        if ((null != vocabulary) && (datasources.get(i).getMapped().contains(vocabulary))) {
            try {
                entities.addAll(resolver
                        .getElementByDynamicQuery(query, datasources.get(i).getEndpoint(), datasources.get(i).getUsername(), datasources.get(i).getPassword(), clazz));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return (List<AuthorizedTerm>) entities;
        }
        return (List<AuthorizedTerm>) entities;
    }

}





