package com.iot.honeyBot.service.impl;


import com.iot.honeyBot.mapper.DatabaseMapper;
import com.iot.honeyBot.mapper.TableMapper;
import com.iot.honeyBot.model.constants.NodeStatus;
import com.iot.honeyBot.model.constants.ProtocolType;
import com.iot.honeyBot.model.crd.device.*;
import com.iot.honeyBot.model.domain.FieldMetadata;
import com.iot.honeyBot.model.domain.PotData;
import com.iot.honeyBot.model.domain.SearchPotDo;
import com.iot.honeyBot.model.domain.TableMetadata;
import com.iot.honeyBot.model.dto.CollectPotDto;
import com.iot.honeyBot.model.dto.SearchPotDto;
import com.iot.honeyBot.model.vo.EdgeNodeVo;
import com.iot.honeyBot.model.vo.Honeypot;
import com.iot.honeyBot.service.PotService;
import com.iot.honeyBot.util.K8sutil;
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

import static com.iot.honeyBot.config.K8sDeviceConfig.DeviceCRDContext;
import static com.iot.honeyBot.model.constants.Constants.*;

@Slf4j
@Service
public class PotServiceImpl implements PotService {
    @Autowired
    KubernetesClient k8sClient;

    @Autowired
    NonNamespaceOperation<EdgeDevice, DeviceList, DoneableDevice, Resource<EdgeDevice, DoneableDevice>> deviceClient;

    @javax.annotation.Resource
    DatabaseMapper databaseMapper;

    @javax.annotation.Resource
    TableMapper tableMapper;

    @Override
    public List<EdgeNodeVo> GetAllEdgeNode() {
        NodeList nodeList = k8sClient.nodes().list();
        List<EdgeNodeVo> nodeVos = new ArrayList<>();
        for (Node node : nodeList.getItems()) {
            if (node.getMetadata().getLabels().containsKey("node-role.kubernetes.io/edge")) {
                EdgeNodeVo edgeNodeVo = this.formatNode(node);
                nodeVos.add(edgeNodeVo);
            }
        }
        return nodeVos;
    }

    @Override
    public EdgeNodeVo GetNode(String nodeName) {
        Node node = k8sClient.nodes().withName(nodeName).get();
        return this.formatNode(node);
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

    @Override
    public void CreatePot(Honeypot honeypot) {
        Map<String,Object> potMap = new HashMap<>();
        potMap.putAll(K8sutil.PotTemplate);
        updatePodCR(honeypot.getProtocol().getProtocol(), honeypot.getPort(), honeypot.getNode(), honeypot.getStatus(),"collected", "false", potMap);
        try {
            k8sClient.customResource(DeviceCRDContext).create("default",potMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void UpdatePot(Honeypot honeypot) {
        Map<String,Object> potMap = k8sClient.customResource(DeviceCRDContext).get("default",honeypot.getName());
        updatePodCR(null, honeypot.getPort(), null, honeypot.getStatus(), "", "", potMap);
        try {
            k8sClient.customResource(DeviceCRDContext).createOrReplace("default",potMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void StartCollectNode(String node) {
        databaseMapper.createDatabase(node);
    }

    @Override
    public void StartCollectPot(CollectPotDto collectPotDto) {
        TableMetadata tableMetadata = new TableMetadata();
        tableMetadata.setDbname(collectPotDto.getNode());
        tableMetadata.setTablename(collectPotDto.getProtocol().getProtocol());
        List<FieldMetadata> fieldMetadata = new ArrayList<>();
        fieldMetadata.add(new FieldMetadata("ts", "TIMESTAMP"));
        fieldMetadata.add(new FieldMetadata("value", "NCHAR(2048)"));
        tableMetadata.setFields(fieldMetadata);
        tableMapper.createSTable(tableMetadata);
        Map<String,Object> potMap = k8sClient.customResource(DeviceCRDContext).get("default",collectPotDto.getPotName());
        this.updatePodCR("","","","", "collected", "true", potMap);
        try {
            k8sClient.customResource(DeviceCRDContext).createOrReplace("default",potMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PotData> GetPotData(SearchPotDto searchPotDto) {
        // get pot first
        EdgeDevice edgeDevice = deviceClient.withName(searchPotDto.getName()).get();
        SearchPotDo searchPotDo = new SearchPotDo();
        searchPotDo.setStartTime(searchPotDto.getStartTime());
        searchPotDo.setNode(edgeDevice.getSpec().getNodeSelector().getNodeSelectorTerms().get(0).getMatchExpressions().get(0).getValues().get(0));
        for (DeviceTwin twin : edgeDevice.getStatus().getTwins()) {
            if (twin.getPropertyName().equals("protocol")) {
                searchPotDo.setProtocol(twin.getDesired().getValue());
                break;
            }
        }
        return tableMapper.selectAll(searchPotDo);
    }

    @Override
    public void DeletePot(String potName) {
        try {
            k8sClient.customResource(DeviceCRDContext).delete("default", potName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Honeypot formatToHoneypot (EdgeDevice edgeDevice) {
        log.info("edgeDevice is {}", edgeDevice);
        Honeypot honeypot = new Honeypot();
        List<DeviceTwin> deviceTwins=edgeDevice.getStatus().getTwins();
        for(DeviceTwin twin:deviceTwins){
            String type = twin.getPropertyName();
            String desiredValue = twin.getDesired().getValue();
            switch (type) {
                case "switch" :
                    honeypot.setStatus(desiredValue);
                    break;
                case "protocol" :
                    honeypot.setProtocol(ProtocolType.valueOfName(desiredValue));
                    break;
                case "port" :
                    honeypot.setPort(desiredValue);
                    break;
                default:
                    break;
            }
        }
        honeypot.setName(edgeDevice.getMetadata().getName());
        honeypot.setNode(edgeDevice.getSpec().getNodeSelector().getNodeSelectorTerms().get(0)
                .getMatchExpressions().get(0).getValues().get(0));
        honeypot.setDescription(edgeDevice.getMetadata().getLabels().get("description"));

        // get attack cnt
        SearchPotDo searchPotDo = new SearchPotDo();
        searchPotDo.setNode(honeypot.getNode());
        searchPotDo.setProtocol(honeypot.getProtocol().getProtocol());
        Integer attachCnt = tableMapper.selectCnt(searchPotDo);
        honeypot.setAttackCnt(attachCnt == null ? 0 : attachCnt);
        return honeypot;
    }

    private EdgeNodeVo formatNode(Node node) {
        EdgeNodeVo edgeNodeVo = new EdgeNodeVo();
        edgeNodeVo.setNodeInfo(node.getStatus().getNodeInfo());
        edgeNodeVo.setAddresses(node.getStatus().getAddresses());
        edgeNodeVo.setAllocatable(node.getStatus().getAllocatable());
        edgeNodeVo.setCapacity(node.getStatus().getCapacity());
        edgeNodeVo.setName(node.getMetadata().getName());
        if (!node.getStatus().getConditions().get(0).getStatus().equals("True")) {
            edgeNodeVo.setStatus(NodeStatus.CRASHED);
        } else {
            edgeNodeVo.setStatus(NodeStatus.RUNNING);
        }
        List<Honeypot> honeypots = this.GetAllPotByNode(node.getMetadata().getName());
        edgeNodeVo.setPotNumber(honeypots.size());
        return edgeNodeVo;
    }

    private void updatePodCR(String protocol, String port, String node, String potStatus, String labelKey, String labelValue, Map<String,Object> potMap) {
        Object metadata = potMap.get("metadata");
        if (metadata instanceof Map) {
            if (protocol != null && ! protocol.equals("")) {
                ((Map) metadata).put("generateName", PotPrefix + Stash + protocol + Stash);
                potMap.put("metadata", metadata);
            }
            if (!labelKey.equals("") && !labelValue.equals("")) {
                Object labels = ((Map<?, ?>) metadata).get("labels");
                if (labels instanceof Map) {
                    ((Map<String, String>) labels).put(labelKey, labelValue);
                }
            }
        }
        if (node != null && !node.equals("")) {
            Object spec = potMap.get("spec");
            if (spec instanceof Map) {
                Object nodeSelector = ((Map) spec).get("nodeSelector");
                if (nodeSelector instanceof Map) {
                    Object nodeSelectorTerms = ((Map) nodeSelector).get("nodeSelectorTerms");
                    if (nodeSelectorTerms instanceof List) {
                        Object term0 = ((List) nodeSelectorTerms).get(0);
                        if (term0 instanceof Map) {
                            Object matchExpressions = ((Map) term0).get("matchExpressions");
                            if (matchExpressions instanceof List) {
                                Object match0 = ((List) matchExpressions).get(0);
                                if (match0 instanceof Map) {
                                    List<String> list = new ArrayList<>();
                                    list.add(node);
                                    ((Map) match0).put("values", list);
                                }
                            }
                        }
                    }
                }
            }
        }
        Object status = potMap.get("status");
        if (status instanceof Map) {
            Object twins = ((Map) status).get("twins");
            if (twins instanceof List) {
                for (Object twin : (List)twins) {
                    if (twin instanceof Map) {
                        String property = ((Map) twin).get("propertyName").toString();
                        Object desired;
                        switch (property) {
                            case "protocol":
                                desired = ((Map) twin).get("desired");
                                if (desired instanceof Map) {
                                    if (protocol != null && !protocol.equals("")) {
                                        ((Map) desired).put("value", protocol);
                                    }
                                }
                                ((Map) twin).put("desired", desired);
                                break;
                            case "port":
                                desired = ((Map) twin).get("desired");
                                if (desired instanceof Map) {
                                    if (port != null && !port.equals("")) {
                                        ((Map) desired).put("value", port);
                                    }
                                }
                                ((Map) twin).put("desired", desired);
                                break;
                            case "switch":
                                desired = ((Map) twin).get("desired");
                                if (desired instanceof Map) {
                                    if (potStatus != null && !potStatus.equals("")) {
                                        ((Map) desired).put("value", potStatus);
                                    }
                                }
                                ((Map) twin).put("desired", desired);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }
    }
}
