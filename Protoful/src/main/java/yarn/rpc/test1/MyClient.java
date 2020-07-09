package yarn.rpc.test1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.net.InetSocketAddress;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/1/14  15:09
 */
public class MyClient {
    public static void main(String[] args) {
        try {
            Configuration conf = new Configuration();
            HelloWorldService proxy = RPC.getProxy(HelloWorldService.class, HelloWorldService.versionID, new InetSocketAddress("localhost",8888), conf);
            String result = proxy.sayHello("lihao-world");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

