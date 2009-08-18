/**
 * 
 */
package ontology.taxonomy;

import java.util.ArrayList;
import java.util.List;

import ontology.common.Descriptor;


/**
 * @author Armando
 *
 */
public class DescriptorsIndex {
	private List<Descriptor<Object>> descriptorsIndex;
	private List<List<Taxon>> otherTaxons ;
	
	public DescriptorsIndex() {
		setDescriptorsIndex(new ArrayList<Descriptor<Object>>());
		setOtherTaxons(new ArrayList<List<Taxon>>());
	}
	
	public void setDescriptorsIndex(List<Descriptor<Object>> descriptorsIndex) {
		this.descriptorsIndex = descriptorsIndex;
	}
	public List<Descriptor<Object>> getDescriptorsIndex() {
		return descriptorsIndex;
	}
	public void setOtherTaxons(List<List<Taxon>> otherTaxons) {
		this.otherTaxons = otherTaxons;
	}
	public List<List<Taxon>> getOtherTaxons() {
		return otherTaxons;
	}
}
