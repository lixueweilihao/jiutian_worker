package hadoop.hadoop.com.rpc.three;

/**
 * Created on 2020-02-04
 *
 * @author :hao.li
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 创建一个客户端的类来调用server的远程的服务(也就是类中的方法)
 */
public class Client {
    static MyProtocol remoteServiceProxy;

    public static void main(String[] args) throws IOException {
        System.out.println("***********");
        /**
         * 想要调用远程的服务(也就是远程类的方法)那就需要先搞上一个远程类的代理
         *
         */
        remoteServiceProxy = RPC.getProxy(
                MyProtocol.class,
                1L,
                new InetSocketAddress("127.0.0.1", 9000),
                new Configuration());
        //调用connection的方法来创建连接
        System.out.println("PPPPPPPPPPPPPP");
        ConnectionStatus conn = remoteServiceProxy.connection("我要连接");
        while (!conn.isStatus()) {
            System.out.println(conn.getReason());
            conn = remoteServiceProxy.connection("我要连接");
        }
        System.out.println(conn.getReason());
        //心跳测试,使用timer类
        Timer heartTimer = new Timer();
        /**
         *这个方法是一个周期执行的方法,非阻塞的方法
         * 参数1:周期执行的任务,需要执行的逻辑写到run()
         * 参数2:提交之后演示多长时间执行第一次
         * 参数3:任务间隔多长时间执行一次
         *
         */
        heartTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                String response_heart = remoteServiceProxy.HeatBeat("我是客户端你好呀");
                System.out.println(response_heart);
            }
        }, 2000L, 2000L);
        //测试远程调用Add的方法
        Scanner input = new Scanner(System.in);
        System.out.println("请输入第一个数字:");
        int num1 = input.nextInt();
        System.out.println("请输入第二个数字:");
        int num2 = input.nextInt();
        int result = remoteServiceProxy.Add(num1, num2);
        System.out.println("result:" + result);

    }
}



