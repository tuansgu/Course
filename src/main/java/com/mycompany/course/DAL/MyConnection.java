package com.mycompany.course.DAL;
    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.SQLException;
public class MyConnection 
{
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/school";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static Connection connect() throws SQLException 
    {
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");       
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            System.out.println("Connect Database Successfully!");
            return connection;
        } 
        catch (ClassNotFoundException e) 
        {
            throw new SQLException("Not Found Driver JDBC!", e);
        }
    }
    public static void close(Connection connection) 
    {
        if (connection != null) {
            try 
            {
                connection.close();
                System.out.println("Close Connect Database Successfully!");
            } 
            catch (SQLException e) 
            {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}
