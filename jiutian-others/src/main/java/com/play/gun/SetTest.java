package com.play.gun;

import com.google.common.collect.Sets;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class SetTest {
    public static void test1() throws IOException {
        Set<String> set1 = new HashSet();
        set1.add("a");
        set1.add("b");
        set1.add("d");
        Set<String> set2 = new HashSet();
        set2.add("a");
        set2.add("b");
        set2.add("c");


        Set<String> result1 = Sets.union(set1, set2);//合集，并集
        System.out.println(result1);
        Set<String> result2 = Sets.intersection(set1, set2);//交集
        System.out.println(result2);
        Set<String> result3 = Sets.difference(set1, set2);//差集 1中有而2中没有的
        System.out.println(result3);
        Set<String> result4 = Sets.difference(set2, set1);//差集 1中有而2中没有的
        System.out.println(result4);
//        Set<String> result4 = Sets.symmetricDifference(set1, set2);//相对差集 1中有2中没有  2中有1中没有的 取出来做结果
//        System.out.println(result4);

        //可以分别把4种不同结果 写出文件

//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("d:/B/result.txt")));
//        bufferedWriter.write("共有:"+result1.size()+"条\r\n");
//        for (String string : result1) {
//            bufferedWriter.write(string+"\r\n");
//        }
//        bufferedWriter.close();


    }

    //    public Set<String> readFile4List(File file) throws IOException{
//        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
//        Set<String> set = new HashSet<String>();
//        String str = null;
//        while((str =bufferedReader.readLine()) != null){
//            if(str.length() > 6){
//                set.add(str.substring(3));
//            }else{
//                set.add(str);
//            }
//
//        }
//        return set;
//    }
    public static void main(String[] args) throws IOException {
        test1();
    }

}
