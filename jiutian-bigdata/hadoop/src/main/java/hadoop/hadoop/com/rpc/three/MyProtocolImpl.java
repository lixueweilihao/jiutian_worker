package hadoop.hadoop.com.rpc.three;

/**
 * Created on 2020-02-03
 *
 * @author :hao.li
 */


import org.apache.hadoop.ipc.ProtocolSignature;
import java.io.IOException;
public class MyProtocolImpl implements MyProtocol {
    private boolean connectionStatus = false;
    /**
     * 这是实现的自定义的MyProtocol的协议的方法的具体的实现
     * 完成客户端输入连接的指令,服务端判断之后返回相应的值
     */
    @Override
    public ConnectionStatus connection(String command) {
        ConnectionStatus conn = new ConnectionStatus();
        String response = null;
        if ("我要连接".equals(command)) {
            response = "连接成功";
            connectionStatus = true;
            conn.setStatus(true);
            conn.setReason(response);
        } else {
            connectionStatus = false;
            response = "连接失败,连接命令是:" + "我要连接,而你的命令是:" + command;
            conn.setStatus(true);
            conn.setReason(response);
        }

        return conn;
    }
    /**
     * 心跳机制,接收到客户端的心跳报文之后将报文返回
     *
     * @param
     * @return
     */
    @Override
    public String HeatBeat(String command_HeartBeant) {
        String response = null;
        if (connectionStatus) {
            response = command_HeartBeant + "谢谢连接";
        }
        return response;
    }

    //远程的ADD的服务
    @Override
    public int Add(int num1, int num2) {
        return num1 + num2;
    }

    //获取versionID
    @Override
    public long getProtocolVersion(String protocol, long clientVersion) throws IOException {
        return versionID;
    }

    //协议签名
    @Override
    public ProtocolSignature getProtocolSignature(String protocol, long clientVersion, int clientMethodsHash) throws IOException {
        return new ProtocolSignature();
    }
}


