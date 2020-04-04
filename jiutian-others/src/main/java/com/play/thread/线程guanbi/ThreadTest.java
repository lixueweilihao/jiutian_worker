package com.play.thread.线程guanbi;

/**
 * @Author : lihao
 * Created on : 2020-04-04
 * @Description : TODO描述类作用
 */
public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Task("1"));
        t.start();
        t.interrupt();
        System.out.println("线程1是否中断:"+t.getName()+":"+t.isInterrupted());
    }

    static class Task implements Runnable{
        String name;

        public Task(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("thread has been interrupt!");
            }
            System.out.println("isInterrupted: " +Thread.currentThread().getName()+"_"+Thread.currentThread().isInterrupted());
            System.out.println("task " + name + " is over");
        }
    }
}
