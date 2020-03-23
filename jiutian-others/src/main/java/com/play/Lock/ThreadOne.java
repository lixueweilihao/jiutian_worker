package com.play.Lock;


public class ThreadOne implements Runnable {
    private  LockTest lt;
    public void run() {
        String tName = Thread.currentThread().getName();
        System.out.println(tName + "还没有锁，开始主动获取锁......");
        lt.lock.lock();// 主动获取锁
        System.out.println(tName + "获取到锁！");
        try {
            int n = 1 / 0;
            System.out.println(n);
            for (int i = 0; i < 10; i++) {
                System.out.println(tName + ":" + i);
            }
        } catch (Exception e) {

            System.out.println(tName + "出错了！！！");
        }
        finally {
            lt.lock.unlock();
        }
    }
}
