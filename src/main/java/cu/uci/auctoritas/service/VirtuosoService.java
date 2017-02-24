package cu.uci.auctoritas.service;

import cu.uci.auctoritas.source.DatasourceRESTResolver;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VirtuosoService {

    public List<Object> getTdos(){
        return new DatasourceRESTResolver<Object>().getElementByDynamicQuery(null, null, null, null, Object.class);
    }

}
