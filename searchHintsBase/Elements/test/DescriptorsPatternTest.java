/**
 * 
 */
package searchHintsBase.Elements.test;

import static org.junit.Assert.*;

import ontology.common.Description;
import ontology.common.Descriptor;
import ontology.common.SSCharacterDescriptor;
import ontology.common.SSHeuristicDescriptor;
import ontology.common.SVCharacterDescriptor;
import ontology.common.SingleValue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import searchHintsBase.Elements.DescriptorsPattern;

/**
 * @author Armando
 *
 */
public class DescriptorsPatternTest {
	private static DescriptorsPattern descriptorsPattern;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Iniciando pruebas para la clase " + DescriptorsPattern.class.getName());
		descriptorsPattern = new DescriptorsPattern();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Terminando pruebas para la clase " + DescriptorsPattern.class.getName());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		descriptorsPattern.addDescriptor(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3)));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		descriptorsPattern.getPattern().clearAllDescriptors();
	}

	/**
	 * Test method for {@link searchHintsBase.elements.DescriptorsPattern#addDescriptor(ontology.common.Descriptor)}.
	 */
	@Test
	public void testAddDescriptor() {
		Descriptor aDescriptor;
		
		System.out.println("Iniciando pruebas para el método AddDescriptor()");
		
		System.out.println("Verificar que no se agregue una referencia a null");
		assertFalse(descriptorsPattern.addDescriptor(null));
		
		System.out.println("Verificar que no haya contradicciones en la descripción o duplicados");
		assertFalse(descriptorsPattern.addDescriptor(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3))));
		assertFalse(descriptorsPattern.addDescriptor(new SSCharacterDescriptor("Pie", "Disposición", "Sobresale al manto")));
		assertFalse(descriptorsPattern.addDescriptor(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(3))));
		assertFalse(descriptorsPattern.addDescriptor(new SSHeuristicDescriptor("Alimentación", "Alimentación", "Carnivoro")));
		
		System.out.println("Verificar que se agregue un descriptor válido");
		aDescriptor = new SSCharacterDescriptor("Cuerpo", "Conformación", "Tiene cerata");
		assertTrue(descriptorsPattern.addDescriptor(aDescriptor));
	}

	/**
	 * Test method for {@link searchHintsBase.elements.DescriptorsPattern#howSimilarTo(java.util.List)}.
	 */
	@Test
	public void testHowSimilarTo() {
		Description dl;
		
		System.out.println("Iniciando pruebas para el método HowSimilarTo()");
		
		System.out.println("Verificar que el patrón es igual a una lista de descriptores");
		dl = new Description();
		dl.addToConcreteDescription(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3)));
		assertEquals(1, descriptorsPattern.howSimilarTo(dl));
		
		System.out.println("Verificar que el patrón es completamente distinto a una lista de descriptores");
		dl.clearAllDescriptors();
		dl.addToConcreteDescription(new SSCharacterDescriptor("Cuerpo", "Conformación", "Tiene cerata"));
		assertEquals(0, descriptorsPattern.howSimilarTo(dl));
		
		System.out.println("Verificar que el patrón es medianamente distinto a una lista de descriptores");
		dl.clearAllDescriptors();
		dl.addToConcreteDescription(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3)));
		dl.addToConcreteDescription(new SSCharacterDescriptor("Cuerpo", "Conformación", "Tiene cerata"));
		assertEquals(0.5, descriptorsPattern.howSimilarTo(dl));
	}
	
	/**
	 * Test method for {@link searchHintsBase.elements.DescriptorsPattern#compareTo(searchHintsBase.elements.DescriptorsPattern)}.
	 */
	@Test
	public void testCompareTo() {
		Description dl;
		DescriptorsPattern dp;
		
		System.out.println("Iniciando pruebas para el método CompareTo()");
		
		System.out.println("Verificar que el patrón de descriptores es igual con otro patrón de descriptores" +
				"según el criterio de comparación");
		dl = new Description();
		dl.addToConcreteDescription(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3)));
		dp = new DescriptorsPattern(dl, 0, 1);
		assertEquals(0, descriptorsPattern.compareTo(dp));
		
		System.out.println("Verificar que el patrón de descriptores es distinto con otro patrón de descriptores" +
				"según el criterio de comparación");
		dl.clearAllDescriptors();
		assertTrue(descriptorsPattern.compareTo(dp) < 0);
	
		dl.addToConcreteDescription(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3)));
		dl.addToConcreteDescription(new SSCharacterDescriptor("Cuerpo", "Conformación", "Tiene cerata"));
		assertTrue(descriptorsPattern.compareTo(dp) > 0);
	}
}
