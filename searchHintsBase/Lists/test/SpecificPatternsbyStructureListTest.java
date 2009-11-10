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

import searchHintsBase.Elements.SpecificDescriptorPattern;
import searchHintsBase.Elements.SpecificPatternsbyStructure;
import searchHintsBase.Lists.SpecificPatternsbyStructureList;

/**
 * @author Armando
 *
 */
public class SpecificPatternsbyStructureListTest {
	private static SpecificPatternsbyStructureList patterns;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Iniciando pruebas para la clase " + SpecificPatternsbyStructureList.class.getName());
		patterns = new SpecificPatternsbyStructureList();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Teminando pruebas para la clase " + SpecificPatternsbyStructureList.class.getName());
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
	 * Test method for {@link searchHintsBase.Lists.SpecificPatternsbyStructureList#add(searchHintsBase.Elements.SpecificPatternsbyStructure)}.
	 */
	@Test
	public void testAddSpecificPatternsbyStructure() {
		SpecificPatternsbyStructure spbs;
		
		System.out.println("Iniciando pruebas para el método Add()");
		
		System.out.println("Verificar que no se agregue una referencia a null");
		assertFalse(patterns.add(null));
		
		System.out.println("Verificar que no se agregue una referencia Sin patronres");
		assertFalse(patterns.add(new SpecificPatternsbyStructure("Pie")));
		
		System.out.println("Verificar que se agregue una referencia válida");
		spbs = new SpecificPatternsbyStructure("Branquias");
		spbs.addPattern(new SpecificDescriptorPattern(new SVCharacterDescriptor("Branquias", 
				"Número de hojas branquiales", new SingleValue(6)), 1));
		assertTrue(patterns.add(spbs));
		
		spbs = new SpecificPatternsbyStructure("Branquias");
		spbs.addPattern(new SpecificDescriptorPattern(new SVCharacterDescriptor("Branquias",
				"Número de hojas branquiales", new SingleValue(6)), 10));
		assertTrue(patterns.add(spbs));
	}

	/**
	 * Test method for {@link searchHintsBase.Lists.SpecificPatternsbyStructureList#sortBySuccessCriteria(java.util.List)}.
	 */
	@Test
	public void testSortBySuccessCriteria() {
		Description d1, d2;
		SpecificPatternsbyStructure spbs;
		
		System.out.println("Iniciando pruebas para el método SortBySuccessCriteria()");
		
		System.out.println("Verificar que se obtiene una lista ordenada de forma descendente según criterio");
		spbs = new SpecificPatternsbyStructure("Branquias");
		spbs.addPattern(new SpecificDescriptorPattern(new SVCharacterDescriptor("Branquias",
				"Número de hojas branquiales", new SingleValue(6)), 10));
		assertTrue(patterns.add(spbs));
		
		spbs = new SpecificPatternsbyStructure("Branquias");
		spbs.addPattern(new SpecificDescriptorPattern(new SSCharacterDescriptor("Branquias",
				"Forma de hojas branquiales", "Tripinnada"), 5));
		assertTrue(patterns.add(spbs));
		
		spbs.addPattern(new SpecificDescriptorPattern(new SSCharacterDescriptor("Branquias", 
				"Posición del ano con respecto a la branquia", "En el centro"), 3));
		assertTrue(patterns.add(spbs));
		
		spbs = new SpecificPatternsbyStructure("Cuerpo");
		spbs.addPattern(new SpecificDescriptorPattern(new SSCharacterDescriptor("Cuerpo", 
				"Forma", "Ovalado"), 8));
		assertTrue(patterns.add(spbs));
		
		spbs = new SpecificPatternsbyStructure("Manto");
		spbs.addPattern(new SpecificDescriptorPattern(new SSCharacterDescriptor("Manto", 
				"Textura", "Lisa"), 2));
		assertTrue(patterns.add(spbs));
		
		spbs.addPattern(new SpecificDescriptorPattern(new SSCharacterDescriptor("Manto", 
				"Forma del borde", "Ondulado"), 10));
		assertTrue(patterns.add(spbs));
		
		d1 = new Description();
		d1.addToConcreteDescription(new SVCharacterDescriptor("Branquias", "Número de hojas branquiales", new SingleValue(6)));
		
		assertEquals(d1.getDescriptors(), patterns.sortBySuccessCriteria(d1).getDescriptors());
		d2 = new Description();
		assertEquals(d2, patterns.sortBySuccessCriteria(d2));
		
		d2.addToConcreteDescription(new SVCharacterDescriptor("Branquias", "Número de hojas branquiales", new SingleValue(6)));
		assertEquals(d2, patterns.sortBySuccessCriteria(d1));
		
		d1.addToConcreteDescription(new SSCharacterDescriptor("Branquias", "Posición durante desplazamiento", "Hacia atras"));
		d1.addToConcreteDescription(new SSCharacterDescriptor("Branquias", "Posición del ano con respecto a la branquia",
				"En el centro"));
		
		d2.addToConcreteDescription(new SSCharacterDescriptor("Branquias", "Posición del ano con respecto a la branquia",
		"En el centro"));
		d2.addToConcreteDescription(new SSCharacterDescriptor("Branquias", "Posición durante desplazamiento", "Hacia atras"));
		assertEquals(d2, patterns.sortBySuccessCriteria(d1));
		
		d1 = new Description();
		d1.addToConcreteDescription(new SSCharacterDescriptor("Manto", "Textura del borde", "Lisa"));
		d1.addToConcreteDescription(new SSCharacterDescriptor("Manto", "Forma del borde", "Ondulado"));
		
		d2 = new Description();
		d2.addToConcreteDescription(new SSCharacterDescriptor("Manto", "Forma del borde", "Ondulado"));
		d2.addToConcreteDescription(new SSCharacterDescriptor("Manto", "Textura del borde", "Lisa"));
		assertEquals(d2, patterns.sortBySuccessCriteria(d1));
	}

	/**
	 * Test method for {@link searchHintsBase.Lists.SpecificPatternsbyStructureList#contains(searchHintsBase.Elements.SpecificPatternsbyStructure)}.
	 */
	@Test
	public void testContainsSpecificPatternsbyStructure() {
		SpecificPatternsbyStructure spbs;
		
		System.out.println("Iniciando pruebas para el método ContainsSpecificPatternsbyStructure()");
		
		System.out.println("Verificar que no contiene una referencia");
		spbs = new SpecificPatternsbyStructure("Branquias");
		spbs.addPattern(new SpecificDescriptorPattern(new SVCharacterDescriptor("Branquias",
				"Número de hojas branquiales", new SingleValue(6)), 1));
		assertFalse(patterns.contains(spbs));
		
		System.out.println("Verificar que sí contiene una referencia");
		assertTrue(patterns.add(spbs));
		assertTrue(patterns.contains(spbs));
	}

	/**
	 * Test method for {@link searchHintsBase.Lists.SpecificPatternsbyStructureList#contains(java.lang.String)}.
	 */
	@Test
	public void testContainsString() {
		SpecificPatternsbyStructure spbs;
		
		System.out.println("Iniciando pruebas para el método ContainsString()");
		
		System.out.println("Verificar que no contiene una referencia");
		spbs = new SpecificPatternsbyStructure("Branquias");
		spbs.addPattern(new SpecificDescriptorPattern(new SVCharacterDescriptor("Branquias",
				"Número de hojas branquiales", new SingleValue(6)), 1));
		assertFalse(patterns.contains(spbs.getStructureName()));
		
		System.out.println("Verificar que sí contiene una referencia");
		assertTrue(patterns.add(spbs));
		assertTrue(patterns.contains(spbs.getStructureName()));
	}

	/**
	 * Test method for {@link searchHintsBase.Lists.SpecificPatternsbyStructureList#getPatterns(java.lang.String)}.
	 */
	@Test
	public void testGetSpecificPatternbyStructure() {
		SpecificPatternsbyStructure spbs;
		
		System.out.println("Iniciando pruebas para el método ContainsString()");
		
		System.out.println("Verificar que no se obtiene tiene una referencia no incluida");
		spbs = new SpecificPatternsbyStructure("Branquias");
		spbs.addPattern(new SpecificDescriptorPattern(new SVCharacterDescriptor("Branquias",
				"Número de hojas branquiales", new SingleValue(6)), 1));
		assertNull(patterns.getPatterns(spbs.getStructureName()));
		
		System.out.println("Verificar que sí se obtiene una referencia no incluida");
		assertTrue(patterns.add(spbs));
		assertNotNull(patterns.getPatterns(spbs.getStructureName()));
		assertSame(spbs, patterns.getPatterns(spbs.getStructureName()));
	}

}
