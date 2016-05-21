//CHECKSTYLE:OFF
package kz.mix.e804.jdbc.main.statement;

import kz.mix.e804.jdbc.main.connection.DbConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbQuery3 {
    public static void main(String[] args) {
        try (Connection connection = DbConnector.connectToDb();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM contact")) {
            // from resultSet metadata, find out how many columns are there and then read the column entries
            int numOfColumns = resultSet.getMetaData().getColumnCount();
            System.out.println("ID \tfName \tlName \temail \t\tphoneNo");
            while (resultSet.next()) {
                // remember that the column index starts from 1 not 0
                for (int i = 1; i <= numOfColumns; i++) {
                    // since we do not know the data type of the column, we use getObject()
                    System.out.print(resultSet.getObject(i) + "\t");
                }
                System.out.println("");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        }
    }
}
