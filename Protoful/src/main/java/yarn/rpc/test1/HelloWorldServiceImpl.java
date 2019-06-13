package yarn.rpc.test1;

import org.apache.hadoop.ipc.ProtocolSignature;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/1/14  15:07
 */
public class HelloWorldServiceImpl implements HelloWorldService {

    public long getProtocolVersion(String protocol, long clientVersion) {
        return 1;
    }

    public ProtocolSignature getProtocolSignature(String protocol, long clientVersion, int clientMethodsHash) {
        try {
            return ProtocolSignature.getProtocolSignature(protocol, clientVersion);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String sayHello(String msg) {
        System.out.println(msg);
        return "hello " + msg;
    }
}

