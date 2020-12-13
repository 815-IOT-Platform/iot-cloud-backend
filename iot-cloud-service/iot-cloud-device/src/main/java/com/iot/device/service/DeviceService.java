package com.iot.device.service;


import com.iot.device.model.dto.EdgeDeviceDto;

import java.util.List;

/**
 * Created by huqiaoqian on 2020/10/14
 */
public interface DeviceService {
    public void createDevice(EdgeDeviceDto devicedto);

    public List<EdgeDeviceDto> getAllDevice();

}
