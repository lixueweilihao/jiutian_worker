package com.learn.zookeeper.fenbushisuo;

import java.io.IOException;

/**
 * @Author : lihao
 * Created on : 2020-03-21
 * @Description : TODO描述类作用
 */
public class ZkLockerTest {
    private static Locker locker;

    public static void testZkLocker() throws IOException {
        for (int i = 0; i < 1000; i++) {
            new Thread(()->{
                locker.lock("user_1", ()-> {
                    try {
                        System.out.println(String.format("user_1 time: %d, threadName: %s", System.currentTimeMillis(), Thread.currentThread().getName()));
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }, "Thread-"+i).start();
        }
        for (int i = 1000; i < 2000; i++) {
            new Thread(()->{
                locker.lock("user_2", ()-> {
                    try {
                        System.out.println(String.format("user_2 time: %d, threadName: %s", System.currentTimeMillis(), Thread.currentThread().getName()));
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }, "Thread-"+i).start();
        }

        System.in.read();
    }

    public static void main(String[] args) throws IOException {
        testZkLocker();
    }
}
