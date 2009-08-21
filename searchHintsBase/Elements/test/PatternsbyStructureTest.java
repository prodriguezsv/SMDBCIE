/**
 * 
 */
package searchHintsBase.Elements.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import ontology.common.CharacterDescriptor;
import ontology.common.Descriptor;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import searchHintsBase.Elements.DescriptorsPattern;
import searchHintsBase.Elements.PatternsbyStructure;

/**
 * @author Armando
 *
 */
public class PatternsbyStructureTest {
	private static PatternsbyStructure patternsbyStructure;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Iniciando pruebas para la clase " + PatternsbyStructure.class.getName());
		patternsbyStructure = new PatternsbyStructure("Cuerpo");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Terminando pruebas para la clase " + PatternsbyStructure.class.getName());
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
		patternsbyStructure.getDescriptorsPatterns().clear();
	}

	/**
	 * Test method for {@link searchHintsBase.elements.PatternsbyStructure#compareTo(searchHintsBase.elements.PatternsbyStructure)}.
	 */
	@Test
	public void testCompareTo() {
		List<Descriptor<Object>> dl;
		PatternsbyStructure pbs;
		DescriptorsPattern dp;
		
		System.out.println("Iniciando pruebas para el método CompareTo()");
		
		System.out.println("Verificar que los patrones de descriptores por estructura son iguales con respecto " +
				"a otro cojunto de patrones según el criterio de comparación");
		dl = new ArrayList<Descriptor<Object>>();
		dl.add(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3));
		dp = new DescriptorsPattern(dl, 1, 0);
		pbs = new PatternsbyStructure("Cuerpo");
		pbs.addPattern(dp);
		patternsbyStructure.addPattern(new DescriptorsPattern(dl, 1, 0));
		assertEquals(0, patternsbyStructure.compareTo(pbs));
		
		System.out.println("Verificar que los patrones de descriptores por estructura son iguales con respecto " +
				"a otro cojunto de patrones según el criterio de comparación");
		dl = new ArrayList<Descriptor<Object>>();
		dl.add(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3));
		dp = new DescriptorsPattern(dl, 10, 0);
		pbs.getDescriptorsPatterns().clear();
		pbs.addPattern(dp);
		assertTrue(patternsbyStructure.compareTo(pbs) > 0);
	
		dl = new ArrayList<Descriptor<Object>>();
		dl.add(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3));
		dp = new DescriptorsPattern(dl, 0, 0);
		pbs.getDescriptorsPatterns().clear();
		pbs.addPattern(dp);
		assertTrue(patternsbyStructure.compareTo(pbs) < 0);
	}

	/**
	 * Test method for {@link searchHintsBase.elements.PatternsbyStructure#addPattern(searchHintsBase.elements.DescriptorsPattern)}.
	 */
	@Test
	public void testAddPattern() {
		List<Descriptor<Object>> dl;
		
		System.out.println("Iniciando pruebas para el método AddPattern()");
		
		System.out.println("Verificar que un patron de descriptores se agrega correctamente");
		dl = new ArrayList<Descriptor<Object>>();
		dl.add(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3));
		assertTrue(patternsbyStructure.addPattern(new DescriptorsPattern(dl, 1, 0)));
		
		dl = new ArrayList<Descriptor<Object>>();
		dl.add(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3));
		assertTrue(patternsbyStructure.addPattern(new DescriptorsPattern(dl, 1, 0)));
		assertTrue(patternsbyStructure.getDescriptorsPatterns().get(0).getSuccessFrequency() == 2);
		
		dl = new ArrayList<Descriptor<Object>>();
		dl.add(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3));
		dl.add(new CharacterDescriptor<Object>("Cuerpo", "Conformación", "Tiene cerata"));
		assertTrue(patternsbyStructure.addPattern(new DescriptorsPattern(dl, 10, 0)));
		assertTrue(patternsbyStructure.getDescriptorsPatterns().get(0).getSuccessFrequency() == 10);
		
		System.out.println("Verificar que un patron de descriptores incompatible o incorrecto no se agrega");
		assertFalse(patternsbyStructure.addPattern(null));
		dl = new ArrayList<Descriptor<Object>>();
		assertFalse(patternsbyStructure.addPattern(new DescriptorsPattern(dl, 1, 0)));
		
		dl = new ArrayList<Descriptor<Object>>();
		dl.add(new CharacterDescriptor<Object>("Branquias", "Número de hojas branquiales", 6));
		assertFalse(patternsbyStructure.addPattern(new DescriptorsPattern(dl, 1, 0)));
	}

	/**
	 * Test method for {@link searchHintsBase.elements.PatternsbyStructure#getDescriptorsPattern(java.util.List)}.
	 */
	@Test
	public void testGetDescriptorsPattern() {
		List<Descriptor<Object>> dl;
		DescriptorsPattern dp;
		
		System.out.println("Iniciando pruebas para el método GetDescriptorsPattern");
		
		System.out.println("Verificar que se obtiene un patron de descriptores con una lista de descriptores" +
				"especificado");
		dl = new ArrayList<Descriptor<Object>>();
		dl.add(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3));
		dl.add(new CharacterDescriptor<Object>("Cuerpo", "Conformación", "Tiene cerata"));
		dp = new DescriptorsPattern(dl, 1, 0);
		patternsbyStructure.addPattern(dp);
		assertNotNull(patternsbyStructure.getDescriptorsPattern(dl));
		assertSame(dp, patternsbyStructure.getDescriptorsPattern(dl));
		
		System.out.println("Verificar que se obtiene null con una lista de descriptores especificado");
		assertNull(patternsbyStructure.getDescriptorsPattern(null));
		
		dl = new ArrayList<Descriptor<Object>>();
		dl.add(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3));
		assertNull(patternsbyStructure.getDescriptorsPattern(dl));
		
		dl.clear();
		assertNull(patternsbyStructure.getDescriptorsPattern(dl));
	}

	/**
	 * Test method for {@link searchHintsBase.elements.PatternsbyStructure#hasAPatternSimilarTo(java.util.List)}.
	 */
	@Test
	public void testHasAPatternSimilarTo() {
		List<Descriptor<Object>> d1, d2, d3;
		DescriptorsPattern dp1, dp2, dp3;
		
		System.out.println("Iniciando pruebas para el método HasAPatternSimilarTo()");
		
		System.out.println("Verificar que se tiene un patron lo suficientemente similar");
		d1 = new ArrayList<Descriptor<Object>>();
		d1.add(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3));
		dp1 = new DescriptorsPattern(d1, 1, 0);
		patternsbyStructure.addPattern(dp1);
		
		d2 = new ArrayList<Descriptor<Object>>();
		d2.add(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3));
		d2.add(new CharacterDescriptor<Object>("Cuerpo", "Conformación", "Tiene cerata"));
		dp2 = new DescriptorsPattern(d2, 10, 0);
		patternsbyStructure.addPattern(dp2);
		
		d3 = new ArrayList<Descriptor<Object>>();
		d3.add(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3));
		d3.add(new CharacterDescriptor<Object>("Cuerpo", "Conformación", "Tiene cerata"));
		d3.add(new CharacterDescriptor<Object>("Cuerpo", "Forma", "Ovalad0"));
		dp3 = new DescriptorsPattern(d3, 10, 0);
		patternsbyStructure.addPattern(dp3);
		
		assertTrue(patternsbyStructure.hasAPatternSimilarTo(d1));
		assertTrue(patternsbyStructure.hasAPatternSimilarTo(d2));
		assertTrue(patternsbyStructure.hasAPatternSimilarTo(d3));
		
		d1 = new ArrayList<Descriptor<Object>>();
		d1.add(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3));
		d1.add(new CharacterDescriptor<Object>("Cuerpo", "Forma", "Ovalad0"));
		
		assertTrue(patternsbyStructure.hasAPatternSimilarTo(d1));
		
		System.out.println("Verificar que no se tiene un patron lo suficientemente similar");
		d1 = new ArrayList<Descriptor<Object>>();
		d1.add(new CharacterDescriptor<Object>("Cuerpo", "Forma", "Ovalad0"));
		
		assertFalse(patternsbyStructure.hasAPatternSimilarTo(d1));
		assertFalse(patternsbyStructure.hasAPatternSimilarTo(null));
		d1.clear();
		assertFalse(patternsbyStructure.hasAPatternSimilarTo(d1));
	}

	/**
	 * Test method for {@link searchHintsBase.elements.PatternsbyStructure#whatPatternIsMostSimilarTo(java.util.List)}.
	 */
	@Test
	public void testWhatPatternIsMostSimilarTo() {
		List<Descriptor<Object>> d1, d2, d3;
		DescriptorsPattern dp1, dp2, dp3;
		
		System.out.println("Iniciando pruebas para el método WhatPatternIsMostSimilarTo()");
		
		System.out.println("Verificar que se obtiene el patron más similar que en efecto es");
		d1 = new ArrayList<Descriptor<Object>>();
		d1.add(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3));
		dp1 = new DescriptorsPattern(d1, 1, 0);
		patternsbyStructure.addPattern(dp1);
		
		d2 = new ArrayList<Descriptor<Object>>();
		d2.add(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3));
		d2.add(new CharacterDescriptor<Object>("Cuerpo", "Conformación", "Tiene cerata"));
		dp2 = new DescriptorsPattern(d2, 10, 0);
		patternsbyStructure.addPattern(dp2);
		
		d3 = new ArrayList<Descriptor<Object>>();
		d3.add(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3));
		d3.add(new CharacterDescriptor<Object>("Cuerpo", "Conformación", "Tiene cerata"));
		d3.add(new CharacterDescriptor<Object>("Cuerpo", "Forma", "Ovalad0"));
		dp3 = new DescriptorsPattern(d3, 10, 0);
		patternsbyStructure.addPattern(dp3);
		
		assertSame(dp1, patternsbyStructure.whatPatternIsMostSimilarTo(d1));
		assertSame(dp2, patternsbyStructure.whatPatternIsMostSimilarTo(d2));
		assertSame(dp3, patternsbyStructure.whatPatternIsMostSimilarTo(d3));
		
		d1 = new ArrayList<Descriptor<Object>>();
		d1.add(new CharacterDescriptor<Object>("Cuerpo", "Longitud", 0.3));
		d1.add(new CharacterDescriptor<Object>("Cuerpo", "Forma", "Ovalad0"));
		
		assertNotNull(patternsbyStructure.whatPatternIsMostSimilarTo(d1));
		assertSame(dp3, patternsbyStructure.whatPatternIsMostSimilarTo(d1));
		
		System.out.println("Verificar que se obtiene null si no hay un patrón lo suficientemente similar");
		d1 = new ArrayList<Descriptor<Object>>();
		d1.add(new CharacterDescriptor<Object>("Cuerpo", "Forma", "Ovalad0"));
		
		assertNull(patternsbyStructure.whatPatternIsMostSimilarTo(d1));
		assertNull(patternsbyStructure.whatPatternIsMostSimilarTo(null));
		d1.clear();
		assertNull(patternsbyStructure.whatPatternIsMostSimilarTo(d1));
	}
}
