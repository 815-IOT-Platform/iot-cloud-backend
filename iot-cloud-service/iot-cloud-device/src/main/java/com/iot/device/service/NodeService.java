package com.iot.device.service;

import java.util.List;

public interface NodeService {

    public List<io.fabric8.kubernetes.api.model.Node> getAllNodes();
}
