package com.iot.common.core.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by rongshuai on 2020/5/18 20:47
 */
@Data
public class RoleDto implements Serializable {
    private static final long serialVersionUID = -6811211457881086125L;

    private String dataScope;

    private Boolean flag;

    private Integer roleId;

    private String roleKey;

    private String roleName;

    private String  roleSort;

    private String status;

}
