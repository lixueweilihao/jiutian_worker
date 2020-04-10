package phoenix;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


/**
 * @Author : lihao
 * Created on : 2020-04-10
 * @Description : TODO描述类作用
 */
public class test {
    public static void main(String args[]) {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");
            System.out.println("加载phoneix、oracle驱动");
            connection = DriverManager.getConnection("jdbc:phoenix:node01:2181","","");
            System.out.println("Phoenix、oracle 连接成功！");
            statement = connection.createStatement();
            statement.execute("upsert into user values (3, 'note of huhong','lihao')");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
