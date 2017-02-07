package cu.uci.auctoritas.source.mapper;

import cu.uci.auctoritas.domain.CorporateAuthor;
import org.apache.jena.query.QuerySolution;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public interface EntityMapper<T> extends RowMapper<T>{
    public T mapRow(QuerySolution result);
}
