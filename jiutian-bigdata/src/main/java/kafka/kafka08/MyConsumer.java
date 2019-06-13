package kafka.kafka08;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyConsumer {
    private static final String TOPIC = "nbilling_effective_inc_order_topic"; //kafka创建的topic
    private final String ZK_CONNECT = "kafkasit02zk01..com:2181,kafkasit02zk02..com:2181,kafkasit02zk03..com:2181";
    private static final String GROUPID = "bdes_nbilling_effective_inc_order_topic";
    Properties props;
    private final ConsumerConnector consumer;


    public MyConsumer() {
        props = new Properties();
        props.put("zookeeper.connect", ZK_CONNECT);
        props.put("group.id", GROUPID);
        props.put("zookeeper.session.timeout.ms", "10000");
        //zk同步时间
        props.put("zookeeper.sync.time.ms", "200");
        //自动提交间隔时间
        props.put("auto.commit.interval.ms", "1000");
        //消息日志自动偏移量,防止宕机后数据无法读取
        props.put("auto.offset.reset", "smallest");
        //序列化类
//        props.put("consumer.id","lihao");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
//        props.put("client.id", "demo-consumer-client");
        //构建consumer connection 对象
        consumer = (ConsumerConnector) Consumer.createJavaConsumerConnector(new ConsumerConfig(props));
    }

    public void consume() {
        //指定需要订阅的topic
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(TOPIC, 1);

        //指定key的编码格
        StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());
        //指定value的编码格式
        StringDecoder valueDecoder = new StringDecoder(new VerifiableProperties());

        //获取topic 和 接受到的stream 集合
        Map<String, List<KafkaStream<String, String>>> map = consumer.createMessageStreams(topicCountMap, keyDecoder, valueDecoder);

        //根据指定的topic 获取 stream 集合
        List<KafkaStream<String, String>> kafkaStreams = map.get(TOPIC);

        ExecutorService executor = Executors.newFixedThreadPool(4);

        //因为是多个 message组成 message set ， 所以要对stream 进行拆解遍历
        for (final KafkaStream<String, String> kafkaStream : kafkaStreams) {

            executor.submit(new Runnable() {

                @Override
                public void run() {
                    //拆解每个的 stream
                    ConsumerIterator<String, String> iterator = kafkaStream.iterator();

                    while (iterator.hasNext()) {

                        //messageAndMetadata 包括了 message ， topic ， partition等metadata信息
                        MessageAndMetadata<String, String> messageAndMetadata = iterator.next();
                        System.out.println("message : " + messageAndMetadata.message() + "  partition :  " + messageAndMetadata.partition());

                    }
                }
            });
        }
    }

    public static void main(String[] args) {
        new MyConsumer().consume();
    }
}
