package iot.device.model.domain.device;

import io.fabric8.kubernetes.api.builder.Function;
import io.fabric8.kubernetes.client.CustomResourceDoneable;

/**
 * Created by huqiaoqian on 2020/10/15
 */
public class DoneableDevice extends CustomResourceDoneable<EdgeDevice> {
    public DoneableDevice(EdgeDevice resource, Function function) {
        super(resource, function);
    }
}
