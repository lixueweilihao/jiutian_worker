package hive.mapreduce;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;
import javax.xml.soap.Text;
import java.io.IOException;

/**
 * Created by Administrator on 2017/7/26.
 */
public class MyMaxTempReducer extends Reducer<Text,IntWritable,Text,IntWritable>{
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int maxValue = Integer.MIN_VALUE;
        for(IntWritable value : values){
            maxValue = Math.max(maxValue,value.get());
        }
        context.write(key,new IntWritable(maxValue));

    }
}
