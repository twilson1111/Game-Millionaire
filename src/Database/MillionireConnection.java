package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MillionireConnection {

    private static final Logger logger = Logger.getLogger(MillionireConnection.class.getName());

    private static final String DB_URL = "jdbc:derby://localhost:1527/MillionaireDB;create=true";
    private static final String USERNAME = "pdc";
    private static final String PASSWORD = "pdc";

    public static final String TABLENAME_USER = "ACCOUNTS";
    public static final String TABLENAME_QA = "QA";

    private final Connection connection;
    private final Statement statement;

    public MillionireConnection() {
        Connection val_connection = null;
        Statement val_statement = null;
        try {
            // Connection
            val_connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            logger.log(Level.FINEST, "DB connected");
            
            val_statement = val_connection.createStatement();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        connection = val_connection;
        statement = val_statement;
    }

    public List<QA> getQAList() {
        List<QA> list = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("select * from APP." + TABLENAME_QA);
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
            logger.log(Level.SEVERE, e.getMessage());
        }

        return list;
    }

    public List<User> getUserList() {
        List<User> list = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("select * from APP." + TABLENAME_USER);
            while (resultSet.next()) {
                list.add(new User(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3)
                ));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return list;
    }
    
    public boolean login(String username, String password) {
        try {
            ResultSet rs = statement.executeQuery("select password from APP." + TABLENAME_USER + " where username = \'" + username + "\'");
            rs.next();
            return password.equals(rs.getString(1));
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Login Failure");
        }
        return false;
    }

    public boolean register(String username, String password) {
        if (!isNameDuplicated(username)) {
            try {
                statement.executeUpdate("insert into APP." + TABLENAME_USER + " values (\'" + username + "\', \'" + password + "\', 0)");
                return true;

            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Update Failure");
            }
        }
        return false;
    }

    private boolean isNameDuplicated(String username) {
        try {
            return statement.executeQuery("select * from APP." + TABLENAME_USER + " where username = \'" + username + "\'").next();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Duplication Check Failure");
        }
        return false;
    }
}
