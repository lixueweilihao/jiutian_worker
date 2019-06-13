package com.Lock;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;


public class LockTestTwo {
    Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        LockTestTwo ltt = new LockTestTwo();
        ltt.testLock2();
    }

    public void testLock2() {
        //两个线程的锁对象是同一个，当获取锁的线程没有释放锁的时候，就产生了死锁，其他线程只能无止尽地等待
        new Thread() {
            @Override
            public void run() {
                String tName = Thread.currentThread().getName();
                System.out.println(tName + "还没有锁，开始主动获取锁......");

                lock.lock();// 主动获取锁

                System.out.println(tName + "获取到锁！");

                try {
                    int n = 1 / 0;
                    System.out.println(n);

                    for (int i = 0; i < 10; i++) {
                        System.out.println(tName + ":" + i);
                    }
                } catch (Exception e) {
                    lock.unlock();
                    System.out.println(tName + "出错了！！！");
                } finally {
                }

            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                String tName = Thread.currentThread().getName();
                System.out.println(tName + "还没有锁，开始主动获取锁......");

                lock.lock();// 主动获取锁，此时获取不到锁，因为之前出错了，lock()不会主动释放锁，线程又没有释放锁，所以就死锁了。

                System.out.println(tName + "tName获取到锁！");

                for (int i = 0; i < 10; i++) {
                    System.out.println(tName + ":" + i);
                }

            }
        }.start();
    }
}
