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
 * 订阅者状态确认对象 an_tpc_mq_confirm
 * 
 * @author ananops
 * @date 2020-06-17
 */
@Alias("tpcMqConfirm")
@Table(name = "an_tpc_mq_confirm")
@EqualsAndHashCode(callSuper = true)
public class TpcMqConfirm extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 版本号 */
    @Excel(name = "版本号")
    private Long version;

    /** 任务ID */
    @Excel(name = "任务ID")
    private Long messageId;

    /** 消息唯一标识 */
    @Excel(name = "消息唯一标识")
    private String messageKey;

    /** 消费者组编码 */
    @Excel(name = "消费者组编码")
    private String consumerCode;

    /** 消费的数次 */
    @Excel(name = "消费的数次")
    private Long consumeCount;

    /** 状态, 10 - 未确认 ; 20 - 已确认; 30 已消费 */
    @Excel(name = "状态, 10 - 未确认 ; 20 - 已确认; 30 已消费")
    private Long status;

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
    public void setMessageId(Long messageId) 
    {
        this.messageId = messageId;
    }

    public Long getMessageId() 
    {
        return messageId;
    }
    public void setMessageKey(String messageKey) 
    {
        this.messageKey = messageKey;
    }

    public String getMessageKey() 
    {
        return messageKey;
    }
    public void setConsumerCode(String consumerCode) 
    {
        this.consumerCode = consumerCode;
    }

    public String getConsumerCode() 
    {
        return consumerCode;
    }
    public void setConsumeCount(Long consumeCount) 
    {
        this.consumeCount = consumeCount;
    }

    public Long getConsumeCount() 
    {
        return consumeCount;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
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
            .append("messageId", getMessageId())
            .append("messageKey", getMessageKey())
            .append("consumerCode", getConsumerCode())
            .append("consumeCount", getConsumeCount())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("creatorId", getCreatorId())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("lastOperatorId", getLastOperatorId())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("companyId", getCompanyId())
            .append("companyName", getCompanyName())
            .toString();
    }

    /**
     * Instantiates a new Tpc mq confirm.
     *
     * @param id           the id
     * @param messageId    the message id
     * @param messageKey   the message key
     * @param consumerCode the consumer code
     */
    public TpcMqConfirm(final Long id, final Long messageId, final String messageKey, final String consumerCode) {
        this.id = id;
        this.messageId = messageId;
        this.messageKey = messageKey;
        this.consumerCode = consumerCode;
    }
}
