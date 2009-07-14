/**
 * @see "Categoría Sukia Scopes de SUKIA Smalltalk"
 */
package scopes;

import java.util.List;

import domainTheory.Attribute;
import domainTheory.Structure;

/**
 * This class contains all structures defined in the system. Each structure MUST contain all the attributes that define it.
 * Each attribute is determined by its name; values are not important here because they don't desscribe any particular taxon.
 * Examples:
 * #leaf:(#color #shape #disposition)
 * #raquis: (#shape #length)
 * #flowering: (#color #disposition)
 * @author Armando
 *
 */
public class StructureScopes extends Scopes {

	public static List<Attribute> attributesFor(String aStructureName) {
		if (getScopeItem(aStructureName) == null)
				return null;

		return ((Structure)getScopeItem(aStructureName)).getAttributes();
	}

	

}
