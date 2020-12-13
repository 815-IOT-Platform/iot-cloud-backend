package iot.device.service;


import iot.device.model.dto.EdgeDeviceModelDto;

import java.util.List;

/**
 * Created by huqiaoqian on 2020/10/16
 */
public interface DeviceModelService {
    public void createDeviceModel(EdgeDeviceModelDto deviceModelDto);

    public List<EdgeDeviceModelDto> getAllDeviceModel();
}
