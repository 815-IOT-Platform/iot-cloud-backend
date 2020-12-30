package com.iot.device.model.domain;

import com.iot.common.annotation.Excel;
import com.iot.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Device extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 版本号 */
    @Excel(name = "版本号")
    private Long version;

    /** 创建人ID */
    @Excel(name = "创建人ID")
    private Long creatorId;

    /** 最后操作人ID */
    @Excel(name = "最后操作人ID")
    private Long lastOperatorId;

    /** 设备名称*/
    @Excel(name = "设备名称")
    private String deviceName;

    /** 设备介绍*/
    @Excel(name = "设备crd")
    private String deviceCrd;


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

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceCrd() {
        return deviceCrd;
    }

    public void setDeviceCrd(String deviceCrd) {
        this.deviceCrd = deviceCrd;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("version", getVersion())
                .append("createBy", getCreateBy())
                .append("creatorId", getCreatorId())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("lastOperatorId", getLastOperatorId())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("deviceName",getDeviceName())
                .append("deviceCrd", getDeviceCrd())
                .toString();
    }
}
