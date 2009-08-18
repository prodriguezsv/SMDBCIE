package ontology.common.test;

import static org.junit.Assert.*;

import ontology.common.CharacterDescriptor;
import ontology.common.Descriptor;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DescriptorTest {
	private static Descriptor<Object> d;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		d = new CharacterDescriptor<Object>("Cuerpo", "Conformación", "Tiene cerata");
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

		d2 = new CharacterDescriptor<Object>("Cuerpo", "Conformación", "Tiene cerata");
		assertEquals(0, d.compareTo(d2));
		d2 = new CharacterDescriptor<Object>("Cuerpo", "Forma", "Alargado");
		assertTrue(d.compareTo(d2)<0);
		d2 = new CharacterDescriptor<Object>("Branquias", "Número de hojas branquiales", 6);
		assertTrue(d.compareTo(d2)>0);
	}

	@Test
	public final void testEquals() {
		Descriptor<Object> d2;
		
		assertFalse(d.equals(null));
		d2 = new CharacterDescriptor<Object>("Cuerpo", "Conformación", "Tiene cerata");
		assertTrue(d.equals(d2));
		d2 = new CharacterDescriptor<Object>("Pie", "Disposición", "Sobresale al manto");
		assertFalse(d.equals(d2));
	}

}
