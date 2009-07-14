/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

/**
 * CaseFigure es una especializacion de CompositeFigure y permite desplegar un caso en la Vista.  El caso se despliega en forma de circulo con la hilera solucion
 * (Case solution) en el centro.
 *
 * Variable de instancia.
 * 	constraints : vector de restriciones utilizadas por HotDraw.
 * @author pabloq
 */
public class CaseFigure {

/*
<name>CaseFigure</name>
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
/*createAt: aPoint with: aNorm

	^( self new initializeAt: aPoint with: aNorm).*/
}


 /*
 *Category initializing
 */

public void initializeAt(){
/*initializeAt: aPoint  with: aNewCase

	"Crea una instancia de la figura para el caso.
		Donde:
			aNewCase es una instancia que modela un caso (instancia de Case).
			aPoint es el punto central del circulo."

	| aCase coords labelText solution caseLabel |
	self metaFigure: aNewCase.

	"Creacion de la figura del caso"
	aCase :=  Refactory.HotDraw.EllipseFigure createAt: aPoint.
	aCase corner: ((aPoint x) + 30) @ ((aPoint y) + 30); lineColor: ColorValue red.

	coords := Point x: (aCase bottomLeft x) + 5
         	                y: (aCase bottomLeft y) - 20.

	labelText := ''.
	solution := self metaFigure solution name.
	(solution isNil) ifFalse: [ labelText := solution asParagraph ].

	caseLabel := Refactory.HotDraw.TextFigure new.
	caseLabel setParagraph: (labelText asParagraph) origin: coords.

	self add: aCase.
	self add: caseLabel.

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

	"Define las opciones de menï¿¿ para la instancia de la figura (CaseFigure)."

	^PopUpMenu

		labels: 'Inspeccionar' withCRs

		values: #(#inspect).*/
}


 /*
 *Category accessing
 */

public void handles(){
/*handles

	"Definicion del los handles laterales para una instancia de CaseFigure"

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
