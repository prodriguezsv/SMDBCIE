/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

/**
* RDNetController implementa el uso de dispositivos perifericos (teclado y mouse).
 * @author pabloq
 */
public class RDNetController {
/*
<name>RDNetController</name>
<environment>Smalltalk</environment>
<super>UI.ControllerWithMenu</super>
<private>false</private>
<indexed-type>none</indexed-type>
<inst-vars></inst-vars>
<class-inst-vars></class-inst-vars>
<imports></imports>
<category>CBR - View</category>
*/


 /*
 *Category menu
 */

public void makeMenu(){
/*makeMenu

	| mb |

	mb := MenuBuilder new.
	mb  add: 'Sucesores      s' -&gt; #display.
	^mb.*/
}

public void menu(){
/*menu

	| mb |

	mb := self makeMenu.
	^mb menu.*/
}

public void menu(Object x){
/*menu: aMenu

        menuHolder :=  ValueHolder with: aMenu*/
}


 /*
 *Category control defaults
 */


public void controlActivity(){
/*controlActivity

	"Controlar la actividad del teclado"

	self sensor keyboardPressed ifTrue:[self readKeyboard].
	^super controlActivity*/
}

public void readKeyboard(){
/*readKeyboard

	"Lee el valor del teclado. En esta aplicacion no se utilizara el teclado para generar comandos.  Por esa razon, este metodo solamente leera
	 el contenido del teclado y no tomara accion alguna (i.e., no invocara ningun metodo del modelo)"

	| char |

	char := self sensor keyboardEvent keyCharacter.
	char asLowercase.
	^char.*/
}

}
