/**
 * @see "Categor&iacute;a Sukia Similarity Assessment de SUKIA Smalltalk"
 */
package system.similarityAssessment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ontology.CBR.SimilarityDegree;
import ontology.values.SingleValue;
import ontology.values.Value;


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
	public static double distanceBetween(Object aValue1, Object aValue2, List<Value> aValueWeightList) {
		double w1, w2;
		
		w1 = getTheWeightOf(aValue1, aValueWeightList);
		// Ojo
		if (w1 == -1) return -1;

		w2 = getTheWeightOf(aValue2, aValueWeightList);
		// Ojo
		if (w2 == -1) return -1;
		
		// Ojo
		return (Math.abs(Math.round(w1 * 100) - Math.round(w2 * 100)));
	}
	
	/**
	 * Returns the weight associated to aValue, if aValue exists in aValueWeightList.
	 * If it doesn't exist, returns nil
	 * @see "M&eacute;todo getTheWeightOf:in: del protocolo de clase class calculating en SUKIA SmallTalk"
	 * @param aValue
	 * @param aValueWeightList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static double getTheWeightOf(Object aValue, List<Value> aValueWeightList) {
		for( Value v:aValueWeightList) {
			if (v instanceof SingleValue)
				if (((SingleValue<Object>)v).getValue().equals(aValue))
					((SingleValue<Object>)v).getWeight();
		}
		
		return -1;
	}
	
	/**
	 * @see "Mï¿½todo similarityDegreeOf:in: del protocolo de clase class calculating en SUKIA SmallTalk"
	 * @param aValue
	 * @param aValueWeightList
	 * @return
	 */
	public static double similarityDegreeOf(Object aValue, List<Value> aValueWeightList) {
		double d;
		
		d = distanceBetween(maxValueWeightOf(aValueWeightList).getValue(), aValue, aValueWeightList);
		
		if (d == -1) return -1;

		return (100 - d);
	}
	
	/**
	 * @see "Mï¿½todo similarityRangeOf:in: del protocolo de clase class calculating en SUKIA SmallTalk"
	 * @param aValue
	 * @param aValueWeightList
	 * @return
	 */
	public static SimilarityDegree similarityRangeOf(Object aValue, List<Value> aValueWeightList) {
		double sd, lb, ub;
		List<ValuedRange> sr;
		ValuedRange r;
		
		sd = similarityDegreeOf(aValue, aValueWeightList);
		if (sd < 0) return SimilarityDegree.VALORNOCOMPARABLE;

		sr = assignValuesUsing(aValueWeightList);
		
		while (!(sr.isEmpty())) {
			r = sr.remove(0);
			lb = r.getLowerBound();
			ub = r.getUpperBound();

			if (sd >= lb && sd <= ub)
				return r.getCategoria();
		}
		
		return null;
	}
	
	/**
	 * @see "Mï¿½todo maxValueWeightOf: del protocolo de clase class weight assessment en SUKIA SmallTalk"
	 * @param aValueWeightList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static SingleValue<Object> maxValueWeightOf(List<Value> aValueWeightList) {
		double maxWeight, w;
		int idx;
		
		if (aValueWeightList.get(0) instanceof SingleValue)
			maxWeight = ((SingleValue<Object>)aValueWeightList.get(0)).getWeight();
		else maxWeight = -1;
		
		idx = 1;

		for( int i = 2; i <= aValueWeightList.size(); i++) {
			if (aValueWeightList.get(i-1) instanceof SingleValue)
				w = ((SingleValue<Object>)aValueWeightList.get(i-1)).getWeight();
			else w = -1;
			
			if (w > maxWeight)  idx = i-1 ;
		}

		return (SingleValue<Object>)aValueWeightList.get(idx);
	}
	
	/**
	 * @see "Mï¿½todo maxWeightOf: del protocolo de clase class weight assessment en SUKIA SmallTalk"
	 * @param aValueWeightList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static double maxWeightOf(List<Value> aValueWeightList) {
		double maxWeight, w;
		
		if (aValueWeightList.get(0) instanceof SingleValue)
			maxWeight = ((SingleValue<Object>)aValueWeightList.get(0)).getWeight();
		else maxWeight = -1;
		
		for( int i = 2; i <= aValueWeightList.size(); i++) {
			if (aValueWeightList.get(i-1) instanceof SingleValue)
				w = ((SingleValue<Object>)aValueWeightList.get(i-1)).getWeight();
			else w = -1;
			if (w > maxWeight) maxWeight = w;
		}
		
		return maxWeight;
	}
	
	/**
	 * Creates a list with the range names and associated range values.
	 * The range values are computed using parameter aValueWeightList.
	 * NOTE: The resulting values for each range are integers, so the union
	 * of ranges will go from 0 to 100.  The reason for having integers instead
	 * of floats (thus having a union of ranges from 0.0 to 1.0) is that floats
	 * present a lot of (decimal digit) of precision problems.
	 * see "Método assignValuesUsing del protocolo de clase class range-value assignment en SUKIA SmallTalk"
	 * @param aValueWeightList
	 * @return
	 */
	public static List<ValuedRange> assignValuesUsing(List<Value> aValueWeightList) {
		List<SimilarityDegree> rCopy;
		List<ValuedRange> valuedRangesList;
		ValuedRange vrInstance;
		int p1, p2;
		
		// This algorithm will not work if self has less than 3 ranges defined
		if (SimilarityDegree.values().length < 3) return null;

		// Create the output list and make a copy of the ranges defines in self.
		valuedRangesList = new ArrayList<ValuedRange>();
		rCopy = new ArrayList<SimilarityDegree>(Arrays.asList(SimilarityDegree.values()));

		// Compute the upper value for the #diferente range name
		p1 = (int) Math.round(SimilarityAssessor.maxWeightOf(aValueWeightList) * 100);
		p1 = 100 - p1;

		// Compute the length of each range
		p2 = Math.round((99 - p1) / ((rCopy.size()) - 2));

		/* Start with range name: #diferente.  This should always be the first range. The range values 
		will go from 0 to p1. Once the range values have been established, increment p1 to be the 
		start of the next range*/
		vrInstance = new ValuedRange();
		vrInstance.setLowerBound(0);
		vrInstance.setLowerBound(p1);
		vrInstance.setCategoria(rCopy.remove(0));
		valuedRangesList.add(vrInstance);
		p1 = p1 + 1;
		
		// So long as there are more than two range names, compute their associated range values
		while (rCopy.size() > 2 ) {
			vrInstance = new ValuedRange();
			vrInstance.setLowerBound(p1);
			vrInstance.setLowerBound(p1+p2);
			vrInstance.setCategoria(rCopy.remove(0));
			valuedRangesList.add(vrInstance);
			p1 = p1 + p2 + 1;
		}
		
		// Compute the range values for the before-last range. Its upper bound will always be 99
		vrInstance = new ValuedRange();
		vrInstance.setLowerBound(p1);
		vrInstance.setLowerBound(99);
		vrInstance.setCategoria(rCopy.remove(0));
		valuedRangesList.add(vrInstance);
		
		// The last range name is always #igual.  Its values will always be the defaults 1.0 and 1.0
		vrInstance = new ValuedRange();
		vrInstance.setLowerBound(100);
		vrInstance.setLowerBound(100);
		vrInstance.setCategoria(rCopy.remove(0));
		valuedRangesList.add(vrInstance);
		
		return valuedRangesList; 
	}
}
