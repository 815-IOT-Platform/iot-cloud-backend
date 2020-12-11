package com.iot.imc.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by rongshuai on 2020/6/5 20:58
 */
@Data
@ApiModel
public class TaskWsDto implements Serializable {
    private static final long serialVersionUID = -2158774713441834233L;

    private Long taskId;

    private Integer status;

    private String statusMsg;
}
