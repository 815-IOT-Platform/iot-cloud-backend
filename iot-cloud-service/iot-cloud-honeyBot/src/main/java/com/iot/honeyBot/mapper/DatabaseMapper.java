package com.iot.honeyBot.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface DatabaseMapper {

    int createDatabase(String dbname);

    int dropDatabase(String dbname);

    int creatDatabaseWithParameters(Map<String,String> map);

    int useDatabase(@Param("dbname") String dbname);

}
