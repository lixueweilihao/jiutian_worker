package com.play.data.位图法;

/**
 * @Author : lihao
 * Created on : 2020-04-18
 * @Description : 无重复序列排序
 * 位图法排序 https://www.cnblogs.com/rrttp/p/7668773.html
 * https://blog.csdn.net/fxkcsdn/article/details/81349336
 *
 * https://www.jianshu.com/p/8d074e8d02aa?utm_campaign=hugo
 */

import java.util.*;

public class javaUniqueSort {
    public static int[] temp = new int[100001];
    public static List<Integer> tempList = new ArrayList<Integer>();
    public static int count ;
    public static long start ;
    public static long end ;

    public static List<Integer> uniqueSort(final List<Integer> uniqueList) {
        javaUniqueSort.tempList.clear();
        for (int i = 0; i < javaUniqueSort.temp.length; i++) {
            javaUniqueSort.temp[i] = 0;
        }
        for (int i = 0; i < uniqueList.size(); i++) {
            javaUniqueSort.temp[uniqueList.get(i)] = 1;
        }
        for (int i = 0; i < javaUniqueSort.temp.length; i++) {
            if (javaUniqueSort.temp[i] == 1) {
                javaUniqueSort.tempList.add(i);
            }
        }

        return javaUniqueSort.tempList;
    }


    public static void getStartTime() {
        javaUniqueSort.start = System.nanoTime();
    }

    public static void getEndTime(final String s) {
        javaUniqueSort.end = System.nanoTime();
        System.out.println(s + ": " + (javaUniqueSort.end - javaUniqueSort.start) + "ns");
    }



    public static void main(final String[] args) {

        List<Integer> firstNum = new ArrayList<Integer>();
        List<Integer> secondNum = new ArrayList<Integer>();

        for (int i = 0; i <= 100000; i++) {
            firstNum.add(i);
            secondNum.add(i);
        }

        Collections.shuffle(firstNum);
        Collections.shuffle(secondNum);


        getStartTime();
        Collections.sort(firstNum);
        getEndTime("java sort run time  ");

        getStartTime();
        secondNum = uniqueSort(secondNum);
        getEndTime("uniqueSort run time ");


    }
}
