package redundantDiscriminationNet.auxiliary.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import redundantDiscriminationNet.auxiliary.ComparingTableTuple;

public class ComparingTableTupleTest {
	private static ComparingTableTuple<Object> ctt;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Iniciando pruebas para la clase " + ComparingTableTuple.class.getName());
		ctt = new ComparingTableTuple<Object>("Longitud", 0.3, 1);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Terminando pruebas para la clase " + ComparingTableTuple.class.getName());
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEquals() {
		ComparingTableTuple<Object> ctt2;
		
		System.out.println("Verificar que una instancia de ComparingTableTuple no null no es igual a null");
		assertFalse(ctt.equals(null));
		
		System.out.println("Verificar la igualdad de dos instancias con los mismos valores");
		ctt2 = new ComparingTableTuple<Object>("Longitud", 0.3, 1);
		assertTrue(ctt.equals(ctt2));
		
		System.out.println("Verificar la desigualdad de dos instancias con distintos valores");
		ctt2 = new ComparingTableTuple<Object>("Disposición", "Sobresale al manto", null);
		assertFalse(ctt.equals(ctt2));
	}
}
