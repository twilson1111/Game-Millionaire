
import Database.DBConnector;
import Database.QA;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<QA> list = DBConnector.getQAList();
        for (QA qa : DBConnector.getQAList()) {
            System.out.println(qa.id);
            System.out.println(qa.question);
            System.out.println(qa.correct);
            System.out.println(qa.wrong1);
            System.out.println(qa.wrong2);
            System.out.println(qa.wrong3);
            System.out.println(qa.wrong4);
            System.out.println(qa.wrong5);
        }
    }
}
