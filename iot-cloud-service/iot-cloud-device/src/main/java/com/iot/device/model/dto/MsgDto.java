package com.iot.device.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by huqiaoqian on 2020/10/29
 */
@Data
public class MsgDto <T> implements Serializable {
    private static final long serialVersionUID = -457716870837363176L;
    /**
     * 消息Id
     */
    private Long messageId;
    /**
     * 消息的主题
     */
    private String topic;
    /**
     * 消息的标签
     */
    private String tag;
    /**
     * 消息的内容
     */
    private T content;
}
