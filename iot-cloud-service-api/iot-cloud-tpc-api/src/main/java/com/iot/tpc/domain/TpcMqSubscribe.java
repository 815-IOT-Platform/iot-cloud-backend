package com.iot.tpc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iot.common.annotation.Excel;
import org.apache.ibatis.type.Alias;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 订阅关系对象 an_tpc_mq_subscribe
 * 
 * @author ananops
 * @date 2020-06-17
 */
@Alias("tpcMqSubscribe")
@Table(name = "an_tpc_mq_subscribe")
public class TpcMqSubscribe implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 消费者ID */
    @Excel(name = "消费者ID")
    private Long consumerId;

    /** 消费者组 */
    @Excel(name = "消费者组")
    private String consumerCode;

    /** TOPIC_ID */
    @Excel(name = "TOPIC_ID")
    private Long topicId;

    /** 主题编码 */
    @Excel(name = "主题编码")
    private String topicCode;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setConsumerId(Long consumerId) 
    {
        this.consumerId = consumerId;
    }

    public Long getConsumerId() 
    {
        return consumerId;
    }
    public void setConsumerCode(String consumerCode) 
    {
        this.consumerCode = consumerCode;
    }

    public String getConsumerCode() 
    {
        return consumerCode;
    }
    public void setTopicId(Long topicId) 
    {
        this.topicId = topicId;
    }

    public Long getTopicId() 
    {
        return topicId;
    }
    public void setTopicCode(String topicCode) 
    {
        this.topicCode = topicCode;
    }

    public String getTopicCode() 
    {
        return topicCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("consumerId", getConsumerId())
            .append("consumerCode", getConsumerCode())
            .append("topicId", getTopicId())
            .append("topicCode", getTopicCode())
            .toString();
    }
}
