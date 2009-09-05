/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package system.test;

import java.util.ArrayList;
import java.util.List;

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
import system.Hypothesis;
import system.PossibleSolution;
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
         * Must be sorted
         */

        assertSame("00acuerpo",instance.getUnmatchedDescription().get(0).getStructure());
    }

    /**
     * Test of copyToJustificationFrom method, of class Hypothesis.
     */
    @Test
    public void testCopyToJustificationFrom() {
        java.lang.System.out.println("copyToJustificationFrom");
        List<Descriptor> aJustificationDescription = new ArrayList<Descriptor>();
        Hypothesis instance = new Hypothesis();
        aJustificationDescription.add(new SSCharacterDescriptor("pie","coloracion","gris_oscuro_casi_negro"));
        aJustificationDescription.add(new SSCharacterDescriptor("pie","coloracion","crema"));
        aJustificationDescription.add(new SSCharacterDescriptor("branquia","posicion_durante_desplazamiento","hacia_atras"));

        assertFalse(instance.copyToJustificationFrom(null));
        assertTrue(instance.copyToJustificationFrom(aJustificationDescription));
        assertSame(3,instance.getJustification().size());
    }

    /**
     * Test of copyToUnmatchedDescriptionFrom method, of class Hypothesis.
     */
    @Test
    public void testCopyToUnmatchedDescriptionFrom() {
        java.lang.System.out.println("copyToUnmatchedDescriptionFrom");
        Hypothesis instance = new Hypothesis();
        List<Descriptor> anUnmatchedDescription = new ArrayList<Descriptor>();
        anUnmatchedDescription.add(new SSCharacterDescriptor("pie","coloracion","gris_oscuro_casi_negro"));
        anUnmatchedDescription.add(new SSCharacterDescriptor("pie","coloracion","crema"));
        anUnmatchedDescription.add(new SSCharacterDescriptor("branquia","posicion_durante_desplazamiento","hacia_atras"));

        assertFalse(instance.copyToUnmatchedDescriptionFrom(null));
        assertTrue(instance.copyToUnmatchedDescriptionFrom(anUnmatchedDescription));
        assertSame(3,instance.getUnmatchedDescription().size());
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
        assertFalse(instance.contains(new SSCharacterDescriptor("pie","coloracion","gris_oscuro_casi_negro"), aDescription));
        instance.setUnmatchedDescription(aDescription);
        assertTrue(instance.contains(new SSCharacterDescriptor("pie","coloracion","gris_oscuro_casi_negro"), aDescription));
        assertTrue(instance.contains(new SSCharacterDescriptor("pie","coloracion","crema"), aDescription));
        /*
         * The first name on the list is greater, so error.
         */
        assertFalse(instance.contains(new SSCharacterDescriptor("branquia","posicion_durante_desplazamiento","hacia_atras"), aDescription));
    }

    /**
     * Test of areThereContradictions method, of class Hypothesis.
     */
    @Test
    public void testAreThereContradictions() {
        java.lang.System.out.println("areThereContradictions");
        Hypothesis instance = new Hypothesis();
        List<Descriptor> aDescription = new ArrayList<Descriptor>();
        aDescription.add(new SSCharacterDescriptor("pie","coloracion","gris_oscuro_casi_negro"));
        aDescription.add(new SSCharacterDescriptor("pie","coloracion","crema"));
        aDescription.add(new SSCharacterDescriptor("branquia","posicion_durante_desplazamiento","hacia_atras"));

        assertTrue(instance.areThereContradictions(new SSCharacterDescriptor("pie","coloracion","gris_oscuro_casi_negro"), aDescription));
        assertTrue(instance.areThereContradictions(new SSCharacterDescriptor("pie","coloracion","crema"), aDescription));
        /*
         * Contradiccion, it doesn't exist
         */
        assertFalse(instance.areThereContradictions(new SSCharacterDescriptor("weird structure","posicion_durante_desplazamiento","hacia_atras"), aDescription));
    }

}