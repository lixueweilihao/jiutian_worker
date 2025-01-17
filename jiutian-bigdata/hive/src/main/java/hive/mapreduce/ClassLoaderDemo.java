package hive.mapreduce; /**
 * Created by lihao on 2017/6/28.
 */
import java.lang.*;
import java.io.*;

class ClassLoaderDemo {

    static String getResource(String rsc) {
        String val = "";

        try {
            Class cls = Class.forName("ClassLoaderDemo");

            // returns the ClassLoader object associated with this Class
            ClassLoader cLoader = cls.getClassLoader();
            // input stream
            InputStream i = cLoader.getSystemResourceAsStream(rsc);
            BufferedReader r = new BufferedReader(new InputStreamReader(i));

            // reads each line
            String l;
            while((l = r.readLine()) != null) {
                val = val + l;
            }
            i.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return val;
    }

    public static void main(String[] args) {

        System.out.println("File1: " + getResource("abc.txt"));
//        System.out.println("File2: " + getResource("test.txt"));
    }
}
