package yarn.rpc.demo.rpc;

/**
 * @Author : lihao
 * Created on : 2020-07-09
 * @Description : TODO描述类作用
 */

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.ipc.ProtocolSignature;

/**
 * @Date May 7, 2015
 *
 * @Author dengjie
 *
 * @Note Implements CaculateService class
 */
public class CaculateServiceImpl implements CaculateService {

    public ProtocolSignature getProtocolSignature(String arg0, long arg1, int arg2) throws IOException {
        return this.getProtocolSignature(arg0, arg1, arg2);
    }

    /**
     * Check the corresponding version
     */
    public long getProtocolVersion(String arg0, long arg1) throws IOException {
        return ConfigureAPI.VersionID.RPC_VERSION;
    }

    /**
     * Add nums
     */
    public IntWritable add(IntWritable arg1, IntWritable arg2) {
        return new IntWritable(arg1.get() + arg2.get());
    }

    /**
     * Sub nums
     */
    public IntWritable sub(IntWritable arg1, IntWritable arg2) {
        return new IntWritable(arg1.get() - arg2.get());
    }

}
