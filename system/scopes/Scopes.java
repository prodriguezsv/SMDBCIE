/**
 * @see "Categoría Sukia Scopes de SUKIA Smalltalk"
 */
package system.scopes;

import java.util.ArrayList;
import java.util.List;

import ontology.common.Attribute;
import ontology.common.GroupingHeuristic;
import ontology.common.Structure;


/**
 * @author Armando
 *
 */
public class Scopes {
	private static List<Object> scopeItems;

	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public Scopes() {
		initialize();	
	}

	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public static void initialize() {
		setScopeItems(new ArrayList<Object>());	
	}
	
	/**
	 * Método de instancia agregado
	 * @param scopeItems
	 */
	public static void setScopeItems(List<Object> someScopeItems) {
		scopeItems = someScopeItems;
	}
	
	/* NOTE: THIS METHOD MUST BE OPTIMIZED TO AVOID THE ADDITION OF REPEATED ITEMS, THAT IS,
	 SCOPE ITEMS WITH NAMES NAMES ALREADY EXISTING*/
	public void addItem(Object aScopeItem) {
		getScopeItems().add(aScopeItem);
	}

	/**
	 * @see "Método scopeItems del protocolo scopeItems en SUKIA SmallTalk"
	 * @return
	 */
	public static List<Object> getScopeItems() {
		return scopeItems;
	}
	
	public static boolean contains(String aScopeItemName) {
		for( int i = 1; i <= scopeItems.size(); i++) {
			if (scopeItems.get(i-1) instanceof Structure) {
				if (((Structure)scopeItems.get(i-1)).getName().equals(aScopeItemName))
					return true;
			} else if (scopeItems.get(i-1) instanceof Attribute) {
				if (((Attribute)scopeItems.get(i-1)).getName().equals(aScopeItemName))
					return true;
			} else if (scopeItems.get(i-1) instanceof GroupingHeuristic) {
				if (((GroupingHeuristic)scopeItems.get(i-1)).getName().equals(aScopeItemName))
					return true;
			}
		}
		
		return false;
	}
	
	public static Object getScopeItem(String aScopeItemName) {
		for( int i = 1; i <= scopeItems.size(); i++) {
			if (scopeItems.get(i-1) instanceof Structure) {
				if (((Structure)scopeItems.get(i-1)).getName().equals(aScopeItemName))
					return scopeItems.get(i-1);
			} else if (scopeItems.get(i-1) instanceof Attribute) {
				if (((Attribute)scopeItems.get(i-1)).getName().equals(aScopeItemName))
					return scopeItems.get(i-1);
			} else if (scopeItems.get(i-1) instanceof GroupingHeuristic) {
				if (((GroupingHeuristic)scopeItems.get(i-1)).getName().equals(aScopeItemName))
					return scopeItems.get(i-1);
			}
		}
		
		return null;
	}
}
