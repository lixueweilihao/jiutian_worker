package yarn.EventStore;

import org.apache.hadoop.yarn.event.AbstractEvent;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/1/14  16:43
 */
public class TaskEvent extends AbstractEvent<TaskEventType> {
    private String taskID; //Task ID

    public TaskEvent(String taskID, TaskEventType type) {
        super(type);
        this.taskID = taskID;
    }

    public String getTaskID() {
        return taskID;
    }
}
