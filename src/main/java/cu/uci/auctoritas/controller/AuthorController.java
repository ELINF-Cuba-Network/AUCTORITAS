package cu.uci.auctoritas.controller;

import cu.uci.auctoritas.domain.CorporateAuthor;
import cu.uci.auctoritas.domain.PersonalAuthor;
import cu.uci.auctoritas.service.CorporateAuthorService;
import cu.uci.auctoritas.service.PersonalAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/author")
public class AuthorController {

    @Autowired
    private PersonalAuthorService personalAuthorService;
    @Autowired
    private CorporateAuthorService corporateAuthorService;

    @RequestMapping(value = "/personal", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ResponseBody
    public List<PersonalAuthor> getPersonalAuthor(@RequestParam(required = false) String name, @RequestParam(required = false) String lastname, @RequestParam(required = false) Integer start, @RequestParam(required = false) Integer limit, @RequestParam(required = false) String dataSourceName){
        return personalAuthorService.getPersonalAuthor((null != name)? name.trim() : name, (null != lastname)? lastname.trim() : lastname,start,limit, dataSourceName);
    }
    @RequestMapping(value = "/corporate",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    List<CorporateAuthor> getCorporateAuthor(@RequestParam(required = false) String name, @RequestParam(required = false) String label, @RequestParam(required = false) Integer start, @RequestParam(required = false) Integer limit, @RequestParam(required = false) String dataSourceName) {
        if (null != name)
            name = name.trim();
        if (null != label)
            label = label.trim();
        return corporateAuthorService.getCorporateAuthor(name, label, start, limit, dataSourceName);
    }
}
