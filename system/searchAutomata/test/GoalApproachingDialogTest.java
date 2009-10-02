/**
 * 
 */
package system.searchAutomata.test;

import static org.junit.Assert.*;

import java.util.List;

import ontology.CBR.Hypothesis;
import ontology.CBR.PossibleSolution;
import ontology.CBR.SimilarityDegree;
import ontology.common.Description;
import ontology.common.Descriptor;
import ontology.common.MeasuringUnit;
import ontology.common.RVCharacterDescriptor;
import ontology.common.RVHeuristicDescriptor;
import ontology.common.RangeValue;
import ontology.common.SSCharacterDescriptor;
import ontology.common.SSHeuristicDescriptor;
import ontology.common.SVCharacterDescriptor;
import ontology.common.SingleValue;
import ontology.taxonomy.Modifier;
import ontology.taxonomy.Taxon;
import ontology.taxonomy.TaxonomicRank;
import ontology.taxonomy.Taxonomy;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import system.searchAutomata.GoalApproachingHandler;
import system.searchAutomata.SearchStatus;

/**
 * @author Armando
 *
 */
public class GoalApproachingDialogTest extends GoalApproachingHandler {
	static Taxonomy taxonomy;
    static Taxon rootTaxon, taxon;
	
	public GoalApproachingDialogTest() {
		super(null, TaxonomicRank.FAMILY, new Hypothesis(), null, SimilarityDegree.MEDIANAMENTESIMILAR);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Iniciando pruebas para la clase " + GoalApproachingHandler.class.getName());
		
		taxonomy = new Taxonomy();
        rootTaxon = new Taxon(TaxonomicRank.ROOT, null);
//-----------------------Taxon No. 1---------------------
        taxon = new Taxon(TaxonomicRank.GENUS, "Chromodorididae"); //Ojo la información es de prueba
//-----------------------Structure No. 1---------------------
        taxon.addToDescription(new SSCharacterDescriptor("Cuerpo","Forma","Alargado"),
                                    new Modifier(1.0,1.0,0.8));
        taxon.addToDescription(new SSCharacterDescriptor("Cuerpo","Forma","Ovalado"),
                                    new Modifier(1.0,1.0,0.1));
        taxon.addToDescription(new RVCharacterDescriptor("Cuerpo","Longitud", new RangeValue(0.3, 4.0,
        		MeasuringUnit.CM)), new Modifier(1.0,1.0,1.0));
        taxon.addToDescription(new SSCharacterDescriptor("Cuerpo","Conformación","Tiene cerata"),
                                    new Modifier(1.0,1.0,1.0));
//-----------------------Structure No. 2---------------------
        taxon.addToDescription(new SSCharacterDescriptor("Pie","Disposición","Sobresale al manto"),
                                    new Modifier(0.8,1.0,0.8));
        taxon.addToDescription(new SSCharacterDescriptor("Pie","Coloración","Blanquecino"),
                                    new Modifier(0.8,1.0,0.7));
        taxon.addToDescription(new SSCharacterDescriptor("Pie","Coloración","Crema"),
                                    new Modifier(0.8,1.0,0.7));
        taxon.addToDescription(new SSCharacterDescriptor("Pie","Coloración","Gris oscuro casi negro"),
                                    new Modifier(0.8,1.0,0.2));
//-----------------------Structure No. 2---------------------
        taxon.addToDescription(new SSCharacterDescriptor("Branquia","Posición durante desplazamiento","Hacia atras"),
                                    new Modifier(0.8,1.0,0.8));
        taxon.addToDescription(new SSCharacterDescriptor("Branquia","¨Posición del ano con respecto a la branquia","En el centro"),
                                    new Modifier(0.8,1.0,0.8));
        taxon.addToDescription(new RVCharacterDescriptor("Branquia","Número hojas branquiales", 
        		new RangeValue(6.0, 9.0)), new Modifier(0.8,1.0,1.0));
        taxon.addToDescription(new SSCharacterDescriptor("Branquia","Forma hojas branquiales","Bipinnada"),
                                    new Modifier(0.8,1.0,0.6));
        taxon.addToDescription(new SSCharacterDescriptor("Branquia","Forma hojas branquiales","Tripinnada"),
                                    new Modifier(0.8,1.0,0.4));
//-----------------------Structure No. 4---------------------
        taxon.addToDescription(new SSCharacterDescriptor("Manto","Textura","Lisa"),
                                    new Modifier(1.0,1.0,0.8));
        taxon.addToDescription(new SSCharacterDescriptor("Manto","Textura","Con tuberculos"),
                                    new Modifier(1.0,1.0,0.4));
        taxon.addToDescription(new SSCharacterDescriptor("Manto","Forma del borde","Ondulado"),
                                    new Modifier(1.0,1.0,0.3));
        taxon.addToDescription(new SSCharacterDescriptor("Manto","Textura del borde","Lisa"),
                                    new Modifier(1.0,1.0,0.7));
//-----------------------Structure No. 5---------------------
        taxon.addToDescription(new SSCharacterDescriptor("Glándulas del manto","Posición","Delante"),
                                    new Modifier(1.0,1.0,0.2));
        taxon.addToDescription(new SSCharacterDescriptor("Glándulas del manto","Posición",
        		"Delante y atras"), new Modifier(1.0,1.0,0.2));
        taxon.addToDescription(new SSCharacterDescriptor("Glándulas del manto","Posición",
        		"Alrededor del manto"), new Modifier(1.0,1.0,0.4));
//-----------------------Structure No. 6---------------------
        taxon.addToDescription(new SSCharacterDescriptor("Rinoforos","Forma","Laminados"),
                                    new Modifier(1.0,1.0,1.0));
        taxon.addToDescription(new RVCharacterDescriptor("Rinoforos","Número de laminillas",
        		new RangeValue(6.0, 20.0)), new Modifier(1.0,1.0,1.0));
//-----------------------Structure No. 7---------------------
        taxon.addToDescription(new SSCharacterDescriptor("Tentáculos orales","Contextura","Macizo"),
                                    new Modifier(1.0,1.0,0.7));
        taxon.addToDescription(new SSCharacterDescriptor("Tentaculos orales","Contextura","Surcado"),
                                    new Modifier(1.0,1.0,0.2));
//-----------------------Grouping Heuristic No. 1---------------------
        taxon.addToDescription(new SSHeuristicDescriptor("Alimentación", "Alimentación","Esponjas"),
                                    new Modifier(1.0,1.0,1.0));
//-----------------------Grouping Heuristic No. 2---------------------
        taxon.addToDescription(new RVHeuristicDescriptor("Profundidad donde se encuentra",
        		"Profundidad donde se encuentra", new RangeValue(0.0, 20.0, MeasuringUnit.CM)),
        		new Modifier(1.0,1.0,1.0));
//-----------------------Grouping Heuristic No. 3---------------------
        taxon.addToDescription(new SSHeuristicDescriptor("Medio de preservacion tenido",
        		"Medio de preservacion tenido","Azul marino"), new Modifier(1.0,1.0,1.0));
        taxon.addToDescription(new SSHeuristicDescriptor("Medio de preservacion tenido",
        		"Medio de preservacion tenido","Celeste"), new Modifier(1.0,1.0,0.8));
        taxon.addToDescription(new SSHeuristicDescriptor("Medio de preservacion tenido",
        		"Medio de preservacion tenido","Amarillento"), new Modifier(1.0,1.0,0.2));
        taxonomy.addTaxon(taxon, rootTaxon);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Terminando pruebas para la clase " + GoalApproachingHandler.class.getName());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.setTaxonomy(taxonomy);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.setTaxonomy(null);
	}

	/**
	 * Test method for {@link system.searchAutomata.GoalApproachingHandler#doDialog()}.
	 */
	@Test
	public void testDoDialog() {
		Taxon predecessorTaxon;
		PossibleSolution ps1;
		Description description;
		
		System.out.println("Iniciando pruebas para el método DoDialog()");
		
		System.out.println("Verificar que se efectúa el dialogo con el usuario");
		predecessorTaxon = new Taxon(TaxonomicRank.FAMILY, "Chromodorididae");
		predecessorTaxon.addSuccessor(taxon);
		ps1  = new PossibleSolution();
		ps1.setSolution(taxon);
		this.addProcessList(ps1);
		
		description = new Description();
		description.addToConcreteDescription(new SSCharacterDescriptor("Cuerpo","Forma","Alargado"));
		description.addToConcreteDescription(new RVCharacterDescriptor("Cuerpo","Longitud", new RangeValue(0.3, 4.0,
        		MeasuringUnit.CM)));
		this.getHypothesis().setDescription(description);
		assertEquals(SearchStatus.SUCCESS, this.doDialog());
	}

	/**
	 * Test method for {@link system.searchAutomata.GoalApproachingHandler#selectPossibleSolutionsNearestToGoal()}.
	 */
	@Test
	public void testSelectPossibleSolutionsNearestToGoal() {
		Taxon taxon;
		PossibleSolution ps1;
		
		System.out.println("Iniciando pruebas para el método SelectPossibleSolutionsNearestToGoal()");
		
		System.out.println("Verificar que no se obtienen soluciones posibles si no existen");
		assertFalse(this.selectPossibleSolutionsNearestToGoal());
		
		System.out.println("Verificar que se obtienen las soluciones posibles más cercanas");
		ps1 = new PossibleSolution();
		taxon = new Taxon(TaxonomicRank.GENUS, "Glossodoris");
		ps1.setSolution(taxon);
		this.addProcessList(ps1);
		
		assertTrue(this.selectPossibleSolutionsNearestToGoal());
		
		ps1 = new PossibleSolution();
		taxon = new Taxon(TaxonomicRank.SPECIES, "Chromodoris clenchi");
		ps1.setSolution(taxon);
		this.addProcessList(ps1);
		
		assertTrue(this.selectPossibleSolutionsNearestToGoal());
		assertTrue(this.getProcessList().size() ==1);
		assertTrue(this.getProcessList().get(0).getLevel() == TaxonomicRank.SPECIES);
	}

	/**
	 * Test method for {@link system.searchAutomata.GoalApproachingHandler#rangeValueDescriptorDialog(PossibleSolution, Taxon, Descriptor)}.
	 */
	@Test
	public void testRangeValueDescriptorDialog() {
		System.out.println("Iniciando pruebas para el método RangeValueDescriptorDialog");
		
		System.out.println("Verificar que se obtienen distintos resultados válidos");
		assertEquals(null, this.rangeValueDescriptorDialog(new SVCharacterDescriptor
			("Cuerpo", "Longitud", new SingleValue(0.3)), new PossibleSolution()));
		/*assertNotNull(this.rangeValueDescriptorDialog(new CharacterDescriptor
			("Cuerpo", "Longitud", new RangeValue(0.3, 4, MeasuringUnit.CM))));*/
	}

	/**
	 * Test method for {@link system.searchAutomata.GoalApproachingHandler#valueDescriptorDialog(List)}.
	 */
	@Test
	public void testValueDescriptorDialog() {
		Description description;
		
		System.out.println("Iniciando pruebas para el método ValueDescriptorDialog()");
		
		System.out.println("Verificar que se obtienen distintos resultados válidos");
		description = new Description();
		
		assertNull(this.valueDescriptorDialog(description, new PossibleSolution()));

		description.clearAllDescriptors();
		description.addDescriptors(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(0.3)));
		description.addDescriptors(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(1.0)));
		description.addDescriptors(new SVCharacterDescriptor("Cuerpo", "Longitud", new SingleValue(3.0)));

		//assertNotNull(this.valueDescriptorDialog(description));		
	}

	/**
	 * Test method for {@link system.searchAutomata.GoalApproachingHandler#determineSimilarityFor(Descriptor, Taxon)}.
	 */
	@Test
	public void testDetermineSimilarityFor() {
		System.out.println("Iniciando pruebas para el método DetermineSimilarityFor()");
		
		System.out.println("Verificar que existe similaridad entre un descriptor y los descriptores de un taxón");
		Taxon taxon = new Taxon(TaxonomicRank.FAMILY, "Chromodorididae");

        taxon.addToDescription(new SSCharacterDescriptor("Cuerpo","Forma","Alargado"),
                                    new Modifier(1.0,1.0,0.8));
        taxon.addToDescription(new SSCharacterDescriptor("Cuerpo","Forma","Ovalado"),
                                    new Modifier(1.0,1.0,0.1));
        
        Descriptor aDescriptor = new SSCharacterDescriptor("Cuerpo","Forma","Alargado");

        assertTrue(this.determineSimilarityFor(aDescriptor, taxon));
        
        System.out.println("Verificar que no existe similaridad entre un descriptor y los descriptores de un taxón");
        
        aDescriptor = new SVCharacterDescriptor("Cuerpo","Longitud", new SingleValue(0.3,
        		MeasuringUnit.CM));
        assertFalse(this.determineSimilarityFor(aDescriptor, taxon));
	}

	/**
	 * Test method for {@link system.searchAutomata.GoalApproachingHandler#areThereContradictions(Descriptor)}.
	 */
	@Test
	public void testAreThereContradictions() {
		PossibleSolution ps = new PossibleSolution();
		
		System.out.println("Iniciando pruebas para el método AreThereContradictions()");
		
		System.out.println("Verificar que hay contradicciones en la descripción o duplicados");
		ps.getSolutionDescription().addDescriptors(new SSCharacterDescriptor("Manto","Forma","Ovalado"));
		ps.getConfirmedDescription().addDescriptors(new SVCharacterDescriptor("Cuerpo","Longitud", new SingleValue(0.3,
        		MeasuringUnit.CM)));
		this.getHypothesis().getPossibleSolutions().add(ps);
		
		assertTrue(this.areThereContradictions(new SSCharacterDescriptor("Manto","Forma","Ovalado")));
		assertTrue(this.areThereContradictions(new SSCharacterDescriptor("Manto","Forma","Cuadrado")));
		
		assertTrue(this.areThereContradictions(new SVCharacterDescriptor("Cuerpo","Longitud", new SingleValue(0.3,
        		MeasuringUnit.CM))));
		assertTrue(this.areThereContradictions(new SVCharacterDescriptor("Cuerpo","Longitud", new SingleValue(3,
        		MeasuringUnit.CM))));
		
		System.out.println("Verificar que no hay contradicciones o duplicados en la descripción");
		assertFalse(this.areThereContradictions(new SVCharacterDescriptor("Branquias", 
				"Número de hojas branquiales", new SingleValue(3))));
		
		ps.getSolutionDescription().clearAllDescriptors();
		ps.getConfirmedDescription().clearAllDescriptors();
		this.getHypothesis().getPossibleSolutions().remove(ps);
	}

	/**
	 * Test method for {@link system.searchAutomata.GoalApproachingHandler#isDescriptorAlreadyProcessed(Descriptor)}.
	 */
	@Test
	public void testIsDescriptorAlreadyProcessed() {
		PossibleSolution ps = new PossibleSolution();
		
		System.out.println("Iniciando pruebas para el método AreThereContradictions()");
		
		System.out.println("Verificar que hay duplicados en la descripción procesada");
		ps.getDoubtfulDescription().addDescriptors(new SSCharacterDescriptor("Manto","Forma","Ovalado"));
		ps.getUnconfirmedDescription().addDescriptors(new SVCharacterDescriptor("Cuerpo","Longitud", new SingleValue(0.3,
        		MeasuringUnit.CM)));
		this.getHypothesis().getPossibleSolutions().add(ps);
		
		assertTrue(this.isDescriptorAlreadyProcessed(new SSCharacterDescriptor("Manto","Forma","Ovalado")));
		
		assertTrue(this.isDescriptorAlreadyProcessed(new SVCharacterDescriptor("Cuerpo","Longitud", new SingleValue(0.3,
        		MeasuringUnit.CM))));
		
		System.out.println("Verificar que no hay duplicados en la descripción procesada");
		assertFalse(this.isDescriptorAlreadyProcessed(new SVCharacterDescriptor("Branquias", 
				"Número de hojas branquiales", new SingleValue(3))));
		
		ps.getDoubtfulDescription().clearAllDescriptors();
		ps.getUnconfirmedDescription().clearAllDescriptors();
		this.getHypothesis().getPossibleSolutions().remove(ps);
	}

}
