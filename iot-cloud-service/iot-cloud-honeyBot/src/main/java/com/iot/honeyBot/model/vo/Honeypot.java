package com.iot.honeyBot.model.vo;

import com.iot.honeyBot.model.constants.ProtocolType;
import lombok.Data;

import java.io.Serializable;

@Data
public class Honeypot implements Serializable {
    private static final long serialVersionUID = 240359477067291607L;

    private String name;

    private String node;

    private ProtocolType protocol;

    private String status;

    private String port;

    private String description;
}
