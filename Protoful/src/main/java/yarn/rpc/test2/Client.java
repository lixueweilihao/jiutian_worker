package yarn.rpc.test2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/1/14  14:51
 */
public class Client {

    public Client() throws IOException {
        InetSocketAddress addr = new InetSocketAddress("localhost", 9000) ;
        MyProtocol proxy = RPC.getProxy(MyProtocol.class, MyProtocol.versionID, addr,
                new Configuration()) ;

        System.out.println(proxy.echo());
    }

    public static void main(String[] args) throws IOException {
        new Client() ;
    }
}

