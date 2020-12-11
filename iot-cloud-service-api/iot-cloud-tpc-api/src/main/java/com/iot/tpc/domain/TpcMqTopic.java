package com.iot.tpc.domain;

import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iot.common.annotation.Excel;
import com.iot.common.core.domain.BaseEntity;
import org.apache.ibatis.type.Alias;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MQ主题对象 an_tpc_mq_topic
 * 
 * @author ananops
 * @date 2020-06-17
 */
@EqualsAndHashCode(callSuper = true)
@Table(name = "an_tpc_mq_topic")
@Alias(value = "tpcMqTopic")
public class TpcMqTopic extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 版本号 */
    @Excel(name = "版本号")
    private Long version;

    /** 生产者ID */
    @Excel(name = "生产者ID")
    private Long producerId;

    /** 城市编码 */
    @Excel(name = "城市编码")
    private String topicCode;

    /** 区域编码 */
    @Excel(name = "区域编码")
    private String topicName;

    /** MQ类型, 10 rocketmq 20 kafka */
    @Excel(name = "MQ类型, 10 rocketmq 20 kafka")
    private Integer mqType;

    /** 消息类型, 10 无序消息, 20 无序消息 */
    @Excel(name = "消息类型, 10 无序消息, 20 无序消息")
    private Integer msgType;

    /** 状态, 10生效,20,失效 */
    @Excel(name = "状态, 10生效,20,失效")
    private Integer status;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;

    /** 创建人ID */
    @Excel(name = "创建人ID")
    private Long creatorId;

    /** 最后操作人ID */
    @Excel(name = "最后操作人ID")
    private Long lastOperatorId;

    /** 公司Id */
    @Excel(name = "公司Id")
    private Long companyId;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String companyName;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setVersion(Long version) 
    {
        this.version = version;
    }

    public Long getVersion() 
    {
        return version;
    }
    public void setProducerId(Long producerId) 
    {
        this.producerId = producerId;
    }

    public Long getProducerId() 
    {
        return producerId;
    }
    public void setTopicCode(String topicCode) 
    {
        this.topicCode = topicCode;
    }

    public String getTopicCode() 
    {
        return topicCode;
    }
    public void setTopicName(String topicName) 
    {
        this.topicName = topicName;
    }

    public String getTopicName() 
    {
        return topicName;
    }
    public void setMqType(Integer mqType)
    {
        this.mqType = mqType;
    }

    public Integer getMqType()
    {
        return mqType;
    }
    public void setMsgType(Integer msgType)
    {
        this.msgType = msgType;
    }

    public Integer getMsgType()
    {
        return msgType;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public String getRemark()
    {
        return remark;
    }
    public void setCreatorId(Long creatorId) 
    {
        this.creatorId = creatorId;
    }

    public Long getCreatorId() 
    {
        return creatorId;
    }
    public void setLastOperatorId(Long lastOperatorId) 
    {
        this.lastOperatorId = lastOperatorId;
    }

    public Long getLastOperatorId() 
    {
        return lastOperatorId;
    }
    public void setCompanyId(Long companyId) 
    {
        this.companyId = companyId;
    }

    public Long getCompanyId() 
    {
        return companyId;
    }
    public void setCompanyName(String companyName) 
    {
        this.companyName = companyName;
    }

    public String getCompanyName() 
    {
        return companyName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("version", getVersion())
            .append("producerId", getProducerId())
            .append("topicCode", getTopicCode())
            .append("topicName", getTopicName())
            .append("mqType", getMqType())
            .append("msgType", getMsgType())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("creatorId", getCreatorId())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("lastOperatorId", getLastOperatorId())
            .append("updateTime", getUpdateTime())
            .append("companyId", getCompanyId())
            .append("companyName", getCompanyName())
            .toString();
    }
}
