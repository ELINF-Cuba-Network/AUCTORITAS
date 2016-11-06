package cu.uci.auctoritas;

import cu.uci.auctoritas.controller.AuthorController;
import cu.uci.auctoritas.controller.ControlledTermController;
import cu.uci.auctoritas.controller.VocabularyControler;
import cu.uci.auctoritas.domain.AuthorizedTerm;
import cu.uci.auctoritas.domain.CorporateAuthor;
import cu.uci.auctoritas.domain.PersonalAuthor;
import cu.uci.auctoritas.domain.Vocabulary;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AuctoritasApplication.class)
public class AuctoritasApplicationTests {
	@Resource
	private AuthorController authorController;
	@Resource
	private VocabularyControler vocabularyControler;
	@Resource
	private ControlledTermController controlledTermController;


	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}
	@Test
	public void personalAuthorTest() {
		System.out.println("authors");

//        AuthorController ins = new AuthorController();
        //String expResult = new ArrayList<>();
		String expResult = "Flavia";

        List<PersonalAuthor> result = authorController.getPersonalAuthor("flavia","gonzalez",1,100);
		String res=result.get(0).getName();
        assertEquals(expResult, res);
	}
	@Test
	public void corporateAuthorTest() {
		System.out.println("authors");
		String expResult = "Universidad de Ciencias Informáticas (UCI), Cuba@es-ES";
		List<CorporateAuthor> result = authorController.getCorporateAuthor("universidad","infor", 1, 100);
		String res=result.get(0).getName();
		assertEquals(expResult, res);
	}

	@Test
	public void vocabularyTest() {
		System.out.println("vocabulary");
		String expResult = "http://controledterms.cu/agrovoc";
		List<Vocabulary> result = vocabularyControler.getVocabulary();
		String res = result.get(0).getUri();
		assertEquals(expResult, res);
	}
	@Test
	public void controlledtermTest() {
		System.out.println("terms");
		String expResult = "Data management systems@en";
		List<AuthorizedTerm> result = controlledTermController.getAuthorizedTerm("databases","en","http://controledterms.cu/acm");
		String res = result.get(0).getTerm();
		assertEquals(expResult, res);
	}

}
