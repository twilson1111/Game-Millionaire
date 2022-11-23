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

public class DBConnector {

    private static final Logger logger = Logger.getLogger(DBConnector.class.getName());

    private static final String DB_URL = "jdbc:derby://localhost:1527/MillionaireDB;create=true";
    private static final String USERNAME = "pdc";
    private static final String PASSWORD = "pdc";

    public static final String TABLENAME_USER = "USERS";
    public static final String TABLENAME_QA = "QA";

    private static Connection connection = null;

    static {
        try {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            logger.log(Level.FINEST, "DB connected");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    public static List<QA> getQAList() {
        List<QA> list = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from APP.QA");
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

    public static List<User> getUserList() {
        List<User> list = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from APP.USERS");
            while (resultSet.next()) {
                list.add(new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4)
                ));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return list;
    }
}
