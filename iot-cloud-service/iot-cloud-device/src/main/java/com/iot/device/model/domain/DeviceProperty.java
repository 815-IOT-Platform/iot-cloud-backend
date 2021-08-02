package com.iot.device.model.domain;

import com.iot.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

public class DeviceProperty implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 设备ID*/
    @Excel(name = "设备ID")
    private Long deviceId;


    /** 属性ID*/
    @Excel(name = "属性ID")
    private Long propertyId;

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("deviceId", getDeviceId())
                .append("propertyId",getPropertyId())
                .toString();
    }
}
