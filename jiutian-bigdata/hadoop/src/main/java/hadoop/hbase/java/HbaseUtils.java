package hadoop.hbase.java;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.security.User;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.security.UserGroupInformation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright @ 2018
 * All right reserved.
 * https://www.cnblogs.com/junrong624/p/7323483.html
 * https://blog.csdn.net/swnjut/article/details/79758226
 *
 * @author Li Hao
 * @since 2018/12/21  15:56
 */
public class HbaseUtils {
    public static Configuration configuration;
    public static Connection connection;
    public static HBaseAdmin admin;

    public static void main(String[] args) throws IOException {
        HbaseUtils hu = new HbaseUtils();
        hu.init();
        long now = System.currentTimeMillis()/1000;
        long a=now-10000;
//        long priv = now - 3000;
        long start = System.currentTimeMillis();
        deleteTimeRange("dtc_stream",a,now);
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }

    public void init() throws IOException {
        User user = User.create(UserGroupInformation.createRemoteUser("root"));
        configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
        configuration.set("hbase.zookeeper.quorum", "10.3.7.234,10.3.7.233,10.3.7.232");
        try {
            connection = ConnectionFactory.createConnection(configuration, user);
            admin = new HBaseAdmin(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void queryTableByRowKey(String rowkey) throws IOException {
        System.out.println("[hbaseoperation] LoginServer queryTableByRowKey...");

        Table table = connection.getTable(TableName.valueOf("table_book"));
        Get get = new Get(rowkey.getBytes());
        Result result = table.get(get);

        List<Cell> listCells = result.listCells();
        for (Cell cell : listCells) {
            String rowKey = Bytes.toString(CellUtil.cloneRow(cell));
            long timestamp = cell.getTimestamp();
            String family = Bytes.toString(CellUtil.cloneFamily(cell));
            String qualifier = Bytes.toString(CellUtil.cloneQualifier(cell));
            String value = Bytes.toString(CellUtil.cloneValue(cell));

            System.out.println(" ===> rowKey : " + rowKey + ",  timestamp : " + timestamp + ", family : " + family + ", qualifier : " + qualifier + ", value : " + value);
        }

        System.out.println("[hbaseoperation] end queryTableByRowKey...");
    }


    public void queryTableByCondition(String authorName) throws IOException {
        System.out.println("[hbaseoperation] LoginServer queryTableByCondition...");

        Table table = connection.getTable(TableName.valueOf("table_book"));
        Filter filter = new SingleColumnValueFilter(Bytes.toBytes("columnfamily_2"), Bytes.toBytes("author"), CompareOp.EQUAL, Bytes.toBytes(authorName));
        Scan scan = new Scan();

        scan.setFilter(filter);

        ResultScanner scanner = table.getScanner(scan);

        for (Result result : scanner) {
            List<Cell> listCells = result.listCells();
            for (Cell cell : listCells) {
                String rowKey = Bytes.toString(CellUtil.cloneRow(cell));
                long timestamp = cell.getTimestamp();
                String family = Bytes.toString(CellUtil.cloneFamily(cell));
                String qualifier = Bytes.toString(CellUtil.cloneQualifier(cell));
                String value = Bytes.toString(CellUtil.cloneValue(cell));

                System.out.println(" ===> rowKey : " + rowKey + ",  timestamp : " + timestamp + ", family : " + family + ", qualifier : " + qualifier + ", value : " + value);
            }
        }

        System.out.println("[hbaseoperation] end queryTableByCondition...");
    }

    public static void deleteTimeRange(String tableName, Long minTime, Long maxTime) {
        Table table = null;

        try {
            Scan scan = new Scan();
            Filter filter1 = new RowFilter(CompareFilter.CompareOp.EQUAL,
                    new RegexStringComparator("^1.2.3.4.5*"));
            scan.setFilter(filter1);
//            scan.setTimeRange(minTime, maxTime);
            table = connection.getTable(TableName.valueOf(tableName));
            ResultScanner rs = table.getScanner(scan);
            List<Delete> list = getDeleteList(rs);
//            if (list.size() > 0) {
//                table.delete(list);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != table) {
                try {
                    table.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private static List<Delete> getDeleteList(ResultScanner rs) {
        List<Delete> list = new ArrayList<>();
        try {

            for (Result r : rs) {
                byte[] row = r.getRow();
                String s = new String(row);
                System.out.println(s);
            }
        } finally {
            rs.close();
        }
        return list;
    }


}

