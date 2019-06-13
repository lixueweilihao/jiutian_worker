package execfile;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;


public class Test04 {
    final static int copySize = 100;

    public static void main(String[] args) throws IOException, InterruptedException {
        File f1 = new File("D:" + File.separator + "test01.txt");
        File f2 = new File("D:" + File.separator + "test02.txt");
        if (!f1.exists()) {
            System.out.println("读入文件不存在");
            return;
        }
        //https://www.cnblogs.com/zhengzhamn/articles/5308872.html
        RandomAccessFile rafR = new RandomAccessFile(f1, "rw");
        RandomAccessFile rafW = new RandomAccessFile(f2, "rw");
        long length = rafR.length();
        System.out.println(length);
        rafW.setLength(length);

        int bord = (int) (length / copySize);
        System.out.println(bord);
        bord = length % copySize == 0 ? bord : bord + 1;
        System.out.println(bord);
        for (int i = 0; i < bord; i++) {
            new MyThread(f1, f2, i * copySize * 1024).start();
        }
        rafR.close();
        rafW.close();
        Thread.sleep(3000);
        System.out.println("复制完成");
    }
}
