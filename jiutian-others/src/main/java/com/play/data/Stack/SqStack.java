package com.play.data.Stack;

/**
 * Copyright @ 2018
 * All right reserved.
 *https://www.cnblogs.com/fzz9/p/8167546.html
 * @author Li Hao
 * @since 2019/2/27  19:33
 */
public class SqStack<T> {
    private T data[];//用数组表示栈元素
    private int maxSize;//栈空间大小(常量)
    private int top;//栈顶指针(指向栈顶元素)

    @SuppressWarnings("unchecked")
    public SqStack(int maxSize){
        this.maxSize = maxSize;
        this.data = (T[]) new Object[maxSize];//泛型数组不能直接new创建，需要使用Object来创建(其实一开始也可以直接使用Object来代替泛型)
        this.top = -1;//有的书中使用0，但这样会占用一个内存
    }

    //判断栈是否为空
    public boolean isNull(){
        boolean flag = this.top<=-1?true:false;
        return flag;
    }

    //判断是否栈满
    public boolean isFull(){
        boolean flag = this.top==this.maxSize-1?true:false;
        return flag;
    }

    //压栈
    public boolean push(T vaule){
        if(isFull()){
            //栈满
            return false;
        }else{
            data[++top] = vaule;//栈顶指针加1并赋值
            return true;
        }
    }

    //弹栈
    public T pop(){
        if(isNull()){
            //栈为空
            return null;
        }else{
            T value = data[top];//取出栈顶元素
            --top;//栈顶指针-1
            return value;
        }
    }

}
