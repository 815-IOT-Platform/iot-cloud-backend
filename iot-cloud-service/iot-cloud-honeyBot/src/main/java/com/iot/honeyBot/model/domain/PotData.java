package com.iot.honeyBot.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class PotData implements Serializable {
    private static final long serialVersionUID = 3007804775727257816L;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS",timezone = "GMT+8")
    private Timestamp ts;

    private String value;
}
