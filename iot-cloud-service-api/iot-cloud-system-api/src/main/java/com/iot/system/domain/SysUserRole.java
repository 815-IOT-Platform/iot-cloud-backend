package com.iot.system.domain;

import lombok.Data;

/**
 * 用户和角色关联 sys_user_role
 * 
 * @author ananops
 */
@Data
public class SysUserRole
{
    /** 用户ID */
    private Long userId;
    
    /** 角色ID */
    private Long roleId;

    private Long companyId;
}
