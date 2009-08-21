/**
 * 
 */
package searchHintsBase.Lists.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ontology.common.CharacterDescriptor;
import ontology.common.Descriptor;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import searchHintsBase.Lists.PatternsbyStructureList;
import searchHintsBase.Elements.DescriptorsPattern;
import searchHintsBase.Elements.PatternsbyStructure;

/**
 * @author Armando
 *
 */
public class PatternsbyStructureListTest {
	private static PatternsbyStructureList patterns;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Iniciando pruebas para la clase " + PatternsbyStructureList.class.getName());
		patterns = new PatternsbyStructureList();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Teminando pruebas para la clase " + PatternsbyStructureList.class.getName());
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
	 * Test method for {@link searchHintsBase.Lists.PatternsbyStructureList#add(searchHintsBase.elements.PatternsbyStructure)}.
	 */
	@Test
	public void testAddPatternsbyStructure() {
		List<Descriptor<Object>> dl;
		PatternsbyStructure patternsbyStructure;
		
		System.out.println("Iniciando pruebas para el método Add()");
		
		System.out.println("Verificar que no se agregue una referencia a null");
		assertFalse(patterns.add(null));
		
		System.out.println("Verificar que no se agregue una referencia Sin patronres");
		assertFalse(patterns.add(new PatternsbyStructure("Pie")));
		
		System.out.println("Verificar que se agregue una referencia válida");
		dl = new ArrayList<Descriptor<Object>>();
		dl.add(new CharacterDescriptor<Object>("Branquias", "Número de hojas branquiales", 6));
		patternsbyStructure = new PatternsbyStructure("Branquias");
		patternsbyStructure.addPattern(new DescriptorsPattern(dl, 1, 0));
		assertTrue(patterns.add(patternsbyStructure));
		
		dl = new ArrayList<Descriptor<Object>>();
		dl.add(new CharacterDescriptor<Object>("Branquias", "Número de hojas branquiales", 6));
		patternsbyStructure = new PatternsbyStructure("Branquias");
		patternsbyStructure.addPattern(new DescriptorsPattern(dl, 10, 0));
		assertTrue(patterns.add(patternsbyStructure));
	}

	/**
	 * Test method for {@link searchHintsBase.Lists.PatternsbyStructureList#sortBySuccessFrecuencyCriteria(java.util.List)}.
	 */
	@Test
	public void testSortBySuccessFrecuencyCriteria() {
		List<Descriptor<Object>> dl;
		PatternsbyStructure patternsbyStructure;
		
		System.out.println("Iniciando pruebas para el método SortBySuccessFrecuencyCriteria()");
		
		System.out.println("Verificar que se obtiene una lista ordenada de forma descendente según criterio");
		dl = new ArrayList<Descriptor<Object>>();
		dl.add(new CharacterDescriptor<Object>("Branquias", "Número de hojas branquiales", 6));
		dl.add(new CharacterDescriptor<Object>("Branquias", "Forma de hojas branquiales", "Tripinnada"));
		dl.add(new CharacterDescriptor<Object>("Branquias", "Posición durante desplazamiento", "Hacia atras"));
		dl.add(new CharacterDescriptor<Object>("Branquias", "Posición del ano con respecto a la branquia",
				"En el centro"));
		patternsbyStructure = new PatternsbyStructure("Branquias");
		patternsbyStructure.addPattern(new DescriptorsPattern(dl, 10, 0));
		assertTrue(patterns.add(patternsbyStructure));
		
		dl = new ArrayList<Descriptor<Object>>();
		dl.add(new CharacterDescriptor<Object>("Branquias", "Número de hojas branquiales", 6));
		dl.add(new CharacterDescriptor<Object>("Branquias", "Posición durante desplazamiento", "Hacia atras"));
		dl.add(new CharacterDescriptor<Object>("Branquias", "Posición del ano con respecto a la branquia",
				"En el centro"));
		patternsbyStructure = new PatternsbyStructure("Branquias");
		patternsbyStructure.addPattern(new DescriptorsPattern(dl, 5, 0));
		assertTrue(patterns.add(patternsbyStructure));
		
		dl = new ArrayList<Descriptor<Object>>();
		dl.add(new CharacterDescriptor<Object>("Cuerpo", "Forma", "Ovalado"));
		dl.add(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 1));
		dl.add(new CharacterDescriptor<Object>("Cuerpo", "Conformación", "Tiene cerata"));
		patternsbyStructure = new PatternsbyStructure("Cuerpo");
		patternsbyStructure.addPattern(new DescriptorsPattern(dl, 8, 0));
		assertTrue(patterns.add(patternsbyStructure));
		
		dl = new ArrayList<Descriptor<Object>>();
		dl.add(new CharacterDescriptor<Object>("Manto", "Textura", "Lisa"));
		dl.add(new CharacterDescriptor<Object>("Manto", "Forma del borde", "Ondulado"));
		dl.add(new CharacterDescriptor<Object>("Manto", "Textura del borde", "Lisa"));
		patternsbyStructure = new PatternsbyStructure("Manto");
		patternsbyStructure.addPattern(new DescriptorsPattern(dl, 2, 0));
		assertTrue(patterns.add(patternsbyStructure));
		
		
		dl = new ArrayList<Descriptor<Object>>();
		dl.add(new CharacterDescriptor<Object>("Branquias", "Número de hojas branquiales", 6));
		dl.add(new CharacterDescriptor<Object>("Branquias", "Posición durante desplazamiento", "Hacia atras"));
		dl.add(new CharacterDescriptor<Object>("Branquias", "Posición del ano con respecto a la branquia",
				"En el centro"));
		dl.add(new CharacterDescriptor<Object>("Manto", "Forma del borde", "Ondulado"));
		dl.add(new CharacterDescriptor<Object>("Manto", "Textura del borde", "Lisa"));
		dl.add(new CharacterDescriptor<Object>("Cuerpo", "Forma", "Ovalado"));
		
		
		assertEquals(new ArrayList<String>(Arrays.asList("Branquias", "Manto", "Cuerpo")),
				patterns.sortBySuccessFrecuencyCriteria(dl));
		
		dl.add(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 1));
		assertEquals(new ArrayList<String>(Arrays.asList("Cuerpo", "Branquias", "Manto")),
				patterns.sortBySuccessFrecuencyCriteria(dl));
	}

	/**
	 * Test method for {@link searchHintsBase.Lists.PatternsbyStructureList#sortByFailureFrecuencyCriteria(java.util.List)}.
	 */
	@Test
	public void testSortByFailureFrecuencyCriteria() {
		List<Descriptor<Object>> dl;
		PatternsbyStructure patternsbyStructure;
		
		System.out.println("Iniciando pruebas para el método SortBySuccessFrecuencyCriteria()");
		
		System.out.println("Verificar que se obtiene una lista ordenada de forma descendente según criterio");
		dl = new ArrayList<Descriptor<Object>>();
		dl.add(new CharacterDescriptor<Object>("Branquias", "Número de hojas branquiales", 6));
		dl.add(new CharacterDescriptor<Object>("Branquias", "Forma de hojas branquiales", "Tripinnada"));
		dl.add(new CharacterDescriptor<Object>("Branquias", "Posición durante desplazamiento", "Hacia atras"));
		dl.add(new CharacterDescriptor<Object>("Branquias", "Posición del ano con respecto a la branquia",
				"En el centro"));
		patternsbyStructure = new PatternsbyStructure("Branquias");
		patternsbyStructure.addPattern(new DescriptorsPattern(dl, 10, 1));
		assertTrue(patterns.add(patternsbyStructure));
		
		dl = new ArrayList<Descriptor<Object>>();
		dl.add(new CharacterDescriptor<Object>("Branquias", "Número de hojas branquiales", 6));
		dl.add(new CharacterDescriptor<Object>("Branquias", "Posición durante desplazamiento", "Hacia atras"));
		dl.add(new CharacterDescriptor<Object>("Branquias", "Posición del ano con respecto a la branquia",
				"En el centro"));
		patternsbyStructure = new PatternsbyStructure("Branquias");
		patternsbyStructure.addPattern(new DescriptorsPattern(dl, 5, 5));
		assertTrue(patterns.add(patternsbyStructure));
		
		dl = new ArrayList<Descriptor<Object>>();
		dl.add(new CharacterDescriptor<Object>("Cuerpo", "Forma", "Ovalado"));
		dl.add(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 1));
		dl.add(new CharacterDescriptor<Object>("Cuerpo", "Conformación", "Tiene cerata"));
		patternsbyStructure = new PatternsbyStructure("Cuerpo");
		patternsbyStructure.addPattern(new DescriptorsPattern(dl, 8, 3));
		assertTrue(patterns.add(patternsbyStructure));
		
		dl = new ArrayList<Descriptor<Object>>();
		dl.add(new CharacterDescriptor<Object>("Manto", "Textura", "Lisa"));
		dl.add(new CharacterDescriptor<Object>("Manto", "Forma del borde", "Ondulado"));
		dl.add(new CharacterDescriptor<Object>("Manto", "Textura del borde", "Lisa"));
		patternsbyStructure = new PatternsbyStructure("Manto");
		patternsbyStructure.addPattern(new DescriptorsPattern(dl, 2, 8));
		assertTrue(patterns.add(patternsbyStructure));
		
		
		dl = new ArrayList<Descriptor<Object>>();
		dl.add(new CharacterDescriptor<Object>("Branquias", "Número de hojas branquiales", 6));
		dl.add(new CharacterDescriptor<Object>("Branquias", "Posición durante desplazamiento", "Hacia atras"));
		dl.add(new CharacterDescriptor<Object>("Branquias", "Posición del ano con respecto a la branquia",
				"En el centro"));
		dl.add(new CharacterDescriptor<Object>("Manto", "Forma del borde", "Ondulado"));
		dl.add(new CharacterDescriptor<Object>("Manto", "Textura del borde", "Lisa"));
		dl.add(new CharacterDescriptor<Object>("Cuerpo", "Forma", "Ovalado"));
		
		
		assertEquals(new ArrayList<String>(Arrays.asList("Branquias", "Manto", "Cuerpo")),
				patterns.sortByFailureFrecuencyCriteria(dl));
		
		dl.add(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 1));
		assertEquals(new ArrayList<String>(Arrays.asList("Cuerpo", "Branquias", "Manto")),
				patterns.sortByFailureFrecuencyCriteria(dl));
	}

	/**
	 * Test method for {@link searchHintsBase.Lists.PatternsbyStructureList#contains(searchHintsBase.elements.PatternsbyStructure)}.
	 */
	@Test
	public void testContainsPatternsbyStructure() {
		List<Descriptor<Object>> dl;
		PatternsbyStructure patternsbyStructure;
		
		System.out.println("Iniciando pruebas para el método ContainsPatternsbyStructure()");
		
		System.out.println("Verificar que no contiene una referencia");
		dl = new ArrayList<Descriptor<Object>>();
		dl.add(new CharacterDescriptor<Object>("Branquias", "Número de hojas branquiales", 6));
		patternsbyStructure = new PatternsbyStructure("Branquias");
		patternsbyStructure.addPattern(new DescriptorsPattern(dl, 1, 0));
		assertFalse(patterns.contains(patternsbyStructure));
		
		System.out.println("Verificar que sí contiene una referencia");
		patterns.add(patternsbyStructure);
		assertTrue(patterns.contains(patternsbyStructure));
	}

	/**
	 * Test method for {@link searchHintsBase.Lists.PatternsbyStructureList#getPatternByStructure(java.lang.String)}.
	 */
	@Test
	public void testGetPatternByStructure() {
		List<Descriptor<Object>> dl;
		PatternsbyStructure patternsbyStructure;
		
		System.out.println("Iniciando pruebas para el método GetPatternByStructure()");
		
		System.out.println("Verificar que no contiene una referencia");
		dl = new ArrayList<Descriptor<Object>>();
		dl.add(new CharacterDescriptor<Object>("Branquias", "Número de hojas branquiales", 6));
		patternsbyStructure = new PatternsbyStructure("Branquias");
		patternsbyStructure.addPattern(new DescriptorsPattern(dl, 1, 0));
		assertNull(patterns.getPatternByStructure(patternsbyStructure.getStructureName()));
		
		System.out.println("Verificar que sí contiene una referencia");
		patterns.add(patternsbyStructure);
		assertNotNull(patterns.getPatternByStructure(patternsbyStructure.getStructureName()));
	}

	/**
	 * Test method for {@link searchHintsBase.Lists.PatternsbyStructureList#contains(java.lang.String)}.
	 */
	@Test
	public void testContainsString() {
		List<Descriptor<Object>> dl;
		PatternsbyStructure patternsbyStructure;
		
		System.out.println("Iniciando pruebas para el método ContainsString()");
		
		System.out.println("Verificar que no contiene una referencia");
		dl = new ArrayList<Descriptor<Object>>();
		dl.add(new CharacterDescriptor<Object>("Branquias", "Número de hojas branquiales", 6));
		patternsbyStructure = new PatternsbyStructure("Branquias");
		patternsbyStructure.addPattern(new DescriptorsPattern(dl, 1, 0));
		assertFalse(patterns.contains(patternsbyStructure.getStructureName()));
		
		System.out.println("Verificar que sí contiene una referencia");
		patterns.add(patternsbyStructure);
		assertTrue(patterns.contains(patternsbyStructure.getStructureName()));
	}
}
