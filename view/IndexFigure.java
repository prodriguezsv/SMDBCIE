/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

/**
 * IndexFigure es una especializacion de CompositeFigure y permite desplegar un í®€ice en la Vista.  El í®€ice se despliega en forma de rectá®§ulo con la hilera etiqueta (Index label) en el centro.
 * Variable de instacia.
 * 	constraints : vector de restriciones utilizadas por HotDraw.
 * @author pabloq
 */
public class IndexFigure {

/*
<name>IndexFigure</name>
<environment>Smalltalk</environment>
<super>Refactory.HotDraw.CompositeFigure</super>
<private>false</private>
<indexed-type>none</indexed-type>
<inst-vars>constraints </inst-vars>
<class-inst-vars></class-inst-vars>
<imports></imports>
<category>CBR - View</category>
*/

/*
 *Category instance creation
 */

public void createAt(){
/*createAt: aPoint with: aIndex

	^( self new initializeAt: aPoint with: aIndex ).*/
}

 /*
 *Category initializing
 */

public void initializeAt(){
/*initializeAt: aPoint  with: aNewIndex

	"Crea una instancia de la figura para el indice.   Los indices se representan con un rectangulo relleno.
		Donde:
			aNewIndex es una instancia que modela un indice (instancia de Index).
			aPoint es el origen del rectangulo."

	| aIndex coords labelText indexLabel |

	self metaFigure: aNewIndex.

	"Creacion de la figura del indice"
	aIndex := Refactory.HotDraw.RectangleFigure createAt: aPoint.
	aIndex corner: ((aPoint x) + 60) @ ((aPoint y) + 30); lineColor: ColorValue blue; fillColor: ColorValue yellow.

	coords := Point x: (aIndex bottomLeft x) + 5
         	                y: (aIndex bottomLeft y) - 20.

	"Creacion del afigura de texto y sus atributos"
	labelText := self metaFigure label asParagraph.

	indexLabel := Refactory.HotDraw.TextFigure new.
	indexLabel setParagraph: labelText origin: coords.

	self add: aIndex.
	self add: indexLabel.

	^self.*/
}


 /*
 *Category copy
 */


public void postCopy(){
/*postCopy

	constraints := constraints copy.*/
}


 /*
 *Category transforming
 */

public void constrainCorner(){
/*constrainCorner: aMouseVariable

	^OrderedCollection with: (self constraintClass xyMouseConstraint: aMouseVariable on: corner).*/
}


 /*
 *Category constraints
 */

public void constraintsCollect(){
/*constraintsCollect: aBlock

	constraints := constraints collect: aBlock.
	^super constraintsCollect: aBlock*/
}

public void constraintsDo(){
/*constraintsDo: aBlock

	constraints do: aBlock.
	^super constraintsDo: aBlock*/
}


 /*
 *Category resources
 */

public void menu(){
/*menu

	"Define las opciones de menï¿¿ para la instancia de la figura (IndexFigure)."

	^PopUpMenu

		labels: 'Inspeccionar' withCRs

		values: #(#inspect).*/
}


 /*
 *Category accessing
 */


public void handles(){
/*handles

	"Definicion del los handles laterales para una instancia de IndexFigure"

	| aBlock |

	aBlock := [:source :dest :aFigure |

				dest addSource: source.
				aFigure addDependent: dest.
				aFigure metaFigure: (Array with: source).
				source addDependent: aFigure.
				dest addDependent: aFigure].

	^(super handles)

    		add: (CommandHandle
				connectionFor: self
				at: #topCenter:
				class: SplineFigure
				action: aBlock);

		add: (CommandHandle
				connectionFor: self
				at: #bottomCenter:
				class: SplineFigure
				action: aBlock);

		yourself*/
}

public void metaFigure(){
/*metaFigure

^self model.*/
}

public void metaFigure(Object c){
/*metaFigure: aCase

self model: aCase.
^self.*/
}

}
