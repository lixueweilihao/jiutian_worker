package com.play.thread.tongxin;

import java.io.PipedOutputStream;

/**
 * @Author : lihao
 * Created on : 2020-03-25
 * @Description : TODO描述类作用
 */
public class ThreadWrite extends Thread {
    private WriteData write;
    private PipedOutputStream out;
    public ThreadWrite(WriteData write,PipedOutputStream out){
        super();
        this.write=write;
        this.out=out;
    }
    public void run(){
        write.writeMethod(out);
    }
}
