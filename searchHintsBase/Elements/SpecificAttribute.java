/**
 * @see "Categoría Sukia Search Hints Elts de SUKIA Smalltalk"
 */
package searchHintsBase.Elements;

/**
 * @author Armando
 *
 */
public class SpecificAttribute implements Comparable<SpecificAttribute>{
	private String name;
	private int frequency;

	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public SpecificAttribute() {
		setName(null);
		setFrequency(0);
	}

	/**
	 * @see "Método attribute: del protocolo adding en SUKIA SmallTalk"
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @see "Método attribute del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @see "Método frequency: del protocolo adding en SUKIA SmallTalk"
	 * @param frequency
	 */
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	/**
	 * @see "Método incrementFrequencyBy: del protocolo adding en SUKIA SmallTalk"
	 * @param frequency
	 */
	public void incrementFrequencyBy(int incrementFrequency) {
		this.frequency = this.frequency + incrementFrequency;
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
	 */
	public int compareTo(SpecificAttribute aSpecificAttribute) {
		return (aSpecificAttribute.getFrequency()-this.getFrequency());
	}
}
