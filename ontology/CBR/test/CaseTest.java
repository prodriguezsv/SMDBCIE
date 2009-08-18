/**
 * 
 */
package ontology.CBR.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ontology.CBR.Case;
import ontology.common.CharacterDescriptor;
import ontology.common.Descriptor;

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
		Descriptor<Object> aDescriptor;
		
		aDescriptor = new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3);
		aCase.addToDescription(aDescriptor);
		aDescriptor = new CharacterDescriptor<Object>("Pie", "Disposición", "Sobresale al manto");
		aCase.addToDescription(aDescriptor);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		aCase.getProblem().getDescription().clear();
	}

	/**
	 * Test method for {@link ontology.CBR.Case#addToDescription(ontology.common.Descriptor)}.
	 */
	@Test
	public final void testAddToDescription() {
		Descriptor<Object> aDescriptor;
		
		System.out.println("Iniciando pruebas para el método AddToDescription()");
		
		System.out.println("Verificar que no se agregue una referencia a null");
		assertFalse(aCase.addToDescription(null));
		
		System.out.println("Verificar que no haya contradicciones en la descripción o duplicados");
		assertFalse(aCase.addToDescription(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3)));
		assertFalse(aCase.addToDescription(new CharacterDescriptor<Object>("Pie", "Disposición", "Sobresale al manto")));
		assertFalse(aCase.addToDescription(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 1)));
		
		System.out.println("Verificar que se agregue un descriptor válido");
		aDescriptor = new CharacterDescriptor<Object>("Cuerpo", "Conformación", "Tiene cerata");
		assertTrue(aCase.addToDescription(aDescriptor));
		aDescriptor = new CharacterDescriptor<Object>("Branquias", "Número de hojas branquiales", 6);
		assertTrue(aCase.addToDescription(aDescriptor));
	}

	/**
	 * Test method for {@link ontology.CBR.Case#addToJustification(ontology.common.Descriptor)}.
	 */
	@Test
	public final void testAddToJustification() {
		Descriptor<Object> aDescriptor;
		
		System.out.println("Iniciando pruebas para el método AddToJustification()");
		
		System.out.println("Verificar que no se agregue un referencia a null");
		assertFalse(aCase.addToDescription(null));
		
		System.out.println("Verificar que no haya contradicciones en la descripción o duplicados");
		assertFalse(aCase.addToDescription(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3)));
		assertFalse(aCase.addToDescription(new CharacterDescriptor<Object>("Pie", "Disposición", "Sobresale al manto")));
		assertFalse(aCase.addToDescription(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 1)));
		
		System.out.println("Verificar que se agregue un descriptor correctamente");
		aDescriptor = new CharacterDescriptor<Object>("Cuerpo", "Conformación", "Tiene cerata");
		assertTrue(aCase.addToDescription(aDescriptor));
		aDescriptor = new CharacterDescriptor<Object>("Branquias", "Número de hojas branquiales", 6);
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
		List<Descriptor<Object>> dl = new ArrayList<Descriptor<Object>>();
		
		System.out.println("Iniciando pruebas para el método GetDescription()");
		
		System.out.println("Verificar que se obtenga una descripción asociada a una estructura");
		dl.add(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3));
		assertEquals(dl, aCase.getDescription("Cuerpo"));
	}
}
