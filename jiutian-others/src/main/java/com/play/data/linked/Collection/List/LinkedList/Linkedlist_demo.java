package com.play.data.linked.Collection.List.LinkedList;

import java.util.*;

/**
 * @Author : lihao
 * Created on : 2020-04-16
 * @Description : https://juejin.im/post/5abfde3f6fb9a028e46ec51c
 */
public class Linkedlist_demo {
    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        list.add("abc");
    }
    public class SimpleStack<E> {
        //默认容量
        private static final int DEFAULT_CAPACITY = 10;
        //栈中存放元素的数组
        private Object[] elements;
        //栈中元素的个数
        private int size = 0;
        //栈顶指针
        private int top;


        public SimpleStack() {
            this(DEFAULT_CAPACITY);
        }

        public SimpleStack(int initialCapacity) {
            elements = new Object[initialCapacity];
            top = -1;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        @SuppressWarnings("unchecked")
        public E pop() throws Exception {
            if (isEmpty()) {
                throw new EmptyStackException();
            }

            E element = (E) elements[top];
            elements[top--] = null;
            size--;
            return element;
        }

        @SuppressWarnings("unchecked")
        public E peek() throws Exception {
            if (isEmpty()) {
                throw new Exception("当前栈为空");
            }
            return (E) elements[top];
        }

        public void push(E element) throws Exception {
            //添加之前确保容量是否满足条件
            ensureCapacity(size + 1);
            elements[size++] = element;
            top++;
        }

        private void ensureCapacity(int minSize) {
            if (minSize - elements.length > 0) {
                grow();
            }
        }

        private void grow() {
            int oldLength = elements.length;
            // 更新容量操作 扩充为原来的1.5倍 这里也可以选择其他方案
            int newLength = oldLength + (oldLength >> 1);
            elements = Arrays.copyOf(elements, newLength);
        }
    }

}
