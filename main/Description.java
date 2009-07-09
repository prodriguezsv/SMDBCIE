/**
 * @see "Categoría Main de SUKIA Smalltalk"
 */
package main;

import java.util.ArrayList;

/**
 * @author Armando
 *
 */
@SuppressWarnings("serial")
public class Description <T extends Descriptor<Object>> extends ArrayList<T>{
	
	/**
	 * 
	 */
	public Description() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Appends aDescriptor to the variable description
	 * @param aDescriptor
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean add(Descriptor<Object> aDescriptor) {
		T descriptor;
		
		if (this.contains(aDescriptor)) return false;

		descriptor = (T) new Descriptor<Object>();
		descriptor.add(aDescriptor.getAttribute(), aDescriptor.getValue());
		super.add(descriptor);
		
		return true;
	}
}
