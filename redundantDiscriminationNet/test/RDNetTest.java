/**
 * 
 */
package redundantDiscriminationNet.test;

import static org.junit.Assert.*;

import ontology.CBR.Case;
import ontology.common.Description;
import ontology.common.SSCharacterDescriptor;
import ontology.common.SVCharacterDescriptor;
import ontology.common.SingleValue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import redundantDiscriminationNet.RDNet;
import redundantDiscriminationNet.RootNorm;
import redundantDiscriminationNet.auxiliary.ComparingTable;
import redundantDiscriminationNet.auxiliary.ComparingTableTuple;

/**
 * @author Armando
 *
 */
public class RDNetTest extends RDNet {
	
	public RDNetTest() {
		super(new RootNorm(new SVCharacterDescriptor("Cuerpo", null, null)));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Iniciando pruebas para la clase " + RDNet.class.getName());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Terminando pruebas para la clase " + RDNet.class.getName());
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
	 * Test method for {@link redundantDiscriminationNet.RDNet#add(ontology.CBR.Case)}.
	 */
	@Test
	public void testAdd() {
		Case c1;
		
		System.out.println("Iniciando pruebas para el método add()");
		
		System.out.println("Verificar que se agrega un caso correctamente");
		c1 = new Case();
		c1.addToDescription(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3)));
		c1.addToDescription(new SSCharacterDescriptor("Cuerpo", "Forma", "Alargado"));
		this.add(c1);
		assertNotNull(this.getRoot().getSuccessorIndex(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3))));
		assertNotNull(this.getRoot().getSuccessorIndex(new SSCharacterDescriptor("Cuerpo", "Forma", "Alargado")));
		
		System.out.println("Verificar que se agrega un caso similar correctamente");
		c1 = new Case();
		c1.addToDescription(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3)));
		c1.addToDescription(new SSCharacterDescriptor("Cuerpo", "Forma", "Alargado"));
		this.add(c1);
		assertNotNull(this.getRoot().getSuccessorIndex(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3))));
		assertNotNull(this.getRoot().getNearestSuccessorNorm(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3))));
		assertNotNull(this.getRoot().getNearestSuccessorNorm(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3)))
				.getNearestSuccessorNorm(new SSCharacterDescriptor("Cuerpo", "Forma", "Alargado")));
		assertNotNull(this.getRoot().getNearestSuccessorNorm(new SSCharacterDescriptor("Cuerpo", "Forma", "Alargado")));
		
		c1 = new Case();
		c1.addToDescription(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3)));
		this.add(c1);
		assertNotNull(this.getRoot().getNearestSuccessorNorm(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3))));
		assertEquals(3, this.getRoot().getNearestSuccessorNorm(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3))).getNumCases());
		assertEquals(2, this.getRoot().getNearestSuccessorNorm(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3)))
				.getNearestSuccessorNorm(new SSCharacterDescriptor("Cuerpo", "Forma", "Alargado")).getNumCases());
		
		c1 = new Case();
		c1.addToDescription(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3)));
		c1.addToDescription(new SSCharacterDescriptor("Cuerpo", "Conformación", "Tiene cerata"));
		this.add(c1);
		assertNotNull(this.getRoot().getNearestSuccessorNorm(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3))));
		assertEquals(4, this.getRoot().getNearestSuccessorNorm(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3))).getNumCases());
		assertNotNull(this.getRoot().getSuccessorIndex(new SSCharacterDescriptor("Cuerpo", "Conformación", "Tiene cerata")));
		
		c1 = new Case();
		c1.addToDescription(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3)));
		c1.addToDescription(new SSCharacterDescriptor("Cuerpo", "Forma", "Alargado"));
		c1.addToDescription(new SSCharacterDescriptor("Cuerpo", "Conformación", "Tiene cerata"));
		this.add(c1);
		assertNotNull(this.getRoot().getNearestSuccessorNorm(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3))));
		assertEquals(5, this.getRoot().getNearestSuccessorNorm(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3))).getNumCases());
		assertNotNull(this.getRoot().getNearestSuccessorNorm(new SSCharacterDescriptor("Cuerpo", "Forma", "Alargado")));
		assertEquals(3, this.getRoot().getNearestSuccessorNorm(new SSCharacterDescriptor("Cuerpo", "Forma", "Alargado")).getNumCases());
		assertNotNull(this.getRoot().getNearestSuccessorNorm(new SSCharacterDescriptor("Cuerpo", "Conformación", "Tiene cerata")));
		assertEquals(2, this.getRoot().getNearestSuccessorNorm(new SSCharacterDescriptor("Cuerpo", "Conformación", "Tiene cerata")).getNumCases());
	}
	
	/**
	 * Test method for {@link redundantDiscriminationNet.RDNet#isCaseDescriptionUsedUp()}.
	 */
	@Test
	public void testIsCaseToInsertDescUsedUp() {
		Case c1;
		
		System.out.println("Iniciando pruebas para el método IsCaseToInsertDescUsedUp()");
		
		System.out.println("Verificar que la descripción del caso a insertar no ha sido utilizada por completo");
		c1 = new Case();
		c1.addToDescription(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3)));
		assertFalse(this.isCaseDescriptionUsedUp(c1));
		this.getRoute().push("Longitud");
		c1.addToDescription(new SSCharacterDescriptor("Cuerpo", "Forma", "Alargado"));
		assertFalse(this.isCaseDescriptionUsedUp(c1));
		
		System.out.println("Verificar que la descripción del caso a insertar ya ha sido utilizada por completo");
		this.getRoute().push("Forma");
		assertTrue(this.isCaseDescriptionUsedUp(c1));
	}

	/**
	 * Test method for {@link redundantDiscriminationNet.RDNet#moveDescElements(java.util.List, java.util.List)}.
	 */
	@Test
	public void testMoveDescElements() {
		Description d1, d2, d3;
		
		System.out.println("Iniciando pruebas para el método moveDescElements()");
		
		System.out.println("Verificar que no mueve los elementos si no se cumplen las precondiciones");
		d1 = new Description();
		d2 = new Description();
		assertFalse(this.moveDescElements(d1, d2));
		d2.addDescriptors(new SSCharacterDescriptor("Cuerpo", "Forma", "Alargado"));
		assertFalse(this.moveDescElements(d1, d2));
		
		System.out.println("Verificar que mueve los elementos si se cumplen las precondiciones");
		d1.addDescriptors(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3)));
		d1.addDescriptors(new SSCharacterDescriptor("Cuerpo", "Forma", "Alargado"));
		d2.clearAllDescriptors();
		d3 = new Description();
		d3.setDescriptors(d1.getDescriptors());
		assertTrue(this.moveDescElements(d1, d2));
		
		System.out.println("Verificar que en efecto movió los elementos");
		assertEquals(d2, d3);
	}

	/**
	 * Test method for {@link redundantDiscriminationNet.RDNet#removeMatchingElementsInTheRoute(redundantDiscriminationNet.auxiliary.ComparingTable)}.
	 */
	@Test
	public void testRemoveMatchingElementsInTheRouteComparingTable() {
		ComparingTable ct1, ct2;
		
		System.out.println("Iniciando pruebas para el método RemoveMatchingElementsInTheRoute(ComparingTable)");
		
		System.out.println("Verificar que devuelve tablas iguales cuando no existe una ruta");
		ct1 = new ComparingTable();
		ct1.add(new ComparingTableTuple<Object>("Longitud", 0.3, 1));
		ct1.add(new ComparingTableTuple<Object>("Forma", "Alargado", "Ovalado"));
		assertEquals(ct1, removeMatchingElementsInTheRoute(ct1));
		
		System.out.println("Verificar que remueve los elementos apropiados si existe una ruta");
		this.getRoute().push("Longitud");
		ct2 = new ComparingTable();
		ct2.add(new ComparingTableTuple<Object>("Forma", "Alargado", "Ovalado"));
		assertEquals(ct2, removeMatchingElementsInTheRoute(ct1));
	}

	/**
	 * Test method for {@link redundantDiscriminationNet.RDNet#removeMatchingElementsInTheRoute(java.util.List)}.
	 */
	@Test
	public void testRemoveMatchingElementsInTheRouteListOfDescriptorOfObject() {
		Description d1, d2;
		
		System.out.println("Iniciando pruebas para el método RemoveMatchingElementsInTheRoute(List)");
		
		System.out.println("Verificar que devuelve el mismo objeto cuando no existe una ruta");
		d1 = new Description();
		d1.addDescriptors(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3)));
		d1.addDescriptors(new SSCharacterDescriptor("Cuerpo", "Forma", "Alargado"));
		assertEquals(d1, removeMatchingElementsInTheRoute(d1));
		
		System.out.println("Verificar que remueve los descriptores apropiados si existe una ruta");
		this.getRoute().push("Longitud");
		d2 = new Description();
		d2.addDescriptors(new SSCharacterDescriptor("Cuerpo", "Forma", "Alargado"));
		assertEquals(d2, removeMatchingElementsInTheRoute(d1));
	}

	/**
	 * Test method for {@link redundantDiscriminationNet.RDNet#areDescriptionsEqual(ontology.CBR.Case, ontology.CBR.Case)}.
	 */
	@Test
	public void testAreDescriptionsEqual() {
		Case c1, c2;
		
		System.out.println("Iniciando pruebas para el método AreDescriptionsEqual()");
		
		System.out.println("Verificar dos casos con descripciones distintas");
		c1 = new Case();
		c1.addToDescription(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3)));
		c2 = new Case();
		c2.addToDescription(new SSCharacterDescriptor("Cuerpo", "Forma", "Alargado"));
		assertFalse(c1.getDescription(this.getRoot().getStructure()).equals(c2.getDescription(this.getRoot().getStructure())));
		c2.addToDescription(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3)));
		assertFalse(c1.getDescription(this.getRoot().getStructure()).equals(c2.getDescription(this.getRoot().getStructure())));
		
		System.out.println("Verificar dos casos con descripciones iguales");
		c2 = new Case();
		c2.addToDescription(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3)));
		assertTrue(c1.getDescription(this.getRoot().getStructure()).equals(c2.getDescription(this.getRoot().getStructure())));
	}

}
