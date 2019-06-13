package zookeeper;

/**
 * Copyright @ 2018 lihao.com
 * All right reserved.
 *https://blog.csdn.net/chinabestchina/article/details/78956703
 * @author Li Hao
 * @since 2018/9/26  11:09
 */
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.queue.DistributedQueue;
import org.apache.curator.framework.recipes.queue.QueueBuilder;
import org.apache.curator.framework.recipes.queue.QueueConsumer;
import org.apache.curator.framework.recipes.queue.QueueSerializer;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

public class ZookeeperDistributedQueueMain {
    public static void main(String[] args) throws Exception {
        //创建zookeeper客户端
        CuratorFramework client = CuratorFrameworkFactory.newClient("10.37.167.202:2015", new ExponentialBackoffRetry(1000, 3));
        client.start();

        //指定锁路径
        String lockPath = "/fm/tasks";

        //消费监听器
        QueueConsumer<String> queueConsumer = new QueueConsumer<String>() {
            //消费队列数据
            @Override
            public void consumeMessage(String message) throws Exception {
                System.out.println("message: " + message);
            }

            @Override
            public void stateChanged(CuratorFramework client, ConnectionState newState) {

            }
        };

        //序列化
        QueueSerializer<String> queueSerializer = new QueueSerializer<String>() {
            @Override
            public byte[] serialize(String item) {
                return item.getBytes();
            }

            @Override
            public String deserialize(byte[] bytes) {
                return new String(bytes);
            }
        };

        //创建分布式队列
        QueueBuilder<String> distributedBuilder = QueueBuilder.builder(client, queueConsumer, queueSerializer, lockPath);
        DistributedQueue<String> distributedQueue = distributedBuilder.buildQueue();
        distributedQueue.start();

        //生成线程池
        ExecutorService executor = Executors.newCachedThreadPool();
        //生产
        BiConsumer<DistributedQueue<String>, String> putConsumer = (queue, item) -> {
            try {
                Callable<Boolean> call = () -> {
                    try {
                        //向队尾添加数据
                        queue.put(item);
                        System.out.println(Thread.currentThread() + "  put : "+ item);
                    } catch (Exception e) {
                    }
                    return true;
                };
                executor.submit(call);
            } catch (Exception e) {

            }
        };

        //分布式队列测试(5个线程生产)
        System.out.println("5个并发线程生产,测试分布式队列");
        //5个生产线程
        for (int i = 0; i < 5; i++) {
            putConsumer.accept(distributedQueue, "item"+i);
        }
//        executor.shutdown();
//        TimeUnit.SECONDS.sleep(20);
//        distributedQueue.close();
//        client.close();
    }
}
