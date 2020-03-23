package hadoop.hadoop.com.rpc.three;

/**
 * Created on 2020-02-03
 *
 * @author :hao.li
 */

import org.apache.hadoop.io.Writable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
/**
 * 创建一个实体类来存储连接的状态信息,次类的对象作为客户端和服务端数据传输的载体
 * 因为需要远程传递,所以要实现序列化的接口
 */
public class ConnectionStatus implements Writable {
    private boolean status;//连接的状态
    private String reason;//失败的原因

    public ConnectionStatus() {
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }

    public ConnectionStatus(boolean status, String reason) {
        this.status = status;
        this.reason = reason;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeBoolean(status);
        out.writeUTF(reason);

    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.status=in.readBoolean();
        this.reason=in.readUTF();
    }

}


