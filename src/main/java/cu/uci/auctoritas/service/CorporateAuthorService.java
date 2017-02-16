package cu.uci.auctoritas.service;

import cu.uci.auctoritas.domain.CorporateAuthor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CorporateAuthorService {
    @Autowired
    private DatasourceService datasourceService;

    public List<CorporateAuthor> getCorporateAuthor(String name, String label, Integer start, Integer limit, String dataSourceName) {
       List<CorporateAuthor> corporateAuthors=datasourceService.getEntities(name, label, CorporateAuthor.class, dataSourceName);
        List<CorporateAuthor> finalList= new ArrayList<>();
        if ((null == start) || (start == 0)) {
            start=1;
        }
        if ((null == limit) || (limit > corporateAuthors.size())) {
            limit = corporateAuthors.size();
        }
        if ((start>corporateAuthors.size())&&(corporateAuthors.size()!=0)){
            CorporateAuthor ca = new CorporateAuthor();
            ca.setName("ERROR, the start parameter exceeds the size of the list");
            ca.setUri("ERROR, the start parameter exceeds the size of the list");
            ca.setAuthority("ERROR, the start parameter exceeds the size of the list");
            finalList.add(ca);
            return finalList;
        }
        while (limit != 0) {//start debe comenzar en 1
            finalList.add(corporateAuthors.get(start-1));
            limit--;
            start++;
        }
        return finalList;
    }
}
