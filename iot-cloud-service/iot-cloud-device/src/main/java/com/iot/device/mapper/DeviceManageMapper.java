package com.iot.device.mapper;

import com.iot.device.model.domain.DeviceManage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface DeviceManageMapper {

    int saveDevice(DeviceManage deviceManage);

    List<DeviceManage> selectAll();

    DeviceManage selectByName(String deviceName);
}
