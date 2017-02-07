package cu.uci.auctoritas.source.mapper;

import cu.uci.auctoritas.domain.PersonalAuthor;
import cu.uci.auctoritas.util.OntologyUtil;
import org.apache.jena.query.QuerySolution;
import org.springframework.jdbc.core.RowMapper;

import javax.persistence.Entity;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PersonalAuthorRowMapper implements EntityMapper<PersonalAuthor> {

    final String FIELD_PERSON = "Person";
    final String FIELD_NAME = "nombre";
    final String FIELD_LASTNAME = "apellidos";
    final String FIELD_URI = "uri";
    final String FIELD_AUTHORITY = "autoridad";


    @Override
    public PersonalAuthor mapRow(ResultSet resultSet, int i) throws SQLException {

        PersonalAuthor pa = new PersonalAuthor();
        pa.setName(resultSet.getString(FIELD_NAME));
        pa.setLastname(resultSet.getString(FIELD_LASTNAME));
        pa.setUri(resultSet.getString(FIELD_URI));
        pa.setAuthority(resultSet.getString(FIELD_AUTHORITY));

        return pa;
    }

    @Override
    public PersonalAuthor mapRow(QuerySolution data) {
        PersonalAuthor pa = new PersonalAuthor();
        String fullName = OntologyUtil.clean(data.get(FIELD_PERSON).asNode().toString());
        String[] name = fullName.split(",");

        pa.setName(name[1].trim());
        pa.setLastname(name[0].trim());
        pa.setUri(OntologyUtil.clean(data.get(FIELD_URI).asNode().toString()));

        return pa;
    }
}
