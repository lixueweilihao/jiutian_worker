package com.leetcode.code75;

import org.apache.commons.collections.map.HashedMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2019-08-01
 *
 * @author :hao.li
 */
public class demo {
    public int[] sortColors(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int a : nums){
            Integer integer = map.get(a);
            if(integer==null){
                map.put(a,1);
            }else {
                map.put(a,++integer);
            }
        }
        int[] result = new int[nums.length];
        for(int j=0;j<map.keySet().size();j++){

        }
        int i_0 = map.get(0).intValue();
        int i_1 = map.get(1).intValue();
        int i_2 = map.get(2).intValue();
        for(int i=0;i<i_0;i++){
            result[i]=0;
        }
        for(int i=i_0;i<i_1+i_0;i++){
            result[i]=1;
        }
        for(int i=i_1+i_0;i<i_2+i_0+i_1;i++){
            result[i]=2;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] a={2,0,2,1,1,0};
        demo d = new demo();
        int[] ints = d.sortColors(a);
        System.out.println(ints);

    }


}
