/**
 * 
 */
package ontology.taxonomy;

/**
 * @author Armando
 *
 */
@SuppressWarnings("serial")
public class Modifier implements jade.content.Concept {
	private double structureWeight;
	private double attributeWeight;
	private double valueWeight;

	/**
	 * 
	 */
	public Modifier() {
		this._internalInstanceName = "";
	}

	private String _internalInstanceName = null;

  	public Modifier(String instance_name) {
	  this._internalInstanceName = instance_name;
  	}

  	public String toString() {
	  return _internalInstanceName;
  	}

	/**
	 * 
	 */
	public Modifier(double structureWeight, double atributeWeight, double valueWeight) {
		setStructureWeight(structureWeight);
		setAttributeWeight(atributeWeight);
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
	public void setAttributeWeight(double atributeWeight) {
		this.attributeWeight = atributeWeight;
	}

	/**
	 * 
	 * @return
	 */
	public double getAttributeWeight() {
		return attributeWeight;
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
