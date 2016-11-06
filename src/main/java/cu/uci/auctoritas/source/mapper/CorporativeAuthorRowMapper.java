package cu.uci.auctoritas.source.mapper;

import cu.uci.auctoritas.domain.CorporateAuthor;
import cu.uci.auctoritas.util.OntologyUtil;
import org.apache.jena.query.QuerySolution;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by bichos on 4/05/16.
 */
public class CorporativeAuthorRowMapper implements EntityMapper<CorporateAuthor> {

    final String FIELD_ORGANIZATION= "organization";
    final String FIELD_NAME = "nombre";
    final String FIELD_URI = "uri";

    @Override
    public CorporateAuthor mapRow(ResultSet resultSet, int i) throws SQLException {
        CorporateAuthor ca = new CorporateAuthor();
        ca.setName(resultSet.getString(FIELD_NAME));
        ca.setUri(resultSet.getString(FIELD_URI));
        return ca;
    }

    @Override
    public CorporateAuthor mapRow(QuerySolution data) {
        CorporateAuthor ca= new CorporateAuthor();

        ca.setName(OntologyUtil.clean(data.get(FIELD_ORGANIZATION).asNode().toString()));
        ca.setUri(OntologyUtil.clean(data.get(FIELD_URI).asNode().toString()));
        return ca;

    }
}
