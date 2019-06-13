package com.play.kafka;

/**
 * Copyright @ 2018 lihao.com
 * All right reserved.
 * Function：Leader Selector
 *
 * @author Li Hao
 * @since 2018/8/23  19:47
 */

import java.io.*;

public class DataInputStreamDemo {
    public static void main(String[] args) throws IOException {

        InputStream is = null;
        DataInputStream dis = null;
        FileOutputStream fos = null;
        DataOutputStream dos = null;
        int[] i = {128,250,430,520,820};

        try{
            // create file output stream
            fos = new FileOutputStream("c:\\test.txt");

            // create data output stream
            dos = new DataOutputStream(fos);

            // for each int in int buffer
            for(int j:i)
            {
                // write int to data output stream
                dos.writeInt(j);
            }

            // force data to the underlying file output stream
            dos.flush();

            // create file input stream
            is = new FileInputStream("c:\\test.txt");

            // create new data input stream
            dis = new DataInputStream(is);

            // available stream to be read
            while(dis.available()>0)
            {
                // read four bytes from data input, return int
                int k = dis.readInt();

                // print int
                System.out.print(k+" ");
            }
        }catch(Exception e){
            // if any error occurs
            e.printStackTrace();
        }finally{

            // releases all system resources from the streams
            if(is!=null)
                is.close();
            if(dis!=null)
                dis.close();
            if(fos!=null)
                fos.close();
            if(dos!=null)
                dos.close();
        }
    }
}
