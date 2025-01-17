package yarn.rpc.demo.rpc;

/**
 * @Author : lihao
 * Created on : 2020-07-09
 * @Description : TODO描述类作用
 */

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.ipc.ProtocolInfo;
import org.apache.hadoop.ipc.VersionedProtocol;


/**
 * @Date May 7, 2015
 *
 * @Author dengjie
 *
 * @Note Data calculate service interface
 */
@ProtocolInfo(protocolName = "", protocolVersion = ConfigureAPI.VersionID.RPC_VERSION)
public interface CaculateService extends VersionedProtocol {

    // defined add function
    public IntWritable add(IntWritable arg1, IntWritable arg2);

    // defined sub function
    public IntWritable sub(IntWritable arg1, IntWritable arg2);

}
