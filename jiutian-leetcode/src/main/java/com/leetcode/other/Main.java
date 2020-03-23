package com.leetcode.other;

/**
 * Created on 2020-02-29
 *
 * @author :hao.li
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static String[] say = new String[5];
    public static ArrayList<String> temp = new ArrayList<String>();
    public static ArrayList<String> result = new ArrayList<String>();

    public void swap(char[] A, int i, int j) {
        char s = A[i];
        A[i] = A[j];
        A[j] = s;
    }

    public void dfs(char[] A, int step) {
        if(step == A.length) {
            StringBuilder s = new StringBuilder("");
            for(int i = 0;i < A.length;i++)
                s.append(A[i]);
            temp.add(s.toString());
        } else {
            for(int i = step;i < A.length;i++) {
                swap(A, i, step);
                dfs(A, step + 1);
                swap(A, i, step);
            }
        }
    }

    public boolean judge(int a, String o, int p, int b) {
        if(b == 1) {  //此次说话为真
            if(o.equals(">")) {
                if(a > p)
                    return true;
                else
                    return false;
            } else if(o.equals(">=")) {
                if(a >= p)
                    return true;
                else
                    return false;
            } else if(o.equals("=")) {
                if(a == p)
                    return true;
                else
                    return false;
            } else if(o.equals("!=")) {
                if(a != p)
                    return true;
                else
                    return false;
            } else if(o.equals("<")) {
                if(a < p)
                    return true;
                else
                    return false;
            } else if(o.equals("<=")) {
                if(a <= p)
                    return true;
                else
                    return false;
            }
        } else if(b == 0) {  //此次说话为假
            if(o.equals(">")) {
                if(a <= p)
                    return true;
                else
                    return false;
            } else if(o.equals(">=")) {
                if(a < p)
                    return true;
                else
                    return false;
            } else if(o.equals("=")) {
                if(a != p)
                    return true;
                else
                    return false;
            } else if(o.equals("!=")) {
                if(a == p)
                    return true;
                else
                    return false;
            } else if(o.equals("<")) {
                if(a >= p)
                    return true;
                else
                    return false;
            } else if(o.equals("<=")) {
                if(a > p)
                    return true;
                else
                    return false;
            }
        }
        return false;
    }

    public void getResult(int i, int j) {
        for(int t = 0;t < temp.size();t++) {
            String s = temp.get(t);
            boolean judge1 = true;
            for(int m = 0;m < 5;m++) {
                char a = say[m].charAt(0);
                String o = "";
                int p = say[m].charAt(say[m].length() - 1) - '0';
                if(say[m].length() == 3) {
                    o = o + say[m].substring(1, 2);
                } else {
                    o = o + say[m].substring(1, 3);
                }
                if(i == m || j == m) {
                    judge1 = judge(s.indexOf(a) + 1, o, p, 1);
                } else {
                    judge1 = judge(s.indexOf(a) + 1, o, p, 0);
                }
                if(judge1 == false)
                    break;
            }
            if(judge1 == false)
                continue;
            int a1 = s.indexOf(('A'+i));
            int a2 = s.indexOf(('A'+j));
            if((a1 == 1 && a2 == 3) || (a1 == 3 && a2 == 1)) {
                judge1 = true;
            } else {
                judge1 = false;
            }

            if(judge1 == true) {
                if(!result.contains(s))
                    result.add(s);
            }
        }
    }

    public static void main(String[] args) {
        Main test = new Main();
        String A = "ABCDE";
        char[] B = A.toCharArray();
        test.dfs(B, 0);
        Scanner in = new Scanner(System.in);
        for(int i = 0;i < 5;i++)
            say[i] = in.next();
        for(int i = 0;i < 5;i++) {
            for(int j = i + 1;j < 5;j++)
                test.getResult(i, j);
        }
        Collections.sort(result);
        for(int i = 0;i < result.size();i++)
            System.out.println(result.get(i));
        System.out.println(result.size());
    }
}
