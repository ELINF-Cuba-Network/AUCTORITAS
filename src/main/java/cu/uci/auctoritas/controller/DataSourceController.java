package cu.uci.auctoritas.controller;

import cu.uci.auctoritas.domain.CorporateAuthor;
import cu.uci.auctoritas.domain.PersonalAuthor;
import cu.uci.auctoritas.service.DatasourceService;
import cu.uci.auctoritas.source.Datasource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "api/datasource")
public class DataSourceController {
    @Autowired
    private DatasourceService datasourceService;

    @RequestMapping(value = "/listPersonalAuthor", method = RequestMethod.GET)
    public List<String> listbyPersonalAuthor(@RequestParam String type){

        List<Datasource> datasources= datasourceService.getDatasources(PersonalAuthor.class);
        List<String> finaldatasources = new ArrayList<>();
        for (Datasource ds: datasources){
            if (ds.getEndpoint().substring(0,4).contains(type)){
                finaldatasources.add(ds.getDatasource());
            }
        }
        return finaldatasources;
    }

    @RequestMapping(value = "/listCorporateAuthor", method = RequestMethod.GET)
    public List<String> listbyCorporateAuthor(@RequestParam String type){

        List<Datasource> datasources= datasourceService.getDatasources(CorporateAuthor.class);
        List<String> finaldatasources = new ArrayList<>();
        for (Datasource ds: datasources){
            if (ds.getEndpoint().substring(0,4).contains(type)){
                finaldatasources.add(ds.getDatasource());
            }
        }
        return finaldatasources;
    }
}
