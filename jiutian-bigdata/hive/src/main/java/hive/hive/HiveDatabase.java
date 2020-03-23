package hive.hive;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by lihao on 2017/7/19.
 * 连接hive数据库
 */
public class HiveDatabase {
    private final static Logger LOGGER = Logger.getLogger("hiveConn.class");
    String driverName = "org.apache.hive.jdbc.HiveDriver";
    private final static String DRIVERNAME = "org.apache.hive.jdbc.HiveDriver";
    private Connection con;
    public Connection getConn(String url,String user,String password){
        Connection con=null;
        try {
            Class.forName(driverName);
            con = DriverManager.getConnection(url, user, password);
            if(con!=null){
                System.out.println("hive数据库连接成功！");
            }else{
                System.out.println("数据库连接失败，请检查配置等因素...");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    public PreparedStatement prepare(Connection con,String sql){
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }
}
