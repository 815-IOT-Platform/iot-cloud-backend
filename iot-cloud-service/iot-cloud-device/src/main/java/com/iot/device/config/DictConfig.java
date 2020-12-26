package com.iot.device.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * Created by rongshuai on 2020/12/26 23:01
 */
@Data
@Configuration
@ConfigurationProperties("dict")
public class DictConfig {
    private Map<String,String> deviceDict;
}
