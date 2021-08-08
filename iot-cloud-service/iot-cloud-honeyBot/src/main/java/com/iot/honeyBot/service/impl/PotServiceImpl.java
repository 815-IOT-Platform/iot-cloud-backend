package com.iot.honeyBot.service.impl;

import com.iot.device.dto.EdgeDeviceDto;
import com.iot.device.dto.EdgeDeviceTwinDto;
import com.iot.honeyBot.model.constants.Constants;
import com.iot.honeyBot.model.constants.ProtocolType;
import com.iot.honeyBot.model.crd.device.*;
import com.iot.honeyBot.model.vo.Honeypot;
import com.iot.honeyBot.service.PotService;
import io.fabric8.kubernetes.api.model.*;
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

import static com.iot.honeyBot.model.constants.Constants.PotDeviceModel;
import static com.iot.honeyBot.model.constants.Constants.PotPrefix;

@Slf4j
@Service
public class PotServiceImpl implements PotService {
    @Autowired
    KubernetesClient k8sClient;

    @Autowired
    NonNamespaceOperation<EdgeDevice, DeviceList, DoneableDevice, Resource<EdgeDevice, DoneableDevice>> deviceClient;

    @Override
    public NodeList GetAllEdgeNode() {
        return k8sClient.nodes().list();
    }

    @Override
    public List<Honeypot> GetAllPotByNode(String node) {
        CustomResourceList<EdgeDevice> deviceList = deviceClient.list();
        List<EdgeDevice> devices=deviceList.getItems();
        List<Honeypot> pots=new ArrayList<>();
        for(EdgeDevice edgeDevice:devices){
            if (edgeDevice.getSpec().getNodeSelector().getNodeSelectorTerms().get(0)
                    .getMatchExpressions().get(0).getValues().get(0).equals(node) && edgeDevice.getMetadata().getName().startsWith("pot")) {
                log.info("deviceName is {}",edgeDevice.getMetadata().getName());
                pots.add(formatToHoneypot(edgeDevice));
            }
        }
        return pots;
    }

    private Honeypot formatToHoneypot (EdgeDevice edgeDevice) {
        Honeypot honeypot = new Honeypot();
        List<DeviceTwin> deviceTwins=edgeDevice.getStatus().getTwins();
        for(DeviceTwin twin:deviceTwins){
            if(twin.getReported()!=null){
                String type = twin.getPropertyName();
                String value = twin.getReported().getValue();
                switch (type) {
                    case "switch" :
                        honeypot.setStatus(value);
                        break;
                    case "protocol" :
                        honeypot.setProtocol(ProtocolType.valueOfName(value));
                        break;
                    case "port" :
                        honeypot.setPort(value);
                        break;
                    default:
                        break;
                }
            }
        }
        honeypot.setName(edgeDevice.getMetadata().getName());
        honeypot.setNode(edgeDevice.getSpec().getNodeSelector().getNodeSelectorTerms().get(0)
                .getMatchExpressions().get(0).getValues().get(0));
        honeypot.setDescription(edgeDevice.getMetadata().getLabels().get("description"));
        return honeypot;
    }

    public EdgeDevice formatEdgeDevice(Honeypot honeypot) {
        EdgeDevice device = new EdgeDevice();
        DeviceSpec deviceSpec = new DeviceSpec();
        DeviceModelRef deviceModelRef = new DeviceModelRef();
        deviceModelRef.setName(PotDeviceModel);
        deviceSpec.setDeviceModelRef(deviceModelRef);
        NodeSelector nodeSelector = new NodeSelector();
        List<NodeSelectorTerm> nodeSelectorTerms = new ArrayList<>();
        NodeSelectorTerm nodeSelectorTerm = new NodeSelectorTerm();
        List<NodeSelectorRequirement> nodeSelectorRequirements = new ArrayList<>();
        NodeSelectorRequirement nodeSelectorRequirement = new NodeSelectorRequirement();
        nodeSelectorRequirement.setOperator("In");
        List<String> nodes = new ArrayList<>();
        nodes.add(honeypot.getNode());
        nodeSelectorRequirement.setValues(nodes);
        nodeSelectorRequirements.add(0,nodeSelectorRequirement);
        nodeSelectorTerm.setMatchExpressions(nodeSelectorRequirements);
        nodeSelectorTerms.add(0,nodeSelectorTerm);
        nodeSelector.setNodeSelectorTerms(nodeSelectorTerms);
        deviceSpec.setNodeSelector(nodeSelector);
        device.setSpec(deviceSpec);

        DeviceStatus deviceStatus = new DeviceStatus();
        List<DeviceTwin> deviceTwins = new ArrayList<>();
        // set switch
        DeviceTwin switchTwin = new DeviceTwin();
        switchTwin.setPropertyName(Constants.Status);
        DeviceDesired switchDesired = new DeviceDesired();
        DeviceDesiredMetadata switchMetadata = new DeviceDesiredMetadata();
        switchMetadata.setType("string");
        switchDesired.setMetadata(switchMetadata);
        switchDesired.setValue(honeypot.getStatus());
        switchTwin.setDesired(switchDesired);
        deviceTwins.add(switchTwin);

        // set protocol
        DeviceTwin protocolTwin = new DeviceTwin();
        switchTwin.setPropertyName(Constants.Protocol);
        DeviceDesired protocolDesired = new DeviceDesired();
        DeviceDesiredMetadata protocolMetadata = new DeviceDesiredMetadata();
        protocolMetadata.setType("string");
        protocolDesired.setMetadata(protocolMetadata);
        protocolDesired.setValue(honeypot.getProtocol().getProtocol());
        protocolTwin.setDesired(protocolDesired);
        deviceTwins.add(protocolTwin);

        // set port
        DeviceTwin portTwin = new DeviceTwin();
        switchTwin.setPropertyName(Constants.Port);
        DeviceDesired portDesired = new DeviceDesired();
        DeviceDesiredMetadata portMetadata = new DeviceDesiredMetadata();
        portMetadata.setType("string");
        portDesired.setMetadata(portMetadata);
        portDesired.setValue(honeypot.getPort());
        portTwin.setDesired(portDesired);
        deviceTwins.add(portTwin);

        deviceStatus.setTwins(deviceTwins);
        device.setStatus(deviceStatus);
        device.setApiVersion("devices.kubeedge.io/v1alpha2");
        device.setKind("Device");
        ObjectMeta objectMeta = new ObjectMeta();
        objectMeta.setGenerateName(PotPrefix + honeypot.getProtocol());
        Map<String,String> map = new HashMap<>();
        map.put("description",honeypot.getDescription());
        objectMeta.setLabels(map);
        objectMeta.setNamespace("default");
        device.setMetadata(objectMeta);
        return device;
    }
}
