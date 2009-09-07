/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ontology.values.test;

import ontology.values.RangeValue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pabloq
 */
public class RangeValueTest {

    public RangeValueTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

//    /**
//     * Test of setMeasuringUnit method, of class RangeValue.
//     */
//    @Test
//    public void testSetMeasuringUnit() {
//        System.out.println("setMeasuringUnit");
//        MeasuringUnit a = MeasuringUnit.CM;
//        MeasuringUnit b = MeasuringUnit.INCH;
//        RangeValue aRangeDescriptor = new RangeValue();
//        aRangeDescriptor.setMeasuringUnit(a);
//        assertEquals(a,aRangeDescriptor.getMeasuringUnit());
//        /*
//         * keep the same mesure unit.
//         *
//         */
//        aRangeDescriptor.setMeasuringUnit(b);
//        assertEquals(a,aRangeDescriptor.getMeasuringUnit());
//
//
//    }


    /**
     * Test of isRangeWithin method, of class RangeValue.
     */
    @Test
    public void testIsRangeWithin() {
        System.out.println("isRangeWithin");
        RangeValue aRangeDescriptor = new RangeValue(0.0, 0.5);
        //same
        assertTrue(aRangeDescriptor.isRangeWithin(0.0, 0.5));
        //is range well
        assertFalse(aRangeDescriptor.isRangeWithin(-0.1, 0.5));
        assertFalse(aRangeDescriptor.isRangeWithin(0.0, 0.56));
        //is not inside
        assertTrue(aRangeDescriptor.isRangeWithin(0.1, 0.5));
        assertTrue(aRangeDescriptor.isRangeWithin(0.0, 0.4));
        
    }

}