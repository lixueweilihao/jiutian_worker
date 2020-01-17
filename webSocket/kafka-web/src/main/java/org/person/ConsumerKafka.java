package org.person;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import static org.person.WebSocket.wbSockets;

/**
 * Created by youtNa on 2017/5/22.
 * https://www.cnblogs.com/yadongliang/p/11713327.html
 */
public class ConsumerKafka extends Thread {

    private KafkaConsumer<String,String> consumer;
    private String topic = "flink-test";

    public ConsumerKafka(){

    }

    @Override
    public void run(){
        Properties props = new Properties();
        props.put("bootstrap.servers", "10.3.7.234:9092,10.3.7.233:9092,10.3.7.232:9092");
        props.put("group.id", "bigdata-DTCsnmpTest05");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "15000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        consumer = new KafkaConsumer<String,String>(props);
        consumer.subscribe(Arrays.asList("DTCsnmpTest05"));
        while (true){
            try {
                ConsumerRecords<String, String> records = consumer.poll(100);
                //Consumer message
                for (ConsumerRecord<String, String> record : records) {
                    //Send message to every client
                    for (WebSocket webSocket :wbSockets){
                        webSocket.sendMessage(record.value());
                    }
                }
            }catch (IOException e){
                System.out.println(e.getMessage());
                continue;
            }
        }
    }

    public void close() {
        try {
            consumer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args){
        ConsumerKafka consumerKafka = new ConsumerKafka();
        consumerKafka.start();
    }
}
