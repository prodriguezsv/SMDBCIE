/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ontology.taxonomy.test;

import java.util.List;
import ontology.common.Modifier;
import ontology.common.RVCharacterDescriptor;
import ontology.common.RVHeuristicDescriptor;
import ontology.common.SSCharacterDescriptor;
import ontology.common.SSHeuristicDescriptor;
import ontology.taxonomy.Taxon;
import ontology.taxonomy.TaxonomicRank;
import ontology.taxonomy.Taxonomy;
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
		taxon1.addToDescription(new SSCharacterDescriptor("Branquia","¨Posición del ano con respecto a la branquia","En el centro"),
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
		taxon1.addToDescription(new SSCharacterDescriptor("Tentaculos orales","Contextura","Surcado"),
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
        Taxon rootTaxon2 = new Taxon(TaxonomicRank.ROOT, null);
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