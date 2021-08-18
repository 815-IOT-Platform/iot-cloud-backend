package com.iot.honeyBot.model.constants;

import java.util.HashMap;
import java.util.Map;

public enum NodeStatus {
    RUNNING("running"),
    CRASHED("crashed");

    private final String status;

    private static final Map<String, NodeStatus> MAP = new HashMap<>();

    static {

        for (NodeStatus status : values()) {
            MAP.put(status.status,  status);
        }
    }


    public static NodeStatus valueOfName(String name) {

        return MAP.get(name);
    }


    NodeStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
}
