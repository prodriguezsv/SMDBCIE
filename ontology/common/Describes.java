package ontology.common;

import jade.util.leap.*;

/**
* Protege name: Describes
* @author ontology bean generator
* @version 2009/09/30, 12:55:45
*/
@SuppressWarnings("serial")
public class Describes implements jade.content.Predicate {

	  private String _internalInstanceName = null;

	  public Describes() {
	    this._internalInstanceName = "";
	  }

	  public Describes(String instance_name) {
	    this._internalInstanceName = instance_name;
	  }

	  public String toString() {
	    return _internalInstanceName;
	  }

	   /**
	   * Protege name: attribute
	   */
	   private String attribute;
	   public void setAttribute(String value) { 
	    this.attribute=value;
	   }
	   public String getAttribute() {
	     return this.attribute;
	   }

	   /**
	   * Protege name: score
	   */
	   private List score = new ArrayList();
	   public void addScore(String elem) { 
	     score.add(elem);
	   }
	   public boolean removeScore(String elem) {
	     boolean result = score.remove(elem);
	     return result;
	   }
	   public void clearAllScore() {
	     score.clear();
	   }
	   public Iterator getAllScore() {return score.iterator(); }
	   public List getScore() {return score; }
	   public void setScore(List l) {score = l; }
	   
}
