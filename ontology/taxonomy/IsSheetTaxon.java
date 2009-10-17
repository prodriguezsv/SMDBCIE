package ontology.taxonomy;



/**
* Protege name: IsSheetTaxon
* @author ontology bean generator
* @version 2009/09/30, 13:34:31
*/
@SuppressWarnings("serial")
public class IsSheetTaxon implements jade.content.Predicate {

  private String _internalInstanceName = null;

  public IsSheetTaxon() {
    this._internalInstanceName = "";
  }

  public IsSheetTaxon(String instance_name) {
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
