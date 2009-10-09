package ontology.common.test;

import static org.junit.Assert.*;

import ontology.common.Descriptor;
import ontology.common.MeasuringUnit;
import ontology.common.RVCharacterDescriptor;
import ontology.common.RangeValue;
import ontology.common.SSCharacterDescriptor;
import ontology.common.SVCharacterDescriptor;
import ontology.common.SingleValue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DescriptorTest {
	private static Descriptor d;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		d = new SSCharacterDescriptor("Cuerpo", "Conformación", "Tiene cerata");
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
		Descriptor d2;

		d2 = new SSCharacterDescriptor("Cuerpo", "Conformación", "Tiene cerata");
		assertEquals(0, d.compareTo(d2));
		d2 = new SSCharacterDescriptor("Cuerpo", "Forma", "Alargado");
		assertTrue(d.compareTo(d2)<0);
		d2 = new SVCharacterDescriptor("Branquias", "Número de hojas branquiales", new SingleValue(6));
		assertTrue(d.compareTo(d2)>0);
	}

	@Test
	public final void testEquals() {
		Descriptor d2,d3,d4;

        SingleValue aSingleValue = new SingleValue(100, MeasuringUnit.CM.getMeasuringUnit());
        RangeValue aRangeValue = new RangeValue(0.0, 100.0, MeasuringUnit.MM.getMeasuringUnit());

                
		d2 = new SSCharacterDescriptor("Cuerpo", "Conformación", "Tiene cerata");
		d3 = new SVCharacterDescriptor("Cuerpo", "longitud",aSingleValue);
		d4 = new RVCharacterDescriptor("Cuerpo", "tamaño cola", aRangeValue);

		assertTrue(d.equals(d2));
		assertTrue(d3.equals(new SVCharacterDescriptor("Cuerpo", "longitud",aSingleValue)));
		assertTrue(d4.equals(new RVCharacterDescriptor("Cuerpo", "tamaño cola", aRangeValue)));

                assertFalse(d.equals(null));
		assertFalse(d.equals(d3));
		assertFalse(d.equals(d4));
	}

}
