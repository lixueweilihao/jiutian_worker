package com.mapreduce;//package mapreduce;
//
//import org.apache.hadoop.conf.Configured;
//import org.apache.hadoop.io.IntWritable;
//import org.apache.hadoop.io.LongWritable;
//import org.apache.hadoop.io.Text;
//import org.apache.hadoop.mapreduce.Job;
//import org.apache.hadoop.mapreduce.Mapper;
//import org.apache.hadoop.mapreduce.Reducer;
//import org.apache.hadoop.util.Tool;
//
//import java.io.IOException;
//import java.util.StringTokenizer;
//
///**
// * Created by lihao on 2017/8/3.
// */
//public class WordCountTest  extends Configured implements Tool {
//    public static class Map extends Mapper<LongWritable, Text, Text, IntWritable> {
//        private final static IntWritable one = new IntWritable(1);
//        private Text word = new Text();
//
//        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
//            String line = value.toString();
//            StringTokenizer st = new StringTokenizer(line);
//            while (st.hasMoreTokens()) {
//                String string = st.nextToken();
//                context.write(new Text(string), one);
//            }
//        }
//    }
//
//    public static class Reduce extends Reducer<Text, IntWritable, Text, IntWritable> {
//        public void reduce(Text key, Iterable<IntWritable> value, Context context) throws IOException, InterruptedException {
//            int sum = 0;
//            for (IntWritable iw : value) {
//                sum += iw.get();
//            }
//            context.write(key, new IntWritable(sum));
//        }
//    }
//
////    public int run(String[] args) throws IOException {
////        Job job = new Job(getConf());
////        job.setJarByClass(WordCountTest.class);
////        job.setJobName("wordcount");
////
////        job.setOutputKeyClass(Text.class);
////        job.setOutputValueClass(IntWritable.class);
////        job.setMapperClass(Map.class);
////
////
////    }
//}
