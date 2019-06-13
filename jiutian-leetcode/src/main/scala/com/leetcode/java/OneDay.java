package com.leetcode.java;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2019-06-12
 *
 * @author :hao.li
 */
public class OneDay {
    /**
     * one
     * */
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        int res = 0;
        int j = 0;
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            res = target - nums[i];
            if (!map.containsKey(res)) {
                map.put(nums[i], i);
            } else {
                j = i;
                break;
            }
        }
        result[0] = map.get(res);
        result[1] = j;
        return result;
    }
    /**
     * two
     * */
}
