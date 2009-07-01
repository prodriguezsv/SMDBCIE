/**
 * 
 */
package domainTheory;

import values.Value;
import values.ValueDescriptor;

/**
 * @author Armando
 *
 */
public class Attribute {
	private String name;
	private Value values;

	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 * @see "Método newWithOneLevel del protocolo instance creation en SUKIA SmallTalk"
	 */
	public Attribute() {
		// TODO Auto-generated constructor stub
		setName(null);
		setValues(new Value());
	}
	
	/**
	 * @see "Método oneLevel del protocolo de clase one level en SUKIA SmallTalk"
	 * @return
	 */
	public static int oneLevel() {
		return 1;
	}
	
	/**
	 * @see "Método name: del protocolo adding en SUKIA SmallTalk"
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @see "Método name del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Metodo de instancia agregado
	 * @param values
	 */
	private void setValues(Value values) {
		this.values = values;
	}

	/**
	 * @see "Método values del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Value getValues() {
		return values;
	}

	/**
	 * @see "Método copyFrom:referencing: del protocolo copying en SUKIA SmallTalk"
	 * @param attribute
	 * @param taxon
	 */
	// Pendiente de traducir
	public void copy(Attribute attribute, Taxon taxon) {
		ValueDescriptor vList, nvd;
		Double ovd;

		if (values.size() < attribute.getValues().size())
			return;

		name = attribute.getName();
		
		for (int i = 1; i <= attribute.getValues().size(); i++) {
			vList = attribute.getValues().get(i);
			
			for (int j = 1; j <= vList.size(); j++) {
				ovd = vList.get(j);
				nvd = new ValueDescriptor();
				
			}
			
		}
				
	}
}
