/**
 * 
 */
package searchHintsBase.Elements;

import static org.junit.Assert.*;

import ontology.common.CharacterDescriptor;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Armando
 *
 */
public class WeightedPatternsbyStructureTest {
	private static WeightedPatternsbyStructure patterns;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Iniciando pruebas para la clase " + WeightedPatternsbyStructure.class.getName());
		patterns = new WeightedPatternsbyStructure("Cuerpo");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Terminando pruebas para la clase " + WeightedPatternsbyStructure.class.getName());
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
		patterns.getPatterns().clear();
	}

	/**
	 * Test method for {@link searchHintsBase.Elements.WeightedPatternsbyStructure#addPattern(searchHintsBase.Elements.WeightedDescriptorPattern)}.
	 */
	@Test
	public void testAddPattern() {
		System.out.println("Iniciando pruebas para el método AddPattern()");
		
		System.out.println("Verificar que un patron de descriptores se agrega correctamente");
		assertTrue(patterns.addPattern(new WeightedDescriptorPattern(new CharacterDescriptor<Object>("Cuerpo",
				"Longitud", 0.3), 0.5, 1)));
		
		assertTrue(patterns.addPattern(new WeightedDescriptorPattern(new CharacterDescriptor<Object>("Cuerpo",
				"Longitud", 0.3), 0.75, 5)));
		assertTrue(patterns.getPatterns().get(0).getNumberTaxa() == 6);
	
		assertTrue(patterns.addPattern(new WeightedDescriptorPattern(new CharacterDescriptor<Object>("Cuerpo",
				"Conformación", "Tiene cerata"), 9, 10)));
		assertTrue(patterns.getPatterns().get(0).getNumberTaxa() == 10);
		
		System.out.println("Verificar que un patron de descriptores incompatible o incorrecto no se agrega");
		assertFalse(patterns.addPattern(null));
		
		assertFalse(patterns.addPattern(new WeightedDescriptorPattern(new CharacterDescriptor<Object>("Branquias",
				"Número de hojas branquiales", 6), 1, 1)));
	}

	/**
	 * Test method for {@link searchHintsBase.Elements.WeightedPatternsbyStructure#contains(searchHintsBase.Elements.WeightedDescriptorPattern)}.
	 */
	@Test
	public void testContains() {
		WeightedDescriptorPattern pattern;
		
		System.out.println("Iniciando pruebas para el método Contains()");
		
		System.out.println("Verificar que no contiene una referencia");
		pattern = new WeightedDescriptorPattern(new CharacterDescriptor<Object>("Cuerpo", "Conformación",
				"Tiene cerata"), 0.5, 1);
		assertFalse(patterns.contains(pattern));
		
		System.out.println("Verificar que sí contiene una referencia");
		assertTrue(patterns.addPattern(pattern));
		assertTrue(patterns.contains(pattern));
	}

	/**
	 * Test method for {@link searchHintsBase.Elements.WeightedPatternsbyStructure#getPattern(ontology.common.Descriptor)}.
	 */
	@Test
	public void testGetPattern() {
		WeightedDescriptorPattern pattern;
		
		System.out.println("Iniciando pruebas para el método ContainsString()");
		
		System.out.println("Verificar que se obtiene una referencia null");
		pattern = new WeightedDescriptorPattern(new CharacterDescriptor<Object>("Cuerpo", "Conformación",
				"Tiene cerata"), 0.5, 1);
		assertNull(patterns.getPattern(pattern.getPattern()));
		
		System.out.println("Verificar que sí obtiene una referencia no null");
		assertTrue(patterns.addPattern(pattern));
		assertNotNull(patterns.getPattern(pattern.getPattern()));
	}

}
