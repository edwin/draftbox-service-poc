package com.rumahbangsa.draftbox.scheduler.db;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author sqlitetutorial.net
 */
public class Connect {
    /**
     * Connect to a sample database
     */
    public static void connect(String filename) {

        String url = "jdbc:sqlite:C:/Users/ASUS/Documents/draftbox-service-poc/" + filename;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * @param args the command line arguments
     */

}