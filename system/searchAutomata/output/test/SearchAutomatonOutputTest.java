package system.searchAutomata.output.test;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//package system.searchAutomata.output.test;

import java.util.ArrayList;
import java.util.List;
import ontology.CBR.Case;
import ontology.common.Description;
import ontology.common.SSCharacterDescriptor;
import ontology.common.SVCharacterDescriptor;
import ontology.taxonomy.Taxon;
import ontology.taxonomy.TaxonomicRank;
import ontology.values.SingleValue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import system.PossibleSolution;
import system.searchAutomata.output.SearchAutomatonOutput;

/**
 *
 * @author pabloq
 */
public class SearchAutomatonOutputTest {
    PossibleSolution aPossibleSolution,aPossibleSolution2;
    Taxon aTaxon;
    Case aCase;
    List<PossibleSolution> aPossibleSolutionList;
    Description aDescription;

    public SearchAutomatonOutputTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        aTaxon = new Taxon(TaxonomicRank.GENUS, "Glossodoris");
        
        aPossibleSolution = new PossibleSolution();
        aPossibleSolution.setSolution(aTaxon);
        
        aCase = new Case();
        aCase.addToDescription(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3)));
        aCase.addToDescription(new SSCharacterDescriptor("Pie", "Disposición", "Sobresale al manto"));

        aPossibleSolution2 = new PossibleSolution();
        aPossibleSolution2.setSolution(aCase);

        aPossibleSolutionList = new ArrayList<PossibleSolution>();
        aPossibleSolutionList.add(aPossibleSolution);
        aPossibleSolutionList.add(aPossibleSolution2);

        aDescription = new Description();
        aDescription.add(new SSCharacterDescriptor("pie","disposicion","sobresale_al_manto"));
        aDescription.add(new SSCharacterDescriptor("pie","coloracion","blanquecino"));
        aDescription.add(new SSCharacterDescriptor("pie","coloracion","crema"));
        aDescription.add(new SSCharacterDescriptor("pie","coloracion","gris_oscuro_casi_negro"));


    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setPossibleSolutions method, of class SearchAutomatonOutput.
     */
    @Test
    public void testSetPossibleSolutions() {
        System.out.println("setPossibleSolutions");
        SearchAutomatonOutput instance = new SearchAutomatonOutput();
        instance.setPossibleSolutions(aPossibleSolutionList);

        assertSame(aPossibleSolutionList,instance.getPossibleSolutions());
        assertSame(((Taxon)aPossibleSolutionList.get(0).getSolution()).getName(),
        		((Taxon)instance.getPossibleSolutions().get(0).getSolution()).getName());
    }

    /**
     * Test of appendToJustification method, of class SearchAutomatonOutput.
     */
    @Test
    public void testAppendToJustification() {
        System.out.println("appendToJustification");
        Description aJustificationList = new Description();
        aJustificationList.addToConcreteDescription(new SSCharacterDescriptor("pie","disposicion","sobresale_al_manto"));
        aJustificationList.addToConcreteDescription(new SSCharacterDescriptor("pie","coloracion","blanquecino"));
        aJustificationList.addToConcreteDescription(new SSCharacterDescriptor("pie","coloracion","crema"));
        aJustificationList.addToConcreteDescription(new SSCharacterDescriptor("pie","coloracion","gris_oscuro_casi_negro"));
        SearchAutomatonOutput instance = new SearchAutomatonOutput();
        instance.addAllToJustification(aJustificationList);
    }

    /**
     * Test of appendToPossibleSolutions method, of class SearchAutomatonOutput.
     */
    @Test
    public void testAppendToPossibleSolutions() {
        System.out.println("appendToPossibleSolutions");
        SearchAutomatonOutput instance = new SearchAutomatonOutput();

        instance.setPossibleSolutions(aPossibleSolutionList);
        assertEquals(aPossibleSolutionList.size(),instance.getPossibleSolutions().size());

        instance.addAllToPossibleSolutions(aPossibleSolutionList);

        assertSame(aPossibleSolutionList.size(),instance.getPossibleSolutions().size());
    }

    /**
     * Test of appendToUnmatchedDescription method, of class SearchAutomatonOutput.
     */
    @Test
    public void testAppendToUnmatchedDescription() {
        System.out.println("appendToUnmatchedDescription");
        SearchAutomatonOutput instance = new SearchAutomatonOutput();
        Description anUnmatchedDescription = new Description();
        anUnmatchedDescription.addToConcreteDescription(new SSCharacterDescriptor("pie","disposicion","sobresale_al_manto"));
        anUnmatchedDescription.addToConcreteDescription(new SSCharacterDescriptor("pie","coloracion","blanquecino"));
        
        instance.addAllToUnmatchedDescription(anUnmatchedDescription);
        
        assertSame(2,instance.getUnmatchedDescription().size());

        /*
         * Must add just new ones.
         */
        anUnmatchedDescription.addToConcreteDescription(new SSCharacterDescriptor("pie","disposicion","new value"));
        anUnmatchedDescription.addToConcreteDescription(new SSCharacterDescriptor("pie","disposicion","other new"));

        instance.addAllToUnmatchedDescription(anUnmatchedDescription);
        assertSame(2,instance.getUnmatchedDescription().size());
        /*
         * Must keep the sames, descriptor already exists.
         */
        anUnmatchedDescription.addToConcreteDescription(new SSCharacterDescriptor("pie","coloracion","blanquecino"));
        instance.addAllToUnmatchedDescription(anUnmatchedDescription);
        assertSame(2,instance.getUnmatchedDescription().size());
        
    }
}