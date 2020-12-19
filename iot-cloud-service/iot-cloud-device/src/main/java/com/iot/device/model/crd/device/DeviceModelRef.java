package com.iot.device.model.crd.device;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by huqiaoqian on 2020/10/15
 */
@Data
public class DeviceModelRef implements Serializable {
    private static final long serialVersionUID = -4852685708966438944L;
    private String name;
}
