package com.Lock;

import java.io.File;
import java.io.IOException;

/**
 * Copyright @ 2018 lihao.com
 * All right reserved.
 *
 * @author Li Hao
 * @since 2018/9/30  15:30
 */
class Operation implements Runnable{
    private StorageDirectory sd ;
    public Operation(StorageDirectory sd){
        this.sd = sd ;
    }
    public void run() {
        try {
            System.out.println("文件加锁");
            sd.lock() ;
        } catch (Exception e) {
            System.out.println("文件加锁失败") ;
            return ;
        }
        try {
            Thread.currentThread().sleep(10000) ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            try {
                System.out.println("释放文件锁");
                sd.unlock() ;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception{
        File dir = new File("F:\\prd26fm\\fm") ;
        StorageDirectory sd1 = new StorageDirectory(dir) ;
        new Thread(new Operation(sd1)).start() ;
          Thread.sleep(2000) ;//注释掉文件加锁失败，解开注释文件加锁成功
        StorageDirectory sd2 = new StorageDirectory(dir) ;
        new Thread(new Operation(sd2)).start() ;
    }

}


