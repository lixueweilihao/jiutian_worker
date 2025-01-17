package hive.mapreduce;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by lihao on 2017/8/1.
 */
public class WCMapper extends Mapper<LongWritable, Text, Text, LongWritable> {
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] words = line.split(" ");
        for (String string : words) {
            context.write(new Text(string), new LongWritable(1));
        }
    }
}
