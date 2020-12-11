/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：MqProducerStoreAspect.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.iot.sdk.aspect;

import com.iot.common.config.thread.ThreadPoolConfig;
import com.iot.common.exception.BusinessException;
import com.iot.sdk.annotation.MqProducerStore;
import com.iot.sdk.domain.MqMessageData;
import com.iot.sdk.enums.DelayLevelEnum;
import com.iot.sdk.enums.MqSendTypeEnum;
import com.iot.sdk.service.MqMessageService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;

import javax.annotation.Resource;
import java.lang.reflect.Method;


/**
 * The class Mq producer store aspect.
 *
 * @author ananops.com @gmail.com
 */
@Slf4j
@Aspect
public class MqProducerStoreAspect implements Ordered {
	@Resource
	private MqMessageService mqMessageService;

	@Value("${ananops.rocketmq.producerGroup}")
	private String producerGroup;

	@Resource
	private ThreadPoolConfig threadPoolConfig;

	/**
	 * Add exe time annotation pointcut.
	 */
	@Pointcut("@annotation(com.iot.sdk.annotation.MqProducerStore)")
	public void mqProducerStoreAnnotationPointcut() {

	}

	/**
	 * Add exe time method object.
	 *
	 * @param joinPoint the join point
	 *
	 * @return the object
	 */
	@Around(value = "mqProducerStoreAnnotationPointcut()")
	public Object processMqProducerStoreJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info("processMqProducerStoreJoinPoint - 线程id={}", Thread.currentThread().getId());
		Object result;
		Object[] args = joinPoint.getArgs();
		MqProducerStore annotation = getAnnotation(joinPoint);
		MqSendTypeEnum type = annotation.sendType();
		int orderType = annotation.orderType().orderType();
		DelayLevelEnum delayLevelEnum = annotation.delayLevel();
		if (args.length == 0) {
			throw new BusinessException("目标接口参数不能为空");
		}
		MqMessageData domain = null;
		for (Object object : args) {
			if (object instanceof MqMessageData) {
				domain = (MqMessageData) object;
				break;
			}
		}

		if (domain == null) {
			throw new BusinessException("目标接口参数不能为空");
		}

		domain.setOrderType(orderType);
		domain.setProducerGroup(producerGroup);
		//1 等待确认
		if (type == MqSendTypeEnum.WAIT_CONFIRM) {
			if (delayLevelEnum != DelayLevelEnum.ZERO) {
				domain.setDelayLevel(delayLevelEnum.delayLevel());
			}
			//发送待确认消息到可靠消息系统
			//本地服务消息落地，可靠消息服务中心也持久化预发送消息 但是不发送
			mqMessageService.saveWaitConfirmMessage(domain); //只持久化未发送
		}
		result = joinPoint.proceed();//返回注解方法 执行业务即保存用户 下面发送到tpc
		if (type == MqSendTypeEnum.SAVE_AND_SEND) {
			mqMessageService.saveAndSendMessage(domain);
		} else if (type == MqSendTypeEnum.DIRECT_SEND) {
			mqMessageService.directSendMessage(domain);
		} else {
			final MqMessageData finalDomain = domain;
			threadPoolConfig.threadPoolTaskExecutor().execute(() -> mqMessageService.confirmAndSendMessage(finalDomain.getMessageKey()));
		}
		return result;
	}

	private static MqProducerStore getAnnotation(JoinPoint joinPoint) {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		return method.getAnnotation(MqProducerStore.class);
	}

	@Override
	public int getOrder() {
		return 1;
	}
}
