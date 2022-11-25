package Database;

import java.sql.Date;

/**
 * Row storage class of table histories
 */
public class GameRecord {

    public final String username;
    public final boolean failure;
    public final int stage;
    public final double money;
    public final Date date;

    public GameRecord(String username, boolean failure, int stage, double money, Date date) {
        this.username = username;
        this.failure = failure;
        this.stage = stage;
        this.money = money;
        this.date = date;
    }
}
