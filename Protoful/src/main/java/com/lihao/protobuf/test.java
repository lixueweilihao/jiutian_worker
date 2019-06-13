package com.lihao.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2018/11/21  11:08
 */
public class test {
    public static void main(String[] args) {
        //序列化
        FirstProtobuf.Person.Builder builder = FirstProtobuf.Person.newBuilder();
        builder.setID(117);
        builder.setUrl("bangde");

        // testBuf
        FirstProtobuf.Person info = builder.build();

        byte[] result = info.toByteArray();
        System.out.println("反序列化过程:"+result.toString());



        //反序列化过程
        try {
            FirstProtobuf.Person testBuf = FirstProtobuf.Person.parseFrom(result);
            System.out.println("反序列化过程:"+"\n"+testBuf);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }
}
