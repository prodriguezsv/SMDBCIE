package ontology.common;

import java.io.Serializable;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


/**
* Protege name: ApliesTo
* @author ontology bean generator
* @version 2009/09/29, 11:00:34
*/
public class ApliesTo implements jade.content.Predicate, Serializable {
	// bean stuff
   protected PropertyChangeSupport pcs = new PropertyChangeSupport(this);

   public void addPropertyChangeListener(PropertyChangeListener pcl) {
     pcs.addPropertyChangeListener(pcl);
   }

   public void removePropertyChangeListener(PropertyChangeListener pcl) {
     pcs.removePropertyChangeListener(pcl);
   }

   private static final long serialVersionUID = -3087841394215437493L;

   private String _internalInstanceName = null;

   public ApliesTo() {
     this._internalInstanceName = "";
   }

   public ApliesTo(String instance_name) {
     this._internalInstanceName = instance_name;
   }

   public String toString() {
     return _internalInstanceName;
   }

    /**
    * Protege name: descriptiveElement
    */
    private Object descriptiveElement;
    public void setDescriptiveElement(Object value) { 
      pcs.firePropertyChange("descriptiveElement", (this.descriptiveElement==null?new Object():this.descriptiveElement), value);
     this.descriptiveElement=value;
    }
    public Object getDescriptiveElement() {
      return this.descriptiveElement;
    }

    /**
    * Protege name: attribute
    */
    private Attribute attribute;
    public void setAttribute(Attribute value) { 
      pcs.firePropertyChange("attribute", (this.attribute==null?new Attribute():this.attribute), value);
     this.attribute=value;
    }
    public Attribute getAttribute() {
      return this.attribute;
    }
}
