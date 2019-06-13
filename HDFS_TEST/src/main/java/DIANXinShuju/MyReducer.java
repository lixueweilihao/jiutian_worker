package DIANXinShuju;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2018/12/12  23:02
 */
/*
 * 自定义Reducer类，重写了reduce方法
 */
public class MyReducer extends
        Reducer<Text, KpiWritable, Text, KpiWritable> {
    protected void reduce(
            Text k2,
            java.lang.Iterable<KpiWritable> v2s,
            org.apache.hadoop.mapreduce.Reducer<Text, KpiWritable, Text, KpiWritable>.Context context)
            throws IOException, InterruptedException {
        long upPackNum = 0L;
        long downPackNum = 0L;
        long upPayLoad = 0L;
        long downPayLoad = 0L;
        for (KpiWritable kpiWritable : v2s) {
            upPackNum += kpiWritable.upPackNum;
            downPackNum += kpiWritable.downPackNum;
            upPayLoad += kpiWritable.upPayLoad;
            downPayLoad += kpiWritable.downPayLoad;
        }

        KpiWritable v3 = new KpiWritable(upPackNum + "", downPackNum + "",
                upPayLoad + "", downPayLoad + "");
        context.write(k2, v3);
    };
}
