package com.iot.device.model.crd.deviceModel;

import io.fabric8.kubernetes.api.builder.Function;
import io.fabric8.kubernetes.client.CustomResourceDoneable;

/**
 * Created by huqiaoqian on 2020/10/15
 */
public class DoneableDeviceModel extends CustomResourceDoneable<EdgeDeviceModel> {
    public DoneableDeviceModel(EdgeDeviceModel resource, Function function) {
        super(resource, function);
    }
}
