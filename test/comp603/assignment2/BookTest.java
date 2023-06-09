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
public class BookTest {

    HomePage homepage = new HomePage();
    private Book book = new Book(homepage);

    public BookTest() {
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
    public void testInvalidInput(){
        String phone = "11231231";
        boolean result = book.phoneisValid(phone);
        assertFalse(result);
    }
    
   public void testValidInput(){
       String phone = "123123123";
       boolean result = book.phoneisValid(phone);
       assertTrue(result);
   }
}
