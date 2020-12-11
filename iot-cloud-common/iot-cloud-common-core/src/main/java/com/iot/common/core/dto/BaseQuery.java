package com.iot.common.core.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by rongshuai on 2020/5/25 20:57
 */
@Data
public class BaseQuery implements Serializable {

    private static final long serialVersionUID = 3319698607712846427L;

    /**
     * 当前页
     */
    private Integer pageNum = 1;

    /**
     * 每页条数
     */
    private Integer pageSize = 10;

    /**
     * 排序
     */
    private String orderByColumn;

    /** 排序的方向 "desc" 或者 "asc". */
    private String isAsc;
}