package com.iot.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * The class User register dto.
 *
 * @author ananops.com@gmail.com
 */
@Data
@ApiModel(value = "用户注册Dto")
public class UserRegisterDto implements Serializable {

    private static final long serialVersionUID = -8019925037057525804L;
    /**
     * 登录名
     */
    @ApiModelProperty(value = "登录名")
    private String loginName;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String  phonenumber;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String   password;

    /**
     * 确认密码
     */
    @ApiModelProperty(value = "确认密码")
    private String confirmPwd;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    private Long companyId;

    private String companyName;

}