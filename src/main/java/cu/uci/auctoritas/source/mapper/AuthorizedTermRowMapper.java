package cu.uci.auctoritas.source.mapper;

import cu.uci.auctoritas.domain.AuthorizedTerm;
import cu.uci.auctoritas.domain.Relation;
import cu.uci.auctoritas.util.OntologyUtil;
import org.apache.jena.query.QuerySolution;

import java.sql.ResultSet;
import java.sql.SQLException;


public class AuthorizedTermRowMapper implements EntityMapper {

    final String FIELD_TERM = "prefLabel";
    final String FIELD_IDENTIFIER = "s";
    final String FIELD_VOCABULARY_URI = "scheme";
    final String FIELD_BROADER_TERM = "broaderTermLabel";
    final String FIELD_NARROWER_TERM = "narrowerTermLabel";

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

        if (null != result.get(FIELD_BROADER_TERM)) {
            AuthorizedTerm broaderTerm = new AuthorizedTerm();
            broaderTerm.setTerm(result.get(FIELD_BROADER_TERM).asNode().toString());
            Relation broaderTermRelation = new Relation(Relation.RELATION_TYPE_BROADER_TERM, broaderTerm);
            authorizedTerm.addRelatedConcept(broaderTermRelation);
        }

        if (null != result.get(FIELD_NARROWER_TERM)) {
            AuthorizedTerm narrowerTerm = new AuthorizedTerm();
            narrowerTerm.setTerm(result.get(FIELD_NARROWER_TERM).asNode().toString());
            Relation narrowerTermRelation = new Relation(Relation.RELATION_TYPE_NARROWER_TERM, narrowerTerm);
            authorizedTerm.addRelatedConcept(narrowerTermRelation);
        }

        return authorizedTerm;
    }

    //esta vacio porque nunca un vocabulario va a almacenarse en una base de datos POSTGRES
    //se deja vacio como esta clase es una interfaz de entity mapper se le hace necesario
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        return null;
    }
}
