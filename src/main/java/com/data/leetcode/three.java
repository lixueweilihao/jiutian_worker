//package com.data.leetcode.ShuZU;
//
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * Copyright @ 2018
// * All right reserved.
// *
// * @author Li Hao
// * @since 2019/3/15  0:24
// */
//public class three {
//    public List<List<Integer>> threeSum(int[] nums) {
//        Map<Integer, Integer> map = new HashMap();
//        if (nums.length < 3) {
//            return null;
//        }
//        Arrays.sort(nums);
//        for (int left = 0; left < nums.length && nums[left] < 0; left++) {
//            for(int i = left+1;i<nums.length;i++){
//                if(!map.containsKey((0-nums[left])-nums[i])){
//                    map.put(nums[i],i);
//                }
//            }
//        }
//
//    }
//
//}
//
//    public static void main(String[] args) {
//        int[] a = {2, 7, 11, 1};
//        int[] b = findMedianSortedArrays(a, 9);
//        System.out.println("[" + b[0] + "," + b[1] + "]");
//    }
//}
