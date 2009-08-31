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
import ontology.common.CharacterDescriptor;
import ontology.common.Descriptor;
import ontology.common.SSCharacterDescriptor;
import ontology.common.SSHeuristicDescriptor;
import ontology.common.SVCharacterDescriptor;
import ontology.common.SVHeuristicDescriptor;
import ontology.values.SingleValue;

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
	private Case caseToInsert;
	/**
	 *  A Stack that contains instances of Case's found during net traversal.
	 */
	private Stack<Case> casesToCompare;
	/**
	 * 	Case-To-Insert description, used to traverse the net, and create new Norms and indices.
	 */
	private List<Descriptor> ciDescription;
	/**
	 * Pointer to the current Norm during net traversal.
	 */
	private Norm currentNorm;
	/**
	 * 	Instance of a Problem Case to be resolved.
	 */
	private ProblemSolutions problemSolutions;
	
	/**
	 * Constructor por defecto
	 */
	public RDNet(RootNorm root) {
		setRoot(root);
		setRoute(new Stack<String>());
		setCaseToInsert(null);
		setCasesToCompare(new Stack<Case>());
		setCiDescription(new ArrayList<Descriptor>());
		setCurrentNorm(null);
		setProblemSolutions(new ProblemSolutions());
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
		this.caseToInsert = caseToInsert;
	}

	/**
	 * M&eacute;todo accesor de lectura
	 * @see "M&eacute;todo caseToInsert del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Case getCaseToInsert() {
		return caseToInsert;
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
	public void setCiDescription(List<Descriptor> ciDesc) {
		this.ciDescription = ciDesc;
	}

	/**
	 * M&eacute;todo accesor de lectura
	 * @see "M&eacute;todo caseToInsertDesc del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public List<Descriptor> getCiDescription() {
		return ciDescription;
	}
	
	/**
	 * @see "M&eacute;todo getCaseDescription del protocolo reading en SUKIA SmallTalk"
	 * @param aCase
	 */
	public void setCiDescription(Case aCase) {
		List<Descriptor> desc;
		
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
	 * Agrega un nuevo caso a la red
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
	 * Inserta el caso en los lugares apropiados de la red de manera redundante
	 * @see "M&eacute;todo processCase del protocolo operation-private en SUKIA SmallTalk"
	 * @return
	 */
	private void processCase() {
		Descriptor d;
		Index ix;
		SheetCase sc;
		Node n;
		
		// Discard from the case's description all descriptors whose attribute is already in the route
		if (this.removeMatchingElementsInTheRoute(this.getCiDescription()).isEmpty())
			return;

		// Si la descripción está vacía ya no hay nuevas rutas a considerar
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
					// Esto significa que el descriptor del caso es único y por lo tanto no describe una
					// norma que agrupa varios casos
					sc = new SheetCase(d);
					sc.setCase(this.getCaseToInsert());
					ix.addSuccessor(sc);
				} else {
					if (n instanceof Norm) {
						// Moverse a la norma n y continuar el proceso desde ahí con los demás descriptores
						// si los hay o insertar el nodo en la norma n
						this.moveToNorm(d, 1);
					} else {
						// Si n es un caso, agrupar el caso a insertar y n en la última norma que representa
						// la ruta que contiene los descriptores comunes a ambos e insertar cada caso en un
						// indice para aquellos descriptores únicos considerando todas las rutas posibles
						if (this.getCiDescription().isEmpty())
							// En este nivel de la red ya no hay nuevas rutas a considerar
							this.processCICCWithCIDescEmpty(d, ix, (SheetCase)n);
						else
							this.processCICCWithCIDescNotEmpty(d, ix, (SheetCase)n);
					}
				}	 
			}
		}
	}
	
	/**
	 * Se mueve a la siguiente norma sucesor encontrada y comienza el proceso a partir de ah&iacute;
	 * @see "M&eacute;todo moveToNormWith:incrementValue: del protocolo operation-private en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @param aNumber
	 */
	private void moveToNorm(Descriptor aDescriptor, int aNumber) {
		List<Descriptor> tmpDesc;
		
		this.setCurrentNorm(this.getCurrentNorm().getNearestSuccessorNorm(aDescriptor));
		this.getCurrentNorm().incrementNumCasesBy(aNumber);
		this.getRoute().push(this.getCurrentNorm().getDescriptor().getAttribute());

		// Si se ha considerado todos los descriptores, la norma actual generaliza el caso 
		if (this.isCaseDescriptionUsedUp(this.getCaseToInsert())) {
			this.addSuccessorCase(this.getCaseToInsert(), this.getCurrentNorm(), aDescriptor);

			// Retornar un nivel atras para considerar nuevas rutas con nuevas combinaciones de descriptores
			// si las hay
			this.getRoute().pop();
			this.setCurrentNorm(this.getCurrentNorm().getNearestPredecessorNorm());
			
			return;
		}
		
		// Save current state of ciDescription, since a totally new checking process (with ciDescription)
		// will take place, considering all description elements
		tmpDesc = new ArrayList<Descriptor>();
		this.moveDescElements(this.getCiDescription(), tmpDesc);
		this.setCiDescription(this.getCaseToInsert());

		// Procesar los siguientes descriptores no considerados de esta ruta hasta este punto, 
		// del caso a insertar incluyendo aquellos ya considerados en otras rutas
		this.processCase();

		// Considerar nuevas rutas con nuevas combinaciones de descriptores si las hay
		// Set ciDescription and everything else to the current state
		this.moveDescElements(tmpDesc, this.getCiDescription()); 
		this.removeMatchingElementsInTheRoute(this.getCiDescription());
		this.getRoute().pop();
		this.setCurrentNorm(this.getCurrentNorm().getNearestPredecessorNorm());
	}
	
	/**
	 * @see "M&eacute;todo processCICCWithCIDescEmpty:index:indexValue:successor: del protocolo operation-private en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @param anIndex
	 * @param aNodeList
	 * @param aSuccessor
	 */
	private void processCICCWithCIDescEmpty(Descriptor aDescriptor, Index anIndex, SheetCase sc) {
		boolean CIInserted;
		boolean CCInserted;
		Norm norm;
		
		// Si las descripciones de los casos son iguales, retomar las rutas ya consideradas para agregar
		// el caso encontrado
		if (this.getCaseToInsert().getDescription(this.getRoot().getStructure())
			.equals(sc.getCase().getDescription(this.getRoot().getStructure()))) {
			this.processCICCWithCIDescNotEmpty(aDescriptor, anIndex, sc);
			return;
		}

		CIInserted = false;
		CCInserted = false;

		norm = new Norm(aDescriptor);
		norm.setPredecessor(anIndex);
		norm.incrementNumCasesBy(2);
		
		this.getCasesToCompare().push(sc.getCase());
		
		sc.removePredecessor(anIndex);
		
		this.getRoute().push(norm.getDescriptor().getAttribute());
		this.setCurrentNorm(this.getCurrentNorm().getNearestSuccessorNorm(aDescriptor));

		// No hay nuevas rutas a considerar en este nivel, pero es posible que haya más niveles
		// fijando un descriptor no utilizado
		if (this.isCaseDescriptionUsedUp(this.getCaseToInsert())) {
			this.addSuccessorCase(this.getCaseToInsert(), this.getCurrentNorm(), aDescriptor);
			CIInserted = true;
		}
		
		// Insertar el caso encontrado en la norma actual si no tiene descriptores a considerar
		if (this.isCaseDescriptionUsedUp(this.getCaseToCompare())) {
			this.getCurrentNorm().addSuccessor(sc);
			CCInserted = true;
		}

		// Si existe al menos un descriptor no utilizado en el caso encontrado o en el caso a insertar
		// procesar esos descriptores en el siguiente nivel, de lo contrario volver al anterior 
		if (CIInserted == false || CCInserted == false) {
			this.whenCCExists();
		} else {
			this.getCasesToCompare().pop();
			this.getRoute().pop();
			this.setCurrentNorm(this.getCurrentNorm().getNearestPredecessorNorm());
		}
	}
	
	/**
	 * @see "M&eacute;todo processCICCWithCIDescNotEmpty:index:indexValue:successor: del protocolo operation-private en SUKIA SmallTalk"
	 * @param aDescriptor
	 * @param anIndex
	 * @param aNodeList
	 * @param aSuccessor
	 */
	private void processCICCWithCIDescNotEmpty(Descriptor aDescriptor, Index anIndex, SheetCase sc) {
		Norm norm;
		
		norm = new Norm(aDescriptor);
		norm.setPredecessor(anIndex);
		/* Both caseToInsert and caseToCompare will live together under this new norm. Thus
		increment the number of (underlying) cases for it by 2*/
		norm.incrementNumCasesBy(2);
		
		// Remove old index reference in the case-to-compare
		sc.removePredecessor(anIndex);
	
		this.getCasesToCompare().push(sc.getCase());

		this.getRoute().push(anIndex.getLabel());
		this.setCurrentNorm(this.getCurrentNorm().getNearestSuccessorNorm(aDescriptor));
		
		// La descripción del caso a insertar no ha sido utilizada completamente, ya que ni
		// siquiera se han agotado las rutas desde este nivel, por lo que no es necesario verificar este
		// hecho
		if (this.isCaseDescriptionUsedUp(this.getCaseToCompare()))
			this.getCurrentNorm().addSuccessor(sc);
		
		this.whenCCExists();
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
	private void whenCCExists() {
		ComparingTable table;
		ComparingTableTuple<Object> tuple;
		Descriptor d = null;
		Norm norm;
		Index ix;
		boolean arevaluesequal;

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
			// Es posible que ya se haya agregado, si es así la red no agrega duplicados
			this.addSuccessorCase(this.getCaseToInsert(), this.getCurrentNorm(),
					this.getCurrentNorm().getDescriptor());

			// Place the case-to-compare as successor of the current norm
			this.addSuccessorCase(this.getCaseToCompare(), this.getCurrentNorm(),
					this.getCurrentNorm().getDescriptor());

			doReturn();
			return;
			
		}
		
		/* At this point, the table CAN NOT be empty, and one of the following three situations is possible:
		 1. CI description is already used up, and CC description has at least one tuple lined up in the table;
		 2. CI description has at least one tuple lined up in the table, and CC description is used up;
		 3. There is at least one tuple in the table with CI and CC values.
		 */
		// Situation 1: If CI description is used up, place CI as successor of the current norm
		// Es posible que ya se haya agregado, si es así la red no agrega duplicados
		if (this.isCaseDescriptionUsedUp(this.getCaseToInsert()))
			this.addSuccessorCase(this.getCaseToInsert(), this.getCurrentNorm(),
					this.getCurrentNorm().getDescriptor());
		
		// Situation 2: If CC description is empty, place CC as successor of the current norm
		// Es posible que ya se haya agregado, si es así la red no agrega duplicados
		if (this.isCaseDescriptionUsedUp(this.getCaseToCompare()))
			this.addSuccessorCase(this.getCaseToCompare(), this.getCurrentNorm(),
					this.getCurrentNorm().getDescriptor());
		
		// Situation 3: Compare each table tuple
		while (!((tuple = table.extractTuple()) == null)) {
			// Create a new index and label it with the tuple's attribute
			ix = new Index(tuple.getAttribute());

			// Add the index to the current norm's index list
			this.getCurrentNorm().addSuccessor(ix);

			/* Check if the values for the tuple are different. 
			 * If Values are diferent, create a new index using the tuple's attribute
			 */
			if (tuple.getACIValue() != null)
				arevaluesequal = tuple.getACIValue().equals(tuple.getACCValue());
			else arevaluesequal = tuple.getACCValue().equals(tuple.getACIValue());
				
			if (!(arevaluesequal)) {
				// If the case-to-insert value is non-null, attach it to the index
				if (!(tuple.getACIValue() == null)) {
					if (this.getRoot().getDescriptor() instanceof CharacterDescriptor) {
						if (tuple.getACIValue() instanceof SingleValue)
							d = new SVCharacterDescriptor();
						else d = new SSCharacterDescriptor();
					} else {
						if (tuple.getACIValue() instanceof SingleValue)
							d = new SVHeuristicDescriptor();
						else d = new SSHeuristicDescriptor();
					}
					
					d.set(this.getRoot().getStructure(), tuple.getAttribute(), tuple.getACIValue());
					this.addSuccessorCase(this.getCaseToInsert(), ix, d);
				}
				
				// If the case-to-compare value is non-nil, attach it to the index"
				if (!(tuple.getACCValue() == null)) {
					if (this.getRoot().getDescriptor() instanceof CharacterDescriptor) {
						if (tuple.getACCValue() instanceof SingleValue)
							d = new SVCharacterDescriptor();
						else d = new SSCharacterDescriptor();
					} else {
						if (tuple.getACCValue() instanceof SingleValue)
							d = new SVHeuristicDescriptor();
						else d = new SSHeuristicDescriptor();
					}
					
					d.set(this.getRoot().getStructure(), tuple.getAttribute(), tuple.getACCValue());
					this.addSuccessorCase(this.getCaseToCompare(), ix, d);
				}
			} else {
				// Create an empty norm and fill its values
				if (this.getRoot().getDescriptor() instanceof CharacterDescriptor) {
					if (tuple.getACCValue() instanceof SingleValue)
						d = new SVCharacterDescriptor();
					else d = new SSCharacterDescriptor();
				} else {
					if (tuple.getACCValue() instanceof SingleValue)
						d = new SVHeuristicDescriptor();
					else d = new SSHeuristicDescriptor();
				}
				
				d.set(this.getRoot().getStructure(), tuple.getAttribute(), tuple.getACCValue());
				norm = new Norm(d);
				norm.incrementNumCasesBy(2);

				// Link the recently created index to this new norm
				norm.setPredecessor(ix);				

				// Add the tuple's attribute to the route
				this.getRoute().push(tuple.getAttribute());

				// Push a new case to compare (Ojo se pone el mismo caso)
				this.getCasesToCompare().push(this.getCaseToCompare());

				// Move to the new norm
				this.setCurrentNorm(this.getCurrentNorm().getNearestSuccessorNorm(d));

				// Repeat the procedure for the new case to compare
				this.whenCCExists();
			}
		}

		this.doReturn();
	}
	
	/**
	 * Agrega un caso nodo sucesosr de predecessor
	 * @param aCase
	 * @param predecessor
	 * @param d
	 */
	private void addSuccessorCase(Case aCase, Node predecessor, Descriptor d) {
		SheetCase sc;
		
		// Place the case as successor of predecessor
		sc = new SheetCase(d);
		sc.setCase(aCase);
		
		predecessor.addSuccessor(sc);
	}
	
	/**
	 * @see "M&eacute;todo doReturn del protocolo operation-private en SUKIA SmallTalk"
	 */
	private void doReturn() {
		this.getCasesToCompare().pop();
		this.getRoute().pop();
		this.setCurrentNorm(this.getCurrentNorm().getNearestPredecessorNorm());
	}
	
	/**
	 * @see "M&eacute;todo isCaseToInsertDescUsedUp del protocolo testing en SUKIA SmallTalk"
	 * @return
	 */
	protected boolean isCaseDescriptionUsedUp(Case c) {
		List<Descriptor> description, descriptionCopy;
		
		description = c.getDescription(this.getRoot().getStructure());
		descriptionCopy = new ArrayList<Descriptor>(description);
		for (Descriptor d:description) {
			if (this.getRoute().contains(d.getAttribute())) {
				descriptionCopy.remove(d);
			}
		}
		
		if (!(descriptionCopy.isEmpty())) return false;
		
		return true;
	}
	
	/**
	 * @see "M&eacute;todo flush del protocolo resetting en SUKIA SmallTalk"
	 */
	private void setToDefault() {
		this.getRoute().clear();
		this.setCaseToInsert(null);
		this.getCasesToCompare().clear();
		this.getCiDescription().clear();
		this.setCurrentNorm(null);
		this.getProblemSolutions().setToDefault();
	}
	
	/**
	 * @see "M&eacute;todo moveDescElementsFromoneDescList:to: del protocolo reading en SUKIA SmallTalk"
	 * @param oneDescList
	 * @param anotherDescList
	 * @return
	 */
	protected boolean moveDescElements(List<Descriptor> oneDescList, List<Descriptor> anotherDescList) {
		if (oneDescList.isEmpty() || !anotherDescList.isEmpty()) return false;
		
		while (!oneDescList.isEmpty()) {
			anotherDescList.add(oneDescList.remove(0));
		}
		
		return true;
	}
	
	/**
	 * Removes elements from a (case's) description, whose attribute is already included in the Route.
	 * IMPORTANT NOTE: The elements from aDescriptionList MUST be able to provide the descriptor's
	 * attribute.
	 * @see "M&eacute;todo removeMatchingElementsInTheRouteFrom: del protocolo removing en SUKIA SmallTalk"
	 * @param aDescriptionList
	 */
	protected ComparingTable removeMatchingElementsInTheRoute(ComparingTable aDescriptionList) {
		List<ComparingTableTuple<Object>> aDescriptionListCopy;
		
		aDescriptionListCopy = new ArrayList<ComparingTableTuple<Object>>(aDescriptionList);

		for (ComparingTableTuple<Object> ctt: aDescriptionListCopy) {
			if (this.getRoute().contains(ctt.getAttribute()))
				aDescriptionList.remove(ctt);
		}

		return aDescriptionList;
	}
	
	/**
	 * Removes elements from a (case's) description, whose attribute is already included in the Route.
	 * IMPORTANT NOTE: The elements from aDescriptionList MUST be able to provide the descriptor's
	 * attribute.
	 * @see "M&eacute;todo removeMatchingElementsInTheRouteFrom: del protocolo removing en SUKIA SmallTalk"
	 * @param aDescriptionList
	 */
	protected List<Descriptor> removeMatchingElementsInTheRoute(List<Descriptor> aDescriptionList) {
		List<Descriptor> aDescriptionListCopy;
		
		aDescriptionListCopy = new ArrayList<Descriptor>(aDescriptionList);
		
		for (Descriptor d: aDescriptionListCopy) {
			if (this.getRoute().contains(d.getAttribute()))
				aDescriptionList.remove(d);
		}

		return aDescriptionList;
	}
	
	/**
	 * Llena el vector solucion con los casos que podrian resolver el problema.
	 * @see "M&eacute;todo betterMatch del protocolo matching en SUKIA SmallTalk"
	 */
	// Ojo Casos de prueba pendientes
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
	 * @see "M&eacute;todo computeWeights del protocolo matching en SUKIA SmallTalk"
	 */
	// Ojo Casos de prueba pendientes
	private void computeWeights() {
		List<Descriptor> description;
		Index actIndex;
		Node successor;
		
		description = this.getProblemSolutions().getProblemCase()
			.getDescription(this.getRoot().getStructure()); // Ojo hay que verificar está parte
		
		// Para cada descriptor del caso problema
		for(Descriptor d: description) {
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
	 * @see "M&eacute;todo gotDeadEnd del protocolo matching en SUKIA SmallTalk"
	 * @param aNorm
	 */
	// Ojo Casos de prueba pendientes
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
	 * @see "M&eacute;todo lowestWeight del protocolo matching en SUKIA SmallTalk"
	 * @return
	 */
	// Ojo Casos de prueba pendientes
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
	 * @see "M&eacute;todo nextBestMatch del protocolo matching en SUKIA SmallTalk"
	 */
	// Ojo Casos de prueba pendientes
	private void nextBestMatch() {
		int mostPredictivePos;
		Descriptor mostPredictiveDescriptor;
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
	 * @see "M&eacute;todo readProblemCase del protocolo matching en SUKIA SmallTalk"
	 */
	// Ojo Casos de prueba pendientes
	private void readProblemCase() {
		List<Index> rootIndexes;
		Descriptor desc = null;
		String value;
		Object simbolValue;
		
		rootIndexes = this.getRoot().successorIndexes();

		// Limpia el vector de deficion del caso problema
		this.problemSolutions.setToDefault();

		// Ojo verificar esta parte
		if (this.getRoot().getDescriptor() instanceof CharacterDescriptor) {
			if (this.getRoot().getDescriptor() instanceof SVCharacterDescriptor)
				desc = new SVCharacterDescriptor();
			else if (this.getRoot().getDescriptor() instanceof SSCharacterDescriptor)
				desc = new SSCharacterDescriptor();
		} else {
			if (this.getRoot().getDescriptor() instanceof SVHeuristicDescriptor)
				desc = new SVHeuristicDescriptor();
			else if (this.getRoot().getDescriptor() instanceof SSHeuristicDescriptor)
				desc = new SSHeuristicDescriptor();
		}

		for(Index ix: rootIndexes) {
			value =  JOptionPane.showInputDialog(ix.getLabel());
			if (!(value == null)) {
				if (ix.getSuccessors().get(0).getDescriptor().getValue() instanceof String)
					simbolValue =  value;
				else simbolValue =  Double.parseDouble(value);
				desc.set(this.getRoot().getStructure(), ix.getLabel(), simbolValue);
				this.getProblemSolutions().addToDescription(desc);
			}
		}
	}	
}
