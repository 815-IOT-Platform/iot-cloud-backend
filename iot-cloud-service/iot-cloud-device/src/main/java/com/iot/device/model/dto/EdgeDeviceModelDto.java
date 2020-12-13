package com.iot.device.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by huqiaoqian on 2020/10/16
 */
@Data
@ApiModel
public class EdgeDeviceModelDto implements Serializable {
    private static final long serialVersionUID = 3389016370326455681L;
    @ApiModelProperty("设备模型名称")
    private String name;
    @ApiModelProperty("设备模型属性")
    private List<DeviceModelPropertyDto> propertyDtos;
    @ApiModelProperty("描述")
    private String description;
}
