package com.iot.device.model.domain;

import com.iot.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

public class DeviceModel implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 设备ID*/
    @Excel(name = "设备ID")
    private Long deviceId;

    /** 设备模型ID*/
    @Excel(name = "设备模型ID")
    private Long deviceModelId;

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getDeviceModelId() {
        return deviceModelId;
    }

    public void setDeviceModelId(Long deviceModelId) {
        this.deviceModelId = deviceModelId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("deviceId", getDeviceId())
                .append("deviceModelId",getDeviceModelId())
                .toString();
    }
}
