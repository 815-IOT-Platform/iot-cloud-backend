package com.iot.tpc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by rongshuai on 2020/2/20 15:57
 */
@Data
@ApiModel
public class AddTopicDto implements Serializable {
    private static final long serialVersionUID = -2196948134829850038L;
    /**
     * 主题编码
     */
    @ApiModelProperty(value = "主题编码")
    private String topicCode;

    /**
     * 主题名称
     */
    @ApiModelProperty(value = "主题名称")
    private String topicName;

    /**
     * MQ类型, 10 rocketmq 20 kafka
     */
    @ApiModelProperty(value = "MQ类型, 10 rocketmq 20 kafka")
    private Integer mqType;

    /**
     * 消息类型, 10 无序消息, 20 无序消息
     */
    @ApiModelProperty(value = "消息类型, 10 无序消息, 20 无序消息")
    private Integer msgType;

    /**
     * 状态, 0生效,10,失效
     */
    @ApiModelProperty(value = "状态, 0生效,10,失效")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remarks;
}
