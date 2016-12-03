package cu.uci.auctoritas.repository;

import cu.uci.auctoritas.domain.UserInfo;
import cu.uci.auctoritas.source.mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserRepository {

    @Value("${postgres.datasource.table}")
    private String table;


    //private final String QUERY_UPDATE = "UPDATE %s SET ( uri, nombre, apellidos, autoridad) = ('%s', '%s', '%s', '%s') where uri IN (SELECT uri FROM %s WHERE uri ilike '%%s%' )";
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void run(UserInfo userInfo) throws Exception {
        StandardPasswordEncoder standardPasswordEncoder = new StandardPasswordEncoder("d@r@yn3");
        userInfo.setPassword(standardPasswordEncoder.encode(userInfo.getPassword()));
        String sql = "INSERT INTO " + table + " (name, lastname, password, username, role) VALUES ( '" + userInfo.getName() + "', '" + userInfo.getLastname() + "', '" + userInfo.getPassword() + "', '" + userInfo.getUsername() + "', '"+userInfo.getRole()+"')";

        jdbcTemplate.execute(sql);
//            jdbcTemplate.query(
//                    "SELECT id, first_name, last_name FROM customers WHERE first_name = ?", new Object[] { "Josh" },
//                    (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
//            )
    }

    public List<UserInfo> getByname(String name) {
        UserRowMapper rowMapper = new UserRowMapper();

        String sql = "select"+" name, lastname, username, role from " + table + " where  name ilike '%" + name + "%'";
        return jdbcTemplate.query(sql,rowMapper);
    }

    public void update(String username, String newpassword) {
        StandardPasswordEncoder standardPasswordEncoder = new StandardPasswordEncoder("d@r@yn3");
        newpassword= standardPasswordEncoder.encode(newpassword);

        String sql = "UPDATE " + table + " SET (password) = ('" + newpassword + "') where username= '" + username+"'";
        jdbcTemplate.execute(sql);
    }

    public void delete(String id) {
        String sql = "DELETE FROM " + table + " WHERE username =  '" +id+"'";
        jdbcTemplate.execute(sql);
    }

}

