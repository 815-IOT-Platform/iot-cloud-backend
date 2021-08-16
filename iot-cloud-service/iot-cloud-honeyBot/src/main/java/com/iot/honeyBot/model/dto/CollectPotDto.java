package com.iot.honeyBot.model.dto;

import com.iot.honeyBot.model.constants.ProtocolType;
import lombok.Data;

import java.io.Serializable;

@Data
public class CollectPotDto implements Serializable {
    private static final long serialVersionUID = -2606657812440831527L;

    private ProtocolType protocol;

    private String node;
}
