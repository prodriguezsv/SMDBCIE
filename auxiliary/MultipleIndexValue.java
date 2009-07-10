/**
 * @see "Categoría Auxiliary de SUKIA Smalltalk"
 */
package auxiliary;

import java.util.ArrayList;
import java.util.List;

import main.Case;

/**
 * Purpose:  An IndexValue is a list that, for most applications, contains two elements: an attribute value (e.g., #red, #square, 15), and a reference
 * to an object.  The object pointed to may be either a case or a norm.  Although this structure is meant to handle two elements, it may accept more
 * elements (by way of the appendSuccessor: method), if and only if these other elements are cases.  When an IndexValue is created and the values
 * are added, its final destination is to be a component of an Index.
 * @author Armando
 *
 */
public class MultipleIndexValue extends IndexValue<Object> {
	private List<Case> successors;

	/**
	 * By default, an IndexValue consists of two elements: the first one is ALWAYS
	 * the index value, and the second one is the successor of  the index. However,
	 * in some situations, an IndexValue may be more than one successor. So, the rule
	 * applies as follows:
	 * 1. If the sucessor is a NORM, then IndexValue may have excatly ONE successor.
	 * 2. If the successor is a CASE, then IndexValue may have one or more successors.
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public MultipleIndexValue() {
		successors = new ArrayList<Case>();
	}
	
	/**
	 * @see "Método successors del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Case> getSuccessors() {
		return successors;
	}

	/**
	 * Método de instancia agregado
	 * @param successors
	 */
	public void setSuccessors(List<Case> successors) {
		this.successors.clear();
		this.successors = successors;
	}

	/**
	 * In some situations, an IndexValue may be more than one successor. The append-rule
	 * applies as follows:
	 * 1. if position nbr. 2 of IndexValue (start of the successors list) is nil, DO NOT append anything;
	 * 2. if position nbr. 2 of IndexValue (start of the successors list) is a Norm, DO NOT append anything;
	 * 3. if aSuccessor is NOT a case, DO NOT append it;
	 * Else, append successor."
	 * @see "Método appendSuccessor: del protocolo adding en SUKIA SmallTalk"
	 * @param aSuccessor
	 */
	public boolean addSuccessor(Case aSuccessor) {
		this.getSuccessors().add(aSuccessor);
		return true;
	}
		
	/**
	 * self = ( nil, nil ) : not valid, OR
	 * self = ( val, nil ) : not valid, OR
	 * self = ( val, succ ) : valid, OR
	 * self = ( val, succ1, ..., succN ) : valid.
	 * @see "Método isValid del protocolo testing en SUKIA SmallTalk"
	 * @return
	 */
	public boolean isValid() {
		return (!(super.getValue() == null || this.successors  == null));
	}
	
	/**
	 * Precondition: self = ( val, succ ) | ( val, succ1, succ2, ..., succN )
	 * @see "Método removeSuccessor: del protocolo removing en SUKIA SmallTalk"
	 * @param aSuccessor
	 */
	public Case removeSuccessor(Case aSuccessor) {
		int i;
		Case s;

		i = 1;
		while (i <= this.getSuccessors().size()) {
			if (this.getSuccessors().get(i-1) == aSuccessor) {
				s = this.getSuccessors().remove(i-1); 
				 
				return s;
			}
			
			i = i + 1;
		}

		/* Postcondition: 	self = ( val, succ ) | ( val, succ1, succ2, ..., succN ) : aSuccessor not found, thus nothing removed from self. Return value: nil, OR
						self = ( val, nil ). Return value: s, OR
						self = (val, succ ). Return value: s, OR
						self = ( val, succ1, ..., succN-1 ). Return value: s*/
		return null;
	}
}
