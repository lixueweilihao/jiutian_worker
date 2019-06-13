package yarn.stateMachine;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.service.CompositeService;
import org.apache.hadoop.service.Service;
import org.apache.hadoop.yarn.event.AsyncDispatcher;
import org.apache.hadoop.yarn.event.Dispatcher;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/1/14  16:45
 */
@SuppressWarnings("unchecked")
public class SimpleMRAppMaster extends CompositeService {
    private Dispatcher dispatcher;       //中央异步调度器
    private String jobID;

    public SimpleMRAppMaster(String name, String jobID) {
        super(name);
        this.jobID = jobID;

    }

    public void serviceInit(final Configuration conf) throws Exception {
        dispatcher = new AsyncDispatcher();//定义一个中央异步调度器
        //注册状态机处理器
        dispatcher.register(JobEventType.class, new JobStateMachine(jobID, dispatcher.getEventHandler()));
        addService((Service) dispatcher);
        super.serviceInit(conf);
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }
}
