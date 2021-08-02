package com.iot.device.model.domain;

import com.iot.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

public class ModelProperty implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 设备ID*/
    @Excel(name = "模型ID")
    private Long modelId;


    /** 属性ID*/
    @Excel(name = "属性ID")
    private Long propertyId;

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
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
                .append("modelId", getModelId())
                .append("propertyId",getPropertyId())
                .toString();
    }
}
