package CountDownLatch;

/**
 * @Author : lihao
 * Created on : 2020-04-04
 * @Description : 使用CountDownLatch开启5个线程，将每个线程的计算结果最后统计。
 */

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * 开启5个线程进行计算，最后所有的线程都计算完了再统计计算结果
 */
public class Test5_CountDownLatch {

    private static Random random = new Random();

    public static void main(String[] args) {
        //数组大小
        int size = 50000;
        //定义数组
        int[] numbers = new int[size];
        //随机初始化数组
        for (int i = 0; i < size; i++) {
            numbers[i] = random.nextInt(100);
        }

        //单线程计算结果
        Long sum = 0L;
        for (int i = 0; i < size; i++) {
            sum += numbers[i];
        }
        System.out.println("单线程计算结果：" + sum);

        //多线程计算结果
        //定义长度为5的数组保存每个线程的计算结果
        final int[] results = new int[5];
        //定义一个大小为5的循环栅栏，传入的runnable是当barrier触发时执行
        CountDownLatch cdl = new CountDownLatch(5);
        Thread thread = new Thread() {
            public void run(){
                try {
                    cdl.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                long sums = 0;
                for(int j = 0;j< 5;j++)
                {
                    sums += results[j];
                }
                System.out.println("多线程计算结果："+sums);
            }
        };
        thread.start();
        //子数组长度
        int length = 10000;
        //定义五个线程去计算
        for (int i = 0; i < 5; i++) {
            //定义子数组
            int[] subNumbers = Arrays.copyOfRange(numbers, (i * length), ((i + 1) * length));
            //盛放计算结果
            int finalI = i;
            new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < subNumbers.length; j++) {
                        results[finalI] += subNumbers[j];
                    }
                    //等待其他线程进行计算
                    cdl.countDown();
                }
            }.start();

        }

    }

}
