/**
 * @see "Categoría Pattern-Matching de SUKIA Smalltalk"
 */
package onthology.CBR;

import java.util.ArrayList;



/**
 * Solution es una especialización de OrderedCollection y se utiliza para almacenar los casos solución
 * para un problema dado.  Solution verifica que el caso propuesto como solución sea un caso que no contradice
 * el caso problema o un caso ya almacenado como solución.
 * @author Armando
 *
 */
@SuppressWarnings("serial")
public class Solution extends ArrayList<Case> {

	/**
	 * Agrega un caso a la lista de casos solucion.  Antes de agregar el caso verifica si este es una posible solucion
	 * al problema y ademas si el caso no existe ya en la lista de soluciones.  El caso solución no debe contradecir en ninguno
	 * de sus atributos al caso problema.
	 * Se asume que  tanto lo que viene como hipotesis de solucion y el caso problema pertenecen a la clase Case.
	 * @see "Método Add:for: del protocolo adding en SUKIA SmallTalk"
	 * @return
	 */
	public void add(Case aHypothesis, ProblemCase aProblemCase) {
		// Si la hipotesis no existe en la lista de soluciones
		if (!(this.contains(aHypothesis))) {
			if (!(aProblemCase.thereAreContradictions(aHypothesis))) {
				// Agrego el caso aunque sea negativo para saber como no deberia solucionarse el problema.
				this.add(aHypothesis);
			}
		}
	}
}
