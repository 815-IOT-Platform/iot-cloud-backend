package com.iot.common.config.properties;

import com.iot.common.config.GlobalConstants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by rongshuai on 2020/6/17 8:59
 */
@Data
@ConfigurationProperties(prefix = GlobalConstants.ROOT_PREFIX)
public class IotProperties {
    private ZookeeperProperties zk = new ZookeeperProperties();
    private RocketMqProperties rocketMq = new RocketMqProperties();
}
