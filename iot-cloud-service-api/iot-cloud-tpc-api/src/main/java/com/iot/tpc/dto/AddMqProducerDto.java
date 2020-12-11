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
public class AddMqProducerDto implements Serializable {
    private static final long serialVersionUID = 6143023106205441649L;
    /**
     * 微服务名称
     */
    @ApiModelProperty(value = "微服务名称")
    private String applicationName;

    /**
     * PID 生产者组编码
     */
    @ApiModelProperty(value = "PID 生产者组编码")
    private String producerCode;

    /**
     * PID 生产者组名称
     */
    @ApiModelProperty(value = "PID 生产者组名称")
    private String producerName;

    @ApiModelProperty(value = "queryMessageUrl")
    private String queryMessageUrl;

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
