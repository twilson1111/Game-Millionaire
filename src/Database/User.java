package Database;

public class User extends Table {
    
    public Column<Integer> id = new Table.Column<>();
    public Column<String> username = new Table.Column<>();
    public Column<String> password = new Table.Column<>();
    public Column<Double> money = new Table.Column<>();
    
    public User(int id, String username, String password, double money) {
        this.id.value = id;
        this.username.value = username;
        this.password.value = password;
        this.money.value = money;
    }
}
