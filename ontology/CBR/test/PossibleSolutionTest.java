/**
 * 
 */
package ontology.CBR.test;

import static org.junit.Assert.*;

import ontology.CBR.Case;
import ontology.CBR.PossibleSolution;
import ontology.common.Descriptor;
import ontology.common.SSCharacterDescriptor;
import ontology.common.SVCharacterDescriptor;
import ontology.common.SingleValue;
import ontology.taxonomy.Taxon;
import ontology.taxonomy.TaxonomicRank;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



/**
 * @author Armando
 *
 */
public class PossibleSolutionTest {
	private static PossibleSolution aPossibleSolution;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Taxon taxon;
		
		System.out.println("Iniciando pruebas para la clase " + PossibleSolution.class.getName());
		aPossibleSolution = new PossibleSolution();
		taxon = new Taxon(TaxonomicRank.GENUS.getRank(), "Glossodoris");
		aPossibleSolution  = new PossibleSolution();
		aPossibleSolution.setSolution(taxon);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Descriptor aDescriptor;
		
		aDescriptor = new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(3));
		aPossibleSolution.addToConfirmedDescription(aDescriptor);
		aDescriptor = new SSCharacterDescriptor("Pie", "Disposición", "Sobresale al manto");
		aPossibleSolution.addToConfirmedDescription(aDescriptor);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link ontology.CBR.PossibleSolution#addToConfirmedDescription(ontology.common.Descriptor)}.
	 */
	@Test
	public void testAddConfirmedDescription() {
		Descriptor aDescriptor;
		
		System.out.println("Iniciando pruebas para el método AddConfirmedDescription()");
		
		System.out.println("Verificar que no haya contradicciones en la descripción o duplicados");
		aDescriptor = new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3));
		aPossibleSolution.addToConfirmedDescription(aDescriptor);
		aDescriptor = new SSCharacterDescriptor("Pie", "Disposición", "Sobresale al manto");
		aPossibleSolution.addToConfirmedDescription(aDescriptor);
	
		assertFalse(aPossibleSolution.addToConfirmedDescription(new SVCharacterDescriptor("Cuerpo", 
				"Longitud", new SingleValue(0.3))));
		assertFalse(aPossibleSolution.addToConfirmedDescription(new SSCharacterDescriptor("Pie", 
				"Disposición", "Sobresale al manto")));
		
		System.out.println("Verificar que se agregue un descriptor válido");
		aDescriptor = new SSCharacterDescriptor("Cuerpo", "Conformación", "Tiene cerata");
		assertTrue(aPossibleSolution.addToConfirmedDescription(aDescriptor));
		aDescriptor = new SVCharacterDescriptor("Branquias", "Número de hojas branquiales", new SingleValue(6));
		assertTrue(aPossibleSolution.addToConfirmedDescription(aDescriptor));
	}

	/**
	 * Test method for {@link ontology.CBR.PossibleSolution#addContradictions(ontology.common.Descriptor)}.
	 */
	@Test
	public void testAddContradictions() {
		Descriptor aDescriptor;
		
		System.out.println("Iniciando pruebas para el método AddContradictions()");
		
		aDescriptor = new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3));
		aPossibleSolution.addToContradictions(aDescriptor);
		aDescriptor = new SSCharacterDescriptor("Pie", "Disposición", "Sobresale al manto");
		aPossibleSolution.addToContradictions(aDescriptor);
		
		System.out.println("Verificar que no haya duplicados");
		assertFalse(aPossibleSolution.addToContradictions(new SVCharacterDescriptor("Cuerpo", 
				"Longitud", new SingleValue(0.3))));
		assertFalse(aPossibleSolution.addToContradictions(new SSCharacterDescriptor("Pie",
				"Disposición", "Sobresale al manto")));
		
		System.out.println("Verificar que se agregue un descriptor válido");
		aDescriptor = new SSCharacterDescriptor("Cuerpo", "Conformación", "Tiene cerata");
		assertTrue(aPossibleSolution.addToContradictions(aDescriptor));
		aDescriptor = new SVCharacterDescriptor("Branquias", "Número de hojas branquiales", new SingleValue(6));
		assertTrue(aPossibleSolution.addToContradictions(aDescriptor));
	}

	/**
	 * Test method for {@link ontology.CBR.PossibleSolution#addToDoubtfulDescription(ontology.common.Descriptor)}.
	 */
	@Test
	public void testAddDoubtfulDescription() {
		Descriptor aDescriptor;
		
		System.out.println("Iniciando pruebas para el método AddDoubtfulDescription()");
		
		aDescriptor = new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3));
		aPossibleSolution.addToDoubtfulDescription(aDescriptor);
		aDescriptor = new SSCharacterDescriptor("Pie", "Disposición", "Sobresale al manto");
		aPossibleSolution.addToDoubtfulDescription(aDescriptor);
		
		System.out.println("Verificar que no haya duplicados");
		assertFalse(aPossibleSolution.addToDoubtfulDescription(new SVCharacterDescriptor("Cuerpo", 
				"Longitud", new SingleValue(0.3))));
		assertFalse(aPossibleSolution.addToDoubtfulDescription(new SSCharacterDescriptor("Pie", 
				"Disposición", "Sobresale al manto")));
		
		System.out.println("Verificar que se agregue un descriptor válido");
		aDescriptor = new SSCharacterDescriptor("Cuerpo", "Conformación", "Tiene cerata");
		assertTrue(aPossibleSolution.addToDoubtfulDescription(aDescriptor));
		aDescriptor = new SVCharacterDescriptor("Branquias", "Número de hojas branquiales", 
				new SingleValue(6));
		assertTrue(aPossibleSolution.addToDoubtfulDescription(aDescriptor));
	}

	/**
	 * Test method for {@link ontology.CBR.PossibleSolution#setSolution(java.lang.Object)}.
	 */
	@Test
	public void testSetSolution() {
		Taxon taxon;
		
		System.out.println("Iniciando pruebas para el método SetSolution()");
		aPossibleSolution.setSolution(new Case());
		assertNotNull(aPossibleSolution.getSolution());
		aPossibleSolution.setSolution(new Taxon(TaxonomicRank.GENUS.getRank(), "Glossodoris"));
		assertTrue(aPossibleSolution.getSolution() instanceof Taxon);
		
		taxon = new Taxon(TaxonomicRank.GENUS.getRank(), "Glossodoris");
		aPossibleSolution  = new PossibleSolution();
		aPossibleSolution.setSolution(taxon);
	}

	/**
	 * Test method for {@link ontology.CBR.PossibleSolution#addToSolutionDescription(ontology.common.Descriptor)}.
	 */
	@Test
	public void testAddSolutionDescription() {
		Descriptor aDescriptor;
		
		System.out.println("Iniciando pruebas para el método AddSolutionDescription()");
		
		aDescriptor = new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3));
		aPossibleSolution.addToSolutionDescription(aDescriptor);
		aDescriptor = new SSCharacterDescriptor("Pie", "Disposición", "Sobresale al manto");
		aPossibleSolution.addToSolutionDescription(aDescriptor);
		
		System.out.println("Verificar que no haya duplicados");
		assertFalse(aPossibleSolution.addToSolutionDescription(new SVCharacterDescriptor("Cuerpo",
				"Longitud", new SingleValue(0.3))));
		assertFalse(aPossibleSolution.addToSolutionDescription(new SSCharacterDescriptor("Pie", "Disposición",
				"Sobresale al manto")));
		
		System.out.println("Verificar que se agregue un descriptor válido");
		aDescriptor = new SSCharacterDescriptor("Cuerpo", "Conformación", "Tiene cerata");
		assertTrue(aPossibleSolution.addToSolutionDescription(aDescriptor));
		aDescriptor = new SVCharacterDescriptor("Branquias", "Número de hojas branquiales", new SingleValue(6));
		assertTrue(aPossibleSolution.addToSolutionDescription(aDescriptor));
	}

	/**
	 * Test method for {@link ontology.CBR.PossibleSolution#addToUnconfirmedDescription(ontology.common.Descriptor)}.
	 */
	@Test
	public void testAddUnconfirmedDescription() {
		Descriptor aDescriptor;
		
		System.out.println("Iniciando pruebas para el método AddUnconfirmedDescription()");
		
		aDescriptor = new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3));
		aPossibleSolution.addToUnconfirmedDescription(aDescriptor);
		aDescriptor = new SSCharacterDescriptor("Pie", "Disposición", "Sobresale al manto");
		aPossibleSolution.addToUnconfirmedDescription(aDescriptor);
		
		System.out.println("Verificar que no haya duplicados");
		assertFalse(aPossibleSolution.addToUnconfirmedDescription(new SVCharacterDescriptor("Cuerpo", 
				"Longitud", new SingleValue(0.3))));
		assertFalse(aPossibleSolution.addToUnconfirmedDescription(new SSCharacterDescriptor("Pie", 
				"Disposición", "Sobresale al manto")));
		
		System.out.println("Verificar que se agregue un descriptor válido");
		aDescriptor = new SSCharacterDescriptor("Cuerpo", "Conformación", "Tiene cerata");
		assertTrue(aPossibleSolution.addToUnconfirmedDescription(aDescriptor));
		aDescriptor = new SVCharacterDescriptor("Branquias", "Número de hojas branquiales", new SingleValue(6));
		assertTrue(aPossibleSolution.addToUnconfirmedDescription(aDescriptor));
	}

	/**
	 * Test method for {@link ontology.CBR.PossibleSolution#getLevel()}.
	 */
	@Test
	public void testGetLevel() {
		System.out.println("Iniciando pruebas para el método GetLevel()");
		assertEquals(aPossibleSolution.getLevel(), TaxonomicRank.GENUS.getRank());
	}

	/**
	 * Test method for {@link ontology.CBR.PossibleSolution#getStatus()}.
	 */
	@Test
	public void testGetStatus() {
		System.out.println("Iniciando pruebas para el método GetStatus()");
		assertTrue(aPossibleSolution.getStatus());
	}

	/**
	 * Test method for {@link ontology.CBR.PossibleSolution#getName()}.
	 */
	@Test
	public void testGetName() {
		System.out.println("Iniciando pruebas para el método GetName()");
		assertEquals(aPossibleSolution.getName(), "Glossodoris");
	}

	/**
	 * Test method for {@link ontology.CBR.PossibleSolution#compareTo(ontology.CBR.PossibleSolution)}.
	 */
	@Test
	public void testCompareTo() {
		PossibleSolution ps; 
		Taxon taxon;
		
		System.out.println("Iniciando pruebas para el método CompareTo()");
		taxon = new Taxon(TaxonomicRank.GENUS.getRank(), "Chromodoris");
		ps  = new PossibleSolution();
		ps.setSolution(taxon);
		
		System.out.println("Verificar que la posible solución es igual con otra según el criterio" +
				"de comparación");
		assertEquals(0, aPossibleSolution.compareTo(ps));
		
		System.out.println("Verificar que la posible solución es distinta con otra según el criterio" +
				"de comparación");
		taxon = new Taxon(TaxonomicRank.FAMILY.getRank(), "Chromodorididae");;
		ps.setSolution(taxon);
		assertTrue(aPossibleSolution.compareTo(ps) < 0);
		taxon = new Taxon(TaxonomicRank.SPECIES.getRank(), "Cadlina sparsa");;
		ps.setSolution(taxon);
		assertTrue(aPossibleSolution.compareTo(ps) > 0);
	}

}
