package com.iot.device.model.domain.deviceModel;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import lombok.Data;

import java.util.List;

/**
 * Created by huqiaoqian on 2020/10/15
 */
@Data
@JsonDeserialize(
        using = JsonDeserializer.None.class
)
public class DeviceModelSpec implements KubernetesResource {
    private static final long serialVersionUID = 8390893355606949277L;
    private List<DeviceModelProperty> properties;
}
