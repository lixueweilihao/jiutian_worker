package com.play.socket_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //创建一个ServerSocket监听8080端口
        ServerSocket server = null;
        try {
            server = new ServerSocket(8080);
            System.out.println("等待客户端连接...");
            Socket socket = server.accept();
            System.out.println("客户端已连接...");
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = is.readLine();
            System.out.println("received frome client:" + line);
            //创建PrintWriter，用于发送数据
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.println("this data is from server");
            pw.flush();
            //关闭资源
            pw.close();
            is.close();
            socket.close();
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

