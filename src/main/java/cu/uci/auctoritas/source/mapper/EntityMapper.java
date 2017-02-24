package cu.uci.auctoritas.source.mapper;

import org.apache.jena.query.QuerySolution;
import org.springframework.jdbc.core.RowMapper;


public interface EntityMapper<T> extends RowMapper<T>{
    public T mapRow(QuerySolution result);
}
