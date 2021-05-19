package com.iot.device.model.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Node implements Serializable {
    private static final long serialVersionUID = -8336160996766647554L;

    private String nodeName;

    private String nodeId;
}
