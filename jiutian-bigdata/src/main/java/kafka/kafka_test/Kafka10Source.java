package kafka.kafka_test;//package kafka.test.kafka_test;
//

//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//
//import java.util.Properties;
//
///**
// * Copyright @ 2018 lihao.com
// * All right reserved.
// * Function：Leader Selector
// *
// * @author Li Hao
// * @since 2018/8/27  11:05
// */
//public class Kafka10Source {
//    public void init() throws Exception {
//        System.setProperty("ldc", "NJXZ");
//        Properties properties = new Properties();
//        properties.put("group.id", "pressure_test_2_oltp_group");
////        properties.put("group.id", "pj_just_test_group_01");
//        /*properties.put("enable.auto.commit", "false");*/
////        properties.put("zookeeper.session.timeout.ms", "60000");
//        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        properties.put("auditor.type", "log");
////        properties.put("zookeeper.connect","kafkasitoltp01zk01..com:2181,kafkasitoltp01zk02..com:2181,kafkasitoltp01zk03..com:2181");
////        properties.put("bootstrap.servers","kafkasitoltp01broker01..com:9092,kafkasitoltp01broker02..com:9092,kafkasitoltp01broker03..com:9092 ");
//
//
////        properties.put("key.deserializer.class", "kafka.deserializer.StringDecoder");
////        properties.put("deserializer.class", "kafka.deserializer.StringDecoder");
//
//
////        SnKafka10Consumer<String, String> snKafkaConsumer = new SnKafka10Consumer<String, String>("pressure_test_oltp_001", properties);
////        snKafkaConsumer.startConsume(new ConuserT());
//
//
////        snKafkaConsumer.subscribe();
//
//
////        snKafkaConsumer.poll(1000);
//
//
////        SnKafkaConsumerFactory snKafkaConsumerFactory = new SnKafkaConsumerFactory();
////        snKafkaConsumerFactory.setTopic("pressure_test_oltp_001");
////        snKafkaConsumerFactory.setProperties(properties);
////        snKafkaConsumerFactory.setListener(new ConuserT());
////        snKafkaConsumerFactory.afterPropertiesSet();
//
////        Thread.sleep(20000);
//        /*snKafkaConsumer.close();*/
//
////        snKafkaConsumerFactory.destroy();
//
//        /*  SnKafkaCommonConsumer<String, String> commonConsumer = new SnKafkaCommonConsumer<>("fxx_testsq_1", properties);*/
//        SnKafkaCommonConsumer<String, String> commonConsumer = new SnKafkaCommonConsumer<>("pressure_test_oltp_001", properties);
//        commonConsumer.startConsume(new ConuserT());
//        Thread.sleep(200000);
//        commonConsumer.close();
//
//    }
//
//    public static void main(String[] args) {
//        Kafka10Source kafka10Source = new Kafka10Source();
//        try {
//            kafka10Source.init();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private class ConuserT implements KafkaMessageListener<String, String> {
////public class ConuserT implements KafkaMessageListener<Object,Object> {
//
//
//        @Override
//        public void onMessage(SnKafkaConsumer snKafkaConsumer, MessageRecord messageRecord) {
//            System.out.println(messageRecord.getMessage());
//
//
//            /*if ()*/
//            /*snKafkaConsumer.commitOffsetSync();*/
//
//        }
//
//    }
//}
