/**
 * 
 */
package redundantDiscriminationNet.test;

import static org.junit.Assert.*;
import ontology.common.SVCharacterDescriptor;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import redundantDiscriminationNet.RDMultiNetRoot;
import redundantDiscriminationNet.RootNorm;

/**
 * @author Armando
 *
 */
public class RDMultiNetRootTest {
	private static RDMultiNetRoot rdmultinetroot;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Iniciando pruebas para la clase " + RDMultiNetRoot.class.getName());
		rdmultinetroot = new RDMultiNetRoot(); 
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Terminando pruebas para la clase " + RDMultiNetRoot.class.getName());
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
		rdmultinetroot.getNets().clear();
	}

	/**
	 * Test method for {@link redundantDiscriminationNet.RDMultiNetRoot#getRDNet(java.lang.String)}.
	 */
	@Test
	public void testGetRDNet() {
		System.out.println("Iniciando pruebas para el método GetRDNet()");
		
		System.out.println("Verificar que se obtiene null para una nueva estructura");
		assertNull(rdmultinetroot.getRDNet("Pie"));
		
		System.out.println("Verificar que se obtiene no null para una estructura");
		rdmultinetroot.addRDNet(new RootNorm(new SVCharacterDescriptor("Pie", null, null)));
		assertNotNull(rdmultinetroot.getRDNet("Pie"));
		
		System.out.println("Verificar que se obtiene null para una nueva estructura");
		assertNull(rdmultinetroot.getRDNet("Cuerpo"));
	}

	/**
	 * Test method for {@link redundantDiscriminationNet.RDMultiNetRoot#addRDNet(java.lang.String)}.
	 */
	@Test
	public void testAddRDNet() {
		System.out.println("Iniciando pruebas para el método AddRDNet()");
		
		System.out.println("Verificar que se agrega una red para una nueva estructura");
		assertTrue(rdmultinetroot.addRDNet(new RootNorm(new SVCharacterDescriptor("Pie", null, null))));
		
		System.out.println("Verificar que no se agrega una red para una estructura duplicada");
		assertFalse(rdmultinetroot.addRDNet(new RootNorm(new SVCharacterDescriptor("Pie", null, null))));
	}

	/**
	 * Test method for {@link redundantDiscriminationNet.RDMultiNetRoot#contains(java.lang.String)}.
	 */
	@Test
	public void testContains() {
		System.out.println("Iniciando pruebas para el método Contains()");
		
		System.out.println("Verificar que contiene una red para una estructura dada");
		rdmultinetroot.addRDNet(new RootNorm(new SVCharacterDescriptor("Pie", null, null)));
		assertTrue(rdmultinetroot.contains("Pie"));
		
		System.out.println("Verificar que no contiene una red para una estructura dada");
		assertFalse(rdmultinetroot.contains("Cuerpo"));
	}

}
