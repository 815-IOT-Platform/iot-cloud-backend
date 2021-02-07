package com.iot.device.service;

import com.iot.common.core.dto.LoginAuthDto;
import com.iot.device.dto.BindEdgeDeviceDto;
import com.iot.device.dto.DeviceManageDto;
import com.iot.device.dto.EdgeDeviceDto;
import com.iot.device.model.domain.DeviceManage;

import java.util.List;

public interface DeviceManageService {

    int createDeviceM(DeviceManageDto deviceManageDto);

    List<DeviceManage> getAllDeviceM();

    DeviceManage getDevice(String deviceName);

}
