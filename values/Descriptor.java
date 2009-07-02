/**
 * 
 */
package values;

import java.util.ArrayList;
import java.util.List;

import domainTheory.Taxon;

/**
 * Clase modificada
 * @see "Clase ValueDescriptor"
 * @author Armando
 */
public abstract class Descriptor {
	private List<Taxon> taxonList;
	
	/**
	 * 
	 */
	public Descriptor() {
		// TODO Auto-generated constructor stub
		setTaxonList(new ArrayList<Taxon>());
	}
		
	/**
	 * Método agregado
	 * @param taxonList
	 */
	public void setTaxonList(List<Taxon> taxonList) {
		this.taxonList = taxonList;
	}

	/**
	 * @see "Método taxonList del protocolo accessing-general en SUKIA SmallTalk"
	 * @return
	 */
	public List<Taxon> getTaxonList() {
		return taxonList;
	}
	
	/**
	 * @see "Método taxon: del protocolo adding-general en SUKIA SmallTalk"
	 * @param aTaxon
	 */
	public void addTaxon(Taxon aTaxon) {			
		this.getTaxonList().add(aTaxon);
	}
	
	public abstract <T> void copyFrom(T aDescriptor, Taxon aTaxon);
}
