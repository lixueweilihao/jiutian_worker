package com.play.thread.线程池.ScheduledThreadPoolExecutor;

import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2020-03-20
 *
 * @author :hao.li
 */
//周期函数测试类
public class ScheduledTask {
    public ScheduledThreadPoolExecutor se = new ScheduledThreadPoolExecutor(5);
    public static void main(String[] args) {
        new ScheduledTask();
    }
    public void fixedPeriodSchedule() {
        // 设定可以循环执行的runnable,初始延迟为0，这里设置的任务的间隔为1秒
        for(int i=0;i<5;i++){
            se.scheduleAtFixedRate(new FixedSchedule(), 0, 4, TimeUnit.SECONDS);
        }
    }
    public ScheduledTask() {
        fixedPeriodSchedule();
    }
    class FixedSchedule implements Runnable {
        public void run() {
            System.out.println("当前线程："+Thread.currentThread().getName()+"  当前时间："+new Date(System.currentTimeMillis()));
        }
    }
}
