package flink.java;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.fs.StringWriter;
import org.apache.flink.streaming.connectors.fs.bucketing.BucketingSink;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer09;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/2/19  9:09
 */
public class ReadFromKafka {

    public static void main(String[] args) throws Exception {
// create execution environment
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        Map properties= new HashMap();
        properties.put("bootstrap.servers", "10.3.6.7:9092,10.3.6.12:9092,10.3.6.16:9092");
        properties.put("group.id", "bigdata_dataflow_pressure_test");
//        properties.put("enable.auto.commit", "true");
//        properties.put("auto.commit.interval.ms", "1000");
//        properties.put("auto.offset.reset", "earliest");
//        properties.put("session.timeout.ms", "30000");
        properties.put("zookeeper.connect","10.3.6.7:9092,10.3.6.12:9092,10.3.6.16:9092");
//        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("topic", "dataflow_pressure_test");
        // parse user parameters
        ParameterTool parameterTool = ParameterTool.fromMap(properties);
        env.getConfig().disableSysoutLogging();
        env.getConfig().setRestartStrategy(RestartStrategies.fixedDelayRestart(4, 10000));
        env.enableCheckpointing(5000); // create a checkpoint every 5 seconds
        env.getConfig().setGlobalJobParameters(parameterTool);
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        System.out.println(parameterTool.getRequired("topic"));

        FlinkKafkaConsumer09<String> consumer08 = new FlinkKafkaConsumer09<String>(
                parameterTool.getRequired("topic"), new SimpleStringSchema(), parameterTool.getProperties());
        DataStream<String> messageStream = env
                .addSource(consumer08);

        // print() will write the contents of the stream to the TaskManager's standard out stream
        // the rebelance call is causing a repartitioning of the data so that all machines
        // see the messages (for example in cases when "num kafka partitions" < "num flink operators"
        messageStream.rebalance().map(new MapFunction<String, String>() {
            private static final long serialVersionUID = 1L;
            @Override
            public String map(String value) throws Exception {
                return value;
            }
        });
        System.out.println("Starting to consumer data...");
        messageStream.print();
        System.out.println("*********** hdfs ***********************");
//        BucketingSink<String> bucketingSink = new BucketingSink<>("hdfs://10.27.1.141:9000/user/bigdata/lihao/test/"); //hdfs上的路径
        System.setProperty("HADOOP_USER_NAME","bigdata");
        BucketingSink<String> bucketingSink = new BucketingSink<>("hdfs://RouterSit/user/bigdata/lihao/test/log/"); //hdfs上的路径
//        BucketingSink<String> bucketingSink1 = bucketingSink.setBucketer((Bucketer<String>) (clock, basePath, value) -> {
//            return basePath;
//        });
//        bucketingSink.setFSConfig()
        bucketingSink.setWriter(new StringWriter<String>())
//                .setBatchSize(1024 * 1024 * 64 )
                .setUseTruncate(false)
                .setBatchRolloverInterval(1000*60*60);
        messageStream.addSink(bucketingSink);
        env.execute();
    }
}

