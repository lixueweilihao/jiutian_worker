package com.play.gun;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ComparisonChain;

import java.util.Objects;

/**
 * Copyright @ 2018 lihao.com
 * All right reserved.
 *
 * @author Li Hao
 * @since 2018/10/31  14:13
 */
public final class TaskUnit implements Comparable<TaskUnit> {
    private final String jobId;
    private final String partition;
    private final String worker;

    @JsonCreator
    public TaskUnit(@JsonProperty("jobId") String jobId,
                    @JsonProperty("partition") String partition,
                    @JsonProperty("worker") String worker) {
        this.jobId = jobId;
        this.partition = partition;
        this.worker = worker;
    }

    public String getJobId() {
        return jobId;
    }

    public String getPartition() {
        return partition;
    }

    public String getWorker() {
        return worker;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("jobId", jobId)
                .add("partition", partition)
                .add("worker", worker)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TaskUnit taskUnit = (TaskUnit) o;
        return Objects.equals(jobId, taskUnit.jobId)
                && Objects.equals(partition, taskUnit.partition)
                && Objects.equals(worker, taskUnit.worker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobId, partition, worker);
    }

    @Override
    public int compareTo(TaskUnit o) {
        return ComparisonChain.start()
                .compare(this.jobId, o.jobId)
                .compare(this.partition, o.partition)
                .compare(this.worker, o.worker)
                .result();
    }
}
