
import Database.QA;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Question {

    public final String text;
    public final int size;
    public final int rightAnswerPos;
    public final List<String> answers;

    public Question(QA qa) {
        text = qa.question;
        int pos = 1;
        while(pos < 5 && qa.wrongs[pos] != null) pos++;
        size = pos + 1;
        
        
        rightAnswerPos = new Random().nextInt(size);
        answers = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            if (i == rightAnswerPos) {
                answers.set(i, qa.correct);
            } else {
                String temp = null;
            }
        }
    }

}
