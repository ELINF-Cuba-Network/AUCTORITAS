package cu.uci.auctoritas.service;

import cu.uci.auctoritas.domain.AuthorizedTerm;
import cu.uci.auctoritas.domain.Vocabulary;
import cu.uci.auctoritas.source.Datasource;
import cu.uci.auctoritas.source.DatasourceRESTResolver;
import cu.uci.auctoritas.source.DatasourceResolver;
import cu.uci.auctoritas.util.OntologyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class VocabularyService {
    @Autowired
    private DatasourceService datasourceService;

    public List<Vocabulary> getVocabulary(){

        List<Datasource> datasources = datasourceService.getDatasources(AuthorizedTerm.class);
        List<Vocabulary> vocabularies= new ArrayList<>();
        for(Datasource ds:datasources){
            Vocabulary voc= new Vocabulary();

            String vocabulary= ds.getMapped();
            int pos1= vocabulary.indexOf(" <");
            int pos2 = vocabulary.indexOf("> ");
            voc.setUri(OntologyUtil.clean2(ds.getMapped().substring(pos1,pos2).trim()));
          vocabularies.add(voc);
        }
        return vocabularies;
    }
}
