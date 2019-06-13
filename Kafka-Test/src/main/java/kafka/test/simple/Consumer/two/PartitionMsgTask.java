package kafka.test.simple.Consumer.two;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/2/26  14:49
 */
import kafka.javaapi.consumer.SimpleConsumer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import kafka.api.FetchRequest;
import kafka.api.FetchRequestBuilder;
import kafka.common.ErrorMapping;
import kafka.javaapi.FetchResponse;
import kafka.javaapi.PartitionMetadata;
import kafka.message.MessageAndOffset;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class PartitionMsgTask implements Runnable {

    private static final Logger logger = LogManager.getLogger(PartitionMsgTask.class);

    private KafkaConfig kafkaConfig = null;

    private String filePath = null;
    private int partitionIndex = 0;
    private SimpleConsumer consumer = null;
    private String leadBroker = null;
    private long readOffset = 0;
    private String clientName = null;

    public PartitionMsgTask(KafkaConfig config, int index) {
        partitionIndex = index;
        this.kafkaConfig = config;
        filePath = kafkaConfig.checkpoint + "/partition" + partitionIndex;
    }

    public void shutdown() {
        if (consumer != null) {
            consumer.close();
        }
    }

    public void run() {
        logger.info("partition:" + partitionIndex + " config:" + kafkaConfig.toString());

        PartitionMetadata metadata = KafkaUtil
                .findLeader(kafkaConfig.replicaBrokers, kafkaConfig.port, kafkaConfig.topic, partitionIndex);
        if (metadata == null) {
            logger.error("Can't find metadata for Topic:" + kafkaConfig.topic
                    + " and Partition:" + partitionIndex + ". Exiting");
            return;
        }
        if (metadata.leader() == null) {
            logger.error("Can't find Leader for Topic:" + kafkaConfig.topic
                    + " and Partition:" + partitionIndex + ". Exiting");
            return;
        }

        leadBroker = metadata.leader().host();
        clientName = "Client_" + kafkaConfig.topic + "_" + partitionIndex;
        logger.info("leadBroker:" + leadBroker + " client:" + clientName);
        consumer = new SimpleConsumer(leadBroker, kafkaConfig.port, 100000, 64 * 1024, clientName);
        // first time to get offset
        readOffset = getOffset();
        logger.info("first time get offset :" + readOffset);
        if (readOffset == -1) {
            logger.error("get offset failed");
            return;
        }

        logger.info("partition" + partitionIndex + " thread run success.");
        while (true) {
            try {
                int ret;
                List<String> messageList = new ArrayList<String>();
                // subscribe message
                long offset = subsribe(kafkaConfig.patchSize, messageList);
                if (offset < 0) {
                    logger.error("subscribe message failed. will continue");
                    continue;
                }
                // todo process messageList
                // todo 如果处理失败，可重试或者继续，自己选择是否保存offset
                // save offset
                ret = saveOffset(offset);
                if (ret != 0) {
                    if (saveOffset(offset) != 0) {
                        continue;
                    }
                }
                readOffset = offset;
            } catch (Exception e) {
                logger.error("exception :" + e.getMessage());
            }
        }
    }

    /**
     * 获取offset
     * @return
     */
    public long getOffset() {
        long offset = -1;
        // get from file
        String offsetFile = filePath + "/" + "offset";
        BufferedReader reader = null;
        try {
            File file = new File(offsetFile);
            reader = new BufferedReader(new FileReader(file));
            String tempStr = reader.readLine();
            offset = Long.parseLong(tempStr);
            reader.close();
            return offset;
        } catch (FileNotFoundException e) {
            logger.info("offset file:" + offsetFile + " not found. will get the "
                    + kafkaConfig.subscribeStartPoint + " offset.");
        } catch (IOException e) {
            logger.error("get offset from file exception");
            return -1;
        }

        if (kafkaConfig.subscribeStartPoint.equals("earliest")) {
            // get earliest offset
            offset = KafkaUtil.getSpecificOffset(consumer,
                    kafkaConfig.topic, partitionIndex,
                    kafka.api.OffsetRequest.EarliestTime(), clientName);
        } else if (kafkaConfig.subscribeStartPoint.equals("latest")) {
            // get latest offset
            offset = KafkaUtil.getSpecificOffset(consumer,
                    kafkaConfig.topic, partitionIndex,
                    kafka.api.OffsetRequest.LatestTime(), clientName);
        } else {
            logger.error("kafka config start point error");
        }
        return offset;
    }

    /**
     * 保持offset
     * @param offset
     * @return
     */
    public int saveOffset(long offset) {
        String offsetFile = filePath + "/" + "offset";
        try {
            File file = new File(offsetFile);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(String.valueOf(offset));
            fileWriter.close();
        } catch (IOException e) {
            logger.error("save offset failed");
            return -1;
        }
        return 0;
    }

    /**
     * 订阅消息
     * @param maxReads
     * @param messageList
     * @return
     * @throws Exception
     */
    public long subsribe(long maxReads, List<String> messageList) throws Exception {
        if (messageList == null) {
            logger.warn("messageList is null");
            return -1;
        }
        int numErrors = 0;
        long offset = readOffset;
        while (maxReads > 0) {
            if (consumer == null) {
                consumer = new SimpleConsumer(leadBroker, kafkaConfig.port, 100000, 64 * 1024, clientName);
            }
            FetchRequest request = new FetchRequestBuilder().clientId(clientName)
                    .addFetch(kafkaConfig.topic, partitionIndex, offset, 100000).build();
            FetchResponse fetchResponse = consumer.fetch(request);

            if (fetchResponse.hasError()) {
                logger.warn("fetch response has error");
                numErrors++;
                short code = fetchResponse.errorCode(kafkaConfig.topic, partitionIndex);
                logger.warn("Error fetching data from the Broker:" + leadBroker + " error code: " + code);
                if (numErrors > 3) {
                    return -1;
                }
                if (code == ErrorMapping.OffsetOutOfRangeCode()) {
                    // We asked for an invalid offset. For simple case ask for the last element to reset
                    // offset = KafkaUtil.getLastOffset(consumer, kafkaConfig.topic, partitionIndex,
                    //        kafka.api.OffsetRequest.LatestTime(), clientName);
                    logger.warn("offset out of range. will get a new offset");
                    offset = getOffset();
                    continue;
                }
                consumer.close();
                consumer = null;
                leadBroker = KafkaUtil.findNewLeader(leadBroker,
                        kafkaConfig.replicaBrokers,
                        kafkaConfig.port,
                        kafkaConfig.topic,
                        partitionIndex);
                continue;
            }
            numErrors = 0;

            long numRead = 0;
            for (MessageAndOffset messageAndOffset : fetchResponse.messageSet(kafkaConfig.topic, partitionIndex)) {
                long currentOffset = messageAndOffset.offset();
                if (currentOffset < offset) {
                    logger.warn("Found an old offset: " + currentOffset + " Expecting: " + offset);
                    continue;
                }
                offset = messageAndOffset.nextOffset();
                ByteBuffer payload = messageAndOffset.message().payload();

                byte[] bytes = new byte[payload.limit()];
                payload.get(bytes);
                String message = new String(bytes, "UTF-8");
                messageList.add(message);
                numRead++;
                maxReads--;
            }
            if (numRead == 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // do nothing
                }
            }
        }
        return offset;
    }
}
