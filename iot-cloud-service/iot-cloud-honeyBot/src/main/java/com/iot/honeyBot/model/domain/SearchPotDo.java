package com.iot.honeyBot.model.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SearchPotDo implements Serializable {
    private static final long serialVersionUID = 7146155317439951172L;

    private String protocol;

    private String node;

    private Date startTime;
}
