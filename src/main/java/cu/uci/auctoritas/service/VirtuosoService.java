package cu.uci.auctoritas.service;

import cu.uci.auctoritas.domain.CorporateAuthor;
import cu.uci.auctoritas.source.Datasource;
import cu.uci.auctoritas.domain.PersonalAuthor;
import cu.uci.auctoritas.domain.Vocabulary;
import cu.uci.auctoritas.source.DatasourceRESTResolver;
import cu.uci.auctoritas.util.OntologyUtil;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class VirtuosoService {

    public List<Object> getTdos(){
        return new DatasourceRESTResolver<Object>().getElementByDynamicQuery(null, null, null, null, Object.class);
    }

}
