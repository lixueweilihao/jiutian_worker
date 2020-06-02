package com.lihao.protobuf.kafka;

/**
 * @Author : lihao
 * Created on : 2020-05-28
 * @Description : TODO描述类作用
 */

import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * protobuf序列化方式实现kafka消息的的序列化
 */
public class ProtobufSerializer implements Serializer<Protobufable> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {}

    @Override
    public byte[] serialize(String topic, Protobufable data) {
        return data.encode();
    }

    @Override
    public void close() {}
}

