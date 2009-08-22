/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ontology.values.test;

import ontology.taxonomy.Taxon;
import ontology.taxonomy.TaxonomicRank;
import ontology.values.MeasuringUnit;
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

    /**
     * Test of setMeasuringUnit method, of class RangeValue.
     */
    @Test
    public void testSetMeasuringUnit() {
        System.out.println("setMeasuringUnit");
        MeasuringUnit a = MeasuringUnit.CM;
        MeasuringUnit b = MeasuringUnit.INCH;
        RangeValue aRangeDescriptor = new RangeValue();
        aRangeDescriptor.setMeasuringUnit(a);
        assertEquals(a,aRangeDescriptor.getMeasuringUnit());
        /*
         * keep the same mesure unit.
         *
         */
        aRangeDescriptor.setMeasuringUnit(b);
        assertEquals(a,aRangeDescriptor.getMeasuringUnit());

        
    }

    /**
     * Test of addValues method, of class RangeValue.
     */
    @Test
    public void testAddValues() {
        System.out.println("addValues");
        Object aDescriptor = null;

        Taxon aTaxon = new Taxon() ;
        aTaxon.setName("Chromodorididae");
        aTaxon.setLevel(TaxonomicRank.FAMILY);
        
        RangeValue instance = new RangeValue();

        RangeValue aRangeDescriptor = new RangeValue();
        aRangeDescriptor.setLowerBound(0.0);
        aRangeDescriptor.setUpperBound(0.5);
        aRangeDescriptor.setMeasuringUnit(MeasuringUnit.CM);
        
        instance.addValues(aRangeDescriptor);
    }

    /**
     * Test of isRangeWithin method, of class RangeValue.
     */
    @Test
    public void testIsRangeWithin() {
        System.out.println("isRangeWithin");
        RangeValue aRangeDescriptor = new RangeValue();
        aRangeDescriptor.setLowerBound(0.0);
        aRangeDescriptor.setUpperBound(0.5);
        //same
        assertTrue(aRangeDescriptor.isRangeWithin(0.0, 0.5));
        //is range well
        assertTrue(aRangeDescriptor.isRangeWithin(-0.1, 0.5));
        assertTrue(aRangeDescriptor.isRangeWithin(0.0, 0.54));
        //is not inside
        assertFalse(aRangeDescriptor.isRangeWithin(0.1, 0.5));
        assertFalse(aRangeDescriptor.isRangeWithin(0.0, 0.4));
        
    }

}