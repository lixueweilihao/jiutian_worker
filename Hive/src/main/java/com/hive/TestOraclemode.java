package com.hive;
import java.sql.*;
import java.util.logging.Logger;

/**
 * Created by lihao on 2017/7/19.
 */
public class TestOraclemode {
    private final static String driver = "oracle.jdbc.OracleDriver";
    static Connection conn = null;
    private final static Logger logger = Logger.getLogger("TestOracle.class");
    PreparedStatement pre = null;// 创建预编译语句对象，一般都是用这个而不用Statement
    ResultSet result = null;// 创建一个结果集对象
    Statement stmt = null;
    static String url = "jdbc:oracle:thin:@192.168.1.207:1521:razor";// 127.0.0.1是本机地址，XE是精简版Oracle的默认数据库名
    static String user = "kafka";// 用户名,系统默认的账户名
    static String password = "kafka";// 你安装时选设置的密码

    public static void main(String[] args) {
        String a ="1";
        String b ="2";
        TestOraclemode tom = new TestOraclemode();
        try {
           Class.forName(driver);
            conn = DriverManager.getConnection(url,user,password);
            String sql = "insert into razor_event_log_day(app_key,event_identifier) values("+a+","+b+")";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
        e.printStackTrace();
    }



    }
}
