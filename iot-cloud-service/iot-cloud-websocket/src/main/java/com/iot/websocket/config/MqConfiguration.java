package com.iot.websocket.config;

import com.iot.common.config.GlobalConstants;
import com.iot.common.config.properties.IotProperties;
import com.iot.common.config.thread.ThreadPoolConfig;
import com.iot.common.constant.MqTopicConstants;
import com.iot.common.utils.StringUtils;
import com.iot.websocket.consumer.listener.WebSocketMqListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Created by rongshuai on 2020/6/18 14:31
 */
@Slf4j
@Configuration
public class MqConfiguration {
    @Autowired
    private IotProperties iotProperties;

    @Autowired
    private ThreadPoolConfig threadPoolConfig;

    @Autowired
    private WebSocketMqListener webSocketMqListener;

    @Bean
    public DefaultMQPushConsumer defaultMQPushConsumer() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(iotProperties.getRocketMq().getConsumerGroup());
        consumer.setNamesrvAddr(iotProperties.getRocketMq().getNamesrvAddr());
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

        String[] strArray = MqTopicConstants.ConsumerTopics.WEBSOCKET.split(GlobalConstants.Symbol.COMMA);
        for (String aStrArray : strArray) {
            String[] topicArray = aStrArray.split(GlobalConstants.Symbol.AT);
            String topic = topicArray[0];
            String tags = topicArray[1];
            if (StringUtils.isEmpty(tags)) {
                tags = "*";
            }
            consumer.subscribe(topic, tags);
            log.info("RocketMq WebSocketPushConsumer topic = {}, tags={}", topic, tags);
        }

        consumer.registerMessageListener(webSocketMqListener);
        consumer.setConsumeThreadMax(2);
        consumer.setConsumeThreadMin(2);

        ThreadPoolTaskExecutor taskExecutor = threadPoolConfig.threadPoolTaskExecutor();
        taskExecutor.execute(() -> {
            try {
                Thread.sleep(5000);
                consumer.start();
                log.info("RocketMq WebSocketPushConsumer OK.");
            } catch (InterruptedException | MQClientException e) {
                log.error("RocketMq WebSocketPushConsumer, 出现异常={}", e.getMessage(), e);
            }
        });
        return consumer;
    }
}
