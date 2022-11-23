package Database;

public class QA extends Table {

    public Column<Integer> id = new Column<>();
    public Column<String> question = new Column<>();
    public Column<String> correct = new Column<>();
    public Column<String> wrong1 = new Column<>();
    public Column<String> wrong2 = new Column<>();
    public Column<String> wrong3 = new Column<>();
    public Column<String> wrong4 = new Column<>();
    public Column<String> wrong5 = new Column<>();

    public QA(int id,
            String question,
            String correct,
            String wrong1,
            String wrong2,
            String wrong3,
            String wrong4,
            String wrong5) {
        this.id.value = id;
        this.question.value = question;
        this.correct.value = correct;
        this.wrong1.value = wrong1;
        this.wrong2.value = wrong2;
        this.wrong3.value = wrong3;
        this.wrong4.value = wrong4;
        this.wrong5.value = wrong5;
    }
}
