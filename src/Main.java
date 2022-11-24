
import Database.MillionireConnection;

public class Main {

    public static void main(String[] args) {
        
        MillionireConnection connection = new MillionireConnection();

        QuestionLoader loader = new QuestionLoader(connection);
        
        for (Question q : loader.getRandom(5)) {
            System.out.println(q);
        }
        
    }
}
