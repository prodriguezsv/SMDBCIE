/**
 * 
 */
package redundantDiscriminationNet.auxiliary.test;

import static org.junit.Assert.*;

import ontology.common.Description;
import ontology.common.SSCharacterDescriptor;
import ontology.common.SVCharacterDescriptor;
import ontology.common.SingleValue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import redundantDiscriminationNet.auxiliary.ComparingTable;
import redundantDiscriminationNet.auxiliary.ComparingTableTuple;

/**
 * @author Armando
 *
 */
public class ComparingTableTest {
	private static ComparingTable act;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Iniciando pruebas para la clase " + ComparingTable.class.getName());
		act = new ComparingTable();
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Terminando pruebas para la clase " + ComparingTable.class.getName());
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
		act.clear();
	}

	/**
	 * Test method for {@link redundantDiscriminationNet.auxiliary.ComparingTable#fill(java.util.List, java.util.List)}.
	 */
	@Test
	public void testFill() {
		Description d1 = new Description();
		Description d2 = new Description();
		ComparingTable ct = new ComparingTable();
		ComparingTableTuple<Object> tuple;
		
		System.out.println("Iniciando pruebas para el método fill()");
		
		System.out.println("Verificar que ComparingTable se llene correctamente");
		d1.addDescriptors(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3)));
		d1.addDescriptors(new SSCharacterDescriptor("Pie", "Disposición", "Sobresale al manto"));
		d1.addDescriptors(new SSCharacterDescriptor("Cuerpo", "Conformación", "Tiene cerata"));
		d1.addDescriptors(new SVCharacterDescriptor("Branquias", "Número de hojas branquiales", new SingleValue(6)));
		
		d2.addDescriptors(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(1)));
		d2.addDescriptors(new SSCharacterDescriptor("Cuerpo", "Forma", "Alargado"));
	
		act.fill(d1, d2);
		
		tuple = new ComparingTableTuple<Object>("Longitud", new SingleValue(0.3), new SingleValue(1));		
		ct.add(tuple);
		tuple = new ComparingTableTuple<Object>("Disposición", "Sobresale al manto", null);		
		ct.add(tuple);
		tuple = new ComparingTableTuple<Object>("Conformación", "Tiene cerata", null);		
		ct.add(tuple);
		tuple = new ComparingTableTuple<Object>("Número de hojas branquiales", new SingleValue(6), null);		
		ct.add(tuple);
		tuple = new ComparingTableTuple<Object>("Forma", null, "Alargado");		
		ct.add(tuple);
		
		assertEquals(ct, act);
	}

	/**
	 * Test method for {@link redundantDiscriminationNet.auxiliary.ComparingTable#extractTuple()}.
	 */
	@Test
	public void testExtractTuple() {
		Description d1 = new Description();
		Description d2 = new Description();
		
		System.out.println("Iniciando pruebas para el método ExtractTuple()");
		
		System.out.println("Verificar que devuelve null cuando ComparingTable está vacía");
		assertNull(act.extractTuple());
		
		System.out.println("Verificar que devuelve el primer elemento de ComparingTable");
		d1.addDescriptors(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3)));
		
		d2.addDescriptors(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(1)));
		d2.addDescriptors(new SSCharacterDescriptor("Cuerpo", "Forma", "Alargado"));
	
		act.fill(d1, d2);
		
		assertEquals(new ComparingTableTuple<Object>("Longitud", new SingleValue(0.3), new SingleValue(1)), act.extractTuple());
	}
}
