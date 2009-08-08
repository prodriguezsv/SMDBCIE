/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package system.searchAutomata.test;

import ontology.common.Description;
import ontology.common.Descriptor;
import ontology.common.GroupingHeuristic;
import ontology.taxonomy.GroupingHeuristicIndex;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import system.searchAutomata.TaxonGHISAutomaton;

/**
 *
 * @author pabloq
 */
public class TaxonGHISAutomatonTest {

    public TaxonGHISAutomatonTest() {
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
     * Test of initializeWithIndex method, of class TaxonGHISAutomaton.
     */
    @Test
    public void testInitializeWithIndex() {
        System.out.println("initializeWithIndex");
        GroupingHeuristicIndex aGroupingHeuristicIndex = null;
        TaxonGHISAutomaton instance = null;
        instance.initializeWithIndex(aGroupingHeuristicIndex);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetGroupingHeuristic method, of class TaxonGHISAutomaton.
     */
    @Test
    public void testResetGroupingHeuristic() {
        System.out.println("resetGroupingHeuristic");
        TaxonGHISAutomaton instance = null;
        instance.resetGroupingHeuristic();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGroupingHeuristic method, of class TaxonGHISAutomaton.
     */
    @Test
    public void testSetGroupingHeuristic() {
        System.out.println("setGroupingHeuristic");
        GroupingHeuristic aGroupingHeuristic = null;
        TaxonGHISAutomaton instance = null;
        instance.setGroupingHeuristic(aGroupingHeuristic);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGroupingHeuristic method, of class TaxonGHISAutomaton.
     */
    @Test
    public void testGetGroupingHeuristic() {
        System.out.println("getGroupingHeuristic");
        TaxonGHISAutomaton instance = null;
        GroupingHeuristic expResult = null;
        GroupingHeuristic result = instance.getGroupingHeuristic();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GetGroupingHeuristicIndex method, of class TaxonGHISAutomaton.
     */
    @Test
    public void testGetGroupingHeuristicIndex() {
        System.out.println("GetGroupingHeuristicIndex");
        TaxonGHISAutomaton instance = null;
        GroupingHeuristicIndex expResult = null;
        GroupingHeuristicIndex result = instance.GetGroupingHeuristicIndex();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of beginWith method, of class TaxonGHISAutomaton.
     */
    @Test
    public void testBeginWith() {
        System.out.println("beginWith");
        Description<Descriptor<Object>> aProblemDescription = null;
        TaxonGHISAutomaton instance = null;
        Object expResult = null;
        Object result = instance.beginWith(aProblemDescription);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchValueDescriptors method, of class TaxonGHISAutomaton.
     */
    @Test
    public void testSearchValueDescriptors() {
        System.out.println("searchValueDescriptors");
        Descriptor<Object> aDescriptor = null;
        TaxonGHISAutomaton instance = null;
        boolean expResult = false;
        boolean result = instance.searchValueDescriptors(aDescriptor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}