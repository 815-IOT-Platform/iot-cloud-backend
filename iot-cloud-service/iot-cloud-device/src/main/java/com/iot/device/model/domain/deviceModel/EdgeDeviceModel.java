package com.iot.device.model.domain.deviceModel;

import io.fabric8.kubernetes.api.model.Namespaced;
import io.fabric8.kubernetes.client.CustomResource;
import lombok.Data;

/**
 * Created by huqiaoqian on 2020/10/15
 */
@Data
public class EdgeDeviceModel extends CustomResource implements Namespaced {
    private static final long serialVersionUID = -5706719349058198312L;

    private DeviceModelSpec spec;
}
