/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ontology.values.test;

import java.util.List;
import ontology.taxonomy.TaxonomicRank;
import ontology.values.SingleValue;
import ontology.values.Values;
import ontology.values.Value;
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
public class ValueTest {
    Values aValue;
    Values aUniqueValue;

    public ValueTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        aValue = new Values ();
        aUniqueValue = new Values (true);
        SingleValue aSingleDescriptor = new SingleValue();
        aSingleDescriptor.addTaxon(null);
        SingleValue aSingleDescriptor2 = new SingleValue();
    }

    @After
    public void tearDown() {
    }



    /**
     * Test of creation method, of class Value.
     */
    @Test
    public void testCreate() {
        System.out.println("CreateValue");
        /*
         * It should have a single level
         */
         assertEquals(1, aUniqueValue.getValueDescriptors().size());
        /*
         * It should have same levels than  TaxonomicRank.getNomenclaturalRanksNumber()
         */
         assertEquals(TaxonomicRank.getNomenclaturalRanksNumber(), aValue.getValueDescriptors().size());
        /*
         * It should return 0 level when it is created without levels
         */
         assertEquals(0, (new Values(false)).getValueDescriptors().size());
        
    }

    /**
     * Test of getValueDescriptors method, of class Value.
     */
    @Test
    public void testGetValueDescriptors_GenericType_TaxonomicRank() {
        System.out.println("getValueDescriptors");
        Value aDescriptor = null;
        TaxonomicRank aLevel = null;
        Values instance = new Values();
        Value expResult = null;
        Value result = instance.getValueDescriptors(aDescriptor, aLevel);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValueDescriptors method, of class Value.
     */
    @Test
    public void testGetValueDescriptors_0args() {
        System.out.println("getValueDescriptors");
        Values instance = new Values();
        Values expResult = null;
        Values result = instance.getValueDescriptors();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValueDescriptors method, of class Value.
     */
    @Test
    public void testGetValueDescriptors_TaxonomicRank() {
        System.out.println("getValueDescriptors");
        TaxonomicRank aLevel = null;
        Values instance = new Values();
        List expResult = null;
        List result = instance.getValueDescriptors(aLevel);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRangeDescriptorsWithNumber method, of class Value.
     */
    @Test
    public void testGetRangeDescriptorsWithNumber() {
        System.out.println("getRangeDescriptorsWithNumber");
        double aNumber = 0.0;
        TaxonomicRank aLevel = null;
        Values instance = new Values();
        List expResult = null;
        List result = instance.getRangeDescriptorsWithNumber(aNumber, aLevel);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRangeDescriptorsWithRange method, of class Value.
     */
    @Test
    public void testGetRangeDescriptorsWithRange() {
        System.out.println("getRangeDescriptorsWithRange");
        double aLowerBound = 0.0;
        double anUpperBound = 0.0;
        TaxonomicRank aLevel = null;
        Values instance = new Values();
        List expResult = null;
        List result = instance.getRangeDescriptorsWithRange(aLowerBound, anUpperBound, aLevel);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSingleDescriptors method, of class Value.
     */
    @Test
    public void testGetSingleDescriptors_GenericType_TaxonomicRank() {
        System.out.println("getSingleDescriptors");
        Object aValue = null;
        TaxonomicRank aLevel = null;
        Values instance = new Values();
        List expResult = null;
        List result = instance.getSingleDescriptors(aValue, aLevel);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSingleDescriptorsWithWeightInRange method, of class Value.
     */
    @Test
    public void testGetSingleDescriptorsWithWeightInRange() {
        System.out.println("getSingleDescriptorsWithWeightInRange");
        Object aValue = null;
        TaxonomicRank aLevel = null;
        double aLowerBound = 0.0;
        double anUpperBound = 0.0;
        Values instance = new Values();
        List expResult = null;
        List result = instance.getSingleDescriptorsWithWeightInRange(aValue, aLevel, aLowerBound, anUpperBound);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSingleDescriptors method, of class Value.
     */
    @Test
    public void testGetSingleDescriptors_4args_1() {
        System.out.println("getSingleDescriptors");
        Object aValue = null;
        double aWeight = 0.0;
        TaxonomicRank aLevel = null;
        String anOperator = "";
        Values instance = new Values();
        List expResult = null;
        List result = instance.getSingleDescriptors(aValue, aWeight, aLevel, anOperator);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSingleDescriptors method, of class Value.
     */
    @Test
    public void testGetSingleDescriptors_4args_2() {
        System.out.println("getSingleDescriptors");
        double aWeight = 0.0;
        TaxonomicRank aLevel = null;
        double aLowerBound = 0.0;
        double anUpperBound = 0.0;
        Values instance = new Values();
        List expResult = null;
        List result = instance.getSingleDescriptors(aWeight, aLevel, aLowerBound, anUpperBound);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSingleDescriptors method, of class Value.
     */
    @Test
    public void testGetSingleDescriptors_3args() {
        System.out.println("getSingleDescriptors");
        double aWeight = 0.0;
        TaxonomicRank aLevel = TaxonomicRank.FAMILY;
        String anOperator = "";
        Values instance = new Values();
        List expResult = null;
        List result;
        //assertEquals(expResult, result);



    /**
     * it should fail using an unvalid taxonomic level or null operator
     */
     assertEquals(null, instance.getSingleDescriptors(aWeight, null, anOperator));
     assertEquals(null, instance.getSingleDescriptors(aWeight, aLevel, null));
    /**
     * it should test operators
     */

        
    }

    /**
     * Test of addValueDescriptor method, of class Value.
     */
    @Test
    public void testAddValueDescriptor() {
        System.out.println("addValueDescriptor");
        Value aValueDescriptor = null;
        TaxonomicRank aLevel = null;
        Values instance = new Values();
        instance.addValueDescriptor(aValueDescriptor, aLevel);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addValueDescriptorWithUniqueValue method, of class Value.
     */
    @Test
    public void testAddValueDescriptorWithUniqueValue() {
        System.out.println("addValueDescriptorWithUniqueValue");
        Value aValueDescriptor = null;
        TaxonomicRank aLevel = null;
        Values instance = new Values();
        instance.addValueDescriptorWithUniqueValue(aValueDescriptor, aLevel);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of includes method, of class Value.
     */
    @Test
    public void testIncludes() {
        System.out.println("includes");
        Value aDescriptor = null;
        int aNumberLevel = 0;
        Values instance = new Values();
        boolean expResult = false;
        boolean result = instance.includes(aDescriptor, aNumberLevel);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of includesUniqueValue method, of class Value.
     */
    @Test
    public void testIncludesUniqueValue() {
        System.out.println("includesUniqueValue");
        Value aDescriptor = null;
        int aNumberLevel = 0;
        Values instance = new Values();
        boolean expResult = false;
        boolean result = instance.includesUniqueValue(aDescriptor, aNumberLevel);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}