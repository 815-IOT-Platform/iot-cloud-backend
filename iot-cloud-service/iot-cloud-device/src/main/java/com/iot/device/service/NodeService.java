package com.iot.device.service;

import com.iot.device.model.domain.Node;

import java.util.List;

public interface NodeService {

    public void createEdgeNode(Node node);

    public List<Node> getAllEdgeNode();
}
