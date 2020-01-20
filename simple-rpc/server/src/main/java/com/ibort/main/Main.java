package com.ibort.main;

import com.ibort.api.HelloService;
import com.ibort.frame.server.Server;
import com.ibort.frame.server.ServerCenter;
import com.ibort.service.HelloServiceImpl;

import java.io.IOException;

/**
 * @author jeffy
 * @date 2018/6/17
 **/
public class Main {

    /**
     * start server
     * @param args
     */
    public static void main(String[] args) {
        Server serviceServer = new ServerCenter(8088);
        serviceServer.register(HelloService.class, HelloServiceImpl.class);
        try {
            System.err.println("server start……");
            serviceServer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
