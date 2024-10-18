package Loader;

import Database.QA;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Question {

    private final String text;
    private final int size;
    private final int rightAnswerPos;
    private final List<String> answers;

    private final Random random = new Random();
    
    public String getText() {
    	return text;
    }
    
    public int getSize() {
    	return size;
    }
    
    public int getRightAnswerPos() {
    	return rightAnswerPos;
    }
    
    public List<String> getAnswers() {
    	return answers;
    }

    public Question(QA qa) {
        text = qa.getQuestion();
        int pos = 1;
        while (pos < 5 && qa.getWrongs()[pos] != null) {
            pos++;
        }
        size = pos + 1;

        answers = new ArrayList<>();

        ArrayList<Integer> liner = new ArrayList<>();
        for (int i = 0; i < size;) {
            int number = random.nextInt(size);
            if (!liner.contains(number)) {
                liner.add(number);
                i++;
            }
        }

        rightAnswerPos = liner.indexOf(0) + 1;

        for (int number : liner) {
            if (number == 0) {
                answers.add(qa.getCorrect());
            } else {
                answers.add(qa.getWrongs()[number - 1]);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(text).append("\n");
        int pos = 0;
        for (String ans : answers) {
            builder.append(++pos).append(". ").append(ans).append("\n");
        }

        // builder.append("Correct: ").append(rightAnswerPos).append("\n");
        return builder.toString();
    }
}

/*
 * OLD CODE
public class Question {

    public final String text;
    public final int size;
    public final int rightAnswerPos;
    public final List<String> answers;
    ...
    
*/
