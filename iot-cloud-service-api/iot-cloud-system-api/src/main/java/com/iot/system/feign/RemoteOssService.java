package com.iot.system.feign;

import com.iot.common.constant.ServiceNameConstants;
import com.iot.system.domain.SysOss;
import com.iot.system.dto.FileUploadDto;
import com.iot.system.feign.factory.RemoteOssFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * Created by rongshuai on 2020/6/10 13:36
 */
@FeignClient(name = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteOssFallbackFactory.class)
public interface RemoteOssService {

    @PostMapping("oss/remoteUpload")
    public SysOss editSave(@RequestBody FileUploadDto fileUploadDto);

}
