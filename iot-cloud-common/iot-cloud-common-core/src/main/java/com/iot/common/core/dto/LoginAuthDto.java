package com.iot.common.core.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by rongshuai on 2020/5/18 19:33
 */
@Data
public class LoginAuthDto implements Serializable {
    private static final long serialVersionUID = 474525199059258954L;

    private Boolean admin;

    private String avatar;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String delFlag;

    private DeptDto dept;

    private Long deptId;

    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loginDate;

    private String loginIp;

    private String loginName;

    private String phoneNumber;

    private String remark;

    private List<Integer> roleIds;

    private List<RoleDto> roles;

    private String salt;

    private String sex;

    private String status;

    private Long userId;

    private String userName;

    private Long companyId;

    private String companyName;

}
