/**
 * 
 */
package system.test;

import static org.junit.Assert.*;

import ontology.CBR.Case;
import ontology.common.CharacterDescriptor;
import ontology.common.Descriptor;
import ontology.taxonomy.Taxon;
import ontology.taxonomy.TaxonomicRank;

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
		taxon = new Taxon();
		taxon.setLevel(TaxonomicRank.GENUS);
		taxon.setName("Glossodoris");
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
		Descriptor<Object> aDescriptor;
		
		aDescriptor = new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3);
		aPossibleSolution.addConfirmedDescription(aDescriptor);
		aDescriptor = new CharacterDescriptor<Object>("Pie", "Disposición", "Sobresale al manto");
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
		Descriptor<Object> aDescriptor;
		
		System.out.println("Iniciando pruebas para el método AddConfirmedDescription()");
		
		aDescriptor = new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3);
		aPossibleSolution.addConfirmedDescription(aDescriptor);
		aDescriptor = new CharacterDescriptor<Object>("Pie", "Disposición", "Sobresale al manto");
		aPossibleSolution.addConfirmedDescription(aDescriptor);
		
		System.out.println("Verificar que no haya contradicciones en la descripción o duplicados");
		assertFalse(aPossibleSolution.addConfirmedDescription(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3)));
		assertFalse(aPossibleSolution.addConfirmedDescription(new CharacterDescriptor<Object>("Pie", "Disposición", "Sobresale al manto")));
		
		System.out.println("Verificar que se agregue un descriptor válido");
		aDescriptor = new CharacterDescriptor<Object>("Cuerpo", "Conformación", "Tiene cerata");
		assertTrue(aPossibleSolution.addConfirmedDescription(aDescriptor));
		aDescriptor = new CharacterDescriptor<Object>("Branquias", "Número de hojas branquiales", 6);
		assertTrue(aPossibleSolution.addConfirmedDescription(aDescriptor));
	}

	/**
	 * Test method for {@link system.PossibleSolution#addContradictions(ontology.common.Descriptor)}.
	 */
	@Test
	public void testAddContradictions() {
		Descriptor<Object> aDescriptor;
		
		System.out.println("Iniciando pruebas para el método AddContradictions()");
		
		aDescriptor = new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3);
		aPossibleSolution.addContradictions(aDescriptor);
		aDescriptor = new CharacterDescriptor<Object>("Pie", "Disposición", "Sobresale al manto");
		aPossibleSolution.addContradictions(aDescriptor);
		
		System.out.println("Verificar que no haya duplicados");
		assertFalse(aPossibleSolution.addContradictions(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3)));
		assertFalse(aPossibleSolution.addContradictions(new CharacterDescriptor<Object>("Pie", "Disposición", "Sobresale al manto")));
		
		System.out.println("Verificar que se agregue un descriptor válido");
		aDescriptor = new CharacterDescriptor<Object>("Cuerpo", "Conformación", "Tiene cerata");
		assertTrue(aPossibleSolution.addContradictions(aDescriptor));
		aDescriptor = new CharacterDescriptor<Object>("Branquias", "Número de hojas branquiales", 6);
		assertTrue(aPossibleSolution.addContradictions(aDescriptor));
	}

	/**
	 * Test method for {@link system.PossibleSolution#addDoubtfulDescription(ontology.common.Descriptor)}.
	 */
	@Test
	public void testAddDoubtfulDescription() {
		Descriptor<Object> aDescriptor;
		
		System.out.println("Iniciando pruebas para el método AddDoubtfulDescription()");
		
		aDescriptor = new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3);
		aPossibleSolution.addDoubtfulDescription(aDescriptor);
		aDescriptor = new CharacterDescriptor<Object>("Pie", "Disposición", "Sobresale al manto");
		aPossibleSolution.addDoubtfulDescription(aDescriptor);
		
		System.out.println("Verificar que no haya duplicados");
		assertFalse(aPossibleSolution.addDoubtfulDescription(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3)));
		assertFalse(aPossibleSolution.addDoubtfulDescription(new CharacterDescriptor<Object>("Pie", "Disposición", "Sobresale al manto")));
		
		System.out.println("Verificar que se agregue un descriptor válido");
		aDescriptor = new CharacterDescriptor<Object>("Cuerpo", "Conformación", "Tiene cerata");
		assertTrue(aPossibleSolution.addDoubtfulDescription(aDescriptor));
		aDescriptor = new CharacterDescriptor<Object>("Branquias", "Número de hojas branquiales", 6);
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
		assertTrue(aPossibleSolution.setSolution(new Taxon()));
		
		assertFalse(aPossibleSolution.setSolution(new Object()));
		
		taxon = new Taxon();
		taxon.setLevel(TaxonomicRank.GENUS);
		taxon.setName("Glossodoris");
		aPossibleSolution  = new PossibleSolution();
		aPossibleSolution.setSolution(taxon);
	}

	/**
	 * Test method for {@link system.PossibleSolution#addSolutionDescription(ontology.common.Descriptor)}.
	 */
	@Test
	public void testAddSolutionDescription() {
		Descriptor<Object> aDescriptor;
		
		System.out.println("Iniciando pruebas para el método AddSolutionDescription()");
		
		aDescriptor = new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3);
		aPossibleSolution.addSolutionDescription(aDescriptor);
		aDescriptor = new CharacterDescriptor<Object>("Pie", "Disposición", "Sobresale al manto");
		aPossibleSolution.addSolutionDescription(aDescriptor);
		
		System.out.println("Verificar que no haya duplicados");
		assertFalse(aPossibleSolution.addSolutionDescription(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3)));
		assertFalse(aPossibleSolution.addSolutionDescription(new CharacterDescriptor<Object>("Pie", "Disposición", "Sobresale al manto")));
		
		System.out.println("Verificar que se agregue un descriptor válido");
		aDescriptor = new CharacterDescriptor<Object>("Cuerpo", "Conformación", "Tiene cerata");
		assertTrue(aPossibleSolution.addSolutionDescription(aDescriptor));
		aDescriptor = new CharacterDescriptor<Object>("Branquias", "Número de hojas branquiales", 6);
		assertTrue(aPossibleSolution.addSolutionDescription(aDescriptor));
	}

	/**
	 * Test method for {@link system.PossibleSolution#addUnconfirmedDescription(ontology.common.Descriptor)}.
	 */
	@Test
	public void testAddUnconfirmedDescription() {
		Descriptor<Object> aDescriptor;
		
		System.out.println("Iniciando pruebas para el método AddUnconfirmedDescription()");
		
		aDescriptor = new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3);
		aPossibleSolution.addUnconfirmedDescription(aDescriptor);
		aDescriptor = new CharacterDescriptor<Object>("Pie", "Disposición", "Sobresale al manto");
		aPossibleSolution.addUnconfirmedDescription(aDescriptor);
		
		System.out.println("Verificar que no haya duplicados");
		assertFalse(aPossibleSolution.addUnconfirmedDescription(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3)));
		assertFalse(aPossibleSolution.addUnconfirmedDescription(new CharacterDescriptor<Object>("Pie", "Disposición", "Sobresale al manto")));
		
		System.out.println("Verificar que se agregue un descriptor válido");
		aDescriptor = new CharacterDescriptor<Object>("Cuerpo", "Conformación", "Tiene cerata");
		assertTrue(aPossibleSolution.addUnconfirmedDescription(aDescriptor));
		aDescriptor = new CharacterDescriptor<Object>("Branquias", "Número de hojas branquiales", 6);
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
		taxon = new Taxon();
		taxon.setLevel(TaxonomicRank.GENUS);
		ps  = new PossibleSolution();
		ps.setSolution(taxon);
		
		System.out.println("Verificar que la posible solución es igual con otra según el criterio" +
				"de comparación");
		assertEquals(0, aPossibleSolution.compareTo(ps));
		
		System.out.println("Verificar que la posible solución es distinta con otra según el criterio" +
				"de comparación");
		taxon.setLevel(TaxonomicRank.FAMILY);;
		ps.setSolution(taxon);
		assertTrue(aPossibleSolution.compareTo(ps) < 0);
		taxon.setLevel(TaxonomicRank.SPECIES);;
		ps.setSolution(taxon);
		assertTrue(aPossibleSolution.compareTo(ps) > 0);
	}

}
