package com.iot.honeyBot.model.crd.device;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import io.fabric8.kubernetes.api.model.NodeSelector;
import lombok.Data;

/**
 * Created by huqiaoqian on 2020/10/15
 */
@Data
@JsonDeserialize(
        using = JsonDeserializer.None.class
)
public class DeviceSpec implements KubernetesResource {
    private static final long serialVersionUID = -4725638800926312492L;
    private DeviceModelRef deviceModelRef;

    private NodeSelector nodeSelector;
}
