package com.leetcode.code347;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created on 2019-08-06
 *
 * @author :hao.li
 */
public class demo {
    public static List<Integer> topKFrequent1(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums) { // 统计频率，key为元素，value为次数
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        // 创建nums.length + 1个桶
        List<Integer>[] bucket = new List[nums.length + 1];
        // 遍历map，根据value值放到对应的桶中
        for (Map.Entry<Integer, Integer> e :map.entrySet()){
            Integer value = e.getValue();
            if (bucket[value] == null) {
                bucket[value] = new ArrayList<Integer>();
            }
            bucket[value].add(e.getKey());
        }
        List<Integer> freList = new ArrayList<>();
        // 桶的编号表示出现次数，所以倒数桶
        for (int j = bucket.length - 1; j > -1 && freList.size() < k; j--) {
            if (bucket[j] != null)
                freList.addAll(bucket[j]);
        }
        return freList;
    }
    public static List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap();
        for(int i=0;i<nums.length;i++){
            Integer o = map.get(nums[i]);
            if(o==null){
                map.put(nums[i],1);
            }else {
                map.put(nums[i],++o);
            }
        }
        List<Integer> collect = map.values().stream().sorted().limit(k).collect(Collectors.toList());
        return collect.stream().map(v->map.get(v)).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        int[] a={1,2};
        int k=2;
        List<Integer> integers = topKFrequent1(a, k);
        System.out.println(integers);
    }
}
