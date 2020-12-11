package com.iot.common.core.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Created By ChengHao On 2020/5/20
 * 未用
 */
@Data
public class BaseEntityExtension implements Serializable {
    private static final long serialVersionUID = 6319102648731596788L;

    /**
     * 版本号
     */
    protected Long version;

    /** 创建人ID */
    protected Long creatorId;

    /** 最后操作人ID */
    protected Long lastOperatorId;

}
