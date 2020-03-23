package hive.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class HiveClientUtils {

    private static String Driver = "org.apache.hive.jdbc.HiveDriver";
    private static String Url = "jdbc:hive2://10.3.7.233:10000/";
    private static String Name = "root";
    private static String Password = "dtc2019233!";


    //填写hive的IP，之前在配置文件中配置的IP


    private static Connection conn;

    private static PreparedStatement ps;

    private static ResultSet rs;

    //创建连接

    public static Connection getConnnection() {

        try {

            Class.forName(Driver);

            //此处的用户名一定是有权限操作HDFS的用户，否则程序会提示"permission deny"异常
            conn = DriverManager.getConnection(Url, Name, Password);

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

            System.exit(1);

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return conn;
    }

    public static PreparedStatement prepare(Connection conn, String sql) {

        PreparedStatement ps = null;

        try {

            ps = conn.prepareStatement(sql);

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return ps;

    }

    public static void getAll(String tablename) {

        conn = getConnnection();

        String sql = "select * from work_order";
        try {
            ps = prepare(conn, sql);
            rs = ps.executeQuery();
            int columns = rs.getMetaData().getColumnCount();
            for (int j = 1; j <=columns; j++) {
                System.out.print(rs.getMetaData().getColumnName(j) + "\t");
            }
            while (rs.next()) {
                for (int i = 1; i <= columns; i++) {
                    System.out.print(rs.getString(i));
                    System.out.print("\t");

                }
                System.out.println();
            }


        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    public static void main(String[] args) {

        String tablename = "`user`";

        getAll(tablename);

    }

}
