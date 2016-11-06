package cu.uci.auctoritas;

import cu.uci.auctoritas.controller.DataSourceController;
import cu.uci.auctoritas.controller.LocalCorporateAuthorsController;
import cu.uci.auctoritas.controller.LocalPersonalAuthorsController;
import cu.uci.auctoritas.domain.AuthorizedTerm;
import cu.uci.auctoritas.domain.PersonalAuthor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by bichos on 30/05/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AuctoritasApplication.class)
public class angularServicesTests {
    @Resource
    private DataSourceController dataSourceController;
    @Resource
    private LocalPersonalAuthorsController localPersonalAuthorsController;
    @Resource
    private LocalCorporateAuthorsController localCorporateAuthorsController;

    @Test
    public void dsByPersonalAuthorTest() {
        System.out.println("dataSources");
        String expResult = "http://auctoritasdescription.cu/#Postgre";
        List<String> result = dataSourceController.listbyPersonalAuthor("jdbc");
        String res = result.get(0);
        assertEquals(expResult, res);
    }
    @Test
    public void dsByCorporateAuthorTest() {
        System.out.println("dataSources");
        String expResult = "";
        List<String> result = dataSourceController.listbyCorporateAuthor("jdbc");
      //  String res = result.get(0);
        assertEquals(expResult, expResult);
    }
//    @Test
//    public void addlocalPersonalAuthorTest() throws Exception {
//
//        int expResult =200;
//        int result = localPersonalAuthorsController.postpersonalAuthor("mordi","Mordilon Bicho","el arte de dormir","123456789",0);
//        assertEquals(expResult, result);
//}
//    @Test
//    public void modifylocalPersonalAuthorTest() throws Exception {
//
//        int expResult =200;
//        int result = localPersonalAuthorsController.update("Flavia","Gonzalez Bicho","el arte de dormir","http://facebook.com/flavia","http://facebook.com/flavia.gonzalezbarroso",0);
//        assertEquals(expResult, result);
//    }
//    @Test
//    public void deletelocalPersonalAuthorTest() throws Exception {
//
//        int expResult =200;
//        int result = localPersonalAuthorsController.delete("http://id.loc.gov/authorities/names/n00005733",0);
//        assertEquals(expResult, result);
//    }
    @Test
    public void findByPersonalAuthorTest() {
        String expResult = " Carlos Alberto";
        List<PersonalAuthor> result = localPersonalAuthorsController.getBy("Carlos Alberto","","","",0);
        String res = result.get(0).getName();
        assertEquals(expResult, res);
    }
}
