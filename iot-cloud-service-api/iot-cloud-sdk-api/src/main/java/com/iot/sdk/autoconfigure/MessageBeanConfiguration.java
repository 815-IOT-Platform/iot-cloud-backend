/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：MessageBeanConfiguration.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.iot.sdk.autoconfigure;

import com.iot.sdk.aspect.MqConsumerStoreAspect;
import com.iot.sdk.aspect.MqProducerStoreAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The class Elastic job auto configuration.
 *
 * @author ananops.com @gmail.com
 */
@Configuration
public class MessageBeanConfiguration {

	@Bean
	@ConditionalOnExpression("${iot-cloud.rocketmq.reliableMessageConsumer:true}")
	public MqConsumerStoreAspect mqConsumerStoreAspect() {
		return new MqConsumerStoreAspect();
	}

	@Bean
	@ConditionalOnExpression("${iot-cloud.rocketmq.reliableMessageProducer:true}")
	public MqProducerStoreAspect mqProducerStoreAspect() {
		return new MqProducerStoreAspect();
	}
}
