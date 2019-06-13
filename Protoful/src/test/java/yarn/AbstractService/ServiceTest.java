package yarn.AbstractService;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.service.Service;
import org.apache.hadoop.service.ServiceStateChangeListener;
import org.junit.Test;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/1/15  20:32
 */
public class ServiceTest {

    @Test
    public void basicTest() {
        Configuration conf  = new Configuration(false);
        //conf.addResource();
        AgentManager agentManager = new AgentManager();
        System.out.println("agentManager status after created: " + agentManager.getServiceState());
        agentManager.init(conf);
        System.out.println("agentManager status after instantiated: " + agentManager.getServiceState());
        agentManager.start();
        System.out.println("agentManager status after start: " + agentManager.getServiceState());
        agentManager.stop();
        System.out.println("agentManager status after stop: " + agentManager.getServiceState());
    }

    @Test
    public  void testStateChangeListener() {
        Configuration conf  = new Configuration(false);
        //conf.addResource();
        AgentManager agentManager = new AgentManager();
        agentManager.registerServiceListener(new ServiceStateChangeListener() {
            @Override
            public void stateChanged(Service service) {
                System.out.println("The state of service "
                        + service.getName()
                        + " changed to :"
                        + service.getServiceState());
            }
        });
        System.out.println("agentManager status after created: " + agentManager.getServiceState());
        agentManager.init(conf);
        System.out.println("agentManager status after instantiated: " + agentManager.getServiceState());
        agentManager.start();
        System.out.println("agentManager status after start: " + agentManager.getServiceState());
        agentManager.stop();
        System.out.println("agentManager status after stop: " + agentManager.getServiceState());
    }
}
