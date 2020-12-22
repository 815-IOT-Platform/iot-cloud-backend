package com.iot.device.feign.factory;


import com.iot.device.feign.RemoteDeviceService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by rongshuai on 2020/6/23 18:39
 */
@Slf4j
@Component
public class RemoteDeviceFullbackFactory implements FallbackFactory<RemoteDeviceService> {
    @Override
    public RemoteDeviceService create(Throwable throwable) {
        log.error(throwable.getMessage());
        return new RemoteDeviceService() {

        };
    }
}
