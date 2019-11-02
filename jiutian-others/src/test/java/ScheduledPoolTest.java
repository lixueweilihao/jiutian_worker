import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2019-11-02
 *
 * @author :hao.li
 */
public class ScheduledPoolTest {

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("hh:mm:ss");

    private static final Random RANDOM = new Random();

    /** 延迟多久执行
     * 输出：
     *  11:04:32
     11:04:35
     */
    public static void schedule() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        printTime();
        scheduledExecutorService.schedule(new Task(), 3, TimeUnit.SECONDS);
    }

    /** 固定周期性执行
     * 输出：
     *  11:05:34
     11:05:36
     11:05:46
     11:05:56
     11:06:06
     11:06:16
     ......
     */
    public static void scheduleAtFixedRate() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        printTime();
        scheduledExecutorService.scheduleAtFixedRate(new Task(), 2, 10, TimeUnit.SECONDS);
    }

    /**
     * 输出：
     *  11:07:39
     11:07:41
     11:07:54
     11:08:08
     11:08:22
     11:08:33
     ......
     */
    public static void scheduleWithFixedDelay() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        printTime();
        scheduledExecutorService.scheduleWithFixedDelay(new Task(), 2, 10, TimeUnit.SECONDS);
    }

    static class Task implements Runnable{
        public void run() {
            printTime();
            try {
                Thread.sleep(RANDOM.nextInt(5) * 1000);
            } catch (InterruptedException e) {
                System.out.println("test");
                e.printStackTrace();
            }
        }
    }

    public static void printTime() {
        Date date = new Date();
        System.out.println(FORMAT.format(date));
    }

    public static void main(String[] args) {
        scheduleWithFixedDelay();
    }
}
