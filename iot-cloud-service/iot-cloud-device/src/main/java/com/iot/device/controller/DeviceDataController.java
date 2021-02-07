package com.iot.device.controller;

import com.alibaba.fastjson.JSON;
import com.iot.common.redis.util.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("deviceData")
@Api(value = "iot-cloud-device-data")
public class DeviceDataController {

    @Autowired
    private RedisUtils redisUtils;

    @GetMapping("getDataByName")
    @ApiOperation("获取设备当前数据")
    public Map<String,Object> getDeviceDataByName(String deviceName) {
        return JSON.parseObject(redisUtils.get(deviceName));
    }

    @GetMapping("getAllData")
    @ApiOperation("获取所有设备当前数据")
    public List<Map<String, Object>> getAllDeviceData() {
        // 模糊查询 keys ~ device_data_******
        return null;
    }
}
