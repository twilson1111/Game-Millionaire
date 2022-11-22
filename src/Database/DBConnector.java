package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnector {

    private static final String DB_URL = "jdbc:derby://localhost:1527/MillionaireDB;create=true";
    private static final String USER_PASS = "pdc";

    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USER_PASS, USER_PASS);
            System.out.println(connection + " connected");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
}
