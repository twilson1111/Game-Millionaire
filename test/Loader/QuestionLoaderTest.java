package Loader;

import Database.MillionaireConnection;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class QuestionLoaderTest {

    /**
     * Test of getRandom method, of class QuestionLoader.
     */
    @Test
    public void testGetRandom() {
        System.out.println("getRandom");
        String type = "Normal";
        int number = 10;
        QuestionLoader instance = new QuestionLoader(new MillionaireConnection());
        List<Question> result;
        assertNotNull(result = instance.getRandom(type, number));
        System.out.println(result.size());
    }

}
