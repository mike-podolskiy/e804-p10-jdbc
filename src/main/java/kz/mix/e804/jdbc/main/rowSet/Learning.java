//CHECKSTYLE:OFF
package kz.mix.e804.jdbc.main.rowSet;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JoinRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

// Пример JoinRowSet
public class Learning {

    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/learning";
        String userName = "root";
        String password = "welcome";

        RowSetFactory rsf = RowSetProvider.newFactory();

        CachedRowSet studentRS = rsf.createCachedRowSet();
        init(studentRS, url, userName, password);
        studentRS.setCommand("select sid, name from student");
        studentRS.execute();

        CachedRowSet enrollmentRS = rsf.createCachedRowSet();
        init(enrollmentRS, url, userName, password);
        enrollmentRS.setCommand("select sid, subject from enrollment");
        enrollmentRS.execute();

        CachedRowSet teacherRS = rsf.createCachedRowSet();
        init(teacherRS, url, userName, password);
        teacherRS.setCommand("select tid, subject from teacher");
        teacherRS.execute();

        JoinRowSet jrs = rsf.createJoinRowSet();
        jrs.addRowSet(studentRS, "sid");
        jrs.addRowSet(enrollmentRS, "sid");

        JoinRowSet jrs2 = rsf.createJoinRowSet();
        jrs.setMatchColumn("subject");
        jrs2.addRowSet(jrs);
        jrs2.addRowSet(teacherRS, "subject");

        System.out.println(jrs2.getMetaData().getColumnCount());

        while (jrs2.next()) {
            System.out.println(jrs2.getObject(1) + " \t" + jrs2.getObject(2) + " \t" + jrs2.getObject(3) + " \t" + jrs2.getObject(4));
        }

    }

    private static void init(RowSet rs, String url, String userName, String password) throws Exception {
        rs.setUrl(url);
        rs.setUsername(userName);
        rs.setPassword(password);
    }
}
