/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package system.searchAutomata.test.system.searchAutomata;

import onthology.common.Attribute;
import onthology.common.Description;
import onthology.common.Descriptor;
import onthology.common.Structure;
import onthology.taxonomy.Taxon;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import system.searchAutomata.TaxonSISAutomaton;

/**
 *
 * @author pabloq
 */
public class TaxonSISAutomatonTest {

    public TaxonSISAutomatonTest() {
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
     * Test of resetAttribute method, of class TaxonSISAutomaton.
     */
    @Test
    public void testResetAttribute() {
        System.out.println("resetAttribute");
        TaxonSISAutomaton instance = null;
        instance.resetAttribute();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetStructure method, of class TaxonSISAutomaton.
     */
    @Test
    public void testResetStructure() {
        System.out.println("resetStructure");
        TaxonSISAutomaton instance = null;
        instance.resetStructure();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAttribute method, of class TaxonSISAutomaton.
     */
    @Test
    public void testSetAttribute() {
        System.out.println("setAttribute");
        Attribute anAttribute = null;
        TaxonSISAutomaton instance = null;
        instance.setAttribute(anAttribute);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStructure method, of class TaxonSISAutomaton.
     */
    @Test
    public void testSetStructure() {
        System.out.println("setStructure");
        Structure aStructure = null;
        TaxonSISAutomaton instance = null;
        instance.setStructure(aStructure);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAttribute method, of class TaxonSISAutomaton.
     */
    @Test
    public void testGetAttribute() {
        System.out.println("getAttribute");
        TaxonSISAutomaton instance = null;
        Object expResult = null;
        Object result = instance.getAttribute();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSimilarityRanges method, of class TaxonSISAutomaton.
     */
    @Test
    public void testGetSimilarityRanges() {
        System.out.println("getSimilarityRanges");
        TaxonSISAutomaton instance = null;
        Object expResult = null;
        Object result = instance.getSimilarityRanges();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStructure method, of class TaxonSISAutomaton.
     */
    @Test
    public void testGetStructure() {
        System.out.println("getStructure");
        TaxonSISAutomaton instance = null;
        Structure expResult = null;
        Structure result = instance.getStructure();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStructureIndex method, of class TaxonSISAutomaton.
     */
    @Test
    public void testGetStructureIndex() {
        System.out.println("getStructureIndex");
        TaxonSISAutomaton instance = null;
        Object expResult = null;
        Object result = instance.getStructureIndex();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of beginWith method, of class TaxonSISAutomaton.
     */
    @Test
    public void testBeginWith() {
        System.out.println("beginWith");
        Description<Descriptor<Object>> aProblemDescription = null;
        TaxonSISAutomaton instance = null;
        boolean expResult = false;
        boolean result = instance.beginWith(aProblemDescription);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of determineSimilarityFor method, of class TaxonSISAutomaton.
     */
    @Test
    public void testDetermineSimilarityFor() {
        System.out.println("determineSimilarityFor");
        Descriptor<Object> aDescriptor = null;
        Taxon aTaxon = null;
        TaxonSISAutomaton instance = null;
        Taxon expResult = null;
        Taxon result = instance.determineSimilarityFor(aDescriptor, aTaxon);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of retrieveTaxa method, of class TaxonSISAutomaton.
     */
    @Test
    public void testRetrieveTaxa() {
        System.out.println("retrieveTaxa");
        Descriptor<Object> aSAVDescriptor = null;
        TaxonSISAutomaton instance = null;
        boolean expResult = false;
        boolean result = instance.retrieveTaxa(aSAVDescriptor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchAttribute method, of class TaxonSISAutomaton.
     */
    @Test
    public void testSearchAttribute() {
        System.out.println("searchAttribute");
        Descriptor<Object> aSAVDescriptor = null;
        TaxonSISAutomaton instance = null;
        Object expResult = null;
        Object result = instance.searchAttribute(aSAVDescriptor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchValueDescriptors method, of class TaxonSISAutomaton.
     */
    @Test
    public void testSearchValueDescriptors() {
        System.out.println("searchValueDescriptors");
        Descriptor<Object> aDescriptor = null;
        TaxonSISAutomaton instance = null;
        Object expResult = null;
        Object result = instance.searchValueDescriptors(aDescriptor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}