package com.example.javafx.DBuntils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    static String url="jdbc:sqlserver://localhost:1433;database=javafxDB"+
            ";autoReconnectForPools=true;encrypt=true;trustServerCertificate=true;";
    static String username="sa";
    static String password="123456";
    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url,username,password);
    }
}
