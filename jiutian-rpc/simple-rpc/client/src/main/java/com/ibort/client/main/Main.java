package com.ibort.client.main;

import com.ibort.api.HelloService;
import com.ibort.frame.server.RPCClient;

import java.net.InetSocketAddress;

/**
 * @author jeffy
 * @date 2018/6/16
 **/
public class Main {
    public static void main(String[] args) {
        HelloService service = RPCClient.getRemoteProxyObj(HelloService.class, new InetSocketAddress("10.3.7.233", 8088));
        System.out.println("客户端调用接口,返回结果为：" + service.sayHi("test"));
    }
}
