/**
 * 
 */
package searchHintsBase.Lists.test;

import static org.junit.Assert.*;

import ontology.common.Description;
import ontology.common.SSCharacterDescriptor;
import ontology.common.SVCharacterDescriptor;
import ontology.common.SingleValue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import searchHintsBase.Elements.WeightedDescriptorPattern;
import searchHintsBase.Elements.WeightedPatternsbyStructure;
import searchHintsBase.Lists.WeightedPatternsbyStructureList;

/**
 * @author Armando
 *
 */
public class WeightedPatternsbyStructureListTest {
	private static WeightedPatternsbyStructureList patterns;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Iniciando pruebas para la clase " + WeightedPatternsbyStructureList.class.getName());
		patterns = new WeightedPatternsbyStructureList();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Iniciando pruebas para la clase " + WeightedPatternsbyStructureList.class.getName());
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
		patterns.clear();
	}

	/**
	 * Test method for {@link searchHintsBase.Lists.WeightedPatternsbyStructureList#add(searchHintsBase.Elements.WeightedPatternsbyStructure)}.
	 */
	@Test
	public void testAddWeightedPatternsbyStructure() {
		WeightedPatternsbyStructure spbs;
		
		System.out.println("Iniciando pruebas para el método Add()");
		
		System.out.println("Verificar que no se agregue una referencia a null");
		assertFalse(patterns.add(null));
		
		System.out.println("Verificar que no se agregue una referencia Sin patronres");
		assertFalse(patterns.add(new WeightedPatternsbyStructure("Pie")));
		
		System.out.println("Verificar que se agregue una referencia válida");
		spbs = new WeightedPatternsbyStructure("Branquias");
		spbs.addPattern(new WeightedDescriptorPattern(new SVCharacterDescriptor("Branquias", 
				"Número de hojas branquiales", new SingleValue(6)), 0.5, 1));
		assertTrue(patterns.add(spbs));
		
		spbs = new WeightedPatternsbyStructure("Branquias");
		spbs.addPattern(new WeightedDescriptorPattern(new SVCharacterDescriptor("Branquias",
				"Número de hojas branquiales", new SingleValue(6)), 9, 10));
		assertTrue(patterns.add(spbs));
	}

	/**
	 * Test method for {@link searchHintsBase.Lists.WeightedPatternsbyStructureList#sortByMeanWeightCriteria(java.util.List)}.
	 */
	@Test
	public void testSortByMeanWeightCriteria() {
		Description d1, d2;
		WeightedPatternsbyStructure spbs;
		
		System.out.println("Iniciando pruebas para el método SortBySuccessCriteria()");
		
		System.out.println("Verificar que se obtiene una lista ordenada de forma descendente según criterio");
		spbs = new WeightedPatternsbyStructure("Branquias");
		spbs.addPattern(new WeightedDescriptorPattern(new SVCharacterDescriptor("Branquias",
				"Número de hojas branquiales", new SingleValue(6)), 9, 10));
		assertTrue(patterns.add(spbs));
		
		spbs = new WeightedPatternsbyStructure("Branquias");
		spbs.addPattern(new WeightedDescriptorPattern(new SSCharacterDescriptor("Branquias",
				"Forma de hojas branquiales", "Tripinnada"), 2, 5));
		assertTrue(patterns.add(spbs));
		
		spbs.addPattern(new WeightedDescriptorPattern(new SSCharacterDescriptor("Branquias", 
				"Posición del ano con respecto a la branquia", "En el centro"), 3, 10));
		assertTrue(patterns.add(spbs));
		
		spbs = new WeightedPatternsbyStructure("Cuerpo");
		spbs.addPattern(new WeightedDescriptorPattern(new SSCharacterDescriptor("Cuerpo", 
				"Forma", "Ovalado"), 1, 8));
		assertTrue(patterns.add(spbs));
		
		spbs = new WeightedPatternsbyStructure("Manto");
		spbs.addPattern(new WeightedDescriptorPattern(new SSCharacterDescriptor("Manto", 
				"Textura", "Lisa"), 2, 6));
		assertTrue(patterns.add(spbs));
		
		spbs.addPattern(new WeightedDescriptorPattern(new SSCharacterDescriptor("Manto", 
				"Forma del borde", "Ondulado"), 1, 10));
		assertTrue(patterns.add(spbs));
		
		d1 = new Description();
		d1.addToConcreteDescription(new SVCharacterDescriptor("Branquias", "Número de hojas branquiales", new SingleValue(6)));
		
		assertEquals(d1, patterns.sortByMeanWeightCriteria(d1));
		d2 = new Description();
		assertEquals(d2.getDescriptors(), patterns.sortByMeanWeightCriteria(d2).getDescriptors());
		
		d2.addToConcreteDescription(new SVCharacterDescriptor("Branquias", "Número de hojas branquiales", new SingleValue(6)));
		assertEquals(d2.getDescriptors(), patterns.sortByMeanWeightCriteria(d1).getDescriptors());
		
		d1.addToConcreteDescription(new SSCharacterDescriptor("Branquias", "Posición durante desplazamiento", "Hacia atras"));
		d1.addToConcreteDescription(new SSCharacterDescriptor("Branquias", "Posición del ano con respecto a la branquia",
				"En el centro"));
		
		d2.addToConcreteDescription(new SSCharacterDescriptor("Branquias", "Posición del ano con respecto a la branquia",
		"En el centro"));
		d2.addToConcreteDescription(new SSCharacterDescriptor("Branquias", "Posición durante desplazamiento", "Hacia atras"));
		assertEquals(d2.getDescriptors(), patterns.sortByMeanWeightCriteria(d1).getDescriptors());
		
		d1 = new Description();
		d1.addToConcreteDescription(new SSCharacterDescriptor("Manto", "Textura del borde", "Lisa"));
		d1.addToConcreteDescription(new SSCharacterDescriptor("Manto", "Forma del borde", "Ondulado"));
		
		d2 = new Description();
		d2.addToConcreteDescription(new SSCharacterDescriptor("Manto", "Forma del borde", "Ondulado"));
		d2.addToConcreteDescription(new SSCharacterDescriptor("Manto", "Textura del borde", "Lisa"));
		assertEquals(d2.getDescriptors(), patterns.sortByMeanWeightCriteria(d1).getDescriptors());
	}

	/**
	 * Test method for {@link searchHintsBase.Lists.WeightedPatternsbyStructureList#contains(searchHintsBase.Elements.WeightedPatternsbyStructure)}.
	 */
	@Test
	public void testContainsWeightedPatternsbyStructure() {
		WeightedPatternsbyStructure spbs;
		
		System.out.println("Iniciando pruebas para el método ContainsSpecificPatternsbyStructure()");
		
		System.out.println("Verificar que no contiene una referencia");
		spbs = new WeightedPatternsbyStructure("Branquias");
		spbs.addPattern(new WeightedDescriptorPattern(new SVCharacterDescriptor("Branquias",
				"Número de hojas branquiales", new SingleValue(6)), 1, 1));
		assertFalse(patterns.contains(spbs));
		
		System.out.println("Verificar que sí contiene una referencia");
		assertTrue(patterns.add(spbs));
		assertTrue(patterns.contains(spbs));
	}

	/**
	 * Test method for {@link searchHintsBase.Lists.WeightedPatternsbyStructureList#contains(java.lang.String)}.
	 */
	@Test
	public void testContainsString() {
		WeightedPatternsbyStructure spbs;
		
		System.out.println("Iniciando pruebas para el método ContainsString()");
		
		System.out.println("Verificar que no contiene una referencia");
		spbs = new WeightedPatternsbyStructure("Branquias");
		spbs.addPattern(new WeightedDescriptorPattern(new SVCharacterDescriptor("Branquias",
				"Número de hojas branquiales", new SingleValue(6)), 1, 1));
		assertFalse(patterns.contains(spbs.getStructureName()));
		
		System.out.println("Verificar que sí contiene una referencia");
		assertTrue(patterns.add(spbs));
		assertTrue(patterns.contains(spbs.getStructureName()));
	}

	/**
	 * Test method for {@link searchHintsBase.Lists.WeightedPatternsbyStructureList#getPatterns(java.lang.String)}.
	 */
	@Test
	public void testGetPatterns() {
		WeightedPatternsbyStructure spbs;
		
		System.out.println("Iniciando pruebas para el método ContainsString()");
		
		System.out.println("Verificar que no se obtiene tiene una referencia no incluida");
		spbs = new WeightedPatternsbyStructure("Branquias");
		spbs.addPattern(new WeightedDescriptorPattern(new SVCharacterDescriptor("Branquias",
				"Número de hojas branquiales", new SingleValue(6)), 0.5, 1));
		assertNull(patterns.getPatterns(spbs.getStructureName()));
		
		System.out.println("Verificar que sí se obtiene una referencia no incluida");
		assertTrue(patterns.add(spbs));
		assertNotNull(patterns.getPatterns(spbs.getStructureName()));
		assertSame(spbs, patterns.getPatterns(spbs.getStructureName()));
	}

}
