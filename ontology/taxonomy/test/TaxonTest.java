/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ontology.taxonomy.test;

import java.util.List;
import ontology.common.Attribute;
import ontology.common.Structure;
import ontology.taxonomy.Taxon;
import ontology.taxonomy.TaxonomicRank;
import ontology.values.SingleDescriptor;
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
    Taxon taxon1,taxon2,taxon3,taxon4,taxon5,taxon6,taxon7,taxon8;

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
        taxon1 = new Taxon();
/*" +++++++++++++++++++++++++++++++++++++ Taxon No. 1 ++++++++++++++++++++++++++ "
t1 := Taxon new.
t1 name: #Chromodorididae.
t1 level: #family.*/
        taxon1.setName("Chromodorididae");
        taxon1.setLevel(TaxonomicRank.FAMILY);
        Structure aStructure = new Structure();
        aStructure.setName("cuerpo");
        aStructure.setWeight(1.0);
        Attribute aAttribute = new Attribute();

        SingleDescriptor aSingleDescriptor = new SingleDescriptor();
        aSingleDescriptor.setValue("alargado");
        aSingleDescriptor.setWeight(0.8);
        aAttribute.getValues().addValueDescriptorWithUniqueValue(aSingleDescriptor, TaxonomicRank.ROOT);
        aSingleDescriptor = new SingleDescriptor();
        aSingleDescriptor.setValue("ovalado");
        aSingleDescriptor.setWeight(0.1);
        aAttribute.getValues().addValueDescriptorWithUniqueValue(aSingleDescriptor, TaxonomicRank.ROOT);
        aStructure.addAttribute(aAttribute);
        
        
        
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
t1 SAVdescription: s.
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



t1 SAVdescription: s.



"-----------------------Structure No. 4---------------------"

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



t1 SAVdescription: s.



"-----------------------Structure No. 5---------------------"

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



t1 SAVdescription: s.



"-----------------------Structure No. 6---------------------"

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



t1 SAVdescription: s.



"-----------------------Structure No. 7---------------------"

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



t1 SAVdescription: s.



"-----------------------Grouping Heuristic No. 1---------------------"

gh := GroupingHeuristic newWithOneLevel.

gh name: #alimenatcion.

gh weight: 1.0.

vd := ValueDescriptor new.

vd value: #esponjas.

vd weight: 1.0.

(gh values) valueDescriptorWithUniqueValue: vd for: GroupingHeuristic oneLevel.

t1 GHdescription: gh.



"-----------------------Grouping Heuristic No. 2---------------------"

gh := GroupingHeuristic newWithOneLevel.

gh name: #profundidad_donde_se_encuentra.

gh weight: 1.0.

vd := ValueDescriptor new.

vd setAsRangeWithUnit: #m.

vd lowerBound: 0.

vd upperBound: 20.

(gh values) valueDescriptorWithUniqueValue: vd for: GroupingHeuristic oneLevel.

t1 GHdescription: gh.



"-----------------------Grouping Heuristic No. 3---------------------"

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
    }

    @After
    public void tearDown() {
    }


    /**
     * Test of setPredecessor method, of class Taxon.
     */
    @Test
    public void testSetPredecessor() {
        System.out.println("setPredecessor");
        Taxon predecessor = null;
        Taxon instance = new Taxon();
        instance.setPredecessor(predecessor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSuccessors method, of class Taxon.
     */
    @Test
    public void testSetSuccessors() {
        System.out.println("setSuccessors");
        List<Taxon> sucessors = null;
        Taxon instance = new Taxon();
        instance.setSuccessors(sucessors);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addSuccessor method, of class Taxon.
     */
    @Test
    public void testAddSuccessor() {
        System.out.println("addSuccessor");
        Taxon successor = null;
        Taxon instance = new Taxon();
        instance.addSuccessor(successor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isSuccessorOf method, of class Taxon.
     */
    @Test
    public void testIsSuccessorOf() {
        System.out.println("isSuccessorOf");
        Taxon aTaxon = null;
        Taxon instance = new Taxon();
        boolean expResult = false;
        boolean result = instance.isSuccessorOf(aTaxon);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unlinkFromTheHierarchy method, of class Taxon.
     */
    @Test
    public void testUnlinkFromTheHierarchy() {
        System.out.println("unlinkFromTheHierarchy");
        Taxon instance = new Taxon();
        instance.unlinkFromTheHierarchy();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isOKDirectLink method, of class Taxon.
     */
    @Test
    public void testIsOKDirectLink() {
        System.out.println("isOKDirectLink");
        Taxon aParentTaxon = null;
        Taxon instance = new Taxon();
        boolean expResult = false;
        boolean result = instance.isOKDirectLink(aParentTaxon);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compareTo method, of class Taxon.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Taxon aTaxon = null;
        Taxon instance = new Taxon();
        int expResult = 0;
        int result = instance.compareTo(aTaxon);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}