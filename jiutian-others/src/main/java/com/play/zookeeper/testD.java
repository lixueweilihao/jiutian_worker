package com.play.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/2/2  17:59
 */
public class testD {
    private static String zkConnect = "10.37.74.18:2015,10.37.74.19:2015,10.37.74.17:2015";
    private static int zkSessionTimeout = 60000;
    private static int zkConnectTimeout = 60000;
    private static int zkbasesleep = 1000;
    private static int zkRetries = 3;
    private static int zkMaxSleep = 5000;
    private static String nameSpace = "fm";

    private static CuratorFramework zkClient;

    public static void init() {
        zkClient = CuratorFrameworkFactory.builder()
                .connectString(zkConnect.trim())
                .sessionTimeoutMs(zkSessionTimeout)
                .connectionTimeoutMs(zkConnectTimeout)
                .retryPolicy(new ExponentialBackoffRetry(
                        zkbasesleep,
                        zkRetries,
                        zkMaxSleep))
                .namespace(nameSpace)
                .build();
    }

    public static void main(String[] args) throws Exception {
        init();
        zkClient.start();
        String s = zkClient.getData().forPath("/master").toString();
        System.out.println(s);

    }
}
