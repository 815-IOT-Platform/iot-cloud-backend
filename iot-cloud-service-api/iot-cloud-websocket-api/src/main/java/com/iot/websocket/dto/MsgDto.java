package com.iot.websocket.dto;

import com.iot.common.core.dto.LoginAuthDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by rongshuai on 2020/6/5 17:59
 */
@Data
@ApiModel
public class MsgDto<T> implements Serializable {
    private static final long serialVersionUID = -6993948448801479841L;

    String id;

    String msgType;

    T msg;

    LoginAuthDto user;
}
