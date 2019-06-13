package kafka.test.simple.Consumer.one;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/2/26  12:00
 */
public class KafkaBrokerInfo {
    public final String brokerHost;
    // 端口号
    public final int brokerPort;

    /**
     * 构造方法
     *
     * @param brokerHost Kafka服务器主机或者IP地址
     * @param brokerPort 端口号
     */
    public KafkaBrokerInfo(String brokerHost, int brokerPort) {
        this.brokerHost = brokerHost;
        this.brokerPort = brokerPort;
    }

    /**
     * 构造方法， 使用默认端口号9092进行构造
     *
     * @param brokerHost
     */
    public KafkaBrokerInfo(String brokerHost) {
        this(brokerHost, 9092);
    }
}
