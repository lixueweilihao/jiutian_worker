package yarn.rpc.demo.rpc;

/**
 * @Author : lihao
 * Created on : 2020-07-09
 * @Description : TODO描述类作用
 */

import java.net.InetSocketAddress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.ipc.RPC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Date May 7, 2015
 *
 * @Author dengjie
 *
 * @Note RPC Client Main
 */
public class RPCClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(RPCClient.class);

    public static void main(String[] args) {
        InetSocketAddress addr = new InetSocketAddress("127.0.0.1", CaculateServer.IPC_PORT);
        try {
            RPC.getProtocolVersion(CaculateService.class);
            CaculateService service = (CaculateService) RPC.getProxy(CaculateService.class,
                    RPC.getProtocolVersion(CaculateService.class), addr, new Configuration());
            int add = service.add(new IntWritable(2), new IntWritable(3)).get();
            int sub = service.sub(new IntWritable(5), new IntWritable(2)).get();
            System.out.println("2+3=" + add);
            System.out.println("5-2=" + sub);
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error("Client has error,info is " + ex.getMessage());
        }
    }

}
