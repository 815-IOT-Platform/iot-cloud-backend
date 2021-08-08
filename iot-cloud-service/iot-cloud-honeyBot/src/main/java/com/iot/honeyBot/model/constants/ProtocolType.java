package com.iot.honeyBot.model.constants;

import java.util.HashMap;
import java.util.Map;

public enum ProtocolType {
    REDIS("redis"),
    HTTP("http"),
    HTTPS("https"),
    TELNET("telnet");

    private final String protocol;

    private static final Map<String, ProtocolType> MAP = new HashMap<>();

    static {

        for (ProtocolType protocol : values()) {
            MAP.put(protocol.protocol, protocol);
        }
    }


    public static ProtocolType valueOfName(String name) {

        return MAP.get(name);
    }


    ProtocolType(String protocol)
    {
        this.protocol = protocol;
    }

    public String getProtocol()
    {
        return protocol;
    }
}
