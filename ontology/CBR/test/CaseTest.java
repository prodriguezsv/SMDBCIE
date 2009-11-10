/**
 * 
 */
package ontology.CBR.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ontology.CBR.Case;
import ontology.common.Description;
import ontology.common.Descriptor;
import ontology.common.SSCharacterDescriptor;
import ontology.common.SVCharacterDescriptor;
import ontology.common.SingleValue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Armando
 *
 */
public class CaseTest {
	private static Case aCase;
	
	/**
	 * 
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Iniciando pruebas para la clase " + Case.class.getName());
		aCase = new Case();
	}

	/**
	 * 
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Terminando pruebas para la clase " + Case.class.getName());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Descriptor aDescriptor;
		
		aDescriptor = new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3));
		aCase.addToDescription(aDescriptor);
		aDescriptor = new SSCharacterDescriptor("Pie", "Disposición", "Sobresale al manto");
		aCase.addToDescription(aDescriptor);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		aCase.getProblem().getDescription().clearAllDescriptors();
	}

	/**
	 * Test method for {@link ontology.CBR.Case#addToDescription(ontology.common.Descriptor)}.
	 */
	@Test
	public final void testAddToDescription() {
		Descriptor aDescriptor;
		
		System.out.println("Iniciando pruebas para el método AddToDescription()");
		
		System.out.println("Verificar que no se agregue una referencia a null");
		assertFalse(aCase.addToDescription(null));
		
		System.out.println("Verificar que no haya contradicciones en la descripción o duplicados");
		assertFalse(aCase.addToDescription(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3))));
		assertFalse(aCase.addToDescription(new SSCharacterDescriptor("Pie", "Disposición", "Sobresale al manto")));
		assertFalse(aCase.addToDescription(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(1))));
		
		System.out.println("Verificar que se agregue un descriptor válido");
		aDescriptor = new SSCharacterDescriptor("Cuerpo", "Conformación", "Tiene cerata");
		assertTrue(aCase.addToDescription(aDescriptor));
		aDescriptor = new SVCharacterDescriptor("Branquias", "Número de hojas branquiales", new SingleValue(6));
		assertTrue(aCase.addToDescription(aDescriptor));
	}

	/**
	 * Test method for {@link ontology.CBR.Case#addToJustification(ontology.common.Descriptor)}.
	 */
	@Test
	public final void testAddToJustification() {
		Descriptor aDescriptor;
		
		System.out.println("Iniciando pruebas para el método AddToJustification()");
		
		System.out.println("Verificar que no se agregue un referencia a null");
		assertFalse(aCase.addToDescription(null));
		
		System.out.println("Verificar que no haya contradicciones en la descripción o duplicados");
		assertFalse(aCase.addToDescription(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3))));
		assertFalse(aCase.addToDescription(new SSCharacterDescriptor("Pie", "Disposición", "Sobresale al manto")));
		assertFalse(aCase.addToDescription(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(1))));
		
		System.out.println("Verificar que se agregue un descriptor correctamente");
		aDescriptor = new SSCharacterDescriptor("Cuerpo", "Conformación", "Tiene cerata");
		assertTrue(aCase.addToDescription(aDescriptor));
		aDescriptor = new SVCharacterDescriptor("Branquias", "Número de hojas branquiales", new SingleValue(6));
		assertTrue(aCase.addToDescription(aDescriptor));
	}

	/**
	 * Test method for {@link ontology.CBR.Case#getCharacterStructuresList()}.
	 */
	@Test
	public final void testGetStructuresList() {
		List<String> sl;
		
		System.out.println("Iniciando pruebas para el método GetStructuresList()");
		
		System.out.println("Verificar que se obtenga una lista de nombres de estructura");
		sl = new ArrayList<String>();
		sl.addAll(Arrays.asList("Cuerpo", "Pie"));
		
		assertEquals(sl, aCase.getCharacterStructuresList());
	}

	/**
	 * Test method for {@link ontology.CBR.Case#getDescription(java.lang.String)}.
	 */
	@Test
	public final void testGetDescription() {
		Description dl = new Description();
		
		System.out.println("Iniciando pruebas para el método GetDescription()");
		
		System.out.println("Verificar que se obtenga una descripción asociada a una estructura");
		dl.addToConcreteDescription(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3)));
		assertEquals(dl, aCase.getDescription("Cuerpo"));
	}
}
