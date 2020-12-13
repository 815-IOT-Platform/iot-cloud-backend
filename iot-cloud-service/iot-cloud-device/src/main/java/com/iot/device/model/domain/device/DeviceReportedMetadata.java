package com.iot.device.model.domain.device;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by huqiaoqian on 2020/10/15
 */
@Data
public class DeviceReportedMetadata implements Serializable {
    private static final long serialVersionUID = -9183065689126256417L;
    private String timestamp;

    private String type;
}
