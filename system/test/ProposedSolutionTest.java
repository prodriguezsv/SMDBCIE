/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package system.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import system.ProposedSolution;
import static org.junit.Assert.*;

/**
 *
 * @author pabloq
 */
public class ProposedSolutionTest {

    public ProposedSolutionTest() {
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
     * Test of setDegreeOfCertainty method, of class ProposedSolution.
     */
    @Test
    public void testSetDegreeOfCertainty() {
        java.lang.System.out.println("setDegreeOfCertainty");
        ProposedSolution instance = new ProposedSolution();

        assertFalse(instance.setDegreeOfCertainty("no at degree description"));
        assertTrue(instance.setDegreeOfCertainty("unknown"));
        assertTrue(instance.setDegreeOfCertainty("uncertain"));
        assertTrue(instance.setDegreeOfCertainty("doubfult"));
        assertTrue(instance.setDegreeOfCertainty("certain"));
    }


}