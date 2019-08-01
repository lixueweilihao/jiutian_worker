package com.leetcode.code350;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created on 2019-08-01
 *
 * @author :hao.li
 */
public class demo1 {
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> list1 = new ArrayList<Integer>();
        for(int a : nums1){
            list1.add(a);
        }
        List<Integer> list2 = new ArrayList<Integer>();
        for(int b: nums2){
            if(list1.contains(b)){
                list2.add(b);
                list1.remove(b);
            }
        }
        int[] result = new int[list2.size()];
        int i =0;
        for(int m:list2){
            result[i++]=m;
        }
        return result;
    }
    public int[] intersect_2(int[] nums1, int[] nums2) {
        List<Integer> list1 = Arrays.stream(nums1)
                .boxed()
                .collect(Collectors.toList());
        List<Integer> list2 = Arrays.stream(nums2)
                .boxed()
                .filter(num -> {
                    if (list1.contains(num)) {
                        list1.remove(num);
                        return true;
                    }
                    return false;
                })
                .collect(Collectors.toList());
        int[] res = new int[list2.size()];
        for (int i = 0; i < list2.size(); i++) {
            res[i] = list2.get(i);
        }
        return res;
    }
    public static int[] intersect_3(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>(nums1.length);
        // 将 nums1 出现的数值及频次放入映射中
        for (int num : nums1) {
            Integer count = map.get(num);
            if (count == null) {
                map.put(num, 1);
            } else {
                map.put(num, ++count);
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            // 获取映射中该数值出现的频次
            Integer count = map.get(num);
            if (count != null && count != 0) {
                list.add(num);
                // 注意每次匹配后，该数值的频次需要减 1（nums1 和 nums2 匹配的数值的频次要相同）
                map.put(num, --count);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
//
//    public int[] intersect(int[] nums1, int[] nums2) {
//        Arrays.sort(nums1);
//        Arrays.sort(nums2);
//        List<Integer> list = new ArrayList<>();
//        for (int i = 0, j = 0; i < nums1.length && j < nums2.length; ) {
//            if (nums1[i] < nums2[j]) {
//                i++;
//            } else if (nums1[i] > nums2[j]) {
//                j++;
//            } else {
//                list.add(nums1[i]);
//                i++;
//                j++;
//            }
//        }
//        int[] res = new int[list.size()];
//        for (int i = 0; i < list.size(); i++) {
//            res[i] = list.get(i);
//        }
//        return res;
//    }

    public static void main(String[] args) {
    int[] a={1,2,2,1};
    int[] b= {2,2};
        int[] c=intersect_3(a,b);
        System.out.println(c);
    }
}
