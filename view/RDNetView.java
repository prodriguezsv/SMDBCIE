/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

/**
 * RDNetView es una especializacion de DrawingEditor que sirve de vista para el modelo RDNet.  En esta vista se construye una memoria de discriminació® ²edundate que almacena casos resueltos para problemas dados.  Los casos en el modelo son descritos por medio de tuplas que contienen pares atributo valor.  Los casos almacenados son utilizados para la resolució® €e nuevos casos.
 *
 * Nomenclatura del RDNetView:
 *
 * Los rectangulos sin relleno representan las normas de la red.
 * Los rectangulos rellenos representan los indices.
 * Los circulos representan los casos almacenados.
 *
 *
 * Varibles de instancia:
 *
 * lastXUsed : almacena la ï¿¿ltima coordenada X utilizada.  Se utiliza durante  el despliegue de la red de casos,  para crear una cuadricula imaginaria de forma que ningï¿¿n componente de la red se encuentre en la misma coordenada X, excepto el primer hijo derecho de una norma o un indice.
 *
 *
 * Utiliza instancias de las clases:
 * 	CaseFigure
 * 	IndexFigure
 * 	IndexValueFigure
 * 	NormFigure
 * para desplegar todos los componentes de la red.
 * @author pabloq
 */
public class RDNetView {
/*
<name>RDNetView</name>
<environment>Smalltalk</environment>
<super>Refactory.HotDraw.DrawingEditor</super>
<private>false</private>
<indexed-type>none</indexed-type>
<inst-vars>lastXUsed </inst-vars>
<class-inst-vars></class-inst-vars>
<imports></imports>
<category>CBR - View</category>
*/

 /*
 *Category defaults
 */


public void defaultTools(){
/*defaultTools

	"Conjunto de herramientas mostradas en el palette. Las herramientas son:
	 1. Un seleccionador de objetos graficos, representado por una flecha"

	^(OrderedCollection new)
		add: Tool selectionTool;
		yourself*/
}


 /*
 *Category instance creation
 */

public void example(){
/*example
	"Ejemplo de memeoria de casos, construido con 4 casos ."

| c1 c2 c3 c4 d r |

"self halt."

c1 := Case new.
c2 := Case new.
c3 := Case new.
c4 := Case new.
d := Descriptor new.
r := RDNet new.

c1 setSolutionWith: ((CaseSolution new) name: #SS1).
d addAttribute: #Temp withValue: 40.
c1 addToDescription: d.
d addAttribute: #Tos withValue: #si.
c1 addToDescription: d.
d addAttribute: #DP withValue: #si.
c1 addToDescription: d.
d addAttribute: #SN withValue: #si.
c1 addToDescription: d.
c1 setStatusWith: #+.

c2 setSolutionWith: ((CaseSolution new) name: #SS2).
d addAttribute: #Temp withValue: 38.
c2 addToDescription: d.
d addAttribute: #Tos withValue: #si.
c2 addToDescription: d.
d addAttribute: #SN withValue: #si.
c2 addToDescription: d.
c2 setStatusWith: #+.

c3 setSolutionWith: ((CaseSolution new) name: #SS3).
d addAttribute: #Tos withValue: #si.
c3 addToDescription: d.
d addAttribute: #DP withValue: #si.
c3 addToDescription: d.
d addAttribute: #Torax withValue: #muestra_oscura.
c3 addToDescription: d.
d addAttribute: #Fumador withValue: #si.
c3 addToDescription: d.
c3 setStatusWith: #+.

c4 setSolutionWith: ((CaseSolution new) name: #C4).
d addAttribute: #Temp withValue: 39.
c4 addToDescription: d.
d addAttribute: #Tos withValue: #si.
c4 addToDescription: d.
d addAttribute: #DP withValue: #si.
c4 addToDescription: d.
d addAttribute: #SN withValue: #no.
c4 addToDescription: d.
d addAttribute: #Fumador withValue: #si.
c4 addToDescription: d.
c4 setStatusWith: #+.

"El caso problema"
r problemCase setSolutionWith: ((CaseSolution new) name: #CP).
"d addAttribute: #SN withValue: #no.
r  problemCase addToDescription: d.
d addAttribute: #Tos withValue: #si.
r  problemCase addToDescription: d.
d addAttribute: #DP withValue: #si.
r problemCase addToDescription: d."

r add: c1.
r add: c2.
r add: c3.
r add: c4.

self halt.
(RDNetView openWith: r) inspect.*/
}

public void openWith(){
/*openWith: anRDNet

	"Asigna a la metafigura de la vista una instacia construida del modelo RDNet.  Es decir el modelo de la red completa a desplegar.
	 anRDNet debe contener una instacia del modelo."

	| editor |

	editor := self new.
	editor drawing: Refactory.HotDraw.Drawing new.
      editor drawing model: anRDNet.
	editor open.
      editor initializeNetView.
      ^editor.*/
}

public void windowSpec(){
/*windowSpec
	"UIPainter new openOnClass: self andSelector: #windowSpec"

	&lt;resource: #canvas&gt;
	^#(#{UI.FullSpec}
		#window:
		#(#{UI.WindowSpec}
			#label: 'Red de Discriminacion Redundante'
			#bounds: #(#{Graphics.Rectangle} 800 600 1355 1080 )
			#isEventDriven: true )
		#component:
		#(#{UI.SpecCollection}
			#collection: #(
				#(#{UI.ArbitraryComponentSpec}
					#layout: #(#{Graphics.LayoutFrame} 0 0 28 0 0 1 0 1 )
					#flags: 11
					#colors:
					#(#{UI.LookPreferences}
						#setBackgroundColor: #(#{Graphics.ColorValue} #white ) )
					#component: #drawing )

"#(#{UI.ArbitraryComponentSpec}
					#layout: #(#{Graphics.LayoutFrame} 0 0 0 0 0 1 28 0 )
					#flags: 0
					#component: #toolbar )"
) ) )*/
}


 /*
 *Category initializing
 */

public void initialize(){
/*initialize

	^self.*/
}

public void initializeNetView(){
/*initializeNetView

	"Inicializa la metafigura de la raiz e invoca al metodo de despliege de la norma raiz y sus indices suscesores.  Luego de que se despliega  toda la red,
	despliega en la esquina superior derecha el caso problema y las mejores posibles soluciones."

	| aRoot |

	aRoot := (self drawing) model root.

	"Despliegue de la norma raiz y sus hijos"
	self lastXUsed: 10.
	self displayNormAt: 20 with: aRoot startLine: 0@0 .

	^self.*/
}


 /*
 *Category displaying
 */


public void displayCaseAt(){
/*displayCaseAt:  aY  with: aCase startLine: aPosCenterFather  indexValue: aIndexValue

	"Despliega un caso.
	 Donde:
		aY: es un entero que contiene la coordenada Y en donde se va a desplegar el caso.
			Recuerde que la coordenada X es una variable de instancia.
		aCase: debe contener la metafigura del caso a desplegar (un caso del modelo).
		aPosCenterFather: debe contener la coordenada central inferior de la figura padre (coordenada de la forma x@y).
		 aIndexValue: 	es parte del modelo y contiene una Instancia de IndexValue de la forma (value, successor),
						donde value representa el valor del indice que me lleva a este caso."

	| aFiguraCaso linea |

	aFiguraCaso := CaseFigure createAt: ( lastXUsed@aY ) with: aCase.
	(self drawing) add: aFiguraCaso.

	(aIndexValue = nil )
	ifTrue: [		linea :=  Refactory.HotDraw.LineFigure start: aPosCenterFather stop: (((aFiguraCaso components) at: 1) topCenter).
				(self drawing) add: linea. ]

	ifFalse: [	IndexValueFigure createAt: aPosCenterFather
					stop: (((aFiguraCaso components) at: 1) topCenter)
					with:aIndexValue drawing:(self drawing). ].
	^ self.*/
}

public void displayIndexAt(){
/*displayIndexAt: aY with: aIndex  startLine: aPosCenterFather

	"Despliega el indice y realiza las gestiones requeridas para que se despliegen sus indices suscesores..
	 	Donde:
		aY: es un entero que contiene la coordenada Y en donde se va a desplegar el caso.
			Recuerde que la coordenada X es una variable de instancia.
		aIndex: debe contener la metafigura del indice a desplegar (un indice del modelo).
		aPosCenterFather: debe contener la coordenada central inferior de la figura padre (coordenada de la forma x@y). "

	| aFiguraIndice aCaso linea sucesores x |

	aFiguraIndice := IndexFigure createAt: ( (self  lastXUsed) @aY )  with: aIndex.
	linea :=  Refactory.HotDraw.LineFigure start: aPosCenterFather stop: (((aFiguraIndice components) at: 1) topCenter).

	(self drawing) add: aFiguraIndice.
	(self drawing) add: linea.

	1 to: ( aIndex successors size ) do:
		[ :indice |
				sucesores := ((aIndex successors) at: indice) successors.
				x := 1.
				[ x &lt;= sucesores size ]
				whileTrue: [

					((((sucesores at: x) class name) = (Case getClassName)) |
					 (((sucesores at: x) class name) = (SAVCase getClassName)))
					ifTrue: [
						aCaso := sucesores at: x.
						self displayCaseAt: (aY + 70 )
							with: aCaso
							startLine: (((aFiguraIndice components) at: 1) bottomCenter)
							indexValue: ((aIndex successors) at: indice). ]

					ifFalse: [
						self displayNormAt: ( aY + 70 )
							with: (sucesores at: x)
							startLine: (((aFiguraIndice components) at: 1) bottomCenter) ].

					self lastXUsed: (lastXUsed + 45).
					x := x + 1.
				].
		].

	^self.*/
}

public void displayNormAt(){
/*displayNormAt: aY with:  aNorm  startLine: aPosCenterFather

	"Despliega la norma y realiza las gestiones requeridas para que se despliegen sus indices suscesores.
	 	Donde:
		aY: es un entero que contiene la coordenada Y en donde se va a desplegar el caso.
			Recuerde que la coordenada X es una variable de instancia.
		aNorm: debe contener la metafigura de la norma a desplegar (una norma del modelo).
		aPosCenterFather: debe contener la coordenada central inferior de la figura padre (coordenada de la forma x@y)."

	| aFiguraNorma linea sucesor |

	(aNorm isRoot)
	ifTrue: [
		aFiguraNorma := NormFigure createAt: ( 430@aY ) with: aNorm.
		(self drawing) add: aFiguraNorma. ]

	ifFalse:[
		aFiguraNorma := NormFigure createAt: ( (self  lastXUsed)@aY ) with: aNorm.
		linea :=  Refactory.HotDraw.LineFigure start: aPosCenterFather stop: (((aFiguraNorma components) at: 1) topCenter).
		(self drawing) add: aFiguraNorma.
		(self drawing) add: linea. ].

	1 to: ( aNorm successors size) do:
	[ :indice |

		sucesor := (aNorm successors) at: indice.

		(((sucesor class name) = (Case getClassName)) |
		 ((sucesor class name) = (SAVCase getClassName)))
		ifTrue: [ self displayCaseAt:  (aY + 100) with: sucesor startLine: (((aFiguraNorma components) at: 1) bottomCenter) indexValue: nil ].

		((sucesor class name) = (Index getClassName))
		ifTrue: [ self displayIndexAt:  (aY + 100) with: sucesor startLine: (((aFiguraNorma components) at: 1) bottomCenter) ].

		self lastXUsed: ( lastXUsed + 30 ).
	].

	^self.*/
}


 /*
 *Category accessing
 */

public void lastXUsed(){
/*lastXUsed

	"Retorna la ï¿¿ltima posició® µtilizada en el eje X. "

	^lastXUsed.*/
}

public void lastXUsed(Object c){
/*lastXUsed: aX

	"Actualiza la ï¿¿ltima posició® µtilizada en el eje X. "

	lastXUsed := aX .
	^lastXUsed.*/
}


 /*
 *Category resource
 */


public void menu(){
/*menu

	"Retorna el menï¿¿ para el editor."

		^PopUpMenu

			labels: 'Caso a resolver' withCRs
			values: #(#caseResolving).*/
}


 /*
 *Category installing
 */

public void caseResolving(){
/*caseResolving

	"Permite leer y buscar la solucuó® °ara un nuevo caso problema."

	"Busca la mejor solucion para el caso problema"
	( (self drawing) metaFigure)  betterMatch.

	^self.*/
}

}
