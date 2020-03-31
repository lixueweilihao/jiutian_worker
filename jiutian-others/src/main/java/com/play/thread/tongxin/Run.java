package com.play.thread.tongxin;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @Author : lihao
 * Created on : 2020-03-25
 * @Description : TODO描述类作用
 */
public class Run {
    public static void main(String[] args) {
        try{
            WriteData writeData = new WriteData();
            ReadData readData = new ReadData();
            PipedInputStream inputStream = new PipedInputStream();
            PipedOutputStream outputStream = new PipedOutputStream();
            outputStream.connect(inputStream);
            ThreadRead tr = new ThreadRead(readData,inputStream);
            tr.start();
            Thread.sleep(2000);
            ThreadWrite tw = new ThreadWrite(writeData,outputStream);
            tw.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
