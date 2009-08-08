/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package system.searchAutomata.test;

import java.util.List;
import ontology.CBR.PossibleSolution;
import ontology.common.Description;
import ontology.common.Descriptor;
import ontology.taxonomy.StructureIndex;
import ontology.taxonomy.Taxon;
import ontology.values.ValueDescriptor;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import system.searchAutomata.TaxonSearchAutomaton;
import system.searchAutomata.output.TaxonAutomatonOutput;

/**
 *
 * @author pabloq
 */
public class TaxonSearchAutomatonTest {

    public TaxonSearchAutomatonTest() {
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
     * Test of newOutput method, of class TaxonSearchAutomaton.
     */
    @Test
    public void testNewOutput() {
        System.out.println("newOutput");
        TaxonSearchAutomaton instance = new TaxonSearchAutomaton();
        instance.newOutput();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStatus method, of class TaxonSearchAutomaton.
     */
    @Test
    public void testSetStatus() {
        System.out.println("setStatus");
        String aStatusValue = "";
        TaxonSearchAutomaton instance = new TaxonSearchAutomaton();
        instance.setStatus(aStatusValue);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addTaxonList method, of class TaxonSearchAutomaton.
     */
    @Test
    public void testAddTaxonList() {
        System.out.println("addTaxonList");
        PossibleSolution taxonList = null;
        TaxonSearchAutomaton instance = new TaxonSearchAutomaton();
        instance.addTaxonList(taxonList);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTaxonList method, of class TaxonSearchAutomaton.
     */
    @Test
    public void testSetTaxonList() {
        System.out.println("setTaxonList");
        List<PossibleSolution> taxonList = null;
        TaxonSearchAutomaton instance = new TaxonSearchAutomaton();
        instance.setTaxonList(taxonList);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTSolutionDescription method, of class TaxonSearchAutomaton.
     */
    @Test
    public void testSetTSolutionDescription() {
        System.out.println("setTSolutionDescription");
        Descriptor<Object> aDescriptor = null;
        TaxonSearchAutomaton instance = new TaxonSearchAutomaton();
        boolean expResult = false;
        boolean result = instance.setTSolutionDescription(aDescriptor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTUnmatchedDescription method, of class TaxonSearchAutomaton.
     */
    @Test
    public void testSetTUnmatchedDescription() {
        System.out.println("setTUnmatchedDescription");
        Descriptor<Object> aDescriptor = null;
        TaxonSearchAutomaton instance = new TaxonSearchAutomaton();
        boolean expResult = false;
        boolean result = instance.setTUnmatchedDescription(aDescriptor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setValueDescriptors method, of class TaxonSearchAutomaton.
     */
    @Test
    public void testSetValueDescriptors() {
        System.out.println("setValueDescriptors");
        List<ValueDescriptor> aValueDescriptorList = null;
        TaxonSearchAutomaton instance = new TaxonSearchAutomaton();
        boolean expResult = false;
        boolean result = instance.setValueDescriptors(aValueDescriptorList);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getJustification method, of class TaxonSearchAutomaton.
     */
    @Test
    public void testGetJustification() {
        System.out.println("getJustification");
        TaxonSearchAutomaton instance = new TaxonSearchAutomaton();
        Description expResult = null;
        Description result = instance.getJustification();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSearchIndex method, of class TaxonSearchAutomaton.
     */
    @Test
    public void testGetSearchIndex() {
        System.out.println("getSearchIndex");
        TaxonSearchAutomaton instance = new TaxonSearchAutomaton();
        Object expResult = null;
        Object result = instance.getSearchIndex();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSearchOutput method, of class TaxonSearchAutomaton.
     */
    @Test
    public void testGetSearchOutput() {
        System.out.println("getSearchOutput");
        TaxonSearchAutomaton instance = new TaxonSearchAutomaton();
        TaxonAutomatonOutput expResult = null;
        TaxonAutomatonOutput result = instance.getSearchOutput();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStatus method, of class TaxonSearchAutomaton.
     */
    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        TaxonSearchAutomaton instance = new TaxonSearchAutomaton();
        String expResult = "";
        String result = instance.getStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTaxonList method, of class TaxonSearchAutomaton.
     */
    @Test
    public void testGetTaxonList() {
        System.out.println("getTaxonList");
        TaxonSearchAutomaton instance = new TaxonSearchAutomaton();
        List expResult = null;
        List result = instance.getTaxonList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTSolutionDescription method, of class TaxonSearchAutomaton.
     */
    @Test
    public void testGetTSolutionDescription() {
        System.out.println("getTSolutionDescription");
        TaxonSearchAutomaton instance = new TaxonSearchAutomaton();
        Description expResult = null;
        Description result = instance.getTSolutionDescription();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTUnmatchedDescription method, of class TaxonSearchAutomaton.
     */
    @Test
    public void testGetTUnmatchedDescription() {
        System.out.println("getTUnmatchedDescription");
        TaxonSearchAutomaton instance = new TaxonSearchAutomaton();
        Description expResult = null;
        Description result = instance.getTUnmatchedDescription();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValueDescriptors method, of class TaxonSearchAutomaton.
     */
    @Test
    public void testGetValueDescriptors() {
        System.out.println("getValueDescriptors");
        TaxonSearchAutomaton instance = new TaxonSearchAutomaton();
        List expResult = null;
        List result = instance.getValueDescriptors();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of associateTaxaToPossibleSolutions method, of class TaxonSearchAutomaton.
     */
    @Test
    public void testAssociateTaxaToPossibleSolutions() {
        System.out.println("associateTaxaToPossibleSolutions");
        List<Taxon> aTaxonList = null;
        TaxonSearchAutomaton instance = new TaxonSearchAutomaton();
        List expResult = null;
        List result = instance.associateTaxaToPossibleSolutions(aTaxonList);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkPrecondition method, of class TaxonSearchAutomaton.
     */
    @Test
    public void testCheckPrecondition() {
        System.out.println("checkPrecondition");
        Description<Descriptor<Object>> aProblemDescription = null;
        TaxonSearchAutomaton instance = new TaxonSearchAutomaton();
        boolean expResult = false;
        boolean result = instance.checkPrecondition(aProblemDescription);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of prepareFailedOutput method, of class TaxonSearchAutomaton.
     */
    @Test
    public void testPrepareFailedOutput() {
        System.out.println("prepareFailedOutput");
        TaxonSearchAutomaton instance = new TaxonSearchAutomaton();
        boolean expResult = false;
        boolean result = instance.prepareFailedOutput();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of prepareSuccessfulOutputWith method, of class TaxonSearchAutomaton.
     */
    @Test
    public void testPrepareSuccessfulOutputWith() {
        System.out.println("prepareSuccessfulOutputWith");
        List<PossibleSolution> aPossibleSolutionsList = null;
        TaxonSearchAutomaton instance = new TaxonSearchAutomaton();
        boolean expResult = false;
        boolean result = instance.prepareSuccessfulOutputWith(aPossibleSolutionsList);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetList method, of class TaxonSearchAutomaton.
     */
    @Test
    public void testResetList() {
        System.out.println("resetList");
        List vdList = null;
        TaxonSearchAutomaton instance = new TaxonSearchAutomaton();
        instance.resetList(vdList);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSearchIndex method, of class TaxonSearchAutomaton.
     */
    @Test
    public void testSetSearchIndex() {
        System.out.println("setSearchIndex");
        StructureIndex aSearchIndex = null;
        TaxonSearchAutomaton instance = new TaxonSearchAutomaton();
        instance.setSearchIndex(aSearchIndex);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of includes method, of class TaxonSearchAutomaton.
     */
    @Test
    public void testIncludes() {
        System.out.println("includes");
        Descriptor<Object> aDescriptor = null;
        Description<Descriptor<Object>> aDescription = null;
        TaxonSearchAutomaton instance = new TaxonSearchAutomaton();
        Descriptor expResult = null;
        Descriptor result = instance.includes(aDescriptor, aDescription);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}