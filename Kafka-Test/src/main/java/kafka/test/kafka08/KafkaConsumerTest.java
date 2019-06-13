package kafka.test.kafka08;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;

public class KafkaConsumerTest {

    public static void main(String[] args) {
        // specify some consumer properties
        Properties props = new Properties();
        props.put("zookeeper.connect", "");
        //group 代表一个消费组
        props.put("group.id", "pj_just_test_group_01");
        //zk连接超时
        props.put("zookeeper.session.timeout.ms", "60000");
        props.put("zookeeper.sync.time.ms", "2000");
        props.put("auto.commit.interval.ms", "60000");
        props.put("auto.offset.reset", "smallest");
        props.put("fetch.message.max.bytes", "10485760");
        props.put("rebalance.max.retries", "10");
        props.put("rebalance.backoff.ms", "5000");
        //序列化类
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        // Create the connection to the cluster
        ConsumerConfig consumerConfig = new ConsumerConfig(props);
        ConsumerConnector consumerConnector = Consumer.createJavaConsumerConnector(consumerConfig);
        StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());
        StringDecoder valueDecoder = new StringDecoder(new VerifiableProperties());

        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("fxx_testsq_1", 1);
        Map<String, List<KafkaStream<String, String>>> topicMessageStreams = consumerConnector.createMessageStreams(map, keyDecoder, valueDecoder);

        ExecutorService executor = Executors.newFixedThreadPool(4);
        int i = 0;
        for(List<KafkaStream<String, String>> streams : topicMessageStreams.values()){
            // consume the messages in the threads
            for (final KafkaStream<String, String> stream : streams) {
                System.out.println(++i);
                executor.submit(new Runnable() {
                    public void run() {
                        ConsumerIterator<String, String> it = stream.iterator();
                        while (it.hasNext()) {
                            try {
                                MessageAndMetadata<String, String> data = it.next();
                                System.out.println("topic" + data.topic());
                                System.out.println("receive：" + data.message());
                            } catch (Exception e) {
                                //TODO
                            }
                        }
                    }
                });
            }
        }
    }
}