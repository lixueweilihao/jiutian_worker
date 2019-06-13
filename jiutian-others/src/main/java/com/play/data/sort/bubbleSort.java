package com.play.data.sort;

/**
 * Copyright @ 2018
 * All right reserved.
 *https://www.cnblogs.com/0201zcr/p/4763806.html
 * https://www.cnblogs.com/wangmingshun/p/5635292.html
 * https://blog.csdn.net/huaxun66/article/details/77847998
 *
 * @author Li Hao
 * @since 2019/2/27  20:20
 */
public class bubbleSort {
    public static void bubbleSort(int[] numbers)
    {
        int temp = 0;
        int size = numbers.length;
        for(int i = 0 ; i < size-1; i ++)
        {
            for(int j = 0 ;j < size-1-i ; j++)
            {
                if(numbers[j] > numbers[j+1])  //交换两数位置
                {
                    temp = numbers[j];
                    numbers[j] = numbers[j+1];
                    numbers[j+1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
//        bubbleSort();
    }

}
