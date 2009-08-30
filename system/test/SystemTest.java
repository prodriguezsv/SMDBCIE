/**
 * 
 */
package system.test;

import ontology.taxonomy.Taxon;
import ontology.taxonomy.TaxonomicRank;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * @author Armando
 *
 */
public class SystemTest {
	private static system.System oracleIDSystem;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Taxon rootTaxon, taxon1, taxon2, taxon3, taxon4, taxon5, taxon6, taxon7, taxon8;
		
		System.out.println("Iniciando pruebas para la clase " + system.System.class.getName());
		oracleIDSystem = new system.System("Molusca");
		
		rootTaxon = new Taxon(TaxonomicRank.ROOT, null);
        
//-----------------------Taxon No. 1---------------------

	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
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

}
