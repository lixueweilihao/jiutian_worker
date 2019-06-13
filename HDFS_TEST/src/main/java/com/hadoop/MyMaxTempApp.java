package com.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Created by Administrator on 2017/7/27.
 */
public class MyMaxTempApp {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
//        if(args.length!=2){
//            System.out.println("Usage:MaxTemperature <input path> <output path>");
//            System.exit(-1);
//        }
        System.setProperty("hadoop.home.dir", "/Users/lixuewei/workspace/hadoop/hadoop-master/hadoop-2.8.3/bin");
//        System.setProperty("HADOOP_USER_NAME", "bigdata");
        Configuration config = new Configuration();
        config.set("fs.defaultFS", "hdfs://10.3.6.7:9000");
        config.set("yarn.resourcemanager.hostname", "10.3.6.7");
        FileSystem fs = FileSystem.get(config);

        Job job = Job.getInstance();
        job.setJarByClass(MyMaxTempApp.class);
        job.setJobName("Max temp");
        FileInputFormat.addInputPath(job,new Path("hdfs://10.27.1.141:9000/user/bigdata/lihao/test/029070-99999-1901"));
//        FileInputFormat.setInputDirRecursive();
        FileOutputFormat.setOutputPath(job,new Path("hdfs://10.27.1.141:9000/user/bigdata/lihao/out"));
        job.setMapperClass(MyMaxTempMapper.class);
        job.setReducerClass(MyMaxTempReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        System.out.println(job.waitForCompletion(true) ? 0 :1);
    }
}