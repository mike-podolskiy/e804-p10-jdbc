//CHECKSTYLE:OFF
package kz.mix.e804.jdbc.main.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import kz.mix.e804.jdbc.main.connection.DbConnector;

public class DbCreate {
    public static void main(String[] args) {
        try (Connection connection = DbConnector.connectToDb();
                Statement statement = connection.createStatement()) {
            // use CREATE TABLE SQL statement to create table familyGroup
            int result = statement.executeUpdate("CREATE TABLE familyGroup (id int not null auto_increment, nickName varchar(30) not null, primary key(id));");
            System.out.println("Table created successfully");

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(-1);
        }
    }
}
