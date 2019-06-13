package com.hadoop.com.rpc.login;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.net.InetSocketAddress;

/**
 * Created on 2019-05-16
 *
 * @author :hao.li
 */
public class LoginClient {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        System.setProperty("hadoop.home.dir", "/");
        LoginServiceInterface proxy =  RPC.getProxy(LoginServiceInterface.class, 1L, new InetSocketAddress("localhost", 10000), conf);

        String result = proxy.login("peerslee", "123456");

        System.out.println(result);
    }
}
