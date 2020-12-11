package com.iot.websocket.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * Created by rongshuai on 2020/6/8 15:03
 */
@Data
@ApiModel
public class WsMsgQueryDto {

    private Long userId;

    private String msgType;

    private Integer status;
}
