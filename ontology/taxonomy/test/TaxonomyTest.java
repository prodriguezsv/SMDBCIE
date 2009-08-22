/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ontology.taxonomy.test;

import java.util.List;
import ontology.common.CharacterDescriptor;
import ontology.common.HeuristicDescriptor;
import ontology.common.Modifier;
import ontology.taxonomy.Taxon;
import ontology.taxonomy.TaxonomicRank;
import ontology.taxonomy.Taxonomy;
import ontology.values.RangeValue;
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
public class TaxonomyTest {
    Taxonomy taxonomyEmpty,taxonomy;
    Taxon rootTaxon,taxon1;

    public TaxonomyTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        taxonomy = new Taxonomy();
        rootTaxon = new Taxon();
        rootTaxon.setName(null);
        rootTaxon.setLevel(TaxonomicRank.ROOT);
        taxon1 = new Taxon();
//-----------------------Taxon No. 1---------------------
        taxon1.setName("Chromodorididae");
        taxon1.setLevel(TaxonomicRank.FAMILY);
//-----------------------Structure No. 1---------------------
        taxon1.addToDescription(new CharacterDescriptor("cuerpo","forma","alargado"),
                                    new Modifier(1.0,1.0,0.8));
        taxon1.addToDescription(new CharacterDescriptor("cuerpo","forma","ovalado"),
                                    new Modifier(1.0,1.0,0.1));

        RangeValue aRangeDescriptor = new RangeValue();
        aRangeDescriptor.setLowerBound(0.3);
        aRangeDescriptor.setUpperBound(4.0);

        taxon1.addToDescription(new CharacterDescriptor("cuerpo","longitud",aRangeDescriptor),
                                    new Modifier(1.0,1.0,1.0));

        taxon1.addToDescription(new CharacterDescriptor("cuerpo","conformacion","tiene_cerata"),
                                    new Modifier(1.0,1.0,1.0));
//-----------------------Structure No. 2---------------------
        taxon1.addToDescription(new CharacterDescriptor("pie","disposicion","sobresale_al_manto"),
                                    new Modifier(0.8,1.0,0.8));
        taxon1.addToDescription(new CharacterDescriptor("pie","coloracion","blanquecino"),
                                    new Modifier(0.8,1.0,0.7));
        taxon1.addToDescription(new CharacterDescriptor("pie","coloracion","crema"),
                                    new Modifier(0.8,1.0,0.7));
        taxon1.addToDescription(new CharacterDescriptor("pie","coloracion","gris_oscuro_casi_negro"),
                                    new Modifier(0.8,1.0,0.2));
//-----------------------Structure No. 2---------------------
        taxon1.addToDescription(new CharacterDescriptor("branquia","posicion_durante_desplazamiento","hacia_atras"),
                                    new Modifier(0.8,1.0,0.8));
        taxon1.addToDescription(new CharacterDescriptor("branquia","posicion_del_ano_con_respecto_a_la_branquia","en_el_centro"),
                                    new Modifier(0.8,1.0,0.8));

        aRangeDescriptor = new RangeValue();
        aRangeDescriptor.setLowerBound(6.0);
        aRangeDescriptor.setUpperBound(9.0);

        taxon1.addToDescription(new CharacterDescriptor("branquia","numero_hojas_branquiales",aRangeDescriptor),
                                    new Modifier(0.8,1.0,1.0));
        taxon1.addToDescription(new CharacterDescriptor("branquia","forma_hojas_branquiales","bipinnada"),
                                    new Modifier(0.8,1.0,0.6));
        taxon1.addToDescription(new CharacterDescriptor("branquia","forma_hojas_branquiales","tripinnada"),
                                    new Modifier(0.8,1.0,0.4));
//-----------------------Structure No. 4---------------------
        taxon1.addToDescription(new CharacterDescriptor("manto","textura","lisa"),
                                    new Modifier(1.0,1.0,0.8));
        taxon1.addToDescription(new CharacterDescriptor("manto","textura","con_tuberculos"),
                                    new Modifier(1.0,1.0,0.4));
        taxon1.addToDescription(new CharacterDescriptor("manto","forma_del_borde","ondulado"),
                                    new Modifier(1.0,1.0,0.3));
        taxon1.addToDescription(new CharacterDescriptor("manto","textura_del_borde","lisa"),
                                    new Modifier(1.0,1.0,0.7));
//-----------------------Structure No. 5---------------------
        taxon1.addToDescription(new CharacterDescriptor("glandulas_del_manto","posicion","delante"),
                                    new Modifier(1.0,1.0,0.2));
        taxon1.addToDescription(new CharacterDescriptor("glandulas_del_manto","posicion","delante_y_atras"),
                                    new Modifier(1.0,1.0,0.2));
        taxon1.addToDescription(new CharacterDescriptor("glandulas_del_manto","posicion","alrededor_del_manto"),
                                    new Modifier(1.0,1.0,0.4));
//-----------------------Structure No. 6---------------------
        taxon1.addToDescription(new CharacterDescriptor("rinoforos","forma","laminados"),
                                    new Modifier(1.0,1.0,1.0));
        aRangeDescriptor = new RangeValue();
        aRangeDescriptor.setLowerBound(6.0);
        aRangeDescriptor.setUpperBound(20.0);
        taxon1.addToDescription(new CharacterDescriptor("rinoforos","numero_de_laminillas",aRangeDescriptor),
                                    new Modifier(1.0,1.0,1.0));
//-----------------------Structure No. 7---------------------
        taxon1.addToDescription(new CharacterDescriptor("tentaculos_orales","contextura","macizo"),
                                    new Modifier(1.0,1.0,0.7));
        taxon1.addToDescription(new CharacterDescriptor("tentaculos_orales","contextura","surcado"),
                                    new Modifier(1.0,1.0,0.2));
//-----------------------Grouping Heuristic No. 1---------------------
        taxon1.addToDescription(new HeuristicDescriptor("heuristica","alimenatcion","esponjas"),
                                    new Modifier(1.0,1.0,1.0));
//-----------------------Grouping Heuristic No. 2---------------------
        aRangeDescriptor = new RangeValue();
        aRangeDescriptor.setLowerBound(0.0);
        aRangeDescriptor.setUpperBound(20.0);
        taxon1.addToDescription(new HeuristicDescriptor("heuristica","profundidad_donde_se_encuentra",aRangeDescriptor),
                                    new Modifier(1.0,1.0,1.0));
//-----------------------Grouping Heuristic No. 3---------------------
        taxon1.addToDescription(new HeuristicDescriptor("heuristica","medio_de_preservacion_tenido","azul_marino"),
                                    new Modifier(1.0,1.0,1.0));
        taxon1.addToDescription(new HeuristicDescriptor("heuristica","medio_de_preservacion_tenido","celeste"),
                                    new Modifier(1.0,1.0,0.8));
        taxon1.addToDescription(new HeuristicDescriptor("heuristica","medio_de_preservacion_tenido","amarillento"),
                                    new Modifier(1.0,1.0,0.2));

        taxonomy.addTaxon(taxon1, rootTaxon);

        taxonomyEmpty = new Taxonomy();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getTaxonListFromLevelIndex method, of class Taxonomy.
     */
    @Test
    public void testGetTaxonListFromLevelIndex() {
        System.out.println("getTaxonListFromLevelIndex");
        List<Taxon> aTaxonList = taxonomy.getTaxonListFromLevelIndex(TaxonomicRank.FAMILY);

        assertEquals(1,aTaxonList.size());

        /*
         * It must be taxon 1
         */
        assertTrue(taxon1.equals(aTaxonList.get(0)));
    }

    /**
     * Test of getTaxonFromLevelIndex method, of class Taxonomy.
     */
    @Test
    public void testGetTaxonFromLevelIndex_String_TaxonomicRank() {
        System.out.println("getTaxonFromLevelIndex");
        assertEquals(taxon1,taxonomy.getTaxonFromLevelIndex("Chromodorididae",TaxonomicRank.FAMILY));
        assertNull(taxonomy.getTaxonFromLevelIndex("Chromodorididae",TaxonomicRank.GENUS));
        assertNull(taxonomy.getTaxonFromLevelIndex("Chromodorididae",TaxonomicRank.SPECIES));
        assertNull(taxonomy.getTaxonFromLevelIndex("unknow taxon",TaxonomicRank.FAMILY));
        assertNull(taxonomy.getTaxonFromLevelIndex(null,TaxonomicRank.FAMILY));
    }

    /**
     * Test of getTaxonFromLevelIndex method, of class Taxonomy.
     */
    @Test
    public void testGetTaxonFromLevelIndex_String() {
        System.out.println("getTaxonFromLevelIndex");
        assertEquals(taxon1,taxonomy.getTaxonFromLevelIndex("Chromodorididae"));
        assertNull(taxonomy.getTaxonFromLevelIndex("unknow taxon"));
        assertNull(taxonomy.getTaxonFromLevelIndex(null));
    }

    /**
     * Test of addTaxon method, of class Taxonomy.
     */
    @Test
    public void testAddTaxon() {
        System.out.println("addTaxon");
        taxonomyEmpty.addTaxon(taxon1, rootTaxon);
        assertEquals(taxon1,taxonomyEmpty.getTaxonFromLevelIndex(taxon1.getName()));
    }

    /**
     * Test of areTaxonomicDependenciesOK method, of class Taxonomy.
     */
    @Test
    public void testAreTaxonomicDependenciesOK() {
        System.out.println("areTaxonomicDependenciesOK");
        Taxon rootTaxon2 = new Taxon();
        rootTaxon2.setName(null);
        rootTaxon2.setLevel(TaxonomicRank.ROOT);
        assertTrue(taxonomyEmpty.areTaxonomicDependenciesOK(rootTaxon2, taxon1));
    }

    /**
     * Test of contains method, of class Taxonomy.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        assertTrue(taxonomy.contains(taxon1));
        assertFalse(taxonomyEmpty.contains(taxon1));
    }

}