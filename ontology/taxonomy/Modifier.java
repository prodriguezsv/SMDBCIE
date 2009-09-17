/**
 * 
 */
package ontology.taxonomy;

/**
 * @author Armando
 *
 */
public class Modifier {
	private double structureWeight;
	private double atributeWeight;
	private double valueWeight;

	/**
	 * 
	 */
	public Modifier() {
		
	}

	/**
	 * 
	 */
	public Modifier(double structureWeight, double atributeWeight, double valueWeight) {
		setStructureWeight(structureWeight);
		setAtributeWeight(atributeWeight);
		setValueWeight(valueWeight);
	}
	
	/**
	 * 
	 * @param structureWeight
	 */
	public void setStructureWeight(double structureWeight) {
		this.structureWeight = structureWeight;
	}

	/**
	 * 
	 * @return
	 */
	public double getStructureWeight() {
		return structureWeight;
	}

	/**
	 * 
	 * @param atributeWeight
	 */
	public void setAtributeWeight(double atributeWeight) {
		this.atributeWeight = atributeWeight;
	}

	/**
	 * 
	 * @return
	 */
	public double getAtributeWeight() {
		return atributeWeight;
	}

	/**
	 * 
	 * @param valueWeight
	 */
	public void setValueWeight(double valueWeight) {
		this.valueWeight = valueWeight;
	}

	/**
	 * 
	 * @return
	 */
	public double getValueWeight() {
		return valueWeight;
	}
}
