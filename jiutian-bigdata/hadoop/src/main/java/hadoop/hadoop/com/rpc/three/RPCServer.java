package hadoop.hadoop.com.rpc.three;

/**
 * Created on 2020-02-03
 *
 * @author :hao.li
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import java.io.IOException;
//创建一个RPC的客户端
public class RPCServer {
    public static void main(String[] args) {
        /**
         * Builder是RPC的一个内部类看一下他的介绍:
         * Class to construct instances of RPC server with specific options.
         * 翻译:这个类是一个构造RPC server端的有特殊参数的对象的类,也就是说用这个类可以构建服务端的对象
         * 并设置相应的参数
         * Builder的下边有一个构造器  生成一个Builder对象
         *  public Builder(Configuration conf) {
         *       this.conf = conf;
         *     }
         *主要看他的方法,有许多设置参数的方法,每个方法都能返回Builder的对象,所以设置参数的时候可以连续设置
         * 比如这样的方法:给这个对象设置通信的协议,返回设置好通信协议的该对象
         *  public Builder setProtocol(Class<?> protocol) {
         *       this.protocol = protocol;
         *       return this;
         *    }
         *  最重要的方法是一个build的方法
         *  这个方法就是根据这个设置好参数的额Builder的对象创建一个server对象
         *
         */
        RPC.Builder builder = new RPC.Builder(new Configuration());
        /**
         * 设置参数如下:监视的端口号,协议的的名称,远程调用的服务的类,主机名
         * 注意:一般定义一个接口实现RPC的同一接口VersionedProtocol这是RPC的顶级的父类,需要被远程调用的服务的类
         * 都要实现这个接口,二一般我们会创建一个协议接口实现这个顶级的父类,之后将我们的服务实现这个协议的接口
         * 这个协议的接口中规定服务中的那些方法可以供调用,这样客户端根据这个接口就能使用动态代理创建服务类在客户端的
         * 代理,通过代理来调用远程服务
         */

        try {
            RPC.Server server = builder
                    .setBindAddress("192.168.1.4")
                    .setPort(9000)
                    .setProtocol(MyProtocol.class)
                    .setInstance(new MyProtocolImpl())
                    .build();
            //创建完成之后开启server端
            server.start();
            System.out.println("服务端开启了,等待远程调用");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


