package cu.uci.auctoritas.source.mapper;

import cu.uci.auctoritas.domain.Vocabulary;
import cu.uci.auctoritas.util.OntologyUtil;
import org.apache.jena.query.QuerySolution;

import java.sql.ResultSet;
import java.sql.SQLException;


public class VocabularyRowMapper implements EntityMapper<Vocabulary> {

    final String FIELD_VOCABULARY= "ds";

    @Override
    public Vocabulary mapRow(QuerySolution data) {
        Vocabulary voc = new Vocabulary();
        voc.setUri(OntologyUtil.clean(data.get(FIELD_VOCABULARY).asNode().toString()));
        return voc;
    }

    //esta vacio porque nunca un vocabulario va a almacenarse en una base de datos POSTGRES
    //se deja vacio como esta clase es una interfaz de entity mapper se le hace necesario
    @Override
    public Vocabulary mapRow(ResultSet resultSet, int i) throws SQLException {
        return null;
    }
}
