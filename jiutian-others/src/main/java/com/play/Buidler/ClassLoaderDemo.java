package com.play.Buidler;



import java.io.File;
import java.net.URL;

public class ClassLoaderDemo {

    public void testcode() {
        URL url1 = ClassLoader.getSystemResource("file.txt");
        System.out.println("url1:\t" + (url1 == null ? "null" : url1.getPath()));

        URL url1withSlash = ClassLoader.getSystemResource("/Test/file.txt");
        System.out.println("url1/:\t" + (url1withSlash == null ? "null" : url1withSlash.getPath()));
    }

    public static void main(String[] args) throws Exception {
        ClassLoaderDemo c = new ClassLoaderDemo();
        c.testcode();
        URL url = ClassLoader.getSystemResource(".");
        System.out.println("url : " + url);


        System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
        System.out.println(ClassLoaderDemo.class.getClassLoader().getResource(""));
        System.out.println(ClassLoader.getSystemResource(""));
        System.out.println(ClassLoaderDemo.class.getResource("").getFile());
        System.out.println("abc  "+ClassLoaderDemo.class.getResource("/")); // Class文件所在路径
        System.out.println(new File("/").getAbsolutePath());
        System.out.println(System.getProperty("user.dir"));

    }
}
