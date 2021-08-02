package com.iot.device.model.crd.device;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by huqiaoqian on 2020/10/15
 */
@Data
public class DeviceReported implements Serializable {
    private static final long serialVersionUID = 5528122236824350129L;
    private DeviceReportedMetadata metadata;

    private String value;
}
