package system.searchAutomata.output.test;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//package system.searchAutomata.output.test;

import java.util.ArrayList;
import java.util.List;
import ontology.CBR.Case;
import ontology.common.Descriptor;
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
    List<Descriptor> aDescription;

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

        aDescription = new ArrayList<Descriptor>();
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

        assertNotSame(aPossibleSolutionList,instance.getPossibleSolutions());
        assertSame(((Taxon)aPossibleSolutionList.get(0).getSolution()).getName(),((Taxon)instance.getPossibleSolutions().get(0).getSolution()).getName());
    }

    /**
     * Test of appendToJustification method, of class SearchAutomatonOutput.
     */
    @Test
    public void testAppendToJustification() {
        System.out.println("appendToJustification");
        List<Descriptor> aJustificationList = new ArrayList<Descriptor>();
        aJustificationList.add(new SSCharacterDescriptor("pie","disposicion","sobresale_al_manto"));
        aJustificationList.add(new SSCharacterDescriptor("pie","coloracion","blanquecino"));
        aJustificationList.add(new SSCharacterDescriptor("pie","coloracion","crema"));
        aJustificationList.add(new SSCharacterDescriptor("pie","coloracion","gris_oscuro_casi_negro"));
        SearchAutomatonOutput instance = new SearchAutomatonOutput();
        instance.appendToJustification(aJustificationList);
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

        instance.appendToPossibleSolutions(aPossibleSolutionList);

        assertNotSame(aPossibleSolutionList.size(),instance.getPossibleSolutions().size());
    }

    /**
     * Test of appendToUnmatchedDescription method, of class SearchAutomatonOutput.
     */
    @Test
    public void testAppendToUnmatchedDescription() {
        System.out.println("appendToUnmatchedDescription");
        SearchAutomatonOutput instance = new SearchAutomatonOutput();
        List<Descriptor> anUnmatchedDescription = new ArrayList<Descriptor>();
        anUnmatchedDescription.add(new SSCharacterDescriptor("pie","disposicion","sobresale_al_manto"));
        anUnmatchedDescription.add(new SSCharacterDescriptor("pie","coloracion","blanquecino"));
        
        instance.appendToUnmatchedDescription(anUnmatchedDescription);
        
        assertSame(2,instance.getUnmatchedDescription().size());

        /*
         * Must add just new ones.
         */
        anUnmatchedDescription.add(new SSCharacterDescriptor("pie","disposicion","new value"));
        anUnmatchedDescription.add(new SSCharacterDescriptor("pie","disposicion","other new"));

        instance.appendToUnmatchedDescription(anUnmatchedDescription);
        assertSame(4,instance.getUnmatchedDescription().size());
        /*
         * Must keep the sames, descriptor already exists.
         */
        anUnmatchedDescription.add(new SSCharacterDescriptor("pie","coloracion","blanquecino"));
        instance.appendToUnmatchedDescription(anUnmatchedDescription);
        assertSame(4,instance.getUnmatchedDescription().size());
        
    }

    /**
     * Test of getDescriptor method, of class SearchAutomatonOutput.
     */
    @Test
    public void testGetDescriptor() {
        System.out.println("getDescriptor");
        SearchAutomatonOutput instance = new SearchAutomatonOutput();
        instance.appendToUnmatchedDescription(aDescription);

        List<Descriptor> aDescription2 = new ArrayList<Descriptor>();
        aDescription2.add(new SSCharacterDescriptor("manos","disposicion","sobresale_al_manto"));
        aDescription2.add(new SSCharacterDescriptor("manos","coloracion","blanquecino"));
        
        Descriptor aCharacterDescriptor = instance.getDescriptor(new SSCharacterDescriptor("pie","coloracion","crema"), instance.getUnmatchedDescription());
        /*
         * Case when it's the same instance and the descriptor exist.
         */
        assertEquals("crema",aCharacterDescriptor.getValue());
        /*
         * Cases when is not the same instance and case when it's the same instance but descriptor doesn't exist.
         */

        assertNull(instance.getDescriptor(new SSCharacterDescriptor("pie","coloracion","crema"), aDescription2));
        assertNull(instance.getDescriptor(new SSCharacterDescriptor("pie","coloracion","red"), instance.getUnmatchedDescription()));
    }


    /**
     * Test of contains method, of class SearchAutomatonOutput.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        SearchAutomatonOutput instance = new SearchAutomatonOutput();
        instance.appendToUnmatchedDescription(aDescription);

        SSCharacterDescriptor aCharacterDescriptor = new SSCharacterDescriptor("pie","coloracion","blanquecino");
        /*
         * Same instance and it contains the descriptor
         */
        assertTrue(instance.contains(aCharacterDescriptor, instance.getUnmatchedDescription()));

        List<Descriptor> aDescription2 = new ArrayList<Descriptor>();
        aDescription2.add(new SSCharacterDescriptor("manos","disposicion","sobresale_al_manto"));
        aDescription2.add(new SSCharacterDescriptor("pie","coloracion","blanquecino"));
        /*
         * Not same instance and it contains the descriptor
         */
        assertFalse(instance.contains(aCharacterDescriptor, aDescription2));
        /*
         * Same instance and it doesn't contain the descriptor
         */
        aCharacterDescriptor = new SSCharacterDescriptor("pie","coloracion","color unknow");
        assertFalse(instance.contains(aCharacterDescriptor, instance.getUnmatchedDescription()));
    }

}