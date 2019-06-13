package com.mapreduce;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by lihao on 2017/8/1.
 */
public class HdfsDemo {
    FileSystem fileSystem=null;
    public void init() throws URISyntaxException, IOException, InterruptedException {
        fileSystem = FileSystem.get(new URI("hdfs://192.168.1.203:8020"),new Configuration());
    }
    public void testUpload() throws IOException {
        InputStream inputStream = new FileInputStream("E:\\abc.txt");
        OutputStream outputStream = fileSystem.create(new Path("/tmp/test/abc.txt"));
        IOUtils.copyBytes(inputStream,outputStream,4096,true);
    }
    public void downLoad() throws IllegalArgumentException, IOException {
        fileSystem.copyToLocalFile(true, new Path("/tmp/abc.txt"), new Path("f://abc.txt"),true);
    }

    public static void main(String[] args) throws InterruptedException, IOException, URISyntaxException {
        HdfsDemo hdfsDemo = new HdfsDemo();
        hdfsDemo.init();
//        hdfsDemo.testUpload();
        hdfsDemo.downLoad();
    }

}
