package com.hive;
import java.sql.*;
import java.util.logging.Logger;

/**
 * Created by lihao on 2017/7/17.
 * 连接数据库
 */
public class SQLConn {
    private final static Logger logger = Logger.getLogger("SQLConn.class");
    private final static String driver = "oracle.jdbc.OracleDriver";
    Connection con;
    /**
     * 连接数据库
     */
    public Connection getSql() {
        try {
            Class.forName(driver);// 加载Oracle驱动程序
            logger.info("开始尝试连接数据库！");
            String url = "jdbc:oracle:thin:@192.168.1.207:1521:razor";// 127.0.0.1是本机地址，XE是精简版Oracle的默认数据库名
            String user = "kafka";// 用户名,系统默认的账户名
            String password = "kafka";// 你安装时选设置的密码
            con = DriverManager.getConnection(url, user, password);// 获取连接
            if (con != null) {
                logger.info("数据库连接成功!");
            } else {
                logger.info("数据库连接失败，请重新检查配置等因素！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
    public ResultSet getOracle(Connection con,String sql){
        ResultSet rs=null;
        try {
            con = getSql();
            PreparedStatement ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void closeSql() {
        if (null != con) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
