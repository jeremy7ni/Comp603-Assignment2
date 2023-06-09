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
public class RoomTest {

    public RoomTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of RoomService method, of class Room.
     */
    @Test
    public void testCalCost() {
        SingleRoom room = new SingleRoom();
        room.setVip(true);
        double result = room.calCost();
        double expected = 120.0;
        assertEquals(expected, result, 0.01);
    }
}
