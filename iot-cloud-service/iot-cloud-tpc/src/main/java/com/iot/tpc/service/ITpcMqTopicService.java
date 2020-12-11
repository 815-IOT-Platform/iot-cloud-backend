package com.iot.tpc.service;

import com.iot.common.core.dto.LoginAuthDto;
import com.iot.common.core.service.IService;
import com.iot.tpc.domain.TpcMqTag;
import com.iot.tpc.domain.TpcMqTopic;
import com.iot.tpc.dto.AddTopicDto;
import com.iot.tpc.dto.TopicBindTagDto;
import com.iot.tpc.vo.TpcMqTopicVo;

import java.util.List;

/**
 * MQ主题Service接口
 * 
 * @author ananops
 * @date 2020-06-17
 */
public interface ITpcMqTopicService extends IService<TpcMqTopic> {
    /**
     * 添加主题
     * @param addTopicDto
     * @param loginAuthDto
     * @return
     */
    TpcMqTopic addMqTopic(AddTopicDto addTopicDto, LoginAuthDto loginAuthDto);

    /**
     * 为主题绑定标签
     * @param topicBindTagDto
     * @param loginAuthDto
     * @return
     */
    TpcMqTag topicBindTag(TopicBindTagDto topicBindTagDto, LoginAuthDto loginAuthDto);
    /**
     * 查询MQ topic列表.
     *
     * @param mdcMqTopic the mdc mq topic
     *
     * @return the list
     */
    List<TpcMqTopicVo> listWithPage(TpcMqTopic mdcMqTopic);

}
