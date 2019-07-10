package com.leetcode.code215;

/**
 * Created on 2019-07-09
 *
 * @author :hao.li
 */
public class Two_demo {

        public static int findKthLargest(int[] nums, int k) {
            //根据快排的思想
            int len=nums.length;
            quickSort(nums,0,len-1,len-k);  //例如第一大的数，那就是最后一位了
            return nums[len-k];
        }

        private static void quickSort(int[] nums, int l, int r, int k) {
            //以l为中位数排序两边的数
            int left=l,right=r;
            int mid=nums[left];

            while(l<r){
                //从左边开始,找到第一个小于nums[l]的值
                while(l<r && mid<=nums[r]){
                    r--;
                }
                //从左边找，找到第一个大于nums[l]的值的值
                while(l<r && mid>=nums[l]){
                    l++;
                }

                //交换l和r的值
                if(l<r){
                    int t=nums[l];
                    nums[l]=nums[r];
                    nums[r]=t;
                }
            }
            //进行归为，把mid的值进行交换
            nums[left]=nums[l];
            nums[l]=mid;

            //此时l的位置是正确的，然后k和l的位置
            if(l>k){
                quickSort(nums,left,l-1,k);
            }else if(l<k){
                quickSort(nums,l+1,right,k);
            }else if(l==k){
                return;
            }
        }
    }


