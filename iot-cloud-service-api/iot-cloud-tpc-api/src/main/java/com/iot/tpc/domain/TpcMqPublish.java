package com.iot.tpc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 发布关系对象 an_tpc_mq_publish
 * 
 * @author ananops
 * @date 2020-06-17
 */
public class TpcMqPublish implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 生产者ID */
    private Long producerId;

    /** TOPIC_ID */
    private Long topicId;

    public void setProducerId(Long producerId) 
    {
        this.producerId = producerId;
    }

    public Long getProducerId() 
    {
        return producerId;
    }
    public void setTopicId(Long topicId) 
    {
        this.topicId = topicId;
    }

    public Long getTopicId() 
    {
        return topicId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("producerId", getProducerId())
            .append("topicId", getTopicId())
            .toString();
    }
}
