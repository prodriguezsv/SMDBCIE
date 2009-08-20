/**
 * @see "Categoría Sukia Search Hints Base de SUKIA Smalltalk"
 */
package searchHintsBase;

import searchHintsBase.Lists.PatternsbyStructureList;
import searchHintsBase.Lists.SpecificPatternsbyStructureList;
import searchHintsBase.Lists.WeightedPatternsList;

/**
 * @author Armando
 *
 */
public class HintsBase {
	private PatternsbyStructureList patternsbyStructureList;
	private SpecificPatternsbyStructureList specStructAttrList;
	private WeightedPatternsList weightedPatternsList;

	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public HintsBase() {
		/* This list must contain objects of class: FrequentStructurePatternElt. All patterns therein MUST be successful 
		(i.e., patterns that have contributed to solve cases successfully)*/
		this.setPatternsbyStructureList(new PatternsbyStructureList());

		// This list must contain objects of class: SpecificStructureAttributeElt
		this.setSpecificPatternsbyStructureList(new SpecificPatternsbyStructureList());

		// This list must contain objects of class: WeightedStructureElt
		this.setWeightedPatternsList(new WeightedPatternsList());
	}

	/**
	 * Método de instancia agregado
	 * @param specStructAttrList
	 */
	public void setSpecificPatternsbyStructureList(SpecificPatternsbyStructureList specStructAttrList) {
		this.specStructAttrList = specStructAttrList;
	}

	/**
	 * @see "Método specStructAttrList del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public SpecificPatternsbyStructureList getSpecStructAttrList() {
		return specStructAttrList;
	}

	/**
	 * Método de instancia agregado
	 * @param successfulFreqStructPattList
	 */
	public void setPatternsbyStructureList(
			PatternsbyStructureList patternsbyStructureList) {
		this.patternsbyStructureList = patternsbyStructureList;
	}

	/**
	 * @see "Método successfulFreqStructPattList del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public PatternsbyStructureList getPatternsbyStructureList() {
		return patternsbyStructureList;
	}

	/**
	 * @see "Método weightedGrpHeuristicList del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public WeightedPatternsList getWeightedPatternsList() {
		return weightedPatternsList;
	}

	/**
	 * Método de instancia agregado
	 * @param weightedPatternsList
	 */
	public void setWeightedPatternsList(WeightedPatternsList weightedPatternsList) {
		this.weightedPatternsList = weightedPatternsList;
	}
}
