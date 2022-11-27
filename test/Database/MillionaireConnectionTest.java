package Database;

import java.sql.Date;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Xu Duo
 */
public class MillionaireConnectionTest {

    private static MillionaireConnection connection;

    @BeforeClass
    public static void setUpClass() {
        connection = new MillionaireConnection();
    }

    /**
     * Test of getQuestionsByType method, of class MillionaireConnection.
     */
    @Test
    public void testGetQuestionsByType() {
        System.out.println("getQuestionsByType");
        String type = "Normal";
        List<QA> list;
        assertNotNull(list = connection.getQuestionsByType(type));
        System.out.println("List size: " + list.size());
    }

    /**
     * Test of login method, of class MillionaireConnection.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        String username = "Test";
        String password = "123456";
        assertEquals(true, connection.login(username, password));
        System.out.println("login successcully");
    }

    /**
     * Test of register method, of class MillionaireConnection.
     */
    @Test
    public void testRegister() {
        System.out.println("register");
        String username = "Test";
        String password = "";
        assertEquals(false, connection.register(username, password));
        System.out.println("register failed: exist username " + username);
    }

    /**
     * Test of getMoneyByName method, of class MillionaireConnection.
     */
    @Test
    public void testGetMoneyByName() {
        System.out.println("getMoneyByName");
        String username = "Test";
        double result = connection.getMoneyByName(username);
        System.out.println(result);
    }

    /**
     * Test of addRecord method, of class MillionaireConnection.
     */
    @Test
    public void testAddRecord() {
        System.out.println("addRecord");
        GameRecord record = new GameRecord(
                "Test",
                false,
                10,
                10.0,
                new Date(System.currentTimeMillis())
        );
        connection.addRecord(record);
    }

    /**
     * Test of getRecords method, of class MillionaireConnection.
     */
    @Test
    public void testGetRecords() {
        System.out.println("getRecords");
        String username = "Test";
        List<GameRecord> result = connection.getRecords(username);
        assertNotNull(result);
        System.out.println(result.size());
    }

}
