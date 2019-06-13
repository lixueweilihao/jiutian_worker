/*
 * Copyright 2017 Suning Inc.
 * Created by Yan Jian on 2017/6/5.
 */
package com.play.http.client;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.common.base.MoreObjects;

import java.util.Date;

@JsonDeserialize(builder = KafkaMetrics.Builder.class)
public final class KafkaMetrics {
    private final String topic;
    private final Integer partition;
    private final String groupId;
    private final Long offset;
    private final Long logSize;
    private final Long lag;
    private final String owner;
    private final Long mtime;

    protected KafkaMetrics(String topic, Integer partition, String groupId, Long offset, Long logSize,
                           Long lag, String owner, Long mtime) {
        this.topic = topic;
        this.partition = partition;
        this.groupId = groupId;
        this.offset = offset;
        this.logSize = logSize;
        this.lag = lag;
        this.owner = owner;
        this.mtime = mtime;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("topic", topic)
                .add("partition", partition)
                .add("groupId", groupId)
                .add("offset", offset)
                .add("logSize", logSize)
                .add("lag", lag)
                .add("owner", owner)
                .add("mtime", new Date(mtime))
                .toString();
    }

    public String getTopic() {
        return topic;
    }

    public Integer getPartition() {
        return partition;
    }

    public String getGroupId() {
        return groupId;
    }

    public Long getOffset() {
        return offset;
    }

    public Long getLogSize() {
        return logSize;
    }

    public Long getLag() {
        return lag;
    }

    public String getOwner() {
        return owner;
    }

    public Long getMtime() {
        return mtime;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {
        private String topic;
        private Integer partition;
        private String groupId;
        private Long offset;
        private Long logSize;
        private Long lag;
        private String owner;
        private Long mtime;

        public Builder setTopic(String topic) {
            this.topic = topic;
            return this;
        }

        public Builder setPartition(Integer partition) {
            this.partition = partition;
            return this;
        }

        public Builder setGroupId(String groupId) {
            this.groupId = groupId;
            return this;
        }

        public Builder setOffset(Long offset) {
            this.offset = offset;
            return this;
        }

        public Builder setLogSize(Long logSize) {
            this.logSize = logSize;
            return this;
        }

        public Builder setLag(Long lag) {
            this.lag = lag;
            return this;
        }

        public Builder setOwner(String owner) {
            this.owner = owner;
            return this;
        }

        public Builder setMtime(Long mtime) {
            this.mtime = mtime;
            return this;
        }

        public KafkaMetrics build() {
            return new KafkaMetrics(topic, partition, groupId, offset, logSize, lag, owner, mtime);
        }
    }
}
