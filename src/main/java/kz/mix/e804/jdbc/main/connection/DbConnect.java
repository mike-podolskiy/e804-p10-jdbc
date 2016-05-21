//CHECKSTYLE:OFF
package kz.mix.e804.jdbc.main.connection;

import java.sql.*;

// The class attempts to acquire a connection with the database
class DbConnect {
    public static void main(String[] args) {
        // url points to jdbc protocol : mysql subprotocol; localhost is the address
        // of the server where we installed our DBMS (i.e. on local machine) and
        // 3306 is the port on which we need to contact our DBMS
        String url = "jdbc:mysql://localhost:3306/";
        // we are connecting to the addressBook database we created earlier
        String database = "addressbook";
        // we login as "root" user with password "mysql123"
        String userName = "root";
        String password = "welcome";

        try (Connection connection = DriverManager.getConnection(url + database, userName, password)) {
            System.out.println("Database connection: Successful");
        } catch (Exception e) {
            System.out.println("Database connection: Failed");
            e.printStackTrace();
        }
    }
}