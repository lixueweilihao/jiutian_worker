package com.data.linked;

import java.util.HashMap;

/**
 * Copyright @ 2018
 * All right reserved.
 * 参考 https://www.cnblogs.com/whgk/p/6589920.html
 * https://blog.csdn.net/u011373710/article/details/54024366
 *
 * @author Li Hao
 * @since 2018/12/13  20:46
 */
public class DemoTest {
    int size;
    Node head = new Node();

    public void addNode(Node node) {
        Node temp = head;
        while (temp.getNext() != null) {
            temp = temp.getNext();
        }
        temp.setData(node.getData());
        temp.setNext(node);
        size++;
    }

    public int getLength() {
        return size;
    }

    public void insertNodeByIndex(int index, Node node) {
        //首先需要判断指定位置是否合法，
        if (index < 1 || index > getLength() + 1) {
            System.out.println("插入位置不合法。");
            return;
        }
        int length = 1;            //记录我们遍历到第几个结点了，也就是记录位置。
        Node temp = head;        //可移动的指针
        while (head.getNext() != null) {//遍历单链表
            if (index == length++) {        //判断是否到达指定位置。
                //注意，我们的temp代表的是当前位置的前一个结点。
                //前一个结点        当前位置        后一个结点
                //temp            temp.next     temp.next.next
                //插入操作。
                node.setNext(temp.getNext());
                temp.setNext(node);
                return;
            }
            temp = temp.getNext();
        }
    }

    public void delNodeByIndex(int index) {
        //判断index是否合理
        if (index < 1 || index > getLength()) {
            System.out.println("给定的位置不合理");
            return;
        }

        //步骤跟insertNodeByIndex是一样的，只是操作不一样。
        int length = 1;
        Node temp = head;
        while (temp.getNext() != null) {
            if (index == length++) {
                //删除操作。
                temp.setNext(temp.getNext().getNext());
                return;
            }
            temp = temp.getNext();
        }
    }

    public void selectSortNode() {
        //判断链表长度大于2，不然只有一个元素，就不用排序了。
        if (getLength() < 2) {
            System.out.println("无需排序");
            return;
        }
        //选择排序
        Node temp = head;            //第一层遍历使用的移动指针，最处指向头结点，第一个结点用temp.next表示
        while (temp.getNext() != null) {    //第一层遍历链表，从第一个结点开始遍历
            Node secondTemp = temp.getNext();        //第二层遍历使用的移动指针，secondTemp指向第一个结点，我们需要用到是第二个结点开始，所以用secondNode.next
            while (secondTemp.getNext() != null) {//第二层遍历,从第二个结点开始遍历
                if (temp.getNext().getData() > secondTemp.getNext().getData()) {    //第二层中的所有结点依次与第一次遍历中选定的结点进行比较，
                    int t = secondTemp.getNext().getData();
                    secondTemp.getNext().setData(temp.getNext().getData());
                    temp.getNext().setData(t);
                }
                secondTemp = secondTemp.getNext();
            }
            temp.setData(temp.getData());
        }
    }

    public void insertSortNode() {
        //判断链表长度大于2，不然只有一个元素，就不用排序了。
        if (getLength() < 2) {
            System.out.println("无需排序");
            return;
        }
        //创建新链表
        Node newHead = new Node(0);    //新链表的头结点
        Node newTemp = newHead;        //新链表的移动指针
        Node temp = head;        //旧链表的移动指针
        if (newTemp.getNext() == null) {        //将第一个结点直接放入新链表中。
            Node node = new Node(temp.getNext().getData());
            newTemp.setNext(node);
            temp = temp.getNext();    //旧链表中指针移到下一位(第二个结点处)。
        }
        while (temp.getNext() != null) {     //    遍历现有链表
            while (newTemp.getNext() != null) {
                //先跟新链表中的第一个结点进行比较,如果符合条件则添加到新链表，注意是在第一个位置上增加结点
                //如果不符合，则跟新链表中第二个结点进行比较，如果都不符合，跳出while，判断是否是到了新链表的最后一个结点，如果是则直接在新链表后面添加即可

                if (newTemp.getNext().getData() < temp.getNext().getData()) {
                    Node node = new Node(temp.getNext().getData());
                    node.setNext(newTemp.getNext());
                    newTemp.setNext(node);
                    break;
                }
                newTemp = newTemp.getNext();
            }
            if (newTemp.getNext() == null) {//到达最末尾还没符合，那么说明该值是新链表中最小的数，直接添加即可到链表中即可
                //直接在新链表后面添加
                Node node = new Node(temp.getNext().getData());
                newTemp.setNext(node);
            }
            //旧链表指针指向下一位结点，继续重复和新链表中的结点进行比较。
            temp = temp.getNext();
            //新链表中的移动指针需要复位，指向头结点
            newTemp = newHead;
        }
        //开始使用新链表，旧链表等待垃圾回收机制将其收回。
        head = newHead;

    }

    public Boolean get(Node n) {
        Node tmp1 = n;
        Node tmp2 = n.getNext();
        while (tmp2 != null) {
            int d1 = tmp1.getData();
            int d2 = tmp2.getData();
            if (d1 == d2) return true;//当两个指针重逢时，说明存在环，否则不存在。
            tmp1 = tmp1.getNext();  //每次迭代时，指针1走一步，指针2走两步
            tmp2 = tmp2.getNext().getNext();
            if (tmp2 == null) return false;//不存在环时，退出
        }
        return true; //如果tmp2为null，说明元素只有一个，也可以说明是存在环
    }

    public static boolean hasLoop2(Node n){
        Node temp1 = n;
        HashMap<Node,Node> ns = new HashMap<Node,Node>();
        while(n!=null){
            if(ns.get(temp1)!=null)
                return true;
            else ns.put(temp1, temp1);
            temp1 = temp1.getNext();
            if(temp1 == null)
                return false;
        }
        return true;
    }


    public void print() {
        Node temp = head.getNext();
//        Node temp = head;
        while (temp.getNext() != null) {
            System.out.println(temp.getData() + "");
            temp = temp.getNext();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DemoTest dt = new DemoTest();
        Node node = new Node(5);
        Node node1 = new Node(6);
        Node node2 = new Node(7);
        Node node3 = new Node(8);
        dt.addNode(node);
        dt.addNode(node1);
        dt.addNode(node2);
        dt.insertNodeByIndex(2, node3);
//        dt.delNodeByIndex(2);
        dt.print();
    }
}
