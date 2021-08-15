package com.iot.honeyBot.controller;


import com.iot.honeyBot.model.domain.Weather;
import com.iot.honeyBot.service.impl.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/weather")
@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    /**
     * create database and table
     *
     * @return
     */
    @GetMapping("/init")
    public boolean init() {
        return weatherService.init();
    }

    /**
     * Pagination Query
     *
     * @param limit
     * @param offset
     * @return
     */
    @GetMapping("/{limit}/{offset}")
    public List<Weather> queryWeather(@PathVariable Long limit, @PathVariable Long offset) {
        return weatherService.query(limit, offset);
    }

    /**
     * upload single weather info
     *
     * @param temperature
     * @param humidity
     * @return
     */
    @PostMapping("/{temperature}/{humidity}")
    public int saveWeather(@PathVariable int temperature, @PathVariable float humidity) {

        return weatherService.save(temperature, humidity);
    }

    /**
     * upload multi weather info
     *
     * @param weatherList
     * @return
     */
    @PostMapping("/batch")
    public int batchSaveWeather(@RequestBody List<Weather> weatherList) {

        return weatherService.save(weatherList);
    }

}
