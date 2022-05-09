package com.ithome.web.Connection;

import com.ithome.web.Constances.Constance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnectionToDatabase() {

        Connection connection = null;

        try {
            // load the driver class
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://204.93.169.198:3306/oferta_oferta_datat_controller?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8&useSSL=false", "oferta_root", "mnbvcxz00A!");
            //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/oferta_oferta_datat_controller?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT&useSSL=false", Constance.USERNAMEINDATA2, Constance.USERPASSWORDINDATA2);

        } catch (ClassNotFoundException | SQLException e) {
            //System.out.println("Where is your MySQL JDBC Driver?");
            System.out.println("Connection Failed! Check output console" + e);
            e.printStackTrace();
        }
        return connection;
    }
}
