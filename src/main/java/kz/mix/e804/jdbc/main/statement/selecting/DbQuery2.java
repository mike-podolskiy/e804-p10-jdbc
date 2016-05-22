//CHECKSTYLE:OFF
package kz.mix.e804.jdbc.main.statement.selecting;

import kz.mix.e804.jdbc.main.connection.DbConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Program to illustrate how to query a database
public class DbQuery2 {
    public static void main(String[] args) {
        // Get connection, execute query, get the result set
        // and print the entries from the result rest
        try (Connection connection = DbConnector.connectToDb();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM contact")) {
            System.out.println("ID \tfName \tlName \temail \t\tphoneNo");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + "\t"
                        + resultSet.getString(2) + "\t"
                        + resultSet.getString(3) + "\t"
                        + resultSet.getString(4) + "\t"
                        + resultSet.getString(5));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        }
    }
}

