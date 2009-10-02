package ontology.common;

import java.io.Serializable;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import jade.util.leap.*;

/**
* Protege name: DescribedBy
* @author ontology bean generator
* @version 2009/09/29, 11:00:34
*/
public class DescribedBy implements jade.content.Predicate, Serializable {
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

  public DescribedBy() {
    this._internalInstanceName = "";
  }

  public DescribedBy(String instance_name) {
    this._internalInstanceName = instance_name;
  }

  public String toString() {
    return _internalInstanceName;
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

   /**
   * Protege name: score
   */
   private List score = new ArrayList();
   public void addScore(Object elem) { 
     score.add(elem);
     //pcs.firePropertyChange("score", oldList, this.score);
   }
   public boolean removeScore(Object elem) {
     boolean result = score.remove(elem);
     //pcs.firePropertyChange("score", oldList, this.score);
     return result;
   }
   public void clearAllScore() {
     score.clear();
     //pcs.firePropertyChange("score", oldList, this.score);
   }
   public Iterator getAllScore() {return score.iterator(); }
   public List getScore() {return score; }
   public void setScore(List l) {score = l; }
}
