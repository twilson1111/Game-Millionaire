package Database;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MillionaireConnection {

    private static final Logger logger = Logger.getLogger(MillionaireConnection.class.getName());

    private static final String DB_URL = "jdbc:derby:MillionaireDB";
    private static final String USERNAME = "pdc";
    private static final String PASSWORD = "pdc";

    public static final String TABLENAME_USER = "USERS";
    public static final String TABLENAME_QA = "QA";

    private final Statement statement;

    public MillionaireConnection() {
        Statement val_statement = null;
        try {
            val_statement = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD).createStatement();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.toString());
        }
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
            if (!rs.next()) {
                return false;
            }

            return password.equals(rs.getString(1));

        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }

        return false;
    }

    public boolean register(String username, String password) {
        try {
            boolean isNameDuplicated = statement.executeQuery("select * from APP." + TABLENAME_USER + " where username = \'" + username + "\'").next();

            if (!isNameDuplicated) {
                statement.executeUpdate("insert into APP." + TABLENAME_USER + " values (\'" + username + "\', \'" + password + "\', 0)");
                return true;
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return false;
    }
}
