package com.iot.device.model.domain.device;

import io.fabric8.kubernetes.client.CustomResourceList;

/**
 * Created by huqiaoqian on 2020/10/15
 */
public class DeviceList extends CustomResourceList<EdgeDevice> {
    private static final long serialVersionUID = 5027043864434896762L;
}
