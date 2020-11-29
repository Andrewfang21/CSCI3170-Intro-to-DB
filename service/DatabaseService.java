package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseService {
    // TODO: Hide it in environment variables?
    final static String DBUrl = "jdbc:mysql://projgw.cse.cuhk.edu.hk:2633/group39";
    final static String DBUser = "Group39";
    final static String DBPassword = "dbg39ad";
    //final static String DBUrl = "jdbc:mysql://localhost:3306/group39";
    //final static String DBUser = "root";
    //final static String DBPassword = "secret";

    private static Connection connection;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (connection != null)
            return connection;
    
        Class.forName("com.mysql.jdbc.Driver");

        connection = DriverManager.getConnection(DBUrl, DBUser, DBPassword);
        return connection;
    }
}
