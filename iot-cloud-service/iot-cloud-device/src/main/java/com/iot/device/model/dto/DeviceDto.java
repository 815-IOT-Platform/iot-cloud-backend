package com.iot.device.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by huqiaoqian on 2020/11/2
 */
@Data
public class DeviceDto implements Serializable {
    private static final long serialVersionUID = 7207636209857137856L;
    private String deviceName;
    private Map<String,String> properties;
}
