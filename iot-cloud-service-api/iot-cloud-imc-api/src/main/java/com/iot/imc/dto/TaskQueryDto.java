package com.iot.imc.dto;

import com.iot.common.core.dto.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by rongshuai on 2019/12/22 12:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class TaskQueryDto extends BaseQuery {
    private static final long serialVersionUID = 8091137737261631366L;

    /**
     * 巡检任务对应的状态
     */
    @ApiModelProperty(value = "巡检任务对应的状态")
    private Integer status;

    /**
     * 巡检任务对应的用户id
     */
    @ApiModelProperty(value = "巡检任务对应的用户ID")
    private Long userId;

    /**
     * 用户id对应的用户角色
     */
    @ApiModelProperty(value = "当前操作的用户角色（1：甲方，2：服务商）")
    private Integer role;

    /**
     * 巡检任务对应的项目id
     */
    @ApiModelProperty(value = "巡检任务对应的项目ID")
    private Long projectId;

    /**
     * 巡检任务名称
     */
    @ApiModelProperty(value = "任务的名称（支持模糊查询）")
    private String taskName;


}
