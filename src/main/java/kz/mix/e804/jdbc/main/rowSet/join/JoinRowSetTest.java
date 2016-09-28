//CHECKSTYLE:OFF
package kz.mix.e804.jdbc.main.rowSet.join;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JoinRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.Connection;
import java.sql.DriverManager;

// Пример JoinRowSet
public class JoinRowSetTest {

    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/learning";
        String userName = "root";
        String password = "welcome";

        Connection connection = DriverManager.getConnection(url, userName, password);
        RowSetFactory rsf = RowSetProvider.newFactory();

        CachedRowSet studentRS = rsf.createCachedRowSet();
        studentRS.setCommand("select sid, name from student");
        studentRS.execute(connection);

        CachedRowSet enrollmentRS = rsf.createCachedRowSet();
        enrollmentRS.setCommand("select sid, subject from enrollment");
        enrollmentRS.execute(connection);

        CachedRowSet teacherRS = rsf.createCachedRowSet();
        teacherRS.setCommand("select tid, subject from teacher");
        teacherRS.execute(connection);

        JoinRowSet jrs = rsf.createJoinRowSet();
        jrs.addRowSet(studentRS, "sid");
        jrs.addRowSet(enrollmentRS, "sid");

        JoinRowSet jrs2 = rsf.createJoinRowSet();
        jrs.setMatchColumn("subject");
        jrs2.addRowSet(jrs);
        jrs2.addRowSet(teacherRS, "subject");

        int colNumber = jrs2.getMetaData().getColumnCount();

        while (jrs2.next()) {
            for (int i = 1; i < colNumber; i++) {
                System.out.print(jrs2.getObject(i) + " \t");
            }
            System.out.println();
        }
    }
}
