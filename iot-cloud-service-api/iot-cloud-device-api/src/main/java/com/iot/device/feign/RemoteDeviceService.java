package com.iot.device.feign;


import com.iot.device.feign.factory.RemoteDeviceFullbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import com.iot.common.constant.ServiceNameConstants;

/**
 * Created by rongshuai on 2020/6/23 18:38
 */
@FeignClient(name = ServiceNameConstants.DEVICE_SERVICE, fallbackFactory = RemoteDeviceFullbackFactory.class)
public interface RemoteDeviceService {

}
