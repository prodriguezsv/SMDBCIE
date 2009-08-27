/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package system.searchAutomata.test;

import system.searchAutomata.*;
import java.util.ArrayList;
import java.util.List;
import ontology.common.CharacterDescriptor;
import ontology.common.Descriptor;
import ontology.taxonomy.Taxon;
import ontology.taxonomy.TaxonomicRank;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import system.PossibleSolution;

/**
 *
 * @author pabloq
 */
public class TaxonomySearchAutomatonTest {

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
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setTSolutionDescription method, of class TaxonSearchAutomaton.
     */
    @Test
    public void testSetTSolutionDescription() {
        System.out.println("setTSolutionDescription");
        CharacterDescriptor cD1,cD2,cD3,cD4;

        cD1 = new CharacterDescriptor<Object>("cuerpo","posicion_de_la_banda_dorsal_continua","centro");
        cD2 = new CharacterDescriptor<Object>("manto","forma","elongado_y_ovalado");
        cD3 = new CharacterDescriptor<Object>("radula","posicion_del_diente_mas_conspicuo","centro");
        cD4 = new CharacterDescriptor<Object>("tentaculos_orales","contextura","macizo");

        TaxonomySearchAutomaton instance = new TaxonomySearchAutomaton();
        instance.addToTSolutionDescription(cD1);
        instance.addToTSolutionDescription(cD1);
        instance.addToTSolutionDescription(cD2);
        instance.addToTSolutionDescription(cD2);
        instance.addToTSolutionDescription(cD3);
        instance.addToTSolutionDescription(cD4);

        assertSame(4,instance.getTSolutionDescription().size());
    }

    /**
     * Test of setTUnmatchedDescription method, of class TaxonSearchAutomaton.
     */
    @Test
    public void testSetTUnmatchedDescription() {
        System.out.println("setTUnmatchedDescription");
        CharacterDescriptor cD1,cD2,cD3,cD4;

        cD1 = new CharacterDescriptor<Object>("cuerpo","posicion_de_la_banda_dorsal_continua","centro");
        cD2 = new CharacterDescriptor<Object>("manto","forma","elongado_y_ovalado");
        cD3 = new CharacterDescriptor<Object>("radula","posicion_del_diente_mas_conspicuo","centro");
        cD4 = new CharacterDescriptor<Object>("tentaculos_orales","contextura","macizo");

        TaxonomySearchAutomaton instance = new TaxonomySearchAutomaton();
        instance.addToTUnmatchedDescription(cD1);
        instance.addToTUnmatchedDescription(cD1);
        instance.addToTUnmatchedDescription(cD2);
        instance.addToTUnmatchedDescription(cD2);
        instance.addToTUnmatchedDescription(cD3);
        instance.addToTUnmatchedDescription(cD4);

        assertSame(4,instance.getTUnmatchedDescription().size());
    }

    /**
     * Test of associateTaxaToPossibleSolutions method, of class TaxonSearchAutomaton.
     */
    @Test
    public void testAssociateTaxaToPossibleSolutions() {
        System.out.println("associateTaxaToPossibleSolutions");
        List<Taxon> aTaxonList = new ArrayList<Taxon>();
        Taxon taxon1,taxon2,taxon3,taxon4;

        taxon1 = new Taxon();
        taxon2 = new Taxon();
        taxon3 = new Taxon();
        taxon4 = new Taxon();
        
        taxon1.setName("Chromodorididae");
        taxon1.setLevel(TaxonomicRank.FAMILY);
        taxon2.setName("Chromodoris");
        taxon2.setLevel(TaxonomicRank.GENUS);
        taxon3.setName("Chromodoris_sphoni");
        taxon3.setLevel(TaxonomicRank.SPECIES);
        taxon4.setName("Chromodoris_clenchi");
        taxon4.setLevel(TaxonomicRank.SPECIES);

        aTaxonList.add(taxon1);
        aTaxonList.add(taxon2);
        aTaxonList.add(taxon3);
        aTaxonList.add(taxon4);

        TaxonomySearchAutomaton instance = new TaxonomySearchAutomaton();
        List<PossibleSolution> result = instance.associateTaxaToPossibleSolutions(aTaxonList);

        assertSame(4,result.size());
        assertSame(taxon1,result.get(0).getSolution());
        assertSame(taxon2,result.get(1).getSolution());
        assertSame(taxon3,result.get(2).getSolution());
        assertSame(taxon4,result.get(3).getSolution());
    }

    /**
     * Test of checkPrecondition method, of class TaxonSearchAutomaton.
     */
    @Test
    public void testCheckPrecondition() {
        System.out.println("checkPrecondition");
        TaxonomySearchAutomaton instance = new TaxonomySearchAutomaton();
        List<Descriptor<Object>> aProblemDescription = new ArrayList<Descriptor<Object>>();
        CharacterDescriptor<Object> cD1,cD2,cD3,cD4;

        cD1 = new CharacterDescriptor<Object>("manto","forma","alargo");


        assertFalse(instance.checkPrecondition(aProblemDescription));

        aProblemDescription.add(cD1);

        assertTrue(instance.checkPrecondition(aProblemDescription));

        cD2 = new CharacterDescriptor<Object>("manto","forma","ovalado");
        cD3 = new CharacterDescriptor<Object>("manto","forma","cuadrado");
        cD4 = new CharacterDescriptor<Object>("manto","forma","redondo");

        aProblemDescription.add(cD2);
        aProblemDescription.add(cD3);
        aProblemDescription.add(cD4);

        assertTrue(instance.checkPrecondition(aProblemDescription));

        aProblemDescription.add(new CharacterDescriptor<Object>("DISTINCT STRUCTURE","forma","redondo"));
        
        assertFalse(instance.checkPrecondition(aProblemDescription));
    }

    /**
     * Test of prepareFailedOutput method, of class TaxonSearchAutomaton.
     */
    @Test
    public void testPrepareFailedOutput() {
        System.out.println("prepareFailedOutput");
        TaxonomySearchAutomaton instance = new TaxonomySearchAutomaton();

        List<Descriptor<Object>> aDescriptorList = new ArrayList<Descriptor<Object>>();
        aDescriptorList.add(new CharacterDescriptor<Object>("cuerpo","posicion_de_la_banda_dorsal_continua","centro"));
        aDescriptorList.add(new CharacterDescriptor<Object>("manto","forma","elongado_y_ovalado"));
        aDescriptorList.add(new CharacterDescriptor<Object>("radula","posicion_del_diente_mas_conspicuo","centro"));
        instance.getSearchOutput().setJustification(aDescriptorList);

        instance.prepareFailedOutput();
    }

    /**
     * Test of prepareSuccessfulOutputWith method, of class TaxonSearchAutomaton.
     */
    @Test
    public void testPrepareSuccessfulOutputWith() {
        System.out.println("prepareSuccessfulOutputWith");
        List<PossibleSolution> aPossibleSolutionsList = new ArrayList<PossibleSolution>();
        aPossibleSolutionsList.add(new PossibleSolution());
        aPossibleSolutionsList.add(new PossibleSolution());
        TaxonomySearchAutomaton instance = new TaxonomySearchAutomaton();
        assertTrue(instance.prepareSuccessfulOutputWith(aPossibleSolutionsList));
        /*
         * doesnt nothing in case that it had been assigned
         */
        List<PossibleSolution>  otherPossibleSolutionsList = new ArrayList<PossibleSolution>();
        otherPossibleSolutionsList.add(new PossibleSolution());
        assertTrue(instance.prepareSuccessfulOutputWith(otherPossibleSolutionsList));

        assertNotSame(1,instance.getSearchOutput().getPossibleSolutions().size());
        assertSame(2,instance.getSearchOutput().getPossibleSolutions().size());
    }

    /**
     * Test of includes method, of class TaxonSearchAutomaton.
     */
    @Test
    public void testIncludes() {
        System.out.println("includes");
        CharacterDescriptor cD1,cD2,cD3,cD4;

        cD1 = new CharacterDescriptor<Object>("cuerpo","posicion_de_la_banda_dorsal_continua","centro");
        cD2 = new CharacterDescriptor<Object>("manto","forma","elongado_y_ovalado");
        cD3 = new CharacterDescriptor<Object>("radula","posicion_del_diente_mas_conspicuo","centro");
        cD4 = new CharacterDescriptor<Object>("tentaculos_orales","contextura","macizo");

        TaxonomySearchAutomaton instance = new TaxonomySearchAutomaton();
        instance.addToTSolutionDescription(cD1);
        instance.addToTSolutionDescription(cD2);
        instance.addToTSolutionDescription(cD3);
        instance.addToTSolutionDescription(cD4);
        List<Descriptor<Object>> listDescription = instance.getTSolutionDescription();
        
        assertTrue(instance.includes(cD1,listDescription));
        assertTrue(instance.includes(new CharacterDescriptor<Object>("radula","posicion_del_diente_mas_conspicuo","centro"),listDescription));
        assertTrue(instance.includes(cD3,listDescription));
        assertTrue(instance.includes(new CharacterDescriptor<Object>("tentaculos_orales","contextura","macizo"),listDescription));
    }

}