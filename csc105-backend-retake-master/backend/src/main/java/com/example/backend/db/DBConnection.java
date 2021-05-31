package com.example.backend.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String dbURL = "jdbc:mysql://10.4.53.32:3306/db_name";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbURL, "", "");
            return con;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
