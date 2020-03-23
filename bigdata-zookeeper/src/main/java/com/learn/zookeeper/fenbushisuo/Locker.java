package com.learn.zookeeper.fenbushisuo;

public interface Locker {
    void lock(String key, Runnable command);
}
