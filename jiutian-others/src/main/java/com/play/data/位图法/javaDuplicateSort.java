package com.play.data.位图法;

/**
 * @Author : lihao
 * Created on : 2020-04-19
 * @Description : 有重复排序
 */
import java.util.*;

public class javaDuplicateSort {
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
        javaDuplicateSort.tempList.clear();
        int[] temp = new int[200001];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = 0;
        }
        for (int i = 0; i < uniqueList.size(); i++) {
            temp[uniqueList.get(i)]++;
        }
        for (int i = 0; i < uniqueList.size(); i++) {
            System.out.print(temp[i]+":");
        }
        for (int i = 0; i < temp.length; i++) {
            for (int j = temp[i]; j > 0; j--) {
                javaDuplicateSort.tempList.add(i);
            }
        }
        System.out.println();
        for (int i = 0; i < 10000; i++) {
            System.out.print(javaDuplicateSort.tempList.get(i)+" : ");
        }

        return javaDuplicateSort.tempList;
    }

    public static void getStartTime() {
        javaDuplicateSort.start = System.nanoTime();
    }

    public static void getEndTime(final String s) {
        javaDuplicateSort.end = System.nanoTime();
        System.out.println(s + ": " + (javaDuplicateSort.end - javaDuplicateSort.start) + "ns");
    }
}
