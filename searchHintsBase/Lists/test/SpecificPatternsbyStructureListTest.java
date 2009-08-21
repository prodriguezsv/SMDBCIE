/**
 * 
 */
package searchHintsBase.Lists.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import ontology.common.CharacterDescriptor;
import ontology.common.Descriptor;

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
		
		System.out.println("Iniciando pruebas para el m�todo Add()");
		
		System.out.println("Verificar que no se agregue una referencia a null");
		assertFalse(patterns.add(null));
		
		System.out.println("Verificar que no se agregue una referencia Sin patronres");
		assertFalse(patterns.add(new SpecificPatternsbyStructure("Pie")));
		
		System.out.println("Verificar que se agregue una referencia v�lida");
		spbs = new SpecificPatternsbyStructure("Branquias");
		spbs.addPattern(new SpecificDescriptorPattern(new CharacterDescriptor<Object>("Branquias", 
				"N�mero de hojas branquiales", 6), 1));
		assertTrue(patterns.add(spbs));
		
		spbs = new SpecificPatternsbyStructure("Branquias");
		spbs.addPattern(new SpecificDescriptorPattern(new CharacterDescriptor<Object>("Branquias",
				"N�mero de hojas branquiales", 6), 10));
		assertTrue(patterns.add(spbs));
	}

	/**
	 * Test method for {@link searchHintsBase.Lists.SpecificPatternsbyStructureList#sortBySuccessCriteria(java.util.List)}.
	 */
	@Test
	public void testSortBySuccessCriteria() {
		List<Descriptor<Object>> d1, d2;
		SpecificPatternsbyStructure spbs;
		
		System.out.println("Iniciando pruebas para el m�todo SortBySuccessCriteria()");
		
		System.out.println("Verificar que se obtiene una lista ordenada de forma descendente seg�n criterio");
		spbs = new SpecificPatternsbyStructure("Branquias");
		spbs.addPattern(new SpecificDescriptorPattern(new CharacterDescriptor<Object>("Branquias",
				"N�mero de hojas branquiales", 6), 10));
		assertTrue(patterns.add(spbs));
		
		spbs = new SpecificPatternsbyStructure("Branquias");
		spbs.addPattern(new SpecificDescriptorPattern(new CharacterDescriptor<Object>("Branquias",
				"Forma de hojas branquiales", "Tripinnada"), 5));
		assertTrue(patterns.add(spbs));
		
		spbs.addPattern(new SpecificDescriptorPattern(new CharacterDescriptor<Object>("Branquias", 
				"Posici�n del ano con respecto a la branquia", "En el centro"), 3));
		assertTrue(patterns.add(spbs));
		
		spbs = new SpecificPatternsbyStructure("Cuerpo");
		spbs.addPattern(new SpecificDescriptorPattern(new CharacterDescriptor<Object>("Cuerpo", 
				"Forma", "Ovalado"), 8));
		assertTrue(patterns.add(spbs));
		
		spbs = new SpecificPatternsbyStructure("Manto");
		spbs.addPattern(new SpecificDescriptorPattern(new CharacterDescriptor<Object>("Manto", 
				"Textura", "Lisa"), 2));
		assertTrue(patterns.add(spbs));
		
		spbs.addPattern(new SpecificDescriptorPattern(new CharacterDescriptor<Object>("Manto", 
				"Forma del borde", "Ondulado"), 10));
		assertTrue(patterns.add(spbs));
		
		d1 = new ArrayList<Descriptor<Object>>();
		d1.add(new CharacterDescriptor<Object>("Branquias", "N�mero de hojas branquiales", 6));
		
		assertEquals(d1, patterns.sortBySuccessCriteria(d1));
		d2 = new ArrayList<Descriptor<Object>>();
		assertEquals(d2, patterns.sortBySuccessCriteria(d2));
		
		d2.add(new CharacterDescriptor<Object>("Branquias", "N�mero de hojas branquiales", 6));
		assertEquals(d2, patterns.sortBySuccessCriteria(d1));
		
		d1.add(new CharacterDescriptor<Object>("Branquias", "Posici�n durante desplazamiento", "Hacia atras"));
		d1.add(new CharacterDescriptor<Object>("Branquias", "Posici�n del ano con respecto a la branquia",
				"En el centro"));
		
		d2.add(new CharacterDescriptor<Object>("Branquias", "Posici�n del ano con respecto a la branquia",
		"En el centro"));
		d2.add(new CharacterDescriptor<Object>("Branquias", "Posici�n durante desplazamiento", "Hacia atras"));
		assertEquals(d2, patterns.sortBySuccessCriteria(d1));
		
		d1 = new ArrayList<Descriptor<Object>>();
		d1.add(new CharacterDescriptor<Object>("Manto", "Textura del borde", "Lisa"));
		d1.add(new CharacterDescriptor<Object>("Manto", "Forma del borde", "Ondulado"));
		
		d2 = new ArrayList<Descriptor<Object>>();
		d2.add(new CharacterDescriptor<Object>("Manto", "Forma del borde", "Ondulado"));
		d2.add(new CharacterDescriptor<Object>("Manto", "Textura del borde", "Lisa"));
		assertEquals(d2, patterns.sortBySuccessCriteria(d1));
	}

	/**
	 * Test method for {@link searchHintsBase.Lists.SpecificPatternsbyStructureList#contains(searchHintsBase.Elements.SpecificPatternsbyStructure)}.
	 */
	@Test
	public void testContainsSpecificPatternsbyStructure() {
		SpecificPatternsbyStructure spbs;
		
		System.out.println("Iniciando pruebas para el m�todo ContainsSpecificPatternsbyStructure()");
		
		System.out.println("Verificar que no contiene una referencia");
		spbs = new SpecificPatternsbyStructure("Branquias");
		spbs.addPattern(new SpecificDescriptorPattern(new CharacterDescriptor<Object>("Branquias",
				"N�mero de hojas branquiales", 6), 1));
		assertFalse(patterns.contains(spbs));
		
		System.out.println("Verificar que s� contiene una referencia");
		assertTrue(patterns.add(spbs));
		assertTrue(patterns.contains(spbs));
	}

	/**
	 * Test method for {@link searchHintsBase.Lists.SpecificPatternsbyStructureList#contains(java.lang.String)}.
	 */
	@Test
	public void testContainsString() {
		SpecificPatternsbyStructure spbs;
		
		System.out.println("Iniciando pruebas para el m�todo ContainsString()");
		
		System.out.println("Verificar que no contiene una referencia");
		spbs = new SpecificPatternsbyStructure("Branquias");
		spbs.addPattern(new SpecificDescriptorPattern(new CharacterDescriptor<Object>("Branquias",
				"N�mero de hojas branquiales", 6), 1));
		assertFalse(patterns.contains(spbs.getStructureName()));
		
		System.out.println("Verificar que s� contiene una referencia");
		assertTrue(patterns.add(spbs));
		assertTrue(patterns.contains(spbs.getStructureName()));
	}

	/**
	 * Test method for {@link searchHintsBase.Lists.SpecificPatternsbyStructureList#getSpecificPatternbyStructure(java.lang.String)}.
	 */
	@Test
	public void testGetSpecificPatternbyStructure() {
		SpecificPatternsbyStructure spbs;
		
		System.out.println("Iniciando pruebas para el m�todo ContainsString()");
		
		System.out.println("Verificar que no se obtiene tiene una referencia no incluida");
		spbs = new SpecificPatternsbyStructure("Branquias");
		spbs.addPattern(new SpecificDescriptorPattern(new CharacterDescriptor<Object>("Branquias",
				"N�mero de hojas branquiales", 6), 1));
		assertNull(patterns.getSpecificPatternbyStructure(spbs.getStructureName()));
		
		System.out.println("Verificar que s� se obtiene una referencia no incluida");
		assertTrue(patterns.add(spbs));
		assertNotNull(patterns.getSpecificPatternbyStructure(spbs.getStructureName()));
		assertSame(spbs, patterns.getSpecificPatternbyStructure(spbs.getStructureName()));
	}

}
