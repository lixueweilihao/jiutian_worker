package kafka.test.ES;

import kafka.test.kafka08.MyProducer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class ProducerMain {

    public static void main(String[] args) {
        String num = "1";
        BlockingQueue<Runnable> bqueue = new ArrayBlockingQueue<Runnable>(20);
        ThreadPoolExecutor poll = new ThreadPoolExecutor(20, 20, 200, TimeUnit.MILLISECONDS, bqueue);
        EsProducer mp = new EsProducer(num);
//        for (int i = 0; i < 100; i++) {
            poll.execute(mp);
//        }
//        poll.shu/tdown();
    }
}
