/**
 * @see "Categoría Sukia Domain Theory de SUKIA Smalltalk"
 */
package ontology.taxonomy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ontology.common.CharacterDescriptor;
import ontology.common.Descriptor;
import ontology.common.HeuristicDescriptor;
import ontology.common.Modifier;
import ontology.values.RangeValue;


/**
 * @author Armando
 *
 */
public class Taxon implements Comparable<Taxon>{
	private TaxonomicRank level;
	private String name;
	private Taxon predecessor;
	private List<Taxon> successors;
	private Map<Descriptor<Object>, Modifier> description;
	//private List<Modifier> modifiers;

	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public Taxon() {
		setLevel(null);
		setName(null);
		//setPredecessor(null);
		//Pendiente ordenamiento
		setSuccessors(new ArrayList<Taxon>());
		//Pendiente ordenamiento
		setModifiedDescription(new HashMap<Descriptor<Object>, Modifier>());
	}

	/**
	 * @see "Método level: del protocolo adding en SUKIA SmallTalk"
	 * @param level
	 */
	public void setLevel(TaxonomicRank level) {
		this.level = level;
	}

	/***
	 * The name of any taxonomic level must be a sequnce of ByteSymbol characters, and it must
	 * be included in the class TaxonomicLevels (i.e., TaxonomicLevels includes: aLevel must be TRUE
	 * before the argument can be assigned to the instance variable 'level'.
	 * NOTE: This program assumes that the argument has been previously verified by the View-related objects.
	 * @see "Método level del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public TaxonomicRank getLevel() {
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
		this.predecessor = predecessor;
		this.predecessor.addSuccessor(this);
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
	public void setSuccessors(List<Taxon> sucessors) {
		this.successors = sucessors;
		Collections.sort(this.successors);
	}

	/**
	 * @see "Método successors del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Taxon> getSuccessors() {
		return successors;
	}
	
	/**
	 * @see "Método sucessor: del protocolo adding-private en SUKIA SmallTalk"
	 * @param sucessor
	 */
	public void addSuccessor(Taxon successor) {
                //TODO
                //check circular dependency
		//successor.setPredecessor(this);
		this.successors.add(successor);
		Collections.sort(this.successors);
	}

	/***
	 * Método de instancia agregado
	 * @param sAVDescription
	 */
	public void setModifiedDescription(Map<Descriptor<Object>, Modifier> aDescription) {
		this.description = aDescription;
	}

	/**
	 * @see "Método SAVdescription del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Map<Descriptor<Object>, Modifier> getModifiedDescription() {
		return description;
	}	
	
	/**
	 * @see "Método SAVdescription del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Descriptor<Object>> getDescription() {
		List<Descriptor<Object>> dl;
		
		dl = new ArrayList<Descriptor<Object>>();
		for (Descriptor<Object> d:description.keySet())
			dl.add(d);
		
		return dl;
	}
        
       public Map<Descriptor<Object>, Modifier> searchByStructure(String structureName){
            Map<Descriptor<Object>, Modifier> result = new HashMap<Descriptor<Object>, Modifier>();
            
            for (Descriptor<Object> d:description.keySet()) {
            	if (d.getStructure().equals(structureName)){
                    result.put(d, description.get(d));
                }
            }

            return result;
       }

       public Map<Descriptor<Object>, Modifier> searchByAttribute(String attributeName){
            Map<Descriptor<Object>, Modifier> result = new HashMap<Descriptor<Object>, Modifier>();
            
            for (Descriptor<Object> d:description.keySet()) {
            	if (d.getAttribute().equals(attributeName)){
                    result.put(d, description.get(d));
                }
            }
            
            return result;
       }
       public Map<Descriptor<Object>, Modifier> searchByValue(Object value){
            Map<Descriptor<Object>, Modifier> result = new HashMap<Descriptor<Object>, Modifier>();
            
            for (Descriptor<Object> d:description.keySet()) {
            	if (d.getValue().equals(value)){
                    result.put(d, description.get(d));
                }
            }
            
            return result;
       }

       public Map<Object, Double> retriveValuesUsing(String structureName,String attributeName){
            Map<Object, Double> result = new HashMap<Object, Double>();
            
            for (Descriptor<Object> d:description.keySet()) {
            	if (d.getStructure().equals(structureName) && d.getAttribute().equals(attributeName)){
                    result.put(d.getValue(), description.get(d).getValueWeight());
                }
            }
            
            return result;
       }
	/**
	 * @see "Método isSuccessorOf: del protocolo inhetitence en SUKIA SmallTalk"
	 */
	public boolean isSuccessorOf(Taxon aTaxon) {
		Taxon  predecessorTaxon;
		
		if (TaxonomicRank.getIndex(level) <= TaxonomicRank.getIndex(aTaxon.getLevel()))
			return false;
		
		predecessorTaxon = predecessor;
		while (!(predecessorTaxon.getLevel().equals(TaxonomicRank.ROOT))) {
			if (predecessorTaxon.getName().equals(aTaxon.getName()))
				return true;

			predecessorTaxon = predecessorTaxon.getPredecessor();
		}
		
		return false;
	}
	
	/**
	 * @see "Método linkTo: del protocolo linking en SUKIA SmallTalk"
	 * @param aTaxon
	 */
	/*public void linkTo(Taxon aTaxon) {
		predecessor = aTaxon;
		aTaxon.addSucessor(this);
	}*/
	
	/**
	 * @see "Método unlinkFromTheHierarchy del protocolo linking en SUKIA SmallTalk"
	 */
	public void unlinkFromTheHierarchy() {
		Taxon p;
		
		p = predecessor;
		for (int i = 1; i <= p.getSuccessors().size(); i++) {
			if (p.getSuccessors().get(i-1) == this) {
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
	public boolean addToDescription(Descriptor<Object> aDescriptor) {
		if (aDescriptor == null) return false;
		
		if (this.getModifiedDescription().containsKey(aDescriptor))
			return false;
		
		aDescriptor.setAssociatedObject(this);
		this.getModifiedDescription().put(aDescriptor, new Modifier());
		
		return true;
	}
	
	public boolean addToDescription(Descriptor<Object> aDescriptor, Modifier aModifier) {
		if (aDescriptor == null) return false;
		
		if (this.getModifiedDescription().containsKey(aDescriptor))
			return false;
		
		aDescriptor.setAssociatedObject(this);
		this.getModifiedDescription().put(aDescriptor, aModifier);
		
		return true;
	}
	
	/**
	 * Removes aDescriptor from the variable description
	 * @param aDescriptor
	 * @return Valor de verdad true indicando que la remoci&oacute;n se llev&oacute; a cabo
	 * @return Valor de verdad false indicando que la remoci&oacute;n no se llev&oacute; a cabo
	 */
	public boolean removeFromDescription(Descriptor<Object> aDescriptor) {
		if (aDescriptor == null) return false;
		
		if (this.getModifiedDescription().remove(aDescriptor) == null)
			return true;
			
		return false;
	}
	
	/**
	 * @see "Método isLinkOKBetween:and: del protocolo de clase testing en SUKIA SmallTalk"
	 * @param aParentTaxon
	 * @param aSuccessorTaxon
	 * @return
	 */
	public boolean isOKDirectLink(Taxon aParentTaxon) {
		return (((TaxonomicRank.getIndex(this.getLevel())) - (TaxonomicRank.getIndex(aParentTaxon.getLevel()))) == 1);
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
		
		if (aParentTaxon.getLevel().equals(TaxonomicRank.ROOT))
			return true;
		
		// Parse the receiver's SAV (structure) description
		for (Descriptor<Object> d:this.getDescription()) {
			if (d.getValue() instanceof RangeValue) {
				/*The attribute's value descriptor is a range value.  Get the receiver's predecessor and loop while the 
				 predecessor's level is not ROOT*/
				pt = aParentTaxon;
				while(!(pt.getLevel().equals(TaxonomicRank.ROOT))) {
					for (Descriptor<Object> d2:pt.getDescription()) {
						if (d2.getValue() instanceof RangeValue) {
							/*Value descriptor found. If the measuring units for the receiver's retrieved range value and the 
							 predecessor's range value are different, then there is an inconsistency*/
							if (!((RangeValue) d2.getValue()).getMeasuringUnit()
									.equals(((RangeValue) d.getValue()).getMeasuringUnit()))
								return false;
							else {
								/* Measuring units are the same for both ranges.  Determine if the receiver's value descriptor
								 range lies within the predecessor's value descriptor range */
								if (((RangeValue) d2.getValue()).isRangeWithin(((RangeValue) d.getValue())
										.getLowerBound(), ((RangeValue) d.getValue()).getUpperBound())) 
									return true;
								else return false; 
							}
						}
					}
				}
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
	public List<Descriptor<Object>> getDescription(String aStructureName) {
		List<Descriptor<Object>> description;
		
		description = new ArrayList<Descriptor<Object>>();
		
		for(Descriptor<Object> d: this.getDescription()) {
			// Determine if the structure name in Deescriptor has already been included in structureList
			if (d.getStructure().equals(aStructureName)) {
				description.add(d);
			} else continue;
		}
		
		return description;
	}
	
	/**
	 * M&eacute;todo de instancia agregado
	 * @return una lista de cadenas representando el nombre de las estructuras
	 */
	public List<String> getCharacterStructuresList() {
		List<String> structuresList;
		
		structuresList = new ArrayList<String>();
		
		for(Descriptor<Object> d: this.getDescription()) {
			if (d instanceof CharacterDescriptor) { 
				// Determine if the structure name in Deescriptor has already been included in structureList
				if (!(structuresList.contains(d.getStructure()))) {
					// The structure name was not found in structureList. Append it to structureList
					structuresList.add(d.getStructure());
				} else continue;
			}
		}
		
		return structuresList;
	}
	
	/**
	 * M&eacute;todo de instancia agregado
	 * @return una lista de cadenas representando el nombre de las estructuras
	 */
	public List<String> getHeuristicStructuresList() {
		List<String> structuresList;
		
		structuresList = new ArrayList<String>();
		
		for(Descriptor<Object> d: this.getDescription()) {
			if (d instanceof HeuristicDescriptor) { 
				// Determine if the structure name in Deescriptor has already been included in structureList
				if (!(structuresList.contains(d.getStructure()))) {
					// The structure name was not found in structureList. Append it to structureList
					structuresList.add(d.getStructure());
				} else continue;
			}
		}
		
		return structuresList;
	}
	
	/**
	 * M&eacute;todo de instancia agregado
	 * @return una lista de cadenas representando el nombre de las estructuras
	 */
	public List<String> getAttributeList(String structureName) {
		List<String> attributesList;
		
		attributesList = new ArrayList<String>();
		
		for(Descriptor<Object> d: this.getDescription()) {
			if (d.getStructure().equals(structureName)) {
				// Determine if the structure name in Deescriptor has already been included in structureList
				if (!(attributesList.contains(d.getAttribute()))) {
					// The structure name was not found in structureList. Append it to structureList
					attributesList.add(d.getStructure());
				} else continue;
			}
		}
		
		return attributesList;
	}
	
	/**
	 * M&eacute;todo de instancia agregado
	 * @return una lista de descriptores relacionados a aStructureName y aAttributeName
	 */
	public List<Descriptor<Object>> getDescription(String aStructureName, String anAttributeName) {
		List<Descriptor<Object>> description;
		
		description = new ArrayList<Descriptor<Object>>();
		
		for(Descriptor<Object> d: this.getDescription()) {
			// Determine if the structure name in Deescriptor has already been included in structureList
			if (d.getStructure().equals(aStructureName) && d.getAttribute().equals(anAttributeName)) {
				description.add(d);
			} else continue;
		}
		
		return description;
	}
}
