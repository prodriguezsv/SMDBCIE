/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ontology.taxonomy.test;

import ontology.common.Attribute;
import ontology.common.Structure;
import ontology.taxonomy.StructureIndex;
import ontology.taxonomy.Taxon;
import ontology.taxonomy.TaxonomicRank;
import ontology.values.MeasuringUnit;
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
public class StructureIndexTest {
    Taxon rootTaxon,taxon1,taxon2,taxon3;
    StructureIndex aStructureIndex;

    public StructureIndexTest() {
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
        taxon2 = new Taxon();
        taxon3 = new Taxon();


        taxon1.setName("Chromodorididae");
        taxon1.setLevel(TaxonomicRank.FAMILY);
        rootTaxon = new Taxon();
        rootTaxon.setName(null);
        rootTaxon.setLevel(TaxonomicRank.ROOT);

        aStructureIndex = new StructureIndex();
        Structure aStructure = new Structure();
        aStructure.setName("cuerpo");
        aStructure.setWeight(1.0);

        Attribute aAttribute = new Attribute();
        aAttribute.setName("forma");

        SingleValue aSingleDescriptor = new SingleValue();
        aSingleDescriptor.setValue("alargado");
        aSingleDescriptor.setWeight(0.8);
        aAttribute.getValues().addValueDescriptorWithUniqueValue(aSingleDescriptor, TaxonomicRank.FAMILY);

        aStructure.addAttribute(aAttribute);
        aStructureIndex.add(aStructure);
        aStructure = new Structure();
        aStructure.setName("cuerpo2");
        aStructure.setWeight(1.0);
        aAttribute = new Attribute();
        aAttribute.setName("anchura");

        RangeValue aRangeDescriptor = new RangeValue();
        aRangeDescriptor.setLowerBound(0.3);
        aRangeDescriptor.setUpperBound(4.0);
        aRangeDescriptor.setMeasuringUnit(MeasuringUnit.MM);
       aAttribute.getValues().addValueDescriptorWithUniqueValue(aRangeDescriptor, TaxonomicRank.FAMILY);

        aStructure.addAttribute(aAttribute);
        aStructureIndex.add(aStructure);

        taxon1.setDescription(aStructureIndex);

        taxon2.setName("Chromodoris");
        taxon2.setLevel(TaxonomicRank.GENUS);


        StructureIndex aStructureIndex2 = new StructureIndex();
        aStructure = new Structure();
        aStructure.setName("cuerpo");
        aStructure.setWeight(1.0);

        aAttribute = new Attribute();
        aAttribute.setName("longitud");

        aRangeDescriptor = new RangeValue();
        aRangeDescriptor.setLowerBound(0.3);
        aRangeDescriptor.setUpperBound(4.0);
        aRangeDescriptor.setMeasuringUnit(MeasuringUnit.MM);
        aAttribute.getValues().addValueDescriptorWithUniqueValue(aRangeDescriptor, TaxonomicRank.GENUS);

        aStructure.addAttribute(aAttribute);
        aStructureIndex2.add(aStructure);

        taxon2.setDescription(aStructureIndex2);

        taxon3.setName("Chromodoris_sphoni");
        taxon3.setLevel(TaxonomicRank.SPECIES);

        taxon1.setPredecessor(rootTaxon);
        taxon2.setPredecessor(taxon1);
        taxon3.setPredecessor(taxon2);

    }

    @After
    public void tearDown() {
    }


    /**
     * Test of isRangesConsistent method, of class StructureIndex.
     */
    @Test
    public void testIsRangesConsistent() {
        System.out.println("isRangesConsistent");
        /*
         * Check is the parent is root
         */
        assertTrue(aStructureIndex.isRangesConsistent(rootTaxon));
        /*
         * Check when it have some ranges in the parent
         */
        assertTrue(aStructureIndex.isRangesConsistent(taxon1));
        /*
         * From level 2
         */
        assertTrue(aStructureIndex.isRangesConsistent(taxon2));
        /*
         * From level 3
         */
        assertTrue(aStructureIndex.isRangesConsistent(taxon3));
        
        /*
         * Check when it must be incosistent
         *
         */

        aStructureIndex = new StructureIndex();
        Structure aStructure = new Structure();
        aStructure.setName("cuerpo");
        aStructure.setWeight(1.0);

        Attribute aAttribute = new Attribute();
        aAttribute.setName("forma");
        aStructureIndex.add(aStructure);
        aStructure = new Structure();
        aStructure.setName("cuerpo2");
        aStructure.setWeight(1.0);
        aAttribute = new Attribute();
        aAttribute.setName("anchura");

        RangeValue aRangeDescriptor = new RangeValue();
        aRangeDescriptor.setLowerBound(0.3);
        aRangeDescriptor.setUpperBound(4.0);
        /* inconsistence*/
        aRangeDescriptor.setMeasuringUnit(MeasuringUnit.CM);
        aAttribute.getValues().addValueDescriptorWithUniqueValue(aRangeDescriptor, TaxonomicRank.FAMILY);

        aStructure.addAttribute(aAttribute);
        aStructureIndex.add(aStructure);
        assertFalse(aStructureIndex.isRangesConsistent(taxon1));
        /*
         * From level 2
         */
        assertFalse(aStructureIndex.isRangesConsistent(taxon2));
        /*
         * From level 3
         */
        assertFalse(aStructureIndex.isRangesConsistent(taxon3));

        aStructureIndex = new StructureIndex();
        aStructure = new Structure();
        aStructure.setName("cuerpo2");
        aStructure.setWeight(1.0);

        aAttribute = new Attribute();
        aAttribute.setName("anchura");

        aRangeDescriptor = new RangeValue();
        /* inconsistence because is smaller than father.*/
        aRangeDescriptor.setLowerBound(0.0);
        aRangeDescriptor.setUpperBound(4.0);
        aRangeDescriptor.setMeasuringUnit(MeasuringUnit.MM);
        aAttribute.getValues().addValueDescriptorWithUniqueValue(aRangeDescriptor, TaxonomicRank.FAMILY);

        aStructure.addAttribute(aAttribute);
        aStructureIndex.add(aStructure);
        assertFalse(aStructureIndex.isRangesConsistent(taxon1));
        /*
         * From level 2
         */
        assertFalse(aStructureIndex.isRangesConsistent(taxon2));
        /*
         * From level 3
         */
        assertFalse(aStructureIndex.isRangesConsistent(taxon3));

    }

}