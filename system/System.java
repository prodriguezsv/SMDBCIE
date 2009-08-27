/**
 * @see "Categoría Sukia Reasoner en SUKIA SmallTalk"
 */
package system;

import ontology.taxonomy.Taxonomy;
import redundantDiscriminationNet.RDMultiNet;
import searchHintsBase.HintsBase;
import system.scopes.AttributeScopes;
import system.scopes.GroupingHeuristicScopes;
import system.scopes.StructureScopes;

/**
 * @see "Clase Sukia en SUKIA SmallTalk"
 * @author Armando
 *
 */
public class System {
	private RDMultiNet caseMemory;
	private HintsBase hintsBase;
	private Object learner;
	private Reasoner reasoner;
	private String taxonomicGroupName;
	private Taxonomy taxonomy;

	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public System(String aCommonName) {
		// Initialize scope classes
		StructureScopes.initialize();
		AttributeScopes.initialize();
		GroupingHeuristicScopes.initialize();

		setTaxonomicGroupName(aCommonName);

		setHintsBase(new HintsBase());
		if (this.loadHintsBase() == null) return;

		setCaseMemory(new RDMultiNet());
		if (this.loadCaseMemory() == null) return;

		setTaxonomy(new Taxonomy());
		if (this.loadTaxonomy() == null) return;

		this.loadReasoner();

		setLearner(null);
	}

	/**
	 * Método de instancia agregado
	 * @param caseMemory
	 */
	public void setCaseMemory(RDMultiNet caseMemory) {
		this.caseMemory = caseMemory;
	}

	/**
	 * THIS IS THE METHOD TO BE USED IN ORDER TO READ THE CASE MEMORY
	 * DATA FROM DISK, AND ASSEMBLING IT IN MEMORY
	 * @see "Método loadCaseMemory del protocolo preparing en SUKIA SmallTalk"
	 */
	public Object loadCaseMemory() {
		return this;
	}
	
	/**
	 * @see "Método caseMemory del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public RDMultiNet getCaseMemory() {
		return caseMemory;
	}

	/**
	 * Método de instancia agregado
	 * @param hintsBase
	 */
	public void setHintsBase(HintsBase hintsBase) {
		this.hintsBase = hintsBase;
	}

	/**
	 * THIS IS THE METHOD TO BE USED IN ORDER TO READ THE CASE MEMORY
	 * DATA FROM DISK, AND ASSEMBLING IT IN MEMORY
	 * @see "Método loadHintsBase del protocolo preparing en SUKIA SmallTalk"
	 */
	public Object loadHintsBase() {
		return this;
	}
	
	/**
	 * @see "Método hintsBase del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public HintsBase getHintsBase() {
		return hintsBase;
	}

	/**
	 * Método de instancia agregado
	 * @param learner
	 */
	public void setLearner(Object learner) {
		this.learner = learner;
	}

	/**
	 * @see "Método learner del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Object getLearner() {
		return learner;
	}

	/**
	 * Método de instancia agregado
	 * @param reasoner
	 */
	public void setReasoner(Reasoner reasoner) {
		this.reasoner = reasoner;
	}

	/**
	 * @see "Método reasoner del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Reasoner getReasoner() {
		return reasoner;
	}

	/**
	 * Examples of the argument aCommonName: #plant, #mollusk, #fungus
	 * @param aCommonName
	 */
	public void setTaxonomicGroupName(String aCommonName) {
		this.taxonomicGroupName = aCommonName;
	}

	/**
	 * @see "Método taxonomicGroupName del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public String getTaxonomicGroupName() {
		return taxonomicGroupName;
	}

	/**
	 * Método de instancia agregado
	 * @param taxonomy
	 */
	public void setTaxonomy(Taxonomy taxonomy) {
		this.taxonomy = taxonomy;
	}
	
	/**
	 * THIS IS THE METHOD TO BE USED IN ORDER TO READ THE CASE MEMORY
	 * DATA FROM DISK, AND ASSEMBLING IT IN MEMORY
	 * @see "Método loadTaxonomy del protocolo preparing en SUKIA SmallTalk"
	 */
	public Object loadTaxonomy() {
		return this;
	}

	/**
	 * @see "Método taxonomy del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Taxonomy getTaxonomy() {
		return taxonomy;
	}

	/**
	 * @see "Método loadReasoner del protocolo preparing en SUKIA SmallTalk"
	 */
	public Object loadReasoner() {
		this.setReasoner(new Reasoner(this));
		
		return this;
	}
}
