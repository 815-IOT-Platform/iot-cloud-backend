package iot.device.config;


import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.apiextensions.CustomResourceDefinition;
import io.fabric8.kubernetes.api.model.apiextensions.CustomResourceDefinitionList;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.Watcher;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import iot.device.model.domain.device.DeviceList;
import iot.device.model.domain.device.DoneableDevice;
import iot.device.model.domain.device.EdgeDevice;
import iot.device.model.domain.deviceModel.DeviceModelList;
import iot.device.model.domain.deviceModel.DoneableDeviceModel;
import iot.device.model.domain.deviceModel.EdgeDeviceModel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
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

    @Bean
    public NonNamespaceOperation<EdgeDevice, DeviceList, DoneableDevice, Resource<EdgeDevice, DoneableDevice>> deviceClient()
            throws KubernetesClientException {
        CustomResourceDefinitionList crds = k8sClient.customResourceDefinitions().list();
        List<CustomResourceDefinition> crdsItems = crds.getItems();
        System.out.println("Found " + crdsItems.size() + " CRD(s)");
        CustomResourceDefinition deviceCRD = null;
        for (CustomResourceDefinition crd : crdsItems) {
            ObjectMeta metadata = crd.getMetadata();
            if (metadata != null) {
                String name = metadata.getName();
                System.out.println("    " + name + " => " + metadata.getSelfLink());
                if (DEVICE_CRD_NAME.equals(name)) {
                    deviceCRD = crd;
                }
            }
        }
        NonNamespaceOperation<EdgeDevice, DeviceList, DoneableDevice, Resource<EdgeDevice, DoneableDevice>> deviceClient =
                k8sClient.customResources(deviceCRD, EdgeDevice.class, DeviceList.class, DoneableDevice.class);
        deviceClient.watch(new Watcher<EdgeDevice>() {
            @Override
            public void eventReceived(Action action, EdgeDevice resource) {
                System.out.println("==> " + action + " for " + resource);
                if (resource.getSpec() == null) {
                    log.error("No Spec for resource " + resource);
                }
            }

            @Override
            public void onClose(KubernetesClientException cause) {
            }
        });
        return deviceClient;
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
