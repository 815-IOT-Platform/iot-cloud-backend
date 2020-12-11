package com.iot.imc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by rongshuai on 2020/5/28 19:31
 */
@Data
@ApiModel
public class TaskLogDto implements Serializable {
    private static final long serialVersionUID = -7010041632707056301L;

    private Long id;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建人ID
     */
    private Long creatorId;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 最近操作人
     */
    private String updateBy;

    /**
     * 最后操作人ID
     */
    private Long lastOperatorId;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 巡检任务的名字
     */
    private String taskName;
    /**
     * 巡检任务的操作
     */
    private String movement;
    /**
     * 操作对应的时间戳
     */
    private Date statusTimestamp;
    /**
     * 操作系统
     */
    private String os;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * ip地址
     */
    private String ipAddress;
    /**
     * 当前任务对应的状态
     */
    private int status;
    /**
     * 当前任务对应的状态名
     */
    private String statusMsg;
}
