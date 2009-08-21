/**
 * Este paquete agrupa los elementos de las distintas listas que almacenan patrones de b&uacute;squeda
 * de casos previamente resueltos
 * @see "Categor&iacute;a Sukia Search Hints Elts de SUKIA Smalltalk"
 */
package searchHintsBase.Elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author Armando
 *
 */
public class SpecificPatternsbyStructure {
	private String structureName;
	private List<SpecificDescriptorPattern> specificDescriptorPatterns;


	/**
	 * Initialize the frequent grouping heuristic element.  The instance variable 'value' will NOT be an
	 * instance of Value, because (at least for now) it doesn't need to store extra information, such as
	 * value levels, weights or ranges. Thus, the contents of the variable value will be a discreet symbol
	 * (no ranges).
	 * @see "M&eacute;todo initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public SpecificPatternsbyStructure(String structureName) {
		setStructureName(structureName);
		setSpecificDescriptorPatterns(new ArrayList<SpecificDescriptorPattern>()); 
	}

	/**
	 * M&eacute;todo accesor de escritura
	 * @see "M&eacute;todo structureName: del protocolo adding en SUKIA SmallTalk"
	 * @param aStructureName
	 */
	public void setStructureName(String aStructureName) {
		this.structureName = aStructureName;
	}

	/**
	 * M&eacute;todo accesor de lectura
	 * @see "M&eacute;todo structureName del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public String getStructureName() {
		return structureName;
	}

	/**
	 * M&eacute;todo accesor de escritura
	 * @param patterns
	 */
	public void setSpecificDescriptorPatterns(List<SpecificDescriptorPattern> patterns) {
		this.specificDescriptorPatterns = patterns;
	}

	/**
	 * M&eacute;todo accesor de lectura
	 * @see "M&eacute;todo specificAttribute del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<SpecificDescriptorPattern> getSpecificDescriptorPatterns() {
		return specificDescriptorPatterns;
	}
	
	/**
	 * Checks if the attribute name of the argument is already included in the specific attributes list.
	 * If the attribute name already exists, then its frequency is incremented by the frequency value
	 * contained in the argument. Else, the argument is included in the specific attributes list.
	 * @see "M&eacute;todo specificAttribute: del protocolo adding en SUKIA SmallTalk"
	 * @param pattern
	 * @return
	 */
	public boolean addPattern(SpecificDescriptorPattern pattern) {
		SpecificDescriptorPattern sdp;
		
		if (pattern == null)
			return false;
		
		if (pattern.getPattern() == null)
			return false;
		
		if (!pattern.getPattern().getStructure().equals(this.getStructureName()))
			return false;
		
		if ((sdp = this.getSpecificDescriptorPattern(pattern.getPattern().getAttribute())) != null)
			sdp.incrementFrequencyBy(pattern.getFrequency());
		else {
			this.getSpecificDescriptorPatterns().add(pattern);
			Collections.sort(this.getSpecificDescriptorPatterns());
		}
				
		return true;
	}
	
	/**
	 * 
	 * @param asdp
	 * @return
	 */
	public boolean contains(SpecificDescriptorPattern asdp) {
		for (SpecificDescriptorPattern sdp:this.getSpecificDescriptorPatterns()) {
			if (sdp.getPattern().getAttribute().equals(asdp.getPattern().getAttribute()))
				return true;
		}
		
		return false;
	}
	
	/**
	 * 
	 * @param anAttributeName
	 * @return
	 */
	public SpecificDescriptorPattern getSpecificDescriptorPattern(String anAttributeName) {
		for (SpecificDescriptorPattern sdp:this.getSpecificDescriptorPatterns()) {
			if (sdp.getPattern().getAttribute().equals(anAttributeName))
				return sdp;
		}
		
		return null;
	}
}
