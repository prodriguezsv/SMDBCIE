/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package system.searchAutomata.output.test.system.searchAutomata.output;

import java.util.List;
import ontology.CBR.PossibleSolution;
import ontology.common.Description;
import ontology.common.Descriptor;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import system.searchAutomata.output.SearchAutomatonOutput;

/**
 *
 * @author pabloq
 */
public class SearchAutomatonOutputTest {

    public SearchAutomatonOutputTest() {
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
     * Test of setJustification method, of class SearchAutomatonOutput.
     */
    @Test
    public void testSetJustification() {
        System.out.println("setJustification");
        Description<Descriptor<Object>> aJustificationsList = null;
        SearchAutomatonOutput instance = new SearchAutomatonOutput();
        instance.setJustification(aJustificationsList);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPossibleSolutions method, of class SearchAutomatonOutput.
     */
    @Test
    public void testSetPossibleSolutions() {
        System.out.println("setPossibleSolutions");
        List<PossibleSolution> aPossibleSolutionList = null;
        SearchAutomatonOutput instance = new SearchAutomatonOutput();
        instance.setPossibleSolutions(aPossibleSolutionList);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUnmatchedDescription method, of class SearchAutomatonOutput.
     */
    @Test
    public void testSetUnmatchedDescription() {
        System.out.println("setUnmatchedDescription");
        Description<Descriptor<Object>> anUnmatchedDescription = null;
        SearchAutomatonOutput instance = new SearchAutomatonOutput();
        instance.setUnmatchedDescription(anUnmatchedDescription);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getJustification method, of class SearchAutomatonOutput.
     */
    @Test
    public void testGetJustification() {
        System.out.println("getJustification");
        SearchAutomatonOutput instance = new SearchAutomatonOutput();
        Description expResult = null;
        Description result = instance.getJustification();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPossibleSolutions method, of class SearchAutomatonOutput.
     */
    @Test
    public void testGetPossibleSolutions() {
        System.out.println("getPossibleSolutions");
        SearchAutomatonOutput instance = new SearchAutomatonOutput();
        List expResult = null;
        List result = instance.getPossibleSolutions();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUnmatchedDescription method, of class SearchAutomatonOutput.
     */
    @Test
    public void testGetUnmatchedDescription() {
        System.out.println("getUnmatchedDescription");
        SearchAutomatonOutput instance = new SearchAutomatonOutput();
        Description expResult = null;
        Description result = instance.getUnmatchedDescription();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of appendToJustification method, of class SearchAutomatonOutput.
     */
    @Test
    public void testAppendToJustification() {
        System.out.println("appendToJustification");
        Description<Descriptor<Object>> aJustificationList = null;
        SearchAutomatonOutput instance = new SearchAutomatonOutput();
        instance.appendToJustification(aJustificationList);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of appendToPossibleSolutions method, of class SearchAutomatonOutput.
     */
    @Test
    public void testAppendToPossibleSolutions() {
        System.out.println("appendToPossibleSolutions");
        List<PossibleSolution> aPossibleSolutionsList = null;
        SearchAutomatonOutput instance = new SearchAutomatonOutput();
        instance.appendToPossibleSolutions(aPossibleSolutionsList);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of appendToUnmatchedDescription method, of class SearchAutomatonOutput.
     */
    @Test
    public void testAppendToUnmatchedDescription() {
        System.out.println("appendToUnmatchedDescription");
        Description<Descriptor<Object>> anUnmatchedDescription = null;
        SearchAutomatonOutput instance = new SearchAutomatonOutput();
        instance.appendToUnmatchedDescription(anUnmatchedDescription);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDescriptor method, of class SearchAutomatonOutput.
     */
    @Test
    public void testGetDescriptor() {
        System.out.println("getDescriptor");
        Descriptor<Object> aDescriptor = null;
        Description<Descriptor<Object>> aDescription = null;
        SearchAutomatonOutput instance = new SearchAutomatonOutput();
        Descriptor expResult = null;
        Descriptor result = instance.getDescriptor(aDescriptor, aDescription);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of contains method, of class SearchAutomatonOutput.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        Descriptor<Object> aSAVDescriptor = null;
        Description<Descriptor<Object>> aDescription = null;
        SearchAutomatonOutput instance = new SearchAutomatonOutput();
        boolean expResult = false;
        boolean result = instance.contains(aSAVDescriptor, aDescription);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}