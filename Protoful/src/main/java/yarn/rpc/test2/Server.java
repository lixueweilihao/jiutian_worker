package yarn.rpc.test2;

import org.apache.hadoop.HadoopIllegalArgumentException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/1/14  14:50
 */
public class Server {

    public Server() throws HadoopIllegalArgumentException, IOException {
        Configuration conf = new Configuration() ;
        RPC.Server server = new RPC.Builder(conf).
                setProtocol(MyProtocol.class).setInstance(new MyProtocolImpl()).
                setBindAddress("localhost").setPort(9000).setNumHandlers(5).build() ;
        server.start();
    }

    public static void main(String[] args) throws HadoopIllegalArgumentException, IOException {
        new Server() ;
    }
}
