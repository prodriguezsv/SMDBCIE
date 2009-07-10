/**
 * @see "Categoría Sukia Scopes de SUKIA Smalltalk"
 */
package scopes;

/**
 * This class contains all (structure) attributes defined in the system. Each attribute MUST contain a "one-Level" set of all the
 * symbol-based, values that define it.  Attributes defined by numeric values will contain an EMPTY "one-Level" values list.
 * Symbol-based values shouldn't have weights.  Weights are useful to determine value similarity in the context of a taxon's
 * description, not in a general list of values for an unassociated attribute.
 * Examples:
 * #color: (#green #greenish #turquoise #acqua #blue #navyblue #skyblue #lightblue #violet #purple #black ...)
 * #shape: (#circular #rounded #ovaled #triangular #square #rectangular ...)
 * #disposition: (#opposed #adyacent ...)
 * #length: ()
 * #numberFilaments: ()
 * @author Armando
 *
 */
public class AtributeScopes extends Scopes {

	/**
	 * 
	 */
	public AtributeScopes() {
		// TODO Auto-generated constructor stub
	}

}
