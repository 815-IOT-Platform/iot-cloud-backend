/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：MqConsumerStoreAspect.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.iot.sdk.aspect;

import com.iot.common.exception.BusinessException;
import com.iot.sdk.annotation.MqConsumerStore;
import com.iot.sdk.domain.MqMessageData;
import com.iot.sdk.enums.MqMessageTypeEnum;
import com.iot.sdk.service.MqMessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.List;


/**
 * The class Mq consumer store aspect.
 *
 * @author ananops.com @gmail.com
 */
@Slf4j
@Aspect
public class MqConsumerStoreAspect {

	@Resource
	private MqMessageService mqMessageService;
	@Value("${ananops.rocketmq.consumerGroup}")
	private String consumerGroup;

	private static final String CONSUME_SUCCESS = "CONSUME_SUCCESS";

	/**
	 * Add exe time annotation pointcut.
	 */
	@Pointcut("@annotation(com.iot.sdk.annotation.MqConsumerStore)")
	public void mqConsumerStoreAnnotationPointcut() {

	}

	/**
	 * Add exe time method object.
	 *
	 * @param joinPoint the join point
	 *
	 * @return the object
	 *
	 * @throws Throwable the throwable
	 */
	@Around(value = "mqConsumerStoreAnnotationPointcut()")
	public Object processMqConsumerStoreJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {

		log.info("processMqConsumerStoreJoinPoint - 线程id={}", Thread.currentThread().getId());
		Object result;
		long startTime = System.currentTimeMillis();
		Object[] args = joinPoint.getArgs();
		MqConsumerStore annotation = getAnnotation(joinPoint);
		boolean isStorePreStatus = annotation.storePreStatus();
		List<MessageExt> messageExtList;
		if (args == null || args.length == 0) {
			throw new BusinessException("目标接口参数不能为空");
		}

		if (!(args[0] instanceof List)) {
			throw new BusinessException("注解使用错误");
		}

		try {
			messageExtList = (List<MessageExt>) args[0];
		} catch (Exception e) {
			log.error("processMqConsumerStoreJoinPoint={}", e.getMessage(), e);
			throw new BusinessException("注解使用错误");
		}

		MqMessageData dto = this.getTpcMqMessageDto(messageExtList.get(0));
		final String messageKey = dto.getMessageKey();
		if (isStorePreStatus) {
			mqMessageService.confirmReceiveMessage(consumerGroup, dto);
		}
		String methodName = joinPoint.getSignature().getName();
		try {
			result = joinPoint.proceed();
			log.info("result={}", result);
			if (CONSUME_SUCCESS.equals(result.toString())) {
				mqMessageService.saveAndConfirmFinishMessage(consumerGroup, messageKey);
			}
		} catch (Exception e) {
			log.error("发送可靠消息, 目标方法[{}], 出现异常={}", methodName, e.getMessage(), e);
			throw e;
		} finally {
			log.info("发送可靠消息 目标方法[{}], 总耗时={}", methodName, System.currentTimeMillis() - startTime);
		}
		return result;
	}

	private MqConsumerStore getAnnotation(JoinPoint joinPoint) {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		return method.getAnnotation(MqConsumerStore.class);
	}

	private MqMessageData getTpcMqMessageDto(MessageExt messageExt) {
		MqMessageData data = new MqMessageData();
		data.setMessageBody(new String(messageExt.getBody()));
		data.setMessageKey(messageExt.getKeys());
		data.setMessageTag(messageExt.getTags());
		data.setMessageTopic(messageExt.getTopic());
		data.setMessageType(MqMessageTypeEnum.CONSUMER_MESSAGE.messageType());
		return data;
	}
}
