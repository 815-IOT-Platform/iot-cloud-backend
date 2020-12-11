package com.iot.tpc.mapper;

import com.iot.common.core.dao.BaseMapper;
import com.iot.tpc.domain.TpcMqTopic;
import com.iot.tpc.vo.TpcMqTopicVo;

import java.util.List;

/**
 * MQ主题Mapper接口
 * 
 * @author ananops
 * @date 2020-06-17
 */
public interface TpcMqTopicMapper extends BaseMapper<TpcMqTopic>
{
    /**
     * List tpc mq topic vo with page list.
     *
     * @param tpcMqTopic the tpc mq topic
     *
     * @return the list
     */
    List<TpcMqTopicVo> listTpcMqTopicVoWithPage(TpcMqTopic tpcMqTopic);
}
