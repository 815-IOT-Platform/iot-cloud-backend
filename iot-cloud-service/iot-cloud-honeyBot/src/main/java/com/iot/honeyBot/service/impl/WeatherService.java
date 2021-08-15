package com.iot.honeyBot.service.impl;



import com.iot.honeyBot.mapper.WeatherMapper;
import com.iot.honeyBot.model.domain.Weather;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WeatherService {

    @Resource
    private WeatherMapper weatherMapper;

    public boolean init() {
        weatherMapper.createDB();
        weatherMapper.createTable();
        return true;
    }

    public List<Weather> query(Long limit, Long offset) {
        return weatherMapper.select(limit, offset);
    }

    public int save(int temperature, float humidity) {
        Weather weather = new Weather();
        weather.setTemperature(temperature);
        weather.setHumidity(humidity);

        return weatherMapper.insert(weather);
    }

    public int save(List<Weather> weatherList) {
        return weatherMapper.batchInsert(weatherList);
    }

}
