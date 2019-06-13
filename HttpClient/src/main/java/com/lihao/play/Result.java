package com.lihao.play;

/**
 * Copyright @ 2018 lihao.com
 * All right reserved.
 *
 * @author Li Hao
 * @since 2018/9/18  14:15
 */
public class Result {
    private String topicName;

    private String groupId;

    private String brokerList;

    private String dcAbbrName;

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getBrokerList() {
        return brokerList;
    }

    public void setBrokerList(String brokerList) {
        this.brokerList = brokerList;
    }

    public String getDcAbbrName() {
        return dcAbbrName;
    }

    public void setDcAbbrName(String dcAbbrName) {
        this.dcAbbrName = dcAbbrName;
    }


}
