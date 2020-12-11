package com.iot.tpc.service;

import com.iot.common.core.dto.LoginAuthDto;
import com.iot.common.core.service.IService;
import com.iot.tpc.domain.TpcMqConsumer;
import com.iot.tpc.domain.TpcMqSubscribe;
import com.iot.tpc.dto.AddMqConsumerDto;
import com.iot.tpc.dto.ConsumerSubscribeTopicDto;
import com.iot.tpc.vo.TpcMqConsumerVo;
import com.iot.tpc.vo.TpcMqSubscribeVo;

import java.util.List;

/**
 * 消费者Service接口
 * 
 * @author ananops
 * @date 2020-06-17
 */
public interface ITpcMqConsumerService extends IService<TpcMqConsumer>
{
    /**
     * 创建一个消费者
     * @param addMqConsumerDto
     * @return
     */
    TpcMqConsumer addConsumer(AddMqConsumerDto addMqConsumerDto, LoginAuthDto loginAuthDto);

    TpcMqSubscribe consumerSubcribeTopic(ConsumerSubscribeTopicDto consumerSubscribeTopicDto);

    /**
     * 查询Mq消费者列表.
     *
     * @param tpcMqConsumer the tpc mq consumer
     *
     * @return the list
     */
    List<TpcMqConsumerVo> listConsumerVoWithPage(TpcMqConsumer tpcMqConsumer);

    /**
     * 查询订阅者列表.
     *
     * @param tpcMqConsumer the tpc mq consumer
     *
     * @return the list
     */
    List<TpcMqSubscribeVo> listSubscribeVoWithPage(TpcMqConsumer tpcMqConsumer);

    /**
     * Delete by tag id.
     *
     * @param tagId the tag id
     *
     * @return the int
     */
    int deleteSubscribeTagByTagId(Long tagId);

    /**
     * 根据消费者ID删除消费者.
     *
     * @param id the id
     *
     * @return the int
     */
    int deleteConsumerById(Long id);

    /**
     * List subscribe vo list.
     *
     * @param subscribeIdList the subscribe id list
     *
     * @return the list
     */
    List<TpcMqSubscribeVo> listSubscribeVo(List<Long> subscribeIdList);

    /**
     * List consumer group by topic list.
     *
     * @param topic the topic
     *
     * @return the list
     */
    List<String> listConsumerGroupByTopic(String topic);

    /**
     * 根据cid更新生产者状态为在线.
     *
     * @param consumerGroup the consumer group
     */
    void updateOnLineStatusByCid(String consumerGroup);

    /**
     * 根据cid更新生产者状态为离线.
     *
     * @param consumerGroup the consumer group
     */
    void updateOffLineStatusByCid(String consumerGroup);
}
