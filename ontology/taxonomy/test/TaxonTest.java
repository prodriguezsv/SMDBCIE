/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ontology.taxonomy.test;

import java.util.ArrayList;
import java.util.List;
import ontology.common.Attribute;
import ontology.common.CharacterDescriptor;
import ontology.common.GroupingHeuristic;
import ontology.common.HeuristicDescriptor;
import ontology.common.Structure;
import ontology.taxonomy.Taxon;
import ontology.taxonomy.TaxonomicRank;
import ontology.values.RangeValue;
import ontology.values.SingleValue;
import ontology.values.Values;
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

        CharacterDescriptor aCDescriptor = new CharacterDescriptor();
        aCDescriptor.setStructure("cuerpo");
        aCDescriptor.setAttribute("forma");

        SingleValue aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("alargado");

        Values aValues = new Values();

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("ovalado");

        aValues.addValueDescriptor(aSingleDescriptor, TaxonomicRank.FAMILY);
        aCDescriptor.setValue(aValues);

        taxon1.addToDescription(aCDescriptor);
        
        aCDescriptor = new CharacterDescriptor();
        aCDescriptor.setStructure("cuerpo");
        aCDescriptor.setAttribute("longitud");

        aValues = new Values();

        RangeValue aRangeDescriptor = new RangeValue();
        aRangeDescriptor.setLowerBound(0.3);
        aRangeDescriptor.setUpperBound(4.0);

        aValues.addValueDescriptor(aRangeDescriptor, TaxonomicRank.FAMILY);
        aCDescriptor.setValue(aValues);

        taxon1.addToDescription(aCDescriptor);

        aCDescriptor = new CharacterDescriptor();
        aCDescriptor.setStructure("cuerpo");
        aCDescriptor.setAttribute("conformacion");

        aValues = new Values();

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("tiene_cerata");

        aValues.addValueDescriptor(aSingleDescriptor, TaxonomicRank.FAMILY);
        aCDescriptor.setValue(aValues);

        taxon1.addToDescription(aCDescriptor);
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

        aCDescriptor = new CharacterDescriptor();
        aCDescriptor.setStructure("pie");
        aCDescriptor.setAttribute("disposicion");

        aValues = new Values();

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("sobresale_al_manto");

        aValues.addValueDescriptor(aSingleDescriptor, TaxonomicRank.FAMILY);
        aCDescriptor.setValue(aValues);

        taxon1.addToDescription(aCDescriptor);

        aCDescriptor = new CharacterDescriptor();
        aCDescriptor.setStructure("pie");
        aCDescriptor.setAttribute("coloracion");

        aValues = new Values();

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("blanquecino");

        aValues.addValueDescriptor(aSingleDescriptor, TaxonomicRank.FAMILY);

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("crema");

        aValues.addValueDescriptor(aSingleDescriptor, TaxonomicRank.FAMILY);

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("gris_oscuro_casi_negro");

        aValues.addValueDescriptor(aSingleDescriptor, TaxonomicRank.FAMILY);

        aCDescriptor.setValue(aValues);

        taxon1.addToDescription(aCDescriptor);

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

        aCDescriptor = new CharacterDescriptor();
        aCDescriptor.setStructure("branquia");
        aCDescriptor.setAttribute("posicion_durante_desplazamiento");

        aValues = new Values();

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("hacia_atras");

        aValues.addValueDescriptor(aSingleDescriptor, TaxonomicRank.FAMILY);

        aCDescriptor.setValue(aValues);

        taxon1.addToDescription(aCDescriptor);

        aCDescriptor = new CharacterDescriptor();
        aCDescriptor.setStructure("branquia");
        aCDescriptor.setAttribute("posicion_del_ano_con_respecto_a_la_branquia");

        aValues = new Values();

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("en_el_centro");

        aValues.addValueDescriptor(aSingleDescriptor, TaxonomicRank.FAMILY);

        aCDescriptor.setValue(aValues);

        taxon1.addToDescription(aCDescriptor);

        aCDescriptor = new CharacterDescriptor();
        aCDescriptor.setStructure("branquia");
        aCDescriptor.setAttribute("numero_hojas_branquiales");

        aValues = new Values();

        aRangeDescriptor = new RangeValue();
        aRangeDescriptor.setLowerBound(6.0);
        aRangeDescriptor.setUpperBound(9.0);

        aValues.addValueDescriptor(aRangeDescriptor, TaxonomicRank.FAMILY);

        aCDescriptor.setValue(aValues);

        taxon1.addToDescription(aCDescriptor);

        aCDescriptor = new CharacterDescriptor();
        aCDescriptor.setStructure("branquia");
        aCDescriptor.setAttribute("numero_hojas_branquiales");

        aValues = new Values();

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("bipinnada");

        aValues.addValueDescriptor(aSingleDescriptor, TaxonomicRank.FAMILY);

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("tripinnada");

        aValues.addValueDescriptor(aSingleDescriptor, TaxonomicRank.FAMILY);

        aCDescriptor.setValue(aValues);

        taxon1.addToDescription(aCDescriptor);
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

        aCDescriptor = new CharacterDescriptor();
        aCDescriptor.setStructure("manto");
        aCDescriptor.setAttribute("textura");

        aValues = new Values();

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("lisa");

        aValues.addValueDescriptor(aSingleDescriptor, TaxonomicRank.FAMILY);

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("con_tuberculos");

        aValues.addValueDescriptor(aSingleDescriptor, TaxonomicRank.FAMILY);

        aCDescriptor.setValue(aValues);

        taxon1.addToDescription(aCDescriptor);

        aCDescriptor = new CharacterDescriptor();
        aCDescriptor.setStructure("manto");
        aCDescriptor.setAttribute("forma_del_borde");

        aValues = new Values();

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("ondulado");

        aValues.addValueDescriptor(aSingleDescriptor, TaxonomicRank.FAMILY);

        aCDescriptor.setValue(aValues);

        taxon1.addToDescription(aCDescriptor);

        aCDescriptor = new CharacterDescriptor();
        aCDescriptor.setStructure("manto");
        aCDescriptor.setAttribute("textura_del_borde");

        aValues = new Values();

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("lisa");

        aValues.addValueDescriptor(aSingleDescriptor, TaxonomicRank.FAMILY);

        aCDescriptor.setValue(aValues);

        taxon1.addToDescription(aCDescriptor);
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

        aCDescriptor = new CharacterDescriptor();
        aCDescriptor.setStructure("glandulas_del_manto");
        aCDescriptor.setAttribute("posicion");

        aValues = new Values();

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("delante");

        aValues.addValueDescriptor(aSingleDescriptor, TaxonomicRank.FAMILY);

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("delante_y_atras");

        aValues.addValueDescriptor(aSingleDescriptor, TaxonomicRank.FAMILY);

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("alrededor_del_manto");

        aValues.addValueDescriptor(aSingleDescriptor, TaxonomicRank.FAMILY);

        aCDescriptor.setValue(aValues);

        taxon1.addToDescription(aCDescriptor);
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

        aCDescriptor = new CharacterDescriptor();
        aCDescriptor.setStructure("rinoforos");
        aCDescriptor.setAttribute("forma");

        aValues = new Values();

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("laminados");

        aValues.addValueDescriptor(aSingleDescriptor, TaxonomicRank.FAMILY);

        aCDescriptor.setValue(aValues);

        taxon1.addToDescription(aCDescriptor);

        aCDescriptor = new CharacterDescriptor();
        aCDescriptor.setStructure("rinoforos");
        aCDescriptor.setAttribute("numero_de_laminillas");

        aValues = new Values();

        aRangeDescriptor = new RangeValue();
        aRangeDescriptor.setLowerBound(6.0);
        aRangeDescriptor.setUpperBound(20.0);

        aValues.addValueDescriptor(aRangeDescriptor, TaxonomicRank.FAMILY);

        aCDescriptor.setValue(aValues);

        taxon1.addToDescription(aCDescriptor);

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
        aCDescriptor = new CharacterDescriptor();
        aCDescriptor.setStructure("tentaculos_orales");
        aCDescriptor.setAttribute("contextura");

        aValues = new Values();

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("macizo");

        aValues.addValueDescriptor(aSingleDescriptor, TaxonomicRank.FAMILY);
        
        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("macizo");

        aValues.addValueDescriptor(aSingleDescriptor, TaxonomicRank.FAMILY);

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("surcado");

        aValues.addValueDescriptor(aSingleDescriptor, TaxonomicRank.FAMILY);

        aCDescriptor.setValue(aValues);

        taxon1.addToDescription(aCDescriptor);

/*"-----------------------Grouping Heuristic No. 1---------------------"
gh := GroupingHeuristic newWithOneLevel.
gh name: #alimenatcion.
gh weight: 1.0.
vd := ValueDescriptor new.
vd value: #esponjas.
vd weight: 1.0.
(gh values) valueDescriptorWithUniqueValue: vd for: GroupingHeuristic oneLevel.
t1 GHdescription: gh.*/
        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("esponjas");

        taxon1.addToDescription(new HeuristicDescriptor ("","alimenatcion",aSingleDescriptor));
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
        aRangeDescriptor = new RangeValue();
        aRangeDescriptor.setLowerBound(0.0);
        aRangeDescriptor.setUpperBound(20.0);

        taxon1.addToDescription(new HeuristicDescriptor ("","profundidad_donde_se_encuentra",aRangeDescriptor));
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
        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("azul_marino");

        taxon1.addToDescription(new HeuristicDescriptor ("","medio_de_preservacion_tenido",aRangeDescriptor));

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("celeste");

        taxon1.addToDescription(new HeuristicDescriptor ("","medio_de_preservacion_tenido",aRangeDescriptor));

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("amarillento");

        taxon1.addToDescription(new HeuristicDescriptor ("","medio_de_preservacion_tenido",aRangeDescriptor));
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
        aCDescriptor = new CharacterDescriptor();
        aCDescriptor.setStructure("cuerpo");
        aCDescriptor.setAttribute("posicion_de_la_banda_dorsal_continua");

        aValues = new Values();

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("centro");

        aValues.addValueDescriptor(aSingleDescriptor, TaxonomicRank.GENUS);

        aCDescriptor.setValue(aValues);

        taxon2.addToDescription(aCDescriptor);

        aCDescriptor = new CharacterDescriptor();
        aCDescriptor.setStructure("cuerpo");
        aCDescriptor.setAttribute("coloracion");

        aValues = new Values();

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("brillante_azul_rojo_blanco_anaranjado_purpura");

        aValues.addValueDescriptor(aSingleDescriptor, TaxonomicRank.GENUS);

        aCDescriptor.setValue(aValues);

        taxon2.addToDescription(aCDescriptor);

        aCDescriptor = new CharacterDescriptor();
        aCDescriptor.setStructure("cuerpo");
        aCDescriptor.setAttribute("forma_ventral");

        aValues = new Values();

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("aplanado");

        aValues.addValueDescriptor(aSingleDescriptor, TaxonomicRank.GENUS);

        aCDescriptor.setValue(aValues);

        taxon2.addToDescription(aCDescriptor);
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

        aCDescriptor = new CharacterDescriptor();
        aCDescriptor.setStructure("manto");
        aCDescriptor.setAttribute("forma");

        aValues = new Values();

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("elongado_y_ovalado");

        aValues.addValueDescriptor(aSingleDescriptor, TaxonomicRank.GENUS);

        aCDescriptor.setValue(aValues);

        taxon2.addToDescription(aCDescriptor);

        aCDescriptor = new CharacterDescriptor();
        aCDescriptor.setStructure("manto");
        aCDescriptor.setAttribute("contextura");

        aValues = new Values();

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("con_glandulas");

        aValues.addValueDescriptor(aSingleDescriptor, TaxonomicRank.GENUS);

        aCDescriptor.setValue(aValues);

        taxon2.addToDescription(aCDescriptor);
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

        aCDescriptor = new CharacterDescriptor();
        aCDescriptor.setStructure("radula");
        aCDescriptor.setAttribute("forma_de_los_dientes");

        aValues = new Values();

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("denticulados");

        aValues.addValueDescriptor(aSingleDescriptor, TaxonomicRank.GENUS);

        aCDescriptor.setValue(aValues);

        taxon2.addToDescription(aCDescriptor);

        aCDescriptor = new CharacterDescriptor();
        aCDescriptor.setStructure("radula");
        aCDescriptor.setAttribute("posicion_del_diente_mas_conspicuo");

        aValues = new Values();

        aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("centro");

        aValues.addValueDescriptor(aSingleDescriptor, TaxonomicRank.GENUS);

        aCDescriptor.setValue(aValues);

        taxon2.addToDescription(aCDescriptor);


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