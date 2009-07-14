/**
 * @see "Categoría Sukia Similarity Assessment de SUKIA Smalltalk"
 */
package similarityAssessment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Armando
 *
 */
public class SimRanges {
	private static List<String> ranges;

	/**
	 * @see "Método initialize del protocolo de clase class initialization en SUKIA SmallTalk"
	 */
	public SimRanges() {
		initialize();
	}

	/**
	 * @see "Método ranges del protocolo de clase class private en SUKIA SmallTalk"
	 * @return
	 */
	private static List<String> getRanges() {
		return ranges;
	}

	/**
	 * Assigns the range names to be used to assess similarity between the values of attributes.
	 * IMPORTANT NOTES:
	 * 1. The first element should ALWAYS be called #diferente and the last one should always be called #igual.
	 * These are special symbols around which functionality in other components will be built.
	 * 2. self should ALWAYS have at least 3 elements (i.e., (#diferente #xxx1 ... #xxxN  #igual)), where #xxxK is some
	 * arbitrary similarity measure).  Otherwise, most methods won't work.  The reason for having at least 3 range names
	 * is simple: if only two were defined (i.e., (#diferente #igual)), none of this similarity assessment stuff would be necessary.
	 * It would just suffice to perform an exact match between two values and, if they don't match the implicit answer is
	 * #diferente; likewise, if the values match, the implicit answer is #igual.
	 * 3. Take a look at method self nonComparable for more range details.
	 * @see "Método initialize del protocolo de clase class initialization en SUKIA SmallTalk"
	 */
	public static void initialize() {
		if (ranges == null) {
			ranges = new ArrayList<String>();
			ranges.add("diferente");
			ranges.add("pocoSimilar");
			ranges.add("relativamenteSimilar");
			ranges.add("medianamenteSimilar");
			ranges.add("considerablementeSimilar");
			ranges.add("altamenteSimilar");
			ranges.add("igual");
		}
	}
	
	/**
	 * @see "Método copy del protocolo de clase class copying en SUKIA SmallTalk"
	 * @return
	 */
	public static List<String> copy() {
		List<String> rangesCopy;
		
		rangesCopy = new ArrayList<String>();
		for( int i = 1; i <= ranges.size(); i++) {
			rangesCopy.add(ranges.get(i-1));
		}

		return rangesCopy; 
	}
	
	/**
	 * @see "Método rangesStartingAt: del protocolo de clase class copying en SUKIA SmallTalk"
	 * @param aRangeName
	 * @return
	 */
	public static List<String> rangesStartingAt(String aRangeName) {
		List<String> outRanges;
		int idx;
		
		outRanges = new ArrayList<String>();
		idx = ranges.indexOf(aRangeName);
		if (idx == -1) return outRanges;
	
		for( int i = idx; i <= ranges.size(); i++) {
			outRanges.add(ranges.get(i-1));
		}
		
		return outRanges;
	}
	
	/**
	 * If a given value does not exist in a given value-weight list, nothing can be said about the
	 * similarity degree of the value.  Thus, return a symbol which expresses this fact.  This symbol
	 * can be regarded as a special type of SimRange with range (-1.0 -1.0)
	 * @see "Método nonComparable del protocolo de clase class non-comparable en SUKIA SmallTalk"
	 * @return
	 */
	public static String nonComparable() {
		return "valorNoComparable";
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
	public static List<ValuedRange> assignValuesUsing(List<WeightedValue> aValueWeightList) {
		List<String> rCopy;
		List<ValuedRange> valuedRangesList;
		ValuedRange vrInstance;
		int p1, p2;
		
		// This algorithm will not work if self has less than 3 ranges defined
		if (getRanges().size() < 3) return null;

		// Create the output list and make a copy of the ranges defines in self.
		valuedRangesList = new ArrayList<ValuedRange>();
		rCopy = copy();

		// Compute the upper value for the #diferente range name
		p1 = (int) Math.round(SimAssessor.maxWeightOf(aValueWeightList) * 100);
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
