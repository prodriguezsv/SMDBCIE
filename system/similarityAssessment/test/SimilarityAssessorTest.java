/**
 * 
 */
package system.similarityAssessment.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ontology.CBR.SimilarityDegree;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import system.similarityAssessment.SimilarityAssessor;

/**
 * @author Armando
 *
 */
public class SimilarityAssessorTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Iniciando pruebas para la clase " + SimilarityAssessor.class.getName());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Terminando pruebas para la clase " + SimilarityAssessor.class.getName());
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
	 * Test method for {@link system.similarityAssessment.SimilarityAssessor#distanceBetween(java.lang.Object, java.lang.Object, java.util.Map)}.
	 */
	@Test
	public void testDistanceBetween() {
		Map<Object, Double> valueweightList;
		
		valueweightList = new HashMap<Object, Double>();
		valueweightList.put(1, 0.5);
		valueweightList.put("Ovalado", 0.7);
		valueweightList.put(0.4, 0.9);
		valueweightList.put("Tiene cerata", 1.0);
		
		assertEquals(50, SimilarityAssessor
				.distanceBetween("Tiene cerata", 1, valueweightList));
		
		valueweightList = new HashMap<Object, Double>();
		valueweightList.put(1, 0.5);
		valueweightList.put("Ovalado", 0.7);
		valueweightList.put(0.4, 0.3);
		valueweightList.put("Tiene Cerata", 0.5);
		
		assertEquals(40, SimilarityAssessor.distanceBetween("Ovalado", 0.4, valueweightList));
		
		assertEquals(-1, SimilarityAssessor.distanceBetween("Ovalado", 0.2, valueweightList));
	}

	/**
	 * Test method for {@link system.similarityAssessment.SimilarityAssessor#similarityDegreeOf(java.lang.Object, java.util.Map)}.
	 */
	@Test
	public void testSimilarityDegreeOf() {
		Map<Object, Double> valueweightList;
		
		valueweightList = new HashMap<Object, Double>();
		valueweightList.put(1, 0.5);
		valueweightList.put("Ovalado", 0.7);
		valueweightList.put(0.4, 0.9);
		valueweightList.put("Tiene cerata", 1.0);
		
		assertEquals(50, SimilarityAssessor.similarityDegreeOf(1, valueweightList));
		
		valueweightList = new HashMap<Object, Double>();
		valueweightList.put(1, 0.5);
		valueweightList.put("Ovalado", 0.7);
		valueweightList.put(0.4, 0.3);
		valueweightList.put("Tiene Cerata", 0.5);
		
		assertEquals(60, SimilarityAssessor.similarityDegreeOf(0.4, valueweightList));
		
		assertEquals(-1, SimilarityAssessor.similarityDegreeOf(0.2, valueweightList));
	}

	/**
	 * Test method for {@link system.similarityAssessment.SimilarityAssessor#similarityRangeOf(java.lang.Object, java.util.Map)}.
	 */
	@Test
	public void testSimilarityRangeOf() {
		Map<Object, Double> valueweightList;
		
		valueweightList = new HashMap<Object, Double>();
		valueweightList.put(1, 0.5);
		valueweightList.put("Ovalado", 0.7);
		valueweightList.put(0.4, 0.9);
		valueweightList.put("Tiene cerata", 1.0);
		
		assertEquals(SimilarityDegree.VALORNOCOMPARABLE, SimilarityAssessor.similarityRangeOf(0.3, valueweightList));
		
		valueweightList = new HashMap<Object, Double>();
		valueweightList.put(1, 0.5);
		valueweightList.put("Ovalado", 0.7);
		valueweightList.put(0.4, 0.3);
		valueweightList.put("Tiene Cerata", 0.5);
		
		assertEquals(SimilarityDegree.MEDIANAMENTESIMILAR, SimilarityAssessor.similarityRangeOf(0.4, valueweightList));
	}

	/**
	 * Test method for {@link system.similarityAssessment.SimilarityAssessor#maxValueWeightOf(java.util.Map)}.
	 */
	@Test
	public void testMaxValueWeightOf() {
		Map<Object, Double> valueweightList;
		
		valueweightList = new HashMap<Object, Double>();
		valueweightList.put(1, 0.5);
		valueweightList.put("Ovalado", 0.7);
		valueweightList.put(0.4, 0.9);
		valueweightList.put("Tiene cerata", 1.0);
		
		assertNotNull(SimilarityAssessor.maxValueWeightOf(valueweightList));
		assertEquals("Tiene cerata", SimilarityAssessor.maxValueWeightOf(valueweightList));
		
		valueweightList = new HashMap<Object, Double>();
		valueweightList.put(1, 0.5);
		valueweightList.put("Ovalado", 0.7);
		valueweightList.put(0.4, 0.3);
		valueweightList.put("Tiene Cerata", 0.5);
		
		assertEquals("Ovalado", SimilarityAssessor.maxValueWeightOf(valueweightList));
		
		valueweightList = new HashMap<Object, Double>();
		
		assertNull(SimilarityAssessor.maxValueWeightOf(valueweightList));
	}

	/**
	 * Test method for {@link system.similarityAssessment.SimilarityAssessor#maxWeightOf(java.util.Collection)}.
	 */
	@Test
	public void testMaxWeightOf() {
		List<Double> weights;
		
		weights = new ArrayList<Double>();
		weights.add(0.5);
		weights.add(0.7);
		weights.add(0.9);
		weights.add(1.0);
		
		assertEquals(1.0, SimilarityAssessor.maxWeightOf(weights));
		
		weights = new ArrayList<Double>();
		weights.add(0.5);
		weights.add(0.7);
		weights.add(0.9);
		weights.add(0.8);
		
		assertEquals(0.9, SimilarityAssessor.maxWeightOf(weights));
		
		weights = new ArrayList<Double>();
		
		assertEquals(0, SimilarityAssessor.maxWeightOf(weights));
	}

	/**
	 * Test method for {@link system.similarityAssessment.SimilarityAssessor#assignValuesUsing(java.util.Collection)}.
	 */
	@Test
	public void testAssignValuesUsing() {
		List<Double> weights;
		
		weights = new ArrayList<Double>();
		weights.add(0.5);
		weights.add(0.7);
		weights.add(0.9);
		weights.add(1.0);
		
		assertTrue(SimilarityAssessor.assignValuesUsing(weights).get(SimilarityDegree.values()
				.length-2).equals(SimilarityDegree.IGUAL));
		
		weights = new ArrayList<Double>();
		weights.add(0.5);
		weights.add(0.7);
		weights.add(0.9);
		weights.add(0.8);
		
		assertTrue(SimilarityAssessor.assignValuesUsing(weights).get(SimilarityDegree.values()
				.length-2).getUpperBound() == 100);
		assertTrue(SimilarityAssessor.assignValuesUsing(weights).get(SimilarityDegree.values()
				.length-2).getLowerBound() == 100);
		
		weights = new ArrayList<Double>();
		
		assertNull(SimilarityAssessor.assignValuesUsing(weights));
	}

}
