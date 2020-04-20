package com.play.data.位图法;

/**
 * @Author : lihao
 * Created on : 2020-04-19
 * @Description : 无重复排序
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class javaDuplicateSort_copy {
    public static List<Integer> tempList = new ArrayList<Integer>();
    public static int count;
    public static long start ;
    public static long end ;

    public static void main(final String[] args) {
        Random random = new Random();
        List<Integer> firstNum = new ArrayList<Integer>();
        List<Integer> secondNum = new ArrayList<Integer>();

        for (int i = 1; i <= 100000; i++) {
            firstNum.add(i);
            secondNum.add(i);
            firstNum.add(random.nextInt(i + 1));
            secondNum.add(random.nextInt(i + 1));
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

    public static List<Integer> uniqueSort(final List<Integer> uniqueList) {
        javaDuplicateSort_copy.tempList.clear();
        int[] temp = new int[200002];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = 0;
        }
        for (int i = 0; i < uniqueList.size(); i++) {
            temp[uniqueList.get(i)]++;
            if(temp[i]>1){
                temp[i]=1;
            }
        }
        for (int i = 0; i < uniqueList.size(); i++) {
            if(temp[i]>1){
                temp[i]=1;
            }
        }
        for(int i=0;i<1000;i++){
            System.out.print( temp[i]+": ");
        }
        for (int i = 0; i < temp.length; i++) {
            for (int j = temp[i]; j > 0; j--) {
                javaDuplicateSort_copy.tempList.add(i);
            }
        }
        System.out.println();
        for(int i=0;i<1000;i++){
            System.out.print( javaDuplicateSort_copy.tempList.get(i)+": ");
        }
        return javaDuplicateSort_copy.tempList;
    }

    public static void getStartTime() {
        javaDuplicateSort_copy.start = System.nanoTime();
    }

    public static void getEndTime(final String s) {
        javaDuplicateSort_copy.end = System.nanoTime();
        System.out.println(s + ": " + (javaDuplicateSort_copy.end - javaDuplicateSort_copy.start) + "ns");
    }
}
