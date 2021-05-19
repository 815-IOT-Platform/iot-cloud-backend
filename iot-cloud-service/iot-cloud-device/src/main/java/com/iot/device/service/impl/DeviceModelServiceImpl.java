package com.iot.device.service.impl;


import com.iot.device.dto.DeviceModelPropertyDto;
import com.iot.device.dto.EdgeDeviceModelDto;
import com.iot.device.model.crd.deviceModel.*;
import com.iot.device.model.crd.modelType.DoubleType;
import com.iot.device.model.crd.modelType.IntType;
import com.iot.device.model.crd.modelType.ModelType;
import com.iot.device.model.crd.modelType.StringType;
import com.iot.device.service.DeviceModelService;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.client.CustomResourceList;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huqiaoqian on 2020/10/16
 */
@Service
public class DeviceModelServiceImpl implements DeviceModelService {
    @Autowired
    private NonNamespaceOperation<EdgeDeviceModel, DeviceModelList, DoneableDeviceModel, Resource<EdgeDeviceModel, DoneableDeviceModel>> deviceModelClient;

    @Override
    public void createDeviceModel(EdgeDeviceModelDto deviceModelDto) {
        try {
            EdgeDeviceModel deviceModel=new EdgeDeviceModel();
            ObjectMeta objectMeta=new ObjectMeta();
            objectMeta.setName(deviceModelDto.getName());
            objectMeta.setNamespace("default");
            deviceModel.setApiVersion("devices.kubeedge.io/v1alpha2");
            deviceModel.setMetadata(objectMeta);
            deviceModel.setKind("DeviceModel");
            DeviceModelSpec modelSpec=new DeviceModelSpec();
            List<DeviceModelProperty> modelProperties=new ArrayList<>();
            List<DeviceModelPropertyDto> modelPropertyDtos=deviceModelDto.getPropertyDtos();
            for(DeviceModelPropertyDto propertyDto:modelPropertyDtos) {
                DeviceModelProperty modelProperty = new DeviceModelProperty();
                modelProperty.setDescription(propertyDto.getDescription());
                modelProperty.setName(propertyDto.getName());
                ModelType type=new ModelType();
                String type1=propertyDto.getType();
                if(type1!=null&&type1.equals("string")){
                    StringType stringType=new StringType();
                    stringType.setAccessMode(propertyDto.getAccessMode());
                    stringType.setDefaultValue(propertyDto.getValue());
                    type.setStringType(stringType);
                }
                else if(type1!=null&&type1.equals("double")){
                    DoubleType doubleType=new DoubleType();
                    doubleType.setAccessMode(propertyDto.getAccessMode());
                    doubleType.setDefaultValue(Double.valueOf(propertyDto.getValue()));
                    type.setDoubleType(doubleType);
                }
                else if(type1!=null&&type1.equals("int")){
                    IntType intType=new IntType();
                    intType.setAccessMode(propertyDto.getAccessMode());
                    intType.setDefaultValue(Integer.valueOf(propertyDto.getValue()));
                    type.setIntType(intType);
                }
                modelProperty.setType(type);
                modelProperties.add(modelProperty);
            }
            modelSpec.setProperties(modelProperties);
            deviceModel.setSpec(modelSpec);
            deviceModelClient.createOrReplace(deviceModel);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("创建设备模型失败");
        }
    }

    @Override
    public List<EdgeDeviceModelDto> getAllDeviceModel() {
        CustomResourceList<EdgeDeviceModel> deviceModelList = deviceModelClient.list();
        List<EdgeDeviceModel> deviceModels=deviceModelList.getItems();
        List<EdgeDeviceModelDto> deviceModelDtos=new ArrayList<>();
        for (EdgeDeviceModel deviceModel:deviceModels){
            EdgeDeviceModelDto deviceModelDto=new EdgeDeviceModelDto();
            List<DeviceModelPropertyDto> propertyDtos=new ArrayList<>();
            List<DeviceModelProperty> properties=deviceModel.getSpec().getProperties();
            for(DeviceModelProperty property:properties){
                DeviceModelPropertyDto propertyDto=new DeviceModelPropertyDto();
                propertyDto.setName(property.getName());
                propertyDto.setDescription(property.getDescription());
                if(property.getType()!=null){
                    if(property.getType().getStringType()!=null){
                        propertyDto.setType("string");
                        propertyDto.setAccessMode(property.getType().getStringType().getAccessMode());
                        propertyDto.setValue(String.valueOf(property.getType().getStringType().getDefaultValue()));
                    }
                    else if(property.getType().getDoubleType()!=null){
                        propertyDto.setType("double");
                        propertyDto.setAccessMode(property.getType().getDoubleType().getAccessMode());
                        propertyDto.setValue(String.valueOf(property.getType().getDoubleType().getDefaultValue()));
                    }
                    else if(property.getType().getIntType()!=null){
                        propertyDto.setType("int");
                        propertyDto.setAccessMode(property.getType().getIntType().getAccessMode());
                        propertyDto.setValue(String.valueOf(property.getType().getIntType().getDefaultValue()));
                    }
                }
                propertyDtos.add(propertyDto);
            }
            if(deviceModel.getMetadata()!=null)
            deviceModelDto.setName(deviceModel.getMetadata().getName());
            deviceModelDto.setPropertyDtos(propertyDtos);
            deviceModelDtos.add(deviceModelDto);
        }
        return deviceModelDtos;
    }
}
