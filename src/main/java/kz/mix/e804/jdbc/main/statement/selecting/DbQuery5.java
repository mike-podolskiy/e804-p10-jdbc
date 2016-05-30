//CHECKSTYLE:OFF
package kz.mix.e804.jdbc.main.statement.selecting;

import javax.sql.rowset.*;
import java.sql.*;

// To illustrate how to use RowSet, RowSetProvider, and RowSetFactory
public class DbQuery5 {

    public static void main(String[] args) {
        String str = "A.B.C!";
        System.out.println(str.replaceAll(".", ",").replace("!", "?"));

        String url = "jdbc:mysql://localhost:3306/addressBook";
        String userName = "root";
        String password = "welcome";

        try {
            // first, create a factory object for rowset
            RowSetFactory rowSetFactory = RowSetProvider.newFactory();
            // create a JDBC rowset from the factory
            JdbcRowSet rowSet = rowSetFactory.createJdbcRowSet();
            rowSet.setUrl(url);
            rowSet.setUsername(userName);
            rowSet.setPassword(password);
            rowSet.setCommand("select * from contact");
            rowSet.execute();
            System.out.println("id tfName tlName \temail \t\tphoneNo");

            while (rowSet.next()) {
                System.out.println(rowSet.getInt("id") + "\t"
                        + rowSet.getString("firstName") + "\t"
                        + rowSet.getString("lastName") + "\t"
                        + rowSet.getString("email") + "\t"
                        + rowSet.getString("phoneNo"));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}


