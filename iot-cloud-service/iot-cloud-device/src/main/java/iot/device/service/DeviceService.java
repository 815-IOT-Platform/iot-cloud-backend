package iot.device.service;


import iot.device.model.dto.DeviceDto;
import iot.device.model.dto.EdgeDeviceDto;

import java.util.List;

/**
 * Created by huqiaoqian on 2020/10/14
 */
public interface DeviceService {
    public void createDevice(EdgeDeviceDto devicedto);

    public List<EdgeDeviceDto> getAllDevice();

}
