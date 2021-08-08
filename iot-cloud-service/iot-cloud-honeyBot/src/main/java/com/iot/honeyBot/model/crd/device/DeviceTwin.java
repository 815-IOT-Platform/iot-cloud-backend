package com.iot.honeyBot.model.crd.device;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by huqiaoqian on 2020/10/15
 */
@Data
public class DeviceTwin implements Serializable {
    private static final long serialVersionUID = 4422726375225209964L;

    private String propertyName;

    private DeviceDesired desired;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private DeviceReported reported;
}
