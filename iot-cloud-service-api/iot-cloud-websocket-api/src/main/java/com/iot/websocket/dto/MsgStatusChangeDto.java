package com.iot.websocket.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by rongshuai on 2020/3/12 12:19
 */
@Data
@ApiModel
public class MsgStatusChangeDto implements Serializable {
    private static final long serialVersionUID = -1614376274879530270L;

    @ApiModelProperty(value = "消息体ID")
    private Long id;

    @ApiModelProperty(value = "消息体状态（0未读，1已读）")
    private Integer status;
}
