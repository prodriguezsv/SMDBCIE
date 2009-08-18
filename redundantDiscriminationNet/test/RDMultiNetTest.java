/**
 * 
 */
package redundantDiscriminationNet.test;

import static org.junit.Assert.*;

import ontology.CBR.Case;
import ontology.common.CharacterDescriptor;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import redundantDiscriminationNet.RDMultiNet;
import redundantDiscriminationNet.RDMultiNetRoot;

/**
 * @author Armando
 *
 */
public class RDMultiNetTest {
	private static RDMultiNet rdmultinet; 

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Iniciando pruebas para la clase " + RDMultiNetRoot.class.getName());
		rdmultinet = new RDMultiNet();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Teminando pruebas para la clase " + RDMultiNetRoot.class.getName());
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
	 * Test method for {@link redundantDiscriminationNet.RDMultiNet#add(ontology.CBR.Case)}.
	 */
	@Test
	public void testAdd() {
		Case c1;
		
		System.out.println("Iniciando pruebas para el método add()");
		
		System.out.println("Verificar que se agrega un caso correctamente");
		c1 = new Case();
		c1.addToDescription(new CharacterDescriptor<Object>("Cuerpo", "Coloración", "Rosado palido y crema"));
		c1.addToDescription(new CharacterDescriptor<Object>("Branquia", "Coloración", "Rosado palido y crema"));
		c1.addToDescription(new CharacterDescriptor<Object>("Branquia", "Forma hojas branquiales", "Tripinnada"));
		c1.addToDescription(new CharacterDescriptor<Object>("Manto", "Forma del borde", "Ondulado"));
		c1.addToDescription(new CharacterDescriptor<Object>("Manto", "Textura", "Lisa"));
		c1.addToDescription(new CharacterDescriptor<Object>("Rinoforos", "Coloración", "Rosado palido y crema"));
		rdmultinet.add(c1);
		assertEquals(4, rdmultinet.getRoot().getNets().size());
		c1 = new Case();
		c1.addToDescription(new CharacterDescriptor<Object>("Cuerpo", "Coloración", "Blanquecino"));
		c1.addToDescription(new CharacterDescriptor<Object>("Cuerpo", "Forma", "Ovalado"));
		c1.addToDescription(new CharacterDescriptor<Object>("Manto", "Coloración del borde", "Amarillo"));
		c1.addToDescription(new CharacterDescriptor<Object>("Rinoforos", "Coloración de los apíces", "Rojo"));
		rdmultinet.add(c1);
		assertEquals(4, rdmultinet.getRoot().getNets().size());		
	}

}
