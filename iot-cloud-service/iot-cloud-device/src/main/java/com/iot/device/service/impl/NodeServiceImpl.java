package com.iot.device.service.impl;

import com.iot.device.service.NodeService;
import io.fabric8.kubernetes.client.KubernetesClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class NodeServiceImpl implements NodeService {

    @Autowired
    private KubernetesClient k8sClient;

    @Override
    public List<io.fabric8.kubernetes.api.model.Node> getAllNodes() {
        return k8sClient.nodes().list().getItems();
    }
}
