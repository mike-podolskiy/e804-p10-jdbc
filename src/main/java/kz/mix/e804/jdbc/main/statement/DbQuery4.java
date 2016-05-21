//CHECKSTYLE:OFF
package kz.mix.e804.jdbc.main.statement;

import kz.mix.e804.jdbc.main.connection.DbConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbQuery4 {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = DbConnector.connectToDb();
                Statement statement = connection.createStatement();
                ResultSet resultset = statement.executeQuery("SELECT firstName, email FROM contact WHERE firstName='Michael'")) {
            System.out.println("fName \temail");
            while (resultset.next()) {
                System.out.println(resultset.getString("firstName") + "\t" + resultset.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
