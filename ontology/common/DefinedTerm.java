package ontology.common;

import java.io.Serializable;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


/**
* Define the terms utilized in the taxonomic  hierarchy
* Protege name: DefinedTerm
* @author ontology bean generator
* @version 2009/09/29, 11:00:34
*/
public abstract class DefinedTerm implements jade.content.Concept, Serializable {
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

	  public DefinedTerm() {
	    this._internalInstanceName = "";
	  }

	  public DefinedTerm(String instance_name) {
	    this._internalInstanceName = instance_name;
	  }

	  public String toString() {
	    return _internalInstanceName;
	  }

	   /**
	   * Protege name: definition
	   */
	   private String definition;
	   public void setDefinition(String value) { 
	     pcs.firePropertyChange("definition", (this.definition==null?new String():this.definition), value);
	    this.definition=value;
	   }
	   public String getDefinition() {
	     return this.definition;
	   }

	   /**
	   * Protege name: term
	   */
	   private String term;
	   public void setTerm(String value) { 
	     pcs.firePropertyChange("term", (this.term==null?new String():this.term), value);
	    this.term=value;
	   }
	   public String getTerm() {
	     return this.term;
	   }
}
