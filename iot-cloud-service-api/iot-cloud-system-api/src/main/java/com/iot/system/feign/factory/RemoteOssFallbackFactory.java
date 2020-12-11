package com.iot.system.feign.factory;



import com.iot.system.domain.SysOss;
import com.iot.system.dto.FileUploadDto;
import com.iot.system.feign.RemoteOssService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * Created by rongshuai on 2020/6/10 13:37
 */
@Slf4j
@Component
public class RemoteOssFallbackFactory implements FallbackFactory<RemoteOssService> {

    @Override
    public RemoteOssService create(Throwable throwable) {
        log.error(throwable.getMessage());
        return new RemoteOssService()
        {

            @Override
            public SysOss editSave(FileUploadDto fileUploadDto)
            {
                return null;
            }
        };
    }
}
