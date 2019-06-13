//package zookeeper;
//
//import org.apache.curator.framework.CuratorFramework;
//import org.apache.curator.framework.CuratorFrameworkFactory;
//import org.apache.curator.retry.ExponentialBackoffRetry;
//import org.apache.curator.test.TestingServer;
//
//import java.io.File;
//
///**
// * Copyright @ 2018 lihao.com
// * All right reserved.
// *
// * @author Li Hao
// * @since 2018/9/18  20:51
// */
//public class TestingServer_Sample {
//    static String path = "/zookeeper";
//
//    public static void main(String[] args) throws Exception {
//
//        TestingServer server = new TestingServer(2181, new File("/home/admin/zk-book-data"));
//
//        CuratorFramework client = CuratorFrameworkFactory.builder().connectionString(server.getConnectString()).sessionTimeoutMs(5000).retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
//
//        client.start();
//
//        System.out.println(client.getChildren().forPath(path));
//
//        server.close();
//    }
