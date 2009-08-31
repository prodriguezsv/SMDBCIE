/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package system.test;

import java.util.ArrayList;
import java.util.List;
import ontology.common.CharacterDescriptor;
import ontology.common.Modifier;
import ontology.taxonomy.Taxon;
import ontology.taxonomy.TaxonomicRank;
import ontology.taxonomy.Taxonomy;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import system.Hypothesis;
import system.PossibleSolution;
import system.PossibleSolutionEvaluator;
import static org.junit.Assert.*;

/**
 *
 * @author pabloq
 */
public class PossibleSolutionEvaluatorTest {
    Taxon rootTaxon,taxon1,taxon2,taxon3,taxon4,taxon5,taxon6,taxon7;
    Taxonomy taxonomy;
    public PossibleSolutionEvaluatorTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {

        rootTaxon = new Taxon();
        rootTaxon.setName(null);
        rootTaxon.setLevel(TaxonomicRank.ROOT);
        taxon1 = new Taxon();
        taxon1.setName("Chromodorididae");
        taxon1.setLevel(TaxonomicRank.FAMILY);
        taxon1.addToDescription(new CharacterDescriptor<Object>("cuerpo","forma","alargado"),
                                    new Modifier(1.0,1.0,0.8));

        taxon1.addToDescription(new CharacterDescriptor<Object>("cuerpo","forma","ovalado"),
                                    new Modifier(1.0,1.0,0.1));

        taxon2 = new Taxon();
        taxon2.setName("Chromodorididae fake");
        taxon2.setLevel(TaxonomicRank.FAMILY);
        taxon2.addToDescription(new CharacterDescriptor<Object>("cuerpo1","forma","ovalado"),
                                    new Modifier(1.0,1.0,1.0));
        taxon2.addToDescription(new CharacterDescriptor<Object>("cuerpo2","forma","ovalado"),
                                    new Modifier(1.0,1.0,1.0));
        taxon3 = new Taxon();
        taxon3.setName("Chromodoris");
        taxon3.setLevel(TaxonomicRank.GENUS);
        taxon3.addToDescription(new CharacterDescriptor<Object>("cuerpo3","forma","ovalado"),
                                    new Modifier(1.0,1.0,1.0));
        taxon4 = new Taxon();
        taxon4.setName("Chromodoris fake");
        taxon4.setLevel(TaxonomicRank.GENUS);
        taxon4.addToDescription(new CharacterDescriptor<Object>("cuerpo4","forma","ovalado"),
                                    new Modifier(1.0,1.0,1.0));
        taxon5 = new Taxon();
        taxon5.setName("Chromodoris_sphoni");
        taxon5.setLevel(TaxonomicRank.SPECIES);
        taxon5.addToDescription(new CharacterDescriptor<Object>("cuerpo5","forma","ovalado"),
                                    new Modifier(1.0,1.0,1.0));
        taxon6 = new Taxon();
        taxon6.setName("Chromodoris_clenchi");
        taxon6.setLevel(TaxonomicRank.SPECIES);
        taxon6.addToDescription(new CharacterDescriptor<Object>("cuerpo6","forma","ovalado"),
                                    new Modifier(1.0,1.0,1.0));
        taxon7 = new Taxon();
        taxon7.setName("Chromodoris_kempfi");
        taxon7.setLevel(TaxonomicRank.SPECIES);
        taxon7.addToDescription(new CharacterDescriptor<Object>("cuerpo7","forma","ovalado"),
                                    new Modifier(1.0,1.0,1.0));


        taxonomy = new Taxonomy();

        taxon1.setPredecessor(rootTaxon);
        assertTrue(taxonomy.addTaxon(taxon1, rootTaxon));
        assertTrue(taxonomy.addTaxon(taxon2, rootTaxon));
        assertTrue(taxonomy.addTaxon(taxon3, taxon1));
        assertTrue(taxonomy.addTaxon(taxon4, taxon2));
        assertTrue(taxonomy.addTaxon(taxon5, taxon3));
        assertTrue(taxonomy.addTaxon(taxon6, taxon3));
        assertTrue(taxonomy.addTaxon(taxon7, taxon4));

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of evaluate method, of class PossibleSolutionEvaluator.
     */
    @Test
    public void testEvaluate_3args() {
        java.lang.System.out.println("evaluate");
        PossibleSolution aPossibleSolution1,aPossibleSolution2,aPossibleSolution3,aPossibleSolution4;
        String aPointAccumulatingScheme = "+";
        PossibleSolutionEvaluator instance = new PossibleSolutionEvaluator(new ArrayList<Hypothesis>(), new ArrayList<Hypothesis>(), new ArrayList<Hypothesis>(),
			new ArrayList<Hypothesis>(), taxonomy);
        /*
         * taxonomy
         * root
         *      taxon1(FAMILY)
         *                      taxon3(GENUS)
         *                                      taxon5(SPECIES)
         *                                      taxon6(SPECIES)
         *      taxon2(FAMILY)
         *                      taxon4(GENUS)
         *                                      taxon7(SPECIES)
         */

        /*
         * Case add points to  PossibleSolution1 and PossibleSolution2, taxon1 present on aCompConflictSet and taxon3 has is succesor of taxon1
         */

        List<Hypothesis> aConflictSet = new ArrayList<Hypothesis>();
        
        Hypothesis aHypothesis = new Hypothesis();
        aPossibleSolution1 = new PossibleSolution ();
        assertTrue(aPossibleSolution1.setSolution(taxon1));
        aHypothesis.addPossibleSolutions(aPossibleSolution1);
        aConflictSet.add(aHypothesis);

        aHypothesis = new Hypothesis();
        aPossibleSolution2 = new PossibleSolution ();
        assertTrue(aPossibleSolution2.setSolution(taxon3));
        aHypothesis.addPossibleSolutions(aPossibleSolution2);
        aConflictSet.add(aHypothesis);

        List<Hypothesis> aCompConflictSet = new ArrayList<Hypothesis>();

        aHypothesis = new Hypothesis();
        aPossibleSolution3 = new PossibleSolution ();
        assertTrue(aPossibleSolution3.setSolution(taxon1));
        aHypothesis.addPossibleSolutions(aPossibleSolution3);
        aCompConflictSet.add(aHypothesis);

        aHypothesis = new Hypothesis();
        aPossibleSolution4 = new PossibleSolution ();
        assertTrue(aPossibleSolution4.setSolution(taxon4));
        aHypothesis.addPossibleSolutions(aPossibleSolution4);
        aCompConflictSet.add(aHypothesis);


        assertEquals(0.0,aPossibleSolution1.getPoints(),0.0);
        assertEquals(0.0,aPossibleSolution2.getPoints(),0.0);

        instance.evaluate(aConflictSet, aCompConflictSet, aPointAccumulatingScheme);

        assertEquals(1.0,aPossibleSolution1.getPoints(),0.0);
        assertEquals(1.0,aPossibleSolution2.getPoints(),0.0);

        /*
         * Case add points to PossibleSolution1, taxon3 present and has is succesor of taxon1 present on aCompConflictSet
         */

        aConflictSet = new ArrayList<Hypothesis>();

        aHypothesis = new Hypothesis();
        aPossibleSolution1 = new PossibleSolution ();
        assertTrue(aPossibleSolution1.setSolution(taxon3));
        aHypothesis.addPossibleSolutions(aPossibleSolution1);
        aConflictSet.add(aHypothesis);

        aHypothesis = new Hypothesis();
        aPossibleSolution2 = new PossibleSolution ();
        assertTrue(aPossibleSolution2.setSolution(taxon4));
        aHypothesis.addPossibleSolutions(aPossibleSolution2);
        aConflictSet.add(aHypothesis);


        aCompConflictSet = new ArrayList<Hypothesis>();

        aHypothesis = new Hypothesis();
        aPossibleSolution3 = new PossibleSolution ();
        assertTrue(aPossibleSolution3.setSolution(taxon3));
        aHypothesis.addPossibleSolutions(aPossibleSolution3);
        aCompConflictSet.add(aHypothesis);

        aHypothesis = new Hypothesis();
        aPossibleSolution4 = new PossibleSolution ();
        assertTrue(aPossibleSolution4.setSolution(taxon1));
        aHypothesis.addPossibleSolutions(aPossibleSolution4);
        aCompConflictSet.add(aHypothesis);

        assertEquals(0.0,aPossibleSolution1.getPoints(),0.0);
        assertEquals(0.0,aPossibleSolution2.getPoints(),0.0);

        instance.evaluate(aConflictSet, aCompConflictSet, aPointAccumulatingScheme);

        assertEquals(2.0,aPossibleSolution1.getPoints(),0.0);
        assertEquals(0.0,aPossibleSolution2.getPoints(),0.0);
        /*
         * Case decrease points to PossibleSolution1, taxon3 present and has is succesor of taxon1 present on aCompConflictSet
         */

        aPointAccumulatingScheme = "-";
        
        aConflictSet = new ArrayList<Hypothesis>();

        aHypothesis = new Hypothesis();
        aPossibleSolution1 = new PossibleSolution ();
        assertTrue(aPossibleSolution1.setSolution(taxon3));
        aHypothesis.addPossibleSolutions(aPossibleSolution1);
        aConflictSet.add(aHypothesis);

        aHypothesis = new Hypothesis();
        aPossibleSolution2 = new PossibleSolution ();
        assertTrue(aPossibleSolution2.setSolution(taxon4));
        aHypothesis.addPossibleSolutions(aPossibleSolution2);
        aConflictSet.add(aHypothesis);


        aCompConflictSet = new ArrayList<Hypothesis>();

        aHypothesis = new Hypothesis();
        aPossibleSolution3 = new PossibleSolution ();
        assertTrue(aPossibleSolution3.setSolution(taxon3));
        aHypothesis.addPossibleSolutions(aPossibleSolution3);
        aCompConflictSet.add(aHypothesis);

        aHypothesis = new Hypothesis();
        aPossibleSolution4 = new PossibleSolution ();
        assertTrue(aPossibleSolution4.setSolution(taxon1));
        aHypothesis.addPossibleSolutions(aPossibleSolution4);
        aCompConflictSet.add(aHypothesis);

        assertEquals(0.0,aPossibleSolution1.getPoints(),0.0);
        assertEquals(0.0,aPossibleSolution2.getPoints(),0.0);

        instance.evaluate(aConflictSet, aCompConflictSet, aPointAccumulatingScheme);

        assertEquals(-2.0,aPossibleSolution1.getPoints(),0.0);
        assertEquals(0.0,aPossibleSolution2.getPoints(),0.0);

    }

    /**
     * Test of evaluate method, of class PossibleSolutionEvaluator.
     */
    @Test
    public void testEvaluate_List_String() {
        java.lang.System.out.println("evaluate");
        PossibleSolution aPossibleSolution1,aPossibleSolution2;
        String aPointAccumulatingScheme = "+";
        PossibleSolutionEvaluator instance = new PossibleSolutionEvaluator(new ArrayList<Hypothesis>(), new ArrayList<Hypothesis>(), new ArrayList<Hypothesis>(),
			new ArrayList<Hypothesis>(), taxonomy);
        /*
         * taxonomy
         * root
         *      taxon1(FAMILY)
         *                      taxon3(GENUS)
         *                                      taxon5(SPECIES)
         *                                      taxon6(SPECIES)
         *      taxon2(FAMILY)
         *                      taxon4(GENUS)
         *                                      taxon7(SPECIES)
         */

        /*
         * Case add points to  PossibleSolution1 and PossibleSolution2,
         * taxon1 just same, and taxon3 same and is sucessor of taxon1
         */

        List<Hypothesis> aConflictSet = new ArrayList<Hypothesis>();

        Hypothesis aHypothesis = new Hypothesis();
        aPossibleSolution1 = new PossibleSolution ();
        assertTrue(aPossibleSolution1.setSolution(taxon1));
        aHypothesis.addPossibleSolutions(aPossibleSolution1);
        aConflictSet.add(aHypothesis);

        aHypothesis = new Hypothesis();
        aPossibleSolution2 = new PossibleSolution ();
        assertTrue(aPossibleSolution2.setSolution(taxon3));
        aHypothesis.addPossibleSolutions(aPossibleSolution2);
        aConflictSet.add(aHypothesis);

        assertEquals(0.0,aPossibleSolution1.getPoints(),0.0);
        assertEquals(0.0,aPossibleSolution2.getPoints(),0.0);

        instance.evaluate(aConflictSet, aPointAccumulatingScheme);

        assertEquals(1.0,aPossibleSolution1.getPoints(),0.0);
        assertEquals(2.0,aPossibleSolution2.getPoints(),0.0);

        /*
         * Case add points to PossibleSolution1 and PossibleSolution2
         * One for each, they are un branches separated
         */

        aConflictSet = new ArrayList<Hypothesis>();

        aHypothesis = new Hypothesis();
        aPossibleSolution1 = new PossibleSolution ();
        assertTrue(aPossibleSolution1.setSolution(taxon3));
        aHypothesis.addPossibleSolutions(aPossibleSolution1);
        aConflictSet.add(aHypothesis);

        aHypothesis = new Hypothesis();
        aPossibleSolution2 = new PossibleSolution ();
        assertTrue(aPossibleSolution2.setSolution(taxon4));
        aHypothesis.addPossibleSolutions(aPossibleSolution2);
        aConflictSet.add(aHypothesis);


        assertEquals(0.0,aPossibleSolution1.getPoints(),0.0);
        assertEquals(0.0,aPossibleSolution2.getPoints(),0.0);

        instance.evaluate(aConflictSet, aPointAccumulatingScheme);

        assertEquals(1.0,aPossibleSolution1.getPoints(),0.0);
        assertEquals(1.0,aPossibleSolution2.getPoints(),0.0);
        /*
         * Case decrease points to PossibleSolution1 and PossibleSolution2
         * Same than before but decreasing points
         */

        aPointAccumulatingScheme = "-";

        aConflictSet = new ArrayList<Hypothesis>();

        aHypothesis = new Hypothesis();
        aPossibleSolution1 = new PossibleSolution ();
        assertTrue(aPossibleSolution1.setSolution(taxon3));
        aHypothesis.addPossibleSolutions(aPossibleSolution1);
        aConflictSet.add(aHypothesis);

        aHypothesis = new Hypothesis();
        aPossibleSolution2 = new PossibleSolution ();
        assertTrue(aPossibleSolution2.setSolution(taxon4));
        aHypothesis.addPossibleSolutions(aPossibleSolution2);
        aConflictSet.add(aHypothesis);

        assertEquals(0.0,aPossibleSolution1.getPoints(),0.0);
        assertEquals(0.0,aPossibleSolution2.getPoints(),0.0);

        instance.evaluate(aConflictSet, aPointAccumulatingScheme);

        assertEquals(-1.0,aPossibleSolution1.getPoints(),0.0);
        assertEquals(-1.0,aPossibleSolution2.getPoints(),0.0);
    }

    /**
     * Test of inheritPossibleSolutionDescriptionsFrom method, of class PossibleSolutionEvaluator.
     */
    @Test
    public void testInheritPossibleSolutionDescriptionsFrom() {
        java.lang.System.out.println("inheritPossibleSolutionDescriptionsFrom");
        PossibleSolutionEvaluator instance = new PossibleSolutionEvaluator(new ArrayList<Hypothesis>(), new ArrayList<Hypothesis>(), new ArrayList<Hypothesis>(),
			new ArrayList<Hypothesis>(), taxonomy);
        PossibleSolution anOldPossibleSolution = new PossibleSolution ();
        assertTrue(anOldPossibleSolution.setSolution(taxon1));
        PossibleSolution aNewPossibleSolution = new PossibleSolution ();
        assertTrue(aNewPossibleSolution.setSolution(taxon6));
        
        anOldPossibleSolution.addSolutionDescription(new CharacterDescriptor<Object>("solutions","forma","ovalado"));
        anOldPossibleSolution.addConfirmedDescription(new CharacterDescriptor<Object>("confirmed","forma","ovalado"));
        anOldPossibleSolution.addUnconfirmedDescription(new CharacterDescriptor<Object>("unconfirmed","forma","ovalado"));
        anOldPossibleSolution.addDoubtfulDescription(new CharacterDescriptor<Object>("doubts","forma","ovalado"));

        assertEquals(0,aNewPossibleSolution.getSolutionDescription().size());
        assertEquals(0,aNewPossibleSolution.getConfirmedDescription().size());
        assertEquals(0,aNewPossibleSolution.getUnconfirmedDescription().size());
        assertEquals(0,aNewPossibleSolution.getDoubtfulDescription().size());

        instance.inheritPossibleSolutionDescriptionsFrom(anOldPossibleSolution, aNewPossibleSolution);

        assertEquals(1,aNewPossibleSolution.getSolutionDescription().size());
        assertEquals(1,aNewPossibleSolution.getConfirmedDescription().size());
        assertEquals(1,aNewPossibleSolution.getUnconfirmedDescription().size());
        assertEquals(1,aNewPossibleSolution.getDoubtfulDescription().size());

        assertEquals("solutions",aNewPossibleSolution.getSolutionDescription().get(0).getStructure());
        assertEquals("confirmed",aNewPossibleSolution.getConfirmedDescription().get(0).getStructure());
        assertEquals("unconfirmed",aNewPossibleSolution.getUnconfirmedDescription().get(0).getStructure());
        assertEquals("doubts",aNewPossibleSolution.getDoubtfulDescription().get(0).getStructure());
    }

}