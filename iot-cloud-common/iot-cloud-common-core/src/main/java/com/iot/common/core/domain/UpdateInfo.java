package com.iot.common.core.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by rongshuai on 2020/5/19 19:20
 */
@Data
public class UpdateInfo implements Serializable {
    private static final long serialVersionUID = 4597850772972291769L;

    /**唯一ID*/
    private Long id;

    /** 创建人ID */
    private Long creatorId;

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 最后操作人ID */
    private Long lastOperatorId;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
