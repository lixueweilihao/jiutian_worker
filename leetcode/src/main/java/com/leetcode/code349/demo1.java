package com.leetcode.code349;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created on 2019-07-31
 *
 * @author :hao.li
 */
public class demo1 {
    public static int[] intersection(int[] nums1, int[] nums2) {
        Set set = new HashSet();
        for(int i=0;i<nums1.length;i++){
            for(int j=0;j<nums2.length;j++){
                if(nums1[i]==nums2[j]){
                    set.add(nums1[i]);
                }
            }
        }


    }

    public static void main(String[] args) {
        int[] a={1,2,2,1};
        int[] b ={2,2};
        Object[] c=intersection(a,b);
        System.out.println(c);


    }
}
