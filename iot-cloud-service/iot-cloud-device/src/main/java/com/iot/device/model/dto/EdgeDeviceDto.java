package com.iot.device.model.dto;

import com.iot.device.model.dto.EdgeDeviceTwinDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by huqiaoqian on 2020/10/15
 */
@Data
@ApiModel
public class EdgeDeviceDto implements Serializable {

    private static final long serialVersionUID = 2619278405554102852L;
    @ApiModelProperty("设备名称")
    private String deviceName;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("设备模型")
    private String model;

    @ApiModelProperty("设备模型")
    private String deviceModelRefName;

    @ApiModelProperty("节点名称")
    private String nodeName;

    @ApiModelProperty("设备属性列表")
    private List<EdgeDeviceTwinDto> deviceTwinDtoList;
}
