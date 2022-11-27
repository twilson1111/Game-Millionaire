package Frames;

import Database.MillionaireConnection;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Xu Duo
 */
public class MainFrameTest {
    
    private MainFrame mainFrame;
    private String username;
    
    @Before
    public void setUp() {
        mainFrame = new MainFrame(new MillionaireConnection(), username);
    }

    /**
     * Test of refreshData method, of class MainFrame.
     */
    @Test
    public void testRefreshData() {
        System.out.println("refreshData");
        mainFrame.refreshData();
    }

    /**
     * Test of getUsername method, of class MainFrame.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        assertEquals(username, mainFrame.getUsername());
    }
    
}
