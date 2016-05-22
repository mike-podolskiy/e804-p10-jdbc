//CHECKSTYLE:OFF
package kz.mix.e804.jdbc.main.statement.updating;

import kz.mix.e804.jdbc.main.connection.DbConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// To illustrate how we can update a database
public class DbUpdate {
    public static void main(String[] args) {
        try (Connection connection = DbConnector.connectToDb();
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet resultSet = statement.executeQuery("select * from contact where firstname like 'Michael'")) {
            // first fetch the data and display it before the update operation
            System.out.println("Before the update");
            System.out.println("id \tfName \tlName \temail \t\tphoneNo");

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + "\t" + resultSet.getString("firstName") + "\t" + resultSet.getString("lastName") + "\t"
                        + resultSet.getString("email") + "\t" + resultSet.getString("phoneNo"));
            }

            // now update the resultSet and display the modified data
            resultSet.absolute(1);
            resultSet.updateString("phoneno", "+919876543211");
            resultSet.updateRow();

            System.out.println("After the update");
            System.out.println("id \tfName \tlName \temail \t\tphoneNo");
            resultSet.beforeFirst();

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + "\t"
                        + resultSet.getString("firstName") + "\t"
                        + resultSet.getString("lastName") + "\t"
                        + resultSet.getString("email") + "\t"
                        + resultSet.getString("phoneNo"));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(-1);
        }
    }
}
