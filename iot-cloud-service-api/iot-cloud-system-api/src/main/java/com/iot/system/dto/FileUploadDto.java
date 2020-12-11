package com.iot.system.dto;

import com.iot.common.core.dto.LoginAuthDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by rongshuai on 2020/6/10 14:31
 */
@Data
@ApiModel(value = "文件上传dto")
public class FileUploadDto implements Serializable {
    private static final long serialVersionUID = 6417889162867867962L;

    private byte[] data;

    private String fileName;

    private LoginAuthDto user;
}
