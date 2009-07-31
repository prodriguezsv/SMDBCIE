/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package system.searchAutomata.test.system.searchAutomata;

import java.util.List;
import onthology.CBR.PossibleSolution;
import onthology.common.Description;
import onthology.common.Descriptor;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import redundantDiscriminationNet.Norm;
import redundantDiscriminationNet.RootNorm;
import system.searchAutomata.Alternative;
import system.searchAutomata.SAVCaseDFSAutomaton;
import system.searchAutomata.output.DFSAutomatonOutput;

/**
 *
 * @author pabloq
 */
public class SAVCaseDFSAutomatonTest {

    public SAVCaseDFSAutomatonTest() {
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
     * Test of newOutput method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testNewOutput() {
        System.out.println("newOutput");
        SAVCaseDFSAutomaton instance = null;
        instance.newOutput();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCurrentNorm method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testSetCurrentNorm() {
        System.out.println("setCurrentNorm");
        Norm aNorm = null;
        SAVCaseDFSAutomaton instance = null;
        instance.setCurrentNorm(aNorm);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addJustification method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testAddJustification() {
        System.out.println("addJustification");
        Descriptor<Object> aJustificationElement = null;
        SAVCaseDFSAutomaton instance = null;
        instance.addJustification(aJustificationElement);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNextLevel method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testSetNextLevel() {
        System.out.println("setNextLevel");
        SAVCaseDFSAutomaton instance = null;
        instance.setNextLevel();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPreviousLevel method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testSetPreviousLevel() {
        System.out.println("setPreviousLevel");
        SAVCaseDFSAutomaton instance = null;
        instance.setPreviousLevel();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetLevel method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testResetLevel() {
        System.out.println("resetLevel");
        SAVCaseDFSAutomaton instance = null;
        instance.resetLevel();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStatus method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testSetStatus() {
        System.out.println("setStatus");
        String aStatusValue = "";
        SAVCaseDFSAutomaton instance = null;
        instance.setStatus(aStatusValue);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStopLevel method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testSetStopLevel() {
        System.out.println("setStopLevel");
        int aLevelNumber = 0;
        SAVCaseDFSAutomaton instance = null;
        instance.setStopLevel(aLevelNumber);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTConfirmedDescription method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testSetTConfirmedDescription() {
        System.out.println("setTConfirmedDescription");
        Descriptor<Object> aSAVDescriptor = null;
        SAVCaseDFSAutomaton instance = null;
        instance.setTConfirmedDescription(aSAVDescriptor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTDoubtfulDescription method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testSetTDoubtfulDescription() {
        System.out.println("setTDoubtfulDescription");
        Descriptor<Object> aSAVDescriptor = null;
        SAVCaseDFSAutomaton instance = null;
        instance.setTDoubtfulDescription(aSAVDescriptor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTSolutionDescription method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testSetTSolutionDescription() {
        System.out.println("setTSolutionDescription");
        Descriptor<Object> aSAVDescriptor = null;
        SAVCaseDFSAutomaton instance = null;
        instance.setTSolutionDescription(aSAVDescriptor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTUnconfirmedDescription method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testSetTUnconfirmedDescription() {
        System.out.println("setTUnconfirmedDescription");
        Descriptor<Object> aSAVDescriptor = null;
        SAVCaseDFSAutomaton instance = null;
        instance.setTUnconfirmedDescription(aSAVDescriptor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTUnmatchedDescription method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testSetTUnmatchedDescription() {
        System.out.println("setTUnmatchedDescription");
        Descriptor<Object> aSAVDescriptor = null;
        SAVCaseDFSAutomaton instance = null;
        instance.setTUnmatchedDescription(aSAVDescriptor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentLevel method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testGetCurrentLevel() {
        System.out.println("getCurrentLevel");
        SAVCaseDFSAutomaton instance = null;
        int expResult = 0;
        int result = instance.getCurrentLevel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentNorm method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testGetCurrentNorm() {
        System.out.println("getCurrentNorm");
        SAVCaseDFSAutomaton instance = null;
        Object expResult = null;
        Object result = instance.getCurrentNorm();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getJustification method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testGetJustification() {
        System.out.println("getJustification");
        SAVCaseDFSAutomaton instance = null;
        Description expResult = null;
        Description result = instance.getJustification();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNetRoot method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testGetNetRoot() {
        System.out.println("getNetRoot");
        SAVCaseDFSAutomaton instance = null;
        RootNorm expResult = null;
        RootNorm result = instance.getNetRoot();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRootLevel method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testGetRootLevel() {
        System.out.println("getRootLevel");
        SAVCaseDFSAutomaton instance = null;
        int expResult = 0;
        int result = instance.getRootLevel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSearchOutput method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testGetSearchOutput() {
        System.out.println("getSearchOutput");
        SAVCaseDFSAutomaton instance = null;
        DFSAutomatonOutput expResult = null;
        DFSAutomatonOutput result = instance.getSearchOutput();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStatus method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        SAVCaseDFSAutomaton instance = null;
        String expResult = "";
        String result = instance.getStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStopLevel method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testGetStopLevel() {
        System.out.println("getStopLevel");
        SAVCaseDFSAutomaton instance = null;
        int expResult = 0;
        int result = instance.getStopLevel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTConfirmedDescription method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testGetTConfirmedDescription() {
        System.out.println("getTConfirmedDescription");
        SAVCaseDFSAutomaton instance = null;
        Description expResult = null;
        Description result = instance.getTConfirmedDescription();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTDoubtfulDescription method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testGetTDoubtfulDescription() {
        System.out.println("getTDoubtfulDescription");
        SAVCaseDFSAutomaton instance = null;
        Description expResult = null;
        Description result = instance.getTDoubtfulDescription();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTSolutionDescription method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testGetTSolutionDescription() {
        System.out.println("getTSolutionDescription");
        SAVCaseDFSAutomaton instance = null;
        Description expResult = null;
        Description result = instance.getTSolutionDescription();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTUnconfirmedDescription method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testGetTUnconfirmedDescription() {
        System.out.println("getTUnconfirmedDescription");
        SAVCaseDFSAutomaton instance = null;
        Description expResult = null;
        Description result = instance.getTUnconfirmedDescription();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTUnmatchedDescription method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testGetTUnmatchedDescription() {
        System.out.println("getTUnmatchedDescription");
        SAVCaseDFSAutomaton instance = null;
        Description expResult = null;
        Description result = instance.getTUnmatchedDescription();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of backtrack method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testBacktrack() {
        System.out.println("backtrack");
        SAVCaseDFSAutomaton instance = null;
        String expResult = "";
        String result = instance.backtrack();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of processPreviousNorm method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testProcessPreviousNorm() {
        System.out.println("processPreviousNorm");
        SAVCaseDFSAutomaton instance = null;
        String expResult = "";
        String result = instance.processPreviousNorm();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeSAVDescriptorFromTConfirmedDesc method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testRemoveSAVDescriptorFromTConfirmedDesc() {
        System.out.println("removeSAVDescriptorFromTConfirmedDesc");
        Norm aNorm = null;
        SAVCaseDFSAutomaton instance = null;
        Object expResult = null;
        Object result = instance.removeSAVDescriptorFromTConfirmedDesc(aNorm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of verifyIndices method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testVerifyIndices() {
        System.out.println("verifyIndices");
        SAVCaseDFSAutomaton instance = null;
        Object expResult = null;
        Object result = instance.verifyIndices();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchForCasesUnderCurrNormUsing method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testSearchForCasesUnderCurrNormUsing() {
        System.out.println("searchForCasesUnderCurrNormUsing");
        Description<Descriptor<Object>> aProblemDescription = null;
        List<PossibleSolution> aPossibleSolutionsList = null;
        SAVCaseDFSAutomaton instance = null;
        Object expResult = null;
        Object result = instance.searchForCasesUnderCurrNormUsing(aProblemDescription, aPossibleSolutionsList);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchForCasesUnderRootUsing method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testSearchForCasesUnderRootUsing() {
        System.out.println("searchForCasesUnderRootUsing");
        Description<Descriptor<Object>> aProblemDescription = null;
        List<PossibleSolution> aPossibleSolutionsList = null;
        SAVCaseDFSAutomaton instance = null;
        Object expResult = null;
        Object result = instance.searchForCasesUnderRootUsing(aProblemDescription, aPossibleSolutionsList);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchForCasesUsing method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testSearchForCasesUsing() {
        System.out.println("searchForCasesUsing");
        Description<Descriptor<Object>> aProblemDescription = null;
        SAVCaseDFSAutomaton instance = null;
        Object expResult = null;
        Object result = instance.searchForCasesUsing(aProblemDescription);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of newSearchWith method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testNewSearchWith() {
        System.out.println("newSearchWith");
        Description<Descriptor<Object>> anOldProblemDescription = null;
        SAVCaseDFSAutomaton instance = null;
        instance.newSearchWith(anOldProblemDescription);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of indexDialog method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testIndexDialog() {
        System.out.println("indexDialog");
        SAVCaseDFSAutomaton instance = null;
        String expResult = "";
        String result = instance.indexDialog();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of presentChoices method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testPresentChoices() {
        System.out.println("presentChoices");
        List<Alternative> alternativeList = null;
        SAVCaseDFSAutomaton instance = null;
        String expResult = "";
        String result = instance.presentChoices(alternativeList);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of processNextNormWith method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testProcessNextNormWith() {
        System.out.println("processNextNormWith");
        Descriptor<Object> aSAVDescriptor = null;
        Norm aNorm = null;
        SAVCaseDFSAutomaton instance = null;
        instance.processNextNormWith(aSAVDescriptor, aNorm);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchCasesDialogUsing method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testSearchCasesDialogUsing() {
        System.out.println("searchCasesDialogUsing");
        Descriptor<Object> aSAVDescriptor = null;
        List<PossibleSolution> aPossibleSolutionsList = null;
        SAVCaseDFSAutomaton instance = null;
        String expResult = "";
        String result = instance.searchCasesDialogUsing(aSAVDescriptor, aPossibleSolutionsList);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of beginWith method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testBeginWith() {
        System.out.println("beginWith");
        Description<Descriptor<Object>> aProblemDescription = null;
        SAVCaseDFSAutomaton instance = null;
        Object expResult = null;
        Object result = instance.beginWith(aProblemDescription);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of retrieveCasesUnderCurrNorm method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testRetrieveCasesUnderCurrNorm() {
        System.out.println("retrieveCasesUnderCurrNorm");
        SAVCaseDFSAutomaton instance = null;
        Object expResult = null;
        Object result = instance.retrieveCasesUnderCurrNorm();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchForNormWith method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testSearchForNormWith() {
        System.out.println("searchForNormWith");
        Description<Descriptor<Object>> aProblemDescription = null;
        SAVCaseDFSAutomaton instance = null;
        Object expResult = null;
        Object result = instance.searchForNormWith(aProblemDescription);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateNormSearchWith method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testUpdateNormSearchWith() {
        System.out.println("updateNormSearchWith");
        Descriptor<Object> aSAVDescriptor = null;
        Norm aNewNorm = null;
        SAVCaseDFSAutomaton instance = null;
        instance.updateNormSearchWith(aSAVDescriptor, aNewNorm);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of includes method, of class SAVCaseDFSAutomaton.
     */
    @Test
    public void testIncludes() {
        System.out.println("includes");
        Descriptor<Object> aSAVDescriptor = null;
        Description<Descriptor<Object>> aDescription = null;
        SAVCaseDFSAutomaton instance = null;
        Object expResult = null;
        Object result = instance.includes(aSAVDescriptor, aDescription);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}