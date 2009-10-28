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

import oracleIDGui.ConfigurationDialog;
import oracleIDGui.OracleIDGui;

import edu.stanford.smi.protege.model.Cls;
import edu.stanford.smi.protege.model.Instance;
import edu.stanford.smi.protege.model.KnowledgeBase;
import edu.stanford.smi.protege.model.Project;

import ontology.CBR.CBRKbBeanFactory;
import ontology.CBR.CBRKbBeanFactoryException;
import ontology.CBR.Case;
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
	private CBRKbBeanFactory cbrKbBeanFactory;
	/**
	 * Espectativas o parámetros del sistema: meta de identificación
	 */
	private String identGoal;
	private int maxNumberSolutions;
	private String minSimilarityDegree;
	private boolean presentFailedSolutions;
	
	private boolean isInteractive;

	/**
	 * Controladores de agentes locales
	 */
	private AgentController interfaceAgentController;
	private AgentController learnerAgentController;
	private AgentController reasonerAgentController;
	private AgentController retrieverAgentController;
	private AgentController evaluatorAgentController;
	private AgentController selectorAgentController;
	/**
	 * Contenedor principal de la plataforma jade
	 */
	private ContainerController container;
	private OracleIDGui systemGui;
	/**
	 * Proyectos de Protege
	 */
	private Project commonProject, CBRProject, taxonomyProject;

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
    	
		commonProject  = new Project(COMMON_PROJECT_FILE_NAME, errors);       
        
        if (errors.size() != 0) {
        	Iterator<String> i = errors.iterator();
            while (i.hasNext()) {
            	System.out.println("Error: " + i.next());
            }
            
            System.exit(0);
        }
        
    	commonKb = commonProject.getKnowledgeBase();
        
        taxonomyProject  = new Project(TAXONOMY_PROJECT_FILE_NAME, errors);        
        
        if (errors.size() != 0) {
        	Iterator<String> i = errors.iterator();
            while (i.hasNext()) {
            	System.out.println("Error: " + i.next());
            }
            
            System.exit(0);
        }
        
    	taxonomyKb = taxonomyProject.getKnowledgeBase();
        
        taxonomyKbBeanFactory = new TaxonomyKbBeanFactory(taxonomyKb);
        
        CBRProject  = new Project(CBR_PROJECT_FILE_NAME, errors);       
        
        if (errors.size() != 0) {
        	Iterator<String> i = errors.iterator();
            while (i.hasNext()) {
            	System.out.println("Error: " + i.next());
            }
            
            System.exit(0);
        }
        
    	cbrKb = CBRProject.getKnowledgeBase();
    	cbrKbBeanFactory = new CBRKbBeanFactory(cbrKb);
        
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
		
		setInteractive(true);

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
	
	public Project getCommonProject() {
		return commonProject;
	}

	public Project getCBRProject() {
		return CBRProject;
	}

	public Project getTaxonomyProject() {
		return taxonomyProject;
	}

	/**
	 * THIS IS THE METHOD TO BE USED IN ORDER TO READ THE CASE MEMORY
	 * DATA FROM DISK, AND ASSEMBLING IT IN MEMORY
	 * @see "Método loadCaseMemory del protocolo preparing en SUKIA SmallTalk"
	 */
	@SuppressWarnings("unchecked")
	private boolean loadCaseMemory() {
		Cls cls = (Cls) cbrKb.getCls("Case");
        
        Iterator i = cls.getDirectInstances().iterator();
        while (i.hasNext()) {
        	Instance instance = (Instance) i.next();
        	
        	Case aCase;
			try {
				aCase = cbrKbBeanFactory.getCase(instance.getName());
			} catch (CBRKbBeanFactoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
        	this.getCaseMemory().add(aCase);		                    
        }
        
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
	
	/**
	 * @see "Método reasoner del protocolo accessing en SUKIA SmallTalk"
	 * @return
	 */
	public AID getEvaluatorAID() {
		AID aid = null;
		
		try {
			aid = new AID(OracleIDSystem.getInstance().getEvaluatorAgentController().getName(), AID.ISGUID);
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
	public AID getSelectorAID() {
		AID aid = null;
		
		try {
			aid = new AID(OracleIDSystem.getInstance().getSelectorAgentController().getName(), AID.ISGUID);
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

	public AgentController getEvaluatorAgentController() {
		return evaluatorAgentController;
	}

	public void setEvaluatorAgentController(AgentController evaluatorAgentController) {
		this.evaluatorAgentController = evaluatorAgentController;
	}

	public AgentController getSelectorAgentController() {
		return selectorAgentController;
	}

	public void setSelectorAgentController(AgentController selectorAgentController) {
		this.selectorAgentController = selectorAgentController;
	}

	private ContainerController getContainer() {
		return container;
	}

	private void setContainer(ContainerController mainContainer) {
		this.container = mainContainer;
	}

	public OracleIDGui getSystemGui() {
		return systemGui;
	}

	public void setSystemGui(OracleIDGui systemGui) {
		this.systemGui = systemGui;
	}

	public boolean isInteractive() {
		return isInteractive;
	}

	public void setInteractive(boolean isThereInteraction) {
		this.isInteractive = isThereInteraction;
	}
	
	private String institution = null;	
	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws TaxonomyKbBeanFactoryException {		
		jade.core.Runtime rt = jade.core.Runtime.instance();
		String institution;
		
		ConfigurationDialog config = new ConfigurationDialog(null);
		
		ContainerController cc = null;
		OracleIDSystem.getInstance().setInstitution(config.getName());
		institution = config.getName();
		
		if (institution != null || config.getHost() != null) {
			// Create a container to host the agents
			Profile p = new ProfileImpl();
			
			if (institution.equals("INBio")) {
				//p.setParameter(Profile.CONTAINER_NAME, "Species-ID");
			
				cc = rt.createMainContainer(p);
				OracleIDSystem.getInstance().setContainer(cc);
			} else {
				p.setParameter(Profile.MAIN_HOST, config.getHost());			
				cc = rt.createAgentContainer(p);
				OracleIDSystem.getInstance().setContainer(cc);
			}
		} else {
			System.exit(0);
		}
		
		if (cc != null) {
			// Create the agents and start them
			try {
				OracleIDSystem.getInstance().setInterfaceAgentController(cc.createNewAgent(institution +
						".Interface", "system.InterfaceAgent", null));
				OracleIDSystem.getInstance().getInterfaceAgentController().start();
				
				OracleIDSystem.getInstance().setReasonerAgentController(cc.createNewAgent(institution +
						".Reasoner", "system.ReasonerAgent", null));
				OracleIDSystem.getInstance().getReasonerAgentController().start();
				
				
				OracleIDSystem.getInstance().setRetrieverAgentController(cc.createNewAgent(institution +
						".Retriever", "system.RetrieverAgent", null));
				OracleIDSystem.getInstance().getRetrieverAgentController().start();
				
				OracleIDSystem.getInstance().setLearnerAgentController(cc.createNewAgent(institution +
						".Learner", "system.LearnerAgent", null));
				OracleIDSystem.getInstance().getLearnerAgentController().start();
				
				OracleIDSystem.getInstance().setEvaluatorAgentController(cc.createNewAgent(institution +
						".Evaluator", "system.EvaluatorAgent", null));
				OracleIDSystem.getInstance().getEvaluatorAgentController().start();
				
				OracleIDSystem.getInstance().setSelectorAgentController(cc.createNewAgent(institution +
						".Selector", "system.SelectorAgent", null));
				OracleIDSystem.getInstance().getSelectorAgentController().start();
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
			OracleIDSystem.getInstance().getEvaluatorAgentController().kill();
			OracleIDSystem.getInstance().getSelectorAgentController().kill();
			
			OracleIDSystem.getInstance().getContainer().kill();				
			
			jade.core.Runtime.instance().shutDown();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
