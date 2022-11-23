
import Database.MillionireConnection;
import Database.QA;
import Database.User;

public class Main {

    public static void main(String[] args) {
        MillionireConnection connection = new MillionireConnection();
        for (QA qa : connection.getQAList()) {
            System.out.println(qa.id);
            System.out.println(qa.question);
            System.out.println(qa.correct);
            System.out.println(qa.wrong1);
            System.out.println(qa.wrong2);
            System.out.println(qa.wrong3);
            System.out.println(qa.wrong4);
            System.out.println(qa.wrong5);
        }

        System.out.println(connection.register("Test", "1"));
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
