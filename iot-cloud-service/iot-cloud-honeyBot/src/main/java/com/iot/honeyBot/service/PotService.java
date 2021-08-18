package com.iot.honeyBot.service;

import com.iot.honeyBot.model.domain.PotData;
import com.iot.honeyBot.model.dto.CollectPotDto;
import com.iot.honeyBot.model.dto.SearchPotDto;
import com.iot.honeyBot.model.vo.EdgeNodeVo;
import com.iot.honeyBot.model.vo.Honeypot;
import io.fabric8.kubernetes.api.model.NodeList;

import java.util.List;

public interface PotService {

    List<EdgeNodeVo> GetAllEdgeNode();

    List<Honeypot> GetAllPotByNode(String node);

    void CreatePot(Honeypot honeypot);

    void UpdatePot(Honeypot honeypot);

    void StartCollectNode(String node);

    void StartCollectPot(CollectPotDto collectPotDto);

    List<PotData> GetPotData(SearchPotDto searchPotDto);
}
