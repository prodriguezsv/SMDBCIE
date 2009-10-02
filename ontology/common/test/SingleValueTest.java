package ontology.common.test;

import ontology.common.RangeValue;
import ontology.common.SingleValue;

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
public class SingleValueTest {

    public SingleValueTest() {
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
     * Test of equals method, of class SingleValue.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        SingleValue aSingleValue = new SingleValue(0.0);
        SingleValue instance = new SingleValue(aSingleValue);
        /*
         * must be the same
         */
        assertTrue(instance.equals(aSingleValue));
        /*
         * not null and not diferent and not instance of another class
         *
         */
        assertFalse(instance.equals(null));
        aSingleValue.setValue(0.1);

        RangeValue aRangeValue = new RangeValue(0.0, 0.0);
        assertFalse(instance.equals(aRangeValue));        
    }

}