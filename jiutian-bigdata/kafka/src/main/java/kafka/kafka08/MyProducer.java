package kafka.kafka08;

import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.flume.source.avro.AvroFlumeEvent;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


public class MyProducer implements Runnable {
    static final String[] HOST = {"1.2.3.4.5", "2.3.4.5.6", "3.4.5.6.7", "4.5.6.7.8", "5.6.7.8.9", "6.7.8.9.10"};
    static final String[] cpu_OID = {"102_101_101_101_101", "102_101_102_103_103"};
    static final String[] OID = {"102_101_103_107_108"};
    Random df = new Random();

    private static final String TOPIC = "zabbix_test"; //kafka创建的topic
//    private static final String TOPIC = "nsfsale_create_order"; //kafka创建的topic
//    private static String TOPIC ; //kafka创建的topic
    private final String BROKER_LIST = "10.3.7.232:9092,10.3.7.233:9092,10.3.7.234:9092";
    //    private final String BROKER_LIST ="A:2181,B:2181,V:2181";
    //    private static String BROKER_LIST;
    private final String SERIALIZER_CLASS = "kafka.serializer.StringEncoder"; // 序列化类
    //    private final String ZK_CONNECT = "A:2181,B:2181,C:2181";
    SpecificDatumWriter<AvroFlumeEvent> writer = new SpecificDatumWriter<AvroFlumeEvent>(AvroFlumeEvent.class);
    ByteArrayOutputStream tempOutStream = new ByteArrayOutputStream();
    BinaryEncoder encoder;
    String schemaString = "{\"type\":\"record\",\"name\":\"Event\",\"fields\":[{\"name\":\"headers\",\"type\":{\"type\":\"map\",\"values\":\"string\"}},{\"name\":\"body\",\"type\":\"int\"}]}";
    byte[] bytes;
    Logger logger = LoggerFactory.getLogger(MyProducer.class);
    private long number = 1000000000;
    Properties props;
    Producer<String, String> producer;
    private AtomicInteger value = new AtomicInteger(1);

    public MyProducer() {
        props = new Properties();
        props.put("acks", "all");
//        props.put("partitioner.class","kafka.test.kafka08.CidPartitioner");
//        props.put("zk.connect", ZK_CONNECT);
        props.put("serializer.class", SERIALIZER_CLASS);
        props.put("bootstrap.servers", BROKER_LIST);
        props.put("request.required.acks", "1");

        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//
//        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        props.put("value.serializer", "org.apaclimithe.kafka.common.serialization.ByteArraySerializer");
        producer = new KafkaProducer<String, String>(props);
    }

    public void publishMessage(String topic, long count) {
        for (int i = 0; i < count; i++) {
            String runtime = new Date().toString();

            File file = new File("D:/WorkerCode/play_demo_work/Kafka-Test/src/lihao.txt");
//            File file = new File("/home/connect/install/lihao.txt");
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(file));
                String tempString = null;
                //一次读入一行，直到读入null为文件结束
                while ((tempString = reader.readLine()) != null) {
                    //显示行号
                    String msg = "line " + getValue() + ": " + tempString;
                    ProducerRecord<String, String> data = new ProducerRecord<String, String>(topic, msg);
                    producer.send(data);
                    System.out.println("msg = " + data.value());
                    increase();
                    Thread.sleep(1);
                }
//                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e1) {
                    }
                }
            }
        }
//        producer.close();
    }

    public void publishMessageOfOne(String topic, long count) {

//        String CONTENT = "{\"account_number\":\"000\",\"balance\":26210,\"firstname\":\"Teri\",\"lastname\":\"Hester\",\"age\":39,\"gender\":\"M\",\"address\":\"653 Abbey Court\",\"employer\":\"Electonic\",\"email\":\"terihester@electonic.com\",\"city\":\"Martell\",\"state\":\"LIHAO\"}";
        for (int i = 0; i < count; i++)
            try {
                int banka = df.nextInt(200);
                int port = df.nextInt(3);
                int value = df.nextInt(100);
                Date date = new Date();
                SimpleDateFormat myFmt1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+0800'");
//                String CONTENT =myFmt1.format(date)+"$$cron$$notice$$dtc-test$$ dtcfinished 0anacron";
                long l = System.currentTimeMillis();
//                String CONTENT = "{\"time\"" + ":" + "\""+l+"\"" + "," + "\"code\"" + ":" + "\""+cpu_OID[df.nextInt(OID.length)]+"."+ banka + "." + port+"\"" +","+ "\"host\"" + ":" + "\""+HOST[df.nextInt(HOST.length)] +"\""+ "," + "\"nameCN\":\"内存总数\",\"value\"" + ":" + "\""+value +"\""+ "," + "\"nameEN\":\"mem_total\"}";
                String CONTENT = "{\"time\"" + ":" + "\""+l+"\"" + "," + "\"code\"" + ":" + "\""+OID[df.nextInt(OID.length)]+"."+ banka +"\"" +","+ "\"host\"" + ":" + "\""+HOST[df.nextInt(HOST.length)] +"\""+ "," + "\"nameCN\":\"内存总数\",\"value\"" + ":" + "\""+value +"\""+ "," + "\"nameEN\":\"mem_total\"}";
                ProducerRecord<String, String> msg = new ProducerRecord(topic, CONTENT);
                System.out.println("cccccccccccccccccccccccccccccccccccccc");
                producer.send(msg);
                System.out.println("msg = " + getValue() + " : " + msg.value());
                increase();
                Thread.sleep(2000);
            } catch (Exception e) {
                logger.info(e.getMessage());
            }
    }


    public void run() {
        try {
            publishMessageOfOne(TOPIC, number);
//                publishMessage(TOPIC, number);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getValue() {
        return value.get();
    }

    public int increase() {
        return value.incrementAndGet();
    }

    public int increase(int i) {
        return value.addAndGet(i);
    }

    public int decrease() {
        return value.decrementAndGet();
    }

    public int decrease(int i) {
        return value.addAndGet(-i);
    }
}
