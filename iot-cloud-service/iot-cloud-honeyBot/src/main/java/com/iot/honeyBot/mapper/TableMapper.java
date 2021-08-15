package com.iot.honeyBot.mapper;


import com.iot.honeyBot.model.domain.TableMetadata;

public interface TableMapper {

    boolean createSTable(TableMetadata tableMetadata);
}