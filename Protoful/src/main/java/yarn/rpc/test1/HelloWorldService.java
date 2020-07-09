package yarn.rpc.test1;

import org.apache.hadoop.ipc.VersionedProtocol;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/1/14  15:07
 */
public interface HelloWorldService extends VersionedProtocol {
    long versionID = 1;
    String sayHello(String msg);
}
