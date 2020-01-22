package com.leetcode.code349;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

/**
 * Created on 2019-07-31
 *
 * @author :hao.li
 */
public class demo1 {
    public static int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
// 获取对应的平方数
        List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
        IntStream stream = Arrays.stream(nums1);
        Set set = new HashSet();
        for(int i=0;i<nums1.length;i++){
            for(int j=0;j<nums2.length;j++){
                if(nums1[i]==nums2[j]){
                    set.add(nums1[i]);
                }
            }
        }
        return set.stream().mapToInt(value -> Integer.valueOf(value.toString())).toArray();
    }

    public static void main(String[] args) {
        int[] a={1,2,2,1};
        int[] b ={2,2};
        int[] c=intersection(a,b);
        System.out.println(c);


    }
}
