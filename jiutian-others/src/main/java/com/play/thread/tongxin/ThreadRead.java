package com.play.thread.tongxin;

import java.io.IOException;
import java.io.PipedInputStream;

/**
 * @Author : lihao
 * Created on : 2020-03-25
 * @Description : TODO描述类作用
 */
public class ThreadRead extends Thread {
    private ReadData read;
    private PipedInputStream input;

    public ThreadRead(ReadData read, PipedInputStream input) {
        super();
        this.read = read;
        this.input = input;
    }
    public void run(){
        try {
            read.readData(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
