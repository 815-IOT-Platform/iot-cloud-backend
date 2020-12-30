package com.iot.device.service;



import com.iot.common.core.dto.LoginAuthDto;
import com.iot.device.dto.EdgeDeviceDto;

import java.util.List;

/**
 * Created by huqiaoqian on 2020/10/14
 */
public interface DeviceService {
    public void createDevice(EdgeDeviceDto devicedto);

    public List<EdgeDeviceDto> getAllDevice();

    public EdgeDeviceDto getDevice(String deviceName);

    public void bindEdgeDevice(String deviceName, LoginAuthDto loginAuthDto);
}
