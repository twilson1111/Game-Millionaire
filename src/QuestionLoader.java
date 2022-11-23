
import Database.MillionireConnection;
import Database.QA;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class QuestionLoader {

    private final MillionireConnection connection = new MillionireConnection();
    private final List<QA> questionList;

    {
        questionList = connection.getQAList();
    }

    public List<QA> getRandom(int number) {
        List<QA> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        Random rand = new Random();
        while(set.size() < number) {
            set.add(rand.nextInt(questionList.size()));
        }
        for (int i : set) {
            list.add(questionList.get(i));
        }
        return list;
    }
}
