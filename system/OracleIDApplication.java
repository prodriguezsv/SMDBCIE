/**
 * @see "Categoría Sukia Reasoner en SUKIA SmallTalk"
 */
package system;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JFrame;

import edu.stanford.smi.protege.model.Cls;
import edu.stanford.smi.protege.model.Instance;
import edu.stanford.smi.protege.model.KnowledgeBase;
import edu.stanford.smi.protege.model.Project;

import oracleIDGui.OracleIDGui;
import ontology.taxonomy.Taxon;
import ontology.taxonomy.Taxonomy;
import ontology.taxonomy.TaxonomyKbBeanFactory;
import ontology.taxonomy.TaxonomyKbBeanFactoryException;
import redundantDiscriminationNet.RDMultiNet;
import searchHintsBase.HintsBase;

/**
 * @see "Clase Sukia en SUKIA SmallTalk"
 * @author Armando
 *
 */
public class OracleIDApplication {
	private RDMultiNet caseMemory;
	private HintsBase hintsBase;
	private Object learner;
	private Reasoner reasoner;
	private Taxonomy taxonomy;
	private KnowledgeBase commonKb, taxonomyKb;
	private TaxonomyKbBeanFactory taxonomyKbBeanFactory;
	private static OracleIDGui thisClass;
	final private String COMMON_PROJECT_FILE_NAME = "c:\\eclipse\\Projects\\Tests\\OracleID\\rsc\\ProtegeOntologies\\" +
						"commonOntology.pprj";
	final private String TAXONOMY_PROJECT_FILE_NAME = "c:\\eclipse\\Projects\\Tests\\OracleID\\rsc\\ProtegeOntologies\\" +
						"TaxonomyOntology.pprj";
	
	/**
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	public OracleIDApplication() throws TaxonomyKbBeanFactoryException {		
		Collection<String> errors = new ArrayList<String>();
    	
		Project project  = new Project(COMMON_PROJECT_FILE_NAME, errors);
        
    	commonKb = project.getKnowledgeBase();
        
        if (errors.size() != 0) {
        	Iterator<String> i = errors.iterator();
            while (i.hasNext()) {
            	System.out.println("Error: " + i.next());
            }
            
            System.exit(0);
        }
        
        project  = new Project(TAXONOMY_PROJECT_FILE_NAME, errors);
        
    	taxonomyKb = project.getKnowledgeBase();
        
        if (errors.size() != 0) {
        	Iterator<String> i = errors.iterator();
            while (i.hasNext()) {
            	System.out.println("Error: " + i.next());
            }
            
            System.exit(0);
        }
        
        taxonomyKbBeanFactory = new TaxonomyKbBeanFactory(taxonomyKb);
        
        hintsBase = new HintsBase();
		if (this.loadHintsBase() == null) return;

		caseMemory = new RDMultiNet();
		if (this.loadCaseMemory() == null) return;

		taxonomy = new Taxonomy();
		if (!this.loadTaxonomy()) System.exit(0);

		reasoner = new Reasoner(this);

		learner = null;
	}
	
	public OracleIDGui getOracleIDGui() {
		return thisClass;
	}

	public KnowledgeBase getCommonKb() {
		return commonKb;
	}

	public KnowledgeBase getTaxonomyKb() {
		return taxonomyKb;
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
	 * @see "Método learner del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Object getLearner() {
		return learner;
	}

	/**
	 * @see "Método reasoner del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public Reasoner getReasoner() {
		return reasoner;
	}
	
	/**
	 * THIS IS THE METHOD TO BE USED IN ORDER TO READ THE CASE MEMORY
	 * DATA FROM DISK, AND ASSEMBLING IT IN MEMORY
	 * @see "Método loadTaxonomy del protocolo preparing en SUKIA SmallTalk"
	 */
	@SuppressWarnings("unchecked")
	public boolean loadTaxonomy() throws TaxonomyKbBeanFactoryException {
		Cls cls = (Cls) taxonomyKb.getCls("Taxon");
        
        Iterator i = cls.getDirectInstances().iterator();
        while (i.hasNext()) {
        	Instance instance = (Instance) i.next();
        	
        	Taxon taxon = taxonomyKbBeanFactory.getTaxon(instance.getName());
        	this.getTaxonomy().addTaxon(taxon);		                    
        }
        
		return true;
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
		return this;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws TaxonomyKbBeanFactoryException {
		OracleIDApplication oracleIDApplication;
		
		oracleIDApplication = new OracleIDApplication();
		
		thisClass = new OracleIDGui(oracleIDApplication);
		thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		thisClass.setVisible(true);
	}
}
