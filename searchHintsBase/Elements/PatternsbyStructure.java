/**
 * Este paquete agrupa los elementos de las distintas listas que almacenan patrones de b&uacute;squeda
 * de casos previamente resueltos
 * @see "Categor&iacute;a Sukia Search Hints Elts de SUKIA Smalltalk"
 */
package searchHintsBase.Elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ontology.common.Description;
import ontology.common.Descriptor;


/**
 * Representa una clase que almacena los patrones por estructura
 * @author Armando
 *
 */
public class PatternsbyStructure implements Comparable<PatternsbyStructure> {
	private String structureName;
	private List<DescriptorsPattern> patterns;
	
	/**
	 * Constructor alternativo
	 * @see "M&eacute;todo initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public PatternsbyStructure(String structureName) {
		setStructureName(structureName);
		setPatterns(new ArrayList<DescriptorsPattern>());
	}

	/**
	 * @see "M&eacute;todo structureName: del protocolo adding en SUKIA SmallTalk"
	 * @param groupingHeuristicName
	 */
	public void setStructureName(String groupingHeuristicName) {
		this.structureName = groupingHeuristicName;
	}

	/**
	 * @see "M&eacute;todo structureName del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public String getStructureName() {
		return structureName;
	}

	/**
	 * @see "M&eacute;todo frequentDescriptorPatternList: del protocolo adding en SUKIA SmallTalk"
	 * @param patterns
	 */
	public void setPatterns(List<DescriptorsPattern> patterns) {
		this.patterns = patterns;
	}

	/**
	 * @see "M&eacute;todo frequentDescriptorPatternList del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<DescriptorsPattern> getPatterns() {
		return patterns;
	}
	
	/**
	 * Compara el primer elemento de la lista de patrones con aPattern por frecuencia de &eacute;xito,
	 * Se supone que la lista de patrones está ordenada en orden descendente por tama&ntilde;o de la lista
	 * de descriptores. Asi que aPattern.getDescriptorsPatterns().get(0) tiene el mayor tama&ntilde;o
	 */
	public int compareTo(PatternsbyStructure aPattern) {
		return (aPattern.getPatterns().get(0).getSuccessFrequency()-
				this.getPatterns().get(0).getSuccessFrequency());
	}
	
	/**
	 * @see "M&eacute;todo addPattern: del protocolo adding en SUKIA SmallTalk"
	 * @param pattern
	 * @return
	 */
	public boolean addPattern(DescriptorsPattern pattern) {
		DescriptorsPattern dp;
		
		if (pattern == null)
			return false;
		
		if (pattern.getPattern().getDescriptors().isEmpty())
			return false;
		
		if (!this.getStructureName().equals(((Descriptor)pattern.getPattern().getDescriptors().get(0)).getStructure()))
			return false;
		
		if ((dp = this.getPattern(pattern.getPattern())) != null)
			dp.incrementSuccessFrequencyBy(pattern.getSuccessFrequency());
		else {
			this.getPatterns().add(pattern);
			Collections.sort(this.getPatterns());
		}
		
		return true;
	}
	
	/**
	 * Determines if the argument aPattern is equal to a pattern contained in self. Being equal to a pattern
	 * in self means two things: 1. both the pattern in self and aPattern MUST be of the same length (i.e., both
	 * patterns must have the same number of Descriptors); 2. all Descriptors in aPattern MUST match the
	 * descriptors of the pattern in self (a 1 to 1 correspondence).
	 * Returns: 	nil - if aPattern is NOT included in self;
	 * an instance of FrequentDescriptorPattern, whose pattern matches the argument.
	 * @see "M&eacute;todo includes: del protocolo testing en SUKIA SmallTalk"
	 * @param aPattern
	 * @return
	 */
	public DescriptorsPattern getPattern(Description aPattern) {
		
		if (aPattern == null)
			return null;
		if (this.getPatterns().isEmpty())
			return null;
		
		// If aPattern's size is greater than the largest pattern in this list, then aPattern is not included
		// in this list
		if (aPattern.getDescriptors().size() > this.getPatterns().get(0).getPattern().getDescriptors().size())
			return null;
		
		// Scan the entire pattern in this list
		for (DescriptorsPattern dp:this.getPatterns()) {
			// Both the retrieved FrequentDescriptorPattern and aPattern MUST be of the same length
			if (dp.getPattern().equals(aPattern))
					return dp;
		}
		
		// self did not have a pattern that matches the argument
		return null;
	}
	
	/**
	 * @see "M&eacute;todo hasAPatternSimilarTo: del protocolo testing en SUKIA SmallTalk"
	 * @param aPattern
	 * @return
	 */
	public boolean hasAPatternSimilarTo(Description aPattern) {
		DescriptorsPattern p;
		
		p = this.whatPatternIsMostSimilarTo(aPattern);
		
		if (p == null)
			return false;

		return true;
	}
	
	/**
	 * Determines if a pattern (i.e., a list with Descriptors) is sufficiently similar to a pattern contained
	 * in this list.
	 * @see "M&eacute;todo whatPatternIsMostSimilarTo: del protocolo determining similarity en SUKIA SmallTalk"
	 * @param aPattern
	 * @return nil - if aPattern is NOT similar to any of the patterns contained in self; an instance of
	 * DescriptorsPattern - if a similar or equal pattern is found in this list.
	 */
	public DescriptorsPattern whatPatternIsMostSimilarTo(Description aPattern) {
		int min, max;
		double simThreshold, simRate, maxSimRate = 0;
		DescriptorsPattern dpret = null;

		if (aPattern == null)
			return null;
		if (this.getPatterns().isEmpty())
			return null;
		
		// Scan the entire pattern list in this list
		for (DescriptorsPattern dp: this.getPatterns()) {
			// Get the max and min pattern lengths (number of Descriptors) between the current pattern and the argument
			if (dp.getPattern().getDescriptors().size() >= aPattern.getDescriptors().size()) {
				max = dp.getPattern().getDescriptors().size();
				min = aPattern.getDescriptors().size();
			} else {
				max = aPattern.getDescriptors().size();
				min = dp.getPattern().getDescriptors().size();
			}

			// Get the minimal accepted similarity rate, according to the longest pattern
			simThreshold = this.minimalSimilarityRate(max);

			// Determine if OK to compare the current pattern against the argument, according to pattern lenghts
			if ((min / (double)max) >= simThreshold) {
				// Determine how similar is the argument to the current pattern in this list
				simRate = dp.howSimilarTo(aPattern);

				// If both patterns are sufficiently similar, return the current pattern in this list
				if (simRate >= simThreshold)
					if (maxSimRate < simRate) {
						maxSimRate = simRate;
						dpret = dp;
					}
			}
		}
		
		// There were no patterns in this list similar to the argument
		return dpret;
	}
	
	/**
	 * This method returns the minimal percentage of similarity accepted, according to the size of a pattern,
	 * which is given by the argument.  The idea is to set a flexible similarity threshold, based on two
	 * aspects:
	 * 1. the maximum size of any two patterns to be compared;
	 * 2. the assumption that the pattern with the smaller size might make a near complete match
	 * (i.e., an exact match of aPatternSize -1 elements)
	 * @see "M&eacute;todo minimalSimilarityRateFor: del protocolo determining similarity en SUKIA SmallTalk"
	 * @param aPatternSize
	 * @return
	 */
	private double minimalSimilarityRate(int aPatternSize) {
		/* 1 out of 2 matches: as default, accept only a complete match (i.e., 100% of both elements).
		 * The largest pattern is too small*/
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
