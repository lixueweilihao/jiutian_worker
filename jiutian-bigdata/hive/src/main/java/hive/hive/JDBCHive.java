package hive.hive;

import java.sql.*;


public class JDBCHive {
    private static String Driver ="org.apache.hive.jdbc.HiveDriver";
    private static String url = "jdbc:hive2://10.3.7.233:10000/";
    private static String name = "root";
    private static String password = "dtc2019233!";

    public static void main(String[] args) {
        try {
            Class.forName(Driver);
            Connection conn = DriverManager.getConnection(url,name,password);
            Statement stat = conn.createStatement();
            String sql = "show databases";
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getString(1));//hive的索引从1开始
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
