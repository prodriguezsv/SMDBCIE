/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package system.searchAutomata.test;

import java.util.List;
import ontology.common.Descriptor;
import ontology.taxonomy.Taxon;
import ontology.taxonomy.Taxonomy;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import system.searchAutomata.TaxonSISAutomaton;
import static org.junit.Assert.*;

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
     * Test of beginWith method, of class TaxonSISAutomaton.
     */
    @Test
    public void testBeginWith() {
        System.out.println("beginWith");
        List<Descriptor<Object>> aProblemDescription = null;
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
        Taxonomy aTaxonomy = null;
        TaxonSISAutomaton instance = null;
        boolean expResult = false;
        boolean result = instance.retrieveTaxa(aSAVDescriptor, aTaxonomy);
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