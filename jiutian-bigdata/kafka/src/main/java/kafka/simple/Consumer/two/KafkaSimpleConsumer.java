package kafka.simple.Consumer.two;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/2/26  14:50
 */
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KafkaSimpleConsumer implements Runnable {
    private static final Logger logger = LogManager.getLogger(KafkaSimpleConsumer.class);

    private KafkaConfig kafkaConfig = null;
    private ExecutorService executor = null;
    private boolean inited = false;

    public void run() {
        if (!inited) {
            logger.error("uninit, init first!");
            return;
        }
        File file = new File(kafkaConfig.checkpoint);
        if (!file.exists()) {
            file.mkdir();
        }
        executor = Executors.newFixedThreadPool(kafkaConfig.partitionNum);
        int threadNum = 0;
        for (; threadNum < kafkaConfig.partitionNum; ++threadNum) {
            file = new File(kafkaConfig.checkpoint +"/partition" + threadNum);
            if (!file.exists() && !file.isDirectory()) {
                file.mkdir();
            }
            logger.info("begin submit partition msg task thread");
            executor.submit(new PartitionMsgTask(kafkaConfig, threadNum));
        }
    }

    public int init(String confFile) {
        Properties props = new Properties();
        kafkaConfig = new KafkaConfig();
        try {
            FileInputStream in = new FileInputStream(confFile);
            props.load(in);
        } catch (FileNotFoundException e) {
            logger.error("kafka config file not found. file name:" + confFile);
            return -1;
        } catch (IOException e) {
            logger.error("properties load file failed");
            return -1;
        }
        kafkaConfig.topic = props.getProperty("topic");
        kafkaConfig.port = Integer.parseInt(props.getProperty("port"));
        kafkaConfig.partitionNum = Integer.parseInt(props.getProperty("partitionNum"));
        kafkaConfig.checkpoint = props.getProperty("checkpoint");
        kafkaConfig.patchSize = Integer.parseInt(props.getProperty("patchSize"));
        String startPoint = props.getProperty("subscribeStartPoint");
        if (!startPoint.equals("latest") && !startPoint.equals("earliest")) {
            logger.error("config file startPoint error. startPoint must be latest or earliest");
            return -1;
        }
        kafkaConfig.subscribeStartPoint = startPoint;
        String brokerList = props.getProperty("brokerList");
        String[] brokers = brokerList.split(",");
        kafkaConfig.replicaBrokers = new ArrayList<String>();
        for (String str : brokers) {
            kafkaConfig.replicaBrokers.add(str);
        }
        inited = true;
        logger.info("init success. kafkaConfig:" + kafkaConfig.toString());
        return 0;
    }

}
