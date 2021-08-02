package com.iot.honeyBot.service;

import io.fabric8.kubernetes.api.model.NodeList;

public interface PotService {

    NodeList GetAllEdgeNode();
}
