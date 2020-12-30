package com.iot.device.service.impl;


import com.iot.common.core.dto.LoginAuthDto;
import com.iot.common.exception.BusinessException;
import com.iot.common.utils.bean.UpdateInfoUtil;
import com.iot.device.dto.BindEdgeDeviceDto;
import com.iot.device.dto.EdgeDeviceDto;
import com.iot.device.dto.EdgeDeviceTwinDto;
import com.iot.device.mapper.DeviceMapper;
import com.iot.device.model.crd.device.*;
import com.iot.device.model.domain.Device;
import com.iot.device.service.DeviceService;
import com.iot.device.util.JacksonUtil;
import io.fabric8.kubernetes.api.model.NodeSelector;
import io.fabric8.kubernetes.api.model.NodeSelectorRequirement;
import io.fabric8.kubernetes.api.model.NodeSelectorTerm;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.client.CustomResourceList;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huqiaoqian on 2020/10/14
 */
@Slf4j
@Service
public class DeviceServiceImpl implements DeviceService {


    @Autowired
    private NonNamespaceOperation<EdgeDevice, DeviceList, DoneableDevice, Resource<EdgeDevice, DoneableDevice>> deviceClient;

    @Autowired
    private KubernetesClient k8sClient;

    @Autowired
    private DeviceMapper deviceMapper;



    @Override
    public void createDevice(EdgeDeviceDto deviceDto) {
        try {
            deviceClient.createOrReplace(formatEdgeDevice(deviceDto));
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("设备创建失败");
        }
    }

    @Override
    public List<EdgeDeviceDto> getAllDevice() {
        CustomResourceList<EdgeDevice> deviceList = deviceClient.list();
        List<EdgeDevice> devices=deviceList.getItems();
        List<EdgeDeviceDto> deviceDtos=new ArrayList<>();
        for(EdgeDevice edgeDevice:devices){
            log.info("deviceName is {}",edgeDevice.getMetadata().getName());
            deviceDtos.add(formatEdgeDeviceDto(edgeDevice));
        }
        return deviceDtos;
    }

    @Override
    public EdgeDeviceDto getDevice(String deviceName) {
        EdgeDevice edgeDevice = deviceClient.withName(deviceName).get();
        log.info("edgeDevice is {}", edgeDevice);
        return formatEdgeDeviceDto(edgeDevice);
    }

    @Override
    public void bindEdgeDevice(BindEdgeDeviceDto bindEdgeDeviceDto, LoginAuthDto loginAuthDto) {
        EdgeDevice edgeDevice = deviceClient.withName(bindEdgeDeviceDto.getEdgeDeviceName()).get();
        log.info("edgeDevice is {}", edgeDevice);
        EdgeDeviceDto edgeDeviceDto = formatEdgeDeviceDto(edgeDevice);
        Device realDevice = formatRealDevice(edgeDeviceDto);
        realDevice.setDeviceName(bindEdgeDeviceDto.getDeviceName());
        log.info("loginAuthDto is {}", loginAuthDto);
        UpdateInfoUtil.setInsertInfo(realDevice, loginAuthDto);
        try {
            deviceMapper.insert(realDevice);
        } catch (Exception e) {
            log.info("error is {}",e.getMessage());
            throw new BusinessException("db error");
        }
    }

    private Device formatRealDevice (EdgeDeviceDto edgeDeviceDto) {
        Device realDevice = new Device();
        realDevice.setEdgeDeviceName(edgeDeviceDto.getDeviceName());
        try {
            String deviceCrd = JacksonUtil.toJson(edgeDeviceDto);
            realDevice.setDeviceCrd(deviceCrd);
        } catch (Exception e) {
            throw new BusinessException("json 序列化 error");
        }
        return realDevice;
    }

    private EdgeDeviceDto formatEdgeDeviceDto (EdgeDevice edgeDevice) {
        EdgeDeviceDto deviceDto = new EdgeDeviceDto();
        List<DeviceTwin> deviceTwins=edgeDevice.getStatus().getTwins();
        List<EdgeDeviceTwinDto> deviceTwinDtos=new ArrayList<>();
        for(DeviceTwin twin:deviceTwins){
            EdgeDeviceTwinDto deviceTwinDto=new EdgeDeviceTwinDto();
            deviceTwinDto.setPropertyName(twin.getPropertyName());
            if (twin.getDesired()!=null && twin.getDesired().getMetadata() != null) {
                deviceTwinDto.setRequireType(twin.getDesired().getMetadata().getType());
            }
            if (twin.getDesired() != null) {
                deviceTwinDto.setRequireValue(twin.getDesired().getValue());
            }
            if(twin.getReported()!=null){
                deviceTwinDto.setReportedTime(twin.getReported().getMetadata().getTimestamp());
                deviceTwinDto.setReportedType(twin.getReported().getMetadata().getType());
                deviceTwinDto.setReportedValue(twin.getReported().getValue());
            }
            deviceTwinDtos.add(deviceTwinDto);
        }
        deviceDto.setDeviceTwinDtoList(deviceTwinDtos);
        deviceDto.setDeviceModelRefName(edgeDevice.getSpec().getDeviceModelRef().getName());
        deviceDto.setNodeName(edgeDevice.getSpec().getNodeSelector().getNodeSelectorTerms().get(0)
                .getMatchExpressions().get(0).getValues().get(0));
        deviceDto.setDeviceName(edgeDevice.getMetadata().getName());
        deviceDto.setDescription(edgeDevice.getMetadata().getLabels().get("description"));
        deviceDto.setModel(edgeDevice.getMetadata().getLabels().get("model"));
        return deviceDto;
    }

    public EdgeDevice formatEdgeDevice(EdgeDeviceDto deviceDto) {
        EdgeDevice device = new EdgeDevice();
        DeviceSpec deviceSpec = new DeviceSpec();
        DeviceModelRef deviceModelRef = new DeviceModelRef();
        deviceModelRef.setName(deviceDto.getDeviceModelRefName());
        deviceSpec.setDeviceModelRef(deviceModelRef);
        NodeSelector nodeSelector = new NodeSelector();
        List<NodeSelectorTerm> nodeSelectorTerms = new ArrayList<>();
        NodeSelectorTerm nodeSelectorTerm = new NodeSelectorTerm();
        List<NodeSelectorRequirement> nodeSelectorRequirements = new ArrayList<>();
        NodeSelectorRequirement nodeSelectorRequirement = new NodeSelectorRequirement();
        nodeSelectorRequirement.setOperator("In");
        List<String> nodes = new ArrayList<>();
        nodes.add(deviceDto.getNodeName());
        nodeSelectorRequirement.setValues(nodes);
        nodeSelectorRequirements.add(0,nodeSelectorRequirement);
        nodeSelectorTerm.setMatchExpressions(nodeSelectorRequirements);
        nodeSelectorTerms.add(0,nodeSelectorTerm);
        nodeSelector.setNodeSelectorTerms(nodeSelectorTerms);
        deviceSpec.setNodeSelector(nodeSelector);
        device.setSpec(deviceSpec);

        DeviceStatus deviceStatus = new DeviceStatus();
        List<DeviceTwin> deviceTwins = new ArrayList<>();
        deviceDto.getDeviceTwinDtoList().forEach(edgeDeviceTwin -> {
            DeviceTwin deviceTwin = new DeviceTwin();
            deviceTwin.setPropertyName(edgeDeviceTwin.getPropertyName());
            DeviceDesired deviceDesired = new DeviceDesired();
            DeviceDesiredMetadata desiredMetadata = new DeviceDesiredMetadata();
            desiredMetadata.setType(edgeDeviceTwin.getRequireType());
            deviceDesired.setMetadata(desiredMetadata);
            deviceDesired.setValue(edgeDeviceTwin.getRequireValue());
            deviceTwin.setDesired(deviceDesired);
            deviceTwins.add(deviceTwin);
        });
        deviceStatus.setTwins(deviceTwins);
        device.setStatus(deviceStatus);
        device.setApiVersion("devices.kubeedge.io/v1alpha1");
        device.setKind("Device");
        ObjectMeta objectMeta = new ObjectMeta();
        objectMeta.setName(deviceDto.getDeviceName());
        Map<String,String> map = new HashMap<>();
        map.put("description",deviceDto.getDescription());
        map.put("model",deviceDto.getModel());
        objectMeta.setLabels(map);
        objectMeta.setNamespace("default");
        device.setMetadata(objectMeta);
        return device;
    }
}
