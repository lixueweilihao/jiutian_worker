package com.play.data.Stack;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/2/27  19:51
 */
class LinkStack<T>{
    //栈顶节点
    private LinkNode<T> top;

    //初始化1
    public LinkStack(){
        this.top = new LinkNode<T>();
    }

    //初始化栈
    public void initStack(){
        this.top.setData(null);
        this.top.setNext(null);
    }
    //是否栈空
    public boolean isNull(){
        boolean flag = top.getNext()==null?true:false;
        return flag;
    }

    //压栈
    public void push(LinkNode<T> node){
        if(isNull()){
            //栈空，即第一次插入
            top.setNext(node);
            node.setNext(null);//该句可以省略(首次插入的元素为栈底元素)
        }else{
            node.setNext(top.getNext());
            top.setNext(node);
        }
    }

    //弹栈
    public LinkNode<T> pop(){
        if(isNull()){
            //栈空无法弹栈
            return null;
        }else{
            LinkNode<T> delNode = top.getNext();//取出删除节点
            top.setNext(top.getNext().getNext());//删除节点
            return delNode;
        }
    }
}


//链式栈节点（外部类实现，也可以使用内部类）
class LinkNode<T>{
    private T data;//数据域
    private LinkNode<T> next;//指针域

    //初始化1
    public LinkNode(){
        this.data = null;
        this.next = null;
    }

    //初始化2
    public LinkNode(T data) {
        super();
        this.data = data;
        this.next = null;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public LinkNode<T> getNext() {
        return next;
    }
    public void setNext(LinkNode<T> next) {
        this.next = next;
    }
}
