package speciesidontology;



/**
* Protege name: Taxon
* @author ontology bean generator
* @version 2009/05/22, 13:27:49
*/
public class Taxon implements jade.content.Concept {

  private static final long serialVersionUID = 5687089858125395496L;

   /**
   * Protege name: nombre
   */
   private String nombre;
   public void setNombre(String value) {
    this.nombre=value;
   }
   public String getNombre() {
     return this.nombre;
   }

   /**
   * Protege name: categoria
   */
   private String categoria;
   public void setCategoria(String value) {
    this.categoria=value;
   }
   public String getCategoria() {
     return this.categoria;
   }
}
