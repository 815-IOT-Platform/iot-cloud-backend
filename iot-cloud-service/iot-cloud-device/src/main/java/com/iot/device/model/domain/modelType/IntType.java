package com.iot.device.model.domain.modelType;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by huqiaoqian on 2020/10/15
 */
@Data
public class IntType implements Serializable {

    private static final long serialVersionUID = 6281387268651830810L;
    private String accessMode;

    private int defaultValue;
}
