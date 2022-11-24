package Database;

public class QA {

    public final Integer id;
    public final String question;
    public final String correct;
    public final String[] wrongs = new String[5]; // leak

    public QA(int id,
            String question,
            String correct,
            String wrong1,
            String wrong2,
            String wrong3,
            String wrong4,
            String wrong5) {
        this.id = id;
        this.question = question;
        this.correct = correct;
        this.wrongs[0] = wrong1;
        this.wrongs[1] = wrong2;
        this.wrongs[2] = wrong3;
        this.wrongs[3] = wrong4;
        this.wrongs[4] = wrong5;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(question).append("\n");
        str.append(correct).append("\n");
        for (String wrong : wrongs) {
            if (wrong == null) {
                break;
            }
            str.append(wrong).append("\n");
        }
        return str.toString();
    }
}
