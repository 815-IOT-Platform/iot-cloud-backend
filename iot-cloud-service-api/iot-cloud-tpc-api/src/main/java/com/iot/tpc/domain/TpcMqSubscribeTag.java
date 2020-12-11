package com.iot.tpc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.ibatis.type.Alias;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * 消费者和TAG中间对象 an_tpc_mq_subscribe_tag
 * 
 * @author ananops
 * @date 2020-06-17
 */
@Table(name = "an_tpc_mq_subscribe_tag")
@Alias(value = "tpcMqSubscribeTag")
public class TpcMqSubscribeTag implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 消费者ID */
    private Long subscribeId;

    /** TAG_ID */
    private Long tagId;

    public void setSubscribeId(Long subscribeId) 
    {
        this.subscribeId = subscribeId;
    }

    public Long getSubscribeId() 
    {
        return subscribeId;
    }
    public void setTagId(Long tagId) 
    {
        this.tagId = tagId;
    }

    public Long getTagId() 
    {
        return tagId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("subscribeId", getSubscribeId())
            .append("tagId", getTagId())
            .toString();
    }
}
