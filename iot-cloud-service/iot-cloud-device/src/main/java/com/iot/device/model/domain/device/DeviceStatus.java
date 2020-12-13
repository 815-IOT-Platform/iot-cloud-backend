package com.iot.device.model.domain.device;

import io.fabric8.kubernetes.api.model.apiextensions.CustomResourceDefinitionStatus;
import lombok.Data;

import java.util.List;

/**
 * Created by huqiaoqian on 2020/10/15
 */
@Data
public class DeviceStatus extends CustomResourceDefinitionStatus {
    private static final long serialVersionUID = -5561533798622769660L;
    private List<DeviceTwin> twins;
}
