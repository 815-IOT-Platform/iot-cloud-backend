package com.iot.device.service.impl;

import com.google.common.base.Charsets;
import com.iot.device.model.domain.Node;
import com.iot.device.service.EtcdService;
import com.iot.device.service.NodeService;
import io.etcd.jetcd.KeyValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class NodeServiceImpl implements NodeService {
    @Autowired
    private EtcdService etcdService;

    private static final String NodePath = "/node";

    @Override
    public void createEdgeNode(Node node) {
        String path = NodePath + "/" + node.getNodeId();
        etcdService.putEtcdKV(path, node.getNodeName());
    }

    @Override
    public List<Node> getAllEdgeNode() {
        List<KeyValue> kvs = etcdService.getEtcdKV(NodePath, NodePath);
        List<Node> nodes = new ArrayList<>();
        for (KeyValue kv : kvs) {
            String key = kv.getKey().toString(Charsets.UTF_8);
            String[] paths = key.split("/");
            String nodeId = paths[paths.length-1];
            String nodeName = kv.getValue().toString(Charsets.UTF_8);
            Node node = new Node();
            node.setNodeId(nodeId);
            node.setNodeName(nodeName);
            nodes.add(node);
        }
        return nodes;
    }
}
