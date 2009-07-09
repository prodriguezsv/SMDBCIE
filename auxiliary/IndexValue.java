/**
 * @see "Categoría Auxiliary de SUKIA Smalltalk"
 */
package auxiliary;

/**
 * Purpose:  An IndexValue is a list that, for most applications, contains two elements: an attribute value (e.g., #red, #square, 15), and a reference
 * to an object.  The object pointed to may be either a case or a norm.  Although this structure is meant to handle two elements, it may accept more
 * elements (by way of the appendSuccessor: method), if and only if these other elements are cases.  When an IndexValue is created and the values
 * are added, its final destination is to be a component of an Index.
 * @author Armando
 *
 */
public abstract class IndexValue <T> {
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
		value = null;
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
	 * self = ( nil, nil ) : not valid, OR
	 * self = ( val, nil ) : not valid, OR
	 * self = ( val, succ ) : valid, OR
	 * self = ( val, succ1, ..., succN ) : valid.
	 * @see "Método isValid del protocolo testing en SUKIA SmallTalk"
	 * @return
	 */
	public abstract boolean isValid();
}
