/**
 * Paquete que reune clases auxiliares para el paquete redundantDiscrininationNet
 * @see "Categoría Auxiliary de SUKIA Smalltalk"
 */
package redundantDiscriminationNet.auxiliary;

import jade.util.leap.Iterator;

import java.util.ArrayList;

import ontology.common.Description;
import ontology.common.Descriptor;

/**
 * This class is used when the case-adding process needs to compare the descriptors of two cases.  When a case
 * is being added to the net, every descriptor is evaluated against indices and norms, in order to traverse
 * the net until a spot is found to insert the new case.  If during net traversal another case is found, then
 * a new set of indices and norms must be created according to the similarities and differences between the
 * descriptors of both cases (i.e., the case-to-insert and the case-to-compare --the case found).  In this
 * situation a ComparingTable is created, and ComparingTableTuple's are added to it.
 * @author Armando
 *
 */
@SuppressWarnings("serial")
public class ComparingTable extends ArrayList<ComparingTableTuple<Object>> {
	
	/**
	 * Takes the descriptors of desc1 and desc2 and:
	 * a) Creates ComparingTableTuple's according to similarities and differences between these descriptors;
	 * b) Each newly created ComparingTableTuple is added to the ComparingTable
	 * @see "Método fillWith:and: del protocolo filling en SUKIA SmallTalk"
	 * @param desc1 La decripción del caso1
	 * @param desc2 La decripción del caso2
	 */
	public void fill(Description desc1, Description desc2) {
		Descriptor d2;
		ComparingTableTuple<Object> tuple;
		
		Iterator i = desc1.getAllDescriptors();
		
		while (i.hasNext()) {
			Descriptor d = (Descriptor) i.next(); 			
			d2 = this.getDescriptor(desc2, d.getAttribute());
			
			tuple = new ComparingTableTuple<Object>(d.getAttribute(), d.getValue(), ((d2 == null)? null:d2.getValue()));
   			
			this.add(tuple);
		}
		
		Iterator j = desc2.getAllDescriptors();
		
		while (j.hasNext()) {
			Descriptor d = (Descriptor) j.next(); 				
			d2 = this.getDescriptor(desc1, d.getAttribute());
			
			if (d2 == null) {
				tuple = new ComparingTableTuple<Object>(d.getAttribute(), null, d.getValue());
   				this.add(tuple);
			}
		}		
	}
	
	/**
	 * Remueve el primer elemento 
	 * @see "Método extractTuple del protocolo removing en SUKIA SmallTalk"
	 * @return El primer elemento
	 */
	public ComparingTableTuple<Object> extractTuple() {
		if (this.isEmpty()) return null;
		
		return this.remove(0);
	}
	
	/**
	 * Obtiene el descriptor de aDescription que posee el atributo anAttribute
	 * @see "Método removeDescriptorIn:with: del protocolo removing en SUKIA SmallTalk"
	 * @param aDescription La descripci&oacute;n en la que se busca 
	 * @param anAttribute El atributo a buscar
	 * @return El descriptor de aDescription que posee el atributo anAttribute o null si no existe
	 */
	private Descriptor getDescriptor(Description aDescription, String anAttribute) {
		if (aDescription.getDescriptors().isEmpty()) return null;

		for (int i = 1; i <= aDescription.getDescriptors().size(); i++) {
			if (((Descriptor)aDescription.getDescriptors().get(i-1)).getAttribute().equals(anAttribute))
				return (Descriptor)aDescription.getDescriptors().get(i-1);
		}
		
		return null;
	}
}
