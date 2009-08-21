/**
 * 
 */
package searchHintsBase.Elements.test;

import static org.junit.Assert.*;

import ontology.common.CharacterDescriptor;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import searchHintsBase.Elements.SpecificDescriptorPattern;
import searchHintsBase.Elements.SpecificPatternsbyStructure;

/**
 * @author Armando
 *
 */
public class SpecificPatternsbyStructureTest {
	private static SpecificPatternsbyStructure patterns;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Iniciando pruebas para la clase " + SpecificPatternsbyStructure.class.getName());
		patterns = new SpecificPatternsbyStructure("Cuerpo");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Terminando pruebas para la clase " + SpecificPatternsbyStructure.class.getName());
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
		patterns.getSpecificDescriptorPatterns().clear();
	}

	/**
	 * Test method for {@link searchHintsBase.Elements.SpecificPatternsbyStructure#addPattern(searchHintsBase.Elements.SpecificDescriptorPattern)}.
	 */
	@Test
	public void testAddPattern() {
		
		System.out.println("Iniciando pruebas para el método AddPattern()");
		
		System.out.println("Verificar que un patron de descriptores se agrega correctamente");
		assertTrue(patterns.addPattern(new SpecificDescriptorPattern(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3), 1)));
		
		assertTrue(patterns.addPattern(new SpecificDescriptorPattern(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3), 1)));
		assertTrue(patterns.getSpecificDescriptorPatterns().get(0).getFrequency() == 2);
	
		assertTrue(patterns.addPattern(new SpecificDescriptorPattern(new CharacterDescriptor<Object>("Cuerpo", "Conformación", "Tiene cerata"), 10)));
		assertTrue(patterns.getSpecificDescriptorPatterns().get(0).getFrequency() == 10);
		
		System.out.println("Verificar que un patron de descriptores incompatible o incorrecto no se agrega");
		assertFalse(patterns.addPattern(null));
		assertFalse(patterns.addPattern(new SpecificDescriptorPattern(null)));
		
		assertFalse(patterns.addPattern(new SpecificDescriptorPattern(new CharacterDescriptor<Object>("Branquias", "Número de hojas branquiales", 6), 1)));
	}

	/**
	 * Test method for {@link searchHintsBase.Elements.SpecificPatternsbyStructure#contains(searchHintsBase.Elements.SpecificDescriptorPattern)}.
	 */
	@Test
	public void testContains() {
		SpecificDescriptorPattern specificDescriptorPattern;
		
		System.out.println("Iniciando pruebas para el método Contains()");
		
		System.out.println("Verificar que no contiene una referencia");
		specificDescriptorPattern = new SpecificDescriptorPattern(new CharacterDescriptor<Object>("Cuerpo", "Conformación", "Tiene cerata"), 1);
		assertFalse(patterns.contains(specificDescriptorPattern));
		
		System.out.println("Verificar que sí contiene una referencia");
		assertTrue(patterns.addPattern(specificDescriptorPattern));
		assertTrue(patterns.contains(specificDescriptorPattern));
	}

	/**
	 * Test method for {@link searchHintsBase.Elements.SpecificPatternsbyStructure#getSpecificDescriptorPattern(java.lang.String)}.
	 */
	@Test
	public void testGetSpecificDescriptorPattern() {
		SpecificDescriptorPattern specificDescriptorPattern;
		
		System.out.println("Iniciando pruebas para el método ContainsString()");
		
		System.out.println("Verificar que no contiene una referencia");
		specificDescriptorPattern = new SpecificDescriptorPattern(new CharacterDescriptor<Object>("Cuerpo", "Conformación", "Tiene cerata"), 1);
		assertNull(patterns.getSpecificDescriptorPattern(specificDescriptorPattern.getPattern().getAttribute()));
		
		System.out.println("Verificar que sí contiene una referencia");
		assertTrue(patterns.addPattern(specificDescriptorPattern));
		assertNotNull(patterns.getSpecificDescriptorPattern(specificDescriptorPattern.getPattern().getAttribute()));
	}

}
