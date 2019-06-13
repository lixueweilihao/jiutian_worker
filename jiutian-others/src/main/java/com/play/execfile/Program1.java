package com.play.execfile;

import java.nio.ByteBuffer;


public class Program1 {
    public static void main(String[] args) {
        ByteBuffer bf = ByteBuffer.allocate(10);
        System.out.println("初始化:  " +bf);
        bf.put((byte) 127);
        System.out.println("初始化:  " +bf);
        bf.flip();
        System.out.println("初始化333:  " +bf);
        System.out.println(bf.get(0));
        bf.clear();
        System.out.println("初始化1:  " +bf);
//        System.out.println(bf.get(0));
        bf.put((byte)'H').put((byte)'e').put((byte)'l').put((byte)'l').put((byte)'0');
        System.out.println("增加之后:  " +bf);
        bf.flip();
        System.out.println("flip之后:  " +bf);
        System.out.println("" + (char) bf.get() + (char) bf.get());
        System.out.println("读取两个元素之后:  " +bf);
        bf.position(0);
        System.out.println("读取两个元素之后:  " +bf);
        bf.mark();
        System.out.println("读取两个元素之后1:  " +bf);
        System.out.println("" + (char) bf.get() + (char) bf.get());
        System.out.println("读取两个元素之后2:  " +bf);
        bf.reset();
        System.out.println("读取两个元素之后3:  " +bf);
        bf.compact();
        System.out.println("读取两个元素之后4:  " +bf);
    }
}
