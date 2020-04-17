package com.play.data.linked.shuang;

/**
 * @Author : lihao
 * Created on : 2020-04-13
 * @Description : TODO描述类作用
 */

public class Node {
    public Object e;
    public Node next;
    public Node pre;
    public Node(){

    }
    public Node(Object e){
        this.e = e;
        next = null;
        pre = null;
    }
}
