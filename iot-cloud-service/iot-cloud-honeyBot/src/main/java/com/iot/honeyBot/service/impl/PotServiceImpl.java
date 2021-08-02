package com.iot.honeyBot.service.impl;

import com.iot.honeyBot.service.PotService;
import io.fabric8.kubernetes.api.model.NodeList;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PotServiceImpl implements PotService {
    @Autowired
    KubernetesClient k8sClient;

    @Override
    public NodeList GetAllEdgeNode() {
        NodeList nodeList =  k8sClient.nodes().list();
        return nodeList;
    }
}
