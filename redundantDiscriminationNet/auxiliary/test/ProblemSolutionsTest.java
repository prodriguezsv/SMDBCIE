/**
 * 
 */
package redundantDiscriminationNet.auxiliary.test;

import static org.junit.Assert.*;

import ontology.CBR.Case;
import ontology.CBR.SimilarityDegree;
import ontology.common.SSCharacterDescriptor;
import ontology.common.SVCharacterDescriptor;
import ontology.common.SingleValue;
import ontology.taxonomy.TaxonomicRank;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import redundantDiscriminationNet.auxiliary.ProblemSolutions;

/**
 * @author Armando
 *
 */
public class ProblemSolutionsTest {
	private static ProblemSolutions aps;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Iniciando pruebas para la clase " + ProblemSolutions.class.getName());
		aps = new ProblemSolutions(new Case());
		
		aps.addToDescription(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3)));
		aps.addToDescription(new SSCharacterDescriptor("Pie", "Disposición", "Sobresale al manto"));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Terminando pruebas para la clase " + ProblemSolutions.class.getName());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link redundantDiscriminationNet.auxiliary.ProblemSolutions#addSolutionCase(ontology.CBR.Case)}.
	 */
	@Test
	public void testAddSolutionCase() {
		Case asc;
		
		System.out.println("Iniciando pruebas para el método AddSolutionCase()");
		asc = new Case();
		asc.getProblem().setGoalRank(TaxonomicRank.FAMILY.getRank());
		asc.getProblem().setLeastSimilarityDegree(SimilarityDegree.ALTAMENTESIMILAR.getSimilarityDegree());
		
		asc.getSolution().setTaxonLevel(TaxonomicRank.FAMILY.getRank());
		asc.getSolution().setTaxonName("Chromorididae");
		
		System.out.println("Verificar que no se agregue una solución con contradicciones con el caso problema");
		asc.addToDescription(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(1)));
		asc.addToDescription(new SSCharacterDescriptor("Pie", "Disposición", "Sobresale al manto"));
		assertFalse(aps.addSolutionCase(asc));
		
		System.out.println("Verificar que se agregue una solución sin contradicciones con el caso problema");
		asc.setToDefault();
		asc.addToDescription(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3)));
		asc.addToDescription(new SSCharacterDescriptor("Pie", "Disposición", "Sobresale al manto"));
		assertTrue(aps.addSolutionCase(asc));
		
		System.out.println("Verificar que no se agregue una solución duplicada");
		asc.setToDefault();
		asc.addToDescription(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3)));
		asc.addToDescription(new SSCharacterDescriptor("Pie", "Disposición", "Sobresale al manto"));
		assertFalse(aps.addSolutionCase(asc));
	}

}
