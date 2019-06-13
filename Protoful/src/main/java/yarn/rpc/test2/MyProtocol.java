package yarn.rpc.test2;

import org.apache.hadoop.ipc.VersionedProtocol;

import java.io.IOException;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/1/14  14:46
 */
public interface MyProtocol extends VersionedProtocol {

    long versionID = 1L ;
    String echo() throws IOException;

}
