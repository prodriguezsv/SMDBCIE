package ontology.taxonomy;

import java.io.Serializable;


/**
* Protege name: IsRootTaxon
* @author ontology bean generator
* @version 2009/09/30, 13:34:31
*/
@SuppressWarnings("serial")
public class IsRootTaxon implements jade.content.Predicate, Serializable {

  private String _internalInstanceName = null;

  public IsRootTaxon() {
    this._internalInstanceName = "";
  }

  public IsRootTaxon(String instance_name) {
    this._internalInstanceName = instance_name;
  }

  public String toString() {
    return _internalInstanceName;
  }

   /**
   * Protege name: taxon
   */
   private Taxon taxon;
   public void setTaxon(Taxon value) { 
    this.taxon=value;
   }
   public Taxon getTaxon() {
     return this.taxon;
   }
}
