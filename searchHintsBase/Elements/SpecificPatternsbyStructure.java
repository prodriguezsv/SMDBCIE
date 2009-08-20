/**
 * @see "Categoría Sukia Search Hints Elts de SUKIA Smalltalk"
 */
package searchHintsBase.Elements;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Armando
 *
 */
public class SpecificPatternsbyStructure {
	private String structureName;
	private List<SpecificDescriptorPattern> specificDescriptorPatterns;


	/**
	 * Initialize the frequent grouping heuristic element.  The instance variable 'value' will NOT be an instance of Value,
	 * because (at least for now) it doesn't need to store extra information, such as value levels, weights or ranges. Thus,
	 * the contents of the variable value will be a discreet symbol (no ranges).
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public SpecificPatternsbyStructure(String structureName) {
		setStructureName(structureName);
		setSpecificDescriptorPatterns(new ArrayList<SpecificDescriptorPattern>()); 
	}

	/**
	 * @see "Método structureName: del protocolo adding en SUKIA SmallTalk"
	 * @param aStructureName
	 */
	public void setStructureName(String aStructureName) {
		this.structureName = aStructureName;
	}

	/**
	 * @see "Método structureName del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public String getStructureName() {
		return structureName;
	}

	/**
	 * Método de instancia agregado
	 * @param patterns
	 */
	public void setSpecificDescriptorPatterns(List<SpecificDescriptorPattern> patterns) {
		this.specificDescriptorPatterns = patterns;
	}

	/**
	 * @see "Método specificAttribute del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<SpecificDescriptorPattern> getSpecificDescriptorPatterns() {
		return specificDescriptorPatterns;
	}
	
	/**
	 * Checks if the attribute name of the argument is already included in the specific attributes list.
	 * If the attribute name already exists, then its frequency is incremented by the frequency value
	 * contained in the argument. Else, the argument is included in the specific attributes list.
	 * @see "Método specificAttribute: del protocolo adding en SUKIA SmallTalk"
	 * @param asdp
	 * @return
	 */
	public boolean addSpecificDescriptorPattern(SpecificDescriptorPattern asdp) {
		if (!asdp.getPattern().getStructure().equals(this.getStructureName()))
			return false;
		
		for (SpecificDescriptorPattern sdp:this.getSpecificDescriptorPatterns()) {
			if (asdp.getPattern().getAttribute().equals(sdp.getPattern().getAttribute())) {
				sdp.incrementFrequencyBy(asdp.getFrequency());
				return true;
			}
		}
		this.getSpecificDescriptorPatterns().add(asdp);
		
		return true;
	}
	
	public boolean contains(SpecificDescriptorPattern asdp) {
		for (SpecificDescriptorPattern sdp:this.getSpecificDescriptorPatterns()) {
			if (sdp.getPattern().getAttribute().equals(asdp.getPattern().getAttribute()))
				return true;
		}
		
		return false;
	}
	
	public SpecificDescriptorPattern getSpecificDescriptorPattern(String anAttributeName) {
		for (SpecificDescriptorPattern sdp:this.getSpecificDescriptorPatterns()) {
			if (sdp.getPattern().getAttribute().equals(anAttributeName))
				return sdp;
		}
		
		return null;
	}
}
