package com.iot.imc.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by rongshuai on 2020/6/5 20:58
 */
@Data
@ApiModel
public class ItemWsDto implements Serializable {
    private static final long serialVersionUID = 7119255629201092354L;

    private Long itemId;

    private Integer status;

    private String statusMsg;
}
