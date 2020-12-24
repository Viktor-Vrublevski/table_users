package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String user = "root";
    private static final String password = "1111";
    private static final String URL = "jdbc:mysql://localhost:3306/usersdb?serverTimezone=Europe/Moscow";

    public Util() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not fount: " + e.getMessage());
        }
    }

    public  Connection getConnect() throws SQLException {
        return DriverManager.getConnection(URL, user, password);
    }
}
