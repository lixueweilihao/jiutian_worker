package com.play.data.linked.demo;

/**
 * @Author : lihao
 * Created on : 2020-04-15
 * @Description : https://blog.csdn.net/qq_38360675/article/details/88847620
 * https://www.jianshu.com/p/fc88c9b4ec12
 */

import java.io.*;
import java.util.*;

public class CH03_02 {

    public static void main(String[] args) throws IOException {
        BufferedReader buf;
        Random rand = new Random();
        buf = new BufferedReader(new InputStreamReader(System.in));

        StuLinkedList List = new StuLinkedList();
        int i, j, findword = 0, data[][] = new int[12][10];
        String name[] = new String[]{"Allen", "Scott", "Bell", "Kobe", "Jamse", "Jack", "Gorge", "Yao", "Bob", "Lisa",
                "Hanson", "Amy"};
        System.out.println("学号  成绩  学号  成绩  学号  成绩   学号  成绩\n");
        for (i = 0; i < 12; i++) {
            data[i][0] = i + 1;
            data[i][1] = (Math.abs(rand.nextInt(50))) + 50;
            List.insert(data[i][0], name[i], data[i][1]);
        }
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 4; j++) {
                System.out.println("[" + data[j * 3 + i][0] + "][" + data[j * 3 + i][1] + "]");
                System.out.println();
            }
            while (true) {
                System.out.println("输入要删除成绩的学号，结束输入-1");
                findword = Integer.parseInt(buf.readLine());
                if (findword == -1) {
                    break;
                } else {
                    Node current = List.first;
                    while (current.data != findword) {
                        current = current.next;
                        if (current.data == findword) {
                            List.delete(current);
                        }
                    }
                    if (List.first.data == findword) {
                        List.delete(current);
                    }
                    System.out.println("删除后成绩链表，请注意！要删除的成绩其学号必须在此链表中\n");
                    List.print();
                }
            }
        }
    }

}
