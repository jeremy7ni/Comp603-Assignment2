
package comp603.assignment2;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jeremy
 */
public class FacilitiesTest {

    private Facilities facilities;
    private HomePage homePage;

    @Before
    public void setUp() {
        homePage = new HomePage(); // Create an instance of HomePage if needed
        facilities = new Facilities(homePage);
    }

    @Test
    public void testFacilities() {
        assertNotNull(facilities);

        // Add additional assertions if needed to test the behavior of Facilities class
    }

    public FacilitiesTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }


    @After
    public void tearDown() {
    }

}
