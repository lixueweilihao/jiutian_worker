package com.mapreduce;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by lihao on 2017/8/1.
 */
public class WCReducer extends Reducer<Text, LongWritable, Text, LongWritable> {
    protected void reduce(Text V2, Iterable<LongWritable> V2S, Context context) throws IOException, InterruptedException {
        long count = 0;
        for (LongWritable longWritable : V2S) {
            count += longWritable.get();
        }
        context.write(V2, new LongWritable(count));
    }
}
