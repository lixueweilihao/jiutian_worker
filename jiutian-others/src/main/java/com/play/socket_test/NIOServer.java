package com.play.socket_test;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;


public class NIOServer {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            //创建ServerSocketChannel，监听8080端口
            ServerSocketChannel ssc = ServerSocketChannel.open();
            //设置为非阻塞模式
            ssc.configureBlocking(false);
            ServerSocket ss =ssc.socket();
            ss.bind(new InetSocketAddress(8080));
            //为ssc注册选择器
            Selector selector = Selector.open();
            SelectionKey skey =ssc.register(selector, SelectionKey.OP_ACCEPT);
            //创建处理器
            Handler handler = new Handler(1024);
            while(true){
//                int num = selector.select();//获取通道内是否有选择器的关心事件, 意思是有多少学生报告
//                if(num<1){continue; }
                //等待请求，每次等待阻塞3s，超过3s后线程继续向下运行，如果传入0或者不传入参数则一直阻塞
                if(selector.select(3000) == 0){
                    System.out.println("等待请求超时----");
                    continue;
                }
                System.out.println("处理请求----");
                //获取处理的SelectionKey
                Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();
                while(keyIter.hasNext()){
                    SelectionKey key = keyIter.next();
                    try{
                        //接收到连接请求时
                        if(key.isAcceptable()){
                            handler.handleAccept(key);
                        }
                        //读数据
                        if(key.isReadable()){
                            handler.handleRead(key);
                        }
                    }catch(IOException ex){
                        keyIter.remove();
                        continue;
                    }
                    //处理完后，从待处理的SelectionKey迭代器中移除当前所使用的key
                    keyIter.remove();
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private static class Handler{
        private int bufferSize = 1024;
        private String localCharset = "UTF-8";
        public Handler(int bufferSize){
            this.bufferSize = bufferSize;
        }
        public void handleAccept(SelectionKey key) throws IOException{
            SocketChannel sc = ((ServerSocketChannel) key.channel()).accept();
            sc.configureBlocking(false);
            sc.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufferSize));
        }
        public void handleRead(SelectionKey key) throws IOException{
            //获取Channel
            SocketChannel sc = (SocketChannel) key.channel();
            //获取buffer并重置
            ByteBuffer buffer = (ByteBuffer)key.attachment();
            buffer.clear();
            //没有读到内容则关闭
            if(sc.read(buffer) == -1)
                sc.close();
            else{
                //将buffer转换为读状态
                buffer.flip();
                //将buffer中接收到的值按localCharset格式编码后保存到receivedString
                String receivedString = Charset.forName(localCharset).newDecoder().decode(buffer).toString();
                System.out.println("received from client:" + receivedString);
                //返回数据给客户端
                String sendString = "this data is from Server";
                buffer = ByteBuffer.wrap(sendString.getBytes(localCharset));
                sc.write(buffer);
                sc.close();
            }
        }
    }
}
