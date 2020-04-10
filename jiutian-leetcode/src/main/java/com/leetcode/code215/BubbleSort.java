package com.leetcode.code215;

/**
 * @Author : lihao
 * Created on : 2020-04-10
 * @Description : TODO描述类作用
 */

public class BubbleSort {

    /**
     * 冒泡排序
     * 每次选择两个相邻的值进行比较，较小的放在前面
     * 第一轮比较时，第一个和第二个比较，第二个和第三个比较，一直到最后一个
     * 第一轮结束，最后一个值为最大值
     * 再进行第二轮比较，比较时，无需再比较最后一个值
     * ...
     * 依次类推，保证每一轮的最后一个值都是最大值，直到没有值再与第一个值比较时，循环结束
     * @param a
     */
    public static void bubSort(int a[]){

        for(int i=0; i<a.length; i++){
            //第一轮比较完后，最后一个位置的值为最大值
            //每遍历一轮，最后的位置就能多确定一个
            for(int j=0; j<a.length-i-1; j++){
                int tmp = a[j];//保存a[j]的值，如果比下一个值大，则交换位置
                if(a[j]>a[j+1]){
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                }
            }
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] test = {5,2,9,4,7,3,1};
        bubSort(test);
        for(int i=0; i<test.length; i++){
            System.out.print(test[i] + " ");
        }

    }

}


