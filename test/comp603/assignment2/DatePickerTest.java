
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
public class DatePickerTest {
    
    private DatePicker datePicker;
    
    public DatePickerTest() {
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

    @Test
    public void testIsDateValid() {
        String inputDate = "2023/06/09";
        String dateFormat = "yyyy-MM-dd";
        boolean result = datePicker.isDateValid(inputDate, dateFormat);
        assertTrue(result);
    }
    
    @Test
    public void testGetDateIn() {
        String dateIn = "2023/06/09";
        datePicker.setDateIn(dateIn);
        String result = datePicker.getDateIn();
        assertEquals(dateIn, result);
    }

    @Test
    public void testGetDateOut() {
        String dateOut = "2023/06/10";
        datePicker.setDateOut(dateOut);
        String result = datePicker.getDateOut();
        assertEquals(dateOut, result);
    }
}
