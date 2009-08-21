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

/**
 * @author Armando
 *
 */
public class SpecificDescriptorPatternTest {
	private static SpecificDescriptorPattern pattern;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Iniciando pruebas para la clase " + SpecificDescriptorPattern.class.getName());
		pattern = new SpecificDescriptorPattern(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3), 5);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Terminando pruebas para la clase " + SpecificDescriptorPattern.class.getName());
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
	 * Test method for {@link searchHintsBase.Elements.SpecificDescriptorPattern#compareTo(searchHintsBase.Elements.SpecificDescriptorPattern)}.
	 */
	@Test
	public void testCompareTo() {
		System.out.println("Iniciando pruebas para el método CompareTo()");
		
		System.out.println("Verificar que el patrón de descriptores es igual con otro patrón de descriptores" +
				"según el criterio de comparación");
		assertEquals(0, pattern.compareTo(new SpecificDescriptorPattern(new CharacterDescriptor<Object>
			("Cuerpo", "Forma", "Ovalado"), 5)));
		
		System.out.println("Verificar que el patrón de descriptores es distinto con otro patrón de descriptores" +
				"según el criterio de comparación");
		assertTrue(pattern.compareTo(new SpecificDescriptorPattern(new CharacterDescriptor<Object>
		("Cuerpo", "Forma", "Ovalado"), 6)) > 0);
		assertTrue(pattern.compareTo(new SpecificDescriptorPattern(new CharacterDescriptor<Object>
		("Cuerpo", "Forma", "Ovalado"), 3)) < 0);
	}

}
