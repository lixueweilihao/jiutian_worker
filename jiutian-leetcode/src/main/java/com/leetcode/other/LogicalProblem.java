package com.leetcode.other;

/**
 * Created on 2020-02-29
 *
 * @author :hao.li
 */
import java.util.ArrayList;
import java.util.Scanner;

public class LogicalProblem {
    //定义五个人，方便递归输出所有排序可能
    public static char[] charAr={'A','B','C','D','E'};
    //储存符合条件的排序
    public static ArrayList<ArrayList<Character>> list=new ArrayList<>();
    //五个人说的五句话
    public static String[] JudgeAr=new String[5];

    public static void main(String[] args){
        Scanner in=new Scanner(System.in);
        for(int i=0;i<5;i++){
            String line=in.nextLine();
            JudgeAr[i]=line;
        }
        list.add(new ArrayList<>());
        boolean[] Mark=new boolean[5];
        //找出所有的排序方式并储存在list中
        StringGeneration(Mark,0);
        //递归后最后一条为空，需要删除
        list.remove(list.size()-1);
        //输出结果
        for(ArrayList<Character> li:list){
            for(Character ch:li){
                System.out.print(ch);
            }
            System.out.println();
        }
        System.out.print(list.size());

    }

    /**
     * 递归生成所有排序可能，并将符合条件的可能保存到{@param list}中
     * @param Mark 标记那些人已经处于排序队列中
     * @param length 当前排序队列中的人数
     */
    public static void StringGeneration(boolean[] Mark,int length){
        if(length==5){
            //判断当前排序队列是否符合要求，符合要求的保存在list中！！
            if(Judge(list.get(list.size()-1))){
                ArrayList<Character> newlist=(ArrayList<Character>)(list.get(list.size()-1).clone());
                list.add(newlist);
            }
            return;
        }
        for(int i=0;i<5;i++){
            if(!Mark[i]){
                list.get(list.size()-1).add(charAr[i]);
                Mark[i]=true;
                StringGeneration(Mark,length+1);
                list.get(list.size()-1).remove(length);
                Mark[i]=false;
            }
        }
        return;
    }

    /**
     * 判断当前名次排序是否符合条件
     * @param charList 当前名次排序
     * @return 符合 return true; 不符合 return false
     */
    public static boolean Judge(ArrayList<Character> charList){
        int[] charLoca=new int[5];
        for(int i=0;i<5;i++){
            int Loca=charList.get(i)-'A';
            charLoca[Loca]=i;
        }
        for(int i=0;i<5;i++){
            int Loca=charList.get(i)-'A';
            ArrayList<Integer> Evaluation=getLoca(JudgeAr[Loca]);
            int OLoca=JudgeAr[Loca].charAt(0)-'A';
            //排第1，3，5位的人说假话，2，4位的人说真话
            if(i%2==0){
                if(Evaluation.contains(charLoca[OLoca]))
                    return false;
            }else{
                if(!Evaluation.contains(charLoca[OLoca]))
                    return false;
            }
        }
        return true;
    }

    /**
     * 当这句话为真时，话中的对象可能的名次：优化方法：程序运行开始就可以将五句话对应状态状态全部保存，避免重复运算
     * @param string 当前这个人说的话
     * @return 可能的名次
     */
    public static ArrayList<Integer> getLoca(String string){
        char[] charAr=string.toCharArray();
        ArrayList<Integer> result=new ArrayList<>();
        if(charAr.length==4){
            int value=charAr[3]-'1';
            if(charAr[1]=='!') {
                for (int i = 0; i < 5; i++) {
                    if (i == value) continue;
                    result.add(i);
                }
            }else if(charAr[1]=='<'){
                for(int i=0;i<=value;i++){
                    result.add(i);
                }
            }else{
                for(int i=value;i<5;i++){
                    result.add(i);
                }
            }

        }else{
            int value=charAr[2]-'1';
            if(charAr[1]=='=') {
                result.add(value);
            }else if(charAr[1]=='<'){
                for(int i=0;i<value;i++){
                    result.add(i);
                }
            }else{
                for(int i=value+1;i<5;i++){
                    result.add(i);
                }
            }

        }
        return result;
    }
}



