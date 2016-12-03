package cu.uci.auctoritas.controller;

import cu.uci.auctoritas.domain.Event;
import cu.uci.auctoritas.domain.PersonalAuthor;
import cu.uci.auctoritas.service.LocalAuthorsNEWService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api/localauthors")
public class LocalPersonalAuthorsController {

    @Autowired
    private LocalAuthorsNEWService localAuthorsNEWService;

    @RequestMapping(value = "/personalauthor/new", method = RequestMethod.POST)
    public<T> Event postpersonalAuthor(@RequestParam String name, @RequestParam String lastname, @RequestParam String authority, @RequestParam String uri, @RequestParam int datasourceindex) throws Exception {
        return localAuthorsNEWService.post(uri,name,lastname,authority,datasourceindex,PersonalAuthor.class);
  }

    @RequestMapping(value = "/personalauthor/findby", method = RequestMethod.GET)
    public List<PersonalAuthor> getBy(@RequestParam (required = false) String name, @RequestParam(required = false) String lastname, @RequestParam(required = false) String authority, @RequestParam (required = false)String uri, @RequestParam int datasourceindex){

        if ((((name==null)||name.isEmpty())&&((uri==null)||uri.isEmpty())&&((lastname==null)||lastname.isEmpty())&&((authority==null)||authority.isEmpty())))
            return null;
        return localAuthorsNEWService.getByID(uri,name,lastname,authority,datasourceindex,PersonalAuthor.class);
    }
    @RequestMapping (value = "/personalauthor/update", method = RequestMethod.PUT)
    public Event update(@RequestParam (required = false) String name, @RequestParam(required = false) String lastname, @RequestParam(required = false) String authority, @RequestParam String uri, @RequestParam String newuri, @RequestParam int datasourceindex){
        return localAuthorsNEWService.update(uri,name,lastname,authority,datasourceindex,PersonalAuthor.class,newuri);
   }
    @RequestMapping (value = "/personalauthor/delete", method = RequestMethod.DELETE)
    public Event delete(@RequestParam String uri, @RequestParam int datasourceindex){
        return localAuthorsNEWService.delete(uri, datasourceindex,PersonalAuthor.class);
    }
}

