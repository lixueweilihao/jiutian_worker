package com.play.kafka;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/2/13  20:49
 */
public class TESTdEMO {
    public static int[] twoSum(int[] nums, int target) {
        int[] a = new int[0];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length - 1; i++) {
            if (!map.containsKey(target - nums[i])) {
                map.put(nums[i], i);
                continue;
            }
            a = new int[]{map.get(target - nums[i]), i};
        }
        return a;
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] ints = twoSum(nums, target);
        System.out.println(ints[0] + " " + ints[1]);
    }
}
