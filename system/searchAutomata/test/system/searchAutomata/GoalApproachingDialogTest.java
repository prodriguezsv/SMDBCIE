/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package system.searchAutomata.test.system.searchAutomata;

import java.util.List;
import ontology.CBR.Hypothesis;
import ontology.CBR.PossibleSolution;
import ontology.common.Attribute;
import ontology.common.Descriptor;
import ontology.taxonomy.Taxon;
import ontology.taxonomy.Taxonomy;
import ontology.values.RangeDescriptor;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import system.searchAutomata.GoalApproachingDialog;
import system.searchAutomata.ReturnValue;

/**
 *
 * @author pabloq
 */
public class GoalApproachingDialogTest {

    public GoalApproachingDialogTest() {
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
     * Test of getGoal method, of class GoalApproachingDialog.
     */
    @Test
    public void testGetGoal() {
        System.out.println("getGoal");
        GoalApproachingDialog instance = new GoalApproachingDialog();
        String expResult = "";
        String result = instance.getGoal();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHypothesis method, of class GoalApproachingDialog.
     */
    @Test
    public void testGetHypothesis() {
        System.out.println("getHypothesis");
        GoalApproachingDialog instance = new GoalApproachingDialog();
        Hypothesis expResult = null;
        Hypothesis result = instance.getHypothesis();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOKList method, of class GoalApproachingDialog.
     */
    @Test
    public void testGetOKList() {
        System.out.println("getOKList");
        GoalApproachingDialog instance = new GoalApproachingDialog();
        List expResult = null;
        List result = instance.getOKList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProcessList method, of class GoalApproachingDialog.
     */
    @Test
    public void testGetProcessList() {
        System.out.println("getProcessList");
        GoalApproachingDialog instance = new GoalApproachingDialog();
        List expResult = null;
        List result = instance.getProcessList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSimilarityRanges method, of class GoalApproachingDialog.
     */
    @Test
    public void testGetSimilarityRanges() {
        System.out.println("getSimilarityRanges");
        GoalApproachingDialog instance = new GoalApproachingDialog();
        List expResult = null;
        List result = instance.getSimilarityRanges();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStatus method, of class GoalApproachingDialog.
     */
    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        GoalApproachingDialog instance = new GoalApproachingDialog();
        String expResult = "";
        String result = instance.getStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTaxonomy method, of class GoalApproachingDialog.
     */
    @Test
    public void testGetTaxonomy() {
        System.out.println("getTaxonomy");
        GoalApproachingDialog instance = new GoalApproachingDialog();
        Taxonomy expResult = null;
        Taxonomy result = instance.getTaxonomy();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of chat method, of class GoalApproachingDialog.
     */
    @Test
    public void testChat() {
        System.out.println("chat");
        GoalApproachingDialog instance = new GoalApproachingDialog();
        Object expResult = null;
        Object result = instance.chat();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of doDialog method, of class GoalApproachingDialog.
     */
    @Test
    public void testDoDialog() {
        System.out.println("doDialog");
        GoalApproachingDialog instance = new GoalApproachingDialog();
        boolean expResult = false;
        boolean result = instance.doDialog();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of selectPossibleSolutionsNearestToGoal method, of class GoalApproachingDialog.
     */
    @Test
    public void testSelectPossibleSolutionsNearestToGoal() {
        System.out.println("selectPossibleSolutionsNearestToGoal");
        GoalApproachingDialog instance = new GoalApproachingDialog();
        Object expResult = null;
        Object result = instance.selectPossibleSolutionsNearestToGoal();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rangeValueDescriptorDialogWith method, of class GoalApproachingDialog.
     */
    @Test
    public void testRangeValueDescriptorDialogWith() {
        System.out.println("rangeValueDescriptorDialogWith");
        RangeDescriptor vd = null;
        Attribute anAttribute = null;
        GoalApproachingDialog instance = new GoalApproachingDialog();
        ReturnValue expResult = null;
        ReturnValue result = instance.rangeValueDescriptorDialogWith(vd, anAttribute);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of valueDescriptorDialogWith method, of class GoalApproachingDialog.
     */
    @Test
    public void testValueDescriptorDialogWith() {
        System.out.println("valueDescriptorDialogWith");
        List<String> displayValues = null;
        List<Object> returnValues = null;
        Attribute anAttribute = null;
        GoalApproachingDialog instance = new GoalApproachingDialog();
        ReturnValue expResult = null;
        ReturnValue result = instance.valueDescriptorDialogWith(displayValues, returnValues, anAttribute);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initializeGoal method, of class GoalApproachingDialog.
     */
    @Test
    public void testInitializeGoal() {
        System.out.println("initializeGoal");
        String aGoal = "";
        Hypothesis aHypothesis = null;
        Taxonomy aTaxonomy = null;
        List<String> simRangesList = null;
        GoalApproachingDialog instance = new GoalApproachingDialog();
        instance.initializeGoal(aGoal, aHypothesis, aTaxonomy, simRangesList);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addOKList method, of class GoalApproachingDialog.
     */
    @Test
    public void testAddOKList() {
        System.out.println("addOKList");
        PossibleSolution aPossibleSolution = null;
        GoalApproachingDialog instance = new GoalApproachingDialog();
        instance.addOKList(aPossibleSolution);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addProcessList method, of class GoalApproachingDialog.
     */
    @Test
    public void testAddProcessList() {
        System.out.println("addProcessList");
        PossibleSolution aPossibleSolution = null;
        GoalApproachingDialog instance = new GoalApproachingDialog();
        instance.addProcessList(aPossibleSolution);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStatus method, of class GoalApproachingDialog.
     */
    @Test
    public void testSetStatus() {
        System.out.println("setStatus");
        String aStatusValue = "";
        GoalApproachingDialog instance = new GoalApproachingDialog();
        instance.setStatus(aStatusValue);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of determineSimilarityFor method, of class GoalApproachingDialog.
     */
    @Test
    public void testDetermineSimilarityFor() {
        System.out.println("determineSimilarityFor");
        Descriptor<Object> aSAVDescriptor = null;
        Taxon aTaxon = null;
        GoalApproachingDialog instance = new GoalApproachingDialog();
        Object expResult = null;
        Object result = instance.determineSimilarityFor(aSAVDescriptor, aTaxon);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isAttributeAlreadyProcessed method, of class GoalApproachingDialog.
     */
    @Test
    public void testIsAttributeAlreadyProcessed() {
        System.out.println("isAttributeAlreadyProcessed");
        Attribute anAttribute = null;
        GoalApproachingDialog instance = new GoalApproachingDialog();
        boolean expResult = false;
        boolean result = instance.isAttributeAlreadyProcessed(anAttribute);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isDescriptorAlreadyProcessed method, of class GoalApproachingDialog.
     */
    @Test
    public void testIsDescriptorAlreadyProcessed() {
        System.out.println("isDescriptorAlreadyProcessed");
        Descriptor<Object> aSAVDescriptor = null;
        GoalApproachingDialog instance = new GoalApproachingDialog();
        boolean expResult = false;
        boolean result = instance.isDescriptorAlreadyProcessed(aSAVDescriptor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}