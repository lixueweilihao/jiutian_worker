package zookeeper.HA;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.io.IOException;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/2/2  17:33
 */
public class lockZkB {
    private static String zkConnect = "10.37.167.202:2181,10.37.167.203:2181,10.37.167.204:2181";
    private static int zkSessionTimeout = 6000;
    private static int zkConnectTimeout=6000;
    private static int zkbasesleep = 1000;
    private static int zkRetries =3;
    private static int zkMaxSleep =10000;
    private static String nameSpace = "locks";

    private static CuratorFramework zkClient;

    public static void init(){
        zkClient=CuratorFrameworkFactory.builder()
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

    public static void main(String[] args) throws IOException, InterruptedException {
        init();
        zkClient.start();
        ElectorRobot electorRobot = new ElectorRobot(zkClient, "/lock", "master");
        electorRobot.open();
        electorRobot.waitForWin();
        while (true){
            System.out.println("i am leader!!!");
            Thread.sleep(1000);
        }
    }
}
