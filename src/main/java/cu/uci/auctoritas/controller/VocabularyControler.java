package cu.uci.auctoritas.controller;

import cu.uci.auctoritas.domain.Vocabulary;
import cu.uci.auctoritas.service.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/vocabulary")

public class VocabularyControler {

    @Autowired
    private VocabularyService vocabularyService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Vocabulary> getVocabulary(){
        return vocabularyService.getVocabulary();
        }
}
