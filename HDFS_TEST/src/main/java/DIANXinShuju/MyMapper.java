package DIANXinShuju;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2018/12/12  23:01
 */
/*
 * 自定义Mapper类，重写了map方法
 */
public class MyMapper extends
        Mapper<LongWritable, Text, Text, KpiWritable> {
    protected void map(
            LongWritable k1,
            Text v1,
            org.apache.hadoop.mapreduce.Mapper<LongWritable, Text, Text, KpiWritable>.Context context)
            throws IOException, InterruptedException {
        String[] spilted = v1.toString().split("\t");
        String msisdn = spilted[1]; // 获取手机号码
        Text k2 = new Text(msisdn); // 转换为Hadoop数据类型并作为k2
        KpiWritable v2 = new KpiWritable(spilted[6], spilted[7],
                spilted[8], spilted[9]);
        context.write(k2, v2);
    };
}
