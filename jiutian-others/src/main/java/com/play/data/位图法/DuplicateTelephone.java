package com.play.data.位图法;

/**
 * @Author : lihao
 * Created on : 2020-05-08
 * @Description : TODO描述类作用
 * https://blog.csdn.net/fxkcsdn/article/details/81349336
 */
import java.util.Random;
public class DuplicateTelephone{
    int randNum=100;
    int min=10000000;
    int max=99999999;
    int len=(max-min+1);
    int bit_per_word=32;

    int  word_offset(int b){
        return b/bit_per_word;
    }
    int bit_offset(int b){
        return b%bit_per_word;
    }
    void setBit(int[] words,int n){
        int temp=n;
        temp-=min;
        words[word_offset(temp)]|=(1<<bit_offset(temp));
    }
    void clearBit(int[] words,int n){
        words[word_offset(n)]&=~(1<<bit_offset(n));
    }
    boolean getBit(int[] words,int n){
        int result=words[word_offset(n)]&(1<<bit_offset(n));
        return result!=0;
    }
    public void sort(){
        int arr[]=new int[randNum];
        System.out.println("数组大小："+randNum);
        int[] words=new int[len/bit_per_word+1];
        int count=0;
        Random r=new Random();
        for(int j=0;j<randNum;j++){
            arr[j]=r.nextInt(len);
            arr[j]+=min;
            System.out.print(arr[j]+" ");
        }
        System.out.println();
        for(int j=0;j<randNum;j++){
            setBit(words,arr[j]);
        }
        System.out.println("排序后a为：");
        for(int i=0;i<len;i++){
            if(getBit(words,i)){
                System.out.print(i+min+" ");
                count++;
            }
        }
        System.out.println();
        System.out.println("总个数为："+count);

    }
    public static void main(String[] args){
        new DuplicateTelephone().sort();
    }

}

