package yarn.stateMachine;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.yarn.conf.YarnConfiguration;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/1/14  20:17
 */
public class SimpleMRAppMasterTest {
    public static void main(String args[]) throws Exception{
        String jobID = "job_20131215_12" ;
        SimpleMRAppMaster appMaster = new SimpleMRAppMaster("Simple MRAppMaster" , jobID ) ;
        YarnConfiguration conf = new YarnConfiguration(new Configuration()) ;
        appMaster.serviceInit(conf) ;
        appMaster.init(conf) ;
        appMaster.start() ;
        appMaster.getDispatcher().getEventHandler().handle(new JobEvent(jobID,JobEventType.JOB_INIT)) ;
    }
}
