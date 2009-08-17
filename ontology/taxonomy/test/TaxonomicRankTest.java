/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ontology.taxonomy.test;

import ontology.taxonomy.TaxonomicRank;
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
public class TaxonomicRankTest {
    TaxonomicRank root,family,genus,species;

    public TaxonomicRankTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {

    root = TaxonomicRank.ROOT;
    family = TaxonomicRank.FAMILY;
    genus = TaxonomicRank.GENUS;
    species = TaxonomicRank.SPECIES;

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getIndex method, of class TaxonomicRank.
     */
    @Test
    public void testGetIndex() {
        System.out.println("getIndex");

        /*
         * Get correct level
         */
        assertEquals(0,TaxonomicRank.getIndex(root));
        assertEquals(1,TaxonomicRank.getIndex(family));
        assertEquals(2,TaxonomicRank.getIndex(genus));
        assertEquals(3,TaxonomicRank.getIndex(species));
        /*
         * null level if null
         */
        assertEquals(-1,TaxonomicRank.getIndex(null));
    }

}