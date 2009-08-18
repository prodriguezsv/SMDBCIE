/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ontology.taxonomy.test;

import java.util.List;
import ontology.taxonomy.GroupingHeuristicIndex;
import ontology.taxonomy.StructureIndex;
import ontology.taxonomy.Taxon;
import ontology.taxonomy.TaxonomicRank;
import ontology.taxonomy.Taxonomy;
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
public class TaxonomyTest {

    public TaxonomyTest() {
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
     * Test of getGroupingHeuristicIndex method, of class Taxonomy.
     */
    @Test
    public void testGetGroupingHeuristicIndex() {
        System.out.println("getGroupingHeuristicIndex");
        Taxonomy instance = new Taxonomy();
        GroupingHeuristicIndex expResult = null;
        GroupingHeuristicIndex result = instance.getGroupingHeuristicIndex();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }



    /**
     * Test of getTaxonListFromLevelIndex method, of class Taxonomy.
     */
    @Test
    public void testGetTaxonListFromLevelIndex() {
        System.out.println("getTaxonListFromLevelIndex");
        TaxonomicRank aLevel = null;
        Taxonomy instance = new Taxonomy();
        List expResult = null;
        List result = instance.getTaxonListFromLevelIndex(aLevel);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTaxonFromLevelIndex method, of class Taxonomy.
     */
    @Test
    public void testGetTaxonFromLevelIndex_String_TaxonomicRank() {
        System.out.println("getTaxonFromLevelIndex");
        String aTaxonName = "";
        TaxonomicRank aLevel = null;
        Taxonomy instance = new Taxonomy();
        Taxon expResult = null;
        Taxon result = instance.getTaxonFromLevelIndex(aTaxonName, aLevel);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTaxonFromLevelIndex method, of class Taxonomy.
     */
    @Test
    public void testGetTaxonFromLevelIndex_String() {
        System.out.println("getTaxonFromLevelIndex");
        String aTaxonName = "";
        Taxonomy instance = new Taxonomy();
        Taxon expResult = null;
        Taxon result = instance.getTaxonFromLevelIndex(aTaxonName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addTaxon method, of class Taxonomy.
     */
    @Test
    public void testAddTaxon() {
        System.out.println("addTaxon");
        Taxon aNewTaxon = null;
        Taxon aParentTaxon = null;
        Taxonomy instance = new Taxonomy();
        boolean expResult = false;
        boolean result = instance.addTaxon(aNewTaxon, aParentTaxon);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of areTaxonomicDependenciesOK method, of class Taxonomy.
     */
    @Test
    public void testAreTaxonomicDependenciesOK() {
        System.out.println("areTaxonomicDependenciesOK");
        Taxon aParentTaxon = null;
        Taxon aSuccessorTaxon = null;
        Taxonomy instance = new Taxonomy();
        boolean expResult = false;
        boolean result = instance.areTaxonomicDependenciesOK(aParentTaxon, aSuccessorTaxon);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of contains method, of class Taxonomy.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        Taxon aTaxon = null;
        Taxonomy instance = new Taxonomy();
        boolean expResult = false;
        boolean result = instance.contains(aTaxon);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of includes_old method, of class Taxonomy.
     */
    @Test
    public void testIncludes_old() {
        System.out.println("includes_old");
        Taxon aTaxon = null;
        Taxonomy instance = new Taxonomy();
        boolean expResult = false;
        boolean result = instance.includes_old(aTaxon);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}