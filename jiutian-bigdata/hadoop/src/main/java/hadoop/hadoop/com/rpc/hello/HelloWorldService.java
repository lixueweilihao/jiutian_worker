package hadoop.hadoop.com.rpc.hello;

import org.apache.hadoop.ipc.VersionedProtocol;

public interface HelloWorldService extends VersionedProtocol {
    long versionID = 1L;
    String sayHello(String msg);
}
