package yarn.EventStore;

import org.apache.hadoop.yarn.event.AbstractEvent;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/1/14  16:44
 */
public class JobEvent extends AbstractEvent<JobEventType> {
    private String jobID;

    public JobEvent(String jobID, JobEventType type) {
        super(type);
        this.jobID = jobID;
    }

    public String getJobId() {
        return jobID;
    }
}
