package com.iot.device.model.crd.device;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by huqiaoqian on 2020/10/15
 */
@Data
public class DeviceDesired implements Serializable {
    private static final long serialVersionUID = -939504641068018609L;

    private DeviceDesiredMetadata metadata;

    private String value;
}
