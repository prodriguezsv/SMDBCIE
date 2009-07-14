/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

/**
 * NormFigure es una especializacion de CompositeFigure y permite desplegar una norma en la Vista.  La norma se despliega en forma de rectangulo con el descriptor en forma de hilera (Norm descriptor) en el centro.
 *
 * Variable de instacia.
 * 	constraints : vector de restriciones utilizadas por HotDraw.
 * @author pabloq
 */
public class NormFigure {

/*
<name>NormFigure</name>
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

	^(self new initializeAt: aPoint with: aNorm).*/
}

 /*
 *Category initializing
 */

public void initializeAt(){
/*initializeAt: aPoint  with: aNewNorm

	"Crea una instancia de la figura para la norma.  Las normas se representan con un rectangulo blanco con bordes.
		Donde:
			aNewNorm es una instancia que modela una norma (instancia de Norm).
			aPoint es el punto origen del rectangulo."

	| aNorm coords labelText normLabel |

	self metaFigure: aNewNorm.

	"Creacion de la figura de la norma"
	aNorm := Refactory.HotDraw.RectangleFigure createAt: aPoint.
	aNorm corner: ((aPoint x) + 90) @ ((aPoint y) + 30); lineColor: ColorValue blue.

	coords := Point x: (aNorm bottomLeft x) + 5
         	                y: (aNorm bottomLeft y) - 20.

	"Creacion del afigura de texto y sus atributos, verificando antes si la norma es la de la raiz"
	(self metaFigure isRoot)
	ifTrue: [ labelText := ('(nil, nil): ' , (self metaFigure numCases printString)) asParagraph ]

	ifFalse: [
		( ( ( self metaFigure descriptor value)  class) name = #ByteSymbol)
			ifFalse: [
				labelText := ( '(' , ( self metaFigure descriptor attribute) , ',' ,
								(  ( self metaFigure descriptor value) printString  ) , '): ' ,
								(  ( self metaFigure numCases) printString ) ) asParagraph ]

			ifTrue:[
				labelText := ( '(',  ( self metaFigure descriptor attribute) , ',' ,
								(  ( self metaFigure descriptor value)   ) , '): ' ,
								(  ( self metaFigure numCases) printString ) ) asParagraph ].
	].

	normLabel := Refactory.HotDraw.TextFigure new.
	normLabel setParagraph: labelText origin: coords.

	self add: aNorm.
	self add: normLabel.

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

	"Define las opciones de menï¿¿ para la instancia de la figura (NormFigure)."

	^PopUpMenu

		labels: 'Inspeccionar' withCRs

		values: #(#inspect).*/
}


 /*
 *Category accessing
 */

public void handles(){
/*handles

	"Definicion del los handles laterales para una instancia de NormFigure"

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
/*metaFigure: aNorm

self model: aNorm.
^self.*/
}

}
