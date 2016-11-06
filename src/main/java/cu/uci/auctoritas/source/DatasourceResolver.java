package cu.uci.auctoritas.source;

import java.util.List;

/**
 * Created by bichos on 4/05/16.
 */
public interface DatasourceResolver<T> {
    List<T> getElementByDynamicQuery(String query, String endpoint, String username, String password, Class<T> clazz);
    void postElementByDynamicQuery(String query, String endpoint, String username, String password, Class<T> clazz);
}
