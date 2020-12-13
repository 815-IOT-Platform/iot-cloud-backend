package iot.device.model.domain.modelType;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by huqiaoqian on 2020/10/15
 */
@Data
public class StringType implements Serializable {
    private static final long serialVersionUID = 8307281252317597164L;
    private String accessMode;

    private String defaultValue;
}
