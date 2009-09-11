/**
 * Reune una clases que calcula la similaridad de un valor conr respecto a un conjunto de valores con peso
 * @see "Categor&iacute;a Sukia Similarity Assessment de SUKIA Smalltalk"
 */
package system.similarityAssessment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import ontology.CBR.SimilarityDegree;


/**
 * @author Armando
 *
 */
public class SimilarityAssessor {

	/**
	 * @see "M&eacute;todo distanceBetween:and:in: del protocolo de clase class calculating en SUKIA SmallTalk"
	 * @param aValue1
	 * @param aValue2
	 * @param aValueWeightList
	 * @return
	 */
	public static double distanceBetween(Object aValue1, Object aValue2, Map<Object, Double> aValueWeightList) {
		Double w1, w2;
		
		w1 = aValueWeightList.get(aValue1);

		if ((w2 = aValueWeightList.get(aValue2)) == null)
			return -1;
		
		return (Math.abs(Math.round(w1 * 100) - Math.round(w2 * 100)));
	}
	
	/**
	 * @see "M&eacute;todo similarityDegreeOf:in: del protocolo de clase class calculating en SUKIA SmallTalk"
	 * @param aValue
	 * @param aValueWeightList
	 * @return
	 */
	public static double similarityDegreeOf(Object aValue, Map<Object, Double> aValueWeightList) {
		double d;
		
		d = distanceBetween(maxValueWeightOf(aValueWeightList), aValue, aValueWeightList);
		
		if (d == -1) return -1;

		return (100 - d);
	}
	
	/**
	 * @see "M&eacute;todo similarityRangeOf:in: del protocolo de clase class calculating en SUKIA SmallTalk"
	 * @param aValue
	 * @param aValueWeightList
	 * @return
	 */
	public static SimilarityDegree similarityRangeOf(Object aValue, Map<Object, Double> aValueWeightList) {
		double sd, lb, ub;
		List<SimilarityDegree> sr;
		SimilarityDegree r;
		
		sd = similarityDegreeOf(aValue, aValueWeightList);
		if (sd < 0) return SimilarityDegree.VALORNOCOMPARABLE;

		sr = assignValuesUsing(aValueWeightList.values());
		
		while (!(sr.isEmpty())) {
			r = sr.remove(0);
			lb = r.getLowerBound();
			ub = r.getUpperBound();

			if (sd >= lb && sd <= ub)
				return r;
		}
		
		return null;
	}
	
	/**
	 * @see "M&eacute;todo maxValueWeightOf: del protocolo de clase class weight assessment en SUKIA SmallTalk"
	 * @param aValueWeightList
	 * @return
	 */
	public static Object maxValueWeightOf(Map<Object, Double> aValueWeightList) {
		double maxWeight = 0;
		Object idx = null;

		for( Object key: aValueWeightList.keySet())
			if ( aValueWeightList.get(key) > maxWeight) {
				maxWeight = aValueWeightList.get(key);
				idx = key ;
			}

		if (idx == null)
			return null;
		
		return idx;
	}
	
	/**
	 * @see "M&eacute;todo maxWeightOf: del protocolo de clase class weight assessment en SUKIA SmallTalk"
	 * @param aWeightList
	 * @return
	 */
	public static double maxWeightOf(Collection<Double> aWeightList) {
		double maxWeight = 0;
		
		for( Double weight: aWeightList)
			if (weight > maxWeight) maxWeight = weight;
		
		return maxWeight;
	}
	
	/**
	 * Creates a list with the range names and associated range values.
	 * The range values are computed using parameter aValueWeightList.
	 * NOTE: The resulting values for each range are integers, so the union
	 * of ranges will go from 0 to 100.  The reason for having integers instead
	 * of floats (thus having a union of ranges from 0.0 to 1.0) is that floats
	 * present a lot of (decimal digit) of precision problems.
	 * see "M&eacute;todo assignValuesUsing del protocolo de clase class range-value assignment en SUKIA SmallTalk"
	 * @param aWeightSet
	 * @return
	 */
	public static List<SimilarityDegree> assignValuesUsing(Collection<Double> aWeightSet) {
		List<SimilarityDegree> rCopy;
		int p1, p2, size;
		
		if (aWeightSet.isEmpty())
			return null;
		
		// This algorithm will not work if self has less than 3 ranges defined
		if (SimilarityDegree.values().length < 3) return null;

		// Make a copy of the ranges defines in self.
		rCopy = new ArrayList<SimilarityDegree>(Arrays.asList(SimilarityDegree.values()));

		// Compute the upper value for the #diferente range name
		p1 = (int) Math.round(SimilarityAssessor.maxWeightOf(aWeightSet) * 100);
		p1 = 100 - p1;

		// Compute the length of each range
		p2 = Math.round((99 - p1) / ((rCopy.size()) - 3));

		/* Start with range name: #diferente.  This should always be the first range. The range values 
		will go from 0 to p1. Once the range values have been established, increment p1 to be the 
		start of the next range*/
		rCopy.get(0).setLowerBound(0);
		rCopy.get(0).setUpperBound(p1);		
		p1 = p1 + 1;
		
		size = rCopy.size();
		// So long as there are more than two range names, compute their associated range values
		for (int i = 1; i < size - 3; i++) {
			rCopy.get(i).setLowerBound(p1);
			rCopy.get(i).setUpperBound(p1+p2);
			p1 = p1 + p2 + 1;
		}
		
		rCopy.get(size - 3).setLowerBound(p1);
		rCopy.get(size - 3).setUpperBound(99);
		
		// The last range name is always #igual.  Its values will always be the defaults 1.0 and 1.0
		// el último valor de rCopy es SimilarityDegree.VAlORNOCOMPARABLE
		rCopy.get(size - 2).setLowerBound(100);
		rCopy.get(size - 2).setUpperBound(100);
		
		return rCopy; 
	}
}
