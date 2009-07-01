package speciesidontology;


import jade.util.leap.*;

/**
* Protege name: Descripcion
* @author ontology bean generator
* @version 2009/05/22, 13:27:49
*/
public class Descripcion implements jade.content.Concept {

  private static final long serialVersionUID = 5687089858125395496L;

  private String _internalInstanceName = null;

  public Descripcion() {
    this._internalInstanceName = "";
  }

    @Override
  public String toString() {
    return _internalInstanceName;
  }

   /**
   * Protege name: elementosDescriptivos
   */
   private List elementosDescriptivos = new ArrayList();
   public void addElementosDescriptivos(ElementoDescriptivo elem) {
     elementosDescriptivos.add(elem);
     if (_internalInstanceName.equals(""))
         _internalInstanceName = elem.toString();
     else
         _internalInstanceName = _internalInstanceName.concat("; "+elem.toString());
   }

   public boolean removeElementosDescriptivos(ElementoDescriptivo elem) {
     boolean result = elementosDescriptivos.remove(elem);
     return result;
   }

   public void clearAllElementosDescriptivos() {
     elementosDescriptivos.clear();
   }
   
   public Iterator getAllElementosDescriptivos() {return elementosDescriptivos.iterator(); }
   public List getElementosDescriptivos() {return elementosDescriptivos; }
   public void setElementosDescriptivos(List l) {elementosDescriptivos = l; }

}
