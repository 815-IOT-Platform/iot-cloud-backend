package com.iot.common.core.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by rongshuai on 2020/5/18 20:22
 */
@Data
public class DeptDto implements Serializable {
    private static final long serialVersionUID = -1476225202381813227L;

    private Long deptId;

    private String deptName;

    private String orderNum;

    private Long parentId;

    private String status;

}
