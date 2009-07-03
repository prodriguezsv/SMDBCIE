/**
 * 
 */
package auxiliary;

import java.util.ArrayList;
import java.util.List;

import main.Node;

/**
 * @author Armando
 *
 */
public class IndexValue <T> {
	private List<Node> successors;
	private T value;

	/**
	 * By default, an IndexValue consists of two elements: the first one is ALWAYS
	 * the index value, and the second one is the successor of  the index. However,
	 * in some situations, an IndexValue may be more than one successor. So, the rule
	 * applies as follows:
	 * 1. If the sucessor is a NORM, then IndexValue may have excatly ONE successor.
	 * 2. If the successor is a CASE, then IndexValue may have one or more successors.
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public IndexValue() {
		// TODO Auto-generated constructor stub
		successors = null;
		value = null;
	}

	/**
	 * Sets self to default values: self = (nil, nil)
	 * @see "Método clear del protocolo initializing en SUKIA SmallTalk"
	 */
	public void clear() {
		successors.clear();
		successors = null;
		value = null;
	}

	/**
	 * Precondition: self = ( nil, nil ) | ( val, nil ) | ( val, succ ) | ( val, succ1, ..., succN )
	 * @see "Método successors del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Node> getSuccessors() {
		return successors;
	}

	
	public void setSuccessors(List<Node> successors) {
		this.successors = successors;
	}

	/**
	 * @see "Método value del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public T getValue() {
		return value;
	}

	/**
	 * @see "Método addValue: del protocolo adding-private en SUKIA SmallTalk"
	 * @param value
	 */
	public void setValue(T value) {
		this.value = value;
	}
	
	/**
	 * @see "Método add:and: del protocolo adding en SUKIA SmallTalk"
	 * @param aValue
	 * @param aSuccessor
	 */
	// Pendiente de traducir
	public void add(T aValue, Node aSuccessor) {
		/* Precondition: self = (nil, nil), or self = (v, s): 
		This method is to be used when an IndexValue instance has only two values,
		 i.e., ( nil, nil ) or ( value, successor ), where successor is either a Norm or a Case.*/

		if (this.getSuccessors().size() > 1) return;

		// Add the value first.
		this.setValue(aValue);

		// Add the successor (Case or Norm) next
		this.addSuccessor(aSuccessor);
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
	// pendiente de traducir
	public void appendSuccessor(Node aSuccessor) {
		
	}
	
	/**
	 * @see "Método addSuccessor: del protocolo adding-private en SUKIA SmallTalk"
	 * @param aSuccessor
	 */
	// pendiente de traducir
	public void addSuccessor(Node aSuccessor) {
		
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
		return (!(this.getValue() == null || this.successors  == null));
	}
	
	/**
	 * @see "Método removeSuccessor: del protocolo removing en SUKIA SmallTalk"
	 * @param aSuccessor
	 */
	public void removeSuccessor(Node aSuccessor) {
			
	}
	
	/**
	 * @see "Método removeSuccessors del protocolo removing en SUKIA SmallTalk"
	 */
	public List<Node> removeSuccessors() {
		List<Node> s;
		
		s = this.getSuccessors();
		
		this.getSuccessors().clear();
		this.setSuccessors(null);
		
		return s;
	}
	
	/**
	 * @see "Método removeValue del protocolo removing en SUKIA SmallTalk"
	 * @return
	 */
	public T removeValue() {
		T value;
		
		value = this.getValue();
		this.value = null;

		return value;
	}
}
