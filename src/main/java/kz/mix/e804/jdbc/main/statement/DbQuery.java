//CHECKSTYLE:OFF
package kz.mix.e804.jdbc.main.statement;

import kz.mix.e804.jdbc.main.connection.DbConnector;

import java.sql.*;

// Program to illustrate how to query a database
public class DbQuery {
    public static void main(String[] args) {
        // Get connection, execute query, get the result set
        // and print the entries from the result rest
        try (Connection connection = DbConnector.connectToDb();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM contact")) {
            System.out.println("ID \tfName \tlName \temail \t\tphoneNo");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + "\t" + resultSet.getString("firstName") + "\t" + resultSet.getString("lastName") + "\t"
                        + resultSet.getString("email") + "\t" + resultSet.getString("phoneNo"));
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        }
    }
}
