package hadoop.hadoop.com.rpc.hello;

import org.apache.hadoop.ipc.ProtocolSignature;

import java.io.IOException;

public class HelloWorldServiceImpl implements HelloWorldService {

    public long getProtocolVersion(String protocol, long clientVersion) throws IOException {
        return HelloWorldService.versionID;
    }

    public ProtocolSignature getProtocolSignature(String protocol, long clientVersion, int clientMethodsHash)
            throws IOException {
        try {
            return ProtocolSignature.getProtocolSignature(protocol, clientVersion);
//            return new ProtocolSignature(HelloWorldService.versionID, null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String sayHello(String msg) {
        return "hello" + msg;
    }
}
