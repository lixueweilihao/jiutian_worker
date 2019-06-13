package Buidler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class ScheduledExecutorTest implements Runnable {
    private String jobName = "";
    public ScheduledExecutorTest(String jobname) {
        super();
        this.jobName = jobname;
    }
    @Override
    public void run() {
        System.out.println("execute " + jobName);
    }
    public static void main(String[] args) {
        int a = 5;
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        if(a<5) {
            service.scheduleAtFixedRate(new ScheduledExecutorTest("test1"), 1, 2, TimeUnit.SECONDS);
        }
        service.scheduleWithFixedDelay(new ScheduledExecutorTest("test2"), 1, 1, TimeUnit.SECONDS);
        /*ScheduledFuture<?> able=service.schedule(new ScheduledExecutorTest("test1"), 2, TimeUnit.SECONDS);
        try {
            System.out.println(able.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/
    }
}