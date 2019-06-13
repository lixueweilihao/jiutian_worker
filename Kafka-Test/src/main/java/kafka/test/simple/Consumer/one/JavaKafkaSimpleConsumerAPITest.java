package kafka.test.simple.Consumer.one;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/2/26  12:04
 */
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gerry on 12/21.
 */
public class JavaKafkaSimpleConsumerAPITest {
    public static void main(String[] args) {
        JavaKafkaSimpleConsumerAPI example = new JavaKafkaSimpleConsumerAPI();
        long maxReads = 100;
        String topic = "dataflow";
        int partitionID = 0;

        KafkaTopicPartitionInfo topicPartitionInfo = new KafkaTopicPartitionInfo(topic, partitionID);
        List<KafkaBrokerInfo> seeds = new ArrayList<KafkaBrokerInfo>();
        seeds.add(new KafkaBrokerInfo("kafkasit02broker01..com,kafkasit02broker02..com,kafkasit02broker03..com", 9092));

        try {
            example.run(maxReads, topicPartitionInfo, seeds);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 获取该topic所属的所有分区ID列表
        System.out.println(example.fetchTopicPartitionIDs(seeds, topic, 100000, 64 * 1024, "client-id"));
    }
}
