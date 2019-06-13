package com.play.zookeeper;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2018/12/5  17:20
 */

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class zkServer implements Runnable {
    private static final String CONNECT_ADDR = "10.37.74.17:2018,10.37.74.18:2018,10.37.74.19:2018";
    //    private static final int SESSION_TIMEOUT = 5000;
    private CuratorFramework zkClient;

    public static CuratorFramework getClient() {
        CuratorFramework curator = CuratorFrameworkFactory.builder()
                .connectString(CONNECT_ADDR)
                .sessionTimeoutMs(6000)
                .connectionTimeoutMs(6000)
                .retryPolicy(new ExponentialBackoffRetry(
                        1000,
                        10000,
                        3))
//                .namespace(conf.get(Keys.zkNamespace))
                .build();
        return curator;
    }

    public void run() {
        zkClient = getClient();
        zkClient.start();
        PathChildrenCache pathChildrenCache = new PathChildrenCache(zkClient, "/watch", false);
        pathChildrenCache.getListenable().addListener((client, event) -> {
            switch (event.getType()) {
                case CHILD_ADDED:
                    System.out.println(Thread.currentThread().getName() + "child add  " + String.valueOf(event.getData().toString()));
                    break;
                case CHILD_REMOVED:
                    System.out.println(Thread.currentThread().getName() + "child remove  " + String.valueOf(event.getData().toString()));
                    break;
                case CHILD_UPDATED:
                    System.out.println(Thread.currentThread().getName() + "child updated  " + String.valueOf(event.getData().toString()));
                    try {
                        System.out.println("delete begin");
                        Thread.sleep(2000);
                        client.delete().forPath("/watch/test1");
                        System.out.println("delete end");
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println(e.getMessage());
                    }
                    break;
                case INITIALIZED:
                case CONNECTION_LOST:
                case CONNECTION_SUSPENDED:
                case CONNECTION_RECONNECTED:
                    System.out.println("-----------");
                default:
                    break;
            }
        });
        try {
            pathChildrenCache.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        zkServer zk = new zkServer();
        BlockingQueue<Runnable> bqueue = new ArrayBlockingQueue<Runnable>(2);
        ThreadPoolExecutor poll = new ThreadPoolExecutor(1, 2, 20, TimeUnit.MILLISECONDS, bqueue);
        poll.execute(zk);
    }
}
