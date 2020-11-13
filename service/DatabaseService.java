package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseService {
    // TODO: Hide it in environment variables?
    final static String DBUrl = "";
    final static String DBUser = "";
    final static String DBPassword = "";

    private static Connection connection;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (connection != null)
            return connection;
    
        Class.forName("com.mysql.jbdc.Driver");

        connection = DriverManager.getConnection(DBUrl, DBUser, DBPassword);
        return connection;
    }
}
