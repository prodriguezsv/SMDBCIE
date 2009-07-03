/**
 * 
 */
package auxiliary;

import java.util.ArrayList;
import java.util.List;
import main.Case;
import main.Descriptor;

/**
 * Purpose:  This class is used when the case-adding process needs to compare the descriptors of two cases.  When a case is being added to the net,
 * every descriptor is evaluated against indices and norms, in order to traverse the net until a spot is found to insert the new case.  If during net traversal
 * another case is found, then a new set of indices and norms must be created according to the similarities and differences between the descriptors of both
 * cases (i.e., the case-to-insert and the case-to-compare --the case found).  In this situation a ComparingTable is created, and ComparingTableTuple's
 * are added to it.
 * @author Armando
 *
 */
public class ComparingTable extends ArrayList<ComparingTableTuple> {

	/**
	 * Método de instancia agregado
	 */
	public ComparingTable() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	/**
	 * Takes the descriptors of aCase1 and aCase2 and:
	 * a) Creates ComparingTableTuple's according to similarities and differences between these descriptors;
	 * b) Each newly created ComparingTableTuple is added to the ComparingTable
	 * @see "Método fillWith:and: del protocolo filling en SUKIA SmallTalk"
	 * @param aCase1
	 * @param aCase2
	 */
	public void fillWith(Case aCase1, Case aCase2) {
		List<Descriptor> desc1, desc2;
		Descriptor d1, d2;
		ComparingTableTuple tuple;

		desc1 = new ArrayList<Descriptor>();
		desc2 = new ArrayList<Descriptor>();
		
		for (int i = 1; i <= 2; i++) {
			if (i == 1) {
				this.copyDescription(aCase1.getDescription(), desc1);
				this.copyDescription(aCase2.getDescription(), desc2);
			} else {
				if (!(desc2.isEmpty())) {
					this.copyDescription(desc2, desc1); 
					while (!(desc2.isEmpty())) desc2.remove(0);
				}					
			}
			
			while (!(desc1.isEmpty())) {
				d1 = desc1.remove(0);
   				d2 = this.removeDescriptorIn(desc2, d1.getAttribute());
   				if (i == 1) {
   					tuple = new ComparingTableTuple(d1.getAttribute(), d1.getValue(), ((d2 == null)? null:d2.getValue()));
   				} else {
   		   			tuple = new ComparingTableTuple(d1.getAttribute(), null, d1.getValue());
   				}
   				this.add(tuple);
			}
   				
		}
	}

	/**
	 * @see "Método copyDescription:into: del protocolo reading en SUKIA SmallTalk"
	 * @param anInputDescription
	 * @param anOutputDescription
	 */
	public void copyDescription(List<Descriptor> anInputDescription, List<Descriptor> anOutputDescription) {
		for (int i = 1; i <= anInputDescription.size(); i++)
			anOutputDescription.add(anInputDescription.get(i-1));
	}
	
	/**
	 * @see "Método extractTuple del protocolo removing en SUKIA SmallTalk"
	 * @return
	 */
	public ComparingTableTuple extractTuple() {
		if (this.isEmpty()) return null;
		
		return this.remove(0);
	}
	
	/**
	 * @see "Método removeDescriptorIn:with: del protocolo removing en SUKIA SmallTalk"
	 * @param aDescription
	 * @param anAttribute
	 * @return
	 */
	public Descriptor removeDescriptorIn(List<Descriptor> aDescription, String anAttribute) {
		if (aDescription.isEmpty()) return null;

		for (int i = 1; i <= aDescription.size(); i++) {
			if (aDescription.get(i-1).getAttribute().equals(anAttribute))
				return aDescription.remove(i-1);
		}
		
		return null;
	}
}
