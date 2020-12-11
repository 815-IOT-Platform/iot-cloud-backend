package com.iot.tpc.domain;

import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iot.common.annotation.Excel;
import com.iot.common.core.domain.BaseEntity;
import org.apache.ibatis.type.Alias;

import javax.persistence.*;
import java.util.List;

/**
 * 可靠消息对象 an_tpc_mq_message
 *
 * @author ananops
 * @date 2020-06-17
 */
@EqualsAndHashCode(callSuper = true)
@Alias(value = "tpcMqMessage")
@Table(name = "an_tpc_mq_message")
public class TpcMqMessage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 版本号 */
    @Excel(name = "版本号")
    private Long version;

    /** 消息key */
    @Excel(name = "消息key")
    private String messageKey;

    /** topic */
    @Excel(name = "topic")
    private String messageTopic;

    /** tag */
    @Excel(name = "tag")
    private String messageTag;

    /** 消息内容 */
    @Excel(name = "消息内容")
    private String messageBody;

    /** 消息类型: 10 - 有序消息 ; 20 - 无序消息 */
    @Excel(name = "消息类型: 10 - 有序消息 ; 20 - 无序消息")
    private Integer messageType;

    /** 生产者PID */
    @Excel(name = "生产者PID")
    private String producerGroup;

    /** 延时级别 1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h */
    @Excel(name = "延时级别 1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h")
    private Integer delayLevel;

    /** 顺序类型 0有序 1无序 */
    @Excel(name = "顺序类型 0有序 1无序")
    private Integer orderType;

    /** 消息状态 */
    @Excel(name = "消息状态")
    private Integer messageStatus;

    /** 状态 */
    @Excel(name = "状态")
    private Integer taskStatus;

    /** 重发次数 */
    @Excel(name = "重发次数")
    private Integer resendTimes;

    /** 是否死亡 0 - 活着; 1-死亡 */
    @Excel(name = "是否死亡 0 - 活着; 1-死亡")
    private Integer dead;

    /** 执行时间 */
    @Excel(name = "执行时间")
    private Integer nextExeTime;

    /** 是否删除 -0 未删除 -1 已删除 */
    @Excel(name = "是否删除 -0 未删除 -1 已删除")
    private Integer yn;

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

    @Transient
    private List<Integer> preStatusList;

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
    public void setMessageKey(String messageKey)
    {
        this.messageKey = messageKey;
    }

    public String getMessageKey()
    {
        return messageKey;
    }
    public void setMessageTopic(String messageTopic)
    {
        this.messageTopic = messageTopic;
    }

    public String getMessageTopic()
    {
        return messageTopic;
    }
    public void setMessageTag(String messageTag)
    {
        this.messageTag = messageTag;
    }

    public String getMessageTag()
    {
        return messageTag;
    }
    public void setMessageBody(String messageBody)
    {
        this.messageBody = messageBody;
    }

    public String getMessageBody()
    {
        return messageBody;
    }
    public void setMessageType(Integer messageType)
    {
        this.messageType = messageType;
    }

    public Integer getMessageType()
    {
        return messageType;
    }
    public void setProducerGroup(String producerGroup)
    {
        this.producerGroup = producerGroup;
    }

    public String getProducerGroup()
    {
        return producerGroup;
    }
    public void setDelayLevel(Integer delayLevel)
    {
        this.delayLevel = delayLevel;
    }

    public Integer getDelayLevel()
    {
        return delayLevel;
    }
    public void setOrderType(Integer orderType)
    {
        this.orderType = orderType;
    }

    public Integer getOrderType()
    {
        return orderType;
    }
    public void setMessageStatus(Integer messageStatus)
    {
        this.messageStatus = messageStatus;
    }

    public Integer getMessageStatus()
    {
        return messageStatus;
    }
    public void setTaskStatus(Integer taskStatus)
    {
        this.taskStatus = taskStatus;
    }

    public Integer getTaskStatus()
    {
        return taskStatus;
    }
    public void setResendTimes(Integer resendTimes)
    {
        this.resendTimes = resendTimes;
    }

    public Integer getResendTimes()
    {
        return resendTimes;
    }
    public void setDead(Integer dead)
    {
        this.dead = dead;
    }

    public Integer getDead()
    {
        return dead;
    }
    public void setNextExeTime(Integer nextExeTime)
    {
        this.nextExeTime = nextExeTime;
    }

    public Integer getNextExeTime()
    {
        return nextExeTime;
    }
    public void setYn(Integer yn)
    {
        this.yn = yn;
    }

    public Integer getYn()
    {
        return yn;
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

    public List<Integer> getPreStatusList() {
        return preStatusList;
    }

    public void setPreStatusList(List<Integer> preStatusList) {
        this.preStatusList = preStatusList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("version", getVersion())
                .append("messageKey", getMessageKey())
                .append("messageTopic", getMessageTopic())
                .append("messageTag", getMessageTag())
                .append("messageBody", getMessageBody())
                .append("messageType", getMessageType())
                .append("producerGroup", getProducerGroup())
                .append("delayLevel", getDelayLevel())
                .append("orderType", getOrderType())
                .append("messageStatus", getMessageStatus())
                .append("taskStatus", getTaskStatus())
                .append("updateTime", getUpdateTime())
                .append("resendTimes", getResendTimes())
                .append("dead", getDead())
                .append("nextExeTime", getNextExeTime())
                .append("yn", getYn())
                .append("createBy", getCreateBy())
                .append("creatorId", getCreatorId())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("lastOperatorId", getLastOperatorId())
                .append("companyId", getCompanyId())
                .append("companyName", getCompanyName())
                .append("remark", getRemark())
                .toString();
    }
}
