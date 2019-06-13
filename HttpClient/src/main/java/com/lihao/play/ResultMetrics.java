package com.lihao.play;

import java.util.List;

/**
 * Copyright @ 2018 lihao.com
 * All right reserved.
 *
 * @author Li Hao
 * @since 2018/9/14  22:45
 */
public class ResultMetrics {

    private String errorMessage;

    private List<Metrics> result;

    private Integer success;

    private Boolean successData;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<Metrics> getResult() {
        return result;
    }

    public void setResult(List<Metrics> result) {
        this.result = result;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public Boolean getSuccessData() {
        return successData;
    }

    public void setSuccessData(Boolean successData) {
        this.successData = successData;
    }
}

class Metrics {
    private String createTime;

    private Long log;

    private String lastSeenTime;

    private Long logSize;

    private Long offset;

    private String owner;
    private String partition;
    private String replicas;
    private String topicName;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Long getLog() {
        return log;
    }

    public void setLog(Long log) {
        this.log = log;
    }

    public String getLastSeenTime() {
        return lastSeenTime;
    }

    public void setLastSeenTime(String lastSeenTime) {
        this.lastSeenTime = lastSeenTime;
    }

    public Long getLogSize() {
        return logSize;
    }

    public void setLogSize(Long logSize) {
        this.logSize = logSize;
    }

    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPartition() {
        return partition;
    }

    public void setPartition(String partition) {
        this.partition = partition;
    }

    public String getReplicas() {
        return replicas;
    }

    public void setReplicas(String replicas) {
        this.replicas = replicas;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
}