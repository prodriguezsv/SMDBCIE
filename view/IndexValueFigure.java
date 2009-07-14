/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

/**
 *IndexValueFigure es una especializacion de CompositeFigure y permite desplegar una liga de un í®€ice a caso en la Vista.  La liga debe llevar en un lugar visible el valor del í®©dce que lleva al caso apuntado.  La liga se despliega en forma de una lí®¥a.  Por el momento no se utiliza toda la funcionalidad de CompositeFigure.
 * Variable de instacia.
 * 	constraints : vector de restriciones utilizadas por HotDraw.
 * @author pabloq
 */
public class IndexValueFigure {


/*
<name>IndexValueFigure</name>
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
/*createAt: aIniPoint stop: aFinPoint with: aIndexValue drawing: aDrawing

	^( self new initializeAt: aIniPoint  stop: aFinPoint with: aIndexValue drawing: aDrawing).*/
}


/*
 *Category accessing
 */

public void handles(){
/*handles

	"Definicion del los handles laterales para una instancia de IndexValueFigure"

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

public void metaFigure(Object x){
/*metaFigure: aCase

self model: aCase.
^self.*/
}


 /*
 *Category resources
 */

public void menu(){
/*menu

	^PopUpMenu

		labels: 'Inspeccionar' withCRs

		values: #(#inspect).*/
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
 *Category transforming
 */

public void constrainCorner(){
/*constrainCorner: aMouseVariable

	^OrderedCollection with: (self constraintClass xyMouseConstraint: aMouseVariable on: corner).*/
}


 /*
 *Category copy
 */


public void postCopy(){
/*postCopy

	constraints := constraints copy.*/
}


 /*
 *Category initializing
 */


public void initializeAt(){
/*initializeAt: aIniPoint stop: aStopPoint  with: aIndexValue drawing: aDrawing

	"Asume que recibe aIndexValue una metafigura instancia de la clase IndexValue."

	| aFiguraIV labelText aIndexValueLabel |
	self metaFigure: aIndexValue.

	"Creacion de la figura del indexValue"
	aFiguraIV :=  Refactory.HotDraw.LineFigure start: aIniPoint stop: aStopPoint.
	aFiguraIV lineColor: (ColorValue black).

	"Creacion del afigura de texto y sus atributos"
	( ( ( ( self metaFigure) value )  class) name = #ByteSymbol)
	ifFalse: [ labelText := ( ( ( self metaFigure) value ) printString ) asParagraph ]
	ifTrue:[ labelText := ( ( self metaFigure) value )  asParagraph ].

	aIndexValueLabel := Refactory.HotDraw.TextFigure new.
	aIndexValueLabel setParagraph: labelText origin: (aFiguraIV center).

	self add: aFiguraIV.
	self add: aIndexValueLabel.

	aDrawing add: self.
	^self*/
}


}
