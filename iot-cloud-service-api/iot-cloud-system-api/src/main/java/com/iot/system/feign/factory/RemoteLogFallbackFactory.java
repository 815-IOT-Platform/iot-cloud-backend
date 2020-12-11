package com.iot.system.feign.factory;

import org.springframework.stereotype.Component;

import com.iot.system.domain.SysLogininfor;
import com.iot.system.domain.SysOperLog;
import com.iot.system.feign.RemoteLogService;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RemoteLogFallbackFactory implements FallbackFactory<RemoteLogService>
{
    @Override
    public RemoteLogService create(Throwable throwable)
    {
        log.error(throwable.getMessage());
        return new RemoteLogService()
        {
            @Override
            public void insertOperlog(SysOperLog operLog)
            {
            }

            @Override
            public void insertLoginlog(SysLogininfor logininfor)
            {
            }
        };
    }
}
