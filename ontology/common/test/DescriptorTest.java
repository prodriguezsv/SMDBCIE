/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ontology.common.test;

import ontology.common.Descriptor;

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
public class DescriptorTest {

    public DescriptorTest() {
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
     * Test of set method, of class Descriptor.
     */
    @Test
    public void testSet() {
        System.out.println("set");
        String aStructure = "";
        String anAttribute = "";
        Object aValue = null;
        Descriptor instance = new Descriptor();
        instance.set(aStructure, anAttribute, aValue);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStructure method, of class Descriptor.
     */
    @Test
    public void testSetStructure() {
        System.out.println("setStructure");
        String structure = "";
        Descriptor instance = new Descriptor();
        instance.setStructure(structure);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStructure method, of class Descriptor.
     */
    @Test
    public void testGetStructure() {
        System.out.println("getStructure");
        Descriptor instance = new Descriptor();
        String expResult = "";
        String result = instance.getStructure();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAttribute method, of class Descriptor.
     */
    @Test
    public void testGetAttribute() {
        System.out.println("getAttribute");
        Descriptor instance = new Descriptor();
        String expResult = "";
        String result = instance.getAttribute();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValue method, of class Descriptor.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        Descriptor instance = new Descriptor();
        Object expResult = null;
        Object result = instance.getValue();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compareTo method, of class Descriptor.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Descriptor<T> aDescriptor = null;
        Descriptor instance = new Descriptor();
        int expResult = 0;
        int result = instance.compareTo(aDescriptor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Descriptor.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object aDescriptor = null;
        Descriptor instance = new Descriptor();
        boolean expResult = false;
        boolean result = instance.equals(aDescriptor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}