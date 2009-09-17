package ontology.taxonomy.test;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import ontology.common.RVCharacterDescriptor;
import ontology.common.RVHeuristicDescriptor;
import ontology.common.SSCharacterDescriptor;
import ontology.common.SSHeuristicDescriptor;
import ontology.taxonomy.Modifier;
import ontology.taxonomy.Taxon;
import ontology.taxonomy.TaxonomicRank;
import ontology.values.MeasuringUnit;
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
        rootTaxon = new Taxon(TaxonomicRank.ROOT, null);
        
//-----------------------Taxon No. 1---------------------
        taxon1 = new Taxon(TaxonomicRank.FAMILY, "Chromodorididae");
//-----------------------Structure No. 1---------------------
        taxon1.addToDescription(new SSCharacterDescriptor("Cuerpo","Forma","Alargado"),
                new Modifier(1.0,1.0,0.8));
		taxon1.addToDescription(new SSCharacterDescriptor("Cuerpo","Forma","Ovalado"),
		        new Modifier(1.0,1.0,0.1));
		taxon1.addToDescription(new RVCharacterDescriptor("Cuerpo","Longitud", new RangeValue(0.3, 4.0,
				MeasuringUnit.CM)), new Modifier(1.0,1.0,1.0));
		taxon1.addToDescription(new SSCharacterDescriptor("Cuerpo","Conformación","Tiene cerata"),
		        new Modifier(1.0,1.0,1.0));
		//-----------------------Structure No. 2---------------------
		taxon1.addToDescription(new SSCharacterDescriptor("Pie","Disposición","Sobresale al manto"),
		        new Modifier(0.8,1.0,0.8));
		taxon1.addToDescription(new SSCharacterDescriptor("Pie","Coloración","Blanquecino"),
		        new Modifier(0.8,1.0,0.7));
		taxon1.addToDescription(new SSCharacterDescriptor("Pie","Coloración","Crema"),
		        new Modifier(0.8,1.0,0.7));
		taxon1.addToDescription(new SSCharacterDescriptor("Pie","Coloración","Gris oscuro casi negro"),
		        new Modifier(0.8,1.0,0.2));
		//-----------------------Structure No. 2---------------------
		taxon1.addToDescription(new SSCharacterDescriptor("Branquia","Posición durante desplazamiento","Hacia atras"),
		        new Modifier(0.8,1.0,0.8));
		taxon1.addToDescription(new SSCharacterDescriptor("Branquia","Posición del ano con respecto a la branquia","En el centro"),
		                new Modifier(0.8,1.0,0.8));
		taxon1.addToDescription(new RVCharacterDescriptor("Branquia","Número hojas branquiales", 
				new RangeValue(6.0, 9.0)), new Modifier(0.8,1.0,1.0));
		taxon1.addToDescription(new SSCharacterDescriptor("Branquia","Forma hojas branquiales","Bipinnada"),
		        new Modifier(0.8,1.0,0.6));
		taxon1.addToDescription(new SSCharacterDescriptor("Branquia","Forma hojas branquiales","Tripinnada"),
		        new Modifier(0.8,1.0,0.4));
		//-----------------------Structure No. 4---------------------
		taxon1.addToDescription(new SSCharacterDescriptor("Manto","Textura","Lisa"),
		        new Modifier(1.0,1.0,0.8));
		taxon1.addToDescription(new SSCharacterDescriptor("Manto","Textura","Con tuberculos"),
		        new Modifier(1.0,1.0,0.4));
		taxon1.addToDescription(new SSCharacterDescriptor("Manto","Forma del borde","Ondulado"),
		        new Modifier(1.0,1.0,0.3));
		taxon1.addToDescription(new SSCharacterDescriptor("Manto","Textura del borde","Lisa"),
		        new Modifier(1.0,1.0,0.7));
		//-----------------------Structure No. 5---------------------
		taxon1.addToDescription(new SSCharacterDescriptor("Glándulas del manto","Posición","Delante"),
		        new Modifier(1.0,1.0,0.2));
		taxon1.addToDescription(new SSCharacterDescriptor("Glándulas del manto","Posición",
				"Delante y atras"), new Modifier(1.0,1.0,0.2));
		taxon1.addToDescription(new SSCharacterDescriptor("Glándulas del manto","Posición",
				"Alrededor del manto"), new Modifier(1.0,1.0,0.4));
		//-----------------------Structure No. 6---------------------
		taxon1.addToDescription(new SSCharacterDescriptor("Rinoforos","Forma","Laminados"),
		        new Modifier(1.0,1.0,1.0));
		taxon1.addToDescription(new RVCharacterDescriptor("Rinoforos","Número de laminillas",
				new RangeValue(6.0, 20.0)), new Modifier(1.0,1.0,1.0));
		//-----------------------Structure No. 7---------------------
		taxon1.addToDescription(new SSCharacterDescriptor("Tentáculos orales","Contextura","Macizo"),
		        new Modifier(1.0,1.0,0.7));
		taxon1.addToDescription(new SSCharacterDescriptor("Tentáculos orales","Contextura","Surcado"),
		        new Modifier(1.0,1.0,0.2));
		//-----------------------Grouping Heuristic No. 1---------------------
		taxon1.addToDescription(new SSHeuristicDescriptor("Alimentación", "Alimentación","Esponjas"),
		        new Modifier(1.0,1.0,1.0));
		//-----------------------Grouping Heuristic No. 2---------------------
		taxon1.addToDescription(new RVHeuristicDescriptor("Profundidad donde se encuentra",
				"Profundidad donde se encuentra", new RangeValue(0.0, 20.0, MeasuringUnit.CM)), 
				new Modifier(1.0,1.0,1.0));
		//-----------------------Grouping Heuristic No. 3---------------------
		taxon1.addToDescription(new SSHeuristicDescriptor("Medio de preservacion tenido",
				"Medio de preservacion tenido","Azul marino"), new Modifier(1.0,1.0,1.0));
		taxon1.addToDescription(new SSHeuristicDescriptor("Medio de preservacion tenido",
				"Medio de preservacion tenido","Celeste"), new Modifier(1.0,1.0,0.8));
		taxon1.addToDescription(new SSHeuristicDescriptor("Medio de preservacion tenido",
				"Medio de preservacion tenido","Amarillento"), new Modifier(1.0,1.0,0.2));
//-----------------------Taxon No. 2---------------------
        taxon2 = new Taxon(TaxonomicRank.GENUS, "Chromodoris");
//-----------------------Structure No. 1---------------------
        taxon2.addToDescription(new SSCharacterDescriptor("Cuerpo","Posición de la banda dorsal continua", "Centro"),
                new Modifier(1.0,1.0,1.0));
        taxon2.addToDescription(new SSCharacterDescriptor("Cuerpo","Coloración","Brillante azul rojo blanco anaranjado purpura"),
                new Modifier(1.0,1.0,1.0));
        taxon2.addToDescription(new SSCharacterDescriptor("Cuerpo","Forma ventral","Aplanado"),
                new Modifier(1.0,1.0,0.6));
//-----------------------Structure No. 2---------------------
        taxon2.addToDescription(new SSCharacterDescriptor("Manto","Forma","Elongado y ovalado"),
                new Modifier(0.8,1.0,0.7));
        taxon2.addToDescription(new SSCharacterDescriptor("Manto","Contextura","Con glandulas"),
                new Modifier(0.8,1.0,0.8));
//-----------------------Structure No. 3---------------------
        taxon2.addToDescription(new SSCharacterDescriptor("Radula","Forma de los dientes","Denticulados"),
                new Modifier(0.3,1.0,1.0));
        taxon2.addToDescription(new SSCharacterDescriptor("Radula","Posición del diente más conspicuo","Centro"),
                new Modifier(0.3,1.0,0.5));

        taxon3 = new Taxon(TaxonomicRank.SPECIES, "Chromodoris sphoni");
        taxon4 = new Taxon(TaxonomicRank.SPECIES, "Chromodoris clenchi");
        taxon5 = new Taxon(TaxonomicRank.SPECIES, "Chromodoris kempfi");
        taxon6 = new Taxon(TaxonomicRank.GENUS, "Cadlina");
        taxon7 = new Taxon(TaxonomicRank.SPECIES, "Cadlina sparsa");
        taxon8 = new Taxon(TaxonomicRank.GENUS, "Hypselodoris");
    }

    @After
    public void tearDown() {
    }


    /**
     * Test of setPredecessor method, of class Taxon.
     */
    @Test
    public void testSetPredecessor() {
        taxon2.setPredecessor(taxon1);

        assertEquals(taxon1, taxon2.getPredecessor());

        assertTrue(taxon1.getSuccessors().contains(taxon2));
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
        taxon2.getSuccessors().clear();
    }

    /**
     * Test of addSuccessor method, of class Taxon.
     */
    @Test
    public void testAddSuccessor() {

        assertTrue(taxon1.addSuccessor(taxon2));

        assertTrue(taxon1.getSuccessors().contains(taxon2));
        
    }

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