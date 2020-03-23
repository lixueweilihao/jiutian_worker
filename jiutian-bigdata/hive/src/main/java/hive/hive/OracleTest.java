package hive.hive;
import org.apache.log4j.Logger;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by lihao on 2017/7/20.
 */
public class OracleTest {
    SQLConn sqlcon =  new SQLConn();
//    GetToDefinition hive = new GetToDefinition();
    Connection conn = null;
//    public ResultSet getOracle(String sql){
//        ResultSet rs=null;
//        try {
//            conn = sqlcon.getSql();
//            PreparedStatement ps = conn.prepareStatement(sql);
//            rs = ps.executeQuery();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return rs;
//    }
    public static void main(String[] args) {
//        String sql = "select productkey,product_id from razor_channel_product";
        String str1 ="abc";
        String str2 ="def";

        String sql = "insert into razor_event_defination(event_id,event_identifier,productkey,event_name,channel_id,product_id,user_id,create_date,active,group_id) values (razor_sequence_event.nextval,"+"'"+str1+"'"+",' ',"+"'"+str1+"'"+",'1',"+"'"+str2+"'"+",'1',sysdate,1,"+"'"+str2+"'"+")";
        OracleTest ot = new OracleTest();
//        ot.getOracle(sql);
//        List lis;t=ot.getEventGroup();
//        System.out.println(list);

    }
}
