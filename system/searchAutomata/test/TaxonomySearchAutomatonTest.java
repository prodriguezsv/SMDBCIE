/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package system.searchAutomata.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ontology.CBR.SimilarityDegree;
import ontology.common.Description;
import ontology.common.Descriptor;
import ontology.common.Modifier;
import ontology.common.RVCharacterDescriptor;
import ontology.common.SSCharacterDescriptor;
import ontology.taxonomy.Taxon;
import ontology.taxonomy.TaxonomicRank;
import ontology.values.RangeValue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import system.PossibleSolution;
import system.searchAutomata.SearchStatus;
import system.searchAutomata.TaxonomySearchAutomaton;

/**
 *
 * @author pabloq
 */
public class TaxonomySearchAutomatonTest {

    Taxon rootTaxon,taxon1,taxon2,taxon3,taxon4,taxon5,taxon6,taxon7,taxon8;
    List<Taxon> listaTaxones1,listaTaxones2,listaTaxones3,listaTaxones4;
    Map<Descriptor, List<Taxon>> searchIndex;

    public TaxonomySearchAutomatonTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {

    	searchIndex = new HashMap<Descriptor, List<Taxon>>();
    	rootTaxon = new Taxon(TaxonomicRank.ROOT, null);
    	taxon1 = new Taxon(TaxonomicRank.FAMILY, "Chromodorididae");
    	taxon2 = new Taxon(TaxonomicRank.GENUS, "Chromodoris");
        
        taxon3 = new Taxon(TaxonomicRank.SPECIES, "Chromodoris sphoni");
        taxon4 = new Taxon(TaxonomicRank.SPECIES, "Chromodoris clenchi");
        taxon5 = new Taxon(TaxonomicRank.SPECIES, "Chromodoris kempfi");
        taxon6 = new Taxon(TaxonomicRank.GENUS, "Cadlina");
        taxon7 = new Taxon(TaxonomicRank.SPECIES, "Cadlina sparsa");
        taxon8 = new Taxon(TaxonomicRank.GENUS, "Hypselodoris");
//-----------------------Taxon No. 1---------------------
        taxon1.addToDescription(new SSCharacterDescriptor("cuerpo","forma","alargado"),
                                    new Modifier(1.0,1.0,0.8));
        taxon1.addToDescription(new SSCharacterDescriptor("cuerpo","forma","ovalado"),
                                    new Modifier(1.0,1.0,0.1));
//-----------------------Structure No. 1---------------------
        listaTaxones1 = new ArrayList<Taxon>();
        listaTaxones2 = new ArrayList<Taxon>();
        listaTaxones3 = new ArrayList<Taxon>();
        listaTaxones4 = new ArrayList<Taxon>();

        listaTaxones1.add(taxon1);
        listaTaxones1.add(taxon4);
        listaTaxones1.add(taxon6);

        listaTaxones2.add(taxon2);
        listaTaxones2.add(taxon8);

        listaTaxones3.add(taxon6);
        listaTaxones3.add(taxon7);
        listaTaxones3.add(taxon1);
        listaTaxones3.add(taxon3);

        listaTaxones4.add(taxon3);
        listaTaxones4.add(taxon6);
        listaTaxones4.add(taxon7);
        listaTaxones4.add(taxon2);

       /*
         * Lista 1
         */
        searchIndex.put(new SSCharacterDescriptor("cuerpo","forma","alargado"),listaTaxones1);
        searchIndex.put(new SSCharacterDescriptor("cuerpo","forma","ovalado"),listaTaxones1);
        RangeValue aRangeDescriptor = new RangeValue(0.3, 4.0);
        /*
         * Lista 2
         */
        searchIndex.put(new RVCharacterDescriptor("cuerpo","longitud",aRangeDescriptor),listaTaxones2);
        searchIndex.put(new SSCharacterDescriptor("cuerpo","conformacion","tiene_cerata"),listaTaxones2);
        searchIndex.put(new SSCharacterDescriptor("pie","disposicion","sobresale_al_manto"),listaTaxones2);
        searchIndex.put(new SSCharacterDescriptor("pie","coloracion","blanquecino"),listaTaxones2);
        /*
         * Lista 3
         */
        searchIndex.put(new SSCharacterDescriptor("pie","coloracion","gris_oscuro_casi_negro"),listaTaxones3);
        searchIndex.put(new SSCharacterDescriptor("pie","coloracion","crema"),listaTaxones3);
        searchIndex.put(new SSCharacterDescriptor("branquia","posicion_durante_desplazamiento","hacia_atras"),listaTaxones3);
        /*
         * Lista 4
         */
        searchIndex.put(new SSCharacterDescriptor("branquia","posicion_del_ano_con_respecto_a_la_branquia","en_el_centro"),listaTaxones4);
        aRangeDescriptor = new RangeValue(6.0, 9.0);
        searchIndex.put(new RVCharacterDescriptor("branquia","numero_hojas_branquiales",aRangeDescriptor),listaTaxones4);
        searchIndex.put(new SSCharacterDescriptor("branquia","forma_hojas_branquiales","bipinnada"),listaTaxones4);
        searchIndex.put(new SSCharacterDescriptor("manto","textura_del_borde","lisa"),listaTaxones4);
        searchIndex.put(new SSCharacterDescriptor("tentaculos_orales","contextura","surcado"),listaTaxones4);
        searchIndex.put(new SSCharacterDescriptor("tentaculos_orales","contextura","macizo"),listaTaxones4);
        
    }

    @After
    public void tearDown() {
    }


    /**
     * Test of addToTSolutionDescription method, of class TaxonomySearchAutomaton.
     */
    @Test
    public void testAddToTSolutionDescription() {
        System.out.println("addToTSolutionDescription");
        TaxonomySearchAutomaton instance = new TaxonomySearchAutomaton(searchIndex,SimilarityDegree.POCOSIMILAR);
        Descriptor aDescriptor1 = new SSCharacterDescriptor("branquia","forma_hojas_branquiales","bipinnada");
        Descriptor aDescriptor2 = new SSCharacterDescriptor("manto","textura_del_borde","lisa");
        Descriptor aDescriptor3 = new SSCharacterDescriptor("pie","coloracion","gris_oscuro_casi_negro");
        Descriptor aDescriptor4 = new SSCharacterDescriptor("cuerpo","conformacion","tiene_cerata");

        assertTrue(instance.addToTSolutionDescription(aDescriptor1));
        assertSame(1,instance.getTSolutionDescription().size());
        assertTrue(instance.addToTSolutionDescription(aDescriptor1));
        assertSame(1,instance.getTSolutionDescription().size());

        assertTrue(instance.addToTSolutionDescription(aDescriptor2));
        assertTrue(instance.addToTSolutionDescription(aDescriptor3));
        assertTrue(instance.addToTSolutionDescription(aDescriptor3));
        assertTrue(instance.addToTSolutionDescription(aDescriptor3));
        assertTrue(instance.addToTSolutionDescription(aDescriptor4));
        assertSame(4,instance.getTSolutionDescription().size());
    }

    /**
     * Test of addToTUnmatchedDescription method, of class TaxonomySearchAutomaton.
     */
    @Test
    public void testAddToTUnmatchedDescription() {
        System.out.println("addToTUnmatchedDescription");
        TaxonomySearchAutomaton instance = new TaxonomySearchAutomaton(searchIndex,SimilarityDegree.POCOSIMILAR);
        Descriptor aDescriptor1 = new SSCharacterDescriptor("branquia","forma_hojas_branquiales","bipinnada");
        Descriptor aDescriptor2 = new SSCharacterDescriptor("manto","textura_del_borde","lisa");
        Descriptor aDescriptor3 = new SSCharacterDescriptor("pie","coloracion","gris_oscuro_casi_negro");
        Descriptor aDescriptor4 = new SSCharacterDescriptor("cuerpo","conformacion","tiene_cerata");

        assertTrue(instance.addToTUnmatchedDescription(aDescriptor1));
        assertSame(1,instance.getTUnmatchedDescription().size());
        assertTrue(instance.addToTUnmatchedDescription(aDescriptor1));
        assertSame(1,instance.getTUnmatchedDescription().size());

        assertTrue(instance.addToTUnmatchedDescription(aDescriptor2));
        assertTrue(instance.addToTUnmatchedDescription(aDescriptor3));
        assertTrue(instance.addToTUnmatchedDescription(aDescriptor3));
        assertTrue(instance.addToTUnmatchedDescription(aDescriptor3));
        assertTrue(instance.addToTUnmatchedDescription(aDescriptor4));
        assertSame(4,instance.getTUnmatchedDescription().size());
    }

    /**
     * Test of associateTaxaToPossibleSolutions method, of class TaxonomySearchAutomaton.
     */
    @Test
    public void testAssociateTaxaToPossibleSolutions() {
        System.out.println("associateTaxaToPossibleSolutions");
        List<Taxon> aTaxonList = new ArrayList<Taxon>();
        Taxon taxon1,taxon2,taxon3,taxon4;

    	taxon1 = new Taxon(TaxonomicRank.FAMILY, "Chromodorididae");
    	taxon2 = new Taxon(TaxonomicRank.GENUS, "Chromodoris");
        taxon3 = new Taxon(TaxonomicRank.SPECIES, "Chromodoris sphoni");
        taxon4 = new Taxon(TaxonomicRank.SPECIES, "Chromodoris clenchi");
        
        aTaxonList.add(taxon1);
        aTaxonList.add(taxon2);
        aTaxonList.add(taxon3);
        aTaxonList.add(taxon4);

        TaxonomySearchAutomaton instance = new TaxonomySearchAutomaton(searchIndex,SimilarityDegree.POCOSIMILAR);
        List<PossibleSolution> result = instance.associateTaxaToPossibleSolutions(aTaxonList);

        assertSame(4,result.size());
        assertSame(taxon1,result.get(0).getSolution());
        assertSame(taxon2,result.get(1).getSolution());
        assertSame(taxon3,result.get(2).getSolution());
        assertSame(taxon4,result.get(3).getSolution());
    }

    /**
     * Test of checkPrecondition method, of class TaxonomySearchAutomaton.
     */
    @Test
    public void testCheckPrecondition() {
        System.out.println("checkPrecondition");
        TaxonomySearchAutomaton instance = new TaxonomySearchAutomaton(searchIndex,SimilarityDegree.DIFERENTE);
        List<Descriptor> aProblemDescription = new ArrayList<Descriptor>();
        SSCharacterDescriptor cD1,cD2,cD3,cD4;

        cD1 = new SSCharacterDescriptor("manto","forma","alargo");


        assertFalse(instance.checkPrecondition(aProblemDescription));

        aProblemDescription.add(cD1);

        assertTrue(instance.checkPrecondition(aProblemDescription));

        cD2 = new SSCharacterDescriptor("manto","forma","ovalado");
        cD3 = new SSCharacterDescriptor("manto","forma","cuadrado");
        cD4 = new SSCharacterDescriptor("manto","forma","redondo");

        aProblemDescription.add(cD2);
        aProblemDescription.add(cD3);
        aProblemDescription.add(cD4);

        assertTrue(instance.checkPrecondition(aProblemDescription));

        aProblemDescription.add(new SSCharacterDescriptor("DISTINCT STRUCTURE","forma","redondo"));

        assertFalse(instance.checkPrecondition(aProblemDescription));
    }

    /**
     * Test of prepareFailedOutput method, of class TaxonomySearchAutomaton.
     */
    @Test
    public void testPrepareFailedOutput() {
        System.out.println("prepareFailedOutput");
        TaxonomySearchAutomaton instance = new TaxonomySearchAutomaton(searchIndex,SimilarityDegree.DIFERENTE);

        Description aDescriptorList = new Description();
        aDescriptorList.addToConcreteDescription(new SSCharacterDescriptor("cuerpo","posicion_de_la_banda_dorsal_continua","centro"));
        aDescriptorList.addToConcreteDescription(new SSCharacterDescriptor("manto","forma","elongado_y_ovalado"));
        aDescriptorList.addToConcreteDescription(new SSCharacterDescriptor("radula","posicion_del_diente_mas_conspicuo","centro"));

        assertTrue(instance.prepareFailedOutput());

        instance.getSearchOutput().setJustification(aDescriptorList);

        assertFalse(instance.prepareFailedOutput());
        
    }

    /**
     * Test of prepareSuccessfulOutputWith method, of class TaxonomySearchAutomaton.
     */
    @Test
    public void testPrepareSuccessfulOutputWith() {
        System.out.println("prepareSuccessfulOutputWith");
        List<PossibleSolution> aPossibleSolutionsList = new ArrayList<PossibleSolution>();
        aPossibleSolutionsList.add(new PossibleSolution());
        aPossibleSolutionsList.add(new PossibleSolution());
        TaxonomySearchAutomaton instance = new TaxonomySearchAutomaton(searchIndex,SimilarityDegree.CONSIDERABLEMENTESIMILAR);
        assertEquals(SearchStatus.SUCCESS,instance.prepareSuccessfulOutputWith(aPossibleSolutionsList));
        /*
         * doesnt nothing in case that it had been assigned
         */
        List<PossibleSolution>  otherPossibleSolutionsList = new ArrayList<PossibleSolution>();
        otherPossibleSolutionsList.add(new PossibleSolution());
        assertEquals(SearchStatus.SUCCESS,instance.prepareSuccessfulOutputWith(otherPossibleSolutionsList));

        assertNotSame(1,instance.getSearchOutput().getPossibleSolutions().size());
        assertSame(2,instance.getSearchOutput().getPossibleSolutions().size());
    }

    /**
     * Test of beginWith method, of class TaxonomySearchAutomaton.
     */
    @Test
    public void testBeginWith() {
        System.out.println("beginWith");
        TaxonomySearchAutomaton instance = new TaxonomySearchAutomaton(searchIndex,SimilarityDegree.CONSIDERABLEMENTESIMILAR);

        List<Descriptor> aProblemDescription = new ArrayList<Descriptor>();

        /*
         * Empty Problem Description
         */

        assertEquals(SearchStatus.ERROR, instance.beginWith(aProblemDescription));


        aProblemDescription.add(new SSCharacterDescriptor("cuerpo","forma","alargado"));
        aProblemDescription.add(new SSCharacterDescriptor("diferent structure","forma","corta"));

        /*
         * not same structure.
         */

        assertEquals(SearchStatus.ERROR, instance.beginWith(aProblemDescription));



        aProblemDescription = new ArrayList<Descriptor>();
        aProblemDescription.add(new SSCharacterDescriptor("tentaculos_orales","contextura","surcado"));
        aProblemDescription.add(new SSCharacterDescriptor("tentaculos_orales","contextura","macizo"));

        /*
         * not description that match with a taxon.
         */
        assertEquals(SearchStatus.ERROR, instance.beginWith(aProblemDescription));



        aProblemDescription = new ArrayList<Descriptor>();
        aProblemDescription.add(new SSCharacterDescriptor("cuerpo","forma","alargado"));
        aProblemDescription.add(new SSCharacterDescriptor("cuerpo","forma","corta"));



        assertEquals(SearchStatus.SUCCESS, instance.beginWith(aProblemDescription));
    }

    /**
     * Test of searchPossibleSolutions method, of class TaxonomySearchAutomaton.
     */
    @Test
    public void testSearchPossibleSolutions() {
        System.out.println("searchPossibleSolutions");
        List<Descriptor> aProblemDescription = new ArrayList<Descriptor>();
        aProblemDescription.add(new SSCharacterDescriptor("cuerpo","forma","alargado"));
        aProblemDescription.add(new SSCharacterDescriptor("cuerpo","forma","corta"));

        TaxonomySearchAutomaton instance = new TaxonomySearchAutomaton(searchIndex,SimilarityDegree.MEDIANAMENTESIMILAR);
        assertTrue( instance.searchPossibleSolutions(aProblemDescription));
    }

    /**
     * Test of determineSimilarityFor method, of class TaxonomySearchAutomaton.
     */
    @Test
    public void testDetermineSimilarityFor() {
        System.out.println("determineSimilarityFor");
        TaxonomySearchAutomaton instance = new TaxonomySearchAutomaton(searchIndex,SimilarityDegree.MEDIANAMENTESIMILAR);
        /*
         * Descriptor that exist on taxon1
         */
        Descriptor aDescriptor = new SSCharacterDescriptor("cuerpo","forma","alargado");

        assertSame(taxon1, instance.determineSimilarityFor(aDescriptor, taxon1));
        /*
         * Descriptor that doesnt  exist on taxon2 (null when it fails)
         */
        assertNotSame(taxon1, instance.determineSimilarityFor(aDescriptor, taxon2));
        assertSame(null, instance.determineSimilarityFor(aDescriptor, taxon2));
    }

}