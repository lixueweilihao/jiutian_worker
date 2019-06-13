package com.hive;
/**
 * Created by lihao on 2017/7/21.
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by lihao on 2017/7/20.
 */

public class HiveTest {
    HiveDatabase hc = new HiveDatabase();
    String url = "jdbc:hive2://192.168.1.202:10000/razor";
    String user = "razor";
    String password = "razor";
    public String getDate(Date date) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        cal.setTime(date);
        String year = String.valueOf(cal.get(Calendar.YEAR));
        String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
        if (month.length() == 1) {
            month = 0 + month;
        }
        String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH) - 3);
        if (day.length() == 1) {
            day = 0 + day;
        }
        return year + month + day;
    }
    public String getLastDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM-ddHHmmss");
        String lastdate = sdf.format(date);
        return lastdate;
    }
    public void getAll(String date, String lastdate) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = hc.getConn(url, user, password);
        String sql = "select a1.app_key as app_key,a1.event_identifier as event_identifier from (select pt_cal_number as cal_number, app_key,event_identifier,version," +
                "count(distinct device_id) as device_cnt,count(distinct user_identifier) as user_cnt,count(event_identifier) as event_cnt," + lastdate + " from razor.dw_event_log " +
                "where pt_cal_number = " + date + " group by pt_cal_number,app_key,event_identifier,version) a1 where a1.cal_number = " + date + " group by a1.app_key,a1.event_identifier";
        try {
            ps = hc.prepare(con, sql);
            rs = ps.executeQuery();
            int a= rs.getMetaData().getColumnCount();
            while(rs.next()){
                for(int i=1;i<=a;i++){
                    System.out.print(rs.getString(i)+"    ");
                }                System.out.println("    ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Date date = new Date();
        HiveTest hive = new HiveTest();
        hive.getAll(hive.getDate(date),hive.getLastDate(date));

    }
}

