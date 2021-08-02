package com.iot.device.model.crd.deviceModel;



import com.iot.device.model.crd.modelType.ModelType;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by huqiaoqian on 2020/10/15
 */
@Data
public class DeviceModelProperty implements Serializable {
    private static final long serialVersionUID = -4004587050089757023L;

    private String name;

    private String description;

    private ModelType type;
}
