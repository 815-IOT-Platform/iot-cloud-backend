package com.iot.imc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Bingyue Duan
 * @version 1.0
 * @date 2020-04-03 23:16
 */
@Data
@ApiModel
public class ImcInspectionTaskDto {

    private String createBy;

    private String updateBy;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    private Long id;

    /**
     * 项目负责人ID
     */
    private Long principalId;

    /**
     * 项目负责人
     */
    private String principalName;

    /**
     * 服务商ID
     */
    private Long facilitatorId;

    /**
     * 服务商名称
     */
    private String facilitatorName;

    /**
     * 项目ID
     */
    private Long projectId;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 巡检位置信息
     */
    private String location;

    /**
     * 当前巡检任务进度
     */
    private Integer status;

    /**
     * 本次巡检总花费
     */
    private BigDecimal totalCost;

    /**
     * 巡检产生的维修维护费用
     */
    private BigDecimal maintenanceCost;

    /**
     * 计划起始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date scheduledStartTime;

    /**
     * 实际完成时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date actualFinishTime;

    /**
     * 计划完成天数
     */
    private Integer days;

    /**
     * 巡检类型（1.按合同产生的巡检，2.甲方负责人主动发出的巡检）
     */
    private Integer inspectionType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 巡检任务名称
     */
    private String taskName;

    /**
     * 巡检周期（月）
     */
    private Integer frequency;

    /**
     * 巡检任务内容
     */
    private String content;

    /**
     * 本次巡检任务所需巡检点位总数
     */
    private Integer pointSum;

    /**
     * 已安排点位数
     */
    private Integer alreadyPoint;
}
