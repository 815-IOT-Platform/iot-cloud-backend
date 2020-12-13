package iot.device.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by huqiaoqian on 2020/10/29
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "rocketmq")
public class RocketMqProperties {

    private String consumerGroup;
    private String producerGroup;
    private String namesrvAddr;

}
