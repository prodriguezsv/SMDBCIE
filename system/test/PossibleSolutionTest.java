/**
 * 
 */
package system.test;

import static org.junit.Assert.*;

import ontology.CBR.Case;
import ontology.common.Descriptor;
import ontology.common.SSCharacterDescriptor;
import ontology.common.SVCharacterDescriptor;
import ontology.taxonomy.Taxon;
import ontology.taxonomy.TaxonomicRank;
import ontology.values.SingleValue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import system.PossibleSolution;


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
		taxon = new Taxon(TaxonomicRank.GENUS, "Glossodoris");
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
		aPossibleSolution.addConfirmedDescription(aDescriptor);
		aDescriptor = new SSCharacterDescriptor("Pie", "Disposición", "Sobresale al manto");
		aPossibleSolution.addConfirmedDescription(aDescriptor);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link system.PossibleSolution#addConfirmedDescription(ontology.common.Descriptor)}.
	 */
	@Test
	public void testAddConfirmedDescription() {
		Descriptor aDescriptor;
		
		System.out.println("Iniciando pruebas para el método AddConfirmedDescription()");
		
		System.out.println("Verificar que no haya contradicciones en la descripción o duplicados");
		aDescriptor = new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3));
		aPossibleSolution.addConfirmedDescription(aDescriptor);
		aDescriptor = new SSCharacterDescriptor("Pie", "Disposición", "Sobresale al manto");
		aPossibleSolution.addConfirmedDescription(aDescriptor);
	
		assertFalse(aPossibleSolution.addConfirmedDescription(new SVCharacterDescriptor("Cuerpo", 
				"Longitud", new SingleValue(0.3))));
		assertFalse(aPossibleSolution.addConfirmedDescription(new SSCharacterDescriptor("Pie", 
				"Disposición", "Sobresale al manto")));
		
		System.out.println("Verificar que se agregue un descriptor válido");
		aDescriptor = new SSCharacterDescriptor("Cuerpo", "Conformación", "Tiene cerata");
		assertTrue(aPossibleSolution.addConfirmedDescription(aDescriptor));
		aDescriptor = new SVCharacterDescriptor("Branquias", "Número de hojas branquiales", new SingleValue(6));
		assertTrue(aPossibleSolution.addConfirmedDescription(aDescriptor));
	}

	/**
	 * Test method for {@link system.PossibleSolution#addContradictions(ontology.common.Descriptor)}.
	 */
	@Test
	public void testAddContradictions() {
		Descriptor aDescriptor;
		
		System.out.println("Iniciando pruebas para el método AddContradictions()");
		
		aDescriptor = new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3));
		aPossibleSolution.addContradictions(aDescriptor);
		aDescriptor = new SSCharacterDescriptor("Pie", "Disposición", "Sobresale al manto");
		aPossibleSolution.addContradictions(aDescriptor);
		
		System.out.println("Verificar que no haya duplicados");
		assertFalse(aPossibleSolution.addContradictions(new SVCharacterDescriptor("Cuerpo", 
				"Longitud", new SingleValue(0.3))));
		assertFalse(aPossibleSolution.addContradictions(new SSCharacterDescriptor("Pie",
				"Disposición", "Sobresale al manto")));
		
		System.out.println("Verificar que se agregue un descriptor válido");
		aDescriptor = new SSCharacterDescriptor("Cuerpo", "Conformación", "Tiene cerata");
		assertTrue(aPossibleSolution.addContradictions(aDescriptor));
		aDescriptor = new SVCharacterDescriptor("Branquias", "Número de hojas branquiales", new SingleValue(6));
		assertTrue(aPossibleSolution.addContradictions(aDescriptor));
	}

	/**
	 * Test method for {@link system.PossibleSolution#addDoubtfulDescription(ontology.common.Descriptor)}.
	 */
	@Test
	public void testAddDoubtfulDescription() {
		Descriptor aDescriptor;
		
		System.out.println("Iniciando pruebas para el método AddDoubtfulDescription()");
		
		aDescriptor = new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3));
		aPossibleSolution.addDoubtfulDescription(aDescriptor);
		aDescriptor = new SSCharacterDescriptor("Pie", "Disposición", "Sobresale al manto");
		aPossibleSolution.addDoubtfulDescription(aDescriptor);
		
		System.out.println("Verificar que no haya duplicados");
		assertFalse(aPossibleSolution.addDoubtfulDescription(new SVCharacterDescriptor("Cuerpo", 
				"Longitud", new SingleValue(0.3))));
		assertFalse(aPossibleSolution.addDoubtfulDescription(new SSCharacterDescriptor("Pie", 
				"Disposición", "Sobresale al manto")));
		
		System.out.println("Verificar que se agregue un descriptor válido");
		aDescriptor = new SSCharacterDescriptor("Cuerpo", "Conformación", "Tiene cerata");
		assertTrue(aPossibleSolution.addDoubtfulDescription(aDescriptor));
		aDescriptor = new SVCharacterDescriptor("Branquias", "Número de hojas branquiales", 
				new SingleValue(6));
		assertTrue(aPossibleSolution.addDoubtfulDescription(aDescriptor));
	}

	/**
	 * Test method for {@link system.PossibleSolution#setSolution(java.lang.Object)}.
	 */
	@Test
	public void testSetSolution() {
		Taxon taxon;
		
		System.out.println("Iniciando pruebas para el método SetSolution()");
		assertTrue(aPossibleSolution.setSolution(new Case()));
		assertTrue(aPossibleSolution.setSolution(new Taxon(TaxonomicRank.GENUS, "Glossodoris")));
		
		assertFalse(aPossibleSolution.setSolution(new Object()));
		
		taxon = new Taxon(TaxonomicRank.GENUS, "Glossodoris");
		aPossibleSolution  = new PossibleSolution();
		aPossibleSolution.setSolution(taxon);
	}

	/**
	 * Test method for {@link system.PossibleSolution#addSolutionDescription(ontology.common.Descriptor)}.
	 */
	@Test
	public void testAddSolutionDescription() {
		Descriptor aDescriptor;
		
		System.out.println("Iniciando pruebas para el método AddSolutionDescription()");
		
		aDescriptor = new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3));
		aPossibleSolution.addSolutionDescription(aDescriptor);
		aDescriptor = new SSCharacterDescriptor("Pie", "Disposición", "Sobresale al manto");
		aPossibleSolution.addSolutionDescription(aDescriptor);
		
		System.out.println("Verificar que no haya duplicados");
		assertFalse(aPossibleSolution.addSolutionDescription(new SVCharacterDescriptor("Cuerpo",
				"Longitud", new SingleValue(0.3))));
		assertFalse(aPossibleSolution.addSolutionDescription(new SSCharacterDescriptor("Pie", "Disposición",
				"Sobresale al manto")));
		
		System.out.println("Verificar que se agregue un descriptor válido");
		aDescriptor = new SSCharacterDescriptor("Cuerpo", "Conformación", "Tiene cerata");
		assertTrue(aPossibleSolution.addSolutionDescription(aDescriptor));
		aDescriptor = new SVCharacterDescriptor("Branquias", "Número de hojas branquiales", new SingleValue(6));
		assertTrue(aPossibleSolution.addSolutionDescription(aDescriptor));
	}

	/**
	 * Test method for {@link system.PossibleSolution#addUnconfirmedDescription(ontology.common.Descriptor)}.
	 */
	@Test
	public void testAddUnconfirmedDescription() {
		Descriptor aDescriptor;
		
		System.out.println("Iniciando pruebas para el método AddUnconfirmedDescription()");
		
		aDescriptor = new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3));
		aPossibleSolution.addUnconfirmedDescription(aDescriptor);
		aDescriptor = new SSCharacterDescriptor("Pie", "Disposición", "Sobresale al manto");
		aPossibleSolution.addUnconfirmedDescription(aDescriptor);
		
		System.out.println("Verificar que no haya duplicados");
		assertFalse(aPossibleSolution.addUnconfirmedDescription(new SVCharacterDescriptor("Cuerpo", 
				"Longitud", new SingleValue(0.3))));
		assertFalse(aPossibleSolution.addUnconfirmedDescription(new SSCharacterDescriptor("Pie", 
				"Disposición", "Sobresale al manto")));
		
		System.out.println("Verificar que se agregue un descriptor válido");
		aDescriptor = new SSCharacterDescriptor("Cuerpo", "Conformación", "Tiene cerata");
		assertTrue(aPossibleSolution.addUnconfirmedDescription(aDescriptor));
		aDescriptor = new SVCharacterDescriptor("Branquias", "Número de hojas branquiales", new SingleValue(6));
		assertTrue(aPossibleSolution.addUnconfirmedDescription(aDescriptor));
	}

	/**
	 * Test method for {@link system.PossibleSolution#getLevel()}.
	 */
	@Test
	public void testGetLevel() {
		System.out.println("Iniciando pruebas para el método GetLevel()");
		assertEquals(aPossibleSolution.getLevel(), TaxonomicRank.GENUS);
	}

	/**
	 * Test method for {@link system.PossibleSolution#getStatus()}.
	 */
	@Test
	public void testGetStatus() {
		System.out.println("Iniciando pruebas para el método GetStatus()");
		assertTrue(aPossibleSolution.getStatus());
	}

	/**
	 * Test method for {@link system.PossibleSolution#getName()}.
	 */
	@Test
	public void testGetName() {
		System.out.println("Iniciando pruebas para el método GetName()");
		assertEquals(aPossibleSolution.getName(), "Glossodoris");
	}

	/**
	 * Test method for {@link system.PossibleSolution#compareTo(system.PossibleSolution)}.
	 */
	@Test
	public void testCompareTo() {
		PossibleSolution ps; 
		Taxon taxon;
		
		System.out.println("Iniciando pruebas para el método CompareTo()");
		taxon = new Taxon(TaxonomicRank.GENUS, "Chromodoris");
		ps  = new PossibleSolution();
		ps.setSolution(taxon);
		
		System.out.println("Verificar que la posible solución es igual con otra según el criterio" +
				"de comparación");
		assertEquals(0, aPossibleSolution.compareTo(ps));
		
		System.out.println("Verificar que la posible solución es distinta con otra según el criterio" +
				"de comparación");
		taxon = new Taxon(TaxonomicRank.FAMILY, "Chromodorididae");;
		ps.setSolution(taxon);
		assertTrue(aPossibleSolution.compareTo(ps) < 0);
		taxon = new Taxon(TaxonomicRank.SPECIES, "Cadlina sparsa");;
		ps.setSolution(taxon);
		assertTrue(aPossibleSolution.compareTo(ps) > 0);
	}

}
