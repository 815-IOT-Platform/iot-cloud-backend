package com.iot.device.controller;

import com.iot.device.dto.DeviceManageDto;
import com.iot.device.dto.EdgeDeviceDto;
import com.iot.device.model.crd.device.DeviceTwin;
import com.iot.device.model.domain.DeviceManage;
import com.iot.device.service.DeviceManageService;
import com.iot.device.service.DeviceService;
import com.iot.device.util.JacksonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("deviceManage")
@Api(value = "iot-device-manage")
public class DeviceManageController {

    @Autowired
    private DeviceManageService deviceManageService;

    @Autowired
    private DeviceService deviceService;

    @GetMapping("getAllDeviceM")
    @ApiOperation("设备管理列表")
    public List<DeviceManage> getDeviceList() {
        return deviceManageService.getAllDeviceM();
    }

    @GetMapping("recreateDevice")
    @ApiOperation("重建Device")
    public void recreateDevice(String deviceName) throws IOException {
        EdgeDeviceDto deviceDto = new EdgeDeviceDto();
        DeviceManage deviceManage = deviceManageService.getDevice(deviceName);
        deviceDto.setDeviceName(deviceManage.getDeviceName());
        deviceDto.setDescription(deviceManage.getDescription());
        deviceDto.setDeviceModelRefName(deviceManage.getDeviceModelName());
        deviceDto.setNodeName(deviceManage.getNodeName());
        String twins = deviceManage.getDeviceTwins();
        List<DeviceTwin> list = (List<DeviceTwin>) JacksonUtil.parseJson(twins,DeviceTwin.class);
        // List<DeviceTwin> => List<DeviceTwinDto>
        deviceDto.setDeviceTwinDtoList(null);
        deviceService.createDevice(deviceDto);
    }

    @PostMapping("saveDeviceM")
    @ApiOperation("保存设备管理信息")
    public void saveDeviceM(DeviceManageDto deviceManageDto) {
        deviceManageService.createDeviceM(deviceManageDto);
    }
}
