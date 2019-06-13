package com.play.execfile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


public class MyThread extends Thread {
    private RandomAccessFile rafR;
    private RandomAccessFile rafW;
    private long startPoint;

    public MyThread(File fR, File fW, long startPoint) {
        try {
            this.rafR = new RandomAccessFile(fR, "r");
            this.rafW = new RandomAccessFile(fW, "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.startPoint = startPoint;

    }

    public void run() {
        try {
            rafR.seek(startPoint);
            rafW.seek(startPoint);
            byte[] by = new byte[1024];
            int len = 0;
            int maxSize = 0;
            while ((len = rafR.read(by)) != -1 && maxSize < Test04.copySize) {
                rafW.write(by, 0, len);
                maxSize++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            {
                try {
                    rafR.close();
                    rafW.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
