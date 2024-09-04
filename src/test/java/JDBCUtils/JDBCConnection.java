package JDBCUtils;

import java.sql.*;

public class JDBCConnection {

    private static final String URL=
   "jdbc:oracle:thin:@//database-1.c3bhgefoqgp7.us-east-1.rds.amazonaws.com:1521/techdb";
    private static final String USER="techtorial";
    private static final String PASSWORD="techtorial2400";

    // Let's create a method to establish a connection.
    public static Connection startConnection() throws SQLException {
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }

//    Creating a query, we will create a statement object
    public static Statement getStatement() throws SQLException {
        Connection connection = startConnection();
        Statement statement = connection.createStatement();
        return statement;
    }

// Using a statement object we could execute the queries
    public static ResultSet executeQuery(String query) throws SQLException {
        Statement statement = getStatement();
        ResultSet resultSet = statement.executeQuery(query);
        return  resultSet;
    }

}
