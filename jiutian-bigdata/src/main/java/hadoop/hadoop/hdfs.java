package hadoop.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.Progressable;

import java.io.*;
import java.net.URI;

public class hdfs {
    //将本地文件上传到hdfs中
    public static void uploadToHdfs() {
        String localSrc = "d://qq.txt";
        String dst = "hdfs://192.168.1.203:8020/tmp/test/abc.txt";
        InputStream in = null;
        FileSystem fs = null;
        FSDataOutputStream fout = null;
        Configuration conf = new Configuration();
        try {
            // 获取读入文件数据
            in = new BufferedInputStream(new FileInputStream(localSrc));
            // 获取目标文件信息
            fs = FileSystem.get(URI.create(dst), conf);
            fout = fs.create(new Path(dst), new Progressable() {
                @Override
                public void progress() {
                    System.out.print("*");
                }
            });
//            IOUtils.copyBytes(in, fout, 4096, true);
            byte[] buffer = new byte[4096];
            int bytesRead = in.read(buffer);
            if (bytesRead > 0) {
                fout.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //从hdfs上读取文件
    public static void readFromHdfs() throws IOException {
        String dst = "hdfs://192.168.1.203:8020/tmp/test/abc.txt";
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(dst), conf);
        FSDataInputStream hdfsIS = fs.open(new Path(dst));


        OutputStream out = new FileOutputStream("d:/qq-hdfs.txt");
        byte[] ioBuffer = new byte[1024];
        int readLen = hdfsIS.read(ioBuffer);
        while (readLen != -1) {
            out.write(ioBuffer, 0, readLen);
            readLen = hdfsIS.read(ioBuffer);
        }
        out.close();
        hdfsIS.close();
        fs.close();
    }


    //以append的方式将内容添加到hdfs上去
    private static void appendToHdfs() throws FileNotFoundException, IOException {
        String dst = "hdfs://192.168.1.203:8020/tmp/test/abc.txt";
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(dst), conf);
        FSDataOutputStream out = fs.append(new Path(dst));

        int readLen = "zhangzk add by hdfs Java api".getBytes().length;

        while (-1 != readLen) {
            out.write("zhangzk add by hdfs java api".getBytes(), 0, readLen);
        }
        out.close();
        fs.close();
    }

    //从hdfs中删除内容
    private static void deleteFromHdfs() throws FileNotFoundException, IOException {
        String dst = "hdfs://192.168.1.203:8020/tmp/test/abc.txt";
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(dst), conf);
        fs.deleteOnExit(new Path(dst));
        fs.close();
    }

    //遍历HDFS上的文件和目录
    private static void getDirectoryFromHdfs() throws FileNotFoundException, IOException {
        String dst = "hdfs://192.168.1.203:8020/tmp/test/abc.txt";
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(dst), conf);
        FileStatus fileList[] = fs.listStatus(new Path(dst));
        int size = fileList.length;
        for (int i = 0; i < size; i++) {
            System.out.println("name:" + fileList[i].getPath().getName() + "/t/tsize:" + fileList[i].getLen());
        }
        fs.close();
    }

    public static void main(String[] args) {
        Configuration conf = new Configuration();
    }
}
