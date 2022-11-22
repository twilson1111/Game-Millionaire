package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static final String DB_URL = "jdbc:derby://localhost:1527/MillionaireDB;create=true";
    private static final String USER_PASS = "pdc";

    public static final String TABLENAME_USER = "USERS";
    public static final String TABLENAME_QA = "QA";

    public static Connection connect() throws SQLException {
        Connection connection;
        connection = DriverManager.getConnection(DB_URL, USER_PASS, USER_PASS);
        System.out.println(connection + " connected");
        return connection;
    }
}
