/**
 * 
 */
package redundantDiscriminationNet.test;

import static org.junit.Assert.*;

import ontology.common.SSCharacterDescriptor;
import ontology.common.SVCharacterDescriptor;
import ontology.values.SingleValue;

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
public class NormTest {
	private static Norm norm;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Iniciando pruebas para la clase " + Norm.class.getName());
		norm = new Norm(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3)));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Terminando pruebas para la clase " + Norm.class.getName());
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
		norm.getSuccessors().clear();
	}

	/**
	 * Test method for {@link redundantDiscriminationNet.Norm#addSuccessor(redundantDiscriminationNet.Node)}.
	 */
	@Test
	public final void testAddSuccessor() {
		System.out.println("Iniciando pruebas para el método AddSuccessor()");
		
		System.out.println("Verificar que un sucesor válido se agregue");
		assertTrue(norm.addSuccessor(new Index("Forma")));
		assertTrue(norm.addSuccessor(new SheetCase(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3)))));
		
		System.out.println("Verificar que un índice duplicado no se agregue");
		assertFalse(norm.addSuccessor(new Index("Forma")));
		
		System.out.println("Verificar que un caso con un descriptor distinto al de la norma no se agregue");
		assertFalse(norm.addSuccessor(new SheetCase(new SSCharacterDescriptor("Pie", "Disposición", "Sobresale al manto"))));
		
		System.out.println("Verificar que una referencia null no se agregue");
		assertFalse(norm.addSuccessor(null));
	}

	/**
	 * Test method for {@link redundantDiscriminationNet.Norm#getPredecessor()}.
	 */
	@Test
	public final void testGetPredecessor() {
		Index index;
		
		System.out.println("Iniciando pruebas para el método GetPredecessor()");
		
		System.out.println("Verificar que se obtiene una referencia null");
		norm.setPredecessor(null);
		assertNull(norm.getPredecessor());
		
		System.out.println("Verificar que se obtiene una referencia no null");
		index = new Index("Longitud");
		norm.setPredecessor(index);
		assertNotNull(norm.getPredecessor());
		
		System.out.println("Verificar que se obtiene la referencia que en efecto es");
		assertSame(index, norm.getPredecessor());
	}

	/**
	 * Test method for {@link redundantDiscriminationNet.Norm#setPredecessor(redundantDiscriminationNet.Node)}.
	 */
	@Test
	public final void testSetPredecessor() {
		System.out.println("Iniciando pruebas para el método SetPredecessor()");
		
		System.out.println("Verificar que se inicializa a null");
		assertTrue(norm.setPredecessor(null));
		
		System.out.println("Verificar que no se agrege un predecesor incompatible");
		assertFalse(norm.setPredecessor(new Index("Forma")));
		
		System.out.println("Verificar que se agrege un predecesor compatible");
		assertTrue(norm.setPredecessor(new Index("Longitud")));
	}

	/**
	 * Test method for {@link redundantDiscriminationNet.Norm#getNearestPredecessorNorm()}.
	 */
	@Test
	public final void testGetNearestPredecessorNorm() {
		Index index;
		Norm norm2;
		
		System.out.println("Iniciando pruebas para el método GetNearestPredecessorNorm()");
		
		System.out.println("Verificar que se obtiene la norma predecesor más cercana null");
		norm.setPredecessor(null);
		assertNull(norm.getNearestPredecessorNorm());
		
		System.out.println("Verificar que se obtiene la norma predecesor más cercana no null");
		index = new Index("Longitud");
		norm2 = new Norm(new SVCharacterDescriptor("Branquias", "Número de hojas branquiales", new SingleValue(6)));
		index.setPredecessor(norm2);
		norm.setPredecessor(index);
		assertNotNull(norm.getNearestPredecessorNorm());
		
		System.out.println("Verificar que se obtiene la norma predecesor más cercana que en efecto es");
		assertSame(norm2, norm.getNearestPredecessorNorm());
	}

	/**
	 * Test method for {@link redundantDiscriminationNet.Norm#successorCases()}.
	 */
	@Test
	public final void testSuccessorCases() {
		System.out.println("Iniciando pruebas para el método SuccessorCases()");
		
		System.out.println("Verificar que se obtiene una lista vacía de casos");
		norm.addSuccessor(new Index("Forma"));
		assertTrue(norm.successorCases().size()==0);
		
		System.out.println("Verificar que se obtiene la lista de casos");
		norm.addSuccessor(new SheetCase(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3))));
		assertTrue(norm.successorCases().size()==1);		
	}

	/**
	 * Test method for {@link redundantDiscriminationNet.Norm#successorIndexes()}.
	 */
	@Test
	public final void testSuccessorIndexes() {
		System.out.println("Iniciando pruebas para el método SuccessorIndexes()");
		
		System.out.println("Verificar que se obtiene una lista vacía de índices");
		norm.addSuccessor(new SheetCase(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3))));
		assertTrue(norm.successorIndexes().size()==0);
		
		System.out.println("Verificar que se obtiene la lista de índices");
		norm.addSuccessor(new Index("Forma"));
		assertTrue(norm.successorIndexes().size()==1);
	}

	/**
	 * Test method for {@link redundantDiscriminationNet.Norm#getNearestSuccessorNorm(ontology.common.Descriptor)}.
	 */
	@Test
	public final void testGetNearestSuccessorNorm() {
		Index index;
		Norm norm2;
		
		System.out.println("Iniciando pruebas para el método GetNearestSuccessorNorm()");
		
		System.out.println("Verificar que se obtiene la norma sucesor más cercana null si no hay sucesores");
		assertNull(norm.getNearestSuccessorNorm(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3))));
		
		System.out.println("Verificar que se obtiene la norma sucesor más cercana no null");
		index = new Index("Longitud");
		norm2 = new Norm(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3)));
		index.addSuccessor(norm2);
		norm.addSuccessor(index);
		assertNotNull(norm.getNearestSuccessorNorm(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3))));
		
		System.out.println("Verificar que no se obtiene la norma sucesor más cercana si no existe");
		assertNull(norm.getNearestSuccessorNorm(new SVCharacterDescriptor("Branquias", "Número de hojas branquiales", new SingleValue(6))));
		
		System.out.println("Verificar que se obtiene la norma sucesor más cercana que en efecto es");
		assertSame(norm2, norm.getNearestSuccessorNorm(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3))));
	}

	/**
	 * Test method for {@link redundantDiscriminationNet.Norm#getSuccessorIndex(java.lang.String)}.
	 */
	@Test
	public final void testGetSuccessorIndexString() {
		Index index;
		
		System.out.println("Iniciando pruebas para el método GetSuccessorIndex(String)");
				
		System.out.println("Verificar que se obtiene un índice sucesor null");
		assertNull(norm.getSuccessorIndex("Cuerpo"));
		
		System.out.println("Verificar que se obtiene un índice sucesor no null");
		index = new Index("Longitud");
		norm.addSuccessor(index);
		assertNotNull(norm.getSuccessorIndex("Longitud"));
		
		System.out.println("Verificar que se obtiene el índice sucesor que en efecto es");
		assertSame(index, norm.getSuccessorIndex("Longitud"));
	}

	/**
	 * Test method for {@link redundantDiscriminationNet.Norm#getSuccessorIndex(java.lang.String, java.lang.Object)}.
	 */
	@Test
	public final void testGetSuccessorIndexDescriptor() {
		Index index;
		Norm norm2;
		
		System.out.println("Iniciando pruebas para el método GetSuccessorIndex(Descriptor)");
				
		System.out.println("Verificar que se obtiene un índice sucesor null");
		assertNull(norm.getSuccessorIndex(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3))));
		
		System.out.println("Verificar que se obtiene un índice sucesor no null");
		index = new Index("Longitud");
		norm2 = new Norm(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3)));
		index.addSuccessor(norm2);
		norm.addSuccessor(index);
		assertNotNull(norm.getSuccessorIndex(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3))));
		
		System.out.println("Verificar que se obtiene el índice sucesor que en efecto es");
		assertSame(index, norm.getSuccessorIndex(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3))));
	}
}
