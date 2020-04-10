package CountDownLatch;

import com.google.gson.internal.Streams;
import org.apache.lucene.util.automaton.LimitedFiniteStringsIterator;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

/**
 * @Author : lihao
 * Created on : 2020-04-04
 * @Description : TODO描述类作用
 */


public class Test5 {
    private static Random random = new Random();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
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
        final int[] results = new int[5];
        //子数组长度
        int length = 10000;
        //定义五个线程去计算
        ExecutorService executor = Executors.newFixedThreadPool(5);
        Callable myCallable = null;
        List<Future> list = new ArrayList<Future>();
        for (int i = 0; i < 5; i++) {
            //定义子数组
            int[] subNumbers = Arrays.copyOfRange(numbers, (i * length), ((i + 1) * length));
            //盛放计算结果
            int finalI = i;
            myCallable = new Callable() {
                @Override
                public Integer call() throws Exception {
                    for (int j = 0; j < subNumbers.length; j++) {
                        results[finalI] += subNumbers[j];
                    }
                    Thread.sleep(10000);
                    return results[finalI];
                }

            };
            Future future = executor.submit(myCallable);
            list.add(future);
        }

        System.out.println("提交任务之前 " + getStringDate());
        int sum2 = 0;
        for (int i = 0; i < list.size(); i++) {
            boolean done = list.get(i).isDone();
            if (!done){
                System.out.println("------------------");
            }
            sum2 += Integer.parseInt(list.get(i).get().toString());

        }
        System.out.println(sum2);
        System.out.println("提交任务之后，获取结果之前 " + sum2);
        System.out.println("获取到结果之后 " + getStringDate());
    }

    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
}
