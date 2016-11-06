package cu.uci.auctoritas.source;

import cu.uci.auctoritas.domain.CorporateAuthor;
import cu.uci.auctoritas.domain.PersonalAuthor;
import cu.uci.auctoritas.source.mapper.CorporativeAuthorRowMapper;
import cu.uci.auctoritas.source.mapper.EntityMapper;
import cu.uci.auctoritas.source.mapper.PersonalAuthorRowMapper;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by bichos on 4/05/16.
 */
public class DatasourceJDBCResolver<T> implements DatasourceResolver<T> {

    @Override
    public List<T> getElementByDynamicQuery(String query, String endpoint, String username, String password, Class<T> clazz) {

        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(endpoint.trim());
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);

        DataSource ds = dataSourceBuilder.build();

        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
        return jdbcTemplate.query(query, getRowMapper(clazz));
    }


    public void postElementByDynamicQuery(String query, String endpoint, String username, String password, Class<T> clazz) {

        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(endpoint.trim());
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);

        DataSource ds = dataSourceBuilder.build();

        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
        jdbcTemplate.execute(query);
    }


    private EntityMapper<T> getRowMapper(Class<T> clazz){
        if(clazz == PersonalAuthor.class)
            return (EntityMapper<T>) new PersonalAuthorRowMapper();
        if(clazz == CorporateAuthor.class)
            return (EntityMapper<T>) new CorporativeAuthorRowMapper();
        return null;
    }


}
