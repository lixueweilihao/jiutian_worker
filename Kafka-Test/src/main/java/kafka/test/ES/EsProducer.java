package kafka.test.ES;

import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.flume.source.avro.AvroFlumeEvent;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;


public class EsProducer implements Runnable {

    private static final String TOPIC = "dataflow"; //kafka创建的topic
    private final String BROKER_LIST = "";
    private final String SERIALIZER_CLASS = "kafka.serializer.StringEncoder"; // 序列化类
    Logger logger = LoggerFactory.getLogger(EsProducer.class);
    private String path = "/home/connect/install/lihao.txt";
    private long number = 1000;
    private String num;
    Properties props;
    Producer<String, String> producer;
    private AtomicInteger value = new AtomicInteger(1);

    public EsProducer(String num) {
        this.num = num;
        props = new Properties();
        props.put("acks", "all");
//        props.put("zk.connect", ZK_CONNECT);
        props.put("serializer.class", SERIALIZER_CLASS);
        props.put("bootstrap.servers", BROKER_LIST);
        props.put("request.required.acks", "1");

        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(props);
    }

    public void publishMessage(String topic, int count) {
        for (int i = 0; i < count; i++) {
            String runtime = new Date().toString();

            File file = new File(path);
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(file));
                String tempString = null;
                while ((tempString = reader.readLine()) != null) {
                    //显示行号
                    String msg = "line " + getValue() + ": " + tempString;
                    ProducerRecord<String, String> data = new ProducerRecord<String, String>(topic, msg);
                    producer.send(data);
                    increase();
                }
            } catch (IOException e) {
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

    public void publishMessageOfOne(String topic, long count) throws InterruptedException, IOException {

//        String CONTENT = "{\"name\":\"changzhou\",\"sex\":\"hefei\",\"age\":20}";
        for (int i = 0; i < count; i++) {
            String CONTENT = "{\"id\":" + i + ",\"systemid\":\"olap\",\"name\":\"sit_auto_day_model\",\"database\":\"olap\",\"tablename\":\"test_day_data\",\"granularity\":\"p1d\",\"ttl\":10,\"timemode\":\"agg\",\"datakeys\":\"kk\",\"createtime\":155,\"modeltype\":\"star\",\"istimeline\":1,\"datastart\":2,\"dataend\":1,\"membermodeltype\":\"none\"}";
            try {
                ProducerRecord<String, String> msg = new ProducerRecord<>(topic, CONTENT);
                producer.send(msg);
                increase();
            } catch (Exception e) {
                logger.info(e.getMessage());
            }
        }
    }


    public void run() {
        try {
            publishMessageOfOne(TOPIC, Long.parseLong(num)*number);
//            publishMessage(TOPIC, number);
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