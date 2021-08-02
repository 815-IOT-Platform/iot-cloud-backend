package com.iot.device.model.mq;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by rongshuai on 2020/12/22
 */
@Data
public class MqSendMsgDto<T> implements Serializable {
    private static final long serialVersionUID = 2535043478418791024L;
    /**
     * 需要推送的用户的Id
     */
    private Long userId;

    /**
     * 发送的消息体Dto
     */
    private T msgBodyDto;
}
