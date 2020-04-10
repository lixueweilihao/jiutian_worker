package com.play.thread.线程池.ScheduledThreadPoolExecutor;

/**
 * @Author : lihao
 * Created on : 2020-04-07
 * @Description : TODO描述类作用
 */

import static java.util.concurrent.TimeUnit.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

public class BeeperControl {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void beepForAnHour() {
        final Runnable beeper = new Runnable() {
            public void run() {
                System.out.println("beep");
            }
        };
        //0延时，每1秒执行下beeper的任务
        final ScheduledFuture<?> beeperHandler = scheduler.scheduleAtFixedRate(beeper, 0, 1, SECONDS);
        //执行6秒后，取消beeperHandler任务的执行并退出程序
        scheduler.schedule(new Runnable() {
            public void run() {
                beeperHandler.cancel(true);
                System.exit(0);
            }
        }, 6, SECONDS);
    }

    public static void main(String[] args) {
        new BeeperControl().beepForAnHour();
    }
}


