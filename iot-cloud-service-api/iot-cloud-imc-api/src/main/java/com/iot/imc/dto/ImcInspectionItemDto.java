package com.iot.imc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by rongshuai on 2020/4/4 16:26
 */
@Data
@ApiModel
public class ImcInspectionItemDto implements Serializable {
    private static final long serialVersionUID = -7869606799514874716L;

    private String createBy;

    private String updateBy;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 巡检任务子项Id
     */
    private Long id;
    /**
     * 从属的巡检任务的ID
     */
    private Long inspectionTaskId;

    /**
     * 计划开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date scheduledStartTime;

    /**
     * 实际开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date actualStartTime;

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
     * 巡检周期（月）
     */
    private Integer frequency;

    /**
     * 巡检子项内容描述
     */
    private String description;

    /**
     * 巡检子项的巡检状态
     */
    private Integer status;

    /**
     * 巡检子项的位置，纬度
     */
    private BigDecimal itemLatitude;

    /**
     * 巡检子项的位置，经度
     */
    private BigDecimal itemLongitude;

    /**
     * 巡检子项结果描述
     */
    private String result;

    /**
     * 巡检子项的名称
     */
    private String itemName;

    /**
     * 巡检子项对应的维修工ID
     */
    private Long maintainerId;

    /**
     * 巡检子项对应的维修工
     */
    private String maintainerName;

    /**
     * 巡检任务子项已经执行的次数
     */
    private Integer count;

    /**
     * 巡检任务子项的网点地址
     */
    private String location;
}
