package database;
import java.sql.*;

/**
 * This class handles the database business including loading the driver and connecting to the database
 *
 */
public class Database {

    //the Database instance
    private static Database databaseHandler;
    //the URL to the database in the form jdbc:mysql://hostname:port/databasename
    private final String DB_URL = "jdbc:mysql://localhost:3306/xtra_vision";
    //the password to the database
    private static final String PASSWORD = "thebestdamnthingever2!";
    //the database user name
    private static final String USER_NAME = "root";

    private Database() {
    }

    /**
     * Returns a Database instance
     * @return a Database instance
     */
    public static Database getInstance() {
        if(databaseHandler == null)
            return new Database();
        return databaseHandler;
    }

    /**
     * Connects to the database
     * @return a Connection object, the connection to the database
     */
    public final Connection connectToDB() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        }catch(SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return connection;
    }


}

