/**
 * 
 */
package ontology.CBR.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ontology.CBR.Case;
import ontology.common.Descriptor;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Armando
 *
 */
public class CaseTest {
	private static Case aCase;
	
	/**
	 * 
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		aCase = new Case();
	}

	/**
	 * 
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Descriptor<Object> aDescriptor;
		
		aDescriptor = new Descriptor<Object>("Estructura1", "Atributo1", 1.5);
		aCase.addToDescription(aDescriptor);
		aDescriptor = new Descriptor<Object>("Estructura2", "Atributo2", "Estado1");
		aCase.addToDescription(aDescriptor);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		aCase.getDescription().clear();
	}

	/**
	 * Test method for {@link ontology.CBR.Case#addToDescription(ontology.common.Descriptor)}.
	 */
	@Test
	public final void testAddToDescription() {
		Descriptor<Object> aDescriptor;
		
		assertFalse(aCase.addToDescription(null));
		aDescriptor = new Descriptor<Object>("Estructura1", "Atributo1", 1.5);
		assertFalse(aCase.addToDescription(aDescriptor));
		aDescriptor = new Descriptor<Object>("Estructura2", "Atributo2", "Estado1");
		assertFalse(aCase.addToDescription(aDescriptor));
		aDescriptor = new Descriptor<Object>("Estructura1", "Atributo1", 2.0);
		assertTrue(aCase.addToDescription(aDescriptor));
	}

	/**
	 * Test method for {@link ontology.CBR.Case#addToJustification(ontology.common.Descriptor)}.
	 */
	@Test
	public final void testAddToJustification() {
		Descriptor<Object> aDescriptor;
		
		assertFalse(aCase.addToDescription(null));
		aDescriptor = new Descriptor<Object>("Estructura1", "Atributo1", 1.5);
		assertFalse(aCase.addToDescription(aDescriptor));
		aDescriptor = new Descriptor<Object>("Estructura2", "Atributo2", "Estado1");
		assertFalse(aCase.addToDescription(aDescriptor));
		aDescriptor = new Descriptor<Object>("Estructura1", "Atributo1", 2.0);
		assertTrue(aCase.addToDescription(aDescriptor));
	}

	/**
	 * Test method for {@link ontology.CBR.Case#getStructuresList()}.
	 */
	@Test
	public final void testGetStructuresList() {
		List<String> sl;
		
		sl = new ArrayList<String>();
		sl.addAll(Arrays.asList("Estructura1", "Estructura2"));
		
		assertEquals(sl, aCase.getStructuresList());
	}

	/**
	 * Test method for {@link ontology.CBR.Case#getDescription(java.lang.String)}.
	 */
	@Test
	public final void testGetDescription() {
		List<Descriptor<Object>> dl = new ArrayList<Descriptor<Object>>();
		
		dl.add(new Descriptor<Object>("Estructura1", "Atributo1", 1.5));
		assertEquals(dl, aCase.getDescription("Estructura1"));
	}
}
