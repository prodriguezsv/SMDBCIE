package speciesidontology;


/**
* Protege name: Caso
* @author ontology bean generator
* @version 2009/05/22, 13:27:49
*/
public class Caso implements jade.content.Concept {

  private static final long serialVersionUID = 5687089858125395496L;

   /**
   * Protege name: especieInferida
   */
   private Taxon especieInferida;
   public void setEspecieInferida(Taxon value) {
    this.especieInferida=value;
   }
   public Taxon getEspecieInferida() {
     return this.especieInferida;
   }

   /**
   * Protege name: descripcion
   */
   private Descripcion descripcion;
   public void setDescripcion(Descripcion value) {
    this.descripcion=value;
   }
   public Descripcion getDescripcion() {
     return this.descripcion;
   }
}
