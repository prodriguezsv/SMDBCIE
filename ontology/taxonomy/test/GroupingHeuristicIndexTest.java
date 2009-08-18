/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ontology.taxonomy.test;

import ontology.common.GroupingHeuristic;
import ontology.taxonomy.GroupingHeuristicIndex;
import ontology.taxonomy.Taxon;
import ontology.taxonomy.TaxonomicRank;
import ontology.values.RangeValue;
import ontology.values.SingleValue;
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
public class GroupingHeuristicIndexTest {
    Taxon taxon;
    GroupingHeuristicIndex gHeuIdx,gHeuIdx2;
    GroupingHeuristic aGroupingHeuristic,aGroupingHeuristic2,aGroupingHeuristic3;

    public GroupingHeuristicIndexTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {

        taxon = new Taxon();
        taxon.setName("Chromodorididae");
        taxon.setLevel(TaxonomicRank.FAMILY);
        
        gHeuIdx = new GroupingHeuristicIndex();
         aGroupingHeuristic = new GroupingHeuristic();
        aGroupingHeuristic.setName("alimentacion");
        aGroupingHeuristic.setWeight(1.0);

        SingleValue aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("esponjas");
        aSingleDescriptor.setWeight(1.0);
        aGroupingHeuristic.getValues().addValueDescriptorWithUniqueValue(aSingleDescriptor, TaxonomicRank.FAMILY);

        gHeuIdx.addGroupingHeuristic(aGroupingHeuristic);

        gHeuIdx2 = new GroupingHeuristicIndex();
        
        aGroupingHeuristic2 = new GroupingHeuristic();
        aGroupingHeuristic2.setName("profundidad_donde_se_encuentra");
        aGroupingHeuristic2.setWeight(1.0);


        RangeValue aRangeDescriptor = new RangeValue();
        aRangeDescriptor.setLowerBound(0.0);
        aRangeDescriptor.setUpperBound(20.0);
        aGroupingHeuristic2.getValues().addValueDescriptorWithUniqueValue(aRangeDescriptor, TaxonomicRank.FAMILY);

        gHeuIdx2.addGroupingHeuristic(aGroupingHeuristic2);

        aGroupingHeuristic3 = new GroupingHeuristic();
        aGroupingHeuristic3.setName("medio_de_preservacion_tenido");
        aGroupingHeuristic3.setWeight(1.0);

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("azul_marino");
        aSingleDescriptor.setWeight(1.0);
        aGroupingHeuristic3.getValues().addValueDescriptorWithUniqueValue(aSingleDescriptor, TaxonomicRank.FAMILY);

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("celeste");
        aSingleDescriptor.setWeight(1.0);
        aGroupingHeuristic3.getValues().addValueDescriptorWithUniqueValue(aSingleDescriptor, TaxonomicRank.FAMILY);

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("amarillento");
        aSingleDescriptor.setWeight(0.2);
        aGroupingHeuristic3.getValues().addValueDescriptorWithUniqueValue(aSingleDescriptor, TaxonomicRank.FAMILY);

        gHeuIdx2.addGroupingHeuristic(aGroupingHeuristic3);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getGroupingHeuristic method, of class GroupingHeuristicIndex.
     */
    @Test
    public void testGetGroupingHeuristic() {
        System.out.println("getGroupingHeuristic");
        /*
         * Get with name
         */
        assertEquals(aGroupingHeuristic,gHeuIdx.getGroupingHeuristic("alimentacion"));
        assertEquals(aGroupingHeuristic3,gHeuIdx2.getGroupingHeuristic("medio_de_preservacion_tenido"));
        assertEquals(aGroupingHeuristic2,gHeuIdx2.getGroupingHeuristic("profundidad_donde_se_encuentra"));
        /*
         * Get null when it doesn't exist
         */
        assertNull(gHeuIdx2.getGroupingHeuristic("no existe"));
        assertNull(gHeuIdx2.getGroupingHeuristic(null));
    }

    /**
     * Test of contains method, of class GroupingHeuristicIndex.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        assertTrue(gHeuIdx2.contains("medio_de_preservacion_tenido"));
        assertTrue(gHeuIdx2.contains("profundidad_donde_se_encuentra"));
        assertFalse(gHeuIdx.contains("medio_de_preservacion_tenido"));
        assertFalse(gHeuIdx.contains("no existe"));
        assertFalse(gHeuIdx.contains(null));
    }

    /**
     * Test of isRangesConsistent method, of class GroupingHeuristicIndex.
     */
    @Test
    public void testIsRangesConsistent() {
        System.out.println("isRangesConsistent");
        Taxon aParentTaxon = null;
        GroupingHeuristicIndex instance = new GroupingHeuristicIndex();
        boolean expResult = false;
        boolean result = instance.isRangesConsistent(aParentTaxon);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}