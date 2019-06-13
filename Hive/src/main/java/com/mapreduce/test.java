package com.mapreduce;

import com.sun.org.apache.regexp.internal.RE;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by lihao on 2017/7/17.
 */
public class test {
    Logger logger = Logger.getLogger("test.class");

    public int testOracle() {
        int rowcount = 0;
        ResultSet pre1=null;
        Connection con = null;// 创建一个数据库连接
        PreparedStatement pre = null;// 创建预编译语句对象，一般都是用这个而不用Statement
        ResultSet result = null;// 创建一个结果集对象
        try {
            String driver = "oracle.jdbc.OracleDriver";
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
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            String sql1 = "select * from razor_event_defination";
            ResultSet result1 = stmt.executeQuery(sql1);
            result1.last();
            int rowcount1 = result1.getRow();
            logger.info("提前的条数为：" + rowcount1);


//

//            result = pre.executeQuery();// 执行查询，注意括号中不需要再加参数
//            while (result.next())
//                // 当结果集不为空时
////                logger.info("学号:" + result.getString("product_id") + " "+"姓名:"
////                        + result.getString("event_identifier")+ " "+"年龄："+ result.getString("group_id"));
//                logger.info("event_id: " + result.getString("event_id") + " " + "event_identifier: " + result.getString("event_identifier") + " " + "productkey：" +
//                        " " + result.getString("productkey") + " " + "event_name: " + result.getString("event_name") + " " + "channel_id: " +
//                        " " + result.getString("channel_id") + " " + "product_id: " + result.getString("product_id") + " " + "user_id: "
//                        + result.getString("user_id") + " " + "create_date: " + result.getDate("create_date") + " " + "active: " + result.getInt("active") + " " + "group_id: " + result.getString("group_id"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowcount;
    }

    public static void main(String[] args) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//        Date date = new Date();

        test t = new test();
        t.testOracle();

    }

}
