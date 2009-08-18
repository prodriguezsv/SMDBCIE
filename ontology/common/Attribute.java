/**
 * @see "Categoría Sukia Domain Theory de SUKIA Smalltalk"
 */
package ontology.common;

import java.util.List;

import ontology.taxonomy.Taxon;
import ontology.taxonomy.TaxonomicRank;
import ontology.values.RangeValue;
import ontology.values.SingleValue;
import ontology.values.Values;
import ontology.values.Value;



/**
 * @author Armando
 *
 */
public class Attribute implements Comparable<Attribute>{
	private String name;
	private Values values;

	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 * @see "Método newWithOneLevel del protocolo instance creation en SUKIA SmallTalk"
	 */
	public Attribute() {
		setName(null);
		setValues(new Values());
	}
	
	/**
	 * @see "Método oneLevel del protocolo de clase one level en SUKIA SmallTalk"
	 * @return
	 */
	public static int oneLevel() {
		return 0;
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
	public void setValues(Values values) {
		this.values = values;
	}

	/**
	 * @see "Método values del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Values getValues() {
		return values;
	}

	/**
	 * @see "Método copyFrom:referencing: del protocolo copying en SUKIA SmallTalk"
	 * @param attribute
	 * @param taxon
	 */
	public <T> boolean addValues(Attribute attribute, Taxon taxon) {
		Value nvd;

		// Si this.value es de un unico nivel no se puede agregar valores
		if (this.getValues().size() < attribute.getValues().size())
			return false;

		name = attribute.getName();
		
		for(List<Value> l: attribute.getValues()) {
			for(Value ovd: l) {
				if (ovd instanceof SingleValue)
					nvd = new SingleValue<T>();
				else {
					nvd = new RangeValue();
				}
				
				nvd.addValues(ovd);
				if (this.getValues().size() == attribute.getValues().size())
					this.getValues().addValueDescriptor(nvd, TaxonomicRank.values()[l.indexOf(ovd)+1]);
				else
					this.getValues().addValueDescriptor(nvd, taxon.getLevel());
			}
		}
				
		return true;
	}
	
	/**
	 * Método de instancia agregado
	 */
	public int compareTo(Attribute aAttribute) {
		return this.getName().compareTo(aAttribute.getName());
	}
}
