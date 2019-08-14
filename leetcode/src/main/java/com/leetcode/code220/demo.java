package com.leetcode.code220;

/**
 * Created on 2019-08-13
 *
 * @author :hao.li
 */
public class demo {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j< nums.length;j++){
                if(Math.abs(nums[j]-nums[i])==t&&Math.abs(j-i)==k){
                    return true;
                }
            }
        }
        return false;
    }
}

