package com.iot.honeyBot.service;

import com.iot.device.dto.EdgeDeviceDto;
import com.iot.honeyBot.model.vo.Honeypot;
import io.fabric8.kubernetes.api.model.NodeList;

import java.util.List;

public interface PotService {

    NodeList GetAllEdgeNode();

    List<Honeypot> GetAllPotByNode(String node);
}
