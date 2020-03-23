package kafka.kafka10;//package kafka.test.kafka10;
//
//import org.apache.kafka.clients.producer.*;
//
//import java.io.*;
//import java.util.Date;
//import java.util.Properties;
//import java.util.Random;
//import java.util.concurrent.Future;
//import java.util.concurrent.atomic.AtomicInteger;
//
//public class ProducerTest {
//    private static final String BROKER_LIST = "kafkasitoltp01broker01..com:9092,kafkasitoltp01broker02..com:9092,kafkasitoltp01broker03..com:9092";
//    private static final String TOPIC = "pressure_test_oltp_001"; //kafka创建的topic
//    private static int number = 1000000;
//    private AtomicInteger value = new AtomicInteger(1);
//    Producer<String, String> producer;
//
//    public ProducerTest() {
//        Properties props = new Properties();
//        props.put("bootstrap.servers", BROKER_LIST);
//        props.put("acks", "all");
//        props.put("retries", 0);
//        props.put("batch.size", 16384);
//        props.put("linger.ms", 1);
//        props.put("buffer.memory", 33554432);
//        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        producer = new KafkaProducer<String, String>(props);
//    }
//
//    public static void main(String[] args) {
//        ProducerTest pt = new ProducerTest();
//        pt.publishMessage(TOPIC,number);
//
////        producer_test2();
//    }
//
//    private void producer_test2() {
//
//        for (int i = 0; i < 10; i++)
//            producer.send(new ProducerRecord<String, String>(TOPIC, Integer.toString(i), Integer.toString(i)));
//
////        producer.close(SimplePartitioner2);
//    }
//
//    private static void producer_test1() {
//        Random rnd = new Random();
//
//        //    /opt/kafka_2.12-1.1.0/bin/kafka-console-producer.sh --broker-list 192.178.0.111:9092 --sync --topic kafkatopic
//        Properties props = new Properties();
//        props.put("bootstrap.servers", BROKER_LIST);
//        props.put("acks", "all");
//        props.put("retries", 0);
//        props.put("batch.size", 16384);
//        props.put("linger.ms", 1);
//        props.put("buffer.memory", 33554432);
//        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        // 配置partitionner选择策略，可选配置
////        props.put("partitioner.class", "com.dx.SimplePartitioner2");
//
//
//        Producer<String, String> producer = new KafkaProducer<String, String>(props);
//        for (long nEvents = 0; nEvents < number; nEvents++) {
//            long runtime = new Date().getTime();
//            String ip = "192.178.0." + rnd.nextInt(255);
//            String msg = runtime + ",www.example.com," + ip;
//            ProducerRecord<String, String> data = new ProducerRecord<String, String>(TOPIC, ip, msg);
//            Future<RecordMetadata> send = producer.send(data,
//                    new Callback() {
//                        public void onCompletion(RecordMetadata metadata, Exception e) {
//                            if (e != null) {
//                                e.printStackTrace();
//                            } else {
//                                System.out.println("The offset of the record we just sent is: " + metadata.offset());
//                            }
//                        }
//                    });
//        }
//        producer.close();
//    }
//
//    public void publishMessage(String topic, int count) {
//        for (int i = 0; i < count; i++) {
//            String runtime = new Date().toString();
//
//            File file = new File("D:/WorkerSpace/play_demo_work/Kafka-Test/src/lihao.txt");
////            File file = new File("/home/connect/install/lihao.txt");
//            BufferedReader reader = null;
//            try {
//                reader = new BufferedReader(new FileReader(file));
//                String tempString = null;
//                //一次读入一行，直到读入null为文件结束
//                while ((tempString = reader.readLine()) != null) {
//                    //显示行号
//                    String msg = "line " + getValue() + ": " + tempString;
//                    ProducerRecord<String, String> data = new ProducerRecord<String, String>(topic, msg);
//                    producer.send(data);
//                    System.out.println("msg = " + data.value());
////                    Thread.sleep(10);
//                    increase();
//                }
////                reader.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                if (reader != null) {
//                    try {
//                        reader.close();
//                    } catch (IOException e1) {
//                    }
//                }
//            }
//        }
////        producer.close();
//    }
//
//
//    public int getValue() {
//        return value.get();
//    }
//
//    public int increase() {
//        return value.incrementAndGet();
//    }
//
//    public int increase(int i) {
//        return value.addAndGet(i);
//    }
//
//    public int decrease() {
//        return value.decrementAndGet();
//    }
//
//    public int decrease(int i) {
//        return value.addAndGet(-i);
//    }
//}
