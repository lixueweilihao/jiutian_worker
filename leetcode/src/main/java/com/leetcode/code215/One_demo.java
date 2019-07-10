package com.leetcode.code215;

/**
 * Created on 2019-07-09
 *
 * @author :hao.li
 */
public class One_demo {
    public static int findKthLargest(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] < nums[j]) {
                    int temp = nums[i];
                    nums[i]=nums[j];
                    nums[j]=temp;
                }
            }
        }
        return nums[k-1];
    }

    public static void main(String[] args) {
        int[] a = {3,2,1,5,6,4};
        int kthLargest = findKthLargest(a, 2);
        System.out.println(kthLargest);

    }
}
