package hive.mapreduce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by lihao on 2017/7/21.
 */
public class HiveT {
    static String driver="org.apache.hive.jdbc.HiveDriver";
    static String url = "jdbc:hive2://192.168.1.202:10000/razor";
    static String user="razor";
    static String password="razor";

    public static void main(String[] args) {
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url,user,password);
            if(conn!=null){
                System.out.print("hive数据库连接成功!");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
