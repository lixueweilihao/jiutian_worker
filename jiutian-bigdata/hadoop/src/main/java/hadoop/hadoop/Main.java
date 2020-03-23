package hadoop.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;

public class Main {
  //args[0]:hdfs uri
  public static void main(String[] args) throws IOException {
    if (args.length == 0) {
      System.out.println("Usage: args[0] is your namenodeip, args[1] is the amount of thread");
    }
    String uri = "hdfs://" + args[0] + ":9000/";
    Configuration conf = new Configuration();
    FileSystem fs = FileSystem.get(URI.create(uri), conf);
    OperationThread operationThread = new OperationThread(fs);
    for (int i = 0; i < Integer.parseInt(args[1]); i++) {
      new Thread(operationThread, "t" + i).start();
    }
  }
}

class OperationThread implements Runnable {
  FileSystem fs;

  public OperationThread(FileSystem fs) {
    this.fs = fs;
  }

  @Override
  public void run() {
    try {
      for (int i = 0; i < 50000; i++)
        operationCycle(i % 10);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void operationCycle(int i) throws IOException {
    long time = System.currentTimeMillis();
//    System.out.println(time);
    String dir = "/users/cy/dir" +  i + time + Thread.currentThread().getName();
    Path dirPath = new Path(dir);

//    if (i == 0) {//1.创建目录
    if (true) {//1.创建目录
      fs.mkdirs(dirPath);
      fs.copyFromLocalFile(new Path("/home/bigdata/cy/1.c"), dirPath);
    } else if (i < 5) {//2.创建文件
//        fs.create(new Path(dir + "/file" + i)).close();
    } else if (i < 10) {//3.ls 目录
//        fs.listStatus(dirPath);
    } else if (i < 81) {//4.ls 文件
//        fs.listStatus(file1Path);
    } else if (i < 121) {//5.打开文件
//        fs.open(file1Path).close();
    } else if (i == 121) {//6.重命名文件
//        fs.rename(file1Path, newFile1Path);
    } else if (i == 122) {//7.删除文件
//        fs.delete(newFile1Path, true);
    } else if (i == 123) {//8.重命名目录
//        fs.rename(dirPath, newDirPath);
    } else if (time % 2 == 0) {//9.删除目录
//        fs.delete(newDirPath, true);
    }

  }
//    System.out.println(System.currentTimeMillis());
}


