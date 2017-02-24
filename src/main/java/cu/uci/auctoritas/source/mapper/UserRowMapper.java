package cu.uci.auctoritas.source.mapper;

import cu.uci.auctoritas.domain.UserInfo;
import org.apache.jena.query.QuerySolution;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserRowMapper implements EntityMapper {
    final String FIELD_NAME = "name";
    final String FIELD_LAST_NAME = "lastname";
    final String FIELD_PASSWORD = "password";
    final String FIELD_USERNAME= "username";
    final String FIELD_ROLE = "role";
    @Override
    public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserInfo userInfo = new UserInfo();
        userInfo.setName(rs.getString(FIELD_NAME));
        userInfo.setLastname(rs.getString(FIELD_LAST_NAME));
        userInfo.setUsername(rs.getString(FIELD_USERNAME));
        userInfo.setRole(rs.getString(FIELD_ROLE));
        return userInfo;
    }

    @Override
    public Object mapRow(QuerySolution result) {
        return null;
    }
}
