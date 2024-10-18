package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import Loader.Question;



/**
 * Database manupulating class containing all the DB operations.
 */
public class MillionaireConnection {
	
	// registration constraints
    private static final int PASSWORD_MIN_LENGTH = 8;
    private static final int USERNAME_MIN_LENGTH = 6;
    class UsernameException extends Exception {
    	public UsernameException() {
    		super("Username failure - must be at least " + USERNAME_MIN_LENGTH + " characters");
    	}
    }
    class PasswordException extends Exception {
    	public PasswordException() {
    		super("Password failure - must be at least " + PASSWORD_MIN_LENGTH + " characters");
    	}
    }
    class ExistingUsernameException extends Exception {
    	public ExistingUsernameException() {
    		super("Sorry, that username already exists.");
    	}
    }

    // static strings of DB connections
    private static final String DB_URL = "jdbc:derby:MillionaireDB";
    private static final String USERNAME = "pdc";
    private static final String PASSWORD = "pdc";
    private static final String TABLENAME_USER = "USERS";
    private static final String TABLENAME_QA = "QA";
    private static final String TABLENAME_HISTORIES = "HISTORIES";

    // static logger
    private static final Logger logger = Logger.getLogger(MillionaireConnection.class.getName());

    // DB connection instances
    private final Connection connection;
    private final Statement statement;
    private final PreparedStatement getQuestionByTypeStatement;
    private final PreparedStatement addRecordStatement;
    private final PreparedStatement updateMoneyStatement;

    // constructor
    public MillionaireConnection() {

        // try connection
        Connection val_connection = null;
        try {
            val_connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Connection failure");

        } finally {
            connection = val_connection;

        }

        // create statements
        Statement val_statement = null;
        PreparedStatement val_getQuestion = null;
        PreparedStatement val_addRecord = null;
        PreparedStatement val_upadteMoney = null;
        try {
            val_statement = connection.createStatement();
            val_getQuestion = connection.prepareStatement("select * from app." + TABLENAME_QA + " where type = ?");
            val_addRecord = connection.prepareStatement("insert into app." + TABLENAME_HISTORIES + " values(?, ?, ?, ?, ?)");
            val_upadteMoney = connection.prepareStatement("update app." + TABLENAME_USER + " set money = money + ? where username = ?");

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Statement creation failure");

        } finally {
            statement = val_statement;
            getQuestionByTypeStatement = val_getQuestion;
            addRecordStatement = val_addRecord;
            updateMoneyStatement = val_upadteMoney;

        }
    }

    /**
     * This method use SQL to help QuestionLoader loading a specific type of
     * questions
     *
     * @param type The type of question to be load
     * @return The list of rows of QA table
     */
    public List<QA> getQuestionsByType(String type) {
        List<QA> list = new ArrayList<>();
        try {
            getQuestionByTypeStatement.setString(1, type);
            ResultSet resultSet = getQuestionByTypeStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new QA(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8)
                ));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Getting questions failure");
        }

        return list;
    }

    /**
     * Use SQL statement to check if password is correct
     *
     * @param username username for login
     * @param password password for login
     * @return if the this login succeed
     */
    public boolean login(String username, String password) {
        try {
            ResultSet rs = statement.executeQuery("select password from APP." + TABLENAME_USER + " where username = \'" + username + "\'");
            if (!rs.next()) {
                return false;
            }

            return password.equals(rs.getString(1));

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Login failure");
        }

        return false;
    }

    /**
     * Use SQL statement to check if this username is registered, if the username and password have the required number of characters,  and update DB
     *
     * @param username username for register
     * @param password password for register
     * @return if the this login succeed
     * @throws exceptions
     */
//  public boolean register(String username, String password) {
//  try {
//      boolean isNameDuplicated = statement.executeQuery("select * from APP." + TABLENAME_USER + " where username = \'" + username + "\'").next();
//
//      if (!isNameDuplicated) {
//          statement.executeUpdate("insert into APP." + TABLENAME_USER + " values (\'" + username + "\', \'" + password + "\', 0)");
//          return true;
//      }
//
//  } catch (SQLException e) {
//      logger.log(Level.SEVERE, "Register failure");
//  }
//  return false;
//}
    public void register(String username, String password) throws Exception {
        try {
        	if (username.length() < USERNAME_MIN_LENGTH) {
        		throw new UsernameException();
        	}
        	if (password.length() < PASSWORD_MIN_LENGTH) {
        		throw new PasswordException();
        	}
        	
            boolean isNameDuplicated = statement.executeQuery("select * from APP." + TABLENAME_USER + " where username = \'" + username + "\'").next();
            
            if (isNameDuplicated) {
            	throw new ExistingUsernameException();
            } else {
                statement.executeUpdate("insert into APP." + TABLENAME_USER + " values (\'" + username + "\', \'" + password + "\', 0)");
            }
        } catch (Exception e) {
        	logger.log(Level.SEVERE, e.getLocalizedMessage());
        	throw e;
        }
    }

    /**
     * Use SQL to get the money of specific user
     *
     * @param username username for login
     * @return amount of money belongs to the user
     */
    public double getMoneyByName(String username) {
        try {
            ResultSet rs = statement.executeQuery("select money from APP." + TABLENAME_USER + " where username = \'" + username + "\'");
            if (!rs.next()) {
                return 0;
            }

            return rs.getDouble(1);

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Getting money failure");
        }

        return 0;
    }

    /**
     * Add a game record by prepared statement.
     *
     * @param record game record
     */
    public void addRecord(GameRecord record) {
        try {
            // update histories
            addRecordStatement.setString(1, record.getUsername());
            addRecordStatement.setBoolean(2, record.getFailure());
            addRecordStatement.setInt(3, record.getStage());
            addRecordStatement.setDouble(4, record.getMoney());
            addRecordStatement.setDate(5, record.getDate());
            addRecordStatement.executeUpdate();

            // update user infos
            updateMoneyStatement.setDouble(1, record.getMoney());
            updateMoneyStatement.setString(2, record.getUsername());
            updateMoneyStatement.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Adding record failure");
        }

    }

    public List<GameRecord> getRecords(String username) {
        List<GameRecord> list = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("select * from app." + TABLENAME_HISTORIES + " where username = \'" + username + "\'");
            while (resultSet.next()) {
                list.add(new GameRecord(
                        resultSet.getString(1),
                        resultSet.getBoolean(2),
                        resultSet.getInt(3),
                        resultSet.getDouble(4),
                        resultSet.getDate(5)
                ));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Getting questions failure");
        }

        return list;
    }
    
    public List<Question> getRandom(String type, int number) {
        List<QA> questionList = getQuestionsByType(type);
        List<Question> list = new ArrayList<>();
        
        Set<Integer> set = new HashSet<>();
        Random rand = new Random();
        while (set.size() < number) {
            set.add(rand.nextInt(questionList.size()));
        }
        for (int i : set) {
            list.add(new Question(questionList.get(i)));
        }
        return list;
    }
}
