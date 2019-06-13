package zookeeper.HA;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.utils.CloseableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/2/2  17:39
 */
public class ElectorRobot extends LeaderSelectorListenerAdapter implements Elector, Closeable {
    private static final Logger logger = LoggerFactory.getLogger(ElectorRobot.class);
    private final LeaderSelector leaderSelector;
    private final AtomicBoolean leader;

    public ElectorRobot(CuratorFramework client, String path, String name) {
        // create a leader selector using the given path for management
        // all participants in a given leader selection must use the same path
        // Master here is also a LeaderSelectorListener but this isn't required
        leaderSelector = new LeaderSelector(client, path, this);
        // for most cases you will want your instance to requeue when it relinquishes leadership
        leaderSelector.setId(name);
        leaderSelector.autoRequeue();
        leader = new AtomicBoolean(false);
    }

    public void open() throws IOException {
        // the selection for this instance doesn't start until the leader selector is started
        // leader selection is done in the background so this call to leaderSelector.start()
        // returns immediately
        logger.info("Starting leader election as {}...", leaderSelector.getId());
        leaderSelector.start();
        logger.info("Started leader election as {}.", leaderSelector.getId());
    }

    @Override
    public boolean wins() {
        return leader.get() && leaderSelector.hasLeadership();
    }

    @Override
    public void takeLeadership(CuratorFramework client) throws Exception {
        leader.compareAndSet(false, true);
        logger.info("{} got leadership.", leaderSelector.getId());
        try {
            while (true) {
                Thread.sleep(100);
                logger.info("{} is now the leader.", leaderSelector.getId());
            }
        } catch (InterruptedException e) {
            logger.error("{} lost leadership!", leaderSelector.getId());
            Thread.currentThread().interrupt();
        } finally {
            leader.set(false);
        }
    }

    @Override
    public void close() {
        logger.info("{} relinquish leadership.", leaderSelector.getId());
        CloseableUtils.closeQuietly(leaderSelector);
    }
}
