package com.dataflow.spark.kafka; /**
 * Spark Streaming Example
 * Connect to Kafka
 * 
 * @author shiqiang 2019-9-4
 * 
 */

//  package com.edulinks.spark.example;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.*;
import scala.Tuple2;

import java.util.*;

public final class StreamingKafka {
    // private static final Pattern SPACE = Pattern.compile(" ");


    public static void main(String[] args) throws Exception {
        String brokers = "localhost:9092";
        String topics = "test";
        String groupid = "kafkaConsumeGroupTest";

        if (args.length < 2) {
            System.err.println("Usage: StreamingKafka <brokerlist> <topic>");
            System.exit(1);
        }else{
            brokers = args[0];
            topics = args[1];
        }

        SparkConf sparkConf = new SparkConf().setAppName("StreamingKafka");
        //可以直接创建JavaStreamingContext，也可以从现有的JavaSparkContext中创建，本例是从现有创建
        //JavaStreamingContext ssc = new JavaStreamingContext(sparkConf, Durations.seconds(2));
        JavaSparkContext sc = new JavaSparkContext(sparkConf);
        sc.setLogLevel("DEBUG");
        JavaStreamingContext jssc = new JavaStreamingContext(sc, Durations.seconds(2));

        //需要消费的TOPIC列表
        Collection<String> topicSet = new HashSet<>(Arrays.asList(topics.split(",")));

        //Kafka初始化连接参数
        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put("metadata.broker.list", brokers);
        kafkaParams.put("bootstrap.servers", brokers);
        kafkaParams.put("group.id", groupid);
        kafkaParams.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaParams.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaParams.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaParams.put("enable.auto.commit", false);

        JavaInputDStream<ConsumerRecord<Object,Object>> lines = KafkaUtils.createDirectStream(
            jssc,
            LocationStrategies.PreferConsistent(),
            ConsumerStrategies.Subscribe(topicSet, kafkaParams)
            );

        //区分JavaDStream, JavaPariDStream

        JavaPairDStream<String, Integer> counts = lines.flatMap(x -> Arrays.asList(x.value().toString().split(" ")).iterator())
            .mapToPair(x -> new Tuple2<String, Integer>(x, 1))
            .reduceByKey((x,y) -> x + y);

        counts.print();

        jssc.start();
        jssc.awaitTermination();
        jssc.close();

    }
}
