/**
 * Copyright @ 2018 lihao.com
 * All right reserved.
 *
 * @author Li Hao
 * @since 2018/11/14  14:09
 */

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;

/**
 * 员工增删改查的应用程序
 * @author Administrator
 *
 */
public class EmployeeCRUDApp {
    static Logger log = LoggerFactory.getLogger(EmployeeCRUDApp.class);

    @SuppressWarnings({ "unchecked", "resource" })
    public static void main(String[] args) throws Exception {
        log.info("test .... ");
        // 先构建client
        Settings settings = Settings.builder()
                .put("cluster.name", "common_test")
                .build();

        TransportClient client = new PreBuiltTransportClient(settings);

        TransportAddress transportAddress = new TransportAddress(InetAddress.getByName("10.3.6.7,10.3.6.12,10.3.6.16"), 9300);
        client.addTransportAddresses(transportAddress);

        createEmployee(client);
//        getEmployee(client);
//        updateEmployee(client);
//        deleteEmployee(client);

        client.close();
    }

    /**
     * 创建员工信息（创建一个document）
     * @param client
     */
    private static void createEmployee(TransportClient client) throws Exception {
        IndexResponse response = client.prepareIndex("company", "employee", "1")
                .setSource(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("name", "jack")
                        .field("age", 27)
                        .field("position", "technique")
                        .field("country", "china")
                        .field("join_date", "2017-01-01")
                        .field("salary", 10000)
                        .endObject())
                .get();
        System.out.println(response.getResult());
    }

    /**
     * 获取员工信息
     * @param client
     * @throws Exception
     */
    private static void getEmployee(TransportClient client) throws Exception {
        GetResponse response = client.prepareGet("company", "employee", "1").get();
        System.out.println(response.getSourceAsString());
    }

    /**
     * 修改员工信息
     * @param client
     * @throws Exception
     */
    private static void updateEmployee(TransportClient client) throws Exception {
        UpdateResponse response = client.prepareUpdate("company", "employee", "1")
                .setDoc(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("position", "technique manager")
                        .endObject())
                .get();
        System.out.println(response.getResult());
    }

    /**
     * 删除 员工信息
     * @param client
     * @throws Exception
     */
    private static void deleteEmployee(TransportClient client) throws Exception {
        DeleteResponse response = client.prepareDelete("company", "employee", "1").get();
        System.out.println(response.getResult());
    }

}
