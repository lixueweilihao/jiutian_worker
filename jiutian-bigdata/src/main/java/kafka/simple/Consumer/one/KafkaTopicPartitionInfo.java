package kafka.simple.Consumer.one;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/2/26  12:02
 */
public class KafkaTopicPartitionInfo {
    public final String topic;
    // 分区id
    public final int partitionID;

    /**
     * 构造函数
     *
     * @param topic       主题名称
     * @param partitionID 分区id
     */
    public KafkaTopicPartitionInfo(String topic, int partitionID) {
        this.topic = topic;
        this.partitionID = partitionID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KafkaTopicPartitionInfo that = (KafkaTopicPartitionInfo) o;

        if (partitionID != that.partitionID) return false;
        return topic != null ? topic.equals(that.topic) : that.topic == null;

    }

    @Override
    public int hashCode() {
        int result = topic != null ? topic.hashCode() : 0;
        result = 31 * result + partitionID;
        return result;
    }
}
