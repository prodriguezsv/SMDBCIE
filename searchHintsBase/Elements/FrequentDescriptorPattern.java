/**
 * 
 */
package searchHintsBase.Elements;

import java.util.ArrayList;
import java.util.List;

import ontology.common.Descriptor;


/**
 * @author Armando
 *
 */
public class FrequentDescriptorPattern {
	private int frequency;
	private List<Descriptor<Object>> pattern;

	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public FrequentDescriptorPattern() {
		setPattern(new ArrayList<Descriptor<Object>>());
		setFrequency(0);
	}

	/**
	 * Método de instancia agregado
	 * @param frequency
	 */
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	/**
	 * @see "Método incrementFrequencyBy: del protocolo adding en SUKIA SmallTalk"
	 * @param frequency
	 */
	public void incrementFrequencyBy(int anInteger) {
		this.frequency = this.frequency + anInteger;
	}
	
	/**
	 * @see "Método incrementFrequency del protocolo adding en SUKIA SmallTalk"
	 * @param frequency
	 */
	public void incrementFrequency() {
		this.frequency = this.frequency + 1;
	}
	
	/**
	 * @see "Método frequency del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public int getFrequency() {
		return frequency;
	}

	/**
	 * Método de instancia agregado
	 * @param pattern
	 */
	public void setPattern(List<Descriptor<Object>> pattern) {
		this.pattern = pattern;
	}

	/**
	 * @see "Método pattern del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Descriptor<Object>> getPattern() {
		return pattern;
	}
	
	/**
	 * @see "Método addDescriptor: del protocolo adding en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @return
	 */
	public boolean addDescriptor(Descriptor<Object> aDescriptor) {
		if (this.contains(aDescriptor))
			return false;
		
		return this.getPattern().add(aDescriptor);
	}
	
	public boolean contains(Descriptor<Object> aDescriptor) {
		for (int i = 1; i <= this.getPattern().size(); i++) {
			if (this.getPattern().get(i-1).getAttribute().equals(aDescriptor.getAttribute()) &&
					this.getPattern().get(i-1).getValue().equals(aDescriptor.getValue()))
				return true;
		}
		
		return false;
	}
	
	public double howSimilarTo(List<Descriptor<Object>> aPattern) {
		int c;
		
		c = 0;
		for (int i = 1; i <= aPattern.size(); i++) {
			if (this.contains(aPattern.get(i-1)))
				c = c + 1;
		}

		if (aPattern.size() >= this.getPattern().size())
			return (double)(c / aPattern.size());

		return (double)(c / this.getPattern().size());
	}
}
