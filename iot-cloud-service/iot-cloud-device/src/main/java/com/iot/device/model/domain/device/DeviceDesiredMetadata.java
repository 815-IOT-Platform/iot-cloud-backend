package com.iot.device.model.domain.device;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by huqiaoqian on 2020/10/15
 */
@Data
public class DeviceDesiredMetadata implements Serializable {
    private static final long serialVersionUID = -3742190290854105693L;

    private String type;
}
