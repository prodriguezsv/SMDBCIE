/**
 * 
 */
package ontology.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Armando
 *
 */
@SuppressWarnings("serial")
public class Description extends ArrayList<Descriptor> {
	/**
	 * M&eacute;todo de instancia agregado
	 * @return una lista de cadenas representando el nombre de las estructuras
	 */
	public List<String> getCharacterStructuresList() {
		List<String> structuresList;
		
		structuresList = new ArrayList<String>();
		
		for(Descriptor d: this) {
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
	 * @return una lista de cadenas representando el nombre de las heur&iacute;sticas
	 */
	public final List<String> getHeuristicStructuresList() {
		List<String> structuresList;
		
		structuresList = new ArrayList<String>();
		
		for(Descriptor d: this) {
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
	 * @return una lista de descriptores relacionados a aStructureName
	 */
	public final List<Descriptor> getDescription(String aStructureName) {
		List<Descriptor> description;
		
		description = new ArrayList<Descriptor>();
		
		for(Descriptor d: this) {
			// Determine if the structure name in Deescriptor has already been included in structureList
			if (d.getStructure().equals(aStructureName)) {
				description.add(d);
			} else continue;
		}
		
		return description;
	}
	
	/**
	 * Verifica si existen contradicciones entre los descriptores (estructura, atributo, valor) del caso
	 * Se dice que existe contradiccion  si existe dos descripciones distintas para el mismo par
	 * (estructura, atributo)
	 * @see "M&eacute;todo thereAreContradictions: del protocolo testing en SUKIA SmallTalk"
	 * @return
	 */
	public boolean areThereContradictions(Descriptor aDescriptor) {
		
		// Para cada par (atributo, valor) de aCase.
		for(Descriptor d: this) {
			if (d.getStructure().equals(aDescriptor.getStructure()) &&
					d.getAttribute().equals(aDescriptor.getAttribute())	) {
					return true; // Hay contradiccion
			}
		}

		return false;  //No hubo contradiccion
	}
	
	/**
	 * Appends aDescriptor to the variable description
	 * @see "M&eacute;todo addToDescription: del protocolo adding en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @return Valor de verdad true indicando que la adici&oacute;n se llev&oacute; a cabo
	 * @return Valor de verdad false indicando que la adici&oacute;n no se llev&oacute; a cabo
	 */
	public boolean addToConcreteDescription(Descriptor aDescriptor) {
		if (aDescriptor == null) return false;
		
		if (aDescriptor instanceof RVCharacterDescriptor
				|| aDescriptor instanceof RVHeuristicDescriptor)
			return false;
		
		if (this.contains(aDescriptor) ||
				this.areThereContradictions(aDescriptor))
			return false;
		
		this.add(aDescriptor);
		aDescriptor.setAssociatedObject(this);
		
		return true;
	}
	
	/**
	 * Appends aDescriptor to the variable description
	 * @see "M&eacute;todo addToDescription: del protocolo adding en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @return Valor de verdad true indicando que la adici&oacute;n se llev&oacute; a cabo
	 * @return Valor de verdad false indicando que la adici&oacute;n no se llev&oacute; a cabo
	 */
	public boolean addToAbstractDescription(Descriptor aDescriptor) {
		if (aDescriptor == null) return false;
		
		if (this.contains(aDescriptor))
			return false;
		
		this.add(aDescriptor);
		
		return true;
	}
	
	/**
	 * M&eacute;todo de instancia agregado
	 * @return una lista de descriptores relacionados a aStructureName y aAttributeName
	 */
	public final List<Descriptor> getDescription(String aStructureName, String anAttributeName) {
		List<Descriptor> description;
		
		description = new ArrayList<Descriptor>();
		
		for(Descriptor d: this) {
			// Determine if the structure name in Deescriptor has already been included in structureList
			if (d.getStructure().equals(aStructureName) && d.getAttribute().equals(anAttributeName)) {
				description.add(d);
			} else continue;
		}
		
		return description;
	}
	
	/**
	 * M&eacute;todo de instancia agregado
	 * @return una lista de cadenas representando el nombre de las estructuras
	 */
	public final List<String> getAttributeList(String structureName) {
		List<String> attributesList;
		
		attributesList = new ArrayList<String>();
		
		for(Descriptor d: this) {
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
	 * @return una lista de cadenas representando el nombre de las estructuras
	 */
	public final List<Descriptor> getCharacterList() {
		List<Descriptor> characterList;
		
		characterList = new ArrayList<Descriptor>();
		
		for(Descriptor d: this) {
			if (d instanceof CharacterDescriptor) { 
				// Determine if the structure name in Deescriptor has already been included in structureList
				if (!(characterList.contains(d.getStructure()))) {
					// The structure name was not found in structureList. Append it to structureList
					characterList.add(d);
				} else continue;
			}
		}
		
		return characterList;
	}
	
	/**
	 * M&eacute;todo de instancia agregado
	 * @return una lista de cadenas representando el nombre de las estructuras
	 */
	public final List<Descriptor> getHeuristicList() {
		List<Descriptor> heuristicList;
		
		heuristicList = new ArrayList<Descriptor>();
		
		for(Descriptor d: this) {
			if (d instanceof HeuristicDescriptor) { 
				// Determine if the structure name in Deescriptor has already been included in structureList
				if (!(heuristicList.contains(d.getStructure()))) {
					// The structure name was not found in structureList. Append it to structureList
					heuristicList.add(d);
				} else continue;
			}
		}
		
		return heuristicList;
	}
}
