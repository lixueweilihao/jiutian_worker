package com.leetcode.code215;

/**
 * @Author : lihao
 * Created on : 2020-04-10
 * @Description : TODO描述类作用
 */

public class InsertSort{
    /**
     * 插入排序
     * 插入排序相当于向一个排好序的数组中加入新值，使得数组再次有序
     * 当第一次遍历时，相当于将第二个值与第一个比较，较小的放在前面
     * 第二次遍历时，则是将第三个值加入前两个值中合适的位置，使得三个值有序
     * ...
     * 当将最后一个值加入到前面已经排好序的数组中时，遍历结束
     * @param a
     */
    public static void insSort(int[] a){
        for(int i=0; i<a.length; i++){
            int tmp = a[i];
            int j = 0;
            //将第i个值插入当前i-1中的合适位置
            for(j=i; j>0&&tmp<a[j-1]; j--){
                //从后向前遍历，如果tmp值更小，说明它的位置需要更靠前
                //所以依次向后移动一个位置
                a[j] = a[j-1];
            }
            a[j] = tmp;
        }
    }
    public static void main(String args[]){
        int[] test = {5,10,6,3,2};
        insSort(test);
        for(int i=0; i<test.length; i++){
            System.out.print(test[i] + " ");
        }
    }

}

