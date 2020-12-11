package com.iot.common.config.properties;

import lombok.Data;

/**
 * Created by rongshuai on 2020/6/17 8:41
 */
@Data
public class RocketMqProperties {
    private String consumerGroup;
    private String producerGroup;
    private String namesrvAddr;
    /**
     * 生产者是否使用可靠消息, 默认不使用 @MqConsumerStore
     */
    private boolean reliableMessageProducer;
    /**
     * 消费者是否使用可靠消息, 默认不使用 @MqProducerStore
     */
    private boolean reliableMessageConsumer;
}
