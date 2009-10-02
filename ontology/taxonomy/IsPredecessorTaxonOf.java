package ontology.taxonomy;

import java.io.Serializable;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;


/**
* Protege name: IsPredecessorTaxonOf
* @author ontology bean generator
* @version 2009/09/30, 13:34:31
*/
public class IsPredecessorTaxonOf implements jade.content.Predicate, Serializable {
	// bean stuff
   protected PropertyChangeSupport pcs = new PropertyChangeSupport(this);

   public void addPropertyChangeListener(PropertyChangeListener pcl) {
     pcs.addPropertyChangeListener(pcl);
   }

   public void removePropertyChangeListener(PropertyChangeListener pcl) {
     pcs.removePropertyChangeListener(pcl);
   }


  private static final long serialVersionUID = -8627856865395943317L;

  private String _internalInstanceName = null;

  public IsPredecessorTaxonOf() {
    this._internalInstanceName = "";
  }

  public IsPredecessorTaxonOf(String instance_name) {
    this._internalInstanceName = instance_name;
  }

  public String toString() {
    return _internalInstanceName;
  }

   /**
   * Protege name: subjectTaxon
   */
   private Taxon subjectTaxon;
   public void setSubjectTaxon(Taxon value) { 
     pcs.firePropertyChange("subjectTaxon", (this.subjectTaxon==null?new Taxon():this.subjectTaxon), value);
    this.subjectTaxon=value;
   }
   public Taxon getSubjectTaxon() {
     return this.subjectTaxon;
   }

   /**
   * Protege name: objectTaxon
   */
   private Taxon objectTaxon;
   public void setObjectTaxon(Taxon value) { 
     pcs.firePropertyChange("objectTaxon", (this.objectTaxon==null?new Taxon():this.objectTaxon), value);
    this.objectTaxon=value;
   }
   public Taxon getObjectTaxon() {
     return this.objectTaxon;
   }
}
