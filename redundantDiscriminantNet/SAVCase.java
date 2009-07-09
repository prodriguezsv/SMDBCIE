/**
 * @see "Categoría Sukia Redundant Discriminant Net de SUKIA Smalltalk"
 */
package redundantDiscriminantNet;

import java.util.ArrayList;
import java.util.List;

import auxiliary.SingleIndexValue;

import main.Case;
import main.Descriptor;
import main.Node;

/**
 * Purpose: Specialization of the class Case; the main difference between these two classes is that the SAVCase's description contains
 * three-tuple, structure-attribute-value SAVDescriptor's, instead of the regular two-tuple, attibute-value Descriptor's. Because of this
 * difference, the SAVCase description must be modified before the net building methods add them to the redundant discrimination nets.
 * In general terms, the SAVCase's description is modified as follows:
 * a) Create a backup copy of the original description (the one with three-tuple SAVDescriptor's) and save it in the instance variable descriptionCopy.
 * b) Group all SAVDescriptor's in the description by structure name.
 * c) For each of the grouped SAVDescriptor: create a new instance of Descriptor, extract the attribute-value part from the SAVDescriptor and
 * add it to the newly created Descriptor.
 * d) Create a new case description based on the current structure name-group, using all the created Descriptor's.  The resulting description will be,
 * of course, a subset of the original description.
 * Next, the SAVCase is added to a net represented by the structure name-group criterion.  Once the adding process is over, the case description
 * is flushed, and a new description is created in terms of the next structure name-group.  The same case is then added to another net represented
 * by the corresponding criterion.  The case-adding process repeats until all the structure name-groups have been worked.  The last step before
 * continuing on another case is to restore the original description from the the descriptionCopy variable.
 * @author Armando
 *
 */
public class SAVCase extends Case {
	private List<Descriptor<Object>> descriptionCopy; //Keeps a copy of the SAVCase's original description, while the case is being added to all the required nets.
	private List<String> structureCopy;				  // Keeps a list of the structure names. The first structure of the list is the one currently being processed, along with all the decriptors in the case's description.
	private List<StructureIndex> predecessors;
	

	/**
	 * Initializes the variables of its object parent.  The local variable 'descriptionCopy' is initialized as an instance of
	 * OrderedCollection
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public SAVCase() {
		super();
		descriptionCopy = new ArrayList<Descriptor<Object>>();
		structureCopy = new ArrayList<String>();
	}

	/**
	 * @see "Método descriptionCopy del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Descriptor<Object>> getDescriptionCopy() {
		return descriptionCopy;
	}

	/**
	 * Método de instancia agregado
	 * @param descriptionCopy
	 */
	public void setDescriptionCopy(List<Descriptor<Object>> descriptionCopy) {
		this.descriptionCopy = descriptionCopy;
	}

	/**
	 * @see "Método structureCopy del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<String> getStructureCopy() {
		return structureCopy;
	}

	/**
	 * Método de instancia agregado
	 * @param structureCopy
	 */
	public void setStructureCopy(List<String> structureCopy) {
		this.structureCopy = structureCopy;
	}
	
	/**
	 * Returns the current structure name (the one being handled in some way).  If the list is empty, returns nil.
	 * NOTE: This method assumes the first structure in the list as the current one. It is the responsiblity of the
	 * driver process to remove the current structure from the list when not needed any more
	 * @see "Método currentStructure del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public String getCurrentStructure() {
		if (structureCopy.isEmpty()) return null;
		return structureCopy.get(0);
	}
	
	/**
	 * Implemented by SAVCase.  For polymorphism reasons, this method is needed by Case, since a net may be composed of Case's or SAVCase's
	 * @see "Método prepareDescriptionWith del protocolo special en SUKIA SmallTalk"
	 * @param aStructure
	 */
	public void prepareDescriptionWith(String aStructure) {
		List<String> sList;
		Descriptor<Object> aDescriptor;
		
		this.backupDescription();
		this.getDescription().clear();

		sList = new ArrayList<String>();
		sList.add(aStructure);
		this.copyStructureListWith(sList);
		
		for( int i = 1; i <= this.getDescriptionCopy().size(); i++) {
			if (((SAVDescriptor)this.getDescriptionCopy().get(i-1)).getStructure().equals(aStructure)) {
				aDescriptor = new Descriptor<Object>();
				aDescriptor.add(this.getDescriptionCopy().get(i-1).getAttribute(), this.getDescriptionCopy().get(i-1).getValue());
				this.addToDescription(aDescriptor);
			}
		}
	}
	
	/**
	 * Restores a SAV case's original description into the variable 'description'
	 * @see "Método restoreDescription del protocolo copying en SUKIA SmallTalk"
	 */
	public void restoreDescription() {
		this.getDescription().clear();
		
		for( int i = 1; i <= this.getDescriptionCopy().size(); i++) {
			this.getDescription().add(descriptionCopy.get(i-1));
		}
	}
	
	/**
	 * @see "Método addPredecessorWith:and: del protocolo adding en SUKIA SmallTalk"
	 */
	public boolean addPredecessor(Node aPredecessor, Object aValue) {
		String aStructure;
		StructureIndex sList;
		SingleIndexValue<Node> ixv;
		int s;
		
		aStructure = this.getCurrentStructure();
		if (aStructure == null) return false;

		sList = null;
		s = 1;
		while (s <= this.predecessors.size()) {
			if (this.predecessors.get(s-1).getLabel().equals(aStructure)) {
				sList = this.predecessors.get(s-1);
				s = this.predecessors.size() + 1;
			} else s = s + 1;
		}
		
		if (sList == null) {
			sList = new StructureIndex();
			sList.setLabel(aStructure);
		}
		
		ixv = new SingleIndexValue<Node>();
		ixv.setValue(aValue);
		ixv.setSuccessor(aPredecessor);
		sList.addIndexValue(ixv);
		if (!(this.predecessors.contains(sList)))
			return this.predecessors.add(sList);
		
		return false;
	}
	
	/**
	 * Creates a copy of the SAV case's description
	 * @see "Método backupDescription del protocolo copying en SUKIA SmallTalk"
	 */
	public void backupDescription() {
		this.clear();
		
		for( int i = 1; i <= this.getDescription().size(); i++) {
			this.getDescriptionCopy().add(this.getDescription().get(i-1));
		}
	}
	
	/**
	 * @see "Método copyStructureListWith: del protocolo copying en SUKIA SmallTalk"
	 * @param aSList
	 * @return
	 */
	public boolean copyStructureListWith(List<String> aSList) {
		if (aSList.isEmpty()) return false;
		
		for( int i = 1; i <= aSList.size(); i++) {
			this.getStructureCopy().add(aSList.get(i-1));
		}
		
		return true;
	}
	
	public void removeCurrentStructure() {
		this.getStructureCopy().remove(0);
	}
	
	public boolean removePredecessor(Node aPredecessor,Object aValue) {
		String currStructure;
		int s, x;
		
		currStructure = this.getCurrentStructure();
		if (currStructure == null) return false;

		s = 1;
		while (s <= this.predecessors.size()) {
			if (!(this.predecessors.get(s-1).getLabel().equals(currStructure)))
				s = s + 1;
			else {
				x = 1;
				while (x <= this.predecessors.get(s-1).getSuccessors().size()) {
					if (this.predecessors.get(s-1).getSuccessors().get(x-1).getValue().equals(aValue) &&
							((SingleIndexValue<Node>)this.predecessors.get(s-1).getSuccessors().get(x-1)).getSuccessor().equals(aPredecessor)) {
						this.predecessors.get(s-1).getSuccessors().remove(x-1); 
						return true;
					} else x = x + 1;
				}
				s = this.predecessors.size() + 1;
			}
		}

		return false;
	}
	
	public void clear() {
		super.clear();
		this.getStructureCopy().clear();
		this.getDescriptionCopy().clear();
	}
	
	/**
	 * @see "Método flushDescriptionCopy del protocolo resetting en SUKIA SmallTalk"
	 * @return
	 */
	public void flushDescriptionCopy() {
		this.getDescriptionCopy().clear();
	}
	
	/**
	 * @see "Método flushStructureCopy del protocolo resetting en SUKIA SmallTalk"
	 * @return
	 */
	public void flushStructureCopy() {
		this.getStructureCopy().clear();
	}

}
