package hive.hive;
/**
 * Created by lihao on 2017/6/30.
 */

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class GetToDefinition {
    SQLConn sconn = null;
    OracleTest ot = null;
    HiveDatabase hc = null;
    String url = "jdbc:hive2://192.168.1.202:10000/razor";
    String user = "razor";
    String password = "razor";

    public GetToDefinition() {
        sconn = new SQLConn();
        ot = new OracleTest();
        hc = new HiveDatabase();
    }

    /**
     * 将ResultSet集合的内容存放到List中
     */
    public List convertList(ResultSet rs) throws SQLException {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        while (rs.next()) {
            Map<String, String> rowData = new HashMap<String, String>();
            for (int i = 1; i <= 1; i++) {
                rowData.put(rs.getString(i), rs.getString(i + 1));
            }
            list.add(rowData);
        }
        return list;
    }

    /**
     * 获取前一天的时间
     */
    public String getDate(Date date) {
        Calendar cal = Calendar.getInstance();
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

    /**
     * 获取此时操作的时间
     */
    public String getLastDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM-ddHHmmss");
        String lastdate = sdf.format(date);
        return lastdate;
    }

    /**
     * 从hive表中查询数据
     */
    public ResultSet getAll(String date, String lastdate) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = hc.getConn(url, user, password);
        String sql = "select a1.app_key as app_key,a1.event_identifier as event_identifier from (select pt_cal_number as cal_number, app_key,event_identifier,version," +
                "count(distinct device_id) as device_cnt,count(distinct user_identifier) as user_cnt,count(event_identifier) as event_cnt," + lastdate + " from razor.dw_event_log " +
                "where pt_cal_number = " + date + " group by pt_cal_number,app_key,event_identifier,version) a1 where a1.cal_number = " + date + " group by a1.app_key,a1.event_identifier";
        try {
            ps = hc.prepare(con, sql);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 读取razor_event_group表中的数据，并将其存放到List中
     */
    public List getEventGroup() {
        List list = null;
        Connection con = sconn.getSql();
        ResultSet rs = sconn.getOracle(con,"select productid,id from razor_event_group where name = 'sys_others'");
        try {
            list = convertList(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sconn.closeSql();
        return list;
    }

    /**
     * 将从hive表中读取的数据与razor_channel_product表进行比较，
     * 提取其中具有相同app_key值的app_key与envent_identifier的值
     */
    public List getCompator(String date, String lastdate) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        try {
            List list1 = convertList(getAll(date, lastdate));
            System.out.println(list1);
            Connection con = sconn.getSql();
            List list2 = convertList(sconn.getOracle(con,"select productkey,product_id from razor_channel_product"));
            System.out.println(list2);
            for (int i = 0; i < list1.size(); i++) {
                for (int j = 0; j < list2.size(); j++) {
                    Map<String, String> map = new HashMap<String, String>();
                    if (list1.get(i).toString().split("=")[0].toString().substring(1).equals(list2.get(j).toString().split("=")[0].toString().substring(1))) {
                        map.put(list2.get(j).toString().split("=")[1].toString().split("}")[0], list1.get(i).toString().split("=")[1].split("}")[0]);
                        list.add(map);
                    } else {
                        continue;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sconn.closeSql();
        return list;
    }

    /**
     * 将getCompator()方法得到的List与razor_event_group表进行比较，然后将name为sys_others的对应的event_identifier,product_id和group_id存到新的List中
     **/
    public List getEventDefinition(String date, String lastdate) {
        List listone = getCompator(date, lastdate);
        System.out.println("listone的数据为：" + listone);
        List listtwo = getEventGroup();
        System.out.println("listwon的数据为：" + listtwo);
        List<Map<String, String>> listthree = new ArrayList<Map<String, String>>();
        for (int i = 0; i < listone.size(); i++) {
            for (int j = 0; j < listtwo.size(); j++) {
                Map<String, String> map = new HashMap<String, String>();
                if (listone.get(i).toString().split("=")[0].toString().substring(1).equals(listtwo.get(j).toString().split("=")[0].toString().substring(1))) {
                    map.put(listone.get(i).toString().split("=")[1].toString().split("}")[0] + "=" + listone.get(i).toString().split("=")[0].toString().substring(1), listtwo.get(j).toString().split("=")[1].split("}")[0]);
                    listthree.add(map);
                } else {
                    continue;
                }
            }
        }
        return listthree;
    }

    /**
     * 将得到的结果与razor_event_definition表里内容进行对比，将没有的行插入到该表中
     */
    public void getDataFromEventDefinition(String date, String lastdate) {
        Connection con = sconn.getSql();
        List list = getEventDefinition(date, lastdate);
        List list1 = null;
        ResultSet rs = sconn.getOracle(con,"select event_identifier,product_id from razor_event_defination where active =1");
        try {
            list1 = convertList(rs);
            System.out.println("从razor_event_defination表中提取的数据为：" + list1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < list.size(); i++) {
            boolean flag = false;
            for (int j = 0; j < list1.size(); j++) {
                if ((list.get(i).toString().split("=")[0].toString().substring(1).equals(list1.get(j).toString().split("=")[0].toString().substring(1))) && (list.get(i).toString().split("=")[1].toString().equals(list1.get(j).toString().split("=")[1].split("}")[0]))) {
                    flag = true;
                    break;
                } else {
                    flag = false;
                    continue;
                }
            }
            if (flag == false) {
                String sql1 = "insert into razor_event_defination(event_id,event_identifier,productkey,event_name,channel_id,product_id,user_id,create_date,active,group_id) values (razor_sequence_event.nextval," + "'" + list.get(i).toString().split("=")[0].toString().substring(1) + "'" + ",' '," + "'" + list.get(i).toString().split("=")[0].toString().substring(1) + "'" + ",'1'," + "'" + list.get(i).toString().split("=")[1] + "'" + ",'1',sysdate,'1'," + "'" + list.get(i).toString().split("=")[1].split("}")[0] + "'" + ")";
                try {
                    PreparedStatement ps = con.prepareStatement(sql1);
                    ps.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        sconn.closeSql();
    }

    public static void main(String[] args) {
        Date date = new Date();
        GetToDefinition getToDefinition = new GetToDefinition();
//        List list3 = getToDefinition.getEventDefinition(getToDefinition.getDate(date), getToDefinition.getLastDate(date), sql);
//        System.out.print(list3);
        getToDefinition.getDataFromEventDefinition(getToDefinition.getDate(date), getToDefinition.getLastDate(date));
    }
}
