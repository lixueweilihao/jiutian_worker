package com.play.execfile;

import java.util.Comparator;
import java.util.TreeSet;


public class test {
    public void demo4() {
        //需求:将字符串按照长度排序
        TreeSet<String> ts = new TreeSet<>(new CompareByLen());        //Comparator c = new CompareByLen();
        ts.add("aaaaaaaa");
        ts.add("z");
        ts.add("wc");
        ts.add("nba");
        ts.add("cba");

        System.out.println(ts);
    }

    public static void main(String[] args) {
        test t = new test();
        t.demo4();
    }

    //定义一个类，实现Comparator接口，并重写compare()方法，
    class CompareByLen /*extends Object*/ implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {        //按照字符串的长度比较
            int num = s1.length() - s2.length();        //长度为主要条件
            return num == 0 ? s1.compareTo(s2) : num;    //内容为次要条件
        }
    }
}
