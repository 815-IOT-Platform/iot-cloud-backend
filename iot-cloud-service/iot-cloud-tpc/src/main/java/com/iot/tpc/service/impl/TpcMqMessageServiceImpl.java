package com.iot.tpc.service.impl;

import com.iot.common.core.dto.MessageQueryDto;
import com.iot.common.core.service.BaseService;
import com.iot.common.exception.BusinessException;
import com.iot.common.zk.generator.UniqueIdGenerator;
import com.iot.tpc.domain.TpcMqConfirm;
import com.iot.tpc.domain.TpcMqMessage;
import com.iot.tpc.dto.MessageTaskQueryDto;
import com.iot.tpc.dto.TpcMqMessageDto;
import com.iot.tpc.enums.MqSendStatusEnum;
import com.iot.tpc.mapper.TpcMqConfirmMapper;
import com.iot.tpc.mapper.TpcMqMessageMapper;
import com.iot.tpc.mq.RocketMqProducer;
import com.iot.tpc.service.ITpcMqConsumerService;
import com.iot.tpc.service.ITpcMqMessageService;
import com.iot.tpc.vo.TpcMessageVo;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;



/**
 * 可靠消息Service业务层处理
 * 
 * @author ananops
 * @date 2020-06-17
 */
@Service
@Slf4j
@Transactional(rollbackFor = Throwable.class)
public class TpcMqMessageServiceImpl extends BaseService<TpcMqMessage> implements ITpcMqMessageService {
    @Resource
    private TpcMqMessageMapper tpcMqMessageMapper;
    @Resource
    private TpcMqConfirmMapper tpcMqConfirmMapper;
    @Resource
    private ITpcMqConsumerService tpcMqConsumerService;

    @Override
    public void saveMessageWaitingConfirm(TpcMqMessageDto messageDto) {

        if (StringUtils.isEmpty(messageDto.getMessageTopic())) {
            throw new BusinessException("消息的消费Topic不能为空");
        }

        Date now = new Date();
        TpcMqMessage message = new ModelMapper().map(messageDto, TpcMqMessage.class);
        //消息状态：WAIT_SEND(10 未发送) SENDING(20 已发送) FINISH(30 已完成)
        message.setMessageStatus(MqSendStatusEnum.WAIT_SEND.sendStatus());
        message.setUpdateTime(now);
        message.setCreateTime(now);
        tpcMqMessageMapper.insertSelective(message); //持久化到表TpcMqMessage
    }

    @Override
    public void confirmAndSendMessage(String messageKey) {
        final TpcMqMessage message = tpcMqMessageMapper.getByMessageKey(messageKey);
        if (message == null) {
            throw new BusinessException("根据消息key查找的消息为空");
        }
        TpcMqMessage update = new TpcMqMessage();
        update.setMessageStatus(MqSendStatusEnum.SENDING.sendStatus());
        update.setId(message.getId());
        update.setUpdateTime(new Date());
        //1 更新消息状态为：SENDING
        tpcMqMessageMapper.updateByPrimaryKeySelective(update);
        //2 创建消费待确认列表(此处topic:SEND_EMAIL_TOPIC) 根据表pc_tpc_mq_subscribe查询出不同topic下相对应的所有consumer_code(消费监听者)即设置该消息被那些服务（CID）监听消费
        this.createMqConfirmListByTopic(message.getMessageTopic(), message.getId(), message.getMessageKey());
        //3 直接发送消息 到中间件RocketMQ队列中
        this.directSendMessage(message.getMessageBody(), message.getMessageTopic(), message.getMessageTag(), message.getMessageKey(), message.getProducerGroup(), message.getDelayLevel());
    }

    @Override
    public void saveAndSendMessage(TpcMqMessageDto tpcMqMessageDto) {
        if (StringUtils.isEmpty(tpcMqMessageDto.getMessageTopic())) {
            throw new BusinessException("消息的消费Topic不能为空");
        }

        Date now = new Date();
        TpcMqMessage message = new ModelMapper().map(tpcMqMessageDto, TpcMqMessage.class);
        message.setMessageStatus(MqSendStatusEnum.SENDING.sendStatus());
        message.setId(generateId());
        message.setUpdateTime(now);
        message.setCreateTime(now);

        tpcMqMessageMapper.insertSelective(message);
        // 创建消费待确认列表
        this.createMqConfirmListByTopic(message.getMessageTopic(), message.getId(), message.getMessageKey());
        this.directSendMessage(tpcMqMessageDto.getMessageBody(), tpcMqMessageDto.getMessageTopic(), tpcMqMessageDto.getMessageTag(), tpcMqMessageDto.getMessageKey(), tpcMqMessageDto.getProducerGroup(), tpcMqMessageDto.getDelayLevel());
    }

    @Override
    public void directSendMessage(String body, String topic, String tag, String key, String pid, Integer delayLevel) {
        RocketMqProducer.sendSimpleMessage(body, topic, tag, key, pid, delayLevel);
    }

    @Override
    public void resendMessageByMessageId(Long messageId) {
        final TpcMqMessage message = tpcMqMessageMapper.selectByPrimaryKey(messageId);
        if (message == null) {
            throw new BusinessException("根据任务Id查找的消息为空");
        }
        this.resendMessage(message);
    }

    @Override
    public void resendMessageByMessageKey(String messageKey) {
        final TpcMqMessage task = tpcMqMessageMapper.getByMessageKey(messageKey);
        this.resendMessage(task);
    }

    @Override
    public void setMessageToAlreadyDead(Long messageId) {
        final TpcMqMessage task = tpcMqMessageMapper.selectByPrimaryKey(messageId);
        if (task == null) {
            throw new BusinessException("根据任务Id查找的消息为空");
        }

        tpcMqMessageMapper.updateAlreadyDeadByMessageId(messageId);
    }

    @Override
    public void deleteMessageByMessageKey(String messageKey) {

        int result = tpcMqMessageMapper.deleteMessageByMessageKey(messageKey);
        if (result < 1) {
            throw new BusinessException("删除消息失败");
        }
    }

    @Override
    public void resendAllDeadMessageByTopicName(String topicName, int batchSize) {
        // 1.查询该topic下所有死亡的消息
        // 2.分页
    }

    @Override
    public List<TpcMqMessage> listMessageForWaitingProcess(MessageTaskQueryDto query) {
        return tpcMqMessageMapper.listMessageForWaitingProcess(query);
    }

    @Override
    public void confirmReceiveMessage(final String cid, final String messageKey) {
        // 1. 校验cid
        // 2. 校验messageKey
        // 3. 校验cid 和 messageKey
        Long confirmId = tpcMqConfirmMapper.getIdMqConfirm(cid, messageKey);
        // 3. 更新消费信息的状态
        tpcMqConfirmMapper.confirmReceiveMessage(confirmId);
    }

    @Override
    public void confirmConsumedMessage(final String cid, final String messageKey) {
        Long confirmId = tpcMqConfirmMapper.getIdMqConfirm(cid, messageKey);
        tpcMqConfirmMapper.confirmConsumedMessage(confirmId);
    }

    @Override
    public int updateMqConfirmStatus(final String cid, final String messageKey) {
        return 0;
    }

    @Override
    public void createMqConfirmListByTopic(final String topic, final Long messageId, final String messageKey) {
        List<TpcMqConfirm> list = Lists.newArrayList();
        TpcMqConfirm tpcMqConfirm;
        List<String> consumerGroupList = tpcMqConsumerService.listConsumerGroupByTopic(topic);
        logger.info("topic={},consumergrouplist={}",topic,consumerGroupList);
        if (com.iot.common.utils.StringUtils.isEmpty(consumerGroupList)) {
            throw new BusinessException("无消费者订阅");
        }
        for (final String cid : consumerGroupList) {
            tpcMqConfirm = new TpcMqConfirm(UniqueIdGenerator.generateId(), messageId, messageKey, cid);
            list.add(tpcMqConfirm);
        }

        tpcMqConfirmMapper.batchCreateMqConfirm(list);
    }

    @Override
    public List<String> queryWaitingConfirmMessageKeyList(final MessageTaskQueryDto query) {
        return tpcMqMessageMapper.queryWaitingConfirmMessageKeyList(query);
    }

    @Override
    public void handleWaitingConfirmMessage(final List<String> deleteKeyList, final List<String> resendKeyList) {
        logger.info("deleteKeyList is {}",deleteKeyList);
        if (deleteKeyList==null || deleteKeyList.size()==0) {
            return;
        }
        tpcMqMessageMapper.batchDeleteMessage(deleteKeyList);
        if (resendKeyList==null || resendKeyList.size()==0) {
            return;
        }
        for (final String messageKey : resendKeyList) {
            this.confirmAndSendMessage(messageKey);
        }
    }

    @Override
    public int updateMqMessageTaskStatus(final TpcMqMessage message) {
        return tpcMqMessageMapper.updateMqMessageTaskStatus(message);
    }

    @Override
    public int updateMqMessageStatus(final TpcMqMessage update) {
        return tpcMqMessageMapper.updateByPrimaryKeySelective(update);
    }

    @Override
    public List<TpcMessageVo> listReliableMessageVo(final MessageQueryDto messageQueryDto) {
        return tpcMqMessageMapper.listReliableMessageVoWithPage(messageQueryDto);
    }

    @Override
    public List<TpcMessageVo> listReliableMessageVo(final List<Long> messageIdList) {
        return tpcMqMessageMapper.listReliableMessageVo(messageIdList);
    }

    private void resendMessage(TpcMqMessage message) {
        if (message == null) {
            throw new BusinessException("根据消息key查找的消息为空");
        }
        tpcMqMessageMapper.addTaskExeCountById(message.getId());
        //TODO 记录重发日志 1.系统自动重发 2.人工重发
        this.directSendMessage(message.getMessageBody(), message.getMessageTopic(), message.getMessageTag(), message.getMessageKey(), message.getProducerGroup(), message.getDelayLevel());
    }
}