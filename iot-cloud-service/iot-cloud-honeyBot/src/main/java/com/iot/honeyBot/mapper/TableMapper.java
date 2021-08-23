package com.iot.honeyBot.mapper;


import com.iot.honeyBot.model.domain.PotData;
import com.iot.honeyBot.model.domain.SearchPotDo;
import com.iot.honeyBot.model.domain.TableMetadata;

import java.util.List;

public interface TableMapper {

    boolean createSTable(TableMetadata tableMetadata);

    List<PotData> selectAll(SearchPotDo searchPotDo);

    Integer selectCnt(SearchPotDo searchPotDo);

    List<PotData> getAlarmData();
}