package system.searchAutomata.output.test;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//package system.searchAutomata.output.test;

import java.util.ArrayList;
import java.util.List;
import ontology.CBR.Case;
import ontology.common.CharacterDescriptor;
import ontology.common.HeuristicDescriptor;
import ontology.common.Modifier;
import ontology.taxonomy.Taxon;
import ontology.taxonomy.TaxonomicRank;
import ontology.taxonomy.Taxonomy;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import system.PossibleSolution;
import static org.junit.Assert.*;
import system.searchAutomata.output.DFSAutomatonOutput;

/**
 *
 * @author pabloq
 */
public class DFSAutomatonOutputTest {
    Taxonomy aTaxonomy;
    Taxon aTaxon,aTaxon2,rootTaxon;
    PossibleSolution aPossibleSolution,aPossibleSolution2;
    Case aCase;
    List<PossibleSolution> aPossibleSolutionList;

    public DFSAutomatonOutputTest() {
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

        rootTaxon = new Taxon();
        rootTaxon.setName(null);
        rootTaxon.setLevel(TaxonomicRank.ROOT);
        aTaxon = new Taxon();
//-----------------------Taxon No. 1---------------------
        aTaxon.setName("Chromodorididae");
        aTaxon.setLevel(TaxonomicRank.FAMILY);
//-----------------------Structure No. 1---------------------
        aTaxon.addToDescription(new CharacterDescriptor("cuerpo","forma","alargado"),
                                    new Modifier(1.0,1.0,0.8));
        aTaxon.addToDescription(new CharacterDescriptor("cuerpo","forma","ovalado"),
                                    new Modifier(1.0,1.0,0.1));
        aTaxon.addToDescription(new CharacterDescriptor("cuerpo","conformacion","tiene_cerata"),
                                    new Modifier(1.0,1.0,1.0));
//-----------------------Structure No. 2---------------------
        aTaxon.addToDescription(new CharacterDescriptor("pie","disposicion","sobresale_al_manto"),
                                    new Modifier(0.8,1.0,0.8));
        aTaxon.addToDescription(new CharacterDescriptor("pie","coloracion","blanquecino"),
                                    new Modifier(0.8,1.0,0.7));
        aTaxon.addToDescription(new CharacterDescriptor("pie","coloracion","crema"),
                                    new Modifier(0.8,1.0,0.7));
        aTaxon.addToDescription(new CharacterDescriptor("pie","coloracion","gris_oscuro_casi_negro"),
                                    new Modifier(0.8,1.0,0.2));
//-----------------------Structure No. 2---------------------
        aTaxon.addToDescription(new CharacterDescriptor("branquia","posicion_durante_desplazamiento","hacia_atras"),
                                    new Modifier(0.8,1.0,0.8));
        aTaxon.addToDescription(new CharacterDescriptor("branquia","posicion_del_ano_con_respecto_a_la_branquia","en_el_centro"),
                                    new Modifier(0.8,1.0,0.8));
        aTaxon.addToDescription(new CharacterDescriptor("branquia","forma_hojas_branquiales","bipinnada"),
                                    new Modifier(0.8,1.0,0.6));
        aTaxon.addToDescription(new CharacterDescriptor("branquia","forma_hojas_branquiales","tripinnada"),
                                    new Modifier(0.8,1.0,0.4));
//-----------------------Grouping Heuristic No. 1---------------------
        aTaxon.addToDescription(new HeuristicDescriptor("heuristica","alimenatcion","esponjas"),
                                    new Modifier(1.0,1.0,1.0));
        aTaxonomy.addTaxon(aTaxon, rootTaxon);

        aPossibleSolution = new PossibleSolution();
        aPossibleSolution.setSolution(aTaxon);

//        aCase = new Case();
//        aCase.addToDescription(new CharacterDescriptor<Object>("pie","coloracion","crema"));
//        aCase.addToDescription(new CharacterDescriptor<Object>("branquia","forma_hojas_branquiales","tripinnada"));
//-----------------------Taxon No. 2---------------------
        aTaxon2 = new Taxon();
        aTaxon2.setName("Chromodoris");
        aTaxon2.setLevel(TaxonomicRank.GENUS);
//-----------------------Structure No. 1---------------------
        aTaxon2.addToDescription(new CharacterDescriptor<Object>("cuerpo","posicion_de_la_banda_dorsal_continua","centro"),
                                    new Modifier(1.0,1.0,1.0));
        aTaxon2.addToDescription(new CharacterDescriptor<Object>("cuerpo","coloracion","brillante_azul_rojo_blanco_anaranjado_purpura"),
                                    new Modifier(1.0,1.0,1.0));
        aTaxon2.addToDescription(new CharacterDescriptor<Object>("cuerpo","forma_ventral","aplanado"),
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
        DFSAutomatonOutput instance = new DFSAutomatonOutput();
        instance.setTaxonomy(aTaxonomy);
        instance.setPossibleSolutions(aPossibleSolutionList);
        
        assertEquals(5,instance.getPossibleSolutions().size());

        assertTrue(instance.compress());

        assertEquals(2,instance.getPossibleSolutions().size());
    }

}