package com.lihao.protobuf.kafka;

/**
 * @Author : lihao
 * Created on : 2020-05-28
 * @Description : TODO描述类作用
 */
public interface Protobufable {

    //将对象转为字节数组
    public byte[] encode();

}

