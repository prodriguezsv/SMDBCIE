/**
 * @see "Categoría Sukia Values de SUKIA Smalltalk"
 */
package ontology.values;

import ontology.taxonomy.Taxon;

/**
 * @author Armando
 *
 */
public class SingleDescriptor<E> extends ValueDescriptor{
	private E value;
	private double weight;
		
	/**
	 * @see "Método value del protocolo accessing-weighted values en SUKIA SmallTalk"
	 * @return
	 */
	public E getValue() {
		return value;
	}

	/**
	 * @see "Método weight del protocolo accessing-weighted values en SUKIA SmallTalk"
	 * @return
	 */
	public double getWeight() {
		return weight;
	}
	
	/**
	 * @see "Método value: del protocolo adding-weighted values en SUKIA SmallTalk"
	 * @param state
	 */
	public void setValue(E value) {
		this.value = value;
	}

	/**
	 * @see "Método weight: del protocolo adding-weighted values en SUKIA SmallTalk"
	 * @param weight
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	/**
	 * @see "Método copyFrom:referencing: del protocolo copying en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @param aTaxon
	 */
	@SuppressWarnings("unchecked")
    @Override
	public <T> void addValues(T aDescriptor, Taxon aTaxon) {
		SingleDescriptor<E> svd;
		
		svd = (SingleDescriptor<E>) aDescriptor;
		this.setValue(svd.getValue());
		this.setWeight(svd.getWeight());
		this.getTaxonList().add(aTaxon);
	}
}
