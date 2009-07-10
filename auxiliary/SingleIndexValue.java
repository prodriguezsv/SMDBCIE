/**
 * @see "Categoría Auxiliary de SUKIA Smalltalk"
 */
package auxiliary;

import main.Node;

/**
 * Purpose:  An IndexValue is a list that, for most applications, contains two elements: an attribute value (e.g., #red, #square, 15), and a reference
 * to an object.  The object pointed to may be either a case or a norm.  Although this structure is meant to handle two elements, it may accept more
 * elements (by way of the appendSuccessor: method), if and only if these other elements are cases.  When an IndexValue is created and the values
 * are added, its final destination is to be a component of an Index.
 * @author Armando
 *
 */
public class SingleIndexValue <T extends Node> extends IndexValue<Object> {
	private T successor;

	/**
	 * By default, an IndexValue consists of two elements: the first one is ALWAYS
	 * the index value, and the second one is the successor of  the index. However,
	 * in some situations, an IndexValue may be more than one successor. So, the rule
	 * applies as follows:
	 * 1. If the sucessor is a NORM, then IndexValue may have excatly ONE successor.
	 * 2. If the successor is a CASE, then IndexValue may have one or more successors.
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	@SuppressWarnings("unchecked")
	public SingleIndexValue() {
		successor = (T) new Object();
	}
	
	/**
	 * Precondition: self = (nil, nil), or self = (v, s):
	 * This method is to be used when an IndexValue instance has only two values,
	 * i.e., ( nil, nil ) or ( value, successor ), where successor is either a Norm or a Case.
	 * @see "Método addValue:withSuccessor: del protocolo adding en SUKIA SmallTalk"
	 * @param aValue
	 * @param aSuccessor
	 */
	public SingleIndexValue(Object aValue, T aSuccessor) {
		// Add the value first.
		super.setValue(aValue);
		
		this.setSuccessor(aSuccessor);
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
	public boolean add(Object aValue, T aSuccessor) {
		// Add the value first.
		super.setValue(aValue);

		this.setSuccessor(aSuccessor);
		
		// Postcondition: self = (nil, nil), or self = (aValue, aSucessor)
		return true;
	}

	/**
	 * @see "Método successors del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public T getSuccessor() {
		return successor;
	}

	/**
	 * Método de instancia agregado
	 * @param successors
	 */
	public void setSuccessor(T successor) {
		this.successor = successor;
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
		return (!(super.getValue() == null || this.successor  == null));
	}
}
