/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ontology.values.test;

import ontology.taxonomy.TaxonomicRank;
import ontology.values.MeasuringUnit;
import ontology.values.RangeValue;
import ontology.values.SingleValue;
import ontology.values.Values;
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
public class ValuesTest {
    SingleValue<Object> vd,vd2,vd3;
    RangeValue vdr,vdr2,vdr3;

    public ValuesTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        vd = new SingleValue<Object>();
        vd2 = new SingleValue<Object>();
        vd3 = new SingleValue<Object>();
        vd.setValue("alargado");
        //vd.setWeight(0.3);

        vd2.setValue("achatado");
        //vd2.setWeight(0.2);

        vd3.setValue("redondo");
        //vd3.setWeight(0.1);

        vdr = new RangeValue();
        vdr2 = new RangeValue();
        vdr3 = new RangeValue();

        vdr.setLowerBound(5.0);
        vdr.setUpperBound(10.0);
        vdr.setMeasuringUnit(MeasuringUnit.INCH);

        vdr2.setLowerBound(0.1);
        vdr2.setUpperBound(0.5);
        vdr2.setMeasuringUnit(MeasuringUnit.CM);

        vdr3.setLowerBound(50.0);
        vdr3.setUpperBound(4.0);
        vdr3.setMeasuringUnit(MeasuringUnit.MM);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getSingleDescriptors method, of class Values.
     */
    @Test
    public void testGetSingleDescriptors() {
        System.out.println("getSingleDescriptors");
        Values instance = new Values();
        instance.addValueDescriptor(vd, TaxonomicRank.FAMILY);
        instance.addValueDescriptor(vdr, TaxonomicRank.FAMILY);
        instance.addValueDescriptor(vd2, TaxonomicRank.FAMILY);
        instance.addValueDescriptor(vd3, TaxonomicRank.FAMILY);
        instance.addValueDescriptor(vdr3, TaxonomicRank.FAMILY);
        assertEquals(vd,instance.getSingleDescriptors(vd.getValue(),TaxonomicRank.FAMILY).get(0));
        assertEquals(vd3,instance.getSingleDescriptors(vd3.getValue(),TaxonomicRank.FAMILY).get(0));
    }

    /**
     * Test of addValueDescriptor method, of class Values.
     */
    @Test
    public void testAddValueDescriptor() {
        System.out.println("addValueDescriptor");
        Values instance = new Values();
        instance.addValueDescriptor(vd, TaxonomicRank.FAMILY);
        instance.addValueDescriptor(vd2, TaxonomicRank.GENUS);
        instance.addValueDescriptor(vd3, TaxonomicRank.SPECIES);


        assertEquals(3,instance.getValueDescriptors().size());
        /*
         * Check it they were added well
         *
         */
        assertEquals(vd,instance.getValueDescriptors().get(TaxonomicRank.getIndex(TaxonomicRank.FAMILY)-1).get(0));
        assertEquals(vd2,instance.getValueDescriptors().get(TaxonomicRank.getIndex(TaxonomicRank.GENUS)-1).get(0));
        assertEquals(vd3,instance.getValueDescriptors().get(TaxonomicRank.getIndex(TaxonomicRank.SPECIES)-1).get(0));
    }

    /**
     * Test of addValueDescriptorWithUniqueValue method, of class Values.
     */
    @Test
    public void testAddValueDescriptorWithUniqueValue() {
        System.out.println("addValueDescriptorWithUniqueValue");
        Values instance = new Values();
        instance.addValueDescriptor(vd, TaxonomicRank.FAMILY);
        instance.addValueDescriptor(vd2, TaxonomicRank.GENUS);
        instance.addValueDescriptor(vd3, TaxonomicRank.SPECIES);


        assertEquals(3,instance.getValueDescriptors().size());
        /*
         * Check it they were added well
         *
         */
        assertEquals(vd,instance.getValueDescriptors().get(TaxonomicRank.getIndex(TaxonomicRank.FAMILY)-1).get(0));
        assertEquals(vd2,instance.getValueDescriptors().get(TaxonomicRank.getIndex(TaxonomicRank.GENUS)-1).get(0));
        assertEquals(vd3,instance.getValueDescriptors().get(TaxonomicRank.getIndex(TaxonomicRank.SPECIES)-1).get(0));
    }

    /**
     * Test of includes method, of class Values.
     */
    @Test
    public void testIncludes() {
        System.out.println("includes");
        Values instance = new Values();
        instance.addValueDescriptor(vd, TaxonomicRank.FAMILY);
        instance.addValueDescriptor(vd2, TaxonomicRank.GENUS);
        instance.addValueDescriptor(vd3, TaxonomicRank.SPECIES);

        instance.addValueDescriptor(vdr, TaxonomicRank.FAMILY);
        instance.addValueDescriptor(vdr2, TaxonomicRank.GENUS);
        instance.addValueDescriptor(vdr3, TaxonomicRank.SPECIES);

        assertTrue(instance.includes(vd, TaxonomicRank.getIndex(TaxonomicRank.FAMILY)-1));
        assertTrue(instance.includes(vd2, TaxonomicRank.getIndex(TaxonomicRank.GENUS)-1));
        assertTrue(instance.includes(vd3, TaxonomicRank.getIndex(TaxonomicRank.SPECIES)-1));
        assertTrue(instance.includes(vdr, TaxonomicRank.getIndex(TaxonomicRank.FAMILY)-1));
        assertTrue(instance.includes(vdr2, TaxonomicRank.getIndex(TaxonomicRank.GENUS)-1));
        assertTrue(instance.includes(vdr3, TaxonomicRank.getIndex(TaxonomicRank.SPECIES)-1));

        /*
         * Some wrong cases
         *
         */
        assertFalse(instance.includes(vd, TaxonomicRank.getIndex(TaxonomicRank.GENUS)-1));
        assertFalse(instance.includes(vdr2, TaxonomicRank.getIndex(TaxonomicRank.SPECIES)-1));

        SingleValue<Object> tvd = new SingleValue<Object>();
        SingleValue<Object> tvd2 = new SingleValue<Object>();
        tvd.setValue("alargado");

        tvd2.setValue("achatado");

        assertTrue(instance.includes(tvd, TaxonomicRank.getIndex(TaxonomicRank.FAMILY)-1));
//        tvd.setWeight(0.9);
//        assertFalse(instance.includes(tvd, TaxonomicRank.getIndex(TaxonomicRank.FAMILY)-1));

        assertTrue(instance.includes(tvd2, TaxonomicRank.getIndex(TaxonomicRank.GENUS)-1));
//        tvd2.setWeight(0.45345);
//        assertFalse(instance.includes(tvd2, TaxonomicRank.getIndex(TaxonomicRank.GENUS)-1));
    }

    /**
     * Test of includesUniqueValue method, of class Values.
     */
    @Test
    public void testIncludesUniqueValue() {
        System.out.println("includesUniqueValue");
        Values instance = new Values();
        instance.addValueDescriptor(vd, TaxonomicRank.FAMILY);
        instance.addValueDescriptor(vd2, TaxonomicRank.GENUS);
        instance.addValueDescriptor(vd3, TaxonomicRank.SPECIES);

        instance.addValueDescriptor(vdr, TaxonomicRank.FAMILY);
        instance.addValueDescriptor(vdr2, TaxonomicRank.GENUS);
        instance.addValueDescriptor(vdr3, TaxonomicRank.SPECIES);

        assertTrue(instance.includes(vd, TaxonomicRank.getIndex(TaxonomicRank.FAMILY)-1));
        assertTrue(instance.includes(vd2, TaxonomicRank.getIndex(TaxonomicRank.GENUS)-1));
        assertTrue(instance.includes(vd3, TaxonomicRank.getIndex(TaxonomicRank.SPECIES)-1));
        assertTrue(instance.includes(vdr, TaxonomicRank.getIndex(TaxonomicRank.FAMILY)-1));
        assertTrue(instance.includes(vdr2, TaxonomicRank.getIndex(TaxonomicRank.GENUS)-1));
        assertTrue(instance.includes(vdr3, TaxonomicRank.getIndex(TaxonomicRank.SPECIES)-1));

        /*
         * Some wrong cases
         *
         */
        assertFalse(instance.includes(vd, TaxonomicRank.getIndex(TaxonomicRank.GENUS)-1));
        assertFalse(instance.includes(vdr2, TaxonomicRank.getIndex(TaxonomicRank.SPECIES)-1));
    }

}