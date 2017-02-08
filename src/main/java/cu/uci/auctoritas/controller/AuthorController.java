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
            produces = {MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseBody
    public List<PersonalAuthor> getPersonalAuthor(@RequestParam(required = false) String name, @RequestParam(required = false) String lastname, @RequestParam(required = false) Integer start, @RequestParam(required = false) Integer limit){
        return personalAuthorService.getPersonalAuthor(name.trim(), lastname.trim(),start,limit);
    }
    @RequestMapping(value = "/corporate",method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE})
    public @ResponseBody List<CorporateAuthor> getCorporateAuthor(@RequestParam(required = false) String name, @RequestParam(required = false) String label, @RequestParam int start, @RequestParam int limit){
        return corporateAuthorService.getCorporateAuthor(name.trim(), label.trim(), start, limit);
    }
}
