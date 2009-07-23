package onthology.common;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DescriptorTest {
	private static Descriptor<Object> d1;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		d1 = new Descriptor<Object>("Estructura1", "Atributo1", "Estado1");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testCompareTo() {
		Descriptor<Object> d2;

		d2 = new Descriptor<Object>("Estructura1", "Atributo1", "Estado1");
		assertEquals(0, d1.compareTo(d2));
		d2 = new Descriptor<Object>("Estructura1", "Atributo2", "Estado1");
		assertEquals(-1, d1.compareTo(d2));
		d2 = new Descriptor<Object>("Estructura1", "Atributo0", "Estado1");
		assertEquals(1, d1.compareTo(d2));
	}

	@Test
	public final void testEquals() {
		Descriptor<Object> d2;
		
		assertFalse(d1.equals(null));
		d2 = new Descriptor<Object>("Estructura1", "Atributo1", "Estado1");
		assertTrue(d1.equals(d2));
		d2 = new Descriptor<Object>("Estructura1", "Atributo1", "Estado2");
		assertFalse(d1.equals(d2));
	}

}
