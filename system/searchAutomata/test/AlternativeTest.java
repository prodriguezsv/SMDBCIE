/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package system.searchAutomata.test;

import ontology.common.Descriptor;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import redundantDiscriminationNet.Node;
import system.searchAutomata.Alternative;

/**
 *
 * @author pabloq
 */
public class AlternativeTest {

    public AlternativeTest() {
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
     * Test of getNode method, of class Alternative.
     */
    @Test
    public void testGetNode() {
        System.out.println("getNode");
        Alternative instance = new Alternative();
        Node expResult = null;
        Node result = instance.getNode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNode method, of class Alternative.
     */
    @Test
    public void testSetNode() {
        System.out.println("setNode");
        Node node = null;
        Alternative instance = new Alternative();
        instance.setNode(node);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDescriptor method, of class Alternative.
     */
    @Test
    public void testSetDescriptor() {
        System.out.println("setDescriptor");
        Descriptor<Object> descriptor = null;
        Alternative instance = new Alternative();
        instance.setDescriptor(descriptor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDescriptor method, of class Alternative.
     */
    @Test
    public void testGetDescriptor() {
        System.out.println("getDescriptor");
        Alternative instance = new Alternative();
        Descriptor expResult = null;
        Descriptor result = instance.getDescriptor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}