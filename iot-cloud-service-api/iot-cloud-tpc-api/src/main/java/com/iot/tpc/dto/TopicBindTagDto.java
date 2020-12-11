package com.iot.tpc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by rongshuai on 2020/2/20 15:58
 */
@Data
@ApiModel
public class TopicBindTagDto implements Serializable {
    private static final long serialVersionUID = 1688941708832437643L;
    /**
     * 主题ID
     */
    @ApiModelProperty(value = "主题ID")
    private Long topicId;

    /**
     * 标签编码
     */
    @ApiModelProperty(value = "标签编码")
    private String tagCode;

    /**
     * 标签名称
     */
    @ApiModelProperty(value = "标签名称")
    private String tagName;

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
