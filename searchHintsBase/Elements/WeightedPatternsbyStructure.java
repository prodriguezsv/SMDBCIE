/**
 * Este paquete agrupa los elementos de las distintas listas que almacenan patrones de b&uacute;squeda
 * de casos previamente resueltos
 * @see "Categor&iacute;a Sukia Search Hints Elts de SUKIA Smalltalk"
 */
package searchHintsBase.Elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ontology.common.Descriptor;


/**
 * @author Armando
 *
 */
public class WeightedPatternsbyStructure {
	private String structureName;
	private List<WeightedDescriptorPattern> patterns;


	/**
	 * Initialize the frequent grouping heuristic element.  The instance variable 'value' will NOT be an
	 * instance of Value, because (at least for now) it doesn't need to store extra information, such as
	 * value levels, weights or ranges. Thus, the contents of the variable value will be a discreet symbol
	 * (no ranges).
	 * @see "M&eacute;todo initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public WeightedPatternsbyStructure(String structureName) {
		setStructureName(structureName);
		setPatterns(new ArrayList<WeightedDescriptorPattern>()); 
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
	public void setPatterns(List<WeightedDescriptorPattern> patterns) {
		this.patterns = patterns;
	}

	/**
	 * M&eacute;todo accesor de lectura
	 * @see "M&eacute;todo specificAttribute del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<WeightedDescriptorPattern> getPatterns() {
		return patterns;
	}
	
	/**
	 * Checks if the attribute name of the argument is already included in the specific attributes list.
	 * If the attribute name already exists, then its frequency is incremented by the frequency value
	 * contained in the argument. Else, the argument is included in the specific attributes list.
	 * @see "M&eacute;todo specificAttribute: del protocolo adding en SUKIA SmallTalk"
	 * @param pattern
	 * @return
	 */
	public boolean addPattern(WeightedDescriptorPattern pattern) {
		WeightedDescriptorPattern wdp;
		
		if (pattern == null)
			return false;
		
		if (pattern.getPattern() == null)
			return false;
		
		if (!pattern.getPattern().getStructure().equals(this.getStructureName()))
			return false;
		
		if (pattern.getAccumulatedWeight() <= 0)
			return false;
		
		if ((wdp = this.getPattern(pattern.getPattern())) != null) {
			wdp.incrementAccumulatedWeight(pattern.getAccumulatedWeight());
			wdp.incrementNumberTaxa(pattern.getNumberTaxa());
		} else {
			this.getPatterns().add(pattern);
			Collections.sort(this.getPatterns());
		}
				
		return true;
	}
	
	/**
	 * 
	 * @param asdp
	 * @return
	 */
	public boolean contains(WeightedDescriptorPattern asdp) {
		for (WeightedDescriptorPattern wdp:this.getPatterns()) {
			if (wdp.getPattern().equals(asdp.getPattern()))
				return true;
		}
		
		return false;
	}
	
	/**
	 * 
	 * @param aDescriptor
	 * @return
	 */
	public WeightedDescriptorPattern getPattern(Descriptor<Object> aDescriptor) {
		for (WeightedDescriptorPattern wdp:this.getPatterns()) {
			if (wdp.getPattern().equals(aDescriptor))
				return wdp;
		}
		
		return null;
	}
}
