/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ontology.common.test;

import ontology.common.Attribute;
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
public class AttributeTest {

    public AttributeTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of compareTo method, of class Attribute.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Attribute aAttribute = new Attribute();
        aAttribute.setName("name1");
        Attribute instance = new Attribute();
        instance.setName("name1");
        int expResult = 0;
        int result = instance.compareTo(aAttribute);
        assertEquals(expResult, result);

        aAttribute.setName("name1");
        instance.setName("name2");
        expResult = 1;
        result = instance.compareTo(aAttribute);
        assertEquals(expResult, result);

        aAttribute.setName("name2");
        instance.setName("name1");
        expResult = -1;
        result = instance.compareTo(aAttribute);
        assertEquals(expResult, result);
    }
}