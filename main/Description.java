package main;


import jade.util.leap.*;

/**
 * Description maneja una lista conteniendo un conjunto de Descriptor's
 * (Una descripción del problema)
*/
public class Description implements jade.content.Concept {
	private static final long serialVersionUID = 5687089858125395496L;
	private String _internalInstanceName = null;
	private List descriptors = new ArrayList();
	
	public Description() {
		_internalInstanceName = "";
	}

    @Override
    public String toString() {
    	return _internalInstanceName;
    }
    
    
    public void addDescriptors(Descriptor descriptor) {
     descriptors.add(descriptor);
     if (_internalInstanceName.equals(""))
         _internalInstanceName = descriptor.toString();
     else
         _internalInstanceName = _internalInstanceName.concat("; "+descriptor.toString());
   }

   public boolean removeElementosDescriptivos(Descriptor descriptor) {
     boolean result = descriptors.remove(descriptor);
     return result;
   }

   public void clearAllElementosDescriptivos() {
     descriptors.clear();
   }
   
   public Iterator getAllElementosDescriptivos() {return descriptors.iterator(); }
   public List getDescriptors() {return descriptors; }
   public void setDescriptors(List l) {descriptors = l; }

}
