package com.Lock;

public class ThreadTwo implements Runnable {
    private  LockTest lt;
    public void run() {
        String tName = Thread.currentThread().getName();
        System.out.println(tName + "还没有锁，开始主动获取锁......");
        lt.lock.lock();// 主动获取锁，此时获取不到锁，因为线程1出错了，lock()不会主动释放锁，线程1又没有释放锁，所以就死锁了。
        System.out.println(tName + "tName获取到锁！");
        for (int i = 0; i < 10; i++) {
            System.out.println(tName + ":" + i);
        }
    }
}
