package Database;

public class User {

    public final String username;
    public final String password;
    public final Double money;

    public User(String username, String password, double money) {
        this.username = username;
        this.password = password;
        this.money = money;
    }
}
