/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ontology.CBR.test;

import ontology.CBR.Hypothesis;
import ontology.CBR.PossibleSolution;
import ontology.common.Description;
import ontology.common.Descriptor;
import ontology.common.SSCharacterDescriptor;
import ontology.taxonomy.Taxon;
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
public class HypothesisTest {

    public HypothesisTest() {
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
     * Test of addPossibleSolutions method, of class Hypothesis.
     */
    @Test
    public void testAddPossibleSolutions() {
        java.lang.System.out.println("addPossibleSolutions");
        Hypothesis instance = new Hypothesis();
        PossibleSolution aPossibleSolution = new PossibleSolution();

        assertTrue(instance.addPossibleSolution(aPossibleSolution));

        assertSame(1,instance.getPossibleSolutions().size());

        aPossibleSolution.setSolution(new Taxon(TaxonomicRank.SPECIES, "Chromodoris sphoni"));

        assertTrue(instance.addPossibleSolution(aPossibleSolution));

        assertSame(2,instance.getPossibleSolutions().size());

        aPossibleSolution = new PossibleSolution();
        aPossibleSolution.setSolution(new Taxon(TaxonomicRank.SPECIES, "Chromodoris sphoni"));
        aPossibleSolution.setSolution(new Taxon(TaxonomicRank.SPECIES, "Chromodoris clenchi"));

        assertTrue(instance.addPossibleSolution(aPossibleSolution));

        assertSame(3,instance.getPossibleSolutions().size());


    }

    /**
     * Test of addUnmatchedDescription method, of class Hypothesis.
     */
    @Test
    public void testAddUnmatchedDescription() {
        java.lang.System.out.println("addUnmatchedDescription");
        Hypothesis instance = new Hypothesis();

        /*
         * just true when unique descriptor
         */
        assertTrue(instance.addToUnmatchedDescription(new SSCharacterDescriptor("01cuerpo","forma","alargado")));
        assertFalse(instance.addToUnmatchedDescription(new SSCharacterDescriptor("01cuerpo","forma","alargado")));
        assertTrue(instance.addToUnmatchedDescription(new SSCharacterDescriptor("00acuerpo","forma","alargado")));

        /*
         * Must be sorted (OJO)
         */

        assertSame("01acuerpo",((Descriptor)instance.getUnmatchedDescription().getDescriptors().get(0)).getStructure());
    }

    /**
     * Test of copyToJustificationFrom method, of class Hypothesis.
     */
    @Test
    public void testCopyToJustificationFrom() {
        java.lang.System.out.println("copyToJustificationFrom");
        Description aJustificationDescription = new Description();
        Hypothesis instance = new Hypothesis();
        aJustificationDescription.addDescriptors(new SSCharacterDescriptor("pie","coloracion","gris_oscuro_casi_negro"));
        aJustificationDescription.addDescriptors(new SSCharacterDescriptor("pie","coloracion","crema"));
        aJustificationDescription.addDescriptors(new SSCharacterDescriptor("branquia","posicion_durante_desplazamiento","hacia_atras"));

        assertFalse(instance.addAllToJustification(null));
        assertTrue(instance.addAllToJustification(aJustificationDescription));
        assertSame(2,instance.getJustification().getDescriptors().size());
    }

    /**
     * Test of copyToUnmatchedDescriptionFrom method, of class Hypothesis.
     */
    @Test
    public void testCopyToUnmatchedDescriptionFrom() {
        java.lang.System.out.println("copyToUnmatchedDescriptionFrom");
        Hypothesis instance = new Hypothesis();
        Description anUnmatchedDescription = new Description();
        anUnmatchedDescription.addDescriptors(new SSCharacterDescriptor("pie","coloracion","gris_oscuro_casi_negro"));
        anUnmatchedDescription.addDescriptors(new SSCharacterDescriptor("pie","coloracion","crema"));
        anUnmatchedDescription.addDescriptors(new SSCharacterDescriptor("branquia","posicion_durante_desplazamiento","hacia_atras"));

        assertFalse(instance.addAllToUnmatchedDescription(null));
        assertTrue(instance.addAllToUnmatchedDescription(anUnmatchedDescription));
        assertSame(2,instance.getUnmatchedDescription().getDescriptors().size());
    }

//    /**
//     * Test of problemDescriptionFor method, of class Hypothesis.
//     */
//    @Test
//    public void testProblemDescriptionFor() {
//        java.lang.System.out.println("problemDescriptionFor");
//        String aTaxonomicGroupName = "";
//        Hypothesis instance = new Hypothesis();
//        List expResult = null;
//        List result = instance.problemDescriptionFor(aTaxonomicGroupName);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of contains method, of class Hypothesis.
     */
    @Test
    public void testContains() {
        java.lang.System.out.println("contains");
        Hypothesis instance = new Hypothesis();
        Description aDescription = new Description();
        aDescription.addToConcreteDescription(new SSCharacterDescriptor("pie","coloracion","gris_oscuro_casi_negro"));
        aDescription.addToConcreteDescription(new SSCharacterDescriptor("pie","coloracion","crema"));
        aDescription.addToConcreteDescription(new SSCharacterDescriptor("branquia","posicion_durante_desplazamiento","hacia_atras"));
        /*
         * Must be on list on the system
         */
        assertFalse(instance.getUnmatchedDescription().getDescriptors().contains(new SSCharacterDescriptor("pie","coloracion","gris_oscuro_casi_negro")));
        assertFalse(instance.getDescription().getDescriptors().contains(new SSCharacterDescriptor("pie","coloracion","gris_oscuro_casi_negro")));
        assertFalse(instance.getJustification().getDescriptors().contains(new SSCharacterDescriptor("pie","coloracion","gris_oscuro_casi_negro")));
        
        instance.setUnmatchedDescription(aDescription);
        instance.setDescription(aDescription);
        instance.setJustification(aDescription);
        
        assertTrue(instance.getUnmatchedDescription().getDescriptors().contains(new SSCharacterDescriptor("pie","coloracion","gris_oscuro_casi_negro")));
        assertTrue(instance.getDescription().getDescriptors().contains(new SSCharacterDescriptor("pie","coloracion","gris_oscuro_casi_negro")));
        assertTrue(instance.getJustification().getDescriptors().contains(new SSCharacterDescriptor("pie","coloracion","gris_oscuro_casi_negro")));
    }

    /**
     * Test of areThereContradictions method, of class Hypothesis.
     */
    @Test
    public void testAreThereContradictions() {
        java.lang.System.out.println("areThereContradictions");
        Hypothesis instance = new Hypothesis();
        Description aDescription = new Description();
        aDescription.addDescriptors(new SSCharacterDescriptor("pie","coloracion","gris_oscuro_casi_negro"));
        aDescription.addDescriptors(new SSCharacterDescriptor("pie","coloracion","crema"));
        aDescription.addDescriptors(new SSCharacterDescriptor("branquia","posicion_durante_desplazamiento","hacia_atras"));

        instance.setUnmatchedDescription(aDescription);
        
        assertTrue(instance.getUnmatchedDescription().areThereContradictions(new SSCharacterDescriptor("pie","coloracion","gris_oscuro_casi_negro")));
        assertTrue(instance.getUnmatchedDescription().areThereContradictions(new SSCharacterDescriptor("pie","coloracion","crema")));
        /*
         * Contradiccion, it doesn't exist
         */
        assertFalse(instance.getUnmatchedDescription().areThereContradictions(new SSCharacterDescriptor("weird structure","posicion_durante_desplazamiento","hacia_atras")));
    }

}