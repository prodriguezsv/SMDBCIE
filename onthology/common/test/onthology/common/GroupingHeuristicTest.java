/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package onthology.common.test.onthology.common;

import onthology.common.Description;
import onthology.common.GroupingHeuristic;
import onthology.taxonomy.Taxon;
import onthology.values.Value;
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
public class GroupingHeuristicTest {

    public GroupingHeuristicTest() {
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
     * Test of oneLevel method, of class GroupingHeuristic.
     */
    @Test
    public void testOneLevel() {
        System.out.println("oneLevel");
        int expResult = 0;
        int result = GroupingHeuristic.oneLevel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class GroupingHeuristic.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        GroupingHeuristic instance = new GroupingHeuristic();
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class GroupingHeuristic.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        GroupingHeuristic instance = new GroupingHeuristic();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setWeight method, of class GroupingHeuristic.
     */
    @Test
    public void testSetWeight() {
        System.out.println("setWeight");
        double weight = 0.0;
        GroupingHeuristic instance = new GroupingHeuristic();
        instance.setWeight(weight);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWeight method, of class GroupingHeuristic.
     */
    @Test
    public void testGetWeight() {
        System.out.println("getWeight");
        GroupingHeuristic instance = new GroupingHeuristic();
        double expResult = 0.0;
        double result = instance.getWeight();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValues method, of class GroupingHeuristic.
     */
    @Test
    public void testGetValues() {
        System.out.println("getValues");
        GroupingHeuristic instance = new GroupingHeuristic();
        Value expResult = null;
        Value result = instance.getValues();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addValues method, of class GroupingHeuristic.
     */
    @Test
    public void testAddValues() {
        System.out.println("addValues");
        GroupingHeuristic aGroupingHeuristic = null;
        Taxon aTaxon = null;
        GroupingHeuristic instance = new GroupingHeuristic();
        instance.addValues(aGroupingHeuristic, aTaxon);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createSAVDescription method, of class GroupingHeuristic.
     */
    @Test
    public void testCreateSAVDescription() {
        System.out.println("createSAVDescription");
        String aTaxonomicGroupName = "";
        GroupingHeuristic instance = new GroupingHeuristic();
        Description expResult = null;
        Description result = instance.createSAVDescription(aTaxonomicGroupName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compareTo method, of class GroupingHeuristic.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        GroupingHeuristic aGroupingHeuristic = null;
        GroupingHeuristic instance = new GroupingHeuristic();
        int expResult = 0;
        int result = instance.compareTo(aGroupingHeuristic);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}