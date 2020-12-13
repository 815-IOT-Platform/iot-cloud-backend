package iot.device.model.domain.device;

import io.fabric8.kubernetes.api.model.Namespaced;
import io.fabric8.kubernetes.client.CustomResource;
import lombok.Data;

/**
 * Created by huqiaoqian on 2020/10/14
 */
@Data
public class EdgeDevice extends CustomResource implements Namespaced {
    private static final long serialVersionUID = -3636934385695603590L;
    private DeviceSpec spec;

    private DeviceStatus status;
}
