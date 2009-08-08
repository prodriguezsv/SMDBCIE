/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ontology.common.test;

import ontology.common.Description;
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
public class DescriptionTest {

    public DescriptionTest() {
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
     * Test of add method, of class Description.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Descriptor<Object> aDescriptor = null;
        Description instance = new Description();
        boolean expResult = false;
        boolean result = instance.add(aDescriptor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}