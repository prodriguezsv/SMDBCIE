/**
 * 
 */
package system.test;

import ontology.common.Modifier;
import ontology.common.RVCharacterDescriptor;
import ontology.common.RVHeuristicDescriptor;
import ontology.common.SSCharacterDescriptor;
import ontology.common.SSHeuristicDescriptor;
import ontology.taxonomy.Taxon;
import ontology.taxonomy.TaxonomicRank;
import ontology.values.MeasuringUnit;
import ontology.values.RangeValue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * @author Armando
 *
 */
public class SystemTest {
	private static system.System oracleIDSystem;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Taxon taxon1, taxon2, taxon3, taxon4, taxon5, taxon6, taxon7, taxon8;
		
		System.out.println("Iniciando pruebas para la clase " + system.System.class.getName());
		oracleIDSystem = new system.System();
        
//-----------------------Taxon No. 1---------------------
        taxon1 = new Taxon(TaxonomicRank.FAMILY, "Chromodorididae");
        //-----------------------Structure No. 1---------------------
        taxon1.addToDescription(new SSCharacterDescriptor("Cuerpo","Forma","Alargado"),
                new Modifier(1.0,1.0,0.8));
  		taxon1.addToDescription(new SSCharacterDescriptor("Cuerpo","Forma","Ovalado"),
  		        new Modifier(1.0,1.0,0.1));
  		taxon1.addToDescription(new RVCharacterDescriptor("Cuerpo","Longitud", new RangeValue(0.3, 4.0,
  				MeasuringUnit.CM)), new Modifier(1.0,1.0,1.0));
  		taxon1.addToDescription(new SSCharacterDescriptor("Cuerpo","Conformación","Tiene cerata"),
  		        new Modifier(1.0,1.0,1.0));
  		//-----------------------Structure No. 2---------------------
  		taxon1.addToDescription(new SSCharacterDescriptor("Pie","Disposición","Sobresale al manto"),
  		        new Modifier(0.8,1.0,0.8));
  		taxon1.addToDescription(new SSCharacterDescriptor("Pie","Coloración","Blanquecino"),
  		        new Modifier(0.8,1.0,0.7));
  		taxon1.addToDescription(new SSCharacterDescriptor("Pie","Coloración","Crema"),
  		        new Modifier(0.8,1.0,0.7));
  		taxon1.addToDescription(new SSCharacterDescriptor("Pie","Coloración","Gris oscuro casi negro"),
  		        new Modifier(0.8,1.0,0.2));
  		//-----------------------Structure No. 3---------------------
  		taxon1.addToDescription(new SSCharacterDescriptor("Branquia","Posición durante desplazamiento",
  				"Hacia atras"), new Modifier(0.8,1.0,0.8));
  		taxon1.addToDescription(new SSCharacterDescriptor("Branquia","Posición del ano con respecto a la " +
  				"branquia","En el centro"), new Modifier(0.8,1.0,0.8));
  		taxon1.addToDescription(new RVCharacterDescriptor("Branquia","Número hojas branquiales", 
  				new RangeValue(6.0, 9.0)), new Modifier(0.8,1.0,1.0));
  		taxon1.addToDescription(new SSCharacterDescriptor("Branquia","Forma hojas branquiales","Bipinnada"),
  		        new Modifier(0.8,1.0,0.6));
  		taxon1.addToDescription(new SSCharacterDescriptor("Branquia","Forma hojas branquiales","Tripinnada"),
  		        new Modifier(0.8,1.0,0.4));
  		//-----------------------Structure No. 4---------------------
  		taxon1.addToDescription(new SSCharacterDescriptor("Manto","Textura","Lisa"),
  		        new Modifier(1.0,1.0,0.8));
  		taxon1.addToDescription(new SSCharacterDescriptor("Manto","Textura","Con tuberculos"),
  		        new Modifier(1.0,1.0,0.4));
  		taxon1.addToDescription(new SSCharacterDescriptor("Manto","Forma del borde","Ondulado"),
  		        new Modifier(1.0,1.0,0.3));
  		taxon1.addToDescription(new SSCharacterDescriptor("Manto","Textura del borde","Lisa"),
  		        new Modifier(1.0,1.0,0.7));
  		//-----------------------Structure No. 5---------------------
  		taxon1.addToDescription(new SSCharacterDescriptor("Glándulas del manto","Posición","Delante"),
  		        new Modifier(1.0,1.0,0.2));
  		taxon1.addToDescription(new SSCharacterDescriptor("Glándulas del manto","Posición",
  				"Delante y atras"), new Modifier(1.0,1.0,0.2));
  		taxon1.addToDescription(new SSCharacterDescriptor("Glándulas del manto","Posición",
  				"Alrededor del manto"), new Modifier(1.0,1.0,0.4));
  		//-----------------------Structure No. 6---------------------
  		taxon1.addToDescription(new SSCharacterDescriptor("Rinoforos","Forma","Laminados"),
  		        new Modifier(1.0,1.0,1.0));
  		taxon1.addToDescription(new RVCharacterDescriptor("Rinoforos","Número de laminillas",
  				new RangeValue(6.0, 20.0)), new Modifier(1.0,1.0,1.0));
  		//-----------------------Structure No. 7---------------------
  		taxon1.addToDescription(new SSCharacterDescriptor("Tentáculos orales","Contextura","Macizo"),
  		        new Modifier(1.0,1.0,0.7));
  		taxon1.addToDescription(new SSCharacterDescriptor("Tentaculos orales","Contextura","Surcado"),
  		        new Modifier(1.0,1.0,0.2));
  		//-----------------------Grouping Heuristic No. 1---------------------
  		taxon1.addToDescription(new SSHeuristicDescriptor("Alimentación", "Alimentación","Esponjas"),
  		        new Modifier(1.0,1.0,1.0));
  		//-----------------------Grouping Heuristic No. 2---------------------
  		taxon1.addToDescription(new RVHeuristicDescriptor("Profundidad donde se encuentra",
  				"Profundidad donde se encuentra", new RangeValue(0.0, 20.0, MeasuringUnit.M)), 
  				new Modifier(1.0,1.0,1.0));
  		//-----------------------Grouping Heuristic No. 3---------------------
  		taxon1.addToDescription(new SSHeuristicDescriptor("Medio de preservacion tenido",
  				"Medio de preservacion tenido","Azul marino"), new Modifier(1.0,1.0,1.0));
  		taxon1.addToDescription(new SSHeuristicDescriptor("Medio de preservacion tenido",
  				"Medio de preservacion tenido","Celeste"), new Modifier(1.0,1.0,0.8));
  		taxon1.addToDescription(new SSHeuristicDescriptor("Medio de preservacion tenido",
  				"Medio de preservacion tenido","Amarillento"), new Modifier(1.0,1.0,0.2));
//-----------------------Taxon No. 2---------------------
	    taxon2 = new Taxon(TaxonomicRank.GENUS, "Chromodoris");
	    //-----------------------Structure No. 1---------------------
	    taxon2.addToDescription(new SSCharacterDescriptor("Cuerpo","Posición de la banda dorsal " +
	      		"continua", "Centro"), new Modifier(1.0,1.0,1.0));
	    taxon2.addToDescription(new SSCharacterDescriptor("Cuerpo","Coloración","Brillante azul rojo " +
	      		"blanco anaranjado purpura"), new Modifier(1.0,1.0,1.0));
	    taxon2.addToDescription(new SSCharacterDescriptor("Cuerpo","Forma ventral","Aplanado"),
	                  new Modifier(1.0,1.0,0.6));
	    //-----------------------Structure No. 2---------------------
	    taxon2.addToDescription(new SSCharacterDescriptor("Manto","Forma","Elongado y ovalado"),
	              new Modifier(0.8,1.0,0.7));
	    taxon2.addToDescription(new SSCharacterDescriptor("Manto","Contextura","Con glándulas"),
	                  new Modifier(0.8,1.0,0.8));
	    //-----------------------Structure No. 3---------------------
	    taxon2.addToDescription(new SSCharacterDescriptor("Radula","Forma de los dientes","Denticulados"),
	              new Modifier(0.3,1.0,1.0));
	    taxon2.addToDescription(new SSCharacterDescriptor("Radula","Posición del diente más conspicuo",
	    		  "Centro"), new Modifier(0.3,1.0,0.5));
//-----------------------Taxon No. 3---------------------
	    taxon3 = new Taxon(TaxonomicRank.SPECIES, "Chromodoris sphoni");
	    //-----------------------Structure No. 1---------------------
	    taxon3.addToDescription(new RVCharacterDescriptor("Cuerpo","Longitud", new RangeValue(0.3, 0.35,
  				MeasuringUnit.CM)), new Modifier(0.0,1.0,1.0));
	    taxon3.addToDescription(new SSCharacterDescriptor("Cuerpo","Forma","Ovalado"),
  		        new Modifier(0.0,1.0,1.0));
	    taxon3.addToDescription(new SSCharacterDescriptor("Cuerpo","Conformación","No tiene tuberculos ni espiculas"),
  		        new Modifier(0.0,1.0,1.0));
	    taxon3.addToDescription(new SSCharacterDescriptor("Cuerpo","Coloración","Verde fosforescente"),
  		        new Modifier(0.0,1.0,0.8));
	    taxon3.addToDescription(new SSCharacterDescriptor("Cuerpo","Coloración","Café rojizo"),
  		        new Modifier(0.0,1.0,0.8));
	    taxon3.addToDescription(new SSCharacterDescriptor("Cuerpo","Coloración del fonfo","Blanco"),
  		        new Modifier(0.0,1.0,1.0));
	    taxon3.addToDescription(new SSCharacterDescriptor("Cuerpo","Coloración del fonfo","Crema"),
  		        new Modifier(0.0,1.0,1.0));
	    taxon3.addToDescription(new SSCharacterDescriptor("Cuerpo","Contextura","Lisa"),
  		        new Modifier(0.0,1.0,1.0));
	    //-----------------------Structure No. 2---------------------
	    taxon3.addToDescription(new SSCharacterDescriptor("Branquia","Forma hojas branquiales","Unipinnada"),
	        new Modifier(0.9,1.0,1.0));
	    taxon3.addToDescription(new SSCharacterDescriptor("Branquia","Coloración","Blanco"),
		        new Modifier(0.9,1.0,1.0));
	    taxon3.addToDescription(new SSCharacterDescriptor("Branquia","Coloración","Blanquecino"),
		        new Modifier(0.9,1.0,0.9));
	    taxon3.addToDescription(new SSCharacterDescriptor("Branquia","Coloración de los ápices","Morado"),
		        new Modifier(0.9,1.0,1.0));
	    //-----------------------Structure No. 3---------------------
  		taxon3.addToDescription(new SSCharacterDescriptor("Manto","Coloración del patron de rayas en forma de cruz","Rojo violaceo"),
  		        new Modifier(1.0,1.0,1.0));
  		taxon3.addToDescription(new SSCharacterDescriptor("Manto","Coloración del patron de rayas en forma de cruz","Granate"),
  		        new Modifier(1.0,1.0,1.0));
  		taxon3.addToDescription(new SSCharacterDescriptor("Manto","Coloración de puntos conspicuos","Amarillo"),
  		        new Modifier(1.0,1.0,1.0));
  		taxon3.addToDescription(new SSCharacterDescriptor("Manto","Coloración línea exterior del borde","Naranja"),
  		        new Modifier(1.0,1.0,1.0));
  		taxon3.addToDescription(new SSCharacterDescriptor("Manto","Coloración línea interior del borde","Rojo violaceo"),
  		        new Modifier(1.0,1.0,1.0));
  		taxon3.addToDescription(new SSCharacterDescriptor("Manto","Coloración de los puntos irregulares localizados entre las líneas del borde","Amarillo oro"),
  		        new Modifier(1.0,1.0,1.0));
  		//-----------------------Structure No. 4---------------------
  		taxon3.addToDescription(new SSCharacterDescriptor("Rinoforos","Coloración","Blanco"),
  		        new Modifier(0.9,1.0,1.0));
  		taxon3.addToDescription(new SSCharacterDescriptor("Rinoforos","Coloración","Blanquecino"),
  		        new Modifier(0.9,1.0,0.9));
  		taxon3.addToDescription(new SSCharacterDescriptor("Rinoforos","Coloración de los ápices","Morado"),
  		        new Modifier(0.9,1.0,1.0));
  		//-----------------------Structure No. 5---------------------
  		taxon3.addToDescription(new SSCharacterDescriptor("Cola","Coloración","Violaceo"),
  		        new Modifier(0.0,1.0,1.0));
  		taxon3.addToDescription(new SSCharacterDescriptor("Cola","Coloración","Blanco"),
  		        new Modifier(0.0,1.0,0.9));
  		taxon3.addToDescription(new SSCharacterDescriptor("Cola","Coloración","Crema"),
  		        new Modifier(0.0,1.0,0.9));
  		taxon3.addToDescription(new SSCharacterDescriptor("Cola","Coloración del borde","Verde azulado moteado de amarillo"),
  		        new Modifier(0.0,1.0,1.0));
  		//-----------------------Structure No. 6---------------------
  		taxon3.addToDescription(new SSCharacterDescriptor("Pie","Coloración","Blanco hialino"),
  		        new Modifier(0.0,1.0,1.0));
  		taxon3.addToDescription(new SSCharacterDescriptor("Pie","Coloración del borde","Blanco opaco"),
  		        new Modifier(0.0,1.0,1.0));
  		//-----------------------Structure No. 7---------------------
  		taxon3.addToDescription(new SSCharacterDescriptor("Aparato genital","Tamano de la glandula vestibular",
  				"Pequeño"), new Modifier(0.0,1.0,1.0));
//-----------------------Taxon No. 4---------------------
	    taxon4 = new Taxon(TaxonomicRank.SPECIES, "Chromodoris clenchi");
	    //-----------------------Structure No. 1---------------------
	    taxon4.addToDescription(new SSCharacterDescriptor("Cuerpo","Coloración","Rojo"),
  		        new Modifier(0.0,1.0,1.0));
	    taxon4.addToDescription(new SSCharacterDescriptor("Cuerpo","Coloración","Rojizo"),
  		        new Modifier(0.0,1.0,0.8));
	    taxon4.addToDescription(new SSCharacterDescriptor("Cuerpo","Coloración","Cafesuzco"),
  		        new Modifier(0.0,1.0,0.5));
	    taxon4.addToDescription(new SSCharacterDescriptor("Cuerpo","Coloración","Blanquecino"),
  		        new Modifier(0.0,1.0,0.1));
	    //-----------------------Structure No. 2---------------------
  		taxon4.addToDescription(new SSCharacterDescriptor("Manto","Coloración de numerosas manchas","Blanco con borde amarillento"),
  		        new Modifier(0.0,1.0,1.0));
  		taxon4.addToDescription(new SSCharacterDescriptor("Manto","Coloración línea exterior del borde","Rojizo"),
  		        new Modifier(0.0,1.0,1.0));
  		taxon4.addToDescription(new SSCharacterDescriptor("Manto","Visibilidad línea exterior del borde cuando muerto","Ninguna"),
  		        new Modifier(0.0,1.0,1.0));
  		taxon4.addToDescription(new SSCharacterDescriptor("Manto","Coloración del borde","Blanco"),
  		        new Modifier(0.0,1.0,1.0));
  		//-----------------------Structure No. 4---------------------
  		taxon4.addToDescription(new SSCharacterDescriptor("Rinoforos","Coloración de dos manchas localizadas en el centro","Blanco"),
  		        new Modifier(0.0,1.0,1.0));
  		taxon4.addToDescription(new SSCharacterDescriptor("Rinoforos","Visibilidad de dos manchas localizadas en el centro cuando muerto","Ninguna"),
  		        new Modifier(0.0,1.0,1.0));
  		taxon4.addToDescription(new SSCharacterDescriptor("Rinoforos","Coloración de la base","Blanco"),
  		        new Modifier(0.0,1.0,1.0));
  		taxon4.addToDescription(new SSCharacterDescriptor("Rinoforos","Coloración de los apices","Rojo purpura"),
  		        new Modifier(0.0,1.0,1.0));
  		taxon4.addToDescription(new SSCharacterDescriptor("Rinoforos","Coloración de los apices","Blanco"),
  		        new Modifier(0.0,1.0,0.1));
//-----------------------Taxon No. 5---------------------
	    taxon5 = new Taxon(TaxonomicRank.SPECIES, "Chromodoris kempfi");
	    //-----------------------Structure No. 1---------------------
  		taxon5.addToDescription(new SSCharacterDescriptor("Manto","Coloración del borde","Amarillo brillante"),
  		        new Modifier(1.0,1.0,1.0));
  		taxon5.addToDescription(new SSCharacterDescriptor("Manto","Coloración del borde","Crema blanquecino"),
  		        new Modifier(1.0,1.0,0.1));
  		taxon5.addToDescription(new SSCharacterDescriptor("Manto","Coloración del borde","Verde azulado"),
  		        new Modifier(1.0,1.0,1.0));
  		taxon5.addToDescription(new SSCharacterDescriptor("Manto","Coloración del borde","Verde grisaceo"),
  		        new Modifier(1.0,1.0,0.1));
  		taxon5.addToDescription(new SSCharacterDescriptor("Manto","Coloración de la banda dorsal continua","Blanco"),
  		        new Modifier(1.0,1.0,1.0));
  		taxon5.addToDescription(new SSCharacterDescriptor("Manto","Coloración de las manchas","Azul oscuro"),
  		        new Modifier(1.0,1.0,1.0));
  		taxon5.addToDescription(new SSCharacterDescriptor("Manto","Coloración de las manchas","Negro"),
  		        new Modifier(1.0,1.0,0.8));
  		taxon5.addToDescription(new SSCharacterDescriptor("Manto","Coloración de las manchas","Celeste"),
  		        new Modifier(1.0,1.0,0.5));
  		//-----------------------Structure No. 2---------------------
  		taxon5.addToDescription(new SSCharacterDescriptor("Rinoforos","Coloración","Purpura"),
  		        new Modifier(0.8,1.0,0.8));
  		taxon5.addToDescription(new SSCharacterDescriptor("Rinoforos","Coloración","De azul a purpura"),
  		        new Modifier(0.8,1.0,1.0));
  		taxon5.addToDescription(new SSCharacterDescriptor("Rinoforos","Coloración","Celeste pálido"),
  		        new Modifier(0.8,1.0,0.5));
  		//-----------------------Structure No. 3---------------------
	    taxon5.addToDescription(new SSCharacterDescriptor("Branquia","Coloración","Purpura"),
		        new Modifier(0.8,1.0,0.8));
	    taxon5.addToDescription(new SSCharacterDescriptor("Branquia","Coloración","De azul a purpura"),
		        new Modifier(0.8,1.0,1.0));
	    taxon5.addToDescription(new SSCharacterDescriptor("Branquia","Coloración","Celeste pálido"),
		        new Modifier(0.8,1.0,0.5));
	    
	    oracleIDSystem.getTaxonomy().addTaxon(taxon1, oracleIDSystem.getTaxonomy().getRootTaxon());
	    oracleIDSystem.getTaxonomy().addTaxon(taxon2, taxon1);
	    oracleIDSystem.getTaxonomy().addTaxon(taxon3, taxon2);
	    oracleIDSystem.getTaxonomy().addTaxon(taxon4, taxon2);
	    oracleIDSystem.getTaxonomy().addTaxon(taxon5, taxon2);

//-----------------------Taxon No. 6---------------------
	    taxon1 = new Taxon(TaxonomicRank.GENUS, "Cadlina");
	    //-----------------------Structure No. 1---------------------
  		taxon1.addToDescription(new SSCharacterDescriptor("Manto","Consistencia","Firme"),
  		        new Modifier(0.0,1.0,1.0));
  		taxon1.addToDescription(new SSCharacterDescriptor("Manto","Contextura","Cubierto de espiculas con pequenos tuberculos"),
  		        new Modifier(0.0,1.0,1.0));
  		//-----------------------Structure No. 2---------------------
  		taxon1.addToDescription(new SSCharacterDescriptor("Glándulas del manto","Tamaño","Grandes en proporcion al cuerpo"),
  		        new Modifier(0.0,1.0,1.0));
  		taxon1.addToDescription(new SSCharacterDescriptor("Glándulas del manto","Disposición",
  				"Rodean completamente al manto"), new Modifier(0.0,1.0,1.0));
  		//-----------------------Structure No. 3---------------------
	    taxon1.addToDescription(new SSCharacterDescriptor("Radula","Forma del diente central","Denticulado"),
	            new Modifier(0.0,1.0,1.0));
//-----------------------Taxon No. 7---------------------
	    taxon2 = new Taxon(TaxonomicRank.SPECIES, "Cadlina sparsa");
	    //-----------------------Structure No. 1---------------------
	    taxon2.addToDescription(new SSCharacterDescriptor("Cuerpo","Coloración del fondo","Salmón"),
  		        new Modifier(0.0,1.0,1.0));
	    taxon2.addToDescription(new SSCharacterDescriptor("Cuerpo","Coloración del fondo","Amarillento"),
  		        new Modifier(0.0,1.0,1.0));
	    taxon2.addToDescription(new RVCharacterDescriptor("Cuerpo","Longitud", new RangeValue(1.0, 3.6,
  				MeasuringUnit.CM)), new Modifier(0.0,1.0,1.0));
	    //-----------------------Structure No. 2---------------------
	    taxon2.addToDescription(new SSCharacterDescriptor("Manto","Coloracion de pequenas manchas laterales","Amarillo"),
  		        new Modifier(0.0,1.0,1.0));
	    //-----------------------Structure No. 3---------------------
	    taxon2.addToDescription(new SSCharacterDescriptor("Branquia","Coloración","Blancuzco"),
		        new Modifier(0.0,1.0,1.0));
	    //-----------------------Structure No. 4---------------------
  		taxon2.addToDescription(new SSCharacterDescriptor("Rinoforos","Coloración","Blancuzco"),
  		        new Modifier(0.0,1.0,1.0));
//-----------------------Taxon No. 8---------------------
	    taxon3 = new Taxon(TaxonomicRank.GENUS, "Hypselodoris");
	    //-----------------------Structure No. 1---------------------
	    taxon3.addToDescription(new SSCharacterDescriptor("Cuerpo","Forma","Abultado dorsalmente"),
  		        new Modifier(0.0,1.0,1.0));
	    //-----------------------Structure No. 2---------------------
	    taxon3.addToDescription(new SSCharacterDescriptor("Manto","Forma","Angosto"),
  		        new Modifier(0.0,1.0,1.0));
	    //-----------------------Structure No. 3---------------------
  		taxon3.addToDescription(new SSCharacterDescriptor("Glándulas del manto","Localización","A lo largo del manto"),
  		        new Modifier(0.0,1.0,1.0));
  		taxon3.addToDescription(new SSCharacterDescriptor("Glándulas del manto","Disposición",
  				"Rodean completamente al manto"), new Modifier(0.0,1.0,1.0));
  		//-----------------------Structure No. 4---------------------
	    taxon3.addToDescription(new SSCharacterDescriptor("Branquia","Disposición","Forman un circulo alrededor del ano"),
		        new Modifier(0.0,1.0,1.0));
	    //-----------------------Structure No. 5---------------------
	    taxon3.addToDescription(new SSCharacterDescriptor("Radula","Conformación","No tiene diente central"),
	            new Modifier(0.0,1.0,1.0));
//-----------------------Taxon No. 9---------------------
	    taxon4 = new Taxon(TaxonomicRank.SPECIES, "Hypselodoris agassizii");
	    //-----------------------Structure No. 1---------------------
	    taxon4.addToDescription(new SSCharacterDescriptor("Cuerpo","Coloración","Azul negruzco"),
  		        new Modifier(0.0,1.0,1.0));
	    taxon4.addToDescription(new SSCharacterDescriptor("Cuerpo","Coloración","Celeste claro"),
  		        new Modifier(0.0,1.0,0.9));
	    taxon4.addToDescription(new SSCharacterDescriptor("Cuerpo","Coloracion de numerosas manchas","Blanco y amarillo"),
  		        new Modifier(0.0,1.0,1.0));
	    taxon4.addToDescription(new RVCharacterDescriptor("Cuerpo","Longitud", new RangeValue(0.3, 0.38,
  				MeasuringUnit.CM)), new Modifier(0.0,1.0,1.0));
	    //-----------------------Structure No. 2---------------------
	    taxon4.addToDescription(new SSCharacterDescriptor("Manto","Coloración de las marcas ovaladas","Blanco"),
  		        new Modifier(0.0,1.0,1.0));
	    taxon4.addToDescription(new SSCharacterDescriptor("Manto","Coloración línea exterior del borde","Amarillo"),
  		        new Modifier(0.0,1.0,1.0));
	    taxon4.addToDescription(new SSCharacterDescriptor("Manto","Coloración línea exterior del borde","Amarillo pálido"),
  		        new Modifier(0.0,1.0,0.8));
	    taxon4.addToDescription(new SSCharacterDescriptor("Manto","Coloración linea media del borde","Azul marino a negro"),
  		        new Modifier(0.0,1.0,1.0));
	    taxon4.addToDescription(new SSCharacterDescriptor("Manto","Coloración línea interior del borde","Verde claro"),
  		        new Modifier(0.0,1.0,1.0));
	    //-----------------------Structure No. 3---------------------
	    taxon4.addToDescription(new SSCharacterDescriptor("Branquia","Coloración hojas branquiales","Crema amarillento"),
		        new Modifier(0.0,1.0,1.0));
	    taxon4.addToDescription(new SSCharacterDescriptor("Branquia","Coloración hojas branquiales","Blanco opaco"),
		        new Modifier(0.0,1.0,0.7));
	    taxon4.addToDescription(new SSCharacterDescriptor("Branquia","Coloración de los ápices","Azul oscuro a negro"),
		        new Modifier(0.0,1.0,1.0));
	    taxon4.addToDescription(new SSCharacterDescriptor("Branquia","Coloración de los ápices","Celeste claro"),
		        new Modifier(0.0,1.0,6.0));
	    //-----------------------Structure No. 4---------------------
  		taxon4.addToDescription(new SSCharacterDescriptor("Rinoforos","Coloración","Negro"),
  		        new Modifier(0.0,1.0,1.0));
  		taxon4.addToDescription(new SSCharacterDescriptor("Rinoforos","Coloración","Celeste claro"),
  		        new Modifier(0.0,1.0,0.6));
  		taxon4.addToDescription(new SSCharacterDescriptor("Rinoforos","Coloración del raquis","Amarillo"),
  		        new Modifier(0.0,1.0,1.0));
  		taxon4.addToDescription(new SSCharacterDescriptor("Rinoforos","Coloración del raquis","Blanco"),
  		        new Modifier(0.0,1.0,0.6));	
//-----------------------Taxon No. 10---------------------
	    taxon5 = new Taxon(TaxonomicRank.GENUS, "Glossodoris");
	    //-----------------------Structure No. 1---------------------
	    taxon5.addToDescription(new SSCharacterDescriptor("Cuerpo","Alto","Considerable"),
  		        new Modifier(0.0,1.0,1.0));
	    //-----------------------Structure No. 2---------------------
	    taxon5.addToDescription(new SSCharacterDescriptor("Manto","Forma","Plegado"),
  		        new Modifier(0.0,1.0,1.0));
	    //-----------------------Structure No. 3---------------------
  		taxon5.addToDescription(new SSCharacterDescriptor("Glándulas del manto","Disposición",
  				"Forman un circulo alrededor del ano"), new Modifier(0.0,1.0,1.0));
  		//-----------------------Structure No. 4---------------------
	    taxon5.addToDescription(new SSCharacterDescriptor("Branquia","Movimiento hojas branquiales durante desplazamiento","Ritmico"),
		        new Modifier(0.0,1.0,1.0));
	    //-----------------------Structure No. 4---------------------
	    taxon5.addToDescription(new SSCharacterDescriptor("Vagina","Forma","Angosta"),
		        new Modifier(0.0,1.0,1.0));   
//-----------------------Taxon No. 11---------------------
	    taxon6 = new Taxon(TaxonomicRank.SPECIES, "Glossodoris dalli");
	//-----------------------Structure No. 1---------------------
	    taxon6.addToDescription(new SSCharacterDescriptor("Cuerpo","Forma","Alargado"),
	            new Modifier(0.0,1.0,1.0));
	    taxon6.addToDescription(new SSCharacterDescriptor("Cuerpo","Forma","Ovalado"),
	            new Modifier(0.0,1.0,0.95));
	    taxon6.addToDescription(new SSCharacterDescriptor("Cuerpo","Consistencia","Rigida"),
	            new Modifier(0.0,1.0,1.0));
	    taxon6.addToDescription(new SSCharacterDescriptor("Cuerpo","Conformación","No tiene espiculas"),
	            new Modifier(0.0,1.0,1.0));
	    taxon6.addToDescription(new SSCharacterDescriptor("Cuerpo","Coloración","Blanco"),
	            new Modifier(0.0,1.0,1.0));
	    taxon6.addToDescription(new SSCharacterDescriptor("Cuerpo","Coloración","Gris claro"),
	            new Modifier(0.0,1.0,1.0));
	//-----------------------Structure No. 2---------------------
	    taxon6.addToDescription(new SSCharacterDescriptor("Manto","Forma numerosos tuberculos pequeños","Redondeado"),
	            new Modifier(0.0,1.0,1.0));
	    taxon6.addToDescription(new SSCharacterDescriptor("Manto","Coloración de manchas de diferentes tamaños y disposición irregular","Pardo"),
	            new Modifier(0.0,1.0,1.0));
	    taxon6.addToDescription(new SSCharacterDescriptor("Manto","Coloración de manchas de diferentes tamaños y disposición irregular","Negro"),
	            new Modifier(0.0,1.0,0.9));
	    taxon6.addToDescription(new SSCharacterDescriptor("Manto","Coloración de los puntos","Crema"),
	            new Modifier(0.0,1.0,1.0));
	    taxon6.addToDescription(new SSCharacterDescriptor("Manto","Coloración de los puntos","Naranja"),
	            new Modifier(0.0,1.0,0.9));
	    taxon6.addToDescription(new SSCharacterDescriptor("Manto","Coloración de los puntos","Rojo"),
	            new Modifier(0.0,1.0,0.9));
	    taxon6.addToDescription(new SSCharacterDescriptor("Manto","Coloración de banda submarginal rodeada de fina línea traslúcida","Rojo"),
	            new Modifier(0.0,1.0,1.0));
	    taxon6.addToDescription(new SSCharacterDescriptor("Manto","Coloración de banda que contiene hilera de glándulas","Blanco"),
	            new Modifier(0.0,1.0,1.0));
	//-----------------------Structure No. 3---------------------
	    taxon6.addToDescription(new SSCharacterDescriptor("Cola","Tamaño","Largo"),
	            new Modifier(0.0,1.0,1.0));
	    taxon6.addToDescription(new SSCharacterDescriptor("Cola","Disposición","Sobresale claramente por detras del notum"),
	            new Modifier(0.0,1.0,1.0));
	//-----------------------Structure No. 4---------------------
	    taxon6.addToDescription(new SSCharacterDescriptor("Branquia","Forma hojas branquiales","Unipinnada"),
	            new Modifier(0.0,1.0,1.0));
	    taxon6.addToDescription(new SSCharacterDescriptor("Branquia","Disposicion hojas branquiales","Forman un rco alrededor del ano"),
	            new Modifier(0.0,1.0,1.0));
	    taxon6.addToDescription(new SSCharacterDescriptor("Branquia","Movimiento Hojas branquiales durante desplazamiento","Ritmico"),
	            new Modifier(0.0,1.0,1.0));
	    taxon6.addToDescription(new SSCharacterDescriptor("Branquia","Tamano Hojas branquiales posteriores con respecto a las anteriores","Mas pequeñas"),
	            new Modifier(0.0,1.0,1.0));
	    taxon6.addToDescription(new SSCharacterDescriptor("Branquia","Coloración hojas branquiales","Blanco"),
	            new Modifier(0.0,1.0,1.0));
	    taxon6.addToDescription(new SSCharacterDescriptor("Branquia","Coloración de los ápices","Rojo"),
	            new Modifier(0.0,1.0,1.0));
	    taxon6.addToDescription(new SSCharacterDescriptor("Branquia","Coloración de los ápices","Blanco"),
	            new Modifier(0.0,1.0,0.9));
	//-----------------------Structure No. 5---------------------
	    taxon6.addToDescription(new SSCharacterDescriptor("Aparato genital","Conformación","Tiene glandula vestibular"),
	            new Modifier(0.0,1.0,1.0));      
//-----------------------Taxon No. 12---------------------
	    taxon7 = new Taxon(TaxonomicRank.SPECIES, "Glossodoris sedna");
	    //-----------------------Structure No. 1---------------------
	    taxon7.addToDescription(new SSCharacterDescriptor("Cuerpo","Forma","Alargado"),
  		        new Modifier(0.0,1.0,1.0));
	    taxon7.addToDescription(new SSCharacterDescriptor("Cuerpo","Forma","Ovalado"),
  		        new Modifier(0.0,1.0,0.95));
	    taxon7.addToDescription(new SSCharacterDescriptor("Cuerpo","Conformación","No tiene espiculas"),
  		        new Modifier(0.0,1.0,1.0));
	    taxon7.addToDescription(new SSCharacterDescriptor("Cuerpo","Coloración del fondo","Blanco hueso"),
  		        new Modifier(0.0,1.0,1.0));
	    taxon7.addToDescription(new SSCharacterDescriptor("Cuerpo","Contextura","Lisa"),
  		        new Modifier(0.0,1.0,1.0));
	    //-----------------------Structure No. 2---------------------
	    taxon7.addToDescription(new SSCharacterDescriptor("Manto","Forma","Fuertemente ondulado"),
  		        new Modifier(0.0,1.0,1.0));
	    taxon7.addToDescription(new SSCharacterDescriptor("Manto","Coloración del borde","Amarillo"),
  		        new Modifier(0.0,1.0,1.0));
	    taxon7.addToDescription(new SSCharacterDescriptor("Manto","Posición de la fila de glándulas subepiteliales " +
	    		"cuando fijado","A lo largo del borde"), new Modifier(0.0,1.0,1.0));
	    taxon7.addToDescription(new SSCharacterDescriptor("Manto","Coloración línea exterior del borde","Amarillo"),
  		        new Modifier(0.0,1.0,1.0));
	    taxon7.addToDescription(new SSCharacterDescriptor("Manto","Coloración linea media del borde","Rojo"),
  		        new Modifier(0.0,1.0,1.0));
	    taxon7.addToDescription(new SSCharacterDescriptor("Manto","Coloración línea interior del borde","Blanco"),
  		        new Modifier(0.0,1.0,1.0));
	    //-----------------------Structure No. 3---------------------
  		taxon7.addToDescription(new SSCharacterDescriptor("Cola","Disposición","Sobresale claramente por detrás del notum"),
  		        new Modifier(0.0,1.0,1.0));
  		taxon7.addToDescription(new SSCharacterDescriptor("Cola","Coloración línea exterior del borde","Amarillo"),
  		        new Modifier(0.0,1.0,1.0));
  		taxon7.addToDescription(new SSCharacterDescriptor("Cola","Coloración linea media del borde","Rojo"),
  		        new Modifier(0.0,1.0,1.0));
  		taxon7.addToDescription(new SSCharacterDescriptor("Cola","Coloración línea interior del borde","Blanco"),
  		        new Modifier(0.0,1.0,1.0));
  		//-----------------------Structure No. 4---------------------
	    taxon7.addToDescription(new SSCharacterDescriptor("Branquia","Forma hojas branquiales","Unipinnada"),
		        new Modifier(0.0,1.0,1.0));
	    taxon7.addToDescription(new SSCharacterDescriptor("Branquia","Disposición hojas branquiales","Forman un arco alrededor del ano"),
		        new Modifier(0.0,1.0,1.0));
	    taxon7.addToDescription(new SSCharacterDescriptor("Branquia","Movimiento hojas branquiales durante desplazamiento","Ritmico"),
		        new Modifier(0.0,1.0,1.0));	    
	    taxon7.addToDescription(new SSCharacterDescriptor("Branquia","Tamano hojas branquiales posteriores con respecto a las anteriores","Más pequeñas"),
		        new Modifier(0.0,1.0,1.0));
	    taxon7.addToDescription(new SSCharacterDescriptor("Branquia","Coloración hojas branquiales","Blanco hueso a traslúcido"),
		        new Modifier(0.0,1.0,1.0));
	    taxon7.addToDescription(new SSCharacterDescriptor("Branquia","Coloración de los ápices","Rojo"),
		        new Modifier(0.0,1.0,1.0));
	    taxon7.addToDescription(new SSCharacterDescriptor("Branquia","Coloración de los ápices","Blanco"),
		        new Modifier(0.0,1.0,1.0));
	    //-----------------------Structure No. 5---------------------
  		taxon7.addToDescription(new SSCharacterDescriptor("Rinoforos","Coloración","Blanco hueso a traslúcido"),
  		        new Modifier(0.0,1.0,1.0));
  		taxon7.addToDescription(new SSCharacterDescriptor("Rinoforos","Coloración de los ápices","Rojo"),
  		        new Modifier(0.0,1.0,1.0));
  		//-----------------------Structure No. 6---------------------
  		taxon7.addToDescription(new SSCharacterDescriptor("Aparato genital","conformacion",
  				"Tiene glándula vestibular"), new Modifier(0.0,1.0,1.0));
//-----------------------Taxon No. 13---------------------
	    taxon8 = new Taxon(TaxonomicRank.GENUS, "Mexichromis");
	//-----------------------Structure No. 1---------------------
        taxon8.addToDescription(new SSCharacterDescriptor("Glándulas del manto","Disposición","Posteriormente unas pocas y grandes"),
        		new Modifier(0.0,1.0,1.0));
        taxon8.addToDescription(new SSCharacterDescriptor("Glándulas del manto","Disposición","Lateralmente unas pocas y pequeñas"),
        		new Modifier(0.0,1.0,1.0));
    //-----------------------Structure No. 2---------------------
        taxon8.addToDescription(new SSCharacterDescriptor("Aparato Genital","Forma de la Glandula Vestibular","Ramificada"),
        		new Modifier(0.0,1.0,1.0));
    //-----------------------Structure No. 3---------------------
        taxon8.addToDescription(new SSCharacterDescriptor("Radula","Forma de los dientes","Bicuspidos y denticulados"),
        		new Modifier(0.0,1.0,1.0));   	    
	    
	    oracleIDSystem.getTaxonomy().addTaxon(taxon1, oracleIDSystem.getTaxonomy()
	    		.getTaxonFromLevelIndex("Chromodorididae"));
	    oracleIDSystem.getTaxonomy().addTaxon(taxon2, taxon1);
	    oracleIDSystem.getTaxonomy().addTaxon(taxon3, oracleIDSystem.getTaxonomy()
	    		.getTaxonFromLevelIndex("Chromodorididae"));
	    oracleIDSystem.getTaxonomy().addTaxon(taxon4, taxon3);
	    oracleIDSystem.getTaxonomy().addTaxon(taxon5, oracleIDSystem.getTaxonomy()
	    		.getTaxonFromLevelIndex("Chromodorididae"));
	    oracleIDSystem.getTaxonomy().addTaxon(taxon6, taxon5);
	    oracleIDSystem.getTaxonomy().addTaxon(taxon7, taxon5);
	    oracleIDSystem.getTaxonomy().addTaxon(taxon8, oracleIDSystem.getTaxonomy()
	    		.getTaxonFromLevelIndex("Chromodorididae"));
	    
//-----------------------Taxon No. 14---------------------
	    taxon1 = new Taxon(TaxonomicRank.SPECIES, "Mexichromis antonii");
	    //-----------------------Structure No. 1---------------------
	    taxon1.addToDescription(new SSCharacterDescriptor("Cuerpo","Forma","Alargado"),
  		        new Modifier(0.0,1.0,1.0));
	    taxon1.addToDescription(new SSCharacterDescriptor("Cuerpo","Conformación","No tiene tuberculos ni espiculas"),
  		        new Modifier(0.0,1.0,1.0));
	    taxon1.addToDescription(new SSCharacterDescriptor("Cuerpo","Coloración","Azul pálido"),
  		        new Modifier(0.0,1.0,1.0));
	    //-----------------------Structure No. 2---------------------
  		taxon1.addToDescription(new SSCharacterDescriptor("Rinoforos","Forma","Cónico multilaminado"),
  		        new Modifier(0.0,1.0,1.0));
  		taxon1.addToDescription(new SSCharacterDescriptor("Rinoforos","Coloración","Gris rojizo"),
  		        new Modifier(0.0,1.0,1.0));
  		taxon1.addToDescription(new SSCharacterDescriptor("Rinoforos","Coloración de los ápices","Negro"),
  		        new Modifier(0.0,1.0,1.0));
  		//-----------------------Structure No. 3---------------------
	    taxon1.addToDescription(new RVCharacterDescriptor("Branquia","Número hojas branquiales",new RangeValue(6, 7)),
		        new Modifier(0.0,1.0,1.0));
	    taxon1.addToDescription(new SSCharacterDescriptor("Branquia","Forma hojas branquiales","Unipinnada"),
		        new Modifier(0.0,1.0,1.0));
	    taxon1.addToDescription(new SSCharacterDescriptor("Branquia","Coloración","Gris rojizo"),
		        new Modifier(0.0,1.0,1.0));
	    taxon1.addToDescription(new SSCharacterDescriptor("Branquia","Coloración de los ápices","Negro"),
		        new Modifier(0.0,1.0,1.0));
	    //-----------------------Structure No. 4---------------------
	    taxon1.addToDescription(new SSCharacterDescriptor("Manto","Textura","Lisa"),
  		        new Modifier(0.0,1.0,1.0));
	    taxon1.addToDescription(new SSCharacterDescriptor("Manto","Forma del borde","Estrecho"),
  		        new Modifier(0.0,1.0,1.0));
	    taxon1.addToDescription(new SSCharacterDescriptor("Manto","Coloración línea exterior del borde","Amarillo naranja"),
  		        new Modifier(0.0,1.0,1.0));
	    taxon1.addToDescription(new SSCharacterDescriptor("Manto","Coloración línea interior del borde","Azul oscuro a negro"),
  		        new Modifier(0.0,1.0,1.0));
	    taxon1.addToDescription(new SSCharacterDescriptor("Manto","Coloración línea interrumpida en el centro","Blanco"),
  		        new Modifier(0.0,1.0,1.0));
	    taxon1.addToDescription(new SSCharacterDescriptor("Manto","Coloración área circundante de línea interrumpida en el centro",
	    		"Gris rojizo"), new Modifier(0.0,1.0,1.0));
	    taxon1.addToDescription(new SSCharacterDescriptor("Manto","Disposición línea interrumpida en el centro","Desde los rinoforos hacia la branquia"),
  		        new Modifier(0.0,1.0,1.0));	   
	    //-----------------------Structure No. 5---------------------
  		taxon1.addToDescription(new SSCharacterDescriptor("Cola","Coloración","Azul oscuro a negro"),
  		        new Modifier(0.0,1.0,1.0));
  		taxon1.addToDescription(new SSCharacterDescriptor("Cola","Coloración de la mancha en forma de V","Azul pálido"),
  		        new Modifier(0.0,1.0,1.0));
  		taxon1.addToDescription(new SSCharacterDescriptor("Cola","Coloración base de la mancha en forma de V","Naranja"),
  		        new Modifier(0.0,1.0,1.0));
//-----------------------Taxon No. 15---------------------
	    taxon2 = new Taxon(TaxonomicRank.FAMILY, "Aplysiidae");
	//-----------------------Structure No. 1---------------------
	    taxon2.addToDescription(new SSCharacterDescriptor("Cuerpo","Consistencia","Voluminoso"),
	            new Modifier(0.0,1.0,1.0));
	    taxon2.addToDescription(new SSCharacterDescriptor("Cuerpo","Textura de la superficie","Lisa"),
	            new Modifier(0.0,1.0,1.0));
	    taxon2.addToDescription(new SSCharacterDescriptor("Cuerpo","Conformacion","Tiene una concha interna"),
	            new Modifier(0.0,1.0,1.0));
	    taxon2.addToDescription(new SSCharacterDescriptor("Cuerpo","Coloracion","Verduzco"),
	            new Modifier(0.0,1.0,0.9));
	    taxon2.addToDescription(new SSCharacterDescriptor("Cuerpo","Coloracion","Grisaceo"),
	            new Modifier(0.0,1.0,0.85));
	    taxon2.addToDescription(new RVCharacterDescriptor("Cuerpo","Longitud",new RangeValue(0.5, 25.0,
	  			MeasuringUnit.CM)), new Modifier(0.0,1.0,1.0));
	//-----------------------Structure No. 2---------------------
        taxon2.addToDescription(new SSCharacterDescriptor("Parapodio","Grado de desarrollo","Mucho"),
        		new Modifier(0.0,1.0,1.0));
        taxon2.addToDescription(new SSCharacterDescriptor("Parapodio","Simetría","Simétrico o asimétrico"),
        		new Modifier(0.0,1.0,1.0));
        taxon2.addToDescription(new SSCharacterDescriptor("Parapodio","Tiene movilidad","Si"),
        		new Modifier(0.0,1.0,1.0));
        taxon2.addToDescription(new SSCharacterDescriptor("Parapodio","Tiene movilidad","No"),
        		new Modifier(0.0,1.0,0.0));
    //-----------------------Structure No. 3---------------------
        taxon2.addToDescription(new SSCharacterDescriptor("Pene","Conformacion","Tiene Espinas"),
        		new Modifier(0.0,1.0,1.0));
//-----------------------Taxon No. 16---------------------
	    taxon3 = new Taxon(TaxonomicRank.GENUS, "Dolabrifera");
	    //-----------------------Structure No. 1---------------------
	    taxon3.addToDescription(new SSCharacterDescriptor("Cuerpo","Forma","Aplanado dorsoventralmente"),
  		        new Modifier(0.0,1.0,1.0));	    
	    taxon3.addToDescription(new SSCharacterDescriptor("Cuerpo","Forma de las papilas","Ramificada"),
  		        new Modifier(0.0,1.0,1.0));
	    taxon3.addToDescription(new SSCharacterDescriptor("Cuerpo","Forma de las papilas","Simple"),
  		        new Modifier(0.0,1.0,1.0));
	    //-----------------------Structure No. 2---------------------
  		taxon3.addToDescription(new SSCharacterDescriptor("Pie","Ancho","Mismo que el cuerpo"),
  		        new Modifier(0.0,1.0,1.0));
  		//-----------------------Structure No. 3---------------------
  		taxon3.addToDescription(new SSCharacterDescriptor("Parapodio","Disposición","Asimétrica y reducida"),
  		        new Modifier(0.0,1.0,1.0));
  		taxon3.addToDescription(new RVCharacterDescriptor("Parapodio","Número de aberturas en la cavidad paleal",new RangeValue(2, 10)),
  		        new Modifier(0.0,1.0,1.0));
  		//-----------------------Structure No. 4---------------------
  		taxon3.addToDescription(new SSCharacterDescriptor("Concha interna","Aspecto","Lamina pequeña y frágil"),
  		        new Modifier(0.0,1.0,1.0));
  		taxon3.addToDescription(new SSCharacterDescriptor("Concha interna","Disposición","Más larga que ancha rodeada por el manto totalmente"),
  		        new Modifier(0.0,1.0,1.0));
//-----------------------Taxon No. 17---------------------
	    taxon4 = new Taxon(TaxonomicRank.GENUS, "Aplysiia");
	//-----------------------Structure No. 1---------------------
        taxon4.addToDescription(new SSCharacterDescriptor("Cuerpo","Forma","Alto alargado y contractil"),
        		new Modifier(0.0,1.0,1.0));
    //-----------------------Structure No. 2---------------------
        taxon4.addToDescription(new SSCharacterDescriptor("Concha interna","Aspecto","Delgada y traslúcida"),
        		new Modifier(0.0,1.0,1.0));
        taxon4.addToDescription(new SSCharacterDescriptor("Concha interna","Disposicion","Envuelta por un lóbulo del manto"),
        		new Modifier(0.0,1.0,1.0));
    //-----------------------Structure No. 3---------------------
        taxon4.addToDescription(new SSCharacterDescriptor("Parapodio","Simetría","Simétricos libres"),
        		new Modifier(0.0,1.0,1.0));
        taxon4.addToDescription(new SSCharacterDescriptor("Parapodio","Simetría","Fusionados"),
        		new Modifier(0.0,1.0,0.9));
    //-----------------------Structure No. 4---------------------
        taxon4.addToDescription(new SSCharacterDescriptor("Tentáculos orales","Forma","Anchos en la parte final"),
        		new Modifier(0.0,1.0,1.0));
    //-----------------------Structure No. 5---------------------
        taxon4.addToDescription(new SSCharacterDescriptor("Pie","Forma","Relativamente ancho"),
        		new Modifier(0.0,1.0,1.0));
        taxon4.addToDescription(new SSCharacterDescriptor("Pie","Prolongacion","Forma una cola corta"),
        		new Modifier(0.0,1.0,1.0));    	    
//-----------------------Taxon No. 18---------------------
	    taxon5 = new Taxon(TaxonomicRank.GENUS, "Petalifera");	    
	//-----------------------Structure No. 1---------------------
	    taxon5.addToDescription(new SSCharacterDescriptor("Cuerpo","Tamaño","Generalmente más pequeñas que las dolabriferas aplisias y notarchus"),
  		        new Modifier(0.0,1.0,1.0));   
	//-----------------------Structure No. 2---------------------
  		taxon5.addToDescription(new SSCharacterDescriptor("Parapodio","Simetría","Asimetricos y reducidos el derecho más desarrollado"),
  		        new Modifier(0.0,1.0,1.0));
  	//-----------------------Structure No. 3---------------------
  		taxon5.addToDescription(new SSCharacterDescriptor("Dorso","Contextura","Lisa con papilas más o menos ramificadas"),
  		        new Modifier(0.0,1.0,1.0));
//-----------------------Taxon No. 19---------------------
	    taxon6 = new Taxon(TaxonomicRank.GENUS, "Notarchus");
	//-----------------------Structure No. 1---------------------
        taxon6.addToDescription(new SSCharacterDescriptor("Cuerpo","Forma","Alto y globoso"),
        		new Modifier(0.0,1.0,1.0));
        taxon6.addToDescription(new SSCharacterDescriptor("Cuerpo","Forma de las papilas contractiles sobre el dorso","Simple y ramificada"),
        		new Modifier(0.0,1.0,1.0));
        taxon6.addToDescription(new SSCharacterDescriptor("Cuerpo","Conformación","No tiene concha interna"),
        		new Modifier(0.0,1.0,1.0));
    //-----------------------Structure No. 2---------------------
        taxon6.addToDescription(new SSCharacterDescriptor("Pie","Forma","Muy estrecho"),
        		new Modifier(0.0,1.0,1.0));
    //-----------------------Structure No. 3---------------------
        taxon6.addToDescription(new SSCharacterDescriptor("Parapodio","Disposición","Fusionados a lo largo de las margenes dejando pequeña abertura en la mitad anterior del cuerpo"),
        		new Modifier(0.0,1.0,1.0)); 
//-----------------------Taxon No. 20---------------------
	    taxon7 = new Taxon(TaxonomicRank.GENUS, "Phyllaplysia"); 
	//-----------------------Structure No. 1---------------------
	    taxon7.addToDescription(new SSCharacterDescriptor("Cuerpo","Forma","Aplanado dorsoventralmente"),
  		        new Modifier(0.0,1.0,1.0));	 
	    taxon7.addToDescription(new SSCharacterDescriptor("Cuerpo","Coloración del fondo","Verde"),
  		        new Modifier(0.0,1.0,1.0));
	    taxon7.addToDescription(new SSCharacterDescriptor("Cuerpo","Camuflaje","Diseño de manchas característico de las algas"),
  		        new Modifier(0.0,1.0,1.0));
	//-----------------------Structure No. 2---------------------
	    taxon7.addToDescription(new SSCharacterDescriptor("Concha interna","Forma","Laminar con el ápice central"),
  		        new Modifier(0.0,1.0,1.0));
	//-----------------------Structure No. 3---------------------
	    taxon7.addToDescription(new SSCharacterDescriptor("Manto","Disposición","No cubre dorsalmente a la concha ni los bordes"),
  		        new Modifier(0.0,1.0,1.0));
	    
	    oracleIDSystem.getTaxonomy().addTaxon(taxon1, oracleIDSystem.getTaxonomy()
	    		.getTaxonFromLevelIndex("Mexichromis"));
	    oracleIDSystem.getTaxonomy().addTaxon(taxon2, oracleIDSystem.getTaxonomy().getRootTaxon());
	    oracleIDSystem.getTaxonomy().addTaxon(taxon3, oracleIDSystem.getTaxonomy()
	    		.getTaxonFromLevelIndex("Aplysiidae"));
	    oracleIDSystem.getTaxonomy().addTaxon(taxon4, oracleIDSystem.getTaxonomy()
	    		.getTaxonFromLevelIndex("Aplysiidae"));
	    oracleIDSystem.getTaxonomy().addTaxon(taxon5, oracleIDSystem.getTaxonomy()
	    		.getTaxonFromLevelIndex("Aplysiidae"));
	    oracleIDSystem.getTaxonomy().addTaxon(taxon6, oracleIDSystem.getTaxonomy()
	    		.getTaxonFromLevelIndex("Aplysiidae"));
	    oracleIDSystem.getTaxonomy().addTaxon(taxon7, oracleIDSystem.getTaxonomy()
	    		.getTaxonFromLevelIndex("Aplysiidae"));

//-----------------------Taxon No. 21---------------------
	    taxon1 = new Taxon(TaxonomicRank.SPECIES, "Dolabrifera dolabrifera");
	//-----------------------Structure No. 1---------------------
	    taxon1.addToDescription(new SSCharacterDescriptor("Cuerpo","Tamaño","Más de 7 cm cuando vivos"),
  		        new Modifier(0.0,1.0,1.0));    
	    taxon1.addToDescription(new SSCharacterDescriptor("Cuerpo","Tamaño","Hasta 4 cm cuando muertos"),
  		        new Modifier(0.0,1.0,0.85));
	    taxon1.addToDescription(new SSCharacterDescriptor("Cuerpo","Forma","Deprimido dorsoventralmente"),
  		        new Modifier(0.0,1.0,1.0));
	    taxon1.addToDescription(new SSCharacterDescriptor("Cuerpo","Coloración del fondo","Verde oliva a marrón oscuro"),
  		        new Modifier(0.0,1.0,1.0));
	    taxon1.addToDescription(new SSCharacterDescriptor("Cuerpo","Coloración del fondo","Verde oliva pálido"),
  		        new Modifier(0.0,1.0,0.8));
	    taxon1.addToDescription(new SSCharacterDescriptor("Cuerpo","Coloración del fondo","Crema pálido"),
  		        new Modifier(0.0,1.0,0.7));
	//-----------------------Structure No. 2---------------------
  		taxon1.addToDescription(new SSCharacterDescriptor("Papilas","Coloración","Violeta rosaceo"),
  		        new Modifier(1.0,1.0,1.0));
  		taxon1.addToDescription(new SSCharacterDescriptor("Papilas","Coloración","Blanquecinas"),
  		        new Modifier(1.0,1.0,0.9));
  		taxon1.addToDescription(new SSCharacterDescriptor("Papilas","Coloración","Verde oliva pálido"),
  		        new Modifier(1.0,1.0,0.8));
  		taxon1.addToDescription(new SSCharacterDescriptor("Papilas","Coloración","Crema pálido"),
  		        new Modifier(1.0,1.0,0.8));
  	//-----------------------Structure No. 3---------------------
	    taxon1.addToDescription(new SSCharacterDescriptor("Concha interna","Disposición","Completamente rodeada por el manto"),
  		        new Modifier(0.0,1.0,1.0));	
	    taxon1.addToDescription(new SSCharacterDescriptor("Concha interna","Tamaño","Reducido"),
  		        new Modifier(0.0,1.0,1.0));
	//-----------------------Grouping Heuristic No. 1---------------------
  		taxon1.addToDescription(new SSHeuristicDescriptor("Lugar donde se encuentra", "Lugar donde se encuentra","Generalmente en las pozas de marea"),
  		        new Modifier(1.0,1.0,1.0));    
//-----------------------Taxon No. 22---------------------
	    taxon2 = new Taxon(TaxonomicRank.SPECIES, "Petalifera ramosa");
	//-----------------------Structure No. 1---------------------
	    taxon2.addToDescription(new SSCharacterDescriptor("Cuerpo","Coloración","Verde claro a intenso"),
  		        new Modifier(0.0,1.0,1.0));
	    taxon2.addToDescription(new SSCharacterDescriptor("Cuerpo","Coloración","Blanco traslúcido"),
  		        new Modifier(0.0,1.0,0.6));
	    taxon2.addToDescription(new SSCharacterDescriptor("Cuerpo","Coloración de los puntos","Rojizo"),
  		        new Modifier(0.0,1.0,1.0));
	//-----------------------Structure No. 2---------------------
  		taxon2.addToDescription(new SSCharacterDescriptor("Parapodio","Grado de desarrollo","Poco"),
  		        new Modifier(0.0,1.0,1.0));
  	//-----------------------Structure No. 3---------------------
  		taxon2.addToDescription(new SSCharacterDescriptor("Dorso","Contextura","Lisa"),
  		        new Modifier(0.0,1.0,1.0));	
  		taxon2.addToDescription(new SSCharacterDescriptor("Dorso","Contextura","Con papilas simples o ramificadas"),
  		        new Modifier(0.0,1.0,0.8));
  	//-----------------------Structure No. 4---------------------
  		taxon2.addToDescription(new SSCharacterDescriptor("Rinoforos","Contextura","Lisa"),
  		        new Modifier(0.0,1.0,1.0));
  		taxon2.addToDescription(new SSCharacterDescriptor("Rinoforos","Contextura","Con papilas simples o ramificadas"),
  		        new Modifier(0.0,1.0,0.8));
  	//-----------------------Structure No. 5---------------------
  		taxon2.addToDescription(new SSCharacterDescriptor("Tentáculos orales","Contextura","Lisa"),
  		        new Modifier(1.0,1.0,1.0));
  		taxon2.addToDescription(new SSCharacterDescriptor("Tentaculos orales","Contextura","Con papilas simples o ramificadas"),
  		        new Modifier(1.0,1.0,0.8));
  	//-----------------------Structure No. 6---------------------
  		taxon2.addToDescription(new SSCharacterDescriptor("Palpos labiales","Grado de desarrollo","Mucho"),
  		        new Modifier(1.0,1.0,1.0));
  		taxon2.addToDescription(new SSCharacterDescriptor("Palpos labiales","Sección en la que son visibles","En la parte dorsal"),
  		        new Modifier(1.0,1.0,1.0));
  	//-----------------------Structure No. 7---------------------
	    taxon2.addToDescription(new SSCharacterDescriptor("Concha interna","Disposición","Semicubiera"),
  		        new Modifier(0.0,1.0,1.0));	    
//-----------------------Taxon No. 23---------------------
	    taxon3 = new Taxon(TaxonomicRank.SPECIES, "Phyllaplysia engeli");
	//-----------------------Structure No. 1---------------------
	    taxon3.addToDescription(new SSCharacterDescriptor("Cuerpo","Forma","Estrecho y aplanado"),
  		        new Modifier(0.0,1.0,1.0));	    
	    taxon3.addToDescription(new SSCharacterDescriptor("Cuerpo","Coloración del fondo","Verde intenso con agrupaciones de pigmento verde"),
  		        new Modifier(0.0,1.0,1.0));    
	    taxon3.addToDescription(new SSCharacterDescriptor("Cuerpo","Coloración de los puntos","Rojizo"),
  		        new Modifier(0.0,1.0,1.0));
	//-----------------------Structure No. 2---------------------
  		taxon3.addToDescription(new SSCharacterDescriptor("Tentáculos orales","Forma","Largos y aplanados"),
  		        new Modifier(1.0,1.0,1.0));
  	//-----------------------Structure No. 3---------------------
  		taxon3.addToDescription(new SSCharacterDescriptor("Palpos labiales","Tamaño","Pequeño"),
  		        new Modifier(1.0,1.0,1.0));
  	//-----------------------Structure No. 4---------------------
  		taxon3.addToDescription(new SSCharacterDescriptor("Papilas","Tamaño","Pequeño"),
  		        new Modifier(1.0,1.0,1.0));
  		taxon3.addToDescription(new SSCharacterDescriptor("Papilas","Disposición","Simples y ramificadas"),
  		        new Modifier(1.0,1.0,1.0));
  		taxon3.addToDescription(new SSCharacterDescriptor("Papilas","Distribución","En el dorso y también sobre los rinoforos"),
  		        new Modifier(1.0,1.0,1.0)); 		
//-----------------------Taxon No. 24---------------------
	    taxon4 = new Taxon(TaxonomicRank.SPECIES, "Aplysiia dactylomela");
	//-----------------------Structure No. 1---------------------
	    taxon4.addToDescription(new SSCharacterDescriptor("Cuerpo","Forma","Alto y voluminoso"),
  		        new Modifier(0.0,1.0,1.0));	    
	    taxon4.addToDescription(new SSCharacterDescriptor("Cuerpo","Coloración del fondo","Cafesuzco a verde oliva con anillos u ocelos de pigmento oscuro"),
  		        new Modifier(0.0,1.0,1.0));
	//-----------------------Structure No. 2---------------------
  		taxon3.addToDescription(new SSCharacterDescriptor("Pie","Forma","Estrecho y prolongado en cola larga"),
  		        new Modifier(0.0,1.0,1.0));	    
//-----------------------Taxon No. 25---------------------
	    taxon5 = new Taxon(TaxonomicRank.SPECIES, "Notarchus punctatus");
	//-----------------------Structure No. 1---------------------
	    taxon5.addToDescription(new SSCharacterDescriptor("Cuerpo","Coloración del fondo","Marron claro a beige con pequenas manchas de pigmento oscuro"),
  		        new Modifier(0.0,1.0,1.0));
	//-----------------------Structure No. 2---------------------
	    taxon5.addToDescription(new SSCharacterDescriptor("Papilas","Tamaño","Pequeño"),
  		        new Modifier(0.0,1.0,1.0));    
	    taxon5.addToDescription(new SSCharacterDescriptor("Papilas","Distribución","En todo el cuerpo a excepción de los rinoforos y tentáculos orales"),
  		        new Modifier(0.0,1.0,1.0));
	    taxon5.addToDescription(new SSCharacterDescriptor("Papilas","Forma","Simples y ramificadas"),
  		        new Modifier(0.0,1.0,1.0));
	//-----------------------Structure No. 3---------------------
  		taxon5.addToDescription(new SSCharacterDescriptor("Pie","Forma","Estrecho y alargado"),
  		        new Modifier(0.0,1.0,1.0)); 
  		
	    oracleIDSystem.getTaxonomy().addTaxon(taxon1, oracleIDSystem.getTaxonomy()
	    		.getTaxonFromLevelIndex("Dolabrifera"));
	    oracleIDSystem.getTaxonomy().addTaxon(taxon2, oracleIDSystem.getTaxonomy()
	    		.getTaxonFromLevelIndex("Petalifera"));
	    oracleIDSystem.getTaxonomy().addTaxon(taxon3, oracleIDSystem.getTaxonomy()
	    		.getTaxonFromLevelIndex("Phyllaplysia"));
	    oracleIDSystem.getTaxonomy().addTaxon(taxon4, oracleIDSystem.getTaxonomy()
	    		.getTaxonFromLevelIndex("Aplysiia"));
	    oracleIDSystem.getTaxonomy().addTaxon(taxon5, oracleIDSystem.getTaxonomy()
	    		.getTaxonFromLevelIndex("Notarchus"));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

}
