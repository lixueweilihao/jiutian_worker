package yarn.AbstractService;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.service.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/1/15  20:27
 */
public class AgentManager extends AbstractService {
    private static final Logger LOG = LoggerFactory.getLogger(AgentManager.class);
    private int expireInterval;
    private int monitorInterval;
    private Thread checkerThread;
    private boolean stopped = false;
    private Map<Agent, Long> running;

    public AgentManager() {
        super("AgentManager");
    }

    @Override
    protected void serviceInit(Configuration conf) throws Exception {
        super.serviceInit(conf);
        int expireIntvl = conf.getInt("com.baidu.dscheduler.agent.expire.ms",
                1000 * 60 * 10);
        //need some sanity check
        this.expireInterval = expireIntvl;
        this.monitorInterval = expireInterval / 3;
        this.checkerThread = new Thread(new PingChecker());
        this.checkerThread.setName("Ping Checker");
        this.checkerThread.setDaemon(true);
    }

    @Override
    protected void serviceStart() throws Exception {
        super.serviceStart();
        this.checkerThread.start();
    }

    @Override
    protected void serviceStop() throws Exception {
        stopped = true;
        checkerThread.interrupt();
        super.serviceStop();
    }

    private class PingChecker implements Runnable {
        private PingChecker() {
        }

        public void run() {
            while (!AgentManager.this.stopped && !Thread.currentThread().isInterrupted()) {
                synchronized (AgentManager.this) {
                    Iterator<Map.Entry<Agent, Long>> iterator = AgentManager.this.running.entrySet().iterator();
                    long currentTime = System.currentTimeMillis();

                    while (true) {
                        if (!iterator.hasNext()) {
                            break;
                        }

                        Map.Entry<Agent, Long> entry = (Map.Entry) iterator.next();
                        if (currentTime > ((Long) entry.getValue()).longValue() + (long) AgentManager.this.expireInterval) {
                            iterator.remove();
                            //AgentManager.this.expire(entry.getKey());
                            AgentManager.LOG.info("Expired:" + entry.getKey().toString() + " Timed out after " + AgentManager.this.expireInterval / 1000 + " secs");
                        }
                    }
                }
                try {
                    Thread.sleep(AgentManager.this.monitorInterval);
                    continue;
                } catch (InterruptedException e) {
                    AgentManager.this.LOG.info( AgentManager.this.getName() + " thread interrupted");
                }
            }
        }
    }
}