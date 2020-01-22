package org.person;

import org.apache.kafka.clients.consumer.KafkaConsumer;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by youtNa on 2017/5/22.
 */
@ServerEndpoint("/wbSocket")
public class WebSocket {
    private Session session;
    public static CopyOnWriteArraySet<WebSocket> wbSockets = new CopyOnWriteArraySet<WebSocket>();
    private KafkaConsumer<String,String> consumer;

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        wbSockets.add(this);
        System.out.println("New session insert,sessionId is "+ session.getId());
        System.out.println("新链接加入,sessionId:"+session.getId());
//        Properties props = new Properties();
//        props.put("bootstrap.servers", "10.3.7.234:9092,10.3.7.233:9092,10.3.7.232:9092");
//        props.put("group.id", "bigdata-flink-test");
//        props.put("enable.auto.commit", "true");
//        props.put("auto.commit.interval.ms", "1000");
//        props.put("session.timeout.ms", "15000");
//        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//
//        consumer = new KafkaConsumer<String,String>(props);
//        System.out.println("tttt");
    }

    @OnClose
    public void onClose(){
        wbSockets.remove(this);
        System.out.println("A session insert,sessionId is "+ session.getId());
//        logger.info("连接关闭,sessionId:"+session.getId());
    }

    @OnMessage
    public void onMessage(String message ,Session session){
        System.out.println("来自客户端的消息:" + message);
        //群发消息
        for (WebSocket item : wbSockets) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
        System.out.println(message + "from " + session.getId());
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
}
