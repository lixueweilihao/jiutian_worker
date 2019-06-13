package DIANXinShuju;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2018/12/12  22:59
 */
/*
 * 自定义数据类型KpiWritable
 */
public class KpiWritable implements Writable {

    long upPackNum;     // 上行数据包数，单位：个
    long downPackNum;     // 下行数据包数，单位：个
    long upPayLoad;     // 上行总流量，单位：byte
    long downPayLoad;    // 下行总流量，单位：byte

    public KpiWritable() {
    }

    public KpiWritable(String upPack, String downPack, String upPay,
                       String downPay) {
        upPackNum = Long.parseLong(upPack);
        downPackNum = Long.parseLong(downPack);
        upPayLoad = Long.parseLong(upPay);
        downPayLoad = Long.parseLong(downPay);
    }

    @Override
    public String toString() {
        String result = upPackNum + "\t" + downPackNum + "\t" + upPayLoad
                + "\t" + downPayLoad;
        return result;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(upPackNum);
        out.writeLong(downPackNum);
        out.writeLong(upPayLoad);
        out.writeLong(downPayLoad);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        upPackNum = in.readLong();
        downPackNum = in.readLong();
        upPayLoad = in.readLong();
        downPayLoad = in.readLong();
    }

}

