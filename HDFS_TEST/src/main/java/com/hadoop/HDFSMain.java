package com.hadoop;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright @ 2018 lihao.com
 * All right reserved.
 *
 * @author Li Hao
 * @since 2018/9/26  14:27
 */
public class HDFSMain {
    public static String uri = "hdfs://10.3.6.7:9000/";
    //    public static String uri = "hdfs://10.37.167.204:9000/";
    //    public static String str = "/user/bigdata/dataflowpre/20180706";
    public static String str = "/logs/test_hdfs";

    //        public static String uri = "hdfs://SuningHadoop2:9000/";
    public static void main(String[] args) throws Exception {
//        System.setProperty("hadoop.home.dir", "D:\\winutils-master\\winutils-master\\hadoop-2.8.3");
//        HDFSMain hdfs = new HDFSMain();
        HdfsUtils hd = new HdfsUtils();
        String str = "user/bigdata" ;
        hd.createNewHDFSFile(str, "abcddd");
        for (int i = 0; i < 1000; i++) {
            hd.write(""+i+"\"event\":\"{\\\"name\\\":\\\"es-869\\\",\\\"sex\\\":\\\"man\\\",\\\"age\\\":869,\\\"city\\\":\\\"name\\\"}\",\"cause\":\"StrictDynamicMappingException[mapping set to strict, dynamic introduction of [city] within [dataflow_es_sink_copy] is not allowed]\" \n");
        }
//        Configuration conf = new Configuration();
//        FileSystem fs = FileSystem.get(URI.create(uri), conf);
//        Path dirPath = new Path(str);

//        List<String> strings = hdfs.listAll(str);
//        System.out.println(strings);
//        hd.mkdir("lihao");
//        hd.deleteDir("lihao");
//        hdfs.uploadLocalFile2HDFS("D:/WorkerSpace/play_demo_work/HDFS_TEST/src/lihao.txt","cy");
//        hdfs.createNewHDFSFile("lihao","我是李浩");
//        byte[] lihaos = hd.readHDFSFile("lihao");
//        System.out.println(lihaos);
    }

}
