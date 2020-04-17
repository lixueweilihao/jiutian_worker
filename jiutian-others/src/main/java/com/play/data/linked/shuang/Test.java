package com.play.data.linked.shuang;

/**
 * @Author : lihao
 * Created on : 2020-04-17
 * @Description : TODO描述类作用
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.junit.Before;

public class Test {
    public static LinkedList<String> dl;
    public static ArrayList<String> l;

    @Before
    public void init() {
        dl = new LinkedList<String>();
        dl.add("N1");
        dl.add("N2");
        dl.add("N3");
        dl.add("N4");
        dl.add("N5");
        l = new ArrayList<String>();
        l.add("N1");
        l.add("N2");
        l.add("N3");
        l.add("N4");
        l.add("N5");

    }

    @org.junit.Test
    public void test() {
        for (String str : dl) {
            System.out.println("双向链表：" + str);
        }
        for (String str : l) {
            System.out.println("普通arrylist:" + str);
        }
    }

    @org.junit.Test
    public void add() {
        // 双向链表独有的向链表头部添加元素
        dl.addFirst("N6");
        dl.addLast("N7");

    }

    @org.junit.Test
    public void del() {
        dl.remove("N1");

        for (String str : dl) {
            System.out.println("双向链表：" + str);
        }
        l.remove("N1");
        for (String str : l) {
            System.out.println("普通arrylist:" + str);
        }

    }

    /**
     * 模仿栈的后进后出的pop
     *
     * @return
     */
    public static String pop() {
        return dl.removeLast();
    }

    /**
     *
     */
    @org.junit.Test
    public void stack() {
        System.out.println(Test.pop());
        for (String str : dl) {
            System.out.println("双向链表：" + str);
        }
    }

    @org.junit.Test
    public void t() {
        Stack<String> statck = new Stack<String>();
        statck.push("N1");
        statck.push("N2");
        statck.push("N3");
        statck.push("N4");
        statck.push("N5");
        System.out.println("pop:" + statck.pop());
        for (String str : statck) {
            System.out.println(str);
        }
    }
    /**
     * 实现队列
     * 队列的数据元素又称为队列元素。在队列中插入一个队列元素称为入队，从队列中删除一个队列元素称为出队。因为队列只允许在一端插入，在另一端删除，
     * 所以只有最早进入队列的元素才能最先从队列中删除，故队列又称为先进先出（FIFO—first in first out）线性表。[1]
     * 队列队首删除 队尾插入
     */
    @org.junit.Test
    public void quene(){
        LinkedList<String> quene = new LinkedList<String>();
        quene = (LinkedList<String>)Collections.synchronizedCollection(quene);
        Test.addQuene(quene, "n1");
        Test.addQuene(quene, "n2");
        Test.addQuene(quene, "n3");
        Test.frecah(quene);
        System.out.println("---------------------------------");
        Test.delQuene(quene);
        Test.frecah(quene);
    }
    public static void frecah(List<String> list) {
        for(String str:list){
            System.out.println(str);
        }
    }
    /**
     * 每次插入都会插入队尾
     * @param quene
     * @param o
     */
    public static void addQuene(LinkedList<String> quene,String o){
        quene.add(o);
    }
    /**
     * 删除 从队首删除
     */
    public static void delQuene(LinkedList<String> quene){
        quene.removeFirst();
    }


}
