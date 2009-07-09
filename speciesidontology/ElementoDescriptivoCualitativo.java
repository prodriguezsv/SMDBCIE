package speciesidontology;



/**
* Protege name: ElementoDescriptivoCualitativo
* @author ontology bean generator
* @version 2009/05/22, 13:27:49
*/
public class ElementoDescriptivoCualitativo extends ElementoDescriptivo {

  private static final long serialVersionUID = 5687089858125395496L;


  public ElementoDescriptivoCualitativo(String s, String p, String e) {
    _internalInstanceName = "("+s+", "+p+", "+e+")";
    estructura = s;
    propiedad = p;
    estado = e;
  }

    @Override
  public String toString() {
    return _internalInstanceName;
  }

   /**
   * Protege name: estado
   */
   private String estado;
   public void setEstado(String value) {
    this.estado=value;
   }
   public String getEstado() {
     return this.estado;
   }
}
