//package kafka.test.kafka_test;
//
//import com.suning.kafka.client.consumer.SnKafka10Consumer;
//import com.suning.kafka.client.producer.SnKafka10Producer;
//
//import java.util.Properties;
//
///**
// * Copyright @ 2018 lihao.com
// * All right reserved.
// *
// * @author Li Hao
// * @since 2018/8/27  15:22
// */
//public class Kafka10Producer {
//    public void init() {
//        System.setProperty("ldc", "NJXZ");
//        Properties properties = new Properties();
//        properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
////        properties.put("auditor.type","kafka");
//        properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
//        properties.put("auditor.type","log");
//        properties.put("bootstrap.servers","kafkasitoltp01broker01..com:9092,kafkasitoltp01broker02..com:9092,kafkasitoltp01broker03..com:9092 ");
//
//        SnKafka10Producer snKafka10Producer = new SnKafka10Producer(properties);
//        snKafka10Producer.send("pressure_test_oltp_001",null,"Hello World");
//
//    }
//
//    public static void main(String[] args) {
//        Kafka10Producer kp = new Kafka10Producer();
//        kp.init();
//    }
//}
