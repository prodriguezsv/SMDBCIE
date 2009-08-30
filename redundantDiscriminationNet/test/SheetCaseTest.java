/**
 * 
 */
package redundantDiscriminationNet.test;

import static org.junit.Assert.*;

import ontology.CBR.Case;
import ontology.common.Description;
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
public class SheetCaseTest {
	private static SheetCase sheetcase;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Iniciando pruebas para la clase " + SheetCase.class.getName());
		sheetcase = new SheetCase(new SSCharacterDescriptor("Pie", "Disposición", "Sobresale al manto"));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Terminando pruebas para la clase " + SheetCase.class.getName());
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
	 * Test method for {@link redundantDiscriminationNet.SheetCase#addPredecessor(redundantDiscriminationNet.Node)}.
	 */
	@Test
	public void testAddPredecessor() {
		Norm norm;
		Index index;
		
		System.out.println("Iniciando pruebas para el método AddPredecessor()");
		
		System.out.println("Verificar que un predecesor válido se agregue");
		norm = new Norm(new SSCharacterDescriptor("Pie", "Disposición", "Sobresale al manto"));
		index = new Index("Disposición");
		assertTrue(sheetcase.addPredecessor(norm));
		assertTrue(sheetcase.addPredecessor(index));
		
		System.out.println("Verificar que un norma con descriptor duplicado no se agregue");
		assertFalse(sheetcase.addPredecessor(norm));
		
		System.out.println("Verificar que un índice incompatible no se agregue");
		assertFalse(sheetcase.addPredecessor(index));
		
		System.out.println("Verificar que una referencia null no se agregue");
		assertFalse(sheetcase.addPredecessor(null));
	}

	/**
	 * Test method for {@link redundantDiscriminationNet.SheetCase#addSuccessor(redundantDiscriminationNet.Node)}.
	 */
	@Test
	public void testAddSuccessor() {
		System.out.println("Iniciando pruebas para el método AddSuccessor()");
		
		System.out.println("Verificar que no es posbible agregar un sucesor");
		assertFalse(sheetcase.addSuccessor(new Index("Disposición")));
	}

	/**
	 * Test method for {@link redundantDiscriminationNet.SheetCase#setCase(ontology.CBR.Case)}.
	 */
	@Test
	public void testSetCase() {
		Case aCase;
		Description dl;
		
		System.out.println("Iniciando pruebas para el método testSetCase()");
		
		System.out.println("Verificar que un caso incompatible no se embebe");
		dl = new Description();
		dl.addToConcreteDescription(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3)));
		aCase = new Case(dl);
		assertFalse(sheetcase.setCase(aCase));
		
		System.out.println("Verificar que un caso compatible se embebe");
		dl = new Description();
		dl.addToConcreteDescription(new SSCharacterDescriptor("Pie", "Disposición", "Sobresale al manto"));
		aCase = new Case(dl);
		assertTrue(sheetcase.setCase(aCase));
		
	}

}
