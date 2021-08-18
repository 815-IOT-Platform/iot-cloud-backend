package com.iot.honeyBot.model.vo;

import com.iot.honeyBot.model.constants.NodeStatus;
import io.fabric8.kubernetes.api.model.NodeAddress;
import io.fabric8.kubernetes.api.model.NodeSystemInfo;
import io.fabric8.kubernetes.api.model.Quantity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class EdgeNodeVo implements Serializable {
    private static final long serialVersionUID = 3931306146217509746L;

    private String name;

    private List<NodeAddress> addresses;

    private Map<String, Quantity> allocatable;

    Map<String, Quantity> capacity;

    private NodeSystemInfo nodeInfo;

    private NodeStatus status;

    private int potNumber;
}
