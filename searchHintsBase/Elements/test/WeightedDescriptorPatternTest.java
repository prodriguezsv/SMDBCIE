/**
 * 
 */
package searchHintsBase.Elements.test;

import static org.junit.Assert.*;

import ontology.common.SSCharacterDescriptor;
import ontology.common.SVCharacterDescriptor;
import ontology.common.SingleValue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import searchHintsBase.Elements.WeightedDescriptorPattern;

/**
 * @author Armando
 *
 */
public class WeightedDescriptorPatternTest {
	private static WeightedDescriptorPattern pattern;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Iniciando pruebas para la clase " + WeightedDescriptorPattern.class.getName());
		pattern = new WeightedDescriptorPattern(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3)), 0.5, 1);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Terminando pruebas para la clase " + WeightedDescriptorPattern.class.getName());
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
	 * Test method for {@link searchHintsBase.Elements.WeightedDescriptorPattern#compareTo(searchHintsBase.Elements.WeightedDescriptorPattern)}.
	 */
	@Test
	public void testCompareTo() {
		System.out.println("Iniciando pruebas para el método CompareTo()");
		
		System.out.println("Verificar que el patrón de descriptores es igual con otro patrón de descriptores" +
				"según el criterio de comparación");
		assertEquals(0, pattern.compareTo(new WeightedDescriptorPattern(new SSCharacterDescriptor
			("Cuerpo", "Forma", "Ovalado"), 0.5, 1)));
		
		System.out.println("Verificar que el patrón de descriptores es distinto con otro patrón de descriptores" +
				"según el criterio de comparación");
		assertTrue(pattern.compareTo(new WeightedDescriptorPattern(new SSCharacterDescriptor
		("Cuerpo", "Forma", "Ovalado"), 0.75, 1)) > 0);
		assertTrue(pattern.compareTo(new WeightedDescriptorPattern(new SSCharacterDescriptor
		("Cuerpo", "Forma", "Ovalado"), 0.25, 1)) < 0);
	}

}
