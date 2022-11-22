package Database;

public class User extends Table {
    
    public Column<Integer> id = new Table.Column<>("ID");
    public Column<String> username = new Table.Column<>("USERNAME");
    public Column<String> password = new Table.Column<>("PASSWORD");
    public Column<Double> money = new Table.Column<>("MONEY");
    
    public User(int id, String username, String password, double money) {
        super("USERS");
        this.id.value = id;
        this.username.value = username;
        this.password.value = password;
        this.money.value = money;
    }
}
