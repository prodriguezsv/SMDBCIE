package ontology.common.test;

import static org.junit.Assert.*;

import ontology.common.CharacterDescriptor;
import ontology.common.Descriptor;

import ontology.values.MeasuringUnit;
import ontology.values.RangeValue;
import ontology.values.SingleValue;
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
		Descriptor<Object> d2,d3,d4;

                SingleValue<Double> aSingleValue = new SingleValue<Double>();
                aSingleValue.setValue(100.0);
                aSingleValue.setMeasuringUnit(MeasuringUnit.CM);

                RangeValue aRangeValue = new RangeValue();

                aRangeValue.setLowerBound(0.0);
                aRangeValue.setUpperBound(100.0);
                aRangeValue.setMeasuringUnit(MeasuringUnit.MM);
                
		d2 = new CharacterDescriptor<Object>("Cuerpo", "Conformación", "Tiene cerata");
		d3 = new CharacterDescriptor<Object>("Cuerpo", "longitud",aSingleValue);
		d4 = new CharacterDescriptor<Object>("Cuerpo", "tamaño cola", aRangeValue);

		assertTrue(d.equals(d2));
		assertTrue(d3.equals(new CharacterDescriptor<Object>("Cuerpo", "longitud",aSingleValue)));
		assertTrue(d4.equals(new CharacterDescriptor<Object>("Cuerpo", "tamaño cola", aRangeValue)));

                assertFalse(d.equals(null));
		assertFalse(d.equals(d3));
		assertFalse(d.equals(d4));
	}

}
