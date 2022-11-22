
import Database.DBConnector;
import Database.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {


        try {
        Connection connection = DBConnector.connect();
            Statement statement = connection.createStatement();

            ResultSet rs  = statement.executeQuery("select * from " + User.getTableName());
            
            while (rs.next()) {
                
            }
            
        } catch (SQLException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
