
import Database.MillionireConnection;
import Database.QA;
import Database.User;

public class Main {

    public static void main(String[] args) {
        
        MillionireConnection connection = new MillionireConnection();

        QuestionLoader loader = new QuestionLoader();
        
        for (QA qa : loader.getRandom(1)) {
            System.out.println(qa);
        }

        System.out.println(connection.register("Test2", "2"));
        System.out.println(connection.register("Test1", "1"));

        for (User user : connection.getUserList()) {
            System.out.println(user.username);
            System.out.println(user.password);
        }

        System.out.println(connection.login("Test", "1"));
        System.out.println(connection.login("Test", "123456"));
        System.out.println(connection.login("Test1", "1"));
        System.out.println(connection.login("Test1", "123456"));
    }
}
