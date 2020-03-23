package kafka.simple.Consumer.two;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/2/26  15:04
 */
public class Main {
    public static void main(String[] args) {
        KafkaSimpleConsumer consumer = new KafkaSimpleConsumer();
        consumer.init("D:\\WorkerCode\\play_demo_work\\Kafka-Test\\src\\main\\resources\\consumer.properties");

        consumer.run();
    }
}
