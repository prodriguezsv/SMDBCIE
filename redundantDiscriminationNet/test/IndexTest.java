/**
 * 
 */
package redundantDiscriminationNet.test;

import static org.junit.Assert.*;


import ontology.common.CharacterDescriptor;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import redundantDiscriminationNet.Index;
import redundantDiscriminationNet.Norm;
import redundantDiscriminationNet.SheetCase;

/**
 * @author Armando
 *
 */
public class IndexTest {
	private static Index index;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Iniciando pruebas para la clase " + Index.class.getName());
		index = new Index("Longitud");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Terminando pruebas para la clase " + Index.class.getName());
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
		index.getSuccessors().clear();
	}

	/**
	 * Test method for {@link redundantDiscriminationNet.Index#addSuccessor(redundantDiscriminationNet.Node)}.
	 */
	@Test
	public final void testAddSuccessor() {
		System.out.println("Iniciando pruebas para el método AddSuccessor()");
		
		System.out.println("Verificar que un sucesor válido se agregue");
		assertTrue(index.addSuccessor(new Norm(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3))));
		assertTrue(index.addSuccessor(new SheetCase(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 1))));
		
		System.out.println("Verificar que un norma con descriptor duplicado no se agregue");
		assertFalse(index.addSuccessor(new Norm(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3))));
		
		System.out.println("Verificar que un caso con descriptor duplicado no se agregue");
		assertFalse(index.addSuccessor(new SheetCase(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 1))));
		
		System.out.println("Verificar que un caso incompatible con el índice no se agregue");
		assertFalse(index.addSuccessor(new SheetCase(new CharacterDescriptor<Object>("Pie", "Disposición", "Sobresale al manto"))));
		
		System.out.println("Verificar que una referencia null no se agregue");
		assertFalse(index.addSuccessor(null));
	}

	/**
	 * Test method for {@link redundantDiscriminationNet.Index#getPredecessor()}.
	 */
	@Test
	public final void testGetPredecessor() {
		Norm norm;
		
		System.out.println("Iniciando pruebas para el método GetPredecessor()");
		
		System.out.println("Verificar que se obtiene una referencia null");
		index.setPredecessor(null);
		assertNull(index.getPredecessor());
		
		System.out.println("Verificar que se obtiene una referencia no null");
		norm = new Norm(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3));
		index.setPredecessor(norm);
		assertNotNull(index.getPredecessor());
		
		System.out.println("Verificar que se obtiene la referencia que en efecto es");
		assertSame(norm, index.getPredecessor());
	}

	/**
	 * Test method for {@link redundantDiscriminationNet.Index#setPredecessor(redundantDiscriminationNet.Norm)}.
	 */
	@Test
	public final void testSetPredecessor() {
		Norm norm;
		
		System.out.println("Iniciando pruebas para el método SetPredecessor()");
		
		System.out.println("Verificar que se inicializa a null");
		assertTrue(index.setPredecessor(null));
		
		System.out.println("Verificar que no se agrege un predecesor incompatible");
		norm = new Norm(new CharacterDescriptor<Object>("Cuerpo", "Conformación", "Tiene cerata"));
		norm.addSuccessor(new Index("Longitud"));
		assertFalse(index.setPredecessor(norm));
		
		System.out.println("Verificar que se agrege un predecesor compatible");
		norm = new Norm(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3));
		assertTrue(index.setPredecessor(norm));
	}

	/**
	 * Test method for {@link redundantDiscriminationNet.Index#getSuccessor(ontology.common.Descriptor)}.
	 */
	@Test
	public final void testGetSuccessor() {
		SheetCase sc;
		Norm norm;
		
		System.out.println("Iniciando pruebas para el método GetSuccessor()");
		
		System.out.println("Verificar que se obtiene el sucesor que es efecto es");
		sc = new SheetCase(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 1));
		norm = new Norm(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3));
		index.addSuccessor(norm);
		index.addSuccessor(sc);
		
		assertSame(sc, index.getSuccessor(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 1)));
		assertSame(norm, index.getSuccessor(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3)));
	}

	/**
	 * Test method for {@link redundantDiscriminationNet.Index#getSuccessorNorm(ontology.common.Descriptor)}.
	 */
	@Test
	public final void testGetSuccessorNorm() {
		Norm norm;
		
		System.out.println("Iniciando pruebas para el método GetSuccessor()");
		
		System.out.println("Verificar que se obtiene el sucesor que es efecto es");
		norm = new Norm(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3));
		index.addSuccessor(norm);
		index.addSuccessor(new SheetCase(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 1)));
		
		assertSame(norm, index.getSuccessorNorm(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3)));
	}
}
