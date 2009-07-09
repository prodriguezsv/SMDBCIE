package speciesidontology;



/**
* Protege name: ElementoDescriptivoCuantitativo
* @author ontology bean generator
* @version 2009/05/22, 13:27:49
*/
public class ElementoDescriptivoCuantitativo extends ElementoDescriptivo {

  private static final long serialVersionUID = 5687089858125395496L;


  public ElementoDescriptivoCuantitativo(String s, String p, Float v) {
    _internalInstanceName = "("+s+", "+p+", "+v.toString()+")";
    estructura = s;
    propiedad = p;
    valor = v;
  }

    @Override
  public String toString() {
    return _internalInstanceName;
  }

   /**
   * Protege name: valor
   */
   private float valor;
   public void setValor(float value) {
    this.valor=value;
   }
   public float getValor() {
     return this.valor;
   }
}
