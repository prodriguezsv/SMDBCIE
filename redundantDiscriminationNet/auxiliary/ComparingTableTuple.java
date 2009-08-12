/**
 * @see "Categoría Auxiliary de SUKIA Smalltalk"
 */
package redundantDiscriminationNet.auxiliary;


/**
 * Purpose: Implements the tuples that will be added to a ComparingTable.  A ComparingTableTuple is a list (i.e., OrderedCollection) of three items
 * that represent the following information:
 * 1st element: a descriptor attribute (e.g., #color, #size, #shape);
 * 2nd element: the case-to-insert value corresponding to the attribute, taken from a descriptor in the case's description .  If the case-to-insert description
 * does not contain a descriptor with that attribute, then the corresponding value in the ComparingTableTuple is nil.
 * 3rd element: the case-to-compare value corresponding to the attribute, taken from a descriptor in the case's description.  If the case-to-compare description
 * does not contain a descriptor with that attribute, then the corresponding value in the ComparingTableTuple is nil.
 * Thus, the form of a ComparingTableTuple is: ( #attribute ci-val cc-val ).
 * @author Armando
 *
 */
public class ComparingTableTuple<T> {
	String attribute;
	T aCIValue;
	T aCCValue;
	
	/**
	 * Método constructor agregado
	 */
	public ComparingTableTuple() {
		this.setAttribute(null);
		this.setACCValue(null);
		this.setACIValue(null);
	}
	
	/**
	 * @see "Método newWith:caseToInsertValue:caseToCompareValue: del protocolo de clase instance creation en SUKIA SmallTalk"
	 * @param anAttribute
	 * @param aCIValue
	 * @param aCCValue
	 */
	public ComparingTableTuple(String anAttribute, T aCIValue, T aCCValue) {
		this.setAttribute(anAttribute);
		this.setACCValue(aCCValue);
		this.setACIValue(aCIValue);
	}

	/**
	 * Returns the attribute name
	 * @see "Método attribute del protocolo reading en SUKIA SmallTalk"
	 * @return
	 */
	public String getAttribute() {
		return attribute;
	}

	/**
	 * Método de instancia agregado
	 * @param attribute
	 */
	private void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	/**
	 * Returns the value of the attribute, found in the case-to-insert description
	 * @see "Método aCIValue del protocolo reading en SUKIA SmallTalk"
	 * @return
	 */
	public T getACIValue() {
		return aCIValue;
	}

	/**
	 * Método de instancia agregado
	 * @param value
	 */
	private void setACIValue(T value) {
		aCIValue = value;
	}

	/**
	 * Returns the value of the attribute, found in the case-to-compare description
	 * @see "Método aCCValue del protocolo reading en SUKIA SmallTalk"
	 * @return
	 */
	public T getACCValue() {
		return aCCValue;
	}

	/**
	 * Método de instancia agregado
	 * @param value
	 */
	private void setACCValue(T value) {
		aCCValue = value;
	}

	/**
	 * @see "Método add:caseToInsertValue:caseToCompareValue: del protocolo adding en SUKIA SmallTalk"
	 * @param anAttribute
	 * @param aCIValue
	 * @param aCCValue
	 */
	public void add(String anAttribute, T aCIValue, T aCCValue) {
		this.setAttribute(anAttribute);
		this.setACCValue(aCCValue);
		this.setACIValue(aCIValue);
	}
	
	/**
	 * Método agregado
	 * @param aDescriptor
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean equals(Object aComparingTableTuple) {
		if (aComparingTableTuple == null) return false;
		if (!(aComparingTableTuple instanceof ComparingTableTuple)) return false;
		
		if (this.getAttribute().equals(((ComparingTableTuple<T>)aComparingTableTuple).getAttribute())) {
			if (this.getACCValue() != null) {
				if (!this.getACCValue().equals(((ComparingTableTuple<T>)aComparingTableTuple).getACCValue()))
					return false;
			} else if (((ComparingTableTuple<T>)aComparingTableTuple).getACCValue() != null)
					return false;
			
			if (this.getACIValue() != null) {
				if (!this.getACIValue().equals(((ComparingTableTuple<T>)aComparingTableTuple).getACIValue()))
					return false;
			} else if (((ComparingTableTuple<T>)aComparingTableTuple).getACIValue() != null)
					return false;
			
			return true;
		}
				
		return false;
	}
}
