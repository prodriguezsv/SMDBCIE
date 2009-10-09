/**
 * @see "Categoría Sukia Reasoner en SUKIA SmallTalk"
 */
package system;

import jade.core.AID;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import oracleIDGui.OracleIDGui;

import edu.stanford.smi.protege.model.Cls;
import edu.stanford.smi.protege.model.Instance;
import edu.stanford.smi.protege.model.KnowledgeBase;
import edu.stanford.smi.protege.model.Project;

import ontology.CBR.SimilarityDegree;
import ontology.taxonomy.Taxon;
import ontology.taxonomy.TaxonomicRank;
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
public class OracleIDSystem {
	/**
	 * Estructuras de datos de memoría compartidas por los agentes
	 */
	private RDMultiNet caseMemory;
	private HintsBase hintsBase;
	private Taxonomy taxonomy;
	/**
	 * Bases de conocimientos: Terminología común, taxonomía, y terminología CBR
	 */
	private KnowledgeBase commonKb, taxonomyKb, cbrKb;
	private TaxonomyKbBeanFactory taxonomyKbBeanFactory;
	/**
	 * Espectativas o parámetros del sistema: meta de identificación
	 */
	private String identGoal;
	private int maxNumberSolutions;
	private String minSimilarityDegree;
	private boolean presentFailedSolutions;
	/**
	 * Controladores de agentes locales
	 */
	private AgentController interfaceAgentController;
	private AgentController learnerAgentController;
	private AgentController reasonerAgentController;
	private AgentController retrieverAgentController;
	/**
	 * Contenedor principal de la plataforma jade
	 */
	private ContainerController mainContainer;
	private OracleIDGui systemGui;
	/**
	 * Ruta de archivos de ontologías
	 */
	private final String COMMON_PROJECT_FILE_NAME = "c:\\eclipse\\Projects\\Tests\\OracleID\\rsc\\" +
			"ProtegeOntologies\\commonOntology.pprj";
	private final String TAXONOMY_PROJECT_FILE_NAME = "c:\\eclipse\\Projects\\Tests\\OracleID\\rsc\\" +
			"ProtegeOntologies\\TaxonomyOntology.pprj";
	private final String CBR_PROJECT_FILE_NAME = "c:\\eclipse\\Projects\\Tests\\OracleID\\rsc\\" +
			"ProtegeOntologies\\CBROntology.pprj";	
  	/**
  	 * The singleton instance of this system
  	 */
  	private static OracleIDSystem theInstance = new OracleIDSystem();

  	/**
  	 * Devuelve la instancia del sistema
  	 * @return
  	 */
  	public static OracleIDSystem getInstance() {
  		return theInstance;
  	}
  	
	/**
	 * @throws TaxonomyKbBeanFactoryException 
	 * @see "Método initialize del protocolo initializing en SUKIA SmallTalk"
	 */
	private OracleIDSystem() {		
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
        
        project  = new Project(CBR_PROJECT_FILE_NAME, errors);
        
    	cbrKb = project.getKnowledgeBase();
        
        if (errors.size() != 0) {
        	Iterator<String> i = errors.iterator();
            while (i.hasNext()) {
            	System.out.println("Error: " + i.next());
            }
            
            System.exit(0);
        }
        
        hintsBase = new HintsBase();
		if (!this.loadHintsBase()) System.exit(0);

		caseMemory = new RDMultiNet();
		if (!this.loadCaseMemory()) System.exit(0);

		taxonomy = new Taxonomy();
		if (!this.loadTaxonomy()) System.exit(0);
		
		// The identification goal. Default value: species
		setIdentGoal(TaxonomicRank.getMostSpecificLevel().getRank());

		// USER EXPECTATION: Maximum number of solutions to present. Default value: 3
		setMaxNumberSolutions(3);

		/* USER EXPECTATION: Show failed cases, in case no successful ones are available
		 Default: true (i.e., show them)*/
		setPresentFailedSolutions(true);

		/* USER EXPECTATION: Minimal similarity degree used in comparisons. 
		 Default: moderately similar to equal*/
		this.setMinSimilarityDegree(SimilarityDegree.MEDIANAMENTESIMILAR.getSimilarityDegree());

		interfaceAgentController = null;
		reasonerAgentController = null;
		learnerAgentController = null;
		retrieverAgentController = null;
	}

	/**
	 * @see "Método identGoal: del protocolo adding en SUKIA SmallTalk"
	 * @param identGoal
	 */
	public void setIdentGoal(String aTaxonomicLevelsValue) {
		identGoal = aTaxonomicLevelsValue;
	}

	/**
	 * @see "Método identGoal del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public String getIdentGoal() {
		return identGoal;
	}

	/**
	 * @see "Método maxNumberSolutions: del protocolo adding en SUKIA SmallTalk"
	 * @param maxNumberSolutions
	 */
	public void setMaxNumberSolutions(int maxNumber) {
		maxNumberSolutions = maxNumber;
	}

	/**
	 * @see "Método maxNumberSolutions del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public int getMaxNumberSolutions() {
		return maxNumberSolutions;
	}

	/**
	 * @see "Método minSimilarityDegree: del protocolo adding en SUKIA SmallTalk"
	 * @param aSimRangesValue
	 */
	public void setMinSimilarityDegree(String aSimRangesValue) {
		minSimilarityDegree = aSimRangesValue;
	}

	/**
	 * @see "Método minSimilarityDegree del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public String getMinSimilarityDegree() {
		return minSimilarityDegree;
	}
	
	/**
	 * @see "Método presentFailedSolutions: del protocolo adding en SUKIA SmallTalk"
	 * @param presentFailedSolutions
	 */
	public void setPresentFailedSolutions(boolean presentFailedSolutions) {
		this.presentFailedSolutions = presentFailedSolutions;
	}

	/**
	 * @see "Método presentFailedSolutions del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public boolean isPresentFailedSolutions() {
		return presentFailedSolutions;
	}

	public KnowledgeBase getCommonKb() {
		return commonKb;
	}

	public KnowledgeBase getTaxonomyKb() {
		return taxonomyKb;
	}
	
	public KnowledgeBase getCbrKb() {
		return cbrKb;
	}

	/**
	 * THIS IS THE METHOD TO BE USED IN ORDER TO READ THE CASE MEMORY
	 * DATA FROM DISK, AND ASSEMBLING IT IN MEMORY
	 * @see "Método loadCaseMemory del protocolo preparing en SUKIA SmallTalk"
	 */
	private boolean loadCaseMemory() {
		return true;
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
	private boolean loadHintsBase() {
		return true;
	}
	
	/**
	 * @see "Método hintsBase del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public HintsBase getHintsBase() {
		return hintsBase;
	}
	
	/**
	 * THIS IS THE METHOD TO BE USED IN ORDER TO READ THE CASE MEMORY
	 * DATA FROM DISK, AND ASSEMBLING IT IN MEMORY
	 * @throws TaxonomyKbBeanFactoryException 
	 * @see "Método loadTaxonomy del protocolo preparing en SUKIA SmallTalk"
	 */
	@SuppressWarnings("unchecked")
	private boolean loadTaxonomy() {
		Cls cls = (Cls) taxonomyKb.getCls("Taxon");
        
        Iterator i = cls.getDirectInstances().iterator();
        while (i.hasNext()) {
        	Instance instance = (Instance) i.next();
        	
        	Taxon taxon;
			try {
				taxon = taxonomyKbBeanFactory.getTaxon(instance.getName());
			} catch (TaxonomyKbBeanFactoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
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
	
	public AID getRetrieverAID() {
		AID aid = null;
		
		try {
			aid = new AID(OracleIDSystem.getInstance().getRetrieverAgentController().getName(), AID.ISGUID);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return aid;
	}

	/**
	 * @see "Método learner del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public AID getLearnerAID() {
		AID aid = null;
		
		try {
			aid = new AID(OracleIDSystem.getInstance().getLearnerAgentController().getName() , AID.ISGUID);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return aid;
	}

	/**
	 * @see "Método reasoner del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public AID getReasonerAID() {
		AID aid = null;
		
		try {
			aid = new AID(OracleIDSystem.getInstance().getReasonerAgentController().getName(), AID.ISGUID);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return aid;
	}
	
	private AgentController getInterfaceAgentController() {
		return interfaceAgentController;
	}

	private void setInterfaceAgentController(AgentController interfaceAgentController) {
		this.interfaceAgentController = interfaceAgentController;
	}

	private AgentController getLearnerAgentController() {
		return learnerAgentController;
	}

	private void setLearnerAgentController(AgentController learnerAgentController) {
		this.learnerAgentController = learnerAgentController;
	}

	private AgentController getReasonerAgentController() {
		return reasonerAgentController;
	}

	private void setReasonerAgentController(AgentController reasonerAgentController) {
		this.reasonerAgentController = reasonerAgentController;
	}

	private AgentController getRetrieverAgentController() {
		return retrieverAgentController;
	}

	private void setRetrieverAgentController(AgentController retrieverAgentController) {
		this.retrieverAgentController = retrieverAgentController;
	}

	private ContainerController getMainContainer() {
		return mainContainer;
	}

	private void setMainContainer(ContainerController mainContainer) {
		this.mainContainer = mainContainer;
	}

	public OracleIDGui getSystemGui() {
		return systemGui;
	}

	public void setSystemGui(OracleIDGui systemGui) {
		this.systemGui = systemGui;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws TaxonomyKbBeanFactoryException {		
		jade.core.Runtime rt = jade.core.Runtime.instance();
		
		// Create a container to host the agents
		Profile p = new ProfileImpl();
		p.setParameter(Profile.CONTAINER_NAME, "Species-ID");
		
		ContainerController cc = rt.createMainContainer(p);
		OracleIDSystem.getInstance().setMainContainer(cc);
		
		if (cc != null) {
			// Create the agents and start them
			try {
				OracleIDSystem.getInstance().setInterfaceAgentController(cc.createNewAgent("Interface", 
						"system.InterfaceAgent", null));
				OracleIDSystem.getInstance().getInterfaceAgentController().start();
				
				OracleIDSystem.getInstance().setReasonerAgentController(cc.createNewAgent("Reasoner", 
						"system.ReasonerAgent", null));
				OracleIDSystem.getInstance().getReasonerAgentController().start();
				
				
				OracleIDSystem.getInstance().setRetrieverAgentController(cc.createNewAgent("Retriever", 
						"system.RetrieverAgent", null));
				OracleIDSystem.getInstance().getRetrieverAgentController().start();
				
				OracleIDSystem.getInstance().setLearnerAgentController(cc.createNewAgent("Learner", 
						"system.LearnerAgent", null));
				OracleIDSystem.getInstance().getLearnerAgentController().start();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}		
	}
	
	public void cleanup() {
		try {
			OracleIDSystem.getInstance().getInterfaceAgentController().kill();
			OracleIDSystem.getInstance().getReasonerAgentController().kill();
			OracleIDSystem.getInstance().getRetrieverAgentController().kill();
			OracleIDSystem.getInstance().getLearnerAgentController().kill();	
			OracleIDSystem.getInstance().getMainContainer().kill();
			jade.core.Runtime.instance().shutDown();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
