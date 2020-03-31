package com.play.thread.tongxin;

import java.io.IOException;
import java.io.PipedInputStream;

/**
 * @Author : lihao
 * Created on : 2020-03-25
 * @Description : TODO描述类作用
 */
public class ReadData {
    public void readData(PipedInputStream input) throws IOException {
        try{
            System.out.println("read :");
            byte[] byteArray = new byte[20];
            int readLength = input.read(byteArray);
            while(readLength!=-1){
                String newData = new String(byteArray,0,readLength);
                System.out.print(newData);
                readLength = input.read(byteArray);
            }
            System.out.println();

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            input.close();
        }
    }
}
