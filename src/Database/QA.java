package Database;

public class QA extends Table {

    public Column<Integer> id = new Column<>("ID");
    public Column<String> question = new Column<>("QUESTION");
    public Column<String> correct = new Column<>("CORRECTANSWER");
    public Column<String> wrong1 = new Column<>("WRONGANSWER1");
    public Column<String> wrong2 = new Column<>("WRONGANSWER2");
    public Column<String> wrong3 = new Column<>("WRONGANSWER3");
    public Column<String> wrong4 = new Column<>("WRONGANSWER4");
    public Column<String> wrong5 = new Column<>("WRONGANSWER5");

    public QA(int id,
            String question,
            String correct,
            String wrong1,
            String wrong2,
            String wrong3,
            String wrong4,
            String wrong5) {
        super("QA");
        this.id.value = id;
        this.correct.value = correct;
        this.wrong1.value = wrong1;
        this.wrong2.value = wrong2;
        this.wrong3.value = wrong3;
        this.wrong4.value = wrong4;
        this.wrong5.value = wrong5;
    }
}
