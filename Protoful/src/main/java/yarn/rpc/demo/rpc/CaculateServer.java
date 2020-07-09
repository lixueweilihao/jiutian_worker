package yarn.rpc.demo.rpc;

/**
 * @Author : lihao
 * Created on : 2020-07-09
 * @Description : TODO描述类作用
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.RPC.Server;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


/**
 * @Date May 7, 2015
 *
 * @Author dengjie
 *
 * @Note Server Main
 */
public class CaculateServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(CaculateServer.class);

    public static final int IPC_PORT = 9090;

    public static void main(String[] args) {
        try {
            Server server = new RPC.Builder(new Configuration()).setProtocol(CaculateService.class)
                    .setBindAddress("127.0.0.1").setPort(IPC_PORT).setInstance(new CaculateServiceImpl()).build();
            server.start();
            LOGGER.info("CaculateServer has started");
            System.in.read();
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error("CaculateServer server error,message is " + ex.getMessage());
        }
    }

}
