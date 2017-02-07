package cu.uci.auctoritas.source.mapper;

import cu.uci.auctoritas.domain.AuthorizedTerm;
import cu.uci.auctoritas.util.OntologyUtil;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.RDFNode;

import java.sql.ResultSet;
import java.sql.SQLException;


public class AuthorizedTermRowMapper implements EntityMapper {

    final String FIELD_TERM = "prefLabel";
    final String FIELD_IDENTIFIER = "s";
    final String FIELD_VOCABULARY_URI = "scheme";

    @Override
    public Object mapRow(QuerySolution result) {
        AuthorizedTerm authorizedTerm = new AuthorizedTerm();
        authorizedTerm.setTerm(OntologyUtil.clean(result.get(FIELD_TERM).asNode().toString()));
        String s = "";
        try {
            s = OntologyUtil.clean(result.get(FIELD_IDENTIFIER).asNode().toString());
        } catch (Exception e) {
            e.getSuppressed();
        }
        String node = result.get(FIELD_VOCABULARY_URI).asNode().toString();
        if(s != null) node = node.concat(s);
        String uri = OntologyUtil.clean(node);

        authorizedTerm.setVocabulary_uri(uri);
        return authorizedTerm;
    }

    //esta vacio porque nunca un vocabulario va a almacenarse en una base de datos POSTGRES
    //se deja vacio como esta clase es una interfaz de entity mapper se le hace necesario
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        return null;
    }
}
