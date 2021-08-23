package com.iot.honeyBot.service;

import com.iot.honeyBot.model.domain.PotData;
import com.iot.honeyBot.model.dto.CollectPotDto;
import com.iot.honeyBot.model.dto.SearchPotDto;
import com.iot.honeyBot.model.vo.EdgeNodeVo;
import com.iot.honeyBot.model.vo.Honeypot;

import java.util.List;

public interface PotService {

    List<EdgeNodeVo> GetAllEdgeNode();

    EdgeNodeVo GetNode(String nodeName);

    List<Honeypot> GetAllPotByNode(String node);

    void CreatePot(Honeypot honeypot);

    void UpdatePot(Honeypot honeypot);

    void StartCollectNode(String node);

    void StartCollectPot(CollectPotDto collectPotDto);

    List<PotData> GetPotData(SearchPotDto searchPotDto);

    void DeletePot(String potName);

    List<PotData> GetAlarmData();
}
