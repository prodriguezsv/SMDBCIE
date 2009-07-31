/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ontology.common.test.onthology.common;

import ontology.common.Attribute;
import ontology.taxonomy.Taxon;
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
public class AttributeTest {

    public AttributeTest() {
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
     * Test of oneLevel method, of class Attribute.
     */
    @Test
    public void testOneLevel() {
        System.out.println("oneLevel");
        int expResult = 0;
        int result = Attribute.oneLevel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Attribute.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        Attribute instance = new Attribute();
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Attribute.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Attribute instance = new Attribute();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValues method, of class Attribute.
     */
    @Test
    public void testGetValues() {
        System.out.println("getValues");
        Attribute instance = new Attribute();
        Value expResult = null;
        Value result = instance.getValues();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addValues method, of class Attribute.
     */
    @Test
    public void testAddValues() {
        System.out.println("addValues");
        Attribute attribute = null;
        Taxon taxon = null;
        Attribute instance = new Attribute();
        boolean expResult = false;
        boolean result = instance.addValues(attribute, taxon);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compareTo method, of class Attribute.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Attribute aAttribute = null;
        Attribute instance = new Attribute();
        int expResult = 0;
        int result = instance.compareTo(aAttribute);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}