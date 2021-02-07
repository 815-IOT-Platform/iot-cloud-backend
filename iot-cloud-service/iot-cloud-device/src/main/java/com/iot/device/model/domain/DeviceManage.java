package com.iot.device.model.domain;

import com.iot.common.annotation.Excel;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class DeviceManage {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 版本号 */
    @Excel(name = "版本号")
    private Long version;

    /** 设备ID */
    @Excel(name = "设备ID")
    private Long deviceId;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String deviceName;

    /** 边缘设备名称 */
    @Excel(name = "边缘设备名称")
    private String edgeDeviceName;

    /** 设备描述 */
    @Excel(name = "设备描述")
    private String description;

    /** 设备模型 */
    @Excel(name = "设备模型")
    private String deviceModelName;

    /** 设备状态 */
    @Excel(name = "设备状态")
    private String state;

    /** 设备属性 */
    @Excel(name = "设备属性列表")
    private String deviceTwins;

    /** 设备所在节点 */
    @Excel(name = "设备所在节点")
    private String nodeName;

}
