package com.hadoop.com.rpc.login;

import java.io.IOException;

import org.apache.hadoop.HadoopIllegalArgumentException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.Server;

/**
 * @author hadoop
 */
public class LoginServer {

    public static void main(String[] args) throws HadoopIllegalArgumentException, IOException {
        Configuration conf = new Configuration();
        System.setProperty("hadoop.home.dir", "/");
        Server server = new RPC.Builder(conf)
                .setBindAddress("localhost")
                .setPort(10000)
                .setProtocol(LoginServiceInterface.class)
                .setInstance(new LoginServiceImplement()).build();
        server.start();
    }
}
