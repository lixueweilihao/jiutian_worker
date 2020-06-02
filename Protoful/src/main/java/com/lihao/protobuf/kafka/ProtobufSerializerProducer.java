package com.lihao.protobuf.kafka;

/**
 * @Author : lihao
 * Created on : 2020-05-28
 * @Description : TODO描述类作用
 */

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * 生产者-使用Protobuf序列化
 */
public class ProtobufSerializerProducer {

    public static final String TOPIC_NAME = "flink-test";
    private  static Properties props = new Properties();

    static{
        props.put("bootstrap.servers", "10.3.6.20:9092,10.3.7.233:9092,10.3.7.232:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "com.lihao.protobuf.kafka.ProtobufSerializer");
    }

    public static void main(String[] args) {
        Producer<String, User> producer = new KafkaProducer<>(props);

        User user = new User(101L,"kafka","serializer@kafka.com",1);
        producer.send(new ProducerRecord<String, User>(TOPIC_NAME, Long.toString(user.getId()), user));

        producer.close();
    }
}

