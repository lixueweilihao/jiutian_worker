package com.play.data.linked.demo;

/**
 * @Author : lihao
 * Created on : 2020-04-15
 * @Description : TODO描述类作用
 */
/**
 * 程序目的：建立一组学生成绩的单向链表程序，包含学号、姓名、和成绩3种数据。 只要输入要删除学生的成绩，就可以遍历该链表，并清除学生的节点，
 * 要结束输入时，输入“-1”,则此时会列出该链表未删除的所有学生数据。
 *
 * @author 86176
 *
 */
//构建节点类
public class Node {
    int data;
    int np;
    String names;
    Node next;

    // 节点声明的构造函数
    public Node(int data, int np, String names) {
        this.data = data;
        this.np = np;
        this.names = names;
        this.next = null;
    }


}

