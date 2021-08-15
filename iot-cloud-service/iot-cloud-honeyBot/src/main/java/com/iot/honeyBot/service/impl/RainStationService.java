package com.iot.honeyBot.service.impl;



import com.iot.honeyBot.mapper.DatabaseMapper;
import com.iot.honeyBot.mapper.RainfallMapper;
import com.iot.honeyBot.mapper.TableMapper;
import com.iot.honeyBot.model.domain.FieldMetadata;
import com.iot.honeyBot.model.domain.Rainfall;
import com.iot.honeyBot.model.domain.TableMetadata;
import com.iot.honeyBot.model.domain.TagMetadata;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RainStationService {

    @Resource
    private DatabaseMapper databaseMapper;
    @Resource
    private TableMapper tableMapper;
    @Resource
    private RainfallMapper rainfallMapper;

    public boolean init() {
        databaseMapper.dropDatabase("rainstation");

        Map<String, String> map = new HashMap<>();
        map.put("dbname", "rainstation");
        map.put("keep", "36500");
        map.put("days", "30");
        map.put("blocks", "4");
        databaseMapper.creatDatabaseWithParameters(map);

        databaseMapper.useDatabase("rainstation");
        return true;
    }

    public boolean createTable() {
        TableMetadata tableMetadata = new TableMetadata();
        tableMetadata.setDbname("rainstation");
        tableMetadata.setTablename("monitoring");

        List<FieldMetadata> fields = new ArrayList<>();
        fields.add(new FieldMetadata("ts", "timestamp"));
        fields.add(new FieldMetadata("name", "NCHAR(10)"));
        fields.add(new FieldMetadata("code", " BINARY(8)"));
        fields.add(new FieldMetadata("rainfall", "float"));
        tableMetadata.setFields(fields);

        List<TagMetadata> tags = new ArrayList<>();
        tags.add(new TagMetadata("station_code", "BINARY(8)"));
        tags.add(new TagMetadata("station_name", "NCHAR(10)"));
        tableMetadata.setTags(tags);

        tableMapper.createSTable(tableMetadata);
        return true;
    }


    public int insert(Rainfall rainfall) {
        Map<String, Object> map = new HashMap<>();
        map.put("dbname", "rainstation");
        map.put("table", "S_53646");
        map.put("stable", "monitoring");
        map.put("values", rainfall);
        return rainfallMapper.save(map);
    }
}