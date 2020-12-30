package com.iot.device.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BindEdgeDeviceDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String deviceName;

    private String edgeDeviceName;
}
