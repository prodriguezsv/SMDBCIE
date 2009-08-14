/**
 * Paquete que implementa una red de discriminaci&oacute;n redundante que representa el mecanismo de
 * identificaci&oacute;n de las llaves taxon&oacute;micas
 * @see "Categoría Main de SUKIA Smalltalk"
 */
package redundantDiscriminationNet;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.JOptionPane;

import ontology.CBR.Case;
import ontology.common.Descriptor;

import redundantDiscriminationNet.auxiliary.ComparingTable;
import redundantDiscriminationNet.auxiliary.ComparingTableTuple;
import redundantDiscriminationNet.auxiliary.ProblemSolutions;

/**
 * Entry access structure to a redundant discrimination net.  This class implements all necessary methods
 * to establish a net root, and add new cases, indices, and norms.
 * @author Armando
 * 
 */
public class RDNet {
	/**
	 *  Instance of class RootNorm.  This is the main entry structure to the rest of the net.
	 */
	private RootNorm root;
	/**
	 *  Control structure used by the case-adding methods.  As new Norm's are reached during net traversal,
	 *  their attributes are placed in this stack to make sure that no indices will be duplicated.
	 */
	private Stack<String> route;
	/**
	 * 	Pointer to the Case to be inserted into the net.
	 */
	private Case caseInsert;
	/**
	 *  A Stack that contains instances of Case's found during net traversal.
	 */
	private Stack<Case> casesToCompare;
	/**
	 * 	Case-To-Insert description, used to traverse the net, and create new Norms and indices.
	 */
	private List<Descriptor<Object>> ciDescription;
	/**
	 * Pointer to the current Norm during net traversal.
	 */
	private Norm currentNorm;
	/**
	 * 	Instance of a Problem Case to be resolved.
	 */
	private ProblemSolutions problemSolutions;
	/**
	 *  Controls the number of recusrive calls during case-to-insert and case-to-compare comparisons,
	 *  in the cas-adding methods.
	 */
	private int recursionCount;
	
	/**
	 * Constructor por defecto
	 */
	public RDNet(RootNorm root) {
		setRoot(root);
		setRoute(new Stack<String>());
		setCaseToInsert(null);
		setCasesToCompare(new Stack<Case>());
		setCiDescription(new ArrayList<Descriptor<Object>>());
		setCurrentNorm(null);
		setProblemSolutions(new ProblemSolutions());
		setRecursionCount(0);
	}

	/**
	 * M&eacute;todo accesor de escritura
	 * @param root
	 */
	public void setRoot(RootNorm root) {
		this.root = root;
	}

	/**
	 * M&eacute;todo accesor de lectura
	 * @see "M&eacute;todo root del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public RootNorm getRoot() {
		return root;
	}

	/**
	 * M&eacute;todo accesor de escritura
	 * @param route
	 */
	public void setRoute(Stack<String> route) {
		this.route = route;
	}

	/**
	 * M&eacute;todo accesor de lectura
	 * @return
	 */
	public Stack<String> getRoute() {
		return route;
	}
	
	/**
	 * M&eacute;todo accesor de lectura
	 * @see "M&eacute;todo route del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Object getNexRoute() {
		return route.peek();
	}

	/**
	 * M&eacute;todo accesor de escritura
	 * @see "M&eacute;todo copyCase: del protocolo reading en SUKIA SmallTalk"
	 * @param caseToInsert
	 */
	public void setCaseToInsert(Case caseToInsert) {
		this.caseInsert = caseToInsert;
	}

	/**
	 * M&eacute;todo accesor de lectura
	 * @see "M&eacute;todo caseToInsert del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Case getCaseToInsert() {
		return caseInsert;
	}

	/**
	 * M&eacute;todo accesor de escritura
	 * @param caseCompare
	 */
	public void setCasesToCompare(Stack<Case> caseCompare) {
		this.casesToCompare = caseCompare;
	}

	/**
	 * M&eacute;todo accesor de lectura
	 * @return
	 */
	public Stack<Case> getCasesToCompare() {
		return casesToCompare;
	}
	
	/**
	 * M&eacute;todo accesor de lectura
	 * @see "M&eacute;todo caseToCompare del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Case getCaseToCompare() {
		return casesToCompare.peek();
	}

	/**
	 * M&eacute;todo accesor de escritura
	 * @param ciDesc
	 */
	public void setCiDescription(List<Descriptor<Object>> ciDesc) {
		this.ciDescription = ciDesc;
	}

	/**
	 * M&eacute;todo accesor de lectura
	 * @see "M&eacute;todo caseToInsertDesc del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Descriptor<Object>> getCiDescription() {
		return ciDescription;
	}
	
	/**
	 * @see "Método getCaseDescription del protocolo reading en SUKIA SmallTalk"
	 * @param aCase
	 */
	public void getCiDescription(Case aCase) {
		List<Descriptor<Object>> desc;
		
		desc = aCase.getDescription(this.getRoot().getStructure());
		
		this.setCiDescription(desc);
	}

	/**
	 * M&eacute;todo accesor de escritura
	 * @param currNorm
	 */
	public void setCurrentNorm(Norm currNorm) {
		this.currentNorm = currNorm;
	}

	/**
	 * M&eacute;todo accesor de lectura
	 * @see "M&eacute;todo currNorm del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Norm getCurrentNorm() {
		return currentNorm;
	}

	/**
	 * M&eacute;todo accesor de escritura
	 * @param problemSolutions
	 */
	public void setProblemSolutions(ProblemSolutions problemSolutions) {
		this.problemSolutions = problemSolutions;
	}

	/**
	 * M&eacute;todo accesor de lectura
	 * @see "M&eacute;todo problemCase del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public ProblemSolutions getProblemSolutions() {
		return problemSolutions;
	}

	/**
	 * M&eacute;todo accesor de escritura
	 * @param recursionCount
	 */
	public void setRecursionCount(int recursionCount) {
		this.recursionCount = recursionCount;
	}

	/**
	 * M&eacute;todo accesor de lectura
	 * @see "M&eacute;todo recursionCount del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public int getRecursionCount() {
		return recursionCount;
	}
	
	/**
	 * @see "M&eacute;todo decrementRecursionCount del protocolo operation-private en SUKIA SmallTalk"
	 */
	private void decrementRecursionCount() {
		recursionCount = recursionCount - 1;
	}
	
	/**
	 * @see "M&eacute;todo incrementRecursionCount del protocolo operation-private en SUKIA SmallTalk"
	 */
	private void incrementRecursionCount() {
		recursionCount = recursionCount + 1;
	}
	
	/**
	 * @see "M&eacute;todo add: del protocolo adding en SUKIA SmallTalk"
	 * @param aCase
	 * @return
	 */
	public void add(Case aCase) {
		// Get ready: Clear working variables, get the case's description, copy the case, and set the current norm
		this.setToDefault();
		this.setCiDescription(aCase.getDescription(this.getRoot().getStructure()));
		this.setCaseToInsert(aCase);
		this.setCurrentNorm(this.getRoot());
		this.getCurrentNorm().incrementNumCasesBy(1);

		// Start the adding process
		this.processCase();
	}	
	
	/**
	 * @see "M&eacute;todo moveToNormWith:incrementValue: del protocolo operation-private en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @param aNumber
	 */
	private boolean moveToNorm(Descriptor<Object> aDescriptor, int aNumber) {
		List<Descriptor<Object>> tmpDesc;
		SheetCase sn;
		boolean retVal;
		
		this.setCurrentNorm(this.getCurrentNorm().getNearestSuccessorNorm(aDescriptor));
		this.getCurrentNorm().incrementNumCasesBy(aNumber);
		this.getRoute().push(this.getCurrentNorm().getDescriptor().getAttribute());

		if (this.isCaseToInsertDescUsedUp()) {
			sn = new SheetCase(aDescriptor);
			sn.setCase(this.getCaseToInsert());
			this.getCurrentNorm().addSuccessor(sn);
			// sn.addPredecessor(this.getCurrentNorm());

			this.getRoute().pop();
			this.setCurrentNorm(this.getCurrentNorm().getNearestPredecessorNorm());
			
			return true;
		}
		
		// Save current state of ciDesc, since a totally new checking process (with ciDesc) will take place,
		// considering all description elements
		tmpDesc = new ArrayList<Descriptor<Object>>();
		this.moveDescElements(this.getCiDescription(), tmpDesc);
		this.getCiDescription(this.getCaseToInsert());

		// Keep moving
		retVal = this.processCase();

		// Set ciDesc and everything else to the current state
		this.moveDescElements(tmpDesc, this.getCiDescription()); 
		this.removeMatchingElementsInTheRoute(this.getCiDescription());
		this.getRoute().pop();
		this.setCurrentNorm(this.getCurrentNorm().getNearestPredecessorNorm());

		return retVal;
	}
	
	/**
	 * @see "M&eacute;todo processCase del protocolo operation-private en SUKIA SmallTalk"
	 * @return
	 */
	private boolean processCase() {
		Descriptor<Object> d;
		Index ix;
		SheetCase sc;
		Node n;
		
		// Discard from the case's description all descriptors whose attribute is already in the route
		if (this.removeMatchingElementsInTheRoute(this.getCiDescription()).isEmpty())
			return true;

		while (!(this.getCiDescription().isEmpty())) {
			// Remove the first descriptor in the case-to-insert description
			d = this.getCiDescription().remove(0);

			// Search for an index with the label's value equal to d's attribute"
			ix = this.getCurrentNorm().getSuccessorIndex(d.getAttribute());
			if (ix == null) {
				// An index with d's attribute was not found. Must create a new one
				ix = new Index(d.getAttribute());
				ix.setPredecessor(this.getCurrentNorm());
				sc = new SheetCase(d);
				sc.setCase(this.getCaseToInsert());
				ix.addSuccessor(sc);
			} else {
				// An index with d's attribute was found. Find an node with d's value
				n = ix.getSuccessor(d);
				
				if (n == null) {
					// An node was not found.  Create a new node and append the caseToInsert as successor
					sc = new SheetCase(d);
					sc.setCase(this.getCaseToInsert());
					ix.addSuccessor(sc);
				} else {
					if (n instanceof Norm) {
						if (!this.moveToNorm(d, 1)) return false;
					} else {
						if (this.getCiDescription().isEmpty())
							if (!this.processCICCWithCIDescEmpty(d, ix, (SheetCase)n)) return false;
						else
							if (!this.processCICCWithCIDescNotEmpty(d, ix, (SheetCase)n)) return false;
					}
				}	 
			}
		}

		return true;
	}
	
	/**
	 * @see "M&eacute;todo processCICCWithCIDescEmpty:index:indexValue:successor: del protocolo operation-private en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @param anIndex
	 * @param aNodeList
	 * @param aSuccessor
	 */
	private boolean processCICCWithCIDescEmpty(Descriptor<Object> aDescriptor, Index anIndex, SheetCase sn) {
		boolean CIInserted;
		boolean CCInserted;
		Norm norm;
		
		// Verifica si las descripciones de los casos relativas a la estructura son iguales
		if (this.areDescriptionsEqual(this.getCaseToInsert(), sn.getCase()))
			return this.processCICCWithCIDescNotEmpty(aDescriptor, anIndex, sn);

		CIInserted = false;
		CCInserted = false;

		norm = new Norm(aDescriptor);
		norm.setPredecessor(anIndex);
		norm.incrementNumCasesBy(2);
		
		this.getCasesToCompare().push(sn.getCase());
		
		sn.removePredecessor(anIndex);
		
		this.getRoute().push(norm.getDescriptor().getAttribute());
		this.setCurrentNorm(this.getCurrentNorm().getNearestSuccessorNorm(aDescriptor));

		if (this.isCaseToInsertDescUsedUp()) {
			this.addSuccessorCase(this.getCaseToInsert(), this.getCurrentNorm(), aDescriptor);
			CIInserted = true;
		}
		
		if (this.isCaseToCompareDescUsedUp()) {
			this.getCurrentNorm().addSuccessor(sn);
			CCInserted = true;
		}
		
		this.setRecursionCount(0);

		if (CIInserted == false || CCInserted == false) {
			this.whenCCExists();
		} else {
			this.getCasesToCompare().pop();
			this.getRoute().pop();
			this.setCurrentNorm(this.getCurrentNorm().getNearestPredecessorNorm());
		}
		
		return true;
	}
	
	/**
	 * @see "M&eacute;todo processCICCWithCIDescNotEmpty:index:indexValue:successor: del protocolo operation-private en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @param anIndex
	 * @param aNodeList
	 * @param aSuccessor
	 */
	private boolean processCICCWithCIDescNotEmpty(Descriptor<Object> aDescriptor, Index anIndex, SheetCase sc) {
		Norm norm;
		
		norm = new Norm(aDescriptor);
		norm.setPredecessor(anIndex);
		/* Both caseToInsert and caseToCompare will live together under this new norm. Thus
		increment the number of (underlying) cases for it by 2*/
		norm.incrementNumCasesBy(2);
	
		// Remove the case-to-compare as successor from the IndexValue
		this.getCasesToCompare().push(sc.getCase());
		// Remove old index reference in the case-to-compare's index list
		sc.removePredecessor(anIndex);
		anIndex.addSuccessor(norm);

		this.getRoute().push(anIndex.getLabel());
		this.setCurrentNorm(this.getCurrentNorm().getNearestSuccessorNorm(aDescriptor));
		
		if (this.isCaseToCompareDescUsedUp())
			this.getCurrentNorm().addSuccessor(sc);
		
		this.setRecursionCount(0);
		
		return this.whenCCExists();
	}
	
	/**
	 * Recursive method that makes description comparisons between a case to be inserted and a case found
	 * during net traversal. Upon each call, a ComparingTable is created and it will contain tuples with
	 * every Descriptor in both case descriptions.  Next, the comparison task begins by removing, one by one,
	 * each of the ComparingTableTuple's.  If the values for the given tuple attribute are different, new
	 * indices are created and the cases are linked to them. If the values for the given tuple are the same,
	 * a new Norm is created and the method is invoked again to start a new Descriptor comparison. The method
	 * stops when all the tuples have been removed from the ComparingTable.
	 * @see "M&eacute;todo whenCCExists del protocolo operation-private en SUKIA SmallTalk"
	 */
	private boolean whenCCExists() {
		ComparingTable table;
		ComparingTableTuple<Object> tuple;
		Descriptor<Object> d;
		Norm norm;
		Index ix;
		
		this.incrementRecursionCount();

		// Create a table with all the descriptors for both the case to insert and the case to compare
		table = new ComparingTable();
		table.fill(this.getCaseToInsert().getDescription(this.getRoot().getStructure()), 
				this.getCaseToCompare().getDescription(this.getRoot().getStructure()));

		// Discard from the table all tuples whose attribute is already in the route
		this.removeMatchingElementsInTheRoute(table);

		/* If the resulting table is empty, then both (CC and CI) descriptions are already on the route.
		 * This means that, at this point, both descriptions are equal and used up along the net traversing.
		 * Thus, no more traversing is possible.
		 * 
		 */
		if (table.isEmpty()) {
			// Place the case-to-insert as successor of the current norm
			this.addSuccessorCase(this.getCaseToInsert(), this.getCurrentNorm(),
					this.getCurrentNorm().getDescriptor());

			// Place the case-to-compare as successor of the current norm
			this.addSuccessorCase(this.getCaseToCompare(), this.getCurrentNorm(),
					this.getCurrentNorm().getDescriptor());

			return doReturn();
		}
		
		/* At this point, the table CAN NOT be empty, and one of the following three situations is possible:
		 1. CI description is already used up, and CC description has at least one tuple lined up in the table;
		 2. CI description has at least one tuple lined up in the table, and CC description is used up;
		 3. There is at least one tuple in the table with CI and CC values.
		 */
		// Situation 1: If CI description is used up, place CI as successor of the current norm
		if (this.isCaseToInsertDescUsedUp())
			this.addSuccessorCase(this.getCaseToInsert(), this.getCurrentNorm(),
					this.getCurrentNorm().getDescriptor());
		
		// Situation 2: If CC description is empty, place CC as successor of the current norm
		if (this.isCaseToCompareDescUsedUp())
			this.addSuccessorCase(this.getCaseToCompare(), this.getCurrentNorm(),
					this.getCurrentNorm().getDescriptor());
		
		// Situation 3: Compare each table tuple
		while (!((tuple = table.extractTuple()) == null)) {
			// Create a new index and label it with the tuple's attribute
			ix = new Index(tuple.getAttribute());

			// Add the index to the current norm's index list
			this.getCurrentNorm().addSuccessor(ix);

			// Set the index predecessor (current) norm
			// ix.setPredecessor(this.getCurrentNorm()); 

			/* Check if the values for the tuple are different. 
			 * If Values are diferent, create a new index using the tuple's attribute
			 */
			if (!(tuple.getACIValue() == tuple.getACCValue())) {
				// If the case-to-insert value is non-null, attach it to the index
				if (!(tuple.getACIValue() == null)) {
					d = new Descriptor<Object>();
					d.set(this.getRoot().getStructure(), tuple.getAttribute(), tuple.getACIValue());
					this.addSuccessorCase(this.getCaseToInsert(), ix, d);
				}
				
				// If the case-to-compare value is non-nil, attach it to the index"
				if (!(tuple.getACCValue() == null)) {
					d = new Descriptor<Object>();
					d.set(this.getRoot().getStructure(), tuple.getAttribute(), tuple.getACCValue());
					this.addSuccessorCase(this.getCaseToCompare(), ix, d);
				}
			} else {
				// Create an empty norm and fill its values
				d = new Descriptor<Object>();
				d.set(this.getRoot().getStructure(), tuple.getAttribute(), tuple.getACCValue());
				norm = new Norm(d);
				norm.incrementNumCasesBy(2);

				// Link the recently created index to this new norm
				norm.setPredecessor(ix);				
				// ix.addSuccessor(norm);

				// Add the tuple's attribute to the route
				this.getRoute().push(tuple.getAttribute());

				// Push a new case to compare (Ojo se pone el mismo caso)
				this.getCasesToCompare().push(this.getCaseToCompare());

				// Move to the new norm
				this.setCurrentNorm(this.getCurrentNorm().getNearestSuccessorNorm(d));

				// Repeat the procedure for the new case to compare
				this.whenCCExists();

				this.removeRepeatingElementsInRoute(table);
			}
		}

		return this.doReturn();
	}
	
	private void addSuccessorCase(Case aCase, Node predecessor, Descriptor<Object> d) {
		SheetCase sc;
		
		// Place the case as successor of predecessor
		sc = new SheetCase(d);
		sc.setCase(aCase);
		
		predecessor.addSuccessor(sc);
	}
	
	/**
	 * Llena el vector solucion con los casos que podrian resolver el problema.
	 * @see "M&eacute;todo betterMatch del protocolo matching en SUKIA SmallTalk"
	 */
	public void betterMatch() {
		String str;
				
		this.getRoute().clear();
		this.setCurrentNorm(this.getRoot());
		
		this.readProblemCase();
		
		this.nextBestMatch();

		// Actualiza la solucion del caso problema
		// this.getProblemSolutions().setSolutionCases(this.getSolutions()); OJO verificar esta linea
		
		str = "";

		for( int i = 1; i <= this.getProblemSolutions().getSolutionCases().size(); i++) {
			str = str +  " " + this.getProblemSolutions().getSolutionCases().get(i-1).getSolution();
			
		}

		if (str.equals("")) 
			JOptionPane.showMessageDialog(null, "No hay solucion", "", JOptionPane.INFORMATION_MESSAGE);
		else JOptionPane.showMessageDialog(null, "Posible solucion: ", str, JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Calcula los pesos correspondientes a cada (atributo, valor) del caso problema en la norma actual.
	 * @see "Método computeWeights del protocolo matching en SUKIA SmallTalk"
	 */
	private void computeWeights() {
		List<Descriptor<Object>> description;
		Index actIndex;
		Node successor;
		
		description = this.getProblemSolutions().getProblemCase()
			.getDescription(this.getRoot().getStructure()); // Ojo hay que verificar está parte
		
		// Para cada descriptor del caso problema
		for(Descriptor<Object> d: description) {
			if (!(this.getRoute().contains(d.getAttribute()))) { 
				// Indice correspondiente a descriptor del caso problema. 
      		 	actIndex =  this.getCurrentNorm().getSuccessorIndex(d);
				// Si el indice para el atributo del caso problema no existe
	  			if (actIndex == null) {
	  				this.getProblemSolutions().setWeight(description.indexOf(d), -1); 
	  			} else {
					// OJO: HAY QUE ARREGLAR ESTA PARTE
					successor = actIndex.getSuccessor(d);
					if (successor == null) {
						this.getProblemSolutions().setWeight(description.indexOf(d), -1);
					} else {
						if (!(successor instanceof Norm)) 
							this.getProblemSolutions().setWeight(description.indexOf(d), 1);
						else
							this.getProblemSolutions().setWeight(description.indexOf(d), ((Norm)successor).getNumCases());
					}
				}	  					
	  		}
		}		
	}
	
	/**
	 * Cuando ya no existen mas atributos  en el caso,  que guien la busqueda de la solución busca en los indices de la norma actual,
	 * posibles soluciones que no contradigan al caso problema, de forma que los indices no hayan sido utilizados.
	 * aNorm : corresponde a la norma actual (utilizada en el backtracking).
	 * @see "Método gotDeadEnd del protocolo matching en SUKIA SmallTalk"
	 * @param aNorm
	 */
	private void gotDeadEnd(Norm aNorm) {
		List<Index> indexes;
		List<Node> successors;
										  
		indexes = aNorm.successorIndexes();
		
		for(Index n1: indexes) {
			// Si no existe el atributo en el caso problema, es una posible ruta a visitar.
			if (this.getProblemSolutions().getValue( n1.getLabel()) == null ) {
				successors = n1.getSuccessors();
				for(Node n2: successors) {
					if (n2 instanceof SheetCase) {
						// Si es un caso hay que propornerlo como solucion.
						this.getProblemSolutions().getSolutionCases().add(((SheetCase)n2).getCase());
					} else {
						// Si es una norma hay con continuar buscando casos bajo esta norma.
						this.gotDeadEnd((Norm) n2);
					}
				}
			}
		}
	}
	
	/**
	 * Devuelve el indice del menor de los pesos del caso problema de forma que el atributo asociado no este
	 * en la ruta actual. Si no existe un mínimo válido  (es decir diferente de -1 y que no pertenece a la
	 * ruta) devuelve null.
	 * @see "Método lowestWeight del protocolo matching en SUKIA SmallTalk"
	 * @return
	 */
	private int lowestWeight() {
		int posicion, min, valor;
		
		posicion = 0;
		min = this.getRoot().getNumCases();

		for( int i = 1; i <= this.getProblemSolutions().getProblemCase().getProblem().getDescription().size(); i++) {
			valor = this.getProblemSolutions().getWeight(i-1);
			if (!(this.getRoute().contains(this.getProblemSolutions().getProblemCase().getProblem().getDescription().get(i-1).getAttribute())) && 
					( valor <= min)    &&	!( valor == -1 )) {
				min = valor;
				posicion = i;
			}
		}
	
		if (posicion == 0) return -1;
		
		return posicion;
	}
	
	/**
	 * Busca el siguiente mejor caso para solucionar el problema
	 * @see "Método nextBestMatch del protocolo matching en SUKIA SmallTalk"
	 */
	private void nextBestMatch() {
		int mostPredictivePos;
		Descriptor<Object> mostPredictiveDescriptor;
		Index predictiveIndex;
		Node successor;
		
		this.computeWeights();
		mostPredictivePos =  this.lowestWeight();

		if (!(mostPredictivePos == -1 )) {
			mostPredictiveDescriptor = this.getProblemSolutions().getProblemCase()
				.getProblem().getDescription().get(mostPredictivePos-1);
			
			// Indice correspondiente al descriptor mas predictivo del caso problema. 
  		 	predictiveIndex = this.getCurrentNorm().getSuccessorIndex(mostPredictiveDescriptor);
  		 	
  		 	// OJO: HAY QUE ARREGLAR ESTA PARTE
			successor = predictiveIndex.getSuccessor(mostPredictiveDescriptor);

			if (successor instanceof SheetCase) {
				this.getRoute().push(mostPredictiveDescriptor.getAttribute());

				/* Como la red es redundantisima no es necesario utilizar mas de una vez un atributo
				self problemCase weightsAt: posMasPredictivo  modifiedWith: -1.*/
				this.getProblemSolutions().getSolutionCases().add(((SheetCase)successor).getCase());
				
				mostPredictivePos =  this.lowestWeight();
				if (!(mostPredictivePos == -1)) {
					// self route flush.
					this.setCurrentNorm(root);
					this.nextBestMatch();
				}
			} else {
				// El indice me lleva a una norma
				this.getRoute().push(mostPredictiveDescriptor.getAttribute());

				// Como la red es redundantisima no es necesario utilizar mas de una vez un atributo
			 	this.getProblemSolutions().setWeight(mostPredictivePos, -1);

			 	this.setCurrentNorm((Norm)successor);
			 	this.nextBestMatch();																															
			}
		} else {
			/* Los atributos restantes no tienen valor en la memoria de casos
			 * Hay que guiar al usuario para descubrir que mas sabe. Si sabe algo mas => metodo de preguntas.
			 * Actualice la norma actual. Cuando el usuario ya no sabe nada mas, saque todos los casos hacia
			 * abajo de la norma actual
			 */
			
			this.gotDeadEnd(this.getCurrentNorm());
			
			// Verifique si aun quedan atributos del caso problema sin usar
			
			this.setCurrentNorm(root);
			this.computeWeights();
			mostPredictivePos =  this.lowestWeight();

			if (!(mostPredictivePos == -1))
				this.nextBestMatch();
		}
	}
	
	/**
	 * Permite leer y buscar la solucion para un nuevo caso problema.
	 * @see "Método readProblemCase del protocolo matching en SUKIA SmallTalk"
	 */
	private void readProblemCase() {
		List<Index> rootIndexes;
		Descriptor<Object> desc;
		String value;
		Object simbolValue;
		
		rootIndexes = this.getRoot().successorIndexes();

		// Limpia el vector de deficion del caso problema
		this.problemSolutions.setToDefault();

		desc = new Descriptor<Object>();

		for(Index ix: rootIndexes) {
			value =  JOptionPane.showInputDialog(ix.getLabel());
			if (!(value == null)) {
				if (ix.getSuccessors().get(0).getValue() instanceof String)
					simbolValue =  value;
				else simbolValue =  Double.parseDouble(value);
				desc.set(this.getRoot().getStructure(), ix.getLabel(), simbolValue);
				this.getProblemSolutions().addToDescription(desc);
			}
		}
	}
	
	/**
	 * @see "Método doReturn del protocolo operation-private en SUKIA SmallTalk"
	 */
	private boolean doReturn() {
		this.decrementRecursionCount();
		
		this.getCasesToCompare().pop();
		this.getRoute().pop();
		
		this.getCurrentNorm().getNearestPredecessorNorm();
		
		return true;
	}
	
	/**
	 * @see "Método removeRepeatingElementsInRouteFrom: del protocolo operation-private en SUKIA SmallTalk"
	 * @param aTable
	 */
	private void removeRepeatingElementsInRoute(ComparingTable aTable) {
		
		for (ComparingTableTuple<Object> t: aTable) {
			if (!((this.getCurrentNorm().getSuccessorIndex(new Descriptor<Object>(null, t.getAttribute(), t.getACIValue())) == null) ||
					(this.getCurrentNorm().getSuccessorIndex(new Descriptor<Object>(null, t.getAttribute(), t.getACCValue())) == null)))
				aTable.remove(t);
		}
	}
	
	/**
	 * @see "Método isCaseToCompareDescUsedUp del protocolo testing en SUKIA SmallTalk"
	 * @return
	 */
	private boolean isCaseToCompareDescUsedUp() {
		List<Descriptor<Object>> ccCopy;
		String a;
		
		ccCopy = this.getCaseToCompare().getDescription(this.getRoot().getStructure());
		for (Descriptor<Object> d:ccCopy) {
			a = d.getAttribute();
			if (this.getRoute().contains(a)) {
				ccCopy.remove(a);
			}
		}
		
		if (!(ccCopy.isEmpty())) return false;
		return true;
	}
	
	/**
	 * @see "Método isCaseToInsertDescUsedUp del protocolo testing en SUKIA SmallTalk"
	 * @return
	 */
	private boolean isCaseToInsertDescUsedUp() {
		List<Descriptor<Object>> ccCopy;
		String a;
		
		ccCopy = this.getCaseToInsert().getDescription(this.getRoot().getStructure());
		for (Descriptor<Object> d:ccCopy) {
			a = d.getAttribute();
			if (this.getRoute().contains(a)) {
				ccCopy.remove(a);
			}
		}
		
		if (!(ccCopy.isEmpty())) return false;
		
		return true;
	}
	
	/**
	 * @see "Método flush del protocolo resetting en SUKIA SmallTalk"
	 */
	private void setToDefault() {
		this.getRoute().clear();
		this.setCaseToInsert(null);
		this.getCasesToCompare().clear();
		this.getCiDescription().clear();
		this.setCurrentNorm(null);
		this.getProblemSolutions().setToDefault();
		this.setRecursionCount(0);
	}
	
	/**
	 * @see "Método moveDescElementsFromoneDescList:to: del protocolo reading en SUKIA SmallTalk"
	 * @param oneDescList
	 * @param anotherDescList
	 * @return
	 */
	private boolean moveDescElements(List<Descriptor<Object>> oneDescList, List<Descriptor<Object>> anotherDescList) {
		if (oneDescList.isEmpty()) return false;
		
		while (oneDescList.isEmpty()) {
			anotherDescList.add(oneDescList.remove(0));
		}
		
		return true;
	}
	
	/**
	 * Removes elements from a (case's) description, whose attribute is already included in the Route.
	 * IMPORTANT NOTE: The elements from aDescriptionList MUST be able to provide the descriptor's
	 * attribute.
	 * @see "Método removeMatchingElementsInTheRouteFrom: del protocolo removing en SUKIA SmallTalk"
	 * @param aDescriptionList
	 */
	private ComparingTable removeMatchingElementsInTheRoute(ComparingTable aDescriptionList) {

		for (ComparingTableTuple<Object> ctt: aDescriptionList) {
			if (this.getRoute().contains(ctt.getAttribute()))
				aDescriptionList.remove(ctt);
		}

		return aDescriptionList;
	}
	
	/**
	 * Removes elements from a (case's) description, whose attribute is already included in the Route.
	 * IMPORTANT NOTE: The elements from aDescriptionList MUST be able to provide the descriptor's
	 * attribute.
	 * @see "Método removeMatchingElementsInTheRouteFrom: del protocolo removing en SUKIA SmallTalk"
	 * @param aDescriptionList
	 */
	private List<Descriptor<Object>> removeMatchingElementsInTheRoute(List<Descriptor<Object>> aDescriptionList) {

		for (Descriptor<Object> d: aDescriptionList) {
			if (this.getRoute().contains(d.getAttribute()))
				aDescriptionList.remove(d);
		}

		return aDescriptionList;
	}
	
	/**
	 * @see "Método areDescriptionsEqualFor:and: del protocolo testing en SUKIA SmallTalk"
	 * @param aCase1
	 * @param aCase2
	 * @return
	 */
	private boolean areDescriptionsEqual(Case aCase1, Case aCase2) {
		List<Descriptor<Object>> desc1, desc2;

		desc1 = aCase1.getDescription(this.getRoot().getStructure());
		desc2 = aCase2.getDescription(this.getRoot().getStructure());
		
		// If the cases' descriptions are of different size, then they are not equal
		if (!(desc1.size() == desc2.size())) return false;
		
		// For every element in the description of aCase1: if its attribute-value pair matches one in desc2,
		// then delete that element from desc2
		for(Descriptor<Object> d1: desc1) {
			for(Descriptor<Object> d2: desc2) {
				if ((d1.getAttribute().equals(d2.getAttribute())) && (d1.getValue().equals(d2.getValue()))) {
					desc2.remove(d2);
					break;
				}
			}
		}

		// If the resulting desc2 is empty, then every element in aCase1 description matched with desc2. Thus, both descriptions are equal
		if (desc2.isEmpty()) return true;

		// Descriptions are different by at least one element
		return false;
	}
}
