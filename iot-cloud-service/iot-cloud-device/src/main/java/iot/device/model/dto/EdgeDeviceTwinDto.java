package iot.device.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by huqiaoqian on 2020/10/16
 */
@Data
public class EdgeDeviceTwinDto implements Serializable {
    private static final long serialVersionUID = 8999771986691881674L;
    private String propertyName;

    private String requireType;

    private String requireValue;

    private String reportedType;

    private String reportedValue;

    private String reportedTime;
}
