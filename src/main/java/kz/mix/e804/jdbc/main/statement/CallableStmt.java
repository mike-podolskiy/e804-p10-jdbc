//CHECKSTYLE:OFF
package kz.mix.e804.jdbc.main.statement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Пример вызова хранимой процедуры с одним IN параметром
public class CallableStmt {
    public static void main(String[] args) {
        try (Connection connection = getConnection()) {
            CallableStatement callableStatement = connection.prepareCall("call add_new_name(?)");
            callableStatement.setString(1, "Vasya");
            callableStatement.execute();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        }
    }

    private static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/";
        String database = "learning";
        String userName = "root";
        String password = "welcome";
        return DriverManager.getConnection(url + database, userName, password);
    }
}
