package com.iot.honeyBot.config;



import com.iot.honeyBot.model.crd.device.DeviceList;
import com.iot.honeyBot.model.crd.device.DoneableDevice;
import com.iot.honeyBot.model.crd.device.EdgeDevice;
import com.iot.honeyBot.model.crd.deviceModel.DeviceModelList;
import com.iot.honeyBot.model.crd.deviceModel.DoneableDeviceModel;
import com.iot.honeyBot.model.crd.deviceModel.EdgeDeviceModel;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.apiextensions.CustomResourceDefinition;
import io.fabric8.kubernetes.api.model.apiextensions.CustomResourceDefinitionList;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.Watcher;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import io.fabric8.kubernetes.client.dsl.base.CustomResourceDefinitionContext;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Created by huqiaoqian on 2020/10/16
 */
@Data
@Configuration
@Slf4j
public class K8sDeviceConfig {
    @Autowired
    private KubernetesClient k8sClient;

    public static String DEVICE_CRD_GROUP = "devices.kubeedge.io";
    public static String DEVICE_CRD_NAME = "devices." +  DEVICE_CRD_GROUP;
    public static String DEVICE_MODEL_CRD_NAME = "devicemodels." + DEVICE_CRD_GROUP;

    public static final CustomResourceDefinitionContext DeviceCRDContext = new CustomResourceDefinitionContext.Builder()
            .withGroup("devices.kubeedge.io")
            .withKind("device")
            .withName("devices.devices.kubeedge.io")
            .withPlural("devices")
            .withScope("Namespaced")
            .withVersion("v1alpha2")
            .build();

    @Bean
    public NonNamespaceOperation<EdgeDevice, DeviceList, DoneableDevice, Resource<EdgeDevice, DoneableDevice>> deviceClient()
            throws KubernetesClientException {
        CustomResourceDefinitionList crds = k8sClient.customResourceDefinitions().list();
        List<CustomResourceDefinition> crdsItems = crds.getItems();
        System.out.println("Found " + crdsItems.size() + " CRD(s)");
        CustomResourceDefinitionContext deviceCrdCtx = null;
        for (CustomResourceDefinition crd : crdsItems) {
            ObjectMeta metadata = crd.getMetadata();
            if (metadata != null) {
                String name = metadata.getName();
                System.out.println("    " + name + " => " + metadata.getName());
                if (DEVICE_CRD_NAME.equals(name)) {
                    deviceCrdCtx = CustomResourceDefinitionContext.fromCrd(crd);
                }
            }
        }
        return k8sClient.customResources(deviceCrdCtx, EdgeDevice.class, DeviceList.class, DoneableDevice.class).inNamespace("default");
    }

    @Bean
    public NonNamespaceOperation<EdgeDeviceModel, DeviceModelList, DoneableDeviceModel, Resource<EdgeDeviceModel, DoneableDeviceModel>> deviceModelClient()
            throws KubernetesClientException {
        CustomResourceDefinitionList crds = k8sClient.customResourceDefinitions().list();
        List<CustomResourceDefinition> crdsItems = crds.getItems();
        System.out.println("Found " + crdsItems.size() + " CRD(s)");
        CustomResourceDefinition deviceModelCRD = null;
        for (CustomResourceDefinition crd : crdsItems) {
            ObjectMeta metadata = crd.getMetadata();
            if (metadata != null) {
                String name = metadata.getName();
                System.out.println("    " + name + " => " + metadata.getSelfLink());
                if (DEVICE_MODEL_CRD_NAME.equals(name)) {
                    deviceModelCRD = crd;
                }
            }
        }
        return k8sClient.customResources(deviceModelCRD, EdgeDeviceModel.class, DeviceModelList.class, DoneableDeviceModel.class);
    }
}
