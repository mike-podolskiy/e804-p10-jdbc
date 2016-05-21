//CHECKSTYLE:OFF
package kz.mix.e804.jdbc.main.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    public static Connection connectToDb() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/";
        String database = "addressBook";
        String userName = "root";
        String password = "welcome";
        return DriverManager.getConnection(url + database, userName, password);
    }
}
