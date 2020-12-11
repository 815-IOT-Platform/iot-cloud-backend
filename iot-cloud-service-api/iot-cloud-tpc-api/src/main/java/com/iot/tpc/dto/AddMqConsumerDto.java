package com.iot.tpc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by rongshuai on 2020/2/20 14:44
 */
@Data
@ApiModel
public class AddMqConsumerDto implements Serializable {
    private static final long serialVersionUID = -1937849395753686345L;
    /**
     * 微服务名称
     */
    @ApiModelProperty(value = "微服务名称")
    private String applicationName;

    /**
     * 消费者编码
     */
    @ApiModelProperty(value = "消费者编码")
    private String consumerCode;

    /**
     * 消费者名字
     */
    @ApiModelProperty(value = "消费者名字")
    private String consumerName;

    /**
     * 状态, 0生效,10,失效
     */
    @ApiModelProperty(value = "状态, 0生效,10,失效")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
