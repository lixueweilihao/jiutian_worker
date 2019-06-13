package socket_test;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;


public class NIOServerNew {

    public static void main(String[] args) {
        try {
            //创建ServerSocketChannel，监听8080端口
            ServerSocketChannel ssc = ServerSocketChannel.open();//新建NIO通道
            ssc.configureBlocking(false);//使通道为非阻塞
            ServerSocket ss = ssc.socket();//创建基于NIO通道的socket连接
            ss.bind(new InetSocketAddress("127.0.0.1", 8080));//新建socket通道的端口
            //为ssc注册选择器
            Selector selector = Selector.open();
            SelectionKey skey = ssc.register(selector, SelectionKey.OP_ACCEPT);
            //创建处理器
//            Handler handler = new Handler(1024);
            while (true) {
                int num = selector.select();//获取通道内是否有选择器的关心事件, 意思是有多少学生报告
                if (num < 1) {
                    continue;
                }
                //等待请求，每次等待阻塞3s，超过3s后线程继续向下运行，如果传入0或者不传入参数则一直阻塞
                System.out.println("处理请求----");
                //获取处理的SelectionKey
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()) {
                    try {
                        SelectionKey key = it.next(); //先得到这个学生的编号key
                        //判断是新生报道还是老生问问题
                        if (key.isAcceptable()) {
                            //这是招生老师的Key说明是新生注册，先找到招生老师，再由招生老师找到新生，就可以给新生注册学号了
                            ServerSocketChannel serverChanel = (ServerSocketChannel) key.channel(); //通过key把学校和老师找到了
                            //从serverSocketChannel中创建出与客户端的连接socketChannel 有了老师才有学生，不可能我教计算机的，来一个想学李小龙的都让他报名
                            SocketChannel sc = serverChanel.accept(); //学生报名成功
                            sc.configureBlocking(false);
                            // 把新连接注册到选择器,新生被接收后给注册个新学号
                            SelectionKey newKey = sc.register(selector,
                                    SelectionKey.OP_READ); //注册学号成功，并分配学生的权限
                            it.remove(); //新生注册任务完成了，呵呵
                            System.out.println("Got connection from " + sc);
                        }
                        //读客户端数据的事件,此时有客户端发数据过来,客户端事件 这是老学生来问问题了。
                        if (key.isReadable()) {
                                // 读取数据 ，接受学生的问题
                                SocketChannel sc = (SocketChannel) key.channel(); //通过学号知道是谁问的问题

                                //下面接受问题
                                int bytesEchoed = 0;
                                ByteBuffer echoBuffer = (ByteBuffer) key.attachment();
                                while ((bytesEchoed = sc.read(echoBuffer)) > 0) {
                                    System.out.println("bytesEchoed:" + bytesEchoed);
                                }
                                echoBuffer.flip();
                                System.out.println("limit:" + echoBuffer.limit());
                                byte[] content = new byte[echoBuffer.limit()];
                                echoBuffer.get(content);
                                String result = new String(content);
//                                doPost(result, sc); //相应老师会去做回答的，细节自己去写吧
                                echoBuffer.clear();
                                it.remove(); //任务完成，记得上面也是一样，要remove掉，否则下一次又来一次任务，就死循环了
                            }
                    } catch (Exception e) {
                    }
                }
            }
        } catch (ClosedChannelException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
