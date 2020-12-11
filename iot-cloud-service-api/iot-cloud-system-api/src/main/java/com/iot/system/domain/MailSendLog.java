package com.iot.system.domain;

import lombok.Data;

import java.util.Date;

/**
 * Created By ChengHao On 2020/6/3
 */
@Data
public class MailSendLog {
    private String msgId;
    /**
     * 员工id
     */
    private Long empId;
    /**
     * 0 消息投递中   1 投递成功   2投递失败
     */
    private Integer status;
    private String routeKey;
    private String exchange;
    /**
     * 重试次数
     */
    private Integer count;
    /**
     * 第一次重试时间
     */
    private Date tryTime;
    private Date createTime;
    private Date updateTime;
}