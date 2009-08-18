/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ontology.taxonomy.test;

import java.util.ArrayList;
import java.util.List;
import ontology.common.Attribute;
import ontology.common.GroupingHeuristic;
import ontology.common.Structure;
import ontology.taxonomy.GroupingHeuristicIndex;
import ontology.taxonomy.StructureIndex;
import ontology.taxonomy.Taxon;
import ontology.taxonomy.TaxonomicRank;
import ontology.values.RangeValue;
import ontology.values.SingleValue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pabloq
 */
public class TaxonTest {
    Taxon rootTaxon,taxon1,taxon2,taxon3,taxon4,taxon5,taxon6,taxon7,taxon8;

    public TaxonTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        rootTaxon = new Taxon();
        rootTaxon.setName(null);
        rootTaxon.setLevel(TaxonomicRank.ROOT);
        taxon1 = new Taxon();
        taxon2 = new Taxon();
        taxon3 = new Taxon();
        taxon4 = new Taxon();
        taxon5 = new Taxon();
        taxon6 = new Taxon();
        taxon7 = new Taxon();
        taxon8 = new Taxon();
/*" +++++++++++++++++++++++++++++++++++++ Taxon No. 1 ++++++++++++++++++++++++++ "
t1 := Taxon new.
t1 name: #Chromodorididae.
t1 level: #family.*/
        taxon1.setName("Chromodorididae");
        taxon1.setLevel(TaxonomicRank.FAMILY);
/*
"-----------------------Structure No. 1---------------------"
s := Structure new.
s name: #cuerpo.
s weight: 1.0.
a := Smalltalk.Attribute newWithOneLevel.
a name: #forma.
vd := ValueDescriptor new.
vd value: #alargado.
vd weight: 0.8.
(a values) valueDescriptorWithUniqueValue: vd for: Smalltalk.Attribute oneLevel.
vd := ValueDescriptor new.
vd value: #ovalado.
vd weight: 0.1.
(a values) valueDescriptorWithUniqueValue: vd for: Smalltalk.Attribute oneLevel.
s attribute: a.
a := Smalltalk.Attribute newWithOneLevel.
a name: #longitud.
vd := ValueDescriptor new.
vd setAsRangeWithUnit: #cm.
vd lowerBound: 0.3.
vd upperBound: 4.
(a values) valueDescriptorWithUniqueValue: vd for: Smalltalk.Attribute oneLevel.
s attribute: a.
a := Smalltalk.Attribute newWithOneLevel.
a name: #conformacion.
vd := ValueDescriptor new.
vd value: #tiene_cerata.
vd weight: 1.0.
(a values) valueDescriptorWithUniqueValue: vd for: Smalltalk.Attribute oneLevel.
s attribute: a.
t1 SAVdescription: s.
*/

        StructureIndex aStructureIndex = new StructureIndex();
        Structure aStructure = new Structure();
        aStructure.setName("cuerpo");
        aStructure.setWeight(1.0);

        Attribute aAttribute = new Attribute();
        aAttribute.setName("forma");

        SingleValue aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("alargado");
        aSingleDescriptor.setWeight(0.8);
        aAttribute.getValues().addValueDescriptorWithUniqueValue(aSingleDescriptor, TaxonomicRank.FAMILY);

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("ovalado");
        aSingleDescriptor.setWeight(0.1);
        aAttribute.getValues().addValueDescriptorWithUniqueValue(aSingleDescriptor, TaxonomicRank.FAMILY);

        aStructure.addAttribute(aAttribute);
        aStructureIndex.add(aStructure);

        aAttribute = new Attribute();
        aAttribute.setName("longitud");

        RangeValue aRangeDescriptor = new RangeValue();
        aRangeDescriptor.setLowerBound(0.3);
        aRangeDescriptor.setUpperBound(4.0);
        aAttribute.getValues().addValueDescriptorWithUniqueValue(aRangeDescriptor, TaxonomicRank.FAMILY);

        aStructure.addAttribute(aAttribute);
        aStructureIndex.add(aStructure);

        aAttribute = new Attribute();
        aAttribute.setName("conformacion");
        
        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("tiene_cerata");
        aSingleDescriptor.setWeight(1.0);
        aAttribute.getValues().addValueDescriptorWithUniqueValue(aSingleDescriptor, TaxonomicRank.FAMILY);

        aStructure.addAttribute(aAttribute);
        aStructureIndex.add(aStructure);
        
        taxon1.setDescription(aStructureIndex);


/*
"-----------------------Structure No. 2---------------------"
s := Structure new.
s name: #pie.
s weight: 0.8.
a := Smalltalk.Attribute newWithOneLevel.
a name: #disposicion.
vd := ValueDescriptor new.
vd value: #sobresale_al_manto.
vd weight: 0.8.
(a values) valueDescriptorWithUniqueValue: vd for: Smalltalk.Attribute oneLevel.
s attribute: a.
a := Smalltalk.Attribute newWithOneLevel.
a name: #coloracion.
vd := ValueDescriptor new.
vd value: #blanquecino.
vd weight: 0.7.
(a values) valueDescriptorWithUniqueValue: vd for: Smalltalk.Attribute oneLevel.
vd := ValueDescriptor new.
vd value: #crema.
vd weight: 0.7.
(a values) valueDescriptorWithUniqueValue: vd for: Smalltalk.Attribute oneLevel.
vd := ValueDescriptor new.
vd value: #gris_oscuro_casi_negro.
vd weight: 0.2.
(a values) valueDescriptorWithUniqueValue: vd for: Smalltalk.Attribute oneLevel.
s attribute: a.
t1 SAVdescription: s.*/

        aStructureIndex = new StructureIndex();
        aStructure = new Structure();
        aStructure.setName("pie");
        aStructure.setWeight(0.8);

        aAttribute = new Attribute();
        aAttribute.setName("disposicion");

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("sobresale_al_manto");
        aSingleDescriptor.setWeight(0.8);
        aAttribute.getValues().addValueDescriptorWithUniqueValue(aSingleDescriptor, TaxonomicRank.FAMILY);

        aStructure.addAttribute(aAttribute);
        aStructureIndex.add(aStructure);

        aAttribute = new Attribute();
        aAttribute.setName("coloracion");

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("blanquecino");
        aSingleDescriptor.setWeight(0.7);
        aAttribute.getValues().addValueDescriptorWithUniqueValue(aSingleDescriptor, TaxonomicRank.FAMILY);
        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("crema");
        aSingleDescriptor.setWeight(0.7);
        aAttribute.getValues().addValueDescriptorWithUniqueValue(aSingleDescriptor, TaxonomicRank.FAMILY);
        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("gris_oscuro_casi_negro");
        aSingleDescriptor.setWeight(0.2);
        aAttribute.getValues().addValueDescriptorWithUniqueValue(aSingleDescriptor, TaxonomicRank.FAMILY);

        aStructure.addAttribute(aAttribute);
        aStructureIndex.add(aStructure);

        taxon1.setDescription(aStructureIndex);


/*
"-----------------------Structure No. 3---------------------"
s := Structure new.
s name: #branquia.
a := Smalltalk.Attribute newWithOneLevel.
a name: #posicion_durante_desplazamiento.
vd := ValueDescriptor new.
vd value: #hacia_atras.
vd weight: 0.8.
(a values) valueDescriptorWithUniqueValue: vd for: Smalltalk.Attribute oneLevel.
s attribute: a.
a := Smalltalk.Attribute newWithOneLevel.
a name: #posicion_del_ano_con_respecto_a_la_branquia.
vd := ValueDescriptor new.
vd value: #en_el_centro.
vd weight: 0.8.
(a values) valueDescriptorWithUniqueValue: vd for: Smalltalk.Attribute oneLevel.
s attribute: a.
a := Smalltalk.Attribute newWithOneLevel.
a name: #numero_hojas_branquiales.
vd := ValueDescriptor new.
vd setAsRange.
vd lowerBound: 6.
vd upperBound: 9.
(a values) valueDescriptorWithUniqueValue: vd for: Smalltalk.Attribute oneLevel.
s attribute: a.
a := Smalltalk.Attribute newWithOneLevel.
a name: #forma_hojas_branquiales.
vd := ValueDescriptor new.
vd value: #bipinnada.
vd weight: 0.6.
(a values) valueDescriptorWithUniqueValue: vd for: Smalltalk.Attribute oneLevel.
vd := ValueDescriptor new.
vd value: #tripinnada.
vd weight: 0.4.
(a values) valueDescriptorWithUniqueValue: vd for: Smalltalk.Attribute oneLevel.
s attribute: a.
t1 SAVdescription: s.*/
        aStructureIndex = new StructureIndex();
        aStructure = new Structure();
        aStructure.setName("branquia");

        aAttribute = new Attribute();
        aAttribute.setName("posicion_durante_desplazamiento");

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("hacia_atras");
        aSingleDescriptor.setWeight(0.8);
        aAttribute.getValues().addValueDescriptorWithUniqueValue(aSingleDescriptor, TaxonomicRank.FAMILY);

        aStructure.addAttribute(aAttribute);
        aStructureIndex.add(aStructure);

        aAttribute = new Attribute();
        aAttribute.setName("posicion_del_ano_con_respecto_a_la_branquia");

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("en_el_centro");
        aSingleDescriptor.setWeight(0.8);
        aAttribute.getValues().addValueDescriptorWithUniqueValue(aSingleDescriptor, TaxonomicRank.FAMILY);

        aStructure.addAttribute(aAttribute);
        aStructureIndex.add(aStructure);

        aAttribute = new Attribute();
        aAttribute.setName("numero_hojas_branquiales");

        aRangeDescriptor = new RangeValue();
        aRangeDescriptor.setLowerBound(6.0);
        aRangeDescriptor.setUpperBound(9.0);
        aAttribute.getValues().addValueDescriptorWithUniqueValue(aRangeDescriptor, TaxonomicRank.FAMILY);

        aStructure.addAttribute(aAttribute);
        aStructureIndex.add(aStructure);

        aAttribute = new Attribute();
        aAttribute.setName("forma_hojas_branquiales");

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("bipinnada");
        aSingleDescriptor.setWeight(0.6);
        aAttribute.getValues().addValueDescriptorWithUniqueValue(aSingleDescriptor, TaxonomicRank.FAMILY);

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("tripinnada");
        aSingleDescriptor.setWeight(0.4);
        aAttribute.getValues().addValueDescriptorWithUniqueValue(aSingleDescriptor, TaxonomicRank.FAMILY);

        aStructure.addAttribute(aAttribute);
        aStructureIndex.add(aStructure);

        taxon1.setDescription(aStructureIndex);

/*"-----------------------Structure No. 4---------------------"
s := Structure new.
s name: #manto.
a := Smalltalk.Attribute newWithOneLevel.
a name: #textura.
vd := ValueDescriptor new.
vd value: #lisa.
vd weight: 0.8.
(a values) valueDescriptorWithUniqueValue: vd for: Smalltalk.Attribute oneLevel.
vd := ValueDescriptor new.
vd value: #con_tuberculos.
vd weight: 0.4.
(a values) valueDescriptorWithUniqueValue: vd for: Smalltalk.Attribute oneLevel.
s attribute: a.
a := Smalltalk.Attribute newWithOneLevel.
a name: #forma_del_borde.
vd := ValueDescriptor new.
vd value: #ondulado.
vd weight: 0.3.
(a values) valueDescriptorWithUniqueValue: vd for: Smalltalk.Attribute oneLevel.
s attribute: a.
a := Smalltalk.Attribute newWithOneLevel.
a name: #textura_del_borde.
vd := ValueDescriptor new.
vd value: #lisa.
vd weight: 0.7.
(a values) valueDescriptorWithUniqueValue: vd for: Smalltalk.Attribute oneLevel.
s attribute: a.
t1 SAVdescription: s.*/
        aStructureIndex = new StructureIndex();
        aStructure = new Structure();
        aStructure.setName("manto");

        aAttribute = new Attribute();
        aAttribute.setName("textura");

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("lisa");
        aSingleDescriptor.setWeight(0.8);
        aAttribute.getValues().addValueDescriptorWithUniqueValue(aSingleDescriptor, TaxonomicRank.FAMILY);

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("con_tuberculos");
        aSingleDescriptor.setWeight(0.4);
        aAttribute.getValues().addValueDescriptorWithUniqueValue(aSingleDescriptor, TaxonomicRank.FAMILY);

        aStructure.addAttribute(aAttribute);
        aStructureIndex.add(aStructure);
        
        aAttribute = new Attribute();
        aAttribute.setName("forma_del_borde");

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("ondulado");
        aSingleDescriptor.setWeight(0.3);
        aAttribute.getValues().addValueDescriptorWithUniqueValue(aSingleDescriptor, TaxonomicRank.FAMILY);

        aStructure.addAttribute(aAttribute);
        aStructureIndex.add(aStructure);

        aAttribute = new Attribute();
        aAttribute.setName("textura_del_borde");

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("lisa");
        aSingleDescriptor.setWeight(0.7);
        aAttribute.getValues().addValueDescriptorWithUniqueValue(aSingleDescriptor, TaxonomicRank.FAMILY);

        aStructure.addAttribute(aAttribute);
        aStructureIndex.add(aStructure);

        taxon1.setDescription(aStructureIndex);

/*"-----------------------Structure No. 5---------------------"
s := Structure new.
s name: #glandulas_del_manto.
a := Smalltalk.Attribute newWithOneLevel.
a name: #posicion.
vd := ValueDescriptor new.
vd value: #delante.
vd weight: 0.2.
(a values) valueDescriptorWithUniqueValue: vd for: Smalltalk.Attribute oneLevel.
vd := ValueDescriptor new.
vd value: #delante_y_atras.
vd weight: 0.2.
(a values) valueDescriptorWithUniqueValue: vd for: Smalltalk.Attribute oneLevel.
vd := ValueDescriptor new.
vd value: #alrededor_del_manto.
vd weight: 0.4.
(a values) valueDescriptorWithUniqueValue: vd for: Smalltalk.Attribute oneLevel.
s attribute: a.
t1 SAVdescription: s.*/
        aStructureIndex = new StructureIndex();
        aStructure = new Structure();
        aStructure.setName("glandulas_del_manto");

        aAttribute = new Attribute();
        aAttribute.setName("posicion");

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("delante");
        aSingleDescriptor.setWeight(0.2);
        aAttribute.getValues().addValueDescriptorWithUniqueValue(aSingleDescriptor, TaxonomicRank.FAMILY);

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("delante_y_atras");
        aSingleDescriptor.setWeight(0.2);
        aAttribute.getValues().addValueDescriptorWithUniqueValue(aSingleDescriptor, TaxonomicRank.FAMILY);

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("alrededor_del_manto");
        aSingleDescriptor.setWeight(0.4);
        aAttribute.getValues().addValueDescriptorWithUniqueValue(aSingleDescriptor, TaxonomicRank.FAMILY);

        aStructure.addAttribute(aAttribute);
        aStructureIndex.add(aStructure);

        taxon1.setDescription(aStructureIndex);

        
/*"-----------------------Structure No. 6---------------------"
s := Structure new.
s name: #rinoforos.
a := Smalltalk.Attribute newWithOneLevel.
a name: #forma.
vd := ValueDescriptor new.
vd value: #laminados.
vd weight: 1.0.
(a values) valueDescriptorWithUniqueValue: vd for: Smalltalk.Attribute oneLevel.
s attribute: a.
a := Smalltalk.Attribute newWithOneLevel.
a name: #numero_de_laminillas.
vd := ValueDescriptor new.
vd setAsRange.
vd lowerBound: 6.
vd upperBound: 20.
(a values) valueDescriptorWithUniqueValue: vd for: Smalltalk.Attribute oneLevel.
s attribute: a.
t1 SAVdescription: s.*/
        aStructureIndex = new StructureIndex();
        aStructure = new Structure();
        aStructure.setName("rinoforos");

        aAttribute = new Attribute();
        aAttribute.setName("forma");

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("laminados");
        aSingleDescriptor.setWeight(1.0);
        aAttribute.getValues().addValueDescriptorWithUniqueValue(aSingleDescriptor, TaxonomicRank.FAMILY);

        aStructure.addAttribute(aAttribute);
        aStructureIndex.add(aStructure);

        aAttribute = new Attribute();
        aAttribute.setName("numero_de_laminillas");

        aRangeDescriptor = new RangeValue();
        aRangeDescriptor.setLowerBound(6.0);
        aRangeDescriptor.setUpperBound(20.0);
        aAttribute.getValues().addValueDescriptorWithUniqueValue(aRangeDescriptor, TaxonomicRank.FAMILY);

        aStructure.addAttribute(aAttribute);
        aStructureIndex.add(aStructure);

        taxon1.setDescription(aStructureIndex);

/*"-----------------------Structure No. 7---------------------"
s := Structure new.
s name: #tentaculos_orales.
a := Smalltalk.Attribute newWithOneLevel.
a name: #contextura.
vd := ValueDescriptor new.
vd value: #macizo.
vd weight: 0.7.
(a values) valueDescriptorWithUniqueValue: vd for: Smalltalk.Attribute oneLevel.
vd := ValueDescriptor new.
vd value: #surcado.
vd weight: 0.2.
(a values) valueDescriptorWithUniqueValue: vd for: Smalltalk.Attribute oneLevel.
s attribute: a.
t1 SAVdescription: s.*/

        aStructureIndex = new StructureIndex();
        aStructure = new Structure();
        aStructure.setName("tentaculos_orales");

        aAttribute = new Attribute();
        aAttribute.setName("contextura");

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("macizo");
        aSingleDescriptor.setWeight(0.7);
        aAttribute.getValues().addValueDescriptorWithUniqueValue(aSingleDescriptor, TaxonomicRank.FAMILY);

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("surcado");
        aSingleDescriptor.setWeight(0.2);
        aAttribute.getValues().addValueDescriptorWithUniqueValue(aSingleDescriptor, TaxonomicRank.FAMILY);

        aStructure.addAttribute(aAttribute);
        aStructureIndex.add(aStructure);

        taxon1.setDescription(aStructureIndex);
        
/*"-----------------------Grouping Heuristic No. 1---------------------"
gh := GroupingHeuristic newWithOneLevel.
gh name: #alimenatcion.
gh weight: 1.0.
vd := ValueDescriptor new.
vd value: #esponjas.
vd weight: 1.0.
(gh values) valueDescriptorWithUniqueValue: vd for: GroupingHeuristic oneLevel.
t1 GHdescription: gh.*/
        GroupingHeuristicIndex aGroupingHeuristicIndex = new GroupingHeuristicIndex();
        GroupingHeuristic aGroupingHeuristic = new GroupingHeuristic();
        aGroupingHeuristic.setName("alimenatcion");
        aGroupingHeuristic.setWeight(1.0);
    
        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("esponjas");
        aSingleDescriptor.setWeight(1.0);
        aGroupingHeuristic.getValues().addValueDescriptorWithUniqueValue(aSingleDescriptor, TaxonomicRank.FAMILY);

        aGroupingHeuristicIndex.addGroupingHeuristic(aGroupingHeuristic);
        taxon1.setGHDescription(aGroupingHeuristicIndex);
/*"-----------------------Grouping Heuristic No. 2---------------------"
gh := GroupingHeuristic newWithOneLevel.
gh name: #profundidad_donde_se_encuentra.
gh weight: 1.0.
vd := ValueDescriptor new.
vd setAsRangeWithUnit: #m.
vd lowerBound: 0.
vd upperBound: 20.
(gh values) valueDescriptorWithUniqueValue: vd for: GroupingHeuristic oneLevel.
t1 GHdescription: gh.*/
        aGroupingHeuristicIndex = new GroupingHeuristicIndex();
        aGroupingHeuristic = new GroupingHeuristic();
        aGroupingHeuristic.setName("profundidad_donde_se_encuentra");
        aGroupingHeuristic.setWeight(1.0);


        aRangeDescriptor = new RangeValue();
        aRangeDescriptor.setLowerBound(0.0);
        aRangeDescriptor.setUpperBound(20.0);
        aGroupingHeuristic.getValues().addValueDescriptorWithUniqueValue(aRangeDescriptor, TaxonomicRank.FAMILY);

        aGroupingHeuristicIndex.addGroupingHeuristic(aGroupingHeuristic);
        taxon1.setGHDescription(aGroupingHeuristicIndex);
/*"-----------------------Grouping Heuristic No. 3---------------------"
gh := GroupingHeuristic newWithOneLevel.
gh name: #medio_de_preservacion_tenido.
gh weight: 1.0.
vd := ValueDescriptor new.
vd value: #azul_marino.
vd weight: 1.0.
(gh values) valueDescriptorWithUniqueValue: vd for: GroupingHeuristic oneLevel.
vd := ValueDescriptor new.
vd value: #celeste.
vd weight: 0.8.
(gh values) valueDescriptorWithUniqueValue: vd for: GroupingHeuristic oneLevel.
vd := ValueDescriptor new.
vd value: #amarillento.
vd weight: 0.2.
(gh values) valueDescriptorWithUniqueValue: vd for: GroupingHeuristic oneLevel.
t1 GHdescription: gh.
*/
        aGroupingHeuristicIndex = new GroupingHeuristicIndex();
        aGroupingHeuristic = new GroupingHeuristic();
        aGroupingHeuristic.setName("medio_de_preservacion_tenido");
        aGroupingHeuristic.setWeight(1.0);

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("azul_marino");
        aSingleDescriptor.setWeight(1.0);
        aGroupingHeuristic.getValues().addValueDescriptorWithUniqueValue(aSingleDescriptor, TaxonomicRank.FAMILY);

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("celeste");
        aSingleDescriptor.setWeight(1.0);
        aGroupingHeuristic.getValues().addValueDescriptorWithUniqueValue(aSingleDescriptor, TaxonomicRank.FAMILY);

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("amarillento");
        aSingleDescriptor.setWeight(0.2);
        aGroupingHeuristic.getValues().addValueDescriptorWithUniqueValue(aSingleDescriptor, TaxonomicRank.FAMILY);

        aGroupingHeuristicIndex.addGroupingHeuristic(aGroupingHeuristic);
        taxon1.setGHDescription(aGroupingHeuristicIndex);

/*" +++++++++++++++++++++++++++++++++++++ Taxon No. 2 +++++++++++++++++++++++++++++++++++++ "
t2 := Taxon new.
t2 name: #Chromodoris.
t2 level: #genus.*/

        taxon2.setName("Chromodoris");
        taxon2.setLevel(TaxonomicRank.GENUS);
/*"-----------------------Structure No. 1---------------------"
s := Structure new.
s name: #cuerpo.
s weight: 1.0.
a := Smalltalk.Attribute newWithOneLevel.
a name: #posicion_de_la_banda_dorsal_continua.
vd := ValueDescriptor new.
vd value: #centro.
vd weight: 1.0.
(a values) valueDescriptorWithUniqueValue: vd for: Smalltalk.Attribute oneLevel.
s attribute: a.
a := Smalltalk.Attribute newWithOneLevel.
a name: #coloracion.
vd := ValueDescriptor new.
vd value: #brillante_azul_rojo_blanco_anaranjado_purpura.
vd weight: 1.0.
(a values) valueDescriptorWithUniqueValue: vd for: Smalltalk.Attribute oneLevel.
s attribute: a.
a := Smalltalk.Attribute newWithOneLevel.
a name: #forma_ventral.
vd := ValueDescriptor new.
vd value: #aplanado.
vd weight: 0.6.
(a values) valueDescriptorWithUniqueValue: vd for: Smalltalk.Attribute oneLevel.
s attribute: a.
t2 SAVdescription: s.*/
        aStructureIndex = new StructureIndex();
        aStructure = new Structure();
        aStructure.setName("cuerpo");
        aStructure.setWeight(1.0);

        aAttribute = new Attribute();
        aAttribute.setName("posicion_de_la_banda_dorsal_continua");

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("centro");
        aSingleDescriptor.setWeight(1.0);
        aAttribute.getValues().addValueDescriptorWithUniqueValue(aSingleDescriptor, TaxonomicRank.GENUS);

        aStructure.addAttribute(aAttribute);
        aStructureIndex.add(aStructure);

        aAttribute = new Attribute();
        aAttribute.setName("coloracion");

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("brillante_azul_rojo_blanco_anaranjado_purpura");
        aSingleDescriptor.setWeight(1.0);
        aAttribute.getValues().addValueDescriptorWithUniqueValue(aSingleDescriptor, TaxonomicRank.GENUS);

        aStructure.addAttribute(aAttribute);
        aStructureIndex.add(aStructure);

        aAttribute = new Attribute();
        aAttribute.setName("forma_ventral");

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("aplanado");
        aSingleDescriptor.setWeight(0.6);
        aAttribute.getValues().addValueDescriptorWithUniqueValue(aSingleDescriptor, TaxonomicRank.GENUS);

        aStructure.addAttribute(aAttribute);
        aStructureIndex.add(aStructure);

        taxon2.setDescription(aStructureIndex);

/*"-----------------------Structure No. 2---------------------"
s := Structure new.
s name: #manto.
s weight: 0.8.
a := Smalltalk.Attribute newWithOneLevel.
a name: #forma.
vd := ValueDescriptor new.
vd value: #elongado_y_ovalado.
vd weight: 0.7.
(a values) valueDescriptorWithUniqueValue: vd for: Smalltalk.Attribute oneLevel.
s attribute: a.
a := Smalltalk.Attribute newWithOneLevel.
a name: #contextura.
vd := ValueDescriptor new.
vd value: #con_glandulas.
vd weight: 0.8.
(a values) valueDescriptorWithUniqueValue: vd for: Smalltalk.Attribute oneLevel.
s attribute: a.
t2 SAVdescription: s.*/
        aStructureIndex = new StructureIndex();
        aStructure = new Structure();
        aStructure.setName("manto");
        aStructure.setWeight(0.8);

        aAttribute = new Attribute();
        aAttribute.setName("forma");

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("elongado_y_ovalado");
        aSingleDescriptor.setWeight(0.7);
        aAttribute.getValues().addValueDescriptorWithUniqueValue(aSingleDescriptor, TaxonomicRank.GENUS);

        aStructure.addAttribute(aAttribute);
        aStructureIndex.add(aStructure);

        aAttribute = new Attribute();
        aAttribute.setName("contextura");

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("con_glandulas");
        aSingleDescriptor.setWeight(0.8);
        aAttribute.getValues().addValueDescriptorWithUniqueValue(aSingleDescriptor, TaxonomicRank.GENUS);

        aStructure.addAttribute(aAttribute);
        aStructureIndex.add(aStructure);

        taxon2.setDescription(aStructureIndex);

/*"-----------------------Structure No. 3---------------------"
s := Structure new.
s name: #radula.
s weight: 0.3.
a := Smalltalk.Attribute newWithOneLevel.
a name: #forma_de_los_dientes.
vd := ValueDescriptor new.
vd value: #denticulados.
vd weight: 1.0.
(a values) valueDescriptorWithUniqueValue: vd for: Smalltalk.Attribute oneLevel.
s attribute: a.
a := Smalltalk.Attribute newWithOneLevel.
a name: #posicion_del_diente_mas_conspicuo.
vd := ValueDescriptor new.
vd value: #centro.
vd weight: 0.5.
(a values) valueDescriptorWithUniqueValue: vd for: Smalltalk.Attribute oneLevel.
s attribute: a.
t2 SAVdescription: s.
*/
        aStructureIndex = new StructureIndex();
        aStructure = new Structure();
        aStructure.setName("radula");
        aStructure.setWeight(0.3);

        aAttribute = new Attribute();
        aAttribute.setName("forma_de_los_dientes");

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("denticulados");
        aSingleDescriptor.setWeight(1.0);
        aAttribute.getValues().addValueDescriptorWithUniqueValue(aSingleDescriptor, TaxonomicRank.GENUS);

        aStructure.addAttribute(aAttribute);
        aStructureIndex.add(aStructure);

        aAttribute = new Attribute();
        aAttribute.setName("posicion_del_diente_mas_conspicuo");

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("centro");
        aSingleDescriptor.setWeight(0.5);
        aAttribute.getValues().addValueDescriptorWithUniqueValue(aSingleDescriptor, TaxonomicRank.GENUS);

        aStructure.addAttribute(aAttribute);
        aStructureIndex.add(aStructure);

        taxon2.setDescription(aStructureIndex);






        taxon3.setName("Chromodoris_sphoni");
        taxon3.setLevel(TaxonomicRank.SPECIES);
        taxon4.setName("Chromodoris_clenchi");
        taxon4.setLevel(TaxonomicRank.SPECIES);
        taxon5.setName("Chromodoris_kempfi");
        taxon5.setLevel(TaxonomicRank.SPECIES);
        taxon6.setName("Cadlina");
        taxon6.setLevel(TaxonomicRank.GENUS);
        taxon7.setName("Cadlina_sparsa");
        taxon7.setLevel(TaxonomicRank.SPECIES);
        taxon8.setName("Hypselodoris");
        taxon8.setLevel(TaxonomicRank.GENUS);


    }

    @After
    public void tearDown() {
    }


    /**
     * Test of setPredecessor method, of class Taxon.
     */
    @Test
    public void testSetPredecessor() {
        taxon1.setPredecessor(taxon2);
        
        assertEquals(taxon2, taxon1.getPredecessor());

        assertTrue(taxon2.getSuccessors().contains(taxon1));

    }

    /**
     * Test of setSuccessors method, of class Taxon.
     */
    @Test
    public void testSetSuccessors() {
        System.out.println("setSuccessors");
        List<Taxon> sucessors = new ArrayList<Taxon>();
        sucessors.add(taxon1);

        taxon2.setSuccessors(sucessors);
    }

//    /**
//     * Test of addSuccessor method, of class Taxon.
//     */
//    @Test
//    public void testAddSuccessor() {
//        System.out.println("addSuccessor");
//        Taxon successor = null;
//        Taxon instance = new Taxon();
//        instance.addSuccessor(successor);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of isSuccessorOf method, of class Taxon.
     */
    @Test
    public void testIsSuccessorOf() {
        System.out.println("isSuccessorOf");
        taxon1.setPredecessor(rootTaxon);
        taxon2.setPredecessor(taxon1);
        taxon6.setPredecessor(taxon1);
        taxon8.setPredecessor(taxon1);

        taxon3.setPredecessor(taxon2);
        taxon4.setPredecessor(taxon6);
        taxon5.setPredecessor(taxon6);
        taxon7.setPredecessor(taxon8);

        /*ROOT=>
         *      1=>
         *          2=>
         *              3
         *          6=>
         *              4
         *              5
         *          8=>
         *              7
         */
        
        assertTrue(taxon4.isSuccessorOf(taxon1));
        assertTrue(taxon5.isSuccessorOf(taxon6));
        assertTrue(taxon8.isSuccessorOf(taxon1));

        assertFalse(taxon7.isSuccessorOf(taxon6));
        assertFalse(taxon3.isSuccessorOf(taxon8));
        assertFalse(taxon7.isSuccessorOf(taxon2));
        
    }

    /**
     * Test of unlinkFromTheHierarchy method, of class Taxon.
     */
    @Test
    public void testUnlinkFromTheHierarchy() {
        System.out.println("unlinkFromTheHierarchy");
        taxon1.setPredecessor(rootTaxon);
        taxon2.setPredecessor(taxon1);
        taxon6.setPredecessor(taxon1);
        taxon8.setPredecessor(taxon1);

        taxon3.setPredecessor(taxon2);
        taxon4.setPredecessor(taxon6);
        taxon5.setPredecessor(taxon6);
        taxon7.setPredecessor(taxon8);

        /*ROOT=>
         *      1=>
         *          2=>
         *              3
         *          6=>
         *              4
         *              5
         *          8=>
         *              7
         *
         * Test unlink 6
         *
         */
        
         /*
          * Before
          */

         assertEquals(3,taxon1.getSuccessors().size());
         assertTrue(taxon1.getSuccessors().contains(taxon6));
         assertTrue(taxon6.getSuccessors().contains(taxon4));
         assertTrue(taxon6.getSuccessors().contains(taxon5));
         assertEquals(taxon1,taxon6.getPredecessor());
        
         /*
          * after
          */
         
         taxon6.unlinkFromTheHierarchy();
        
         assertEquals(2,taxon1.getSuccessors().size());
         assertFalse(taxon1.getSuccessors().contains(taxon6));
         assertTrue(taxon6.getSuccessors().contains(taxon4));
         assertTrue(taxon6.getSuccessors().contains(taxon5));
         assertEquals(2,taxon6.getSuccessors().size());
         assertEquals(null,taxon6.getPredecessor());

    }

    /**
     * Test of isOKDirectLink method, of class Taxon.
     */
    @Test
    public void testIsOKDirectLink() {
        System.out.println("isOKDirectLink");
        taxon1.setPredecessor(rootTaxon);
        taxon2.setPredecessor(taxon1);
        taxon6.setPredecessor(taxon1);
        taxon8.setPredecessor(taxon1);

        taxon3.setPredecessor(taxon2);
        taxon4.setPredecessor(taxon6);
        taxon5.setPredecessor(taxon6);
        taxon7.setPredecessor(taxon8);

        /*ROOT=>
         *      1=>
         *          2=>
         *              3
         *          6=>
         *              4
         *              5
         *          8=>
         *              7
         */
        assertTrue(taxon6.isOKDirectLink(taxon1));
        assertTrue(taxon2.isOKDirectLink(taxon1));
        assertTrue(taxon8.isOKDirectLink(taxon1));
        assertTrue(taxon4.isOKDirectLink(taxon6));
        assertTrue(taxon5.isOKDirectLink(taxon6));
        assertTrue(taxon7.isOKDirectLink(taxon6));
        /*
         * Same levels or more than 1 level of distance
         *
         */
        assertFalse(taxon3.isOKDirectLink(taxon1));
        assertFalse(taxon4.isOKDirectLink(taxon1));
        assertFalse(taxon5.isOKDirectLink(taxon1));
        assertFalse(taxon7.isOKDirectLink(taxon1));

        assertFalse(taxon8.isOKDirectLink(taxon6));
        assertFalse(taxon2.isOKDirectLink(taxon6));
        assertFalse(taxon6.isOKDirectLink(taxon8));
    }

    /**
     * Test of compareTo method, of class Taxon.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");

        /*
         * Test same
         */
        assertEquals(0,taxon1.compareTo(taxon1));
        assertEquals(0,taxon2.compareTo(taxon2));
        assertEquals(0,taxon3.compareTo(taxon3));
        /*
         * Test bigger
         */
        assertEquals(15,taxon2.compareTo(taxon1));
        assertEquals(15,taxon3.compareTo(taxon1));
        /*
         * Test smaller
         */
        assertEquals(-15,taxon1.compareTo(taxon2));
        assertEquals(-15,taxon1.compareTo(taxon3));
    }

}