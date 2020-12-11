package com.iot.common.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by rongshuai on 2020/6/4 11:37
 */
@Data
@AllArgsConstructor
public class RegisterDto {

    private String app;

    private String host;
}
