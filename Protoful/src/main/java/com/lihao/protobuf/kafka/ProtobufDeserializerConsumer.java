package com.lihao.protobuf.kafka;

/**
 * @Author : lihao
 * Created on : 2020-05-28
 * @Description : TODO描述类作用
 */

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.*;

/**
 * 消费者-使用Protobuf反序列化
 */
/**
 * 消费者-使用Protobuf反序列化
 */
public class ProtobufDeserializerConsumer {
    private  static Properties props = new Properties();

    private static boolean isClose = false;

    static{
        props.put("bootstrap.servers", "10.3.6.20:9092,10.3.7.233:9092,10.3.7.232:9092");
//        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
    }

    public  static void main(String args[]){
        KafkaConsumer<String, byte[]> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("flink-test"));

        while (!isClose) {
            ConsumerRecords<String, byte[]> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, byte[]> record : records)
                System.out.printf("key = %s, value = %s%n", record.key(), new User(record.value()));
        }

        consumer.close();
    }
}



