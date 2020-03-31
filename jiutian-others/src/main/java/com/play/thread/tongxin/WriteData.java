package com.play.thread.tongxin;

import java.io.IOException;
import java.io.PipedOutputStream;

/**
 * @Author : lihao
 * Created on : 2020-03-25
 * @Description : TODO描述类作用
 */
public class WriteData {
    public void writeMethod(PipedOutputStream out){
        System.out.println("write :");
        try {
            for (int i = 0; i < 300; i++) {
                String outData = "" + (i + 1);
                out.write(outData.getBytes());
                System.out.print(outData);
            }
            System.out.println();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
