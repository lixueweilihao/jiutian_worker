package kafka.kafka08;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class ProducerMain {

    public static void main(String[] args) {
        BlockingQueue<Runnable> bqueue = new ArrayBlockingQueue<Runnable>(20);
        ThreadPoolExecutor poll = new ThreadPoolExecutor(20, 20, 200, TimeUnit.MILLISECONDS, bqueue);
        MyProducer mp = new MyProducer();
        for (int i = 0; i < 10; i++) {
            poll.execute(mp);
        }
        poll.shutdown();
    }
}
