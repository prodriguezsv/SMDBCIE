package system.searchAutomata.output.test;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//package system.searchAutomata.output.test;

import java.util.ArrayList;
import java.util.List;
import ontology.CBR.Case;
import ontology.CBR.PossibleSolution;
import ontology.common.SSCharacterDescriptor;
import ontology.common.SSHeuristicDescriptor;
import ontology.taxonomy.Modifier;
import ontology.taxonomy.Taxon;
import ontology.taxonomy.TaxonomicRank;
import ontology.taxonomy.Taxonomy;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import system.searchAutomata.output.CaseMemoryDFSAutomatonOutput;

/**
 *
 * @author pabloq
 */
public class CaseBaseDFSAutomatonOutputTest {
    Taxonomy aTaxonomy;
    Taxon aTaxon,aTaxon2,rootTaxon;
    PossibleSolution aPossibleSolution,aPossibleSolution2;
    Case aCase;
    List<PossibleSolution> aPossibleSolutionList;

    public CaseBaseDFSAutomatonOutputTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        aTaxonomy = new Taxonomy();

        rootTaxon = new Taxon(TaxonomicRank.ROOT.getRank(), null);
//-----------------------Taxon No. 1---------------------
        aTaxon = new Taxon(TaxonomicRank.FAMILY.getRank(), "Chromodorididae");
//-----------------------Structure No. 1---------------------
        aTaxon.addToDescription(new SSCharacterDescriptor("Cuerpo","Forma","Alargado"),
                                    new Modifier(1.0,1.0,0.8));
        aTaxon.addToDescription(new SSCharacterDescriptor("Cuerpo","Forma","Ovalado"),
                                    new Modifier(1.0,1.0,0.1));
        aTaxon.addToDescription(new SSCharacterDescriptor("Cuerpo","Conformación","Tiene cerata"),
                                    new Modifier(1.0,1.0,1.0));
//-----------------------Structure No. 2---------------------
        aTaxon.addToDescription(new SSCharacterDescriptor("Pie","Disposición","Sobresale al manto"),
                                    new Modifier(0.8,1.0,0.8));
        aTaxon.addToDescription(new SSCharacterDescriptor("Pie","Coloración","Blanquecino"),
                                    new Modifier(0.8,1.0,0.7));
        aTaxon.addToDescription(new SSCharacterDescriptor("Pie","Coloracion","Crema"),
                                    new Modifier(0.8,1.0,0.7));
        aTaxon.addToDescription(new SSCharacterDescriptor("Pie","Coloración","Gris oscuro casi negro"),
                                    new Modifier(0.8,1.0,0.2));
//-----------------------Structure No. 2---------------------
        aTaxon.addToDescription(new SSCharacterDescriptor("Branquia","Posición durante desplazamiento", 
        		"Hacia atras"), new Modifier(0.8,1.0,0.8));
        aTaxon.addToDescription(new SSCharacterDescriptor("Branquia","Posición del ano con respecto a la branquia","En el centro"),
                                    new Modifier(0.8,1.0,0.8));
        aTaxon.addToDescription(new SSCharacterDescriptor("Branquia","Forma hojas branquiales","Bipinnada"),
                                    new Modifier(0.8,1.0,0.6));
        aTaxon.addToDescription(new SSCharacterDescriptor("Branquia","Forma hojas branquiales","Tripinnada"),
                                    new Modifier(0.8,1.0,0.4));
//-----------------------Grouping Heuristic No. 1---------------------
        aTaxon.addToDescription(new SSHeuristicDescriptor("Alimenatcion","Alimenatcion","Esponjas"),
                                    new Modifier(1.0,1.0,1.0));
        aTaxonomy.addTaxon(aTaxon, rootTaxon);

        aPossibleSolution = new PossibleSolution();
        aPossibleSolution.setSolution(aTaxon);

//        aCase = new Case();
//        aCase.addToDescription(new SSCharacterDescriptor("pie","coloracion","crema"));
//        aCase.addToDescription(new SSCharacterDescriptor("branquia","forma_hojas_branquiales","tripinnada"));
//-----------------------Taxon No. 2---------------------
        aTaxon2 = new Taxon(TaxonomicRank.GENUS.getRank(), "Chromodoris");
//-----------------------Structure No. 1---------------------
        aTaxon2.addToDescription(new SSCharacterDescriptor("Cuerpo","Posición de la banda dorsal continua","Centro"),
                                    new Modifier(1.0,1.0,1.0));
        aTaxon2.addToDescription(new SSCharacterDescriptor("Cuerpo","Coloración",
        		"brillante azul rojo blanco anaranjado purpura"), new Modifier(1.0,1.0,1.0));
        aTaxon2.addToDescription(new SSCharacterDescriptor("Cuerpo","Forma ventral","Aplanado"),
                                    new Modifier(1.0,1.0,0.6));

        aTaxonomy.addTaxon(aTaxon2, aTaxon);

        aPossibleSolution2 = new PossibleSolution();
        aPossibleSolution2.setSolution(aTaxon2);

        
        aPossibleSolutionList = new ArrayList<PossibleSolution>();
        aPossibleSolutionList.add(aPossibleSolution);
        aPossibleSolutionList.add(aPossibleSolution2);
        aPossibleSolutionList.add(aPossibleSolution2);
        aPossibleSolutionList.add(aPossibleSolution);
        aPossibleSolutionList.add(aPossibleSolution2);


    }

    @After
    public void tearDown() {
    }

    /**
     * Test of compress method, of class DFSAutomatonOutput.
     */
    @Test
    public void testCompress() {
        System.out.println("compress");
        CaseMemoryDFSAutomatonOutput instance = new CaseMemoryDFSAutomatonOutput();
        instance.setTaxonomy(aTaxonomy);
        instance.setPossibleSolutions(aPossibleSolutionList);
        
        assertEquals(5,instance.getPossibleSolutions().size());

        assertTrue(instance.compressPossibleSolutions());

        assertEquals(2,instance.getPossibleSolutions().size());
    }

}