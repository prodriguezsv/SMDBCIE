/**
 * @see "Categoría Sukia Domain Theory de SUKIA Smalltalk"
 */
package ontology.taxonomy;

import jade.util.leap.ArrayList;
import jade.util.leap.Iterator;
import jade.util.leap.List;
import jade.util.leap.Set;
import jade.util.leap.SortedSetImpl;

import java.util.HashMap;
import java.util.Map;

import ontology.common.Description;
import ontology.common.Descriptor;
import ontology.common.RangeValue;


/**
 * @author Armando
 *
 */
@SuppressWarnings("serial")
public class Taxon implements jade.content.Concept, Comparable<Taxon>{
	private String level;
	private String name;
	private Taxon predecessor;
	private List successors;
	private WeightedDescription weightedDescription;

	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public Taxon(String level, String name) {
		setLevel(level);
		setName(name);
		predecessor = null;
		setSuccessors(new ArrayList());
		setWeightedDescription(new WeightedDescription());
	}

	private String _internalInstanceName = null;

  	public Taxon() {
	  this._internalInstanceName = "";
  	}

  	public Taxon(String instance_name) {
	  this._internalInstanceName = instance_name;
	  setSuccessors(new ArrayList());
  	}

  	public String toString() {
	  return _internalInstanceName;
  	}

	/**
	 * @see "Método level: del protocolo adding en SUKIA SmallTalk"
	 * @param level
	 */
	public void setLevel(String level) {
		boolean iscontained = false;
		
		for (TaxonomicRank t:TaxonomicRank.values()) {
			if (t.getRank().equals(level)) {
				iscontained = true;
				break;
			}
		}
		
		if (iscontained) this.level = level;
	}

	/***
	 * The name of any taxonomic level must be a sequnce of ByteSymbol characters, and it must
	 * be included in the class TaxonomicLevels (i.e., TaxonomicLevels includes: aLevel must be TRUE
	 * before the argument can be assigned to the instance variable 'level'.
	 * NOTE: This program assumes that the argument has been previously verified by the View-related objects.
	 * @see "Método level del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public String getLevel() {
		return level;
	}

	/***
	 * The name of  any taxon must be a single sequence of ByteSymbol characters. This program assumes
	 * that the argument 'aName' has been previously verified by the View-related objects. To avoid name
	 * confusion or duplications, without exception ALL NAMES MUST BE IN LOWERCASE LETTERS.
	 * Names belonging to the species level MUST have an underscore between the genus name and the
	 * epithet. The two main reasons for having species names in this format are:
	 * 1. Search speed: it's faster to compare against the species' instance variable 'name', than having to
	 * 	compare the epithet part against the species' 'name' and then asking its corresponding predecessor
	 * 	taxon (the genus) for it's name, before any comparison can be made.
	 * 2. In the unlikely event that a species Taxon ever became unlinked from the hierarchy, it would a lot
	 * 	easier to link it again to its parent genus if the name is complete (i..e, genus + epithet) that if the
	 * 	name consisted only of the epithet.
	 * Examples:
	 * 	For a family, the name might be: #fabaceae.
	 * 	For a genus: #lantana.
	 * 	For a species, the name MUST be composed of the corresponding genus name plus the epithet,
	 * 	separated by an undersore: #lantana_camara.
	 * @see "Método name: del protocolo adding en SUKIA SmallTalk"
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @see "Método name del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @see "Método predecessor: del protocolo adding-private en SUKIA SmallTalk"
	 * @param predecessor
	 */
	public void setPredecessor(Taxon predecessor) {
		if (predecessor == null) return; //Ojo revisar comportamiento
		
		if (this.predecessor != predecessor) {
			this.predecessor = predecessor;
			this.predecessor.addSuccessor(this);
		}
	}

	/**
	 * @see "Método predecessor del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Taxon getPredecessor() {
		return predecessor;
	}

	/**
	 * Método de instancia agregado
	 * @param sucessor
	 */
	public void setSuccessors(List sucessors) {
		this.successors = sucessors;
		sortTaxon(this.successors);
	}

	/**
	 * @see "Método successors del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List getSuccessors() {
		return successors;
	}
	
	/**
	 * @see "Método sucessor: del protocolo adding-private en SUKIA SmallTalk"
	 * @param sucessor
	 */
	public boolean addSuccessor(Taxon successor) {
       if (TaxonomicRank.getIndex(TaxonomicRank.valueOf(successor.getLevel().toUpperCase()))
    		   - TaxonomicRank.getIndex(TaxonomicRank.valueOf((this.getLevel().toUpperCase()))) != 1)
    	   return false; //Verificar comportamiento
       
		if (!this.getSuccessors().contains(successor)) {
			this.successors.add(successor);
			sortTaxon(this.successors);
			successor.setPredecessor(this);
			return true;
		}
		
		return false;
	}
	
	public void clearAllSuccessors() {
		successors.clear();
	}
	
	public Iterator getAllSuccessors() {return successors.iterator(); }

	/***
	 * Método de instancia agregado
	 * @param sAVDescription
	 */
	public void setWeightedDescription(WeightedDescription aDescription) {
		this.weightedDescription = aDescription;
	}

	/**
	 * @see "Método SAVdescription del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public WeightedDescription getWeightedDescription() {
		return weightedDescription;
	}	
	
	/**
	 * @see "Método SAVdescription del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Description getDescription() {
		return this.getWeightedDescription().getDescription();
	}

   /**
    * 
    * @param structureName
    * @param attributeName
    * @return
    */
   public Map<Object, Double> retriveWeightedValues(String structureName, String attributeName){
        Map<Object, Double> result = new HashMap<Object, Double>();
        
        Iterator i = weightedDescription.getAllWeightedDescriptors();
		
		while (i.hasNext()) {
			WeightedDescriptor d = (WeightedDescriptor) i.next();
        
        	if (d.getDescriptor().getStructure().equals(structureName) &&
        			d.getDescriptor().getAttribute().equals(attributeName)){
                result.put(d.getDescriptor().getValue(), d.getModifier().getValueWeight());
            }
        }
        
        return result;
   }
   
	/**
	 * @see "Método isSuccessorOf: del protocolo inhetitence en SUKIA SmallTalk"
	 */
	public boolean isSuccessorOf(Taxon aTaxon) {
		Taxon  predecessorTaxon;
		
		if (TaxonomicRank.getIndex(TaxonomicRank.valueOf(getLevel().toUpperCase())) 
				<= TaxonomicRank.getIndex(TaxonomicRank.valueOf(aTaxon.getLevel().toUpperCase())))
			return false;
		
		predecessorTaxon = predecessor;
		while (!(predecessorTaxon.getLevel().equals(TaxonomicRank.ROOT.getRank()))) {
			if (predecessorTaxon.getName().equals(aTaxon.getName()))
				return true;

			predecessorTaxon = predecessorTaxon.getPredecessor();
		}
		
		return false;
	}
	
	/**
	 * @see "Método unlinkFromTheHierarchy del protocolo linking en SUKIA SmallTalk"
	 */
	public void unlinkFromTheHierarchy() {
		Taxon p;
		
		p = predecessor;
		for (int i = 1; i <= p.getSuccessors().size(); i++) {
			if (p.getSuccessors().get(i-1).equals(this)) {
				 p.getSuccessors().remove(i-1);
				 predecessor = null; 
			}
		}
	}
	
	/**
	 * Appends aDescriptor to the variable description
	 * @see "M&eacute;todo addToDescription: del protocolo adding en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @return Valor de verdad true indicando que la adici&oacute;n se llev&oacute; a cabo
	 * @return Valor de verdad false indicando que la adici&oacute;n no se llev&oacute; a cabo
	 */
	public boolean addToDescription(Descriptor aDescriptor) {
		return this.getWeightedDescription().addToAbstractDescription(aDescriptor);
	}
	
	public boolean addToDescription(Descriptor aDescriptor, Modifier aModifier) {
		return this.getWeightedDescription().addToAbstractDescription(aDescriptor, aModifier);		
	}
		
	/**
	 * Removes aDescriptor from the variable description
	 * @param aDescriptor
	 * @return Valor de verdad true indicando que la remoci&oacute;n se llev&oacute; a cabo
	 * @return Valor de verdad false indicando que la remoci&oacute;n no se llev&oacute; a cabo
	 */
	public boolean removeFromDescription(Descriptor aDescriptor) {
		return this.getWeightedDescription().removeFromDescription(aDescriptor);
	}
	
	/**
	 * @see "Método isLinkOKBetween:and: del protocolo de clase testing en SUKIA SmallTalk"
	 * @param aParentTaxon
	 * @param aSuccessorTaxon
	 * @return
	 */
	public boolean isOKDirectLink(Taxon aParentTaxon) {
		return (TaxonomicRank.getIndex(TaxonomicRank.valueOf(getLevel().toUpperCase())) 
				- (TaxonomicRank.getIndex(TaxonomicRank.valueOf(aParentTaxon.getLevel().toUpperCase()))) == 1);
	}
	
	/**
	 * Método de instancia agregado
	 */
    @Override
	public int compareTo(Taxon aTaxon) {
		return this.getName().compareTo(aTaxon.getName());
	}
    
	/**
	 * If the receiver's SAV description contains structures with attributes that have range values, then, for each one of those structures:
	 * seek a structure in the receiver's predecessor's SAV description whose name matches the (receiver's) structure name;
	 * if the structure is found, seek in the attributes of the found structure for an attribute that matches the (receiver's structure) attribute name;
	 * if the attribute is found, seek a ValueDescriptor that is a range value;
	 * if the ValueDescriptor is found, determine if the measuring units are the same, and if the receiver's range value lies within the ValueDescriptor
	 * range just found.
	 * If the ranges are consistent, return true. Else, return false (i.e., inconsistent ranges).
	 * If neither the first nor the second steps are satisfied, get the receiver's predecessor's predecessor, and start again.
	 * This method will stop when the predecessor's level is ROOT
	 * @see "Método SAVRangesConsistentWith: del protocolo testing en SUKIA SmallTalk"
	 * @param aParentTaxon
	 * @return
	 */
	public boolean isRangesConsistent(Taxon aParentTaxon) {
		Taxon pt;
		
		if (aParentTaxon.getLevel().equals(TaxonomicRank.ROOT.getRank()))
			return true;
		
		// Parse the receiver's SAV (structure) description
		Iterator i = this.getDescription().getAllDescriptors();
		
		while (i.hasNext()) {
			Descriptor d = (Descriptor) i.next(); 
			if (d.getValue() instanceof RangeValue) {
				/*The attribute's value descriptor is a range value.  Get the receiver's predecessor and loop while the 
				 predecessor's level is not ROOT*/
				pt = aParentTaxon;
				while(!(pt.getLevel().equals(TaxonomicRank.ROOT.getRank()))) {
					Iterator j = pt.getDescription().getAllDescriptors();
					
					while (j.hasNext()) {
						Descriptor d2 = (Descriptor) j.next();
						
	                     if (!(d2.getValue() instanceof RangeValue)) 
	                         continue;
	
                         if (d.getStructure().equals(d2.getStructure()) && d.getAttribute().equals(d2.getAttribute())){
                            if (!((RangeValue) d2.getValue()).getMeasuringUnit().equals(((RangeValue) d.getValue()).getMeasuringUnit())){
                                    return false;
                            }
                            return (((RangeValue) d2.getValue()).isWithinthisBounds(((RangeValue) d.getValue()).getLowerBound(), ((RangeValue) d.getValue()).getUpperBound()));
                         }
                                            
					}//end for (Descriptor d2:pt.getDescription())
					
                    pt = pt.getPredecessor();
				}//end while
			}
		}
											
		/*The entire SAV decription of the receiver was parsed and nothing was found in the SAV description of all its
		 predecessors. Thus, assume there are no inconsistencies*/
		return true;
	}
	
	/**
	 * M&eacute;todo de instancia agregado
	 * @return una lista de descriptores relacionados a aStructureName
	 */
	public final Description getDescription(String aStructureName) {
		return this.getDescription().getDescription(aStructureName);
	}
	
	/**
	 * M&eacute;todo de instancia agregado
	 * @return una lista de cadenas representando el nombre de las estructuras
	 */
	public final java.util.List<String> getCharacterStructuresList() {
		return this.getDescription().getCharacterStructuresList();
	}
	
	/**
	 * M&eacute;todo de instancia agregado
	 * @return una lista de cadenas representando el nombre de las estructuras
	 */
	public final java.util.List<String> getHeuristicStructuresList() {
		return this.getDescription().getHeuristicStructuresList();
	}
	
	/**
	 * M&eacute;todo de instancia agregado
	 * @return una lista de cadenas representando el nombre de las estructuras
	 */
	public final java.util.List<String> getAttributeList(String structureName) {
		return this.getDescription().getAttributeList(structureName);
	}
	
	/**
	 * M&eacute;todo de instancia agregado
	 * @return una lista de descriptores relacionados a aStructureName y aAttributeName
	 */
	public final Description getDescription(String aStructureName, String anAttributeName) {
		return this.getDescription().getDescription(aStructureName, anAttributeName);
	}
	
	/**
	 * Determina la igualdad entre dos descriptores
	 * @param aTaxon
	 * @return
	 */
	public boolean equals(Object aTaxon) {
		if (aTaxon == null) return false;
		if (!(aTaxon instanceof Taxon)) return false;
		
		if (this.getLevel().equals(((Taxon)aTaxon).getLevel()) &&
				this.getName().equals(((Taxon)aTaxon).getName()))
			return true;
		else return false;
	}
	
	private void sortTaxon(List taxonList) {
		Set taxonSet = new SortedSetImpl();
		
		Iterator i = taxonList.iterator();
		
		while (i.hasNext()) {
			taxonSet.add(i.next());
		}
		
		taxonList.clear();
		
		Iterator j = taxonSet.iterator();
		
		while (j.hasNext()) {
			taxonList.add(j.next());
		}
	}
}
