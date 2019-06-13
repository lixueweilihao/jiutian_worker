package com.data.Stack;

import java.util.Stack;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/3/4  20:11
 */
public class twenty {
    public boolean matching(String expression) {
        if (expression == null || expression == "") {
            System.out.println("输入表达式为空或没有输入表达式");
        }
        Stack<Character> stack = new Stack<Character>();
        for(int i =0;i<expression.length();i++){
            stack.push(expression.charAt(i));
        }
        for (int index = 0; index < expression.length(); index++) {
            switch (expression.charAt(index)) {
                case '(':
                    stack.push(expression.charAt(index));
                    break;
                case '{':
                    stack.push(expression.charAt(index));
                    break;
                case '[':
                    stack.push(expression.charAt(index));
                    break;
                case ')':
                    if (!stack.empty() && stack.peek() == '(') {
                        stack.pop();
                    }
                    break;
                case '}':
                    if (!stack.empty() && stack.peek() == '{') {
                        stack.pop();
                    }
                    break;
                case ']':
                    if (!stack.empty() && stack.peek() == '[') {
                        stack.pop();
                    }
            }
        }
        if (stack.empty()) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        twenty t = new twenty();
        System.out.println(t.matching("]"));

    }
}
