package ontology.taxonomy;

/**
* Protege name: IsPredecessorTaxonOf
* @author ontology bean generator
* @version 2009/09/30, 13:34:31
*/
@SuppressWarnings("serial")
public class IsPredecessorTaxonOf implements jade.content.Predicate {
	
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
    this.objectTaxon=value;
   }
   public Taxon getObjectTaxon() {
     return this.objectTaxon;
   }
}
