package hive.mapreduce;
/**
 * Created by lihao on 2017/7/2.
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;

public class FileReadFromHdfs {
    public static void main(String[] args) {
        try {
            String url = "hdfs://192.168.1.203:8020/tmp/The_Man_of_Property.txt";
            Configuration conf = new Configuration();
            FileSystem fs = FileSystem.get(URI.create(url),conf);
            FSDataInputStream hdfsinputStream = fs.open(new Path(url));
            byte[] ioBuffer = new byte[1024];
            int readLen = hdfsinputStream.read(ioBuffer);
            while(readLen!=-1){
                System.out.write(ioBuffer,0,readLen);
                readLen = hdfsinputStream.read(ioBuffer);
            }
            hdfsinputStream.close();
            fs.close();
        }catch(IOException e){
            e.printStackTrace();
        }

//    try{
//        String url = "hdfs://192.168.1.203:8020/tmp/test/abc.txt";
//        Configuration conf = new Configuration();
//        FileSystem fs = FileSystem.get(URI.create(url), conf);
//        FSDataInputStream hdfsin = fs.open(new Path(url));
//        byte[] ioBuffer = new byte[1024];
//        int readLen = hdfsin.read(ioBuffer);
//        while(readLen!=-1)
//        {
//            System.out.write(ioBuffer, 0, readLen);
//            readLen = hdfsin.read(ioBuffer);
//        }
//        hdfsin.close();
//        fs.close();
////        FSDataOutputStream hdfsout = fs.create(new Path(url));
////        hdfsout.writeChars("abc");
////        fs.close();
////        hdfsout.close();
//    }catch(
//    IOException e){
//        e.printStackTrace();
//    }
//        try {
//            URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
//            String url = "hdfs://192.168.1.203:9000/tmp/test/abc.txt";
//            URL u = new URL(url);
//            URLConnection conn = u.openConnection();
//            InputStream  is = conn.getInputStream();
//            FileOutputStream out = new FileOutputStream("E:/hello.txt");
//            byte[] buf = new byte[1024];
//            int len = -1;
//            while ((len = is.read(buf)) != -1) {
//                out.write(buf, 0, len);
//            }
//            is.close();
//            out.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();

    }
}







