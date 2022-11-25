package Loader;


import Database.MillionaireConnection;
import Database.QA;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class QuestionLoader {

    private final MillionaireConnection connection;

    public QuestionLoader(MillionaireConnection connection) {
        this.connection = connection;
    }

    public List<Question> getRandom(int number) {
        List<QA> questionList = connection.getQAList();
        List<Question> list = new ArrayList<>();
        
        Set<Integer> set = new HashSet<>();
        Random rand = new Random();
        while (set.size() < number) {
            set.add(rand.nextInt(questionList.size()));
        }
        for (int i : set) {
            list.add(new Question(questionList.get(i)));
        }
        return list;
    }
}
