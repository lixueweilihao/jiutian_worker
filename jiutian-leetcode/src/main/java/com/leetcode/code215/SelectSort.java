package com.leetcode.code215;

/**
 * @Author : lihao
 * Created on : 2020-04-10
 * @Description : TODO描述类作用
 */

public class SelectSort {

    /**
     * 选择排序
     * 选择第一个与其后的所有元素进行比较，将最小值放在第一个位置
     * 第一个位置放好之后，再选择第二个与其后的所有元素进行比较，再将最小值放在第二个位置
     * ...
     * 以此类推，直到最后一个位置也被放置了元素
     *
     * @param a int数组
     */
    public static void selSort(int a[]){

        if(a==null||a.length==0){
            return;
        }
        for (int i = 0; i < a.length; i++) {
            int tmp = a[i];//存储遍历时最小的值
            int flag = i;//存储最小值的位置
            for(int j = i+1;j<a.length;j++){
                if(a[j]<tmp){
                    //找到更小的值，将值和位置存储起来
                    tmp = a[j];
                    flag = j;
                }
            }

            int tmp2 = a[i];//临时保存a[i]的值，用于交换值得位置
            a[i] = tmp;
            a[flag] = tmp2;
        }
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] test = {2,3,5,4,9,6,7};
        selSort(test);

        for(int i=0; i<test.length; i++){
            System.out.print(test[i] + " ");
        }

    }

}

