package com.leetcode.code922;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2019-08-01
 *
 * @author :hao.li
 */
public class demo {
    public static int[] sortArrayByParityII(int[] A) {
        List<Integer> list1 = new ArrayList<Integer>();
        List<Integer> list2 = new ArrayList<Integer>();
        for(int a:A){
            if(a%2==0){
                list1.add(a);
            }else {
                list2.add(a);
            }
        }
        int[] result = new int[A.length];
        for(int i=0,j=0;i<=A.length&j<A.length/2;i+=2,j++){
            result[i]=list1.get(j);
            result[i+1]=list2.get(j);
        }
        return result;

    }

    public static void main(String[] args) {
        int[] a = {4,1,1,0,1,0};
        int[] b = sortArrayByParityII(a);
        System.out.println(b);
    }
}
