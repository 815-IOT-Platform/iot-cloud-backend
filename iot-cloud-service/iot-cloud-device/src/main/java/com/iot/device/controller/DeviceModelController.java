package com.iot.device.controller;


import com.iot.device.dto.EdgeDeviceModelDto;
import com.iot.device.service.DeviceModelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by huqiaoqian on 2020/10/16
 */
@RestController
@RequestMapping("model")
@Api(value = "k8s-deviceModel",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DeviceModelController {
    @Autowired
    private DeviceModelService deviceModelService;

    @PostMapping("addDeviceModel")
    @ApiOperation("创建设备模型")
    public void addDeviceModel(@RequestBody EdgeDeviceModelDto deviceModelDto){
        deviceModelService.createDeviceModel(deviceModelDto);
    }

    @GetMapping("getAllEdgeDeviceModel")
    @ApiOperation("获取全部设备模型")
    public List<EdgeDeviceModelDto> getAllEdgeDeviceModel() {
        return deviceModelService.getAllDeviceModel();
    }
}
