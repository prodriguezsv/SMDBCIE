/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package onthology.common.test.onthology.common;

import java.util.List;
import onthology.common.Attribute;
import onthology.common.Description;
import onthology.common.Structure;
import onthology.taxonomy.Taxon;
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
public class StructureTest {

    public StructureTest() {
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
     * Test of setName method, of class Structure.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        Structure instance = new Structure();
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Structure.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Structure instance = new Structure();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setWeight method, of class Structure.
     */
    @Test
    public void testSetWeight() {
        System.out.println("setWeight");
        Double weight = null;
        Structure instance = new Structure();
        instance.setWeight(weight);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWeight method, of class Structure.
     */
    @Test
    public void testGetWeight() {
        System.out.println("getWeight");
        Structure instance = new Structure();
        Double expResult = null;
        Double result = instance.getWeight();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addAttribute method, of class Structure.
     */
    @Test
    public void testAddAttribute() {
        System.out.println("addAttribute");
        Attribute anAttribute = null;
        Structure instance = new Structure();
        instance.addAttribute(anAttribute);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAttributes method, of class Structure.
     */
    @Test
    public void testGetAttributes() {
        System.out.println("getAttributes");
        Structure instance = new Structure();
        List expResult = null;
        List result = instance.getAttributes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAttribute method, of class Structure.
     */
    @Test
    public void testGetAttribute() {
        System.out.println("getAttribute");
        String anAttributeName = "";
        Structure instance = new Structure();
        Attribute expResult = null;
        Attribute result = instance.getAttribute(anAttributeName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addAtributes method, of class Structure.
     */
    @Test
    public void testAddAtributes() {
        System.out.println("addAtributes");
        Structure aStructure = null;
        Taxon aTaxon = null;
        Structure instance = new Structure();
        instance.addAtributes(aStructure, aTaxon);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createDescription method, of class Structure.
     */
    @Test
    public void testCreateDescription() {
        System.out.println("createDescription");
        String aDummyName = "";
        Structure instance = new Structure();
        Description expResult = null;
        Description result = instance.createDescription(aDummyName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compareTo method, of class Structure.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Structure aStructure = null;
        Structure instance = new Structure();
        int expResult = 0;
        int result = instance.compareTo(aStructure);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of contains method, of class Structure.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        String anAttributeName = "";
        Structure instance = new Structure();
        boolean expResult = false;
        boolean result = instance.contains(anAttributeName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}