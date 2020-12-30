package com.iot.device.controller;



import com.iot.common.core.controller.BaseController;
import com.iot.device.dto.BindEdgeDeviceDto;
import com.iot.device.dto.EdgeDeviceDto;
import com.iot.device.model.crd.device.EdgeDevice;
import com.iot.device.service.DeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by huqiaoqian on 2020/10/14
 */
@RestController
@RequestMapping("device")
@Api(value = "iot-cloud-device",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DeviceController extends BaseController {
    @Autowired
    private DeviceService deviceService;

    @PostMapping("addDevice")
    @ApiOperation("创建设备")
    public void addEdgeDevice(@RequestBody EdgeDeviceDto deviceDto) {
        deviceService.createDevice(deviceDto);
    }

    @GetMapping("getAllEdgeDevice")
    @ApiOperation( "获取全部设备")
    public List<EdgeDeviceDto> getAllDevice() {
        return deviceService.getAllDevice();
    }

    @GetMapping("getDevice/{deviceName}")
    @ApiOperation("获取指定设备")
    public EdgeDeviceDto getDevice(@PathVariable String deviceName) {
        return deviceService.getDevice(deviceName);
    }

    @PostMapping("bindEdgeDevice/")
    @ApiOperation("绑定设备影子")
    public void bindEdgeDevice(@RequestBody BindEdgeDeviceDto bindEdgeDeviceDto) {
        deviceService.bindEdgeDevice(bindEdgeDeviceDto, getLoginAuthDto());
    }



}
