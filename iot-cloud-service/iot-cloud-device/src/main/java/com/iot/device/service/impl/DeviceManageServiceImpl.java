package com.iot.device.service.impl;

import com.iot.common.utils.bean.BeanUtils;
import com.iot.device.dto.DeviceManageDto;
import com.iot.device.mapper.DeviceManageMapper;
import com.iot.device.model.domain.DeviceManage;
import com.iot.device.service.DeviceManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class DeviceManageServiceImpl implements DeviceManageService {

    @Resource
    private DeviceManageMapper deviceManageMapper;

    @Override
    public int createDeviceM(DeviceManageDto deviceManageDto) {
        DeviceManage deviceManage = new DeviceManage();
        BeanUtils.copyProperties(deviceManageDto,deviceManage);
        return deviceManageMapper.saveDevice(deviceManage);
    }

    @Override
    public List<DeviceManage> getAllDeviceM() {
        return deviceManageMapper.selectAll();
    }

    @Override
    public DeviceManage getDevice(String deviceName) {
        return deviceManageMapper.selectByName(deviceName);
    }
}
