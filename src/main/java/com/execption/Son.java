package com.execption;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/1/30  14:47
 */
public class Son implements Cloneable {
    public static void main(String[] args) {
        Son p = new Son();
        System.out.println(p);
        Thread t = new Thread(new Runnable() {
            public void run() {
                ThreadLocal<Son> threadLocal = new ThreadLocal<>();
                System.out.println(threadLocal);
                threadLocal.set(p);
                System.out.println(threadLocal.get());
                threadLocal.remove();
                try {
                    threadLocal.set((Son) p.clone());
                    System.out.println(threadLocal.get());
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                System.out.println(threadLocal);
            }
        });
        t.setName("kafka");
        t.start();
    }
}
