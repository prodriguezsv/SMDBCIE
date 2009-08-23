/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ontology.taxonomy.test;

import java.util.ArrayList;
import java.util.List;
import ontology.common.CharacterDescriptor;
import ontology.common.HeuristicDescriptor;
import ontology.common.Modifier;
import ontology.taxonomy.Taxon;
import ontology.taxonomy.TaxonomicRank;
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
public class TaxonTest {
    Taxon rootTaxon,taxon1,taxon2,taxon3,taxon4,taxon5,taxon6,taxon7,taxon8;

    public TaxonTest() {
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
        taxon2 = new Taxon();
        taxon3 = new Taxon();
        taxon4 = new Taxon();
        taxon5 = new Taxon();
        taxon6 = new Taxon();
        taxon7 = new Taxon();
        taxon8 = new Taxon();
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
//-----------------------Taxon No. 2---------------------
        taxon2.setName("Chromodoris");
        taxon2.setLevel(TaxonomicRank.GENUS);
//-----------------------Structure No. 1---------------------
        taxon2.addToDescription(new CharacterDescriptor("cuerpo","posicion_de_la_banda_dorsal_continua","centro"),
                                    new Modifier(1.0,1.0,1.0));
        taxon2.addToDescription(new CharacterDescriptor("cuerpo","coloracion","brillante_azul_rojo_blanco_anaranjado_purpura"),
                                    new Modifier(1.0,1.0,1.0));
        taxon2.addToDescription(new CharacterDescriptor("cuerpo","forma_ventral","aplanado"),
                                    new Modifier(1.0,1.0,0.6));
//-----------------------Structure No. 2---------------------
        taxon2.addToDescription(new CharacterDescriptor("manto","forma","elongado_y_ovalado"),
                                    new Modifier(0.8,1.0,0.7));
        taxon2.addToDescription(new CharacterDescriptor("manto","contextura","con_glandulas"),
                                    new Modifier(0.8,1.0,0.8));
//-----------------------Structure No. 3---------------------
        taxon2.addToDescription(new CharacterDescriptor("radula","forma_de_los_dientes","denticulados"),
                                    new Modifier(0.3,1.0,1.0));
        taxon2.addToDescription(new CharacterDescriptor("radula","posicion_del_diente_mas_conspicuo","centro"),
                                    new Modifier(0.3,1.0,0.5));

        taxon3.setName("Chromodoris_sphoni");
        taxon3.setLevel(TaxonomicRank.SPECIES);
        taxon4.setName("Chromodoris_clenchi");
        taxon4.setLevel(TaxonomicRank.SPECIES);
        taxon5.setName("Chromodoris_kempfi");
        taxon5.setLevel(TaxonomicRank.SPECIES);
        taxon6.setName("Cadlina");
        taxon6.setLevel(TaxonomicRank.GENUS);
        taxon7.setName("Cadlina_sparsa");
        taxon7.setLevel(TaxonomicRank.SPECIES);
        taxon8.setName("Hypselodoris");
        taxon8.setLevel(TaxonomicRank.GENUS);


    }

    @After
    public void tearDown() {
    }


    /**
     * Test of setPredecessor method, of class Taxon.
     */
    @Test
    public void testSetPredecessor() {
        taxon1.setPredecessor(taxon2);
        
        assertEquals(taxon2, taxon1.getPredecessor());

        assertTrue(taxon2.getSuccessors().contains(taxon1));

    }

    /**
     * Test of setSuccessors method, of class Taxon.
     */
    @Test
    public void testSetSuccessors() {
        System.out.println("setSuccessors");
        List<Taxon> sucessors = new ArrayList<Taxon>();
        sucessors.add(taxon1);

        taxon2.setSuccessors(sucessors);
    }

//    /**
//     * Test of addSuccessor method, of class Taxon.
//     */
//    @Test
//    public void testAddSuccessor() {
//        System.out.println("addSuccessor");
//        Taxon successor = null;
//        Taxon instance = new Taxon();
//        instance.addSuccessor(successor);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of isSuccessorOf method, of class Taxon.
     */
    @Test
    public void testIsSuccessorOf() {
        System.out.println("isSuccessorOf");
        taxon1.setPredecessor(rootTaxon);
        taxon2.setPredecessor(taxon1);
        taxon6.setPredecessor(taxon1);
        taxon8.setPredecessor(taxon1);

        taxon3.setPredecessor(taxon2);
        taxon4.setPredecessor(taxon6);
        taxon5.setPredecessor(taxon6);
        taxon7.setPredecessor(taxon8);

        /*ROOT=>
         *      1=>
         *          2=>
         *              3
         *          6=>
         *              4
         *              5
         *          8=>
         *              7
         */
        
        assertTrue(taxon4.isSuccessorOf(taxon1));
        assertTrue(taxon5.isSuccessorOf(taxon6));
        assertTrue(taxon8.isSuccessorOf(taxon1));

        assertFalse(taxon7.isSuccessorOf(taxon6));
        assertFalse(taxon3.isSuccessorOf(taxon8));
        assertFalse(taxon7.isSuccessorOf(taxon2));
        
    }

    /**
     * Test of unlinkFromTheHierarchy method, of class Taxon.
     */
    @Test
    public void testUnlinkFromTheHierarchy() {
        System.out.println("unlinkFromTheHierarchy");
        taxon1.setPredecessor(rootTaxon);
        taxon2.setPredecessor(taxon1);
        taxon6.setPredecessor(taxon1);
        taxon8.setPredecessor(taxon1);

        taxon3.setPredecessor(taxon2);
        taxon4.setPredecessor(taxon6);
        taxon5.setPredecessor(taxon6);
        taxon7.setPredecessor(taxon8);

        /*ROOT=>
         *      1=>
         *          2=>
         *              3
         *          6=>
         *              4
         *              5
         *          8=>
         *              7
         *
         * Test unlink 6
         *
         */
        
         /*
          * Before
          */

         assertEquals(3,taxon1.getSuccessors().size());
         assertTrue(taxon1.getSuccessors().contains(taxon6));
         assertTrue(taxon6.getSuccessors().contains(taxon4));
         assertTrue(taxon6.getSuccessors().contains(taxon5));
         assertEquals(taxon1,taxon6.getPredecessor());
        
         /*
          * after
          */
         
         taxon6.unlinkFromTheHierarchy();
        
         assertEquals(2,taxon1.getSuccessors().size());
         assertFalse(taxon1.getSuccessors().contains(taxon6));
         assertTrue(taxon6.getSuccessors().contains(taxon4));
         assertTrue(taxon6.getSuccessors().contains(taxon5));
         assertEquals(2,taxon6.getSuccessors().size());
         assertEquals(null,taxon6.getPredecessor());

    }

    /**
     * Test of isOKDirectLink method, of class Taxon.
     */
    @Test
    public void testIsOKDirectLink() {
        System.out.println("isOKDirectLink");
        taxon1.setPredecessor(rootTaxon);
        taxon2.setPredecessor(taxon1);
        taxon6.setPredecessor(taxon1);
        taxon8.setPredecessor(taxon1);

        taxon3.setPredecessor(taxon2);
        taxon4.setPredecessor(taxon6);
        taxon5.setPredecessor(taxon6);
        taxon7.setPredecessor(taxon8);

        /*ROOT=>
         *      1=>
         *          2=>
         *              3
         *          6=>
         *              4
         *              5
         *          8=>
         *              7
         */
        assertTrue(taxon6.isOKDirectLink(taxon1));
        assertTrue(taxon2.isOKDirectLink(taxon1));
        assertTrue(taxon8.isOKDirectLink(taxon1));
        assertTrue(taxon4.isOKDirectLink(taxon6));
        assertTrue(taxon5.isOKDirectLink(taxon6));
        assertTrue(taxon7.isOKDirectLink(taxon6));
        /*
         * Same levels or more than 1 level of distance
         *
         */
        assertFalse(taxon3.isOKDirectLink(taxon1));
        assertFalse(taxon4.isOKDirectLink(taxon1));
        assertFalse(taxon5.isOKDirectLink(taxon1));
        assertFalse(taxon7.isOKDirectLink(taxon1));

        assertFalse(taxon8.isOKDirectLink(taxon6));
        assertFalse(taxon2.isOKDirectLink(taxon6));
        assertFalse(taxon6.isOKDirectLink(taxon8));
    }

    /**
     * Test of compareTo method, of class Taxon.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");

        /*
         * Test same
         */
        assertEquals(0,taxon1.compareTo(taxon1));
        assertEquals(0,taxon2.compareTo(taxon2));
        assertEquals(0,taxon3.compareTo(taxon3));
        /*
         * Test bigger
         */
        assertEquals(15,taxon2.compareTo(taxon1));
        assertEquals(15,taxon3.compareTo(taxon1));
        /*
         * Test smaller
         */
        assertEquals(-15,taxon1.compareTo(taxon2));
        assertEquals(-15,taxon1.compareTo(taxon3));
    }

}