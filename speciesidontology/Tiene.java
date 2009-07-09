package speciesidontology;



/**
* Protege name: Tiene
* @author ontology bean generator
* @version 2009/05/22, 13:27:49
*/
public class Tiene implements jade.content.Predicate {

   private static final long serialVersionUID = 5687089858125395496L;

   /**
   * Protege name: descripcion1
   */
   private Descripcion descripcion;
   public void setDescripcion(Descripcion value) {
    this.descripcion=value;
   }
   public Descripcion getDescripcion1() {
     return this.descripcion;
   }

   /**
   * Protege name: caso
   */
   private Caso caso;
   public void setCaso(Caso value) {
    this.caso=value;
   }
   public Caso getCaso() {
     return this.caso;
   }
}
