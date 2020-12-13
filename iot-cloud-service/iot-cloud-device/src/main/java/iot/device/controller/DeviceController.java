package iot.device.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import iot.device.model.dto.EdgeDeviceDto;
import iot.device.service.DeviceService;
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
public class DeviceController {
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

}
