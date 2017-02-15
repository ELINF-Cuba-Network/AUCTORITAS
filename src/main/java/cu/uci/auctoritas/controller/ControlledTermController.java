package cu.uci.auctoritas.controller;

import cu.uci.auctoritas.domain.AuthorizedTerm;
import cu.uci.auctoritas.service.ControledTermsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/term")
public class ControlledTermController {
    @Autowired
    private ControledTermsService controledTermsService;

    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<AuthorizedTerm> getAuthorizedTerm(@RequestParam String term, @RequestParam String language, @RequestParam(required = false) String vocabulary){
        term = "\""+term+"\"";
        return controledTermsService.getAuthorizedTerms(term, language, vocabulary);
    }

}
