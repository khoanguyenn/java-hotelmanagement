package fraus.javaproject.utils;
/**
 * DatabaseUtil class, connect & execute to MySQL database as config
 * @author Huy Ha Xuan
 * @version 1.0.0
 * @deprecated due to the safety reasons, desktop application don't have to connect directly to database.
 * */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
    static final String DB_URL = "jdbc:mysql://localhost/mydata";
    static final String DB_USERNAME = "root";
    static final String DB_PASSWORD = "KhoANguyeN2020@";

    /**
     * Get connection to the database
     * @return connection if there are no error
     * @return null if errors
     * */
    public static Connection getConnection() {
        Connection connection;
        try{
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            return connection;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    /**
     * Execute SQL query as given
     * @param query query as STRING
     * */
    public static int executeQuery(String query) {
        try(
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ) {
            statement.executeUpdate(query);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return 0;
    }
}
