package com.play.http.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Copyright @ 2018 lihao.com
 * All right reserved.
 *  https://www.jianshu.com/p/99c627c6aa9b
 * @author Li Hao
 * @since 2018/9/13  16:50
 */
public class HelloWorld {
    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault(); // 创建httpClient实例
//        HttpGet httpget = new HttpGet("http://10.49.130.41/common/consumer/getConsumeDetail.htm"); // 创建httpget实例
        KafkaMetrics[] kafkaMetrics;
        HttpPost httppost = new HttpPost("http://kafkasit.com/common/consumer/getConsumeDetail.htm"); // 创建httpget实例
        httppost.addHeader("Content-Type", "application/json;charset=utf-8");
        CloseableHttpResponse response = null;
        try {

            Result result = new Result();
            result.setTopicName("pressure_test_oltp_001");
            result.setGroupId("pressure_test_2_oltp_group");
            result.setBrokerList("kafkasitoltp01broker01..com:9092,kafkasitoltp01broker02..com:9092,kafkasitoltp01broker03.g.com:9092");
            result.setDcAbbrName("NJXZ");
//            JSONObject jsonParam = new JSONObject();
////            jsonParam.put("topicName", "storm_ems_app_log_sit_njxz");
////            jsonParam.put("groupId", "bigdata_storm_ems_app_log_sit_njxz");
////            jsonParam.put("brokerList", "kafkasit02broker01..com:9092,kafkasit02broker02..com:9092,kafkasit02broker03..com:9092");
//            jsonParam.put("topicName", "pressure_test_oltp_001");
//            jsonParam.put("groupId", "pressure_test_2_oltp_group");
//            jsonParam.put("brokerList", "kafkasitoltp01broker01..com:9092,kafkasitoltp01broker02..com:9092,kafkasitoltp01broker03..com:9092");
//            jsonParam.put("dcAbbrName", "NJXZ");
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(result);
            System.out.println("bbbb"+json);
            StringEntity entity = new StringEntity(json.toString(), "utf-8");
            System.out.println("aaaaa"+entity);
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httppost.setEntity(entity);
            response = httpClient.execute(httppost);

            String json2 = EntityUtils.toString(response.getEntity(), "utf-8"); // 获取返回实体
            System.out.println(json2);
            kafkaMetrics = jsonParser(json2, "bigdata_storm_ems_app_log_sit_njxz");
            System.out.println(kafkaMetrics);
            for(KafkaMetrics km : kafkaMetrics){
                System.out.println(km.getOwner());
            }
        } catch (ClientProtocolException e) {  // http协议异常
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) { // io异常
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // 执行get请求

//        String json2 = EntityUtils.toString(response.getEntity(), "utf-8"); // 获取返回实
            response.close();

    }
    private static KafkaMetrics[] jsonParser(String str, String groupId) {
        KafkaMetrics[] kafkaMetrics = null;
        try {

            ResultMetrics objectFromJson = JsonUtil.getObjectFromJson(str, ResultMetrics.class);
            List<Metrics> result = objectFromJson.getResult();
            kafkaMetrics = new KafkaMetrics[result.size()-1];
            int i;
            for (i=0; i < result.size()-1; i++) {
                Metrics metrics = result.get(i);
                if(!(metrics.getPartition().equals("total"))) {
                    kafkaMetrics[i] = new KafkaMetrics(metrics.getTopicName(),
                            new Integer(metrics.getPartition()), groupId, metrics.getOffset(), metrics.getLogSize(),
                            metrics.getLog(), metrics.getOwner(), convertTimeToLong(metrics.getLastSeenTime()));
                    System.out.println(kafkaMetrics);
                }
            }
//            return kafkaMetrics;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kafkaMetrics;
    }
    public static Long convertTimeToLong(String time) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if(time==null||time.isEmpty()){
                return 0L;
            }
            date = sdf.parse(time);
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }
}
