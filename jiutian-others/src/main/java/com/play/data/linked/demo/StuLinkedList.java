package com.play.data.linked.demo;

/**
 * @Author : lihao
 * Created on : 2020-04-15
 * @Description : TODO描述类作用
 */
/**
 * 程序目的：定义头结点和尾结点的节点指针，以及建链表方法和删除节点方法
 * @author 86176
 *
 */
public class StuLinkedList {
    public Node first;
    public Node last;

    public boolean isEmpty() {
        return first == null;
    }

    // 创建输入方法
    public void print() {
        Node current = first;
        while (current != null) {
            System.out.println("[" + current.data + "" + current.names + "" + current.np + "]");
            current = current.next;
        }
        System.out.println();
    }

    // 创建插入方法
    public void insert(int data, String names, int np) {
        Node newNode = new Node(data, np, names);
        if (this.isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
    }

    // 创建删除节点的方法
    public void delete(Node delNode) {
        Node newNode;
        Node tmp;
        // 删除链表的第一个节点，只需要把链表首指针指向第二个节点即可
        if (first.data == delNode.data) {
            first = first.next;
        }
        // 删除链表后的最后一个节点，只要将指向最后一个节点的指针直接指向null即可
        else if (last.data == delNode.data) {
            System.out.println("I am here\n");
            newNode = first;
            while (newNode.next != last) {
                newNode = newNode.next;
//                newNode.next = last.next;
//                last = newNode;
            }
            newNode.next=last.next;
        }
        //删除链表内的中间节点：只要将删除节点的前一个节点指针指向要删除节点的下一个节点即可
        else {
            newNode = first;
            tmp = first;
            while (newNode.data != delNode.data) {
                tmp = newNode;
                newNode = newNode.next;
            }
            tmp.next = delNode.next;
        }
    }

}

