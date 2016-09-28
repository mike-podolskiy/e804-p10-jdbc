//CHECKSTYLE:OFF
package kz.mix.e804.jdbc.main.rowSet.filtered;

import javax.sql.rowset.FilteredRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class FilteredRowSetTest {

    /*
     * Sample names for test
     */
    private static final String[] NAMES = { "Bill Gates", "Steve Jobs",
            "Mark Zuckerberg", "Alan Turing", "Linus Torlvalds" };

    /**
     * The main class to run test
     */
    public static void main(String[] args) throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/learning", "root",
                "welcome");
        // Just only prepare data for test
       // prepareData(connection);
        RowSetFactory rsf = RowSetProvider.newFactory();
        FilteredRowSet usersRS = rsf.createFilteredRowSet();
        usersRS.setCommand("select * from USER");
        usersRS.execute(connection);
        usersRS.setFilter(new SearchFilter("^[A-L].*"));
        dumpRS(usersRS);
    }

    /**
     * Dump {@link ResultSet}
     *
     * @param rs
     *            input{@link ResultSet} to dump
     * @throws SQLException
     * @throws Exception
     */
    public static void dumpRS(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int cc = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= cc; i++) {
                System.out.println(rsmd.getColumnLabel(i) + " = "
                        + rs.getObject(i) + " ");
            }
            System.out.println("");
        }
    }
    /**
     * Prepare data for test
     *
     * @param connection
     *            {@link Connection} will be used to prepare data
     * @throws SQLException
     */
    private static void prepareData(Connection connection) throws SQLException {
        connection.createStatement().execute("create table USER (name varchar(256))");
        PreparedStatement prepareStatement = connection
                .prepareStatement("insert into USER (name) values (?)");
        for (String name : NAMES) {
            prepareStatement.setString(1, name);
            prepareStatement.execute();
        }
    }
}
