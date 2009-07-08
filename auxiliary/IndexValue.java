/**
 * @see "Categoría Auxiliary de SUKIA Smalltalk"
 */
package auxiliary;

import java.util.ArrayList;
import java.util.List;
import main.Case;
import main.Node;
import main.Norm;

/**
 * Purpose:  An IndexValue is a list that, for most applications, contains two elements: an attribute value (e.g., #red, #square, 15), and a reference
 * to an object.  The object pointed to may be either a case or a norm.  Although this structure is meant to handle two elements, it may accept more
 * elements (by way of the appendSuccessor: method), if and only if these other elements are cases.  When an IndexValue is created and the values
 * are added, its final destination is to be a component of an Index.
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
		successors = new ArrayList<Node>();
		value = null;
	}
	
	/**
	 * Precondition: self = (nil, nil), or self = (v, s):
	 * This method is to be used when an IndexValue instance has only two values,
	 * i.e., ( nil, nil ) or ( value, successor ), where successor is either a Norm or a Case.
	 * @see "Método addValue:withSuccessor: del protocolo adding en SUKIA SmallTalk"
	 * @param aValue
	 * @param aSuccessor
	 */
	public IndexValue(T aValue, Node aSuccessor) {
		// Add the value first.
		this.setValue(aValue);
		
		successors = new ArrayList<Node>();
		// Add the successor (Case or Norm) next
		this.addSuccessor(aSuccessor);
	}
	
	/**
	 * Precondition: self = (nil, nil), or self = (v, s):
	 * This method is to be used when an IndexValue instance has only two values,
	 * i.e., ( nil, nil ) or ( value, successor ), where successor is either a Norm or a Case.
	 * @see "Método addValue:withSuccessor: del protocolo adding en SUKIA SmallTalk"
	 * @param aValue
	 * @param aSuccessor
	 * @return
	 */
	public boolean add(T aValue, Node aSuccessor) {
		if (this.getSuccessors().size() > 1) return false;

		// Add the value first.
		this.setValue(aValue);

		// Add the successor (Case or Norm) next
		if (this.addSuccessor(aSuccessor) == false) return false;

		// Postcondition: self = (nil, nil), or self = (aValue, aSucessor)
		return true;
	}

	/**
	 * @see "Método clear del protocolo initializing en SUKIA SmallTalk"
	 */
	/*public void clear() {
		successors.clear();
		successors = null;
		value = null;
	}*/

	/**
	 * @see "Método successors del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Node> getSuccessors() {
		return successors;
	}

	/**
	 * Método de instancia agregado
	 * @param successors
	 */
	public void setSuccessors(List<Node> successors) {
		this.successors.clear();
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
	 * In some situations, an IndexValue may be more than one successor. The append-rule
	 * applies as follows:
	 * 1. if position nbr. 2 of IndexValue (start of the successors list) is nil, DO NOT append anything;
	 * 2. if position nbr. 2 of IndexValue (start of the successors list) is a Norm, DO NOT append anything;
	 * 3. if aSuccessor is NOT a case, DO NOT append it;
	 * Else, append successor."
	 * @see "Método appendSuccessor: del protocolo adding en SUKIA SmallTalk"
	 * @param aSuccessor
	 */
	public boolean addSuccessor(Node aSuccessor) {
		if (!(this.getSuccessors().isEmpty()) && (this.getSuccessors().get(0) instanceof Norm ||
				!(aSuccessor instanceof Case)))
				return false;
			
			this.getSuccessors().add(aSuccessor);
			return true;
	}
	
	/**
	 * Precondition: self last = (nil or s)
	 * IMPORTANT NOTE: The only valid succesors for an IndexValue are Cases or Norms
	 * @see "Método addSuccessor: del protocolo adding-private en SUKIA SmallTalk"
	 * @param aSuccessor
	 */
	/*public void setOneElementSuccessor(Node aSuccessor) {

		if (this.getSuccessors().size() > 1) return;
		if (!(this.getSuccessors().isEmpty()))
			this.getSuccessors().remove(0);
		this.getSuccessors().add(aSuccessor);
	}*/
	
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
	 * Precondition: self = ( val, succ ) | ( val, succ1, succ2, ..., succN )
	 * @see "Método removeSuccessor: del protocolo removing en SUKIA SmallTalk"
	 * @param aSuccessor
	 */
	public Node removeSuccessor(Node aSuccessor) {
		int i;
		Node s;

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
	
	/**
	 * @see "Método removeSuccessors del protocolo removing en SUKIA SmallTalk"
	 */
	/*public List<Node> removeSuccessors() {
		List<Node> s;
		
		s = this.getSuccessors();
		
		this.getSuccessors().clear();
		this.setSuccessors(null);
		
		return s;
	}*/
	
	/**
	 * @see "Método removeValue del protocolo removing en SUKIA SmallTalk"
	 * @return
	 */
	/*public T removeValue() {
		T value;
		
		value = this.getValue();
		this.value = null;

		return value;
	}*/
}
