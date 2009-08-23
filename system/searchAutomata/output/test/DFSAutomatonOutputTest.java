/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package system.searchAutomata.output.test;

import ontology.taxonomy.Taxonomy;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import system.searchAutomata.output.DFSAutomatonOutput;

/**
 *
 * @author pabloq
 */
public class DFSAutomatonOutputTest {

    public DFSAutomatonOutputTest() {
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
     * Test of compress method, of class DFSAutomatonOutput.
     */
    @Test
    public void testCompress() {
        System.out.println("compress");
        DFSAutomatonOutput instance = new DFSAutomatonOutput();
        boolean expResult = false;
        boolean result = instance.compress();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTaxonomy method, of class DFSAutomatonOutput.
     */
    @Test
    public void testGetTaxonomy() {
        System.out.println("getTaxonomy");
        DFSAutomatonOutput instance = new DFSAutomatonOutput();
        Taxonomy expResult = null;
        Taxonomy result = instance.getTaxonomy();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTaxonomy method, of class DFSAutomatonOutput.
     */
    @Test
    public void testSetTaxonomy() {
        System.out.println("setTaxonomy");
        Taxonomy aTaxonomy = null;
        DFSAutomatonOutput instance = new DFSAutomatonOutput();
        instance.setTaxonomy(aTaxonomy);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}