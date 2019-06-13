package com.play.thread;

/**
 * @Author: lihao
 * @Date: Create in 10:15 2017/12/21
 * @Description:
 * @Modified By:
 */
public class MainTest {
    public static void main(String[] args) {
        SyncThread st = new SyncThread();
        Thread thread1 = new Thread(st, "SyncThread1");
        Thread thread2 = new Thread(st, "SyncThread2");
        thread1.start();
        thread2.start();
    }
}
