package com.iot.device.model.crd.modelType;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by huqiaoqian on 2020/10/15
 */
@Data
public class DoubleType implements Serializable {
    private static final long serialVersionUID = -3632366576217604530L;
    private String accessMode;

    private double defaultValue;
}
