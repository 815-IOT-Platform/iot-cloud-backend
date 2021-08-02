package com.iot.device.service;



import com.iot.common.core.dto.LoginAuthDto;
import com.iot.device.dto.BindEdgeDeviceDto;
import com.iot.device.dto.EdgeDeviceDto;

import java.util.List;

/**
 * Created by huqiaoqian on 2020/10/14
 */
public interface DeviceService {
    public void createDevice(EdgeDeviceDto devicedto);

    public List<EdgeDeviceDto> getAllDevice();

    public EdgeDeviceDto getDevice(String deviceName);

    public void bindEdgeDevice(BindEdgeDeviceDto bindEdgeDeviceDto, LoginAuthDto loginAuthDto);

    public EdgeDeviceDto getEdgeDeviceFromBind(Long realDeviceId);

    public void reCreateEdgeDeviceFromBind(Long realDeviceId);
}
