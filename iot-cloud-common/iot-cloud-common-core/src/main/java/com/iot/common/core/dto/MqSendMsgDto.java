package com.iot.common.core.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by rongshuai on 2020/2/25 12:21
 */
@Data
public class MqSendMsgDto<T> implements Serializable{
    private static final long serialVersionUID = 5719853631617757446L;

    /**
     * 需要推送的用户的Id
     */
    private Long userId;

    /**
     * 发送的消息体Dto
     */
    private T msgBodyDto;

}
