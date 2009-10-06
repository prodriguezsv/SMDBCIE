/**
 * 
 */
package ontology.common;

import jade.util.leap.ArrayList;
import jade.util.leap.Iterator;
import jade.util.leap.List;
import jade.util.leap.Set;
import jade.util.leap.SortedSetImpl;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

/**
 * @author Armando
 *
 */
public class Description implements jade.content.Concept, Serializable {
   // bean stuff
   protected PropertyChangeSupport pcs = new PropertyChangeSupport(this);

   public void addPropertyChangeListener(PropertyChangeListener pcl) {
     pcs.addPropertyChangeListener(pcl);
   }

   public void removePropertyChangeListener(PropertyChangeListener pcl) {
     pcs.removePropertyChangeListener(pcl);
   }


  private static final long serialVersionUID = -3087841394215437493L;

  private String _internalInstanceName = null;

  public Description() {
    this._internalInstanceName = "";
  }

  public Description(String instance_name) {
    this._internalInstanceName = instance_name;
  }

  public String toString() {
    return _internalInstanceName;
  }

   /**
   * Protege name: descriptors
   */
   private List descriptors = new ArrayList();
   public void addDescriptors(Descriptor elem) { 
     descriptors.add(elem);
     //pcs.firePropertyChange("descriptors", oldList, this.descriptors);
   }
   public boolean removeDescriptors(Descriptor elem) {
     boolean result = descriptors.remove(elem);
     //pcs.firePropertyChange("descriptors", oldList, this.descriptors);
     return result;
   }
   public void clearAllDescriptors() {
     descriptors.clear();
     //pcs.firePropertyChange("descriptors", oldList, this.descriptors);
   }
   public Iterator getAllDescriptors() {return descriptors.iterator(); }
   public List getDescriptors() {return descriptors; }
   public void setDescriptors(List l) {descriptors = l; }
	/**
	 * M&eacute;todo de instancia agregado
	 * @return una lista de cadenas representando el nombre de las estructuras
	 */
	public java.util.List<String> getCharacterStructuresList() {
		java.util.List<String> structuresList;
		
		structuresList = new java.util.ArrayList<String>();
		Iterator i = this.getAllDescriptors();
		
		while (i.hasNext()) {
			Descriptor d = (Descriptor) i.next(); 
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
	public final java.util.List<String> getHeuristicStructuresList() {
		java.util.List<String> structuresList;
		
		structuresList = new java.util.ArrayList<String>();
		
		Iterator i = this.getAllDescriptors();
		
		while (i.hasNext()) {
			Descriptor d = (Descriptor) i.next(); 
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
	 * @return una lista de cadenas representando el nombre de las heur&iacute;sticas
	 */
	public final java.util.List<String> getStructuresList() {
		java.util.List<String> structuresList;
		
		structuresList = new java.util.ArrayList<String>();
		
		Iterator i = this.getAllDescriptors();
		
		while (i.hasNext()) {
			Descriptor d = (Descriptor) i.next(); 			 
			// Determine if the structure name in Deescriptor has already been included in structureList
			if (!(structuresList.contains(d.getStructure()))) {
				// The structure name was not found in structureList. Append it to structureList
				structuresList.add(d.getStructure());
			} else continue;
		}
		
		return structuresList;
	}
	
	/**
	 * M&eacute;todo de instancia agregado
	 * @return una lista de descriptores relacionados a aStructureName
	 */
	public final Description getDescription(String aStructureName) {
		Description description;
		
		description = new Description();
		
		Iterator i = this.getAllDescriptors();
		
		while (i.hasNext()) {
			Descriptor d = (Descriptor) i.next(); 
			// Determine if the structure name in Deescriptor has already been included in structureList
			if (d.getStructure().equals(aStructureName)) {
				description.addDescriptors(d);
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
		Iterator i = this.getAllDescriptors();
		
		while (i.hasNext()) {
			Descriptor d = (Descriptor) i.next(); 
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
		
		if (this.getDescriptors().contains(aDescriptor) ||
				this.areThereContradictions(aDescriptor))
			return false;
		
		this.addDescriptors(aDescriptor);
		this.sortDescription();
		
		return true;
	}
	
	/**
	 * @see "Método copyToJustificationFrom: del protocolo adding en SUKIA SmallTalk"
	 * @param aDescription
	 * @return
	 */
	public boolean addAllToConcreteDescription(Description aDescription) {
		if (aDescription == null) return false;
		
		Iterator i = aDescription.getAllDescriptors();
		
		while (i.hasNext()) {
			Descriptor d = (Descriptor) i.next(); 
            this.addToConcreteDescription(d);
            this.sortDescription();
        }
        
		return true;
	}
	
	/**
	 * @see "Método copyToJustificationFrom: del protocolo adding en SUKIA SmallTalk"
	 * @param aDescription
	 * @return
	 */
	public boolean addAllToAbstractDescription(Description aDescription) {
		if (aDescription == null) return false;
		
		Iterator i = aDescription.getAllDescriptors();
		
		while (i.hasNext()) {
			Descriptor d = (Descriptor) i.next(); 
            this.addToAbstractDescription(d);
            this.sortDescription();
        }
        
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
		
		if (this.getDescriptors().contains(aDescriptor))
			return false;
		
		this.addDescriptors(aDescriptor);
		this.sortDescription();
		
		return true;
	}
	
	/**
	 * M&eacute;todo de instancia agregado
	 * @return una lista de descriptores relacionados a aStructureName y aAttributeName
	 */
	public final Description getDescription(String aStructureName, String anAttributeName) {
		Description description;
		
		description = new Description();
		
		Iterator i = this.getAllDescriptors();
		
		while (i.hasNext()) {
			Descriptor d = (Descriptor) i.next(); 
			// Determine if the structure name in Deescriptor has already been included in structureList
			if (d.getStructure().equals(aStructureName) && d.getAttribute().equals(anAttributeName)) {
				description.addDescriptors(d);
			} else continue;
		}
		
		return description;
	}
	
	/**
	 * M&eacute;todo de instancia agregado
	 * @return una lista de cadenas representando el nombre de las estructuras
	 */
	public final java.util.List<String> getAttributeList(String structureName) {
		java.util.List<String> attributesList;
		
		attributesList = new java.util.ArrayList<String>();
		
		Iterator i = this.getAllDescriptors();
		
		while (i.hasNext()) {
			Descriptor d = (Descriptor) i.next(); 
			if (d.getStructure().equals(structureName)) {
				// Determine if the structure name in Deescriptor has already been included in structureList
				if (!(attributesList.contains(d.getAttribute()))) {
					// The structure name was not found in structureList. Append it to structureList
					attributesList.add(d.getAttribute());
				} else continue;
			}
		}
		
		return attributesList;
	}
	
	/**
	 * M&eacute;todo de instancia agregado
	 * @return una lista de cadenas representando el nombre de las estructuras
	 */
	public final Description getCharacterList() {
		Description characterList;
		
		characterList = new Description();
		
		Iterator i = this.getAllDescriptors();
		
		while (i.hasNext()) {
			Descriptor d = (Descriptor) i.next(); 
			if (d instanceof CharacterDescriptor) { 
				// Determine if the structure name in Deescriptor has already been included in structureList
				if (!(characterList.getDescriptors().contains(d.getStructure()))) {
					// The structure name was not found in structureList. Append it to structureList
					characterList.addDescriptors(d);
				} else continue;
			}
		}
		
		return characterList;
	}
	
	/**
	 * M&eacute;todo de instancia agregado
	 * @return una lista de cadenas representando el nombre de las estructuras
	 */
	public final Description getHeuristicList() {
		Description heuristicList;
		
		heuristicList = new Description();
		
		Iterator i = this.getAllDescriptors();
		
		while (i.hasNext()) {
			Descriptor d = (Descriptor) i.next(); 
			if (d instanceof HeuristicDescriptor) { 
				// Determine if the structure name in Deescriptor has already been included in structureList
				if (!(heuristicList.getDescriptors().contains(d.getStructure()))) {
					// The structure name was not found in structureList. Append it to structureList
					heuristicList.addDescriptors(d);
				} else continue;
			}
		}
		
		return heuristicList;
	}
	
	private void sortDescription() {
		Set descriptionSet = new SortedSetImpl();
		
		Iterator i = this.getAllDescriptors();
		
		while (i.hasNext()) {
			descriptionSet.add(i.next());
		}
		
		this.clearAllDescriptors();
		
		Iterator j = descriptionSet.iterator();
		
		while (j.hasNext()) {
			this.getDescriptors().add(j.next());
		}
	}
}
