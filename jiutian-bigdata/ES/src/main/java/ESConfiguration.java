/**
 * Copyright @ 2018 lihao.com
 * All right reserved.
 *
 * @author Li Hao
 * @since 2018/11/14  16:48
 */
/*
* setBulkActions(1000):每添加1000个request，执行一次bulk操作
* setBulkSize(new ByteSizeValue(5, ByteSizeUnit.MB)):每达到5M的请求size时，执行一次bulk操作
* setFlushInterval(TimeValue.timeValueSeconds(5)):每5s执行一次bulk操作
* setConcurrentRequests(1):默认是1，表示积累bulk requests和发送bulk是异步的，其数值表示发送bulk的并发线程数，设置为0表示二者同步的
*setBackoffPolicy(BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100),3)):当ES由于资源不足发生异常
EsRejectedExecutionException重試策略：默认（50ms, 8）,
* 策略算法：start + 10 * ((int) Math.exp(0.8d * (currentlyConsumed)) - 1)
* */


import com.google.common.net.InetAddresses;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.UnknownHostException;
import java.util.function.Supplier;

//import org.elasticsearch.common.network.InetAddresses;


public class ESConfiguration {
    public static final Logger logger = LoggerFactory.getLogger(ESConfiguration.class);
    private String host = "10.3.6.7,10.3.6.12,10.3.6.16";
//    private String host = "10.37.2.142,10.37.2.143,10.37.2.144";


    private Supplier<BulkProcessor.Listener> listener = () -> new BulkProcessor.Listener() {

        @Override
        public void beforeBulk(long l, BulkRequest bulkRequest) {
            System.out.println("begin to worker.");
        }

        @Override
        public void afterBulk(long l, BulkRequest bulkRequest, BulkResponse bulkResponse) {
            System.out.println("End to worker");
        }

        @Override
        public void afterBulk(long l, BulkRequest bulkRequest, Throwable throwable) {
            logger.error("{} data bulk failed,reason :{}", bulkRequest.numberOfActions(), throwable);
        }
    };
    public BulkProcessor bulkProcessor() throws UnknownHostException {
        Settings settings = Settings.builder().put("cluster.name", "kafka_connect_test").build();
        TransportClient client = new PreBuiltTransportClient(settings);
        TransportAddress transportAddress;
        for (String host : host.split(",")) {
            transportAddress = new TransportAddress(InetAddresses.forString(host.trim()), 9200);
            client.addTransportAddresses(transportAddress);
        }

        return BulkProcessor.builder(client, listener.get())
                .setBulkActions(1)
                .setBulkSize(new ByteSizeValue(5, ByteSizeUnit.MB))
                .setFlushInterval(TimeValue.timeValueSeconds(5))
                .setConcurrentRequests(1)
                .setBackoffPolicy(BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100), 3))
                .build();
    }
}
