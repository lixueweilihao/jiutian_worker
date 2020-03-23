package hadoop.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class mapreducer {

    static String inpath = "hdfs://10.3.6.7:9000/user/hadoop/test/";
    static String outpath = "hdfs://10.3.6.7:9000/user/hadoop/test1/test";

    /**
     * Mapper<KEYIN,VALUEIN,KEYOUT,VALUEOUT>
     * KEYIN   ：输入 KV 数据对 中的key 的数据类型
     * 默认情况下，是MR框架所读到的一行文本的起始偏移量， long ---> LongWritable
     * VALUEIN ：输入输入 KV 数据对 中的value 的数据类型
     * 默认情况下，是MR框架所读到的一行文本的内容， String ---> Text
     * KEYOUT  ：输出 KV 数据对 中的key 的数据类型
     * 用户自定义逻辑处理完成后输出数据中的 key ,在此处是单词， String ---> Text
     * VALUEOUT：输出 KV 数据对 中的value 的数据类型
     * 用户自定义逻辑处理完成后输出数据中的 value 此处是单词次数 ，Integer ---> IntWritabel
     *
     * @author hadoop1
     */
    public static class WordMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
        /**
         * map 方法是提供给 map task 进程来调用的，map task 进程是每读取一行文本来调用一次重写的map 方法
         * map task 在调用 map 方法时，传递的参数
         * key  ： 一行文本的偏移量
         * value:  一行文本的内容
         */
        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            //得到一行文本后 ，将其转成 String
            String line = value.toString();
            //将文本切割
            String[] words = line.split(" ");
            //输出 <单词 , 1> 到上下文
            for (String word : words) {
                context.write(new Text(word), new IntWritable(1));
            }
        }
    }

    /**
     * Reducer<KEYIN,VALUEIN,KEYOUT,VALUEOUT>
     * KEYIN   ： 对应 mapper 阶段输出的结果的 key 的类型
     * <p>
     * VALUEIN ：对应 mapper 阶段输出的结果的 value 的类型
     * <p>
     * KEYOUT  ：reduce 处理完成后输出结果KV对中 key 的类型
     * <p>
     * VALUEOUT：reduce 处理完成后输出结果KV对中 value 的类型
     */
    public static class WordReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
        /**
         * reduce方法提供给 reduce task 进程调用
         * <p>
         * reduce task 会将shuffle阶段分发过来的大量的KV数据进行聚合，聚合的机制是相同的key的 kv 对聚合为一组
         * 然后reduce task 对每一组聚合的KV调用重写的reduce 方法
         * 比如：<hello,1><hello,1><hello,1><hello,1><hello,1>
         * <world,1><world,1><world,1>
         * hello 会调用一次reduce方法进行处理，world组 也会调用一次reduce方法进行处理
         * 调用时传递的参数：
         * key    : 一组KV中的key
         * values : 一组KV中所有value的迭代器
         */
        public void reduce(Text key, Iterable<IntWritable> values, Context context)
                throws IOException, InterruptedException {
            int count = 0;
            //遍历一组KV 中key 对应的 value 迭代器所有的值 进行累加。
            for (IntWritable value : values) {
                count += value.get();
            }
            //输出单词的统计结果
            context.write(key, new IntWritable(count));
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = new Job(conf, "word count");
//        System.setProperty("hadoop.home.dir", "/Users/lixuewei/workspace/hadoop/hadoop-master/hadoop-2.8.3/bin");
        System.setProperty("HADOOP_USER_NAME", "hadoop");
        //设置job所需的jar包的本地路径
        //job.setJar("/home.hadoop1/wc.jar");
        job.setJarByClass(mapreducer.class);
        //指定业务job要使用的 mapper/reduce 业务类
        job.setMapperClass(WordMapper.class);
        job.setReducerClass(WordReducer.class);
        //指定reduce 完成后最终的数据KV类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //指定输入文件的hdfs的所在目录
        FileInputFormat.addInputPath(job, new Path(inpath));
        //指定输出结果的hdfs所在目录
        FileOutputFormat.setOutputPath(job, new Path(outpath));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}

