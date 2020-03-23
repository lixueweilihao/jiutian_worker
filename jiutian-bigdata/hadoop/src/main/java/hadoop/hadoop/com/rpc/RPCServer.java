package hadoop.hadoop.com.rpc;

import hadoop.hadoop.com.rpc.login.LoginServiceImplement;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

public class RPCServer implements MyBizable {

    @Override
    public String doSomething(String str) {
        return str;
    }
    /**
     * @param args
     * @throws Exception
     * @throws
     */
    public static void main(String[] args) throws  Exception {
        System.setProperty("hadoop.home.dir", "/");
        RPC.Server server = new RPC.Builder(new Configuration())
                .setProtocol(LoginServiceImplement.class)
                .setInstance(new RPCServer())
                .setBindAddress("localhost")
                .setPort(8077)
                .build();
        server.start();
    }

}
