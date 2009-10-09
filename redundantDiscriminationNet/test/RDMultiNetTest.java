/**
 * 
 */
package redundantDiscriminationNet.test;

import static org.junit.Assert.*;

import ontology.CBR.Case;
import ontology.common.SSCharacterDescriptor;
import ontology.taxonomy.TaxonomicRank;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import redundantDiscriminationNet.RDMultiNet;
import redundantDiscriminationNet.RDMultiNetRoot;

/**
 * @author Armando
 *
 */
public class RDMultiNetTest {
	private static RDMultiNet rdmultinet; 

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Iniciando pruebas para la clase " + RDMultiNetRoot.class.getName());
		rdmultinet = new RDMultiNet();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Teminando pruebas para la clase " + RDMultiNetRoot.class.getName());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link redundantDiscriminationNet.RDMultiNet#add(ontology.CBR.Case)}.
	 */
	@Test
	public void testAdd() {
		Case c1, c4, c5914, c67, c8, c10, c11, c12;
		
		System.out.println("Iniciando pruebas para el método add()");
		
		System.out.println("Verificar que se agrega un caso correctamente");
		c1 = new Case();
		c1.addToDescription(new SSCharacterDescriptor("Cuerpo", "Coloración", "Rosado palido y crema"));
		c1.addToDescription(new SSCharacterDescriptor("Branquia", "Coloración", "Rosado palido y crema"));
		c1.addToDescription(new SSCharacterDescriptor("Branquia", "Forma hojas branquiales", "Tripinnada"));
		c1.addToDescription(new SSCharacterDescriptor("Manto", "Forma del borde", "Ondulado"));
		c1.addToDescription(new SSCharacterDescriptor("Manto", "Textura", "Lisa"));
		c1.addToDescription(new SSCharacterDescriptor("Rinoforos", "Coloración", "Rosado palido y crema"));
		rdmultinet.add(c1);
		assertEquals(4, rdmultinet.getRoot().getNets().size());
		c1 = new Case();
		c1.addToDescription(new SSCharacterDescriptor("Cuerpo", "Coloración", "Blanquecino"));
		c1.addToDescription(new SSCharacterDescriptor("Cuerpo", "Forma", "Ovalado"));
		c1.addToDescription(new SSCharacterDescriptor("Manto", "Coloración del borde", "Amarillo"));
		c1.addToDescription(new SSCharacterDescriptor("Rinoforos", "Coloración de los ápices", "Rojo"));
		rdmultinet.add(c1);
		assertEquals(4, rdmultinet.getRoot().getNets().size());		
		
		System.out.println("Iniciando pruebas más completas");
		
		rdmultinet.getRoot().getNets().clear();
		
//-----------------------Caso resuelto No. 1---------------------
		c4 = new Case();
		c4.getSolution().setTaxonLevel(TaxonomicRank.SPECIES.getRank());
		c4.getSolution().setTaxonName("Glossodoris sedna");
		c4.setState(true);
		
		assertTrue(c4.addToDescription(new SSCharacterDescriptor("Manto", "Coloración del borde", "Amarillo")));
		assertTrue(c4.addToDescription(new SSCharacterDescriptor("Manto", "Coloración de los ápices", "Rojo")));
		
//-----------------------Caso resuelto No. 2---------------------
		c5914 = new Case();
		c5914.getSolution().setTaxonLevel(TaxonomicRank.FAMILY.getRank());
		c5914.getSolution().setTaxonName("Chromodorididae");
		c5914.setState(true);
		
		assertTrue((c5914.addToDescription(new SSCharacterDescriptor("Rinoforos", "Coloración del raquis", "Blanco"))));
		assertTrue((c5914.addToDescription(new SSCharacterDescriptor("Rinoforos", "Coloración", "Blanco"))));
		assertTrue((c5914.addToDescription(new SSCharacterDescriptor("Branquia", "Coloración", "Blanco"))));
		assertTrue((c5914.addToDescription(new SSCharacterDescriptor("Pie", "Disposición", "Sobresale al manto"))));
		
//-----------------------Caso resuelto No. 3---------------------
		c67 = new Case();
		c67.getSolution().setTaxonLevel(TaxonomicRank.SPECIES.getRank());
		c67.getSolution().setTaxonName("Hypselodoris agassizii");
		c67.setState(true);
		
		assertTrue(c67.addToDescription(new SSCharacterDescriptor("Branquia", "Coloración de los ápices", "Azul oscuro a negro")));
		assertTrue((c67.addToDescription(new SSCharacterDescriptor("Pie", "Disposición", "Sobresale al manto"))));
		assertTrue((c67.addToDescription(new SSCharacterDescriptor("Manto", "Coloración línea exterior del borde", "Amarillo"))));
		assertTrue((c67.addToDescription(new SSCharacterDescriptor("Manto", "Coloración línea interior del borde", "Verde claro"))));
		assertTrue((c67.addToDescription(new SSCharacterDescriptor("Rinoforos", "Coloración del raquis", "Blanco"))));
		
//-----------------------Caso resuelto No. 4---------------------
		c8 = new Case();
		c8.getSolution().setTaxonLevel(TaxonomicRank.SPECIES.getRank());
		c8.getSolution().setTaxonName("Glossodoris dalli");
		c8.setState(true);
		
		assertTrue((c8.addToDescription(new SSCharacterDescriptor("Cuerpo", "Forma", "Ovalado"))));

//-----------------------Caso resuelto No. 5---------------------
		c10 = new Case();
		c10.getSolution().setTaxonLevel(TaxonomicRank.SPECIES.getRank());
		c10.getSolution().setTaxonName("Aplysiia dactylomela");
		c10.setState(true);
		
		assertTrue((c10.addToDescription(new SSCharacterDescriptor("Cuerpo", "Conformación", "Tiene una concha interna"))));
		assertTrue((c10.addToDescription(new SSCharacterDescriptor("Cuerpo", "Coloración del fondo", "Cafesuzco a verde oliva con anillos u ocelos de pigmento oscuro"))));
		assertTrue((c10.addToDescription(new SSCharacterDescriptor("Cuerpo", "Forma", "Alto alargado y contractil"))));
		assertTrue((c10.addToDescription(new SSCharacterDescriptor("Parapodio", "Grado de desarrollo", "Mucho"))));
		assertTrue((c10.addToDescription(new SSCharacterDescriptor("Parapodio", "Simetría", "Simétricos libres"))));
		
//-----------------------Caso resuelto No. 6---------------------
		c11 = new Case();
		c11.getSolution().setTaxonLevel(TaxonomicRank.SPECIES.getRank());
		c11.getSolution().setTaxonName("Chromodoris kempfi");
		c11.setState(true);
		
		assertTrue((c11.addToDescription(new SSCharacterDescriptor("Manto", "Coloración de la banda dorsal continua", "Blanco"))));
		
//-----------------------Caso resuelto No. 7---------------------
		c12 = new Case();
		c12.getSolution().setTaxonLevel(TaxonomicRank.SPECIES.getRank());
		c12.getSolution().setTaxonName("Chromodoris kempfi");
		c12.setState(true);
		
		assertTrue((c12.addToDescription(new SSCharacterDescriptor("Cuerpo", "Coloración", "Verduzco"))));
		assertTrue((c12.addToDescription(new SSCharacterDescriptor("Cuerpo", "Conformación", "Tiene una concha interna"))));
		assertTrue((c12.addToDescription(new SSCharacterDescriptor("Cuerpo", "Consistencia", "Voluminoso"))));
		
		/**
		 * Agregar casos a la memoria
		 */
		rdmultinet.add(c4);
		rdmultinet.add(c5914);
		rdmultinet.add(c67);
		rdmultinet.add(c8);
		rdmultinet.add(c10);
		rdmultinet.add(c11);
		rdmultinet.add(c12);
		
		assertEquals(6, rdmultinet.getRoot().getNets().size());		
	}
}
