package com.iot.imc.dto;


import com.iot.common.core.dto.LoginAuthDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by rongshuai on 2019/12/5 10:27
 */
@Data
@ApiModel
public class ImcItemChangeStatusDto implements Serializable {
    private static final long serialVersionUID = -3707411305206374359L;

    /**
     * 巡检任务子项的ID
     */
    @ApiModelProperty(value = "巡检任务子项ID")
    private Long itemId;

    /**
     * 巡检任务子项修改后的状态
     */
    @ApiModelProperty(value = "巡检任务子项修改后的状态")
    private Integer status;

    /**
     * 巡检任务子项修改后的状态描述
     */
    @ApiModelProperty(value = "巡检任务子项修改后的状态描述")
    private String statusMsg;

    /**
     * 当前操作用户的身份信息
     */
    @ApiModelProperty(value = "当前操作用户的LoginAuthDto")
    private LoginAuthDto loginAuthDto;

    /**
     * 附件id
     */
    @ApiModelProperty(value = "巡检任务子项对应的附件id")
    private List<Long> attachmentIds;
}
