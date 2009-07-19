/**
 * @see "Categor�a Sukia Similarity Assessment de SUKIA Smalltalk"
 */
package system.similarityAssessment;

import java.util.List;

import onthology.values.SingleDescriptor;
import onthology.values.ValueDescriptor;


/**
 * @author Armando
 *
 */
public class SimAssessor {

	/**
	 * @see "M�todo distanceBetween:and:in: del protocolo de clase class calculating en SUKIA SmallTalk"
	 * @param aValue1
	 * @param aValue2
	 * @param aValueWeightList
	 * @return
	 */
	public static double distanceBetween(Object aValue1, Object aValue2, List<ValueDescriptor> aValueWeightList) {
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
	 * @see "M�todo getTheWeightOf:in: del protocolo de clase class calculating en SUKIA SmallTalk"
	 * @param aValue
	 * @param aValueWeightList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static double getTheWeightOf(Object aValue, List<ValueDescriptor> aValueWeightList) {
		for( int i = 1; i <= aValueWeightList.size(); i++) {
			if (aValueWeightList.get(i-1) instanceof SingleDescriptor)
				if (((SingleDescriptor<Object>)aValueWeightList.get(i-1)).getValue().equals(aValue))
					((SingleDescriptor<Object>)aValueWeightList.get(i-1)).getWeight();
		}
		
		return -1;
	}
	
	/**
	 * @see "M�todo similarityDegreeOf:in: del protocolo de clase class calculating en SUKIA SmallTalk"
	 * @param aValue
	 * @param aValueWeightList
	 * @return
	 */
	public static double similarityDegreeOf(Object aValue, List<ValueDescriptor> aValueWeightList) {
		double d;
		
		d = distanceBetween(maxValueWeightOf(aValueWeightList).getValue(), aValue, aValueWeightList);
		
		if (d == -1) return -1;

		return (100 - d);
	}
	
	/**
	 * @see "M�todo similarityRangeOf:in: del protocolo de clase class calculating en SUKIA SmallTalk"
	 * @param aValue
	 * @param aValueWeightList
	 * @return
	 */
	public static String similarityRangeOf(Object aValue, List<ValueDescriptor> aValueWeightList) {
		double sd, lb, ub;
		List<ValuedRange> sr;
		ValuedRange r;
		
		sd = similarityDegreeOf(aValue, aValueWeightList);
		if (sd < 0) return SimRanges.nonComparable();

		sr = SimRanges.assignValuesUsing(aValueWeightList);
		
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
	 * @see "M�todo maxValueWeightOf: del protocolo de clase class weight assessment en SUKIA SmallTalk"
	 * @param aValueWeightList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static SingleDescriptor<Object> maxValueWeightOf(List<ValueDescriptor> aValueWeightList) {
		double maxWeight, w;
		int idx;
		
		if (aValueWeightList.get(0) instanceof SingleDescriptor)
			maxWeight = ((SingleDescriptor<Object>)aValueWeightList.get(0)).getWeight();
		else maxWeight = -1;
		
		idx = 1;

		for( int i = 2; i <= aValueWeightList.size(); i++) {
			if (aValueWeightList.get(i-1) instanceof SingleDescriptor)
				w = ((SingleDescriptor<Object>)aValueWeightList.get(i-1)).getWeight();
			else w = -1;
			
			if (w > maxWeight)  idx = i-1 ;
		}

		return (SingleDescriptor<Object>)aValueWeightList.get(idx);
	}
	
	/**
	 * @see "M�todo maxWeightOf: del protocolo de clase class weight assessment en SUKIA SmallTalk"
	 * @param aValueWeightList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static double maxWeightOf(List<ValueDescriptor> aValueWeightList) {
		double maxWeight, w;
		
		if (aValueWeightList.get(0) instanceof SingleDescriptor)
			maxWeight = ((SingleDescriptor<Object>)aValueWeightList.get(0)).getWeight();
		else maxWeight = -1;
		
		for( int i = 2; i <= aValueWeightList.size(); i++) {
			if (aValueWeightList.get(i-1) instanceof SingleDescriptor)
				w = ((SingleDescriptor<Object>)aValueWeightList.get(i-1)).getWeight();
			else w = -1;
			if (w > maxWeight) maxWeight = w;
		}
		
		return maxWeight;
	}
}
