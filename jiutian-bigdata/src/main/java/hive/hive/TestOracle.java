package hive.hive;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by lihao on 2017/7/13.
 * 转化存储过程
 */
public class TestOracle {
//    GetToDefinition hive = new GetToDefinition();
    SQLConn sqlconn = new SQLConn();
    private final static Logger logger = Logger.getLogger("TestOracle.class");
    PreparedStatement pre = null;// 创建预编译语句对象，一般都是用这个而不用Statement
    ResultSet result = null;// 创建一个结果集对象
    Statement stmt = null;
    int rowcount = 0;

    /**
     * 将时间转换为yyyyMMdd格式
     * getDate产生的是当天的数据
     */
//    public String getDate(Date date) {
//        Calendar cal = Calendar.getInstance();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//        cal.setTime(date);
//        String year = String.valueOf(cal.get(Calendar.YEAR));
//        String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
//        if (month.length() == 1) {
//            month = 0 + month;
//        }
//        String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH) - 1);
//        if (day.length() == 1) {
//            day = 0 + day;
//        }
//        return year + month + day;
//    }

    public String getLastDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM-ddHHmmss");
        String last = sdf.format(date);
        return last;
    }
    /**
     * 将数据从oracle数据库中提取符合条件的数据插入到razor_event_defination表中
     */

    public void testOracle(String date1, String date2) {
        try {
            Connection con = sqlconn.getSql();
//            stmt = sqlconn.con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
//            String sql1 = "select * from razor_event_defination";
//            rowcount1 = getLines(sql1);
//            logger.info("提前的条数为：" + rowcount1);

//           String sql="select a1.app_key,a1.event_identifier " +
//                    "from ("+ hive.getAll(date1,date2) + ") a1 where a1.cal_number=?";
//            List<Map<String,Object>> list = hive.getAll(date1, date2);
//            for(Map<String,Object> m:list){
//                Set<String> keySet = m.keySet();
//                Iterator<String> iterator = keySet.iterator();
//                while(iterator.hasNext()){
//                    String key = iterator.next();
//                    Object value =m.get(key);
//                    String sql = "insert into table razor_event_log_day value (" +key+ " ," +value+")";
//                    PreparedStatement ps1 = con.prepareStatement(sql);
//                    ps1.executeUpdate();
//                }
//
//            }
            String sql2 = "";
//                    "select ae.event_identifier as event_identifier,cp.product_id as product_id,ep.id as group_id from ("+ hive.getAll(date1,date2)+")ae,razor_channel_product cp,razor_event_group ep where ae.app_key = cp.productkey and cp.product_id = ep.productid and ep.name = 'sys_others'";
            PreparedStatement ps = con.prepareStatement(sql2);
            ResultSet rs = ps.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Date date = new Date();
        TestOracle tq = new TestOracle();
//        tq.testOracle(tq.getDate(date),tq.getLastDate(date));
    }

}
