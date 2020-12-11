package com.iot.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created By ChengHao On 2020/6/5
 */
@Data
@ApiModel(value = "服务商注册Dto")
public class CompanyRegisterDto implements Serializable {

    private static final long serialVersionUID = -3468880006589264396L;

    private Long companyId;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "登录名", required = true)
    private String companyName;

    /**
     * 统一社会信任代码
     */
    @ApiModelProperty(value = "统一社会信任代码", required = true)
    private String companyCode;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    /**
     * 确认密码
     */
    @ApiModelProperty(value = "确认密码", required = true)
    private String confirmPwd;

//    /**
//     * 联系人姓名
//     */
//    @ApiModelProperty(value = "联系人姓名", required = true)
//    private String contact;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String  phonenumber;

    /**
     * 联系人邮箱
     */
    @ApiModelProperty(value = "联系人邮箱", required = true)
    private String email;

    /**
     * 手机短信验证码
     */
    @ApiModelProperty(value = "手机短信验证码", required = true)
    private String phoneSmsCode;

//    /**
//     * 注册token
//     */
//    @ApiModelProperty(value = "注册渠道，必填填入PC", required = true)
//    private String registerSource;
}

