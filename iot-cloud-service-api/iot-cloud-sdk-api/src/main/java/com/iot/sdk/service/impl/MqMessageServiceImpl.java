/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：MqMessageServiceImpl.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.iot.sdk.service.impl;

import com.iot.common.config.thread.ThreadPoolConfig;
import com.iot.common.core.domain.R;
import com.iot.common.core.dto.MessageQueryDto;
import com.iot.common.core.dto.MqMessageVo;
import com.iot.common.exception.BusinessException;
import com.iot.common.utils.StringUtils;
import com.iot.common.zk.generator.UniqueIdGenerator;
import com.iot.sdk.domain.MqMessageData;
import com.iot.sdk.enums.MqMessageTypeEnum;
import com.iot.sdk.enums.MqSendTypeEnum;
import com.iot.sdk.mapper.MqMessageDataMapper;
import com.iot.sdk.service.MqMessageService;
import com.iot.tpc.dto.TpcMqMessageDto;
import com.iot.tpc.feign.RemoteTpcService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * The class Mq message service.
 *
 * @author ananops.com @gmail.com
 */
@Slf4j
@Service
public class MqMessageServiceImpl implements MqMessageService {
	@Resource
	private MqMessageDataMapper mqMessageDataMapper;
	@Resource
	private RemoteTpcService tpcMqMessageFeignApi;
	@Resource
	private ThreadPoolConfig threadPoolConfig;

	@Value("${spring.profiles.active}")
	String profile;
	@Value("${spring.application.name}")
	String applicationName;

	@Override
	public void saveMqProducerMessage(MqMessageData mqMessageData) {
		// 校验消息数据
		this.checkMessage(mqMessageData);
		// 先保存消息
		mqMessageData.setMessageType(MqMessageTypeEnum.PRODUCER_MESSAGE.messageType());
		mqMessageData.setId(UniqueIdGenerator.generateId());
		mqMessageDataMapper.insertSelective(mqMessageData);
	}

	private void checkMessage(MqMessageData mqMessageData) {
		if (null == mqMessageData) {
			throw new BusinessException("消息数据不能为空");
		}
		String messageTopic = mqMessageData.getMessageTopic();
		String messageBody = mqMessageData.getMessageBody();
		String messageKey = mqMessageData.getMessageKey();
		String producerGroup = mqMessageData.getProducerGroup();
		if (StringUtils.isEmpty(messageKey)) {
			throw new BusinessException("消息KEY不能为空");
		}
		if (StringUtils.isEmpty(messageTopic)) {
			throw new BusinessException("消息的消费Topic不能为空");
		}
		if (StringUtils.isEmpty(messageBody)) {
			throw new BusinessException("消息体不能为空");
		}

		if (StringUtils.isEmpty(producerGroup)) {
			throw new BusinessException("消息PID不能为空");
		}
	}

	@Override
	public void confirmAndSendMessage(String messageKey) {
		// 发送确认消息给消息中心
		try {
			R result = tpcMqMessageFeignApi.confirmAndSendMessage(messageKey);
			if (result == null) {
				throw new BusinessException("微服务不在线,或者网络超时");
			}
			if ((int)result.get("code")==500) {
				throw new BusinessException("消息中心接口异常");
			}
			log.info("<== saveMqProducerMessage - 存储并发送消息给消息中心成功. messageKey={}", messageKey);
		} catch (Exception e) {
			log.error("<== saveMqProducerMessage - 存储并发送消息给消息中心失败. messageKey={}", messageKey, e);
		}

	}

	@Override
	public void saveMqConsumerMessage(MqMessageData mqMessageData) {

	}

	@Override
	public void deleteMessageByMessageKey(String messageKey, MqSendTypeEnum type) {
		log.info("删除预发送消息. messageKey={}, type={}", messageKey, type);
		if (type == MqSendTypeEnum.WAIT_CONFIRM) {
			threadPoolConfig.threadPoolTaskExecutor().execute(() -> tpcMqMessageFeignApi.deleteMessageByMessageKey(messageKey));
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void confirmReceiveMessage(String cid, MqMessageData messageData) {
		final String messageKey = messageData.getMessageKey();
		log.info("confirmReceiveMessage - 消费者={}, 确认收到key={}的消息", cid, messageKey);
		// 先保存消息
		messageData.setMessageType(MqMessageTypeEnum.CONSUMER_MESSAGE.messageType());
		messageData.setId(UniqueIdGenerator.generateId());
		mqMessageDataMapper.insertSelective(messageData);

		R result = tpcMqMessageFeignApi.confirmReceiveMessage(cid, messageKey);
		log.info("tpcMqMessageFeignApi.confirmReceiveMessage result={}", result);
		if (result == null) {
			throw new BusinessException("微服务不在线,或者网络超时");
		}
		if ((int)result.get("code")==500) {
			throw new BusinessException("消息中心接口异常");
		}
	}

	@Override
	public void saveAndConfirmFinishMessage(String cid, String messageKey) {
		R result = tpcMqMessageFeignApi.confirmConsumedMessage(cid, messageKey);
		log.info("tpcMqMessageFeignApi.confirmReceiveMessage result={}", result);
		if (result == null) {
			throw new BusinessException("微服务不在线,或者网络超时");
		}
		if ((int)result.get("code")==500) {
			throw new BusinessException("消息中心接口异常");
		}
	}

//	@Override
//	@Transactional(rollbackFor = Exception.class)
//	public void deleteMqMessage(final int shardingTotalCount, final int shardingItem, final String tags) {
//		// 分页参数每页5000条
//		int pageSize = 1000;
//		int messageType;
//		if (AliyunMqTopicConstants.MqTagEnum.DELETE_PRODUCER_MESSAGE.getTag().equals(tags)) {
//			messageType = MqMessageTypeEnum.PRODUCER_MESSAGE.messageType();
//		} else {
//			messageType = MqMessageTypeEnum.CONSUMER_MESSAGE.messageType();
//		}
//
//		int totalCount = mqMessageDataMapper.getBefore7DayTotalCount(shardingTotalCount, shardingItem, messageType);
//		if (totalCount == 0) {
//			return;
//		}
//		// 分页参数, 总页数
//		int pageNum = (totalCount - 1) / pageSize + 1;
//
//		for (int currentPage = 1; currentPage < pageNum; currentPage++) {
//			List<Long> idList = mqMessageDataMapper.getIdListBefore7Day(shardingTotalCount, shardingItem, messageType, currentPage, pageSize);
//			mqMessageDataMapper.batchDeleteByIdList(idList);
//		}
//	}

//	@Override
//	public void deleteMessageTopic(final String body, final String tags) {
//		ShardingContextDto shardingContextDto = JSON.parseObject(body, ShardingContextDto.class);
//		int shardingTotalCount = shardingContextDto.getShardingTotalCount();
//		int shardingItem = shardingContextDto.getShardingItem();
//
//		this.deleteMqMessage(shardingTotalCount, shardingItem, tags);
//	}

	@Override
	public List<String> queryMessageKeyList(final List<String> messageKeyList) {
		return mqMessageDataMapper.queryMessageKeyList(messageKeyList);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveWaitConfirmMessage(final MqMessageData mqMessageData) {
		//持久化到本地mysql
		this.saveMqProducerMessage(mqMessageData);
		// 发送预发送状态的消息给消息中心
		TpcMqMessageDto tpcMqMessageDto = mqMessageData.getTpcMqMessageDto();
		tpcMqMessageFeignApi.saveMessageWaitingConfirm(tpcMqMessageDto); //saveMessageWaitingConfirm持久化TpcMqMessage
		log.info("<== saveWaitConfirmMessage - 存储预发送消息成功. messageKey={}", mqMessageData.getMessageKey());
	}

	@Override
	public void saveAndSendMessage(final MqMessageData mqMessageData) {
		this.saveMqProducerMessage(mqMessageData);
		// 发送预发送状态的消息给消息中心
		TpcMqMessageDto tpcMqMessageDto = mqMessageData.getTpcMqMessageDto();
		//调用远端可靠消息服务 持久化等待确认消息
		tpcMqMessageFeignApi.saveAndSendMessage(tpcMqMessageDto);
		//mqMessageData 此时为调用远端服务返回来的数据
		log.info("<== saveAndSendMessage - 保存并送消息成功. messageKey={}", mqMessageData.getMessageKey());
	}

	@Override
	public void directSendMessage(final MqMessageData mqMessageData) {
		// 发送预发送状态的消息给消息中心
		TpcMqMessageDto tpcMqMessageDto = mqMessageData.getTpcMqMessageDto();
		tpcMqMessageFeignApi.directSendMessage(tpcMqMessageDto);
		log.info("<== directSendMessage - 直接送消息成功. messageKey={}", mqMessageData.getMessageKey());
	}

	@Override
	public R queryMessageListWithPage(final MessageQueryDto messageQueryDto) {
		PageHelper.startPage(messageQueryDto.getPageNum(), messageQueryDto.getPageSize());
		List<MqMessageVo> list = mqMessageDataMapper.queryMessageListWithPage(messageQueryDto);

		if (StringUtils.isEmpty(list)) {
			list = Lists.newArrayList();
		}
		return R.data(new PageInfo<>(list));
	}
}
