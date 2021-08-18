package com.iot.honeyBot.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iot.honeyBot.model.constants.ProtocolType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class SearchPotDto implements Serializable {
    private static final long serialVersionUID = 8278731060380603550L;

    private ProtocolType protocol;

    private String node;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;
}
