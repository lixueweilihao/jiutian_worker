package com.hadoop.com.rpc;

import java.net.InetSocketAddress;

import com.hadoop.com.rpc.login.LoginServiceImplement;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

public class RPCClient {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        System.setProperty("hadoop.home.dir", "/");
        LoginServiceImplement proxy = RPC.getProxy(LoginServiceImplement.class, 1,new InetSocketAddress("localhost", 8077) , new Configuration());
        String result = proxy.login("服务端","123");
        System.out.println(result);
        RPC.stopProxy(proxy);
    }

}
