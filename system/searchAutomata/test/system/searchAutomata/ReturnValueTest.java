/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package system.searchAutomata.test.system.searchAutomata;

import onthology.common.Descriptor;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import system.searchAutomata.ReturnValue;

/**
 *
 * @author pabloq
 */
public class ReturnValueTest {

    public ReturnValueTest() {
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
     * Test of setResponse method, of class ReturnValue.
     */
    @Test
    public void testSetResponse() {
        System.out.println("setResponse");
        String response = "";
        ReturnValue instance = new ReturnValue();
        instance.setResponse(response);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getResponse method, of class ReturnValue.
     */
    @Test
    public void testGetResponse() {
        System.out.println("getResponse");
        ReturnValue instance = new ReturnValue();
        String expResult = "";
        String result = instance.getResponse();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDescriptor method, of class ReturnValue.
     */
    @Test
    public void testSetDescriptor() {
        System.out.println("setDescriptor");
        Descriptor<Object> aDescriptor = null;
        ReturnValue instance = new ReturnValue();
        instance.setDescriptor(aDescriptor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDescriptor method, of class ReturnValue.
     */
    @Test
    public void testGetDescriptor() {
        System.out.println("getDescriptor");
        ReturnValue instance = new ReturnValue();
        Descriptor expResult = null;
        Descriptor result = instance.getDescriptor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}