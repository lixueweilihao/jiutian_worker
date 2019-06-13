package com.play.Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class LockTest {
    Lock lock = new ReentrantLock();
    public static void main(String[] args) {
       ThreadOne to = new ThreadOne();
        ThreadTwo tt = new ThreadTwo();
        Thread t1 = new Thread(to);
        Thread t2 = new Thread(tt);
        System.out.println("lihoa");
       t1.start();
       t2.start();
    }
}


