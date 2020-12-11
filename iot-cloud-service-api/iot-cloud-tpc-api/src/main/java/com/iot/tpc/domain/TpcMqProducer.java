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
 * 生产者对象 an_tpc_mq_producer
 * 
 * @author ananops
 * @date 2020-06-17
 */
@EqualsAndHashCode(callSuper = true)
@Table(name = "an_tpc_mq_producer")
@Alias(value = "tpcMqProducer")
public class TpcMqProducer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 版本号 */
    @Excel(name = "版本号")
    private Long version;

    /** 微服务名称 */
    @Excel(name = "微服务名称")
    private String applicationName;

    /** 城市编码 */
    @Excel(name = "城市编码")
    private String producerCode;

    /** 区域编码 */
    @Excel(name = "区域编码")
    private String producerName;

    /** 提供查询对账的地址 */
    @Excel(name = "提供查询对账的地址")
    private String queryMessageUrl;

    /** 状态, 10生效,20,失效 */
    @Excel(name = "状态, 10生效,20,失效")
    private Integer status;

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
    public void setApplicationName(String applicationName) 
    {
        this.applicationName = applicationName;
    }

    public String getApplicationName() 
    {
        return applicationName;
    }
    public void setProducerCode(String producerCode) 
    {
        this.producerCode = producerCode;
    }

    public String getProducerCode() 
    {
        return producerCode;
    }
    public void setProducerName(String producerName) 
    {
        this.producerName = producerName;
    }

    public String getProducerName() 
    {
        return producerName;
    }
    public void setQueryMessageUrl(String queryMessageUrl) 
    {
        this.queryMessageUrl = queryMessageUrl;
    }

    public String getQueryMessageUrl() 
    {
        return queryMessageUrl;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
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
            .append("applicationName", getApplicationName())
            .append("producerCode", getProducerCode())
            .append("producerName", getProducerName())
            .append("queryMessageUrl", getQueryMessageUrl())
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
