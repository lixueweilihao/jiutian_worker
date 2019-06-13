//package kafka.test.kafka10;
//
//
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//import org.apache.kafka.common.serialization.StringDeserializer;
//
//import java.util.Arrays;
//import java.util.Properties;
//
///**
// * zk启动：sh /opt/zookeeper-3.4.11/bin/zkServer.sh start &
// * kafka启动：sh /opt/kafka_2.12-1.1.0/bin/kafka-server-start.sh /opt/kafka_2.12-1.1.0/config/server.properties &
// */
//public class ConsumerTest {
//    private static final String BROKER_LIST = "kafkasitoltp01broker01..com:9092,kafkasitoltp01broker02..com:9092,kafkasitoltp01broker03..com:9092";
//
//    private static final String TOPIC = "pressure_test_oltp_001"; //kafka创建的topic
////    private final String ZK_CONNECT = "10.37.167.204:2015,10.37.167.203:2015,10.37.167.202:2015";
//    private static final String GROUPID = "pressure_test_2_oltp_group";
//    public static void main(String[] args) {
//        Properties props = new Properties();
//
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_LIST);
//        props.put(ConsumerConfig.GROUP_ID_CONFIG ,GROUPID) ;
//        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
//        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put("consumer.id","lihao");
//        props.put(ConsumerConfig.CLIENT_ID_CONFIG,"suning");
//        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
//        consumer.subscribe(Arrays.asList(TOPIC));
//
//        while (true) {
//            ConsumerRecords<String, String> records = consumer.poll(100);
//            for (ConsumerRecord<String, String> record : records) {
//                System.out.printf("offset = %d, key = %s, value = %s,partition =%s%n", record.offset(), record.key(), record.value(),record.partition());
//            }
//        }
//    }
//}