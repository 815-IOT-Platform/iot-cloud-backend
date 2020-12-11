package com.iot.system.domain;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * Created By ChengHao On 2020/6/5
 */
@Data
@ApiModel(value = "用户注册Dto")
public class ActiveUserDto implements Serializable {

    private static final long serialVersionUID = 4990141896217248659L;

    private String activeUrl;

    private SysUser sysUser;
}
