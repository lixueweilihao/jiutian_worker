package hadoop.hadoop.com.rpc.hello;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.net.InetSocketAddress;

/**
 * 客户端
 */
public class MyClient {
    public static void main(String[] args) {
        try {
            Configuration conf = new Configuration();
//            System.setProperty("hadoop.home.dir", "/");
            HelloWorldService proxy = RPC.getProxy(HelloWorldService.class, HelloWorldService.versionID, new InetSocketAddress("192.168.1.4",8888), conf);
            String result = proxy.sayHello("world");
            System.out.println("lihao:"+result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
