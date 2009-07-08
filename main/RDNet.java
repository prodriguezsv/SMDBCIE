/**
 * @see "Categoría Main de SUKIA Smalltalk"
 */
package main;

import java.util.List;

import javax.swing.JOptionPane;

import patternMatching.ProblemCase;
import patternMatching.Solution;
import redundantDiscriminantNet.SAVCase;
import auxiliary.CBRStack;
import auxiliary.ComparingTable;
import auxiliary.ComparingTableTuple;
import auxiliary.IndexValue;

/**
 * Purpose: Entry access structure to a redundant discrimination net.  This class implements all necessary methods to establish a net root,
 * and add new cases, indices, and norms.
 * @author Armando
 *
 */
public class RDNet {
	private RootNorm root; 		// Instance of class RootNorm.  This is the main entry structure to the rest of the net.
	private CBRStack<String> route; 	// Control structure (CBRStack) used by the case-adding methods.  As new Norm's are reached during net traversal,
								// their attributes are placed in this stack to make sure that no indices will be duplicated.
	private Case caseInsert;	// Pointer to the Case to be inserted into the net.
	private CBRStack<Case> caseCompare; 	// A CBRStack that contains instances of Case's found during net traversal.
	private Description<Descriptor<Object>> ciDesc;	// Case-To-Insert description, used to traverse the net, and create new Norms and indices.
	private Norm currNorm; 				// Pointer to the current Norm during net traversal.
	private ProblemCase problemCase;	// Instance of a Problem Case to be resolved.
	private Solution solution;			// Solution object obtained from the adaptation of a Case found.
	private int recursionCount; 	// Controls the number of recusrive calls during case-to-insert and case-to-compare comparisons, in the cas-adding methods.
	
	/**
	 * 
	 */
	public RDNet() {
		setRoot(new RootNorm());
		setRoute(new CBRStack<String>());
		setCaseInsert(null);
		setCaseCompare(new CBRStack<Case>());
		setCiDesc(new Description<Descriptor<Object>>());
		setCurrNorm(null);
		setProblemCase(new ProblemCase());
		setSolution(new Solution());
		setRecursionCount(0);
	}

	public void setRoot(RootNorm root) {
		this.root = root;
	}

	/**
	 * @see "Método root del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public RootNorm getRoot() {
		return root;
	}

	public void setRoute(CBRStack<String> route) {
		this.route = route;
	}

	public CBRStack<String> getRoute() {
		return route;
	}
	
	/**
	 * @see "Método route del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Object getNexRoute() {
		return route.peek();
	}

	/**
	 * @see "Método copyCase: del protocolo reading en SUKIA SmallTalk"
	 * @param caseInsert
	 */
	public void setCaseInsert(Case caseInsert) {
		this.caseInsert = caseInsert;
	}

	/**
	 * @see "Método caseToInsert del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Case getCaseInsert() {
		return caseInsert;
	}

	public void setCaseCompare(CBRStack<Case> caseCompare) {
		this.caseCompare = caseCompare;
	}

	/**
	 * Método de instancia agregado
	 * @return
	 */
	public CBRStack<Case> getCaseCompare() {
		return caseCompare;
	}
	
	/**
	 * @see "Método caseToCompare del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Case getCaseToCompare() {
		return caseCompare.peek();
	}

	public void setCiDesc(Description<Descriptor<Object>> ciDesc) {
		this.ciDesc = ciDesc;
	}

	/**
	 * @see "Método caseToInsertDesc del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Description<Descriptor<Object>> getCiDesc() {
		return ciDesc;
	}

	public void setCurrNorm(Norm currNorm) {
		this.currNorm = currNorm;
	}

	/**
	 * @see "Método currNorm del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Norm getCurrNorm() {
		return currNorm;
	}

	public void setProblemCase(ProblemCase problemCase) {
		this.problemCase = problemCase;
	}

	/**
	 * @see "Método problemCase del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public ProblemCase getProblemCase() {
		return problemCase;
	}

	public void setSolution(Solution solution) {
		this.solution = solution;
	}

	/**
	 * @see "Método solution del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Solution getSolution() {
		return solution;
	}

	public void setRecursionCount(int recursionCount) {
		this.recursionCount = recursionCount;
	}

	/**
	 * @see "Método recursionCount del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public int getRecursionCount() {
		return recursionCount;
	}
	
	/**
	 * @see "Método decrementRecursionCount del protocolo operation-private en SUKIA SmallTalk"
	 */
	private void decrementRecursionCount() {
		recursionCount = recursionCount - 1;
	}
	
	/**
	 * @see "Método incrementRecursionCount del protocolo operation-private en SUKIA SmallTalk"
	 */
	private void incrementRecursionCount() {
		recursionCount = recursionCount + 1;
	}
	
	/**
	 * @see "Método add: del protocolo adding en SUKIA SmallTalk"
	 * @param aCase
	 * @return
	 */
	public void add(Case aCase) {
		// Step 2. Get ready: Clear working variables, get the case's description, copy the case, and set the current norm
		this.clear();
		this.getCaseDescription(aCase);
		this.setCaseInsert(aCase);
		this.setCurrNorm(this.getRoot());
		this.getCurrNorm().incrementNumCasesBy(1);

		// Step 3: Start the adding process
		this.processCase();
	}
	
	/**
	 * Llena el vector solucion con los casos que podrian resolver el problema.
	 * @see "Método betterMatch del protocolo matching en SUKIA SmallTalk"
	 */
	public void betterMatch() {
		String str;
		
		this.getRoute().clear();
		this.setSolution(null);
		this.setCurrNorm(this.getRoot());
		
		this.readProblemCase();
		
		this.nextBestMatch();

		// Actualiza la solucion del caso problema
		this.getProblemCase().setSolutionCases(this.getSolution());
		
		str = "";

		for( int i = 1; i <= this.getProblemCase().getSolutionCases().size(); i++) {
			str = str +  " " + this.getProblemCase().getSolutionCases().get(i-1).getSolution();
			
		}

		if (str == "" ) 
			JOptionPane.showMessageDialog(null, "No hay solucion", "", JOptionPane.INFORMATION_MESSAGE);
		else JOptionPane.showMessageDialog(null, "Posible solucion: ", str, JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Calcula los pesos correspondientes a cada (atributo, valor) del caso problema en la norma actual.
	 * @see "Método computeWeights del protocolo matching en SUKIA SmallTalk"
	 */
	public void computeWeights() {
		Descriptor<Object> descriptorAct;
		Index indiceAct;
		Node sucesor;
		
		// Para cada par (atributo, valor) del caso problema
		for( int i = 1; i <= this.getProblemCase().getDescription().size(); i++) {
			descriptorAct = this.getProblemCase().getDescription().get(i-1);
			// ( ( ( (problemCase weightAt: caract )  = -1) not)  & ( (route containsTheObject: ( descriptorAct attribute ) ) not ) )
			if (!(this.getRoute().contains(descriptorAct.getAttribute()))) { 
				// Indice correspondiente a descriptor del caso problema. 
      		 	indiceAct =  this.getCurrNorm().getIndex(descriptorAct.getAttribute(), descriptorAct.getValue());
				// Si el indice para el atributo del caso problema no existe
	  			if (indiceAct == null) {
	  				this.getProblemCase().weightsAt(i-1, -1); 
	  			} else {
					// OJO: HAY QUE ARREGLAR ESTA PARTE
					sucesor = indiceAct.getIndexValuesSuccessors(descriptorAct.getValue()).get(0);
					if (sucesor == null) {
						this.getProblemCase().weightsAt(i-1, -1);
					} else {
						if ((sucesor instanceof Case) || (sucesor instanceof SAVCase) ) {
							this.getProblemCase().weightsAt(i-1, 1);
						} else {
							this.getProblemCase().weightsAt(i-1, ((Norm)sucesor).getNumCases());
						}
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
	//Pendiente de traducir (ojo)
	public void gotDeadEnd(Norm aNorm) {
		List<Index> indexes;
		List<IndexValue<Object>> sucesoresIndice;
		
		indexes = aNorm.successorIndexes();
		
		for( int i = 1; i <= indexes.size(); i++) {
			// Si no existe el atributo en el caso problema, es una posible ruta a visitar.
			if (this.getProblemCase().valueOf( indexes.get(i-1).getLabel()) == null ) {
				sucesoresIndice = indexes.get(i-1).getSuccessors();
				for( int j = 1; j <= sucesoresIndice.size(); j++) {
					// Verificar el índice
					if ((sucesoresIndice.get(j-1).getSuccessors().get(0) instanceof Case) || 
							(sucesoresIndice.get(j-1).getSuccessors().get(0) instanceof SAVCase)) {
						this.getSolution().add((Case)sucesoresIndice.get(i-1).getSuccessors().get(0), this.getProblemCase());
					} else {
						this.gotDeadEnd((Norm)sucesoresIndice.get(i-1).getSuccessors().get(0));
					}
				}
			}
		}
	}
	
	/**
	 * Devuelve el indice del menor de los pesos del caso problema de forma que el atributo asociado no este en la ruta actual.
	 * Si no existe un mínimo válido  (es decir diferente de -1 y que no pertenece a la ruta) devuelve nil.
	 * @see "Método lowestWeight del protocolo matching en SUKIA SmallTalk"
	 * @return
	 */
	public int lowestWeight() {
		int posicion, min, valor;
		
		posicion = 0;
		min = this.getRoot().getNumCases();

		for( int i = 1; i <= this.getProblemCase().getDescription().size(); i++) {
			valor = this.getProblemCase().getWeightAt(i-1);
			if (!(this.getRoute().contains(this.getProblemCase().getDescription().get(i-1).getAttribute())) && 
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
	// Pendiente de traducir (ojo)
	public void nextBestMatch() {
		int posMasPredictivo;
		Descriptor<Object> descriptorMasPredictivo;
		Index indicePredictivo;
		Node sucesorIndice; // Ojo verificar
		
		this.computeWeights();
		posMasPredictivo =  this.lowestWeight();

		if (!(posMasPredictivo == -1 )) {
			descriptorMasPredictivo = this.getProblemCase().getDescription().get(posMasPredictivo-1);
			// Indice correspondiente al descriptor mas predictivo del caso problema. 
  		 	indicePredictivo = this.getCurrNorm().getIndex(descriptorMasPredictivo.getAttribute(), descriptorMasPredictivo.getValue());
  		 	
  		 	// OJO: HAY QUE ARREGLAR ESTA PARTE
			sucesorIndice = indicePredictivo.getIndexValuesSuccessors(descriptorMasPredictivo.getValue()).get(0);
			if (( sucesorIndice instanceof Case) || (sucesorIndice instanceof SAVCase)) {
				this.getRoute().push(descriptorMasPredictivo.getAttribute());

				// Como la red es redundantisima no es necesario utilizar mas de una vez un atributo
			} else {
				
			}
		}		
	}
	
	/**
	 * Permite leer y buscar la solucion para un nuevo caso problema.
	 * @see "Método readProblemCase del protocolo matching en SUKIA SmallTalk"
	 */
	public void readProblemCase() {
		List<Index> indicesRaiz;
		Descriptor<Object> desc;
		String valor;
		Object valorSimbolo;
		
		indicesRaiz = this.getRoot().successorIndexes();

		// Limpia el vector de deficion del csao problema
		this.problemCase.clear();

		desc = new Descriptor<Object>();

		for( int i = 1; i <= indicesRaiz.size(); i++) {
			valor =  JOptionPane.showInputDialog(indicesRaiz.get(i-1).getLabel());
			if (!(valor == null)) {
				if (indicesRaiz.get(i-1).getSuccessors().get(0).getValue() instanceof String)
					valorSimbolo =  valor;
				else valorSimbolo =  Double.parseDouble(valor);
				desc.add(indicesRaiz.get(i-1).getLabel(), valorSimbolo);
				this.getProblemCase().addToDescription(desc);
			}
		}
	}
	
	/**
	 * @see "Método doReturn del protocolo operation-private en SUKIA SmallTalk"
	 */
	private boolean doReturn() {
		this.decrementRecursionCount();
		
		if (this.getRecursionCount() == 0) {
			this.getCaseToCompare().restoreDescription();
			this.getCaseToCompare().flushDescriptionCopy();
			this.getCaseToCompare().flushStructureCopy();
		}
		
		this.getCaseCompare().pop();
		this.getRoute().pop();
		
		this.getCurrNorm().getPredecessorNorm();
		
		return true;
	}
	
	/**
	 * @see "Método moveToNormWith:incrementValue: del protocolo operation-private en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @param aNumber
	 */
	private boolean moveToNormWith(Descriptor<Object> aDescriptor,int aNumber) {
		Description<Descriptor<Object>> tmpDesc;
		boolean retVal;
		
		this.setCurrNorm(this.getCurrNorm().successorNormWith(aDescriptor));
		this.getCurrNorm().incrementNumCasesBy(aNumber);
		this.getRoute().push(this.getCurrNorm().getDescriptor().getAttribute());

		if (this.isCaseToInsertDescUsedUp()) {
			this.getCurrNorm().addSuccessor(this.getCaseInsert());
			this.getCaseInsert().addPredecessor(this.getCurrNorm(), this.getCurrNorm().getDescriptor().getValue());

			this.getRoute().pop();
			this.setCurrNorm(this.getCurrNorm().getPredecessorNorm());
		}
		
		// Save current state of ciDesc, since a totally new checking process (with ciDesc) will take place, considering all description elements
		tmpDesc = new Description<Descriptor<Object>>();
		this.moveDescElementsFrom(this.getCiDesc(), tmpDesc);
		this.getCaseDescription((this.getCaseInsert()));

		// Keep moving
		retVal = this.processCase();

		// Set ciDesc and everything else to the current state
		this.moveDescElementsFrom(tmpDesc, this.getCiDesc()); 
		this.removeMatchingElementsInTheRouteFrom(this.getCiDesc());
		this.getRoute().pop();
		this.setCurrNorm(this.getCurrNorm().getPredecessorNorm());

		return retVal;
	}
	
	/**
	 * @see "Método processCase del protocolo operation-private en SUKIA SmallTalk"
	 * @return
	 */
	private boolean processCase() {
		Descriptor<Object> d;
		Index ix;
		IndexValue<Object> ixv;
		List<Node> ixvSuccessors;
		Node succ;
		
		// Discard from the case's description all descriptors whose attribute is already in the route
		if (this.removeMatchingElementsInTheRouteFrom(this.getCiDesc()).isEmpty())
			return true;

		while (!(this.getCiDesc().isEmpty())) {
			// Remove the first descriptor in the case-to-insert description
			d = this.getCiDesc().remove(0);

			// Search for an index with the label's value equal to d's attribute"
			ix = this.getCurrNorm().getIndex(d.getAttribute());
			if (ix == null) {
				// An index with d's value was not found. Must create a new one
				ix = new Index();
				ix.setLabel(d.getAttribute());
				ix.setPredecessorNorm(this.getCurrNorm());
				this.getCurrNorm().addSuccessor(ix);
				ixv = new IndexValue<Object>();
				if (ixv.add(d.getValue(), this.getCaseInsert()) == false) return false;
				ix.addIndexValue(ixv);
				this.getCaseInsert().addPredecessor(ix, d.getValue()); 
			} else {
				// An index with d's attribute was found. Find an index value with d's value
				ixv = ix.getIndexValue(d.getValue());
				if (ixv == null) {
					// An index value was not found.  Create a new IndexValue and append the caseToInsert as successor
					ixv = new IndexValue<Object>();
					if (ixv.add(d.getValue(), this.getCaseInsert()) == false) return false;
					ix.addIndexValue(ixv);
					this.getCaseInsert().addPredecessor(ix, d.getValue());
				} else {
					// Get the list of successors of the current IndexValue
					ixvSuccessors =  ixv.getSuccessors();

					/* Each IndexValue MUST point to only one successor. If it doesn't, rais
					 an exception and get the hell out*/
					if (!(ixvSuccessors.size() == 1)) return false;

					// Get the ONLY successor from the successors' list
					succ = ixvSuccessors.get(0);

					// If successor is a NORM, set it as the new current norm and continue
					if (succ instanceof Norm) {
						if (this.moveToNormWith(d, 1) == false) return false; 
					} else {
						/* If the successor is NOT a CASE, raise an exception and get the hell out. Else,
						 process the case-to-insert and case-to-compare accordingly*/
						if (!(succ instanceof Case)) {
							return false;
						} else {
							if (this.getCiDesc().isEmpty()) {
								if (this.processCICCWithCIDescEmpty(d, ix, ixv, (Case)succ) == false) return false;
							} else {
								if (this.processCICCWithCIDescNotEmpty(d, ix, ixv, (Case)succ) == false) return false;
							}
						}
					}
				}	 
			}
		}

		return true;
	}
	
	/**
	 * @see "Método processCICCWithCIDescEmpty:index:indexValue:successor: del protocolo operation-private en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @param anIndex
	 * @param anIndexValue
	 * @param aSuccessor
	 */
	private boolean processCICCWithCIDescEmpty(Descriptor<Object> aDescriptor, Index anIndex, IndexValue<Object> anIndexValue, Case aSuccessor) {
		boolean CIInserted;
		boolean CCInserted;
		Norm norm;
		
		if (this.areDescriptionsEqualFor(this.getCaseInsert(), aSuccessor)) {
			return this.processCICCWithCIDescNotEmpty(aDescriptor, anIndex, anIndexValue, aSuccessor);
		}

		CIInserted = false;
		CCInserted = false;

		norm = new Norm();
		norm.setDescriptor(aDescriptor);
		norm.setPredecessorIndex(anIndex);
		norm.incrementNumCasesBy(2);
		
		anIndexValue.getSuccessors().remove(aSuccessor);
		this.getCaseCompare().push(aSuccessor);
		this.getCaseToCompare().prepareDescriptionWith(this.getCaseInsert().currentStructure());
		this.getCaseToCompare().removePredecessor(anIndex, aDescriptor.getValue());

		anIndexValue .addSuccessor(norm);

		this.getRoute().push(norm.getDescriptor().getAttribute());
		this.setCurrNorm(this.getCurrNorm().successorNormWith(aDescriptor));

		if (this.isCaseToInsertDescUsedUp()) {
			this.getCurrNorm().addSuccessor(this.getCaseInsert());
			this.getCaseInsert().addPredecessor(this.getCurrNorm(), this.getCurrNorm().getDescriptor().getValue());
			CIInserted = true;
		}
		
		if (this.isCaseToCompareDescUsedUp()) {
			this.getCurrNorm().addSuccessor(this.getCaseToCompare());
			this.getCaseToCompare().addPredecessor(this.getCurrNorm(), this.getCurrNorm().getDescriptor().getValue());
			CCInserted = true;
		}
		
		this.setRecursionCount(0);

		if (CIInserted == false || CCInserted == false) {
			this.whenCCExists();
		} else {
			this.getCaseToCompare().restoreDescription();
			this.getCaseToCompare().flushDescriptionCopy();
			this.getCaseToCompare().flushStructureCopy();
			
			this.getCaseCompare().pop();
			this.getRoute().pop();
			this.setCurrNorm(this.getCurrNorm().getPredecessorNorm());
		}
		
		return true;
	}
	
	/**
	 * @see "Método processCICCWithCIDescNotEmpty:index:indexValue:successor: del protocolo operation-private en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @param anIndex
	 * @param anIndexValue
	 * @param aSuccessor
	 */
	private boolean processCICCWithCIDescNotEmpty(Descriptor<Object> aDescriptor, Index anIndex, IndexValue<Object> anIndexValue, Case aSuccessor) {
		Norm norm;
		
		norm = new Norm();
		norm.setDescriptor(aDescriptor);
		norm.setPredecessorIndex(anIndex);
		/* Both caseToInsert and caseToCompare will live together under this new norm. Thus
		increment the number of (underlying) cases for it by 2*/
		norm.incrementNumCasesBy(2);
	
		// Remove the case-to-compare as successor from the IndexValue
		anIndexValue.getSuccessors().remove(aSuccessor);
		this.getCaseCompare().push(aSuccessor);
		// Remove old index reference in the case-to-compare's index list
		this.getCaseToCompare().prepareDescriptionWith(this.getCaseInsert().currentStructure());
		this.getCaseToCompare().removePredecessor(anIndex, aDescriptor.getValue());

		anIndexValue .addSuccessor(norm);

		this.getRoute().push(norm.getDescriptor().getAttribute());
		norm.setPredecessorIndex(anIndex);
		this.getRoute().push(anIndex.getLabel());
		this.setCurrNorm(this.getCurrNorm().successorNormWith(aDescriptor));
		
		if (this.isCaseToCompareDescUsedUp()) {
			this.getCurrNorm().addSuccessor(this.getCaseToCompare());
			this.getCaseToCompare().addPredecessor(this.getCurrNorm(), this.getCurrNorm().getDescriptor().getValue());
		}
		
		this.setRecursionCount(0);
		return this.whenCCExists();
	}
	
	/**
	 * @see "Método removeRepeatingElementsInRouteFrom: del protocolo operation-private en SUKIA SmallTalk"
	 * @param aTable
	 */
	private void removeRepeatingElementsInRouteFrom(ComparingTable aTable) {
		ComparingTableTuple<Object> t;
		int i;
		
		i = 1;
		while (i <= aTable.size()) {
			t = aTable.get(i-1);
			if (!((this.getCurrNorm().getIndex(t.getAttribute(), t.getACIValue()) == null) ||
					(this.getCurrNorm().getIndex(t.getAttribute(), t.getACCValue()) == null)))
				aTable.remove(i-1);
			else i = i + 1;
		}
	}

	/**
	 * Recursive method that makes description comparisons between a case to be inserted and a case found during net traversal.
	 * Upon each call, a ComparingTable is created and it will contain tuples with every Descriptor in both case descriptions.  Next,
	 * the comparison task begins by removing, one by one, each of the ComparingTableTuple's.  If the values for the given tuple
	 * attribute are different, new indices are created and the cases are linked to them.  If the values for the given tuple are the same,
	 * a new Norm is created and the method is invoked again to start a new Descriptor comparison.  The method stops when all the
	 * tuples have been removed from the ComparingTable.
	 * @see "Método whenCCExists del protocolo operation-private en SUKIA SmallTalk"
	 */
	private boolean whenCCExists() {
		ComparingTable table;
		ComparingTableTuple<Object> tuple;
		Norm norm;
		Descriptor<Object> d;
		Index ix;
		IndexValue<Object> ixv;
		
		this.incrementRecursionCount();

		// Create a table with all the descriptors for both the case to insert and the case to compare
		table = new ComparingTable();
		table.fill(this.getCaseInsert(), this.getCaseToCompare());

		// Discard from the table all tuples whose attribute is already in the route
		this.removeMatchingElementsInTheRouteFrom(table);

		/* If the resulting table is empty, then both (CC and CI) descriptions are already on the route. This means that,
		 at this point, both descriptions are equal and used up along the net traversing. Thus, no more traversing is
		 possible.*/
		if (table.isEmpty()) {
			// Place the case-to-insert as successor of the current norm
			this.getCurrNorm().addSuccessor(this.getCaseInsert());
			this.getCaseInsert().addPredecessor(this.getCurrNorm(), this.getCurrNorm().getDescriptor().getValue());

			// Place the case-to-compare as successor of the current norm
			this.getCurrNorm().addSuccessor(this.getCaseToCompare());
			this.getCaseToCompare().addPredecessor(this.getCurrNorm(), this.getCurrNorm().getDescriptor().getValue());

			return doReturn();
		}
		
		/* At this point, the table CAN NOT be empty, and one of the following three situations is possible:
		 1. CI description is already used up, and CC description has at least one tuple lined up in the table;
		 2. CI description has at least one tuple lined up in the table, and CC description is used up;
		 3. There is at least one tuple in the table with CI and CC values.*/

		// Situation 1: If CI description is used up, place CI as successor of the current norm
		if (this.isCaseToInsertDescUsedUp()) {
			this.getCurrNorm().addSuccessor(this.getCaseInsert());
			this.getCaseInsert().addPredecessor(this.getCurrNorm(), this.getCurrNorm().getDescriptor().getValue());
		}
		
		// Situation 2: If CC description is empty, place CC as successor of the current norm
		if (this.isCaseToCompareDescUsedUp()) {
			this.getCurrNorm().addSuccessor(this.getCaseToCompare());
			this.getCaseToCompare().addPredecessor(this.getCurrNorm(), this.getCurrNorm().getDescriptor().getValue());
		}
		
		// Situation 3: Compare each table tuple
		while (!((tuple = table.extractTuple()) == null)) {
			// Create a new index and label it with the tuple's attribute
			ix = new Index();
			ix.setLabel(tuple.getAttribute());

			// Add the index to the current norm's index list
			this.getCurrNorm().addSuccessor(ix);

			// Set the index predecessor (current) norm
			ix.setPredecessorNorm(this.getCurrNorm()); 

			/* Check if the values for the tuple are different
			"Values are diferent. Create a new index using the tuple's attribute*/
			if (!(tuple.getACIValue() == tuple.getACCValue())) {
				// If the case-to-insert value is non-nil, attach it to the index
				if (!(tuple.getACIValue() == null)) {
					ixv = new IndexValue<Object>(tuple.getACIValue(), this.getCaseInsert());
					ix.addIndexValue(ixv);

					// Add the index-and-value to the case's index list
					this.getCaseInsert().addPredecessor(ix,tuple.getACIValue()); 
				}
				
				// If the case-to-compare value is non-nil, attach it to the index"
				if (!(tuple.getACCValue() == null)) {
					ixv = new IndexValue<Object>(tuple.getACCValue(), this.getCaseToCompare());
					ix.addIndexValue(ixv);

					// Add the index-and-value to the case's index list"
					this.getCaseToCompare().addPredecessor(ix,tuple.getACCValue());
				}
			} else {
				// Create an empty norm and fill its values
				norm = new Norm();
				d = new Descriptor<Object>();
				d.add(tuple.getAttribute(), tuple.getACIValue());
				norm.setDescriptor(d);
				norm.incrementNumCasesBy(2);

				// Link the recently created index to this new norm
				norm.setPredecessorIndex(ix);
				ixv = new IndexValue<Object>();
				ixv.add(tuple.getACIValue(), norm);
				ix.addIndexValue(ixv);

				// Add the tuple's attribute to the route
				this.getRoute().push(tuple.getAttribute());

				// Push a new case to compare
				this.getCaseCompare().push(this.getCaseToCompare());

				// Move to the new norm
				this.setCurrNorm(this.getCurrNorm().successorNormWith(d));

				// Repeat the procedure for the new case to compare
				this.whenCCExists();

				this.removeRepeatingElementsInRouteFrom(table);

			}
		}

		return this.doReturn();
	}
	
	/**
	 * @see "Método isCaseToCompareDescUsedUp del protocolo testing en SUKIA SmallTalk"
	 * @return
	 */
	public boolean isCaseToCompareDescUsedUp() {
		List<Descriptor<Object>> ccCopy;
		String a;
		int i;
		
		ccCopy = this.getCaseToCompare().getDescription().subList(0, this.getCaseToCompare().getDescription().size());
		i = 1;
		while (i <= ccCopy.size()) {
			a = ccCopy.get(i-1).getAttribute();
			if (this.getRoute().contains(a)) {
				ccCopy.remove(i-1);
				i = 1;
			} else {
				 i = i + 1;
			}
		}
		
		if (ccCopy.isEmpty()) return false;
		return true;
	}
	
	/**
	 * @see "Método isCaseToInsertDescUsedUp del protocolo testing en SUKIA SmallTalk"
	 * @return
	 */
	public boolean isCaseToInsertDescUsedUp() {
		List<Descriptor<Object>> ccCopy;
		String a;
		int i;
		
		ccCopy = this.getCaseInsert().getDescription().subList(0, this.getCaseInsert().getDescription().size());
		i = 1;
		while (i <= ccCopy.size()) {
			a = ccCopy.get(i-1).getAttribute();
			if (this.getRoute().contains(a)) {
				ccCopy.remove(i-1);
				i = 1;
			} else {
				 i = i + 1;
			}
		}
		
		if (ccCopy.isEmpty()) return false;
		return true;
	}
	
	/**
	 * @see "Método flush del protocolo resetting en SUKIA SmallTalk"
	 */
	void clear() {
		this.getRoute().clear();
		this.setCaseInsert(null);
		this.getCaseCompare().clear();
		this.getCiDesc().clear();
		this.setCurrNorm(null);
		this.getProblemCase().clear();
		this.setSolution(null);
		this.setRecursionCount(0);
	}
	
	/**
	 * @see "Método getCaseDescription del protocolo reading en SUKIA SmallTalk"
	 * @param aCase
	 */
	public void getCaseDescription(Case aCase) {
		Description<Descriptor<Object>> desc;
		
		desc = aCase.getDescription();
		
		for( int i = 1; i <= desc.size(); i++) {
			this.getCiDesc().add(desc.get(i-1));
		}
	}
	
	/**
	 * @see "Método moveDescElementsFromoneDescList:to: del protocolo reading en SUKIA SmallTalk"
	 * @param oneDescList
	 * @param anotherDescList
	 * @return
	 */
	public boolean moveDescElementsFrom(Description<Descriptor<Object>> oneDescList, Description<Descriptor<Object>> anotherDescList) {
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
	public ComparingTable removeMatchingElementsInTheRouteFrom(ComparingTable aDescriptionList) {
		int i;

		i = 1;
		while (i <= aDescriptionList.size()) {
			if (this.getRoute().contains(aDescriptionList.get(i-1).getAttribute()))
				aDescriptionList.remove(i-1);
			else i = i + 1;
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
	public Description<Descriptor<Object>> removeMatchingElementsInTheRouteFrom(Description<Descriptor<Object>> aDescriptionList) {
		int i;

		i = 1;
		while (i <= aDescriptionList.size()) {
			if (this.getRoute().contains(aDescriptionList.get(i-1).getAttribute()))
				aDescriptionList.remove(i-1);
			else i = i + 1;
		}

		return aDescriptionList;
	}
	
	/**
	 * @see "Método areDescriptionsEqualFor:and: del protocolo testing en SUKIA SmallTalk"
	 * @param aCase1
	 * @param aCase2
	 * @return
	 */
	public boolean areDescriptionsEqualFor(Case aCase1, Case aCase2) {
		Description<Descriptor<Object>> desc2;
		Descriptor<Object> d;
		int j;
		
		if (!((aCase1 instanceof Case) && (aCase2 instanceof Case))) return false;

		// If the cases' descriptions are of different size, then they are not equal
		if (!(aCase1.getDescription().size() == aCase2.getDescription().size())) return false;

		// Copy aCase2 description into desc2
		desc2 = new Description<Descriptor<Object>>();
		for( int i = 1; i <= aCase2.getDescription().size(); i++) {
			desc2.add(aCase2.getDescription().get(i-1));
		}
		
		// For every element in the description of aCase1: if its attribute-value pair matches one in desc2, then delete that element from desc2
		for( int i = 1; i <= aCase1.getDescription().size(); i++) {
			d = aCase1.getDescription().get(i-1);
			j = 1;
			while (j <= desc2.size()) {
				if ((d.getAttribute() == desc2.get(j-1).getAttribute()) && (d.getValue() == desc2.get(j-1).getValue())) {
					desc2.remove(j-1);
					j = desc2.size() + 1;
				} else j = j + 1;
			}
		}

		// If the resulting desc2 is empty, then every element in aCase1 description matched with desc2. Thus, both descriptions are equal
		if (desc2.isEmpty()) return true;

		// Descriptions are different by at least one element
		return false;
	}
}
