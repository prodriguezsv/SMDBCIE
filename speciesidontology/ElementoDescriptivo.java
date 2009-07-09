package speciesidontology;



/**
* Protege name: ElementoDescriptivo
* @author ontology bean generator
* @version 2009/05/22, 13:27:49
*/
public abstract class ElementoDescriptivo implements jade.content.Concept {

  private static final long serialVersionUID = 5687089858125395496L;

  protected String _internalInstanceName = null;

  public ElementoDescriptivo() {
    this._internalInstanceName = "";
  }

    @Override
  public String toString() {
    return _internalInstanceName;
  }

   /**
   * Protege name: propiedad
   */
   protected String propiedad;
   public void setPropiedad(String value) {
    this.propiedad=value;
   }
   public String getPropiedad() {
     return this.propiedad;
   }

   /**
   * Protege name: estructura
   */
   protected String estructura;
   public void setEstructura(String value) {
    this.estructura=value;
   }
   public String getEstructura() {
     return this.estructura;
   }
}
