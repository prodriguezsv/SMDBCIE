/**
 * @see "Categoría Sukia Search Hints Elts de SUKIA Smalltalk"
 */
package searchHintsBase.Elements;

import java.util.ArrayList;
import java.util.List;

import ontology.common.Descriptor;


/**
 * @author Armando
 *
 */
public class FrequentStructurePatternElt implements Comparable<FrequentStructurePatternElt> {
	private String structureName;
	private List<FrequentDescriptorPattern> frequentDescriptorPatternList;

	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public FrequentStructurePatternElt() {
		setStructureName(null);
		setFrequentDescriptorPatternList(new ArrayList<FrequentDescriptorPattern>());
	}

	/**
	 * Método de instancia agregado
	 */
	public int compareTo(FrequentStructurePatternElt aFrequentStructurePatternElt) {
		return (aFrequentStructurePatternElt.getFrequentDescriptorPatternList().get(0).getFrequency()-
				this.getFrequentDescriptorPatternList().get(0).getFrequency());
	}

	/**
	 * @see "Método structureName: del protocolo adding en SUKIA SmallTalk"
	 * @param groupingHeuristicName
	 */
	public void setStructureName(String groupingHeuristicName) {
		this.structureName = groupingHeuristicName;
	}

	/**
	 * @see "Método structureName del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public String getStructureName() {
		return structureName;
	}

	/**
	 * @see "Método frequentDescriptorPatternList: del protocolo adding en SUKIA SmallTalk"
	 * @param frequentDescriptorPatternList
	 */
	public void setFrequentDescriptorPatternList(
			List<FrequentDescriptorPattern> frequentDescriptorPatternList) {
		this.frequentDescriptorPatternList = frequentDescriptorPatternList;
	}

	/**
	 * @see "Método frequentDescriptorPatternList del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<FrequentDescriptorPattern> getFrequentDescriptorPatternList() {
		return frequentDescriptorPatternList;
	}
	
	/**
	 * @see "Método addPattern: del protocolo adding en SUKIA SmallTalk"
	 * @param aFrequentDescriptorPattern
	 * @return
	 */
	public boolean addPattern(FrequentDescriptorPattern aFrequentDescriptorPattern) {
		FrequentDescriptorPattern fdp;
		
		fdp = this.getFDPattern(aFrequentDescriptorPattern.getPattern());
		if (fdp == null) {
			this.getFrequentDescriptorPatternList().add(aFrequentDescriptorPattern);
			return  true; 
		}

		fdp.incrementFrequencyBy(aFrequentDescriptorPattern.getFrequency());
		
		return false;
	}
	
	/**
	 * Determines if the argument aPattern is equal to a pattern contained in self. Being equal to a pattern
	 * in self means two things: 1. both the pattern in self and aPattern MUST be of the same length (i.e., both
	 * patterns must have the same number of Descriptors); 2. all Descriptors in aPattern MUST match the
	 * descriptors of the pattern in self (a 1 to 1 correspondence).
	 * Returns: 	nil - if aPattern is NOT included in self;
	 * an instance of FrequentDescriptorPattern, whose pattern matches the argument.
	 * @see "Método includes: del protocolo testing en SUKIA SmallTalk"
	 * @param aPattern
	 * @return
	 */
	public boolean contains(List<Descriptor<Object>> aPattern) {
		FrequentDescriptorPattern fdp;
		int c;
		
		if (this.getFrequentDescriptorPatternList().isEmpty())
			return false;
		
		// If aPattern's size is greater than the largest pattern in self, then aPattern is not included in self
		if (aPattern.size() > this.getFrequentDescriptorPatternList().get(0).getPattern().size())
			return false;
		
		// Scan the entire pattern list in self
		for (int i = 1; i <= this.getFrequentDescriptorPatternList().size(); i++) {
			// Obtain the next FrequentDescriptorPattern in self
			fdp = this.getFrequentDescriptorPatternList().get(i-1);

			// Both the retrieved FrequentDescriptorPattern and aPattern MUST be of the same length
			if (fdp.getPattern().size() == aPattern.size()) {
				// Scan the entire list of Descriptors in aPattern. Initialize a 'match-keeping counter'
				c = 0;
				for (int j = 1; j <= aPattern.size(); j++) {
					/* If the next Descriptor of aPattern is included in the retrieved FrequentDescriptorPattern 
					pattern, increment the counter*/
					if (fdp.contains(aPattern.get(j-1)))
						c = c + 1;
				}
				// Determine if both patterns are equal. If so, return the FrequentDescriptorPattern instance
				if (c == fdp.getPattern().size())
					return true;
			}
		}
		
		// self did not have a pattern that matches the argument
		return false;
	}
	
	/**
	 * Método de instancia agregado
	 * @param aPattern
	 * @return
	 */
	public FrequentDescriptorPattern getFDPattern(List<Descriptor<Object>> aPattern) {
		FrequentDescriptorPattern fdp;
		int c;
		
		if (this.getFrequentDescriptorPatternList().isEmpty())
			return null;
		
		// If aPattern's size is greater than the largest pattern in self, then aPattern is not included in self
		if (aPattern.size() > this.getFrequentDescriptorPatternList().get(0).getPattern().size())
			return null;
		
		// Scan the entire pattern list in self
		for (int i = 1; i <= this.getFrequentDescriptorPatternList().size(); i++) {
			// Obtain the next FrequentDescriptorPattern in self
			fdp = this.getFrequentDescriptorPatternList().get(i-1);

			// Both the retrieved FrequentDescriptorPattern and aPattern MUST be of the same length
			if (fdp.getPattern().size() == aPattern.size()) {
				// Scan the entire list of Descriptors in aPattern. Initialize a 'match-keeping counter'
				c = 0;
				for (int j = 1; j <= aPattern.size(); j++) {
					/* If the next Descriptor of aPattern is included in the retrieved FrequentDescriptorPattern 
					pattern, increment the counter*/
					if (fdp.contains(aPattern.get(j-1)))
						c = c + 1;
				}
				// Determine if both patterns are equal. If so, return the FrequentDescriptorPattern instance
				if (c == fdp.getPattern().size())
					return fdp;
			}
		}
		
		// self did not have a pattern that matches the argument
		return null;
	}
	
	/**
	 * @see "Método hasAPatternSimilarTo: del protocolo testing en SUKIA SmallTalk"
	 * @param aPattern
	 * @return
	 */
	public boolean hasAPatternSimilarTo(List<Descriptor<Object>> aPattern) {
		FrequentDescriptorPattern p;
		
		p = this.whatPatternIsMostSimilarTo(aPattern);
		
		if (p == null)
			return false;

		return true;
	}
	
	/**
	 * Determines if a pattern (i.e., a list with Descriptors) is sufficiently similar to a pattern contained in self.
	 * Returns:	nil - if aPattern is NOT similar to any of the patterns contained in self;
	 * an instance of FrequentDescriptorPattern - if a similar or equal pattern is found in self.
	 * @see "Método whatPatternIsMostSimilarTo: del protocolo determining similarity en SUKIA SmallTalk"
	 * @param aPattern
	 * @return
	 */
	public FrequentDescriptorPattern whatPatternIsMostSimilarTo(List<Descriptor<Object>> aPattern) {
		int min, max;
		double simThreshold, simRate;

		// Scan the entire pattern list in self
		for (int i = 1; i <= this.getFrequentDescriptorPatternList().size(); i++) {
			// Get the max and min pattern lengths (number of Descriptors) between the current pattern and the argument
			if (this.getFrequentDescriptorPatternList().get(i-1).getPattern().size() >= aPattern.size()) {
				max = this.getFrequentDescriptorPatternList().get(i-1).getPattern().size();
				min = aPattern.size();
			} else {
				max = aPattern.size();
				min = this.getFrequentDescriptorPatternList().get(i-1).getPattern().size();
			}

			// Get the minimal accepted similarity rate, according to the longest pattern
			simThreshold = this.minimalSimilarityRateFor(max);

			// Determine if OK to compare the current pattern against the argument, according to pattern lenghts
			if ((min / max) >= simThreshold) {
				// Determine how similar is the argument to the current pattern in self
				simRate = this.getFrequentDescriptorPatternList().get(i-1).howSimilarTo(aPattern);

				// If both patterns are sufficiently similar, return the current pattern in self
				if (simRate >= simThreshold)
					this.getFrequentDescriptorPatternList().get(i-1);
			}
		}
		
		// There were no patterns in self similar to the argument
		return null;
	}
	
	/**
	 * This method returns the minimal percentage of similarity accepted, according to the size of a pattern,
	 * which is given by the argument.  The idea is to set a flexible similarity threshold, based on two aspects:
	 * 1. the maximum size of any two patterns to be compared; 2. the assumption that the pattern with the
	 * smaller size might make a near complete match (i.e., an exact match of aPatternSize -1 elements)
	 * @see "Método minimalSimilarityRateFor: del protocolo determining similarity en SUKIA SmallTalk"
	 * @param aPatternSize
	 * @return
	 */
	public double minimalSimilarityRateFor(int aPatternSize) {
		/* 1 out of 2 matches: as default, accept only a complete match (i.e., 100% of both elements). The largest
		 pattern is too small*/
		if (aPatternSize <= 2)
			return 1.0;

		// 2 out of 3 matches
		if (aPatternSize == 3)
			return 0.66;

		// 3 out of 4 matches
		if (aPatternSize == 4)
			return 0.75;

		// 4 out of 5 matches
		if (aPatternSize == 5)
			return 0.8;

		// 5 out of 6 matches
		if (aPatternSize == 6)
			return 0.83;
		
		/* (n >= 6) out of ((m > 6) & (n <= m)) matches. Set 85% similarity rate as default. This default is arbitrary.
		NOTE: As an optimization, it might be necessary to increase the 85% minimal similarity rate for larger 
		descriptions, in order to keep, as small as possible, the number of Descriptors that don't match. For 
		example, 85% of 30 Descriptors allows for a minimum number of matches of +- 25 -- and 5 non-matches.  
		So, for 30 Descriptors, an 88% minimal similarity rate might be more suitable; as the number of non-matches 
		could be kept down to +- 4. For 50 Descriptors 91% might be a more suitable minimal similarity rate, as the 
		number of non-matches is kept down to +- 5 (as opposed to +- 8 non-matches is 85% were applied).  However, 
		at this point it is unknown whether descriptions might get THAT large. Thus, this topic will be dealt with when 
		the Reasoner is operational, and real data is used. (HB - 23-May-1999)*/

		return 0.85;
	}
}
