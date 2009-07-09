package speciesidontology;



/**
* Protege name: Identifica
* @author ontology bean generator
* @version 2009/05/22, 13:27:49
*/
public class Identifica implements jade.content.Predicate {

  private static final long serialVersionUID = 5687089858125395496L;

   /**
   * Protege name: agente
   */
   private jade.core.AID agente;
   public void setAgente(jade.core.AID value) {
    this.agente=value;
   }
   public jade.core.AID getAgente() {
     return this.agente;
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
