package com.iot.common.config;

import com.iot.common.config.properties.IotProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by rongshuai on 2020/6/17 8:39
 */
@Configuration
@EnableConfigurationProperties(IotProperties.class)
public class IotCoreConfig {

}
