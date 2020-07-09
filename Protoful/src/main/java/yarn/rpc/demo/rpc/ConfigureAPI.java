package yarn.rpc.demo.rpc;


/**
 * @Date May 7, 2015
 *
 * @Author dengjie
 *
 * @Note Defined rpc info
 */
public class ConfigureAPI {

    public interface VersionID {
        public static final long RPC_VERSION = 7788L;
    }

    public interface ServerAddress {
        public static final int NIO_PORT = 8888;
        public static final String NIO_IP = "localhost";
    }

}
