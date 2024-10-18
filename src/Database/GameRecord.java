package Database;

import java.sql.Date;

/**
 * Row storage class of table histories
 */
// NEW CODE
public class GameRecord {

    private final String username;
    private final boolean failure;
    private final int stage;
    private final double money;
    private final Date date;

    public GameRecord(String username, boolean failure, int stage, double money, Date date) {
        this.username = username;
        this.failure = failure;
        this.stage = stage;
        this.money = money;
        this.date = date;
    }
    
    public String getUsername() {
    	return username;
    }
    
    public boolean getFailure() {
    	return failure;
    }
    
    public int getStage() {
    	return stage;
    }
    
    public double getMoney() {
    	return money;
    }
    
    public Date getDate() {
    	return date;
    }
}

// OLD CODE
/*
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

 */
