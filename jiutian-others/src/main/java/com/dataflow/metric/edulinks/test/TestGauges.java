package com.dataflow.metric.edulinks.test;

/**
 * Created on 2019-11-03
 *
 * @author :hao.li
 * 参考：https://www.cnblogs.com/nexiyi/p/metrics_sample_1.html
 * https://blog.csdn.net/endlu/article/details/80162705
 */

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * User: hzwangxx
 * Date: 14-2-17
 * Time: 14:47
 * 测试Gauges，实时统计pending状态的job个数
 */
public class TestGauges {
    /**
     * 实例化一个registry，最核心的一个模块，相当于一个应用程序的metrics系统的容器，维护一个Map
     */
    private static final MetricRegistry metrics = new MetricRegistry();

    private static Queue<String> queue = new LinkedBlockingDeque<String>();

    /**
     * 在控制台上打印输出
     */
    private static ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics).build();

    public static void main(String[] args) throws InterruptedException {
//        reporter.start(3, TimeUnit.SECONDS);

        //实例化一个Gauge
        Gauge<Integer> gauge = new Gauge<Integer>() {
            @Override
            public Integer getValue() {
                return queue.size();
            }
        };

        //注册到容器中
        metrics.register(MetricRegistry.name(TestGauges.class, "pending-job", "size"), gauge);

        //测试JMX
        JmxReporter jmxReporter = JmxReporter.forRegistry(metrics).build();
        jmxReporter.start();

        //模拟数据
        for (int i=0; i<20; i++){
            queue.add("a");
            Thread.sleep(1000);
        }

    }
}

